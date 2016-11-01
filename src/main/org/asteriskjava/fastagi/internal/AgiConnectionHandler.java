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
import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.fastagi.AgiScript;
import org.asteriskjava.fastagi.MappingStrategy;
import org.asteriskjava.util.Log;
import org.asteriskjava.util.LogFactory;

/**
 * An AgiConnectionHandler is created and run by the AgiServer whenever a new
 * socket connection from an Asterisk Server is received.
 * <p>
 * It reads the request using an AgiReader and runs the AgiScript configured to
 * handle this type of request. Finally it closes the socket connection.
 *
 * @author srt
 * @version $Id: AgiConnectionHandler.java 633 2007-03-25 17:02:18Z srt $
 */
public class AgiConnectionHandler implements Runnable
{
	private static final String AJ_AGISTATUS_VARIABLE = "AJ_AGISTATUS";
	private static final String AJ_AGISTATUS_NOT_FOUND = "NOT_FOUND";
	private static final String AJ_AGISTATUS_SUCCESS = "SUCCESS";
	private static final String AJ_AGISTATUS_FAILED = "FAILED";
	private final Log logger = LogFactory.getLog(getClass());

	/**
	 * The socket connection.
	 */
	private final Socket socket;

	/**
	 * The AGI channel
	 */
	private final AgiChannel channel;

	/**
	 * The strategy to use to determine which script to run.
	 */
	private final MappingStrategy mappingStrategy;

	/**
	 * Creates a new AGIConnectionHandler to handle the given socket connection.
	 *
	 * @param socket the socket connection to handle.
	 * @param mappingStrategy the strategy to use to determine which script to
	 *            run.
	 */
	public AgiConnectionHandler(Socket socket, MappingStrategy mappingStrategy)
		throws IOException
	{
		this.socket = socket;
		this.mappingStrategy = mappingStrategy;

		channel = new AgiChannelImpl(socket);
	}

	public void run()
	{
		try
		{
			AgiRequest request;
			AgiScript script;

			request = channel.getRequest();

			script = mappingStrategy.determineScript(request);
			if (script == null)
			{
				final String errorMessage;

				errorMessage = "No script configured for URL '" + request.getRequestURL() + "' (script '"
						+ request.getScript() + "')";
				logger.error(errorMessage);

				channel.setVariable(AJ_AGISTATUS_VARIABLE, AJ_AGISTATUS_NOT_FOUND);
				channel.verbose(errorMessage, 1);
			}
			else
				runScript(script, request, channel);
		}
		catch (IOException e)
		{
			logger.error(String.format("IOException while handling request: %s", e.getMessage()));
		}
		finally
		{
			try
			{
				socket.close();
			}
			catch (IOException e) // NOPMD
			{
				// swallow
			}
		}
	}

	private void runScript(AgiScript script, AgiRequest request, AgiChannel channel)
		throws IOException
	{
		String threadName;
		threadName = Thread.currentThread().getName();

		logger.info("Begin AgiScript " + script.getClass().getName() + " on " + threadName);
		try
		{
			script.service(request, channel);
			channel.setVariable(AJ_AGISTATUS_VARIABLE, AJ_AGISTATUS_SUCCESS);
		}
		catch (Exception e)
		{
			logger.error("Exception running AgiScript " + script.getClass().getName() + " on " + threadName, e);
			channel.setVariable(AJ_AGISTATUS_VARIABLE, AJ_AGISTATUS_FAILED);
		}
		logger.info("End AgiScript " + script.getClass().getName() + " on " + threadName);
	}
}
