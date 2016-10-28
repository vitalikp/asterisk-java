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

import org.asteriskjava.fastagi.AgiChannel;
import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.fastagi.AgiScript;
import org.asteriskjava.fastagi.MappingStrategy;
import org.asteriskjava.util.Log;
import org.asteriskjava.util.LogFactory;
import org.asteriskjava.util.SocketConnectionFacade;

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
	private final SocketConnectionFacade socket;

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
	public AgiConnectionHandler(SocketConnectionFacade socket, MappingStrategy mappingStrategy)
	{
		this.socket = socket;
		this.mappingStrategy = mappingStrategy;
	}

	protected AgiReader createReader()
	{
		return new AgiReaderImpl(socket);
	}

	protected AgiWriter createWriter()
	{
		return new AgiWriterImpl(socket);
	}

	public void run()
	{
		AgiChannel channel = null;

		try
		{
			AgiReader reader;
			AgiWriter writer;
			AgiRequest request;
			AgiScript script;

			reader = createReader();
			writer = createWriter();

			request = reader.readRequest();
			channel = new AgiChannelImpl(request, writer, reader);

			script = mappingStrategy.determineScript(request);
			if (script == null)
			{
				final String errorMessage;

				errorMessage = "No script configured for URL '" + request.getRequestURL() + "' (script '"
						+ request.getScript() + "')";
				logger.error(errorMessage);

				setStatusVariable(channel, AJ_AGISTATUS_NOT_FOUND);
				logToAsterisk(channel, errorMessage);
			}
			else
			{
				runScript(script, request, channel);
			}
		}
		catch (Exception e)
		{
			setStatusVariable(channel, AJ_AGISTATUS_FAILED);
			logger.error("Unexpected Exception while handling request", e);
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
	{
		String threadName;
		threadName = Thread.currentThread().getName();

		logger.info("Begin AgiScript " + script.getClass().getName() + " on " + threadName);
		try
		{
			script.service(request, channel);
			setStatusVariable(channel, AJ_AGISTATUS_SUCCESS);
		}
		catch (Exception e)
		{
			logger.error("Exception running AgiScript " + script.getClass().getName() + " on " + threadName, e);
			setStatusVariable(channel, AJ_AGISTATUS_FAILED);
		}
		logger.info("End AgiScript " + script.getClass().getName() + " on " + threadName);
	}

	private void setStatusVariable(AgiChannel channel, String value)
	{
		if (channel == null)
		{
			return;
		}

		try
		{
			channel.setVariable(AJ_AGISTATUS_VARIABLE, value);
		}
		catch (Exception e) // NOPMD
		{
			// swallow
		}
	}

	private void logToAsterisk(AgiChannel channel, String message)
	{
		if (channel == null)
		{
			return;
		}

		try
		{
			channel.verbose(message, 1);
		}
		catch (Exception e) // NOPMD
		{
			// swallow
		}
	}
}
