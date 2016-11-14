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

import java.io.IOException;
import java.net.Socket;

import org.asteriskjava.fastagi.AgiChannel;
import org.asteriskjava.fastagi.AgiHangupException;
import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.fastagi.InvalidCommandSyntaxException;
import org.asteriskjava.fastagi.InvalidOrUnknownCommandException;
import org.asteriskjava.fastagi.command.AgiCommand;
import org.asteriskjava.fastagi.command.AnswerCommand;
import org.asteriskjava.fastagi.command.BackgroundCommand;
import org.asteriskjava.fastagi.command.ChannelStatusCommand;
import org.asteriskjava.fastagi.command.ControlStreamFileCommand;
import org.asteriskjava.fastagi.command.DatabaseDelCommand;
import org.asteriskjava.fastagi.command.DatabaseDelTreeCommand;
import org.asteriskjava.fastagi.command.DatabaseGetCommand;
import org.asteriskjava.fastagi.command.DatabasePutCommand;
import org.asteriskjava.fastagi.command.ExecCommand;
import org.asteriskjava.fastagi.command.GetDataCommand;
import org.asteriskjava.fastagi.command.GetFullVariableCommand;
import org.asteriskjava.fastagi.command.GetOptionCommand;
import org.asteriskjava.fastagi.command.GetVariableCommand;
import org.asteriskjava.fastagi.command.HangupCommand;
import org.asteriskjava.fastagi.command.PlaybackCommand;
import org.asteriskjava.fastagi.command.RecordFileCommand;
import org.asteriskjava.fastagi.command.SayAlphaCommand;
import org.asteriskjava.fastagi.command.SayDateTimeCommand;
import org.asteriskjava.fastagi.command.SayDigitsCommand;
import org.asteriskjava.fastagi.command.SayNumberCommand;
import org.asteriskjava.fastagi.command.SayPhoneticCommand;
import org.asteriskjava.fastagi.command.SayTimeCommand;
import org.asteriskjava.fastagi.command.SetAutoHangupCommand;
import org.asteriskjava.fastagi.command.SetCallerIdCommand;
import org.asteriskjava.fastagi.command.SetContextCommand;
import org.asteriskjava.fastagi.command.SetExtensionCommand;
import org.asteriskjava.fastagi.command.SetMusicOffCommand;
import org.asteriskjava.fastagi.command.SetMusicOnCommand;
import org.asteriskjava.fastagi.command.SetPriorityCommand;
import org.asteriskjava.fastagi.command.SetVariableCommand;
import org.asteriskjava.fastagi.command.StreamFileCommand;
import org.asteriskjava.fastagi.command.VerboseCommand;
import org.asteriskjava.fastagi.command.WaitForDigitCommand;
import org.asteriskjava.fastagi.reply.AgiReply;

/**
 * Default implementation of the AgiChannel interface.
 *
 * @author srt
 * @version $Id: AgiChannelImpl.java 633 2007-03-25 17:02:18Z srt $
 */
public class AgiChannelImpl implements AgiChannel
{
	private final AgiRequest request;
	private final AgiWriter agiWriter;
	private final AgiReader agiReader;

	public AgiChannelImpl(Socket socket)
		throws IOException
	{
		agiWriter = new AgiWriterImpl(socket);
		agiReader = new AgiReader(socket);

		request = agiReader.readRequest();
	}

	public AgiRequest getRequest()
	{
		return request;
	}

	public String getName()
	{
		return request.getChannel();
	}

	public String getUniqueId()
	{
		return request.getUniqueId();
	}

	private AgiReply sendCommand(AgiCommand command)
		throws IOException
	{
		return sendCommand(command.buildCommand());
	}

	public synchronized AgiReply sendCommand(String command)
		throws IOException
	{
		AgiReply reply;

		agiWriter.sendCommand(command);
		reply = agiReader.readReply();

		if (reply.getStatus() == AgiReply.SC_INVALID_OR_UNKNOWN_COMMAND)
			throw new InvalidOrUnknownCommandException(command);

		if (reply.getStatus() == AgiReply.SC_DEAD_CHANNEL)
			throw new AgiHangupException();

		if (reply.getStatus() == AgiReply.SC_INVALID_COMMAND_SYNTAX)
			throw new InvalidCommandSyntaxException(reply.getSynopsis(), reply.getUsage());

		return reply;
	}

	public void answer() throws IOException
	{
		sendCommand(new AnswerCommand());
	}

	public void hangup() throws IOException
	{
		sendCommand(new HangupCommand());
	}

	public void setAutoHangup(int time) throws IOException
	{
		sendCommand(new SetAutoHangupCommand(time));
	}

	public void setCallerId(String callerId) throws IOException
	{
		sendCommand(new SetCallerIdCommand(callerId));
	}

	public void playMusicOnHold() throws IOException
	{
		sendCommand(new SetMusicOnCommand());
	}

	public void playMusicOnHold(String musicOnHoldClass) throws IOException
	{
		sendCommand(new SetMusicOnCommand(musicOnHoldClass));
	}

	public void stopMusicOnHold() throws IOException
	{
		sendCommand(new SetMusicOffCommand());
	}

	public int getChannelStatus() throws IOException
	{
		AgiReply reply;

		reply = sendCommand(new ChannelStatusCommand());

		return reply.getResultCode();
	}

	public String getData(String file) throws IOException
	{
		AgiReply reply;

		reply = sendCommand(new GetDataCommand(file));

		return reply.getResult();
	}

	public String getData(String file, long timeout) throws IOException
	{
		AgiReply reply;

		reply = sendCommand(new GetDataCommand(file, timeout));

		return reply.getResult();
	}

	public String getData(String file, long timeout, int maxDigits)
		throws IOException
	{
		AgiReply reply;

		reply = sendCommand(new GetDataCommand(file, timeout, maxDigits));

		return reply.getResult();
	}

	public char getOption(String file, String escapeDigits)
		throws IOException
	{
		AgiReply reply;

		reply = sendCommand(new GetOptionCommand(file, escapeDigits));

		return reply.getResultCodeAsChar();
	}

	public char getOption(String file, String escapeDigits, int timeout)
		throws IOException
	{
		AgiReply reply;

		reply = sendCommand(new GetOptionCommand(file, escapeDigits, timeout));

		return reply.getResultCodeAsChar();
	}

	public int exec(String application) throws IOException
	{
		AgiReply reply;

		reply = sendCommand(new ExecCommand(application));

		return reply.getResultCode();
	}

	public int exec(String application, String options) throws IOException
	{
		AgiReply reply;

		reply = sendCommand(new ExecCommand(application, options));

		return reply.getResultCode();
	}

	@Override
	public int playback(String ... filenames)
		throws IOException
	{
		AgiReply reply;

		reply = sendCommand(new PlaybackCommand(filenames));

		return reply.getResultCode();
	}

	@Override
	public int background(String ... filenames)
		throws IOException
	{
		AgiReply reply;

		reply = sendCommand(new BackgroundCommand(filenames));

		return reply.getResultCode();
	}

	public void setContext(String context) throws IOException
	{
		sendCommand(new SetContextCommand(context));
	}

	public void setExtension(String extension) throws IOException
	{
		sendCommand(new SetExtensionCommand(extension));
	}

	public void setPriority(String priority) throws IOException
	{
		sendCommand(new SetPriorityCommand(priority));
	}

	public void streamFile(String file) throws IOException
	{
		sendCommand(new StreamFileCommand(file));
	}

	public char streamFile(String file, String escapeDigits)
		throws IOException
	{
		AgiReply reply;

		reply = sendCommand(new StreamFileCommand(file, escapeDigits));

		return reply.getResultCodeAsChar();
	}

	public void sayDigits(String digits) throws IOException
	{
		sendCommand(new SayDigitsCommand(digits));
	}

	public char sayDigits(String digits, String escapeDigits)
		throws IOException
	{
		AgiReply reply;

		reply = sendCommand(new SayDigitsCommand(digits, escapeDigits));

		return reply.getResultCodeAsChar();
	}

	public void sayNumber(String number) throws IOException
	{
		sendCommand(new SayNumberCommand(number));
	}

	public char sayNumber(String number, String escapeDigits)
		throws IOException
	{
		AgiReply reply;

		reply = sendCommand(new SayNumberCommand(number, escapeDigits));

		return reply.getResultCodeAsChar();
	}

	public void sayPhonetic(String text) throws IOException
	{
		sendCommand(new SayPhoneticCommand(text));
	}

	public char sayPhonetic(String text, String escapeDigits)
		throws IOException
	{
		AgiReply reply;

		reply = sendCommand(new SayPhoneticCommand(text, escapeDigits));

		return reply.getResultCodeAsChar();
	}

	public void sayAlpha(String text) throws IOException
	{
		sendCommand(new SayAlphaCommand(text));
	}

	public char sayAlpha(String text, String escapeDigits)
		throws IOException
	{
		AgiReply reply;

		reply = sendCommand(new SayAlphaCommand(text, escapeDigits));

		return reply.getResultCodeAsChar();
	}

	public void sayTime(long time) throws IOException
	{
		sendCommand(new SayTimeCommand(time));
	}

	public char sayTime(long time, String escapeDigits) throws IOException
	{
		AgiReply reply;

		reply = sendCommand(new SayTimeCommand(time, escapeDigits));

		return reply.getResultCodeAsChar();
	}

	public String getVariable(String name) throws IOException
	{
		AgiReply reply;

		reply = sendCommand(new GetVariableCommand(name));
		if (reply.getResultCode() != 1)
			return null;

		return reply.getExtra();
	}

	public void setVariable(String name, String value) throws IOException
	{
		sendCommand(new SetVariableCommand(name, value));
	}

	public char waitForDigit(int timeout) throws IOException
	{
		AgiReply reply;

		reply = sendCommand(new WaitForDigitCommand(timeout));

		return reply.getResultCodeAsChar();
	}

	public String getFullVariable(String name) throws IOException
	{
		AgiReply reply;

		reply = sendCommand(new GetFullVariableCommand(name));
		if (reply.getResultCode() != 1)
			return null;

		return reply.getExtra();
	}

	public String getFullVariable(String name, String channel) throws IOException
	{
		AgiReply reply;

		reply = sendCommand(new GetFullVariableCommand(name, channel));
		if (reply.getResultCode() != 1)
			return null;

		return reply.getExtra();
	}

	public char sayDateTime(long time, String escapeDigits, String format, String timezone) throws IOException
	{
		AgiReply reply;

		reply = sendCommand(new SayDateTimeCommand(time, escapeDigits, format, timezone));

		return reply.getResultCodeAsChar();
	}

	public char sayDateTime(long time, String escapeDigits, String format) throws IOException
	{
		AgiReply reply;

		reply = sendCommand(new SayDateTimeCommand(time, escapeDigits, format));

		return reply.getResultCodeAsChar();
	}

	public char sayDateTime(long time, String escapeDigits) throws IOException
	{
		AgiReply reply;

		reply = sendCommand(new SayDateTimeCommand(time, escapeDigits));

		return reply.getResultCodeAsChar();
	}

	public void sayDateTime(long time) throws IOException
	{
		sendCommand(new SayDateTimeCommand(time));
	}

	public String databaseGet(String family, String key) throws IOException
	{
		AgiReply reply;

		reply = sendCommand(new DatabaseGetCommand(family, key));
		if (reply.getResultCode() != 1)
			return null;

		return reply.getExtra();
	}

	public void databasePut(String family, String key, String value) throws IOException
	{
		sendCommand(new DatabasePutCommand(family, key, value));
	}

	public void databaseDel(String family, String key) throws IOException
	{
		sendCommand(new DatabaseDelCommand(family, key));
	}

	public void databaseDelTree(String family) throws IOException
	{
		sendCommand(new DatabaseDelTreeCommand(family));
	}

	public void databaseDelTree(String family, String keytree) throws IOException
	{
		sendCommand(new DatabaseDelTreeCommand(family, keytree));
	}

	public void verbose(String message, int level) throws IOException
	{
		sendCommand(new VerboseCommand(message, level));
	}

	public void recordFile(String file, String format, String escapeDigits, int timeout) throws IOException
	{
		sendCommand(new RecordFileCommand(file, format, escapeDigits, timeout));
	}

	public void recordFile(String file, String format, String escapeDigits, int timeout, int offset, boolean beep, int maxSilence) throws IOException
	{
		sendCommand(new RecordFileCommand(file, format, escapeDigits, timeout, offset, beep, maxSilence));
	}

	public void controlStreamFile(String file) throws IOException
	{
		sendCommand(new ControlStreamFileCommand(file));
	}

	public char controlStreamFile(String file, String escapeDigits) throws IOException
	{
		AgiReply reply;

		reply = sendCommand(new ControlStreamFileCommand(file, escapeDigits));

		return reply.getResultCodeAsChar();
	}

	public char controlStreamFile(String file, String escapeDigits, int offset) throws IOException
	{
		AgiReply reply;

		reply = sendCommand(new ControlStreamFileCommand(file, escapeDigits, offset));

		return reply.getResultCodeAsChar();
	}

	public char controlStreamFile(String file, String escapeDigits, int offset, String forwardDigit, String rewindDigit, String pauseDigit) throws IOException
	{
		AgiReply reply;

		reply = sendCommand(new ControlStreamFileCommand(file, escapeDigits, offset, forwardDigit, rewindDigit, pauseDigit));

		return reply.getResultCodeAsChar();
	}
}
