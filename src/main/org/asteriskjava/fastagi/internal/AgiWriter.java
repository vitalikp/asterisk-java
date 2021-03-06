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

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Default implementation of the AGIWriter interface.
 *
 * @author srt
 * @version $Id: AgiWriterImpl.java 458 2006-07-05 22:32:02Z srt $
 */
class AgiWriter
{
	private final Socket socket;
	private final BufferedWriter writer;

	AgiWriter(Socket socket)
		throws IOException
	{
		this.socket = socket;

		OutputStream outputStream = socket.getOutputStream();

		writer = new BufferedWriter(new OutputStreamWriter(outputStream));
	}

	public void sendCommand(String command) throws IOException
	{
		writer.write(command + "\n");
		writer.flush();
	}

	public void shutdownOutput()
		throws IOException
	{
    	socket.shutdownOutput();
	}
}
