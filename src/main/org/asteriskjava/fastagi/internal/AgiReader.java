/*
 *  Copyright 2004-2006 Stefan Reuter
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.asteriskjava.fastagi.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.asteriskjava.fastagi.AgiHangupException;
import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.fastagi.reply.AgiReply;

/**
 * Default implementation of the AgiReader implementation.
 *
 * @author srt
 * @version $Id: AgiReaderImpl.java 580 2006-12-27 22:21:49Z srt $
 */
public class AgiReader
{
	private final Socket socket;
	private final BufferedReader reader;

	AgiReader(Socket socket)
		throws IOException
	{
		this.socket = socket;

		InputStream inputStream = socket.getInputStream();

		reader = new BufferedReader(new InputStreamReader(inputStream));
	}

	public AgiRequest readRequest() throws IOException
	{
		AgiRequestImpl request;

		request = new AgiRequestImpl();
		request.setLocalAddress(socket.getLocalAddress());
		request.setLocalPort(socket.getLocalPort());
		request.setRemoteAddress(socket.getInetAddress());
		request.setRemotePort(socket.getPort());

		String line;

		int pos;
		String name;
		String value;
		while ((line = reader.readLine()) != null)
		{
			if (line.length() == 0)
				break;

			if (!line.startsWith("agi_"))
				continue;

			pos = line.indexOf(':');
			if (pos < 0)
				continue;

			name = line.substring(4, pos).trim();
			value = line.substring(pos + 1).trim();

			request.set(name, value);
		}

		return request;
	}

	public List<String> readEOF()
		throws IOException
	{
		List<String> lines = new ArrayList<String>();

		String line;

		// read the remaining data
		while ((line = reader.readLine()) != null)
			lines.add(line);

		return lines;
	}

	public AgiReply readReply() throws IOException
	{
		AgiReply reply;
		List<String> lines;
		String line;

		lines = new ArrayList<String>();

		line = reader.readLine();

		if (line == null)
			throw new AgiHangupException();

		if ("HANGUP".equals(line))
		{
			// shutdown output stream
			socket.shutdownOutput();

			readEOF();

			throw new AgiHangupException();
		}

		lines.add(line);

		// read synopsis and usage if statuscode is 520
		if (line.startsWith(Integer.toString(AgiReply.SC_INVALID_COMMAND_SYNTAX)))
		{
			while ((line = reader.readLine()) != null)
			{
				lines.add(line);

				if (line.startsWith(Integer.toString(AgiReply.SC_INVALID_COMMAND_SYNTAX)))
					break;
			}
		}

		reply = new AgiReplyImpl(lines);

		return reply;
	}
}
