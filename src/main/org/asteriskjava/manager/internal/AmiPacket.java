package org.asteriskjava.manager.internal;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.asteriskjava.util.SocketConnectionFacade;

public class AmiPacket implements Packet
{
	private static final String END_OF_STREAM_ERR = "EOS reached. No data available";

	private PackType packType = PackType.None;
	private String type;
	private String ID;
	private String privilege;
	private Date dateReceived;

	private final Map<String, String> props = new HashMap<String, String>();
	private final StringBuilder output = new StringBuilder();

	public PackType getPackType()
	{
		return packType;
	}

	public String getType()
	{
		return type;
	}

	public String getID()
	{
		return ID;
	}

	public String getPrivilege()
	{
		return privilege;
	}

	public Date getDateReceived()
	{
		return dateReceived;
	}

	public Map<String, String> getProps()
	{
		return props;
	}

	public String getOutput()
	{
		return output.toString();
	}

	private void decodeType(String name, String value)
	{
		switch (packType)
		{
			case None:
				if ("Response".equalsIgnoreCase(name))
				{
					packType = PackType.Response;
					type = value;
					return;
				}

				if ("Event".equalsIgnoreCase(name))
				{
					packType = PackType.Event;
					type = value;
					return;
				}
				break;

			case Event:
				// Change in Asterisk 1.4 where the name of the UserEvent is sent as property instead
				// of the event name (AJ-48)
				if ("UserEvent".equalsIgnoreCase(type))
				{
					if (value == null)
					{
						type = null;
						return;
					}

					type += value.toLowerCase(Locale.ENGLISH);
				}
				break;
		}
	}

	private int readLine(SocketConnectionFacade socket, StringBuilder buf)
		throws IOException
	{
		String line;

		line = socket.readLine();
		if (line == null)
			throw new IOException(END_OF_STREAM_ERR);

		buf.append(line);

		return line.length();
	}

	private void readOutput(SocketConnectionFacade socket)
		throws IOException
	{
		String line;
		int off;

		off = 0;
		while (readLine(socket, output) > 0)
		{
			line = output.substring(off);

			if ("--END COMMAND--".equals(line) || " --END COMMAND--".equals(line))
				break;

			output.append('\0');
			off += line.length() + 1;
		}

		output.delete(off, output.length());
	}

	public void read(SocketConnectionFacade socket)
		throws IOException
	{
		StringBuilder buf = new StringBuilder();
		String name;
		String value;
		String line;
		int index;

		while (readLine(socket, buf) > 0)
		{
			line = buf.toString();
			buf.setLength(0);

			index = line.indexOf(":");
			if (index < 0)
				continue; // unknown line, ignore

			if (index == 0 || line.length() - 2 <= index)
				continue;

			name = line.substring(0, index).toLowerCase(Locale.ENGLISH);
			value = line.substring(index + 2);

			if ("Privilege".equalsIgnoreCase(name))
			{
				privilege = value;
				continue;
			}

			if ("Output".equalsIgnoreCase(name))
			{
				output.append(value);
				output.append('\0');
				continue;
			}

			if ("ActionID".equalsIgnoreCase(name))
			{
				// decode internal action ID
				ID = ManagerUtil.getInternalActionId(value);
				value = ManagerUtil.stripInternalActionId(value);
				if ("Command".equalsIgnoreCase(privilege))
					readOutput(socket);
			}
			else
				decodeType(name, value);

			props.put(name, value);
		}

		dateReceived = new Date();
	}
}
