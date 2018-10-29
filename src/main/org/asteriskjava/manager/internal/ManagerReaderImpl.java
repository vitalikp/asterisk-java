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
package org.asteriskjava.manager.internal;

import org.asteriskjava.util.Log;
import org.asteriskjava.util.LogFactory;
import org.asteriskjava.util.SocketConnectionFacade;

import java.io.IOException;

public class ManagerReaderImpl implements Runnable
{
    /**
     * Instance logger.
     */
    private final Log logger = LogFactory.getLog(getClass());

    /**
     * The dispatcher to use for dispatching events and responses.
     */
    private final Dispatcher dispatcher;

    /**
     * The socket to use for reading from the asterisk server.
     */
    private SocketConnectionFacade socket;

    /**
     * Exception that caused this reader to terminate if any.
     */
    private IOException terminationException;

    /**
     * Creates a new ManagerReaderImpl.
     *
     * @param dispatcher the dispatcher to use for dispatching events and responses.
     */
    public ManagerReaderImpl(final Dispatcher dispatcher)
    {
        this.dispatcher = dispatcher;
    }

    /**
     * Sets the socket to use for reading from the asterisk server.
     *
     * @param socket the socket to use for reading from the asterisk server.
     */
    public void setSocket(final SocketConnectionFacade socket)
    {
        this.socket = socket;
    }

    private void readPrompt()
        throws IOException
    {
        String line;

        line = socket.readLine();

        // maybe we will find a better way to identify the protocol identifier but for now
        // this works quite well.
        if (line.startsWith("Asterisk Call Manager/") ||
                line.startsWith("Asterisk Call Manager Proxy/") ||
                line.startsWith("OpenPBX Call Manager/") ||
                line.startsWith("CallWeaver Call Manager/"))
        {
            dispatcher.onPrompt(line);
            return;
        }

        throw new IOException("AMI welcome prompt is missing!");
    }

    /**
     * Reads line by line from the asterisk server, sets the protocol identifier as soon as it is
     * received and dispatches the received events and responses via the associated dispatcher.
     */
    public void run()
    {
        AmiPacket packet;

        if (socket == null)
        {
            throw new IllegalStateException("Unable to run: socket is null.");
        }

        try
        {
            // welcome prompt
            readPrompt();

            // main loop
            while (socket.isConnected())
            {
                packet = new AmiPacket();
                packet.read(socket);
                dispatcher.dispatch(packet);
            }

            logger.debug("Reached end of stream, terminating reader.");
        }
        catch (IOException e)
        {
            this.terminationException = e;
            logger.info("Terminating reader thread: " + e.getMessage());
        }
        finally
        {
            // cleans resources and reconnects if needed
            dispatcher.onDisconnect();
        }
    }

    public IOException getTerminationException()
    {
        return terminationException;
    }
}
