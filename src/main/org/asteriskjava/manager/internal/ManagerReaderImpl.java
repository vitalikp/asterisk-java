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

import org.asteriskjava.manager.event.ManagerEvent;
import org.asteriskjava.manager.event.ProtocolIdentifierReceivedEvent;
import org.asteriskjava.manager.response.ManagerResponse;
import org.asteriskjava.util.DateUtil;
import org.asteriskjava.util.Log;
import org.asteriskjava.util.LogFactory;
import org.asteriskjava.util.SocketConnectionFacade;

import java.io.IOException;

/**
 * Default implementation of the ManagerReader interface.
 *
 * @author srt
 * @version $Id: ManagerReaderImpl.java 878 2007-08-01 22:04:11Z srt $
 */
public class ManagerReaderImpl implements ManagerReader
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
     * The source to use when creating {@link ManagerEvent}s.
     */
    private final Object source;

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
     * @param source     the source to use when creating {@link ManagerEvent}s
     */
    public ManagerReaderImpl(final Dispatcher dispatcher, Object source)
    {
        this.dispatcher = dispatcher;
        this.source = source;
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
        ProtocolIdentifierReceivedEvent event;
        String line;

        line = socket.readLine();

        // maybe we will find a better way to identify the protocol identifier but for now
        // this works quite well.
        if (line.startsWith("Asterisk Call Manager/") ||
                line.startsWith("Asterisk Call Manager Proxy/") ||
                line.startsWith("OpenPBX Call Manager/") ||
                line.startsWith("CallWeaver Call Manager/"))
        {
            event = new ProtocolIdentifierReceivedEvent(source);
            event.setProtocolIdentifier(line);
            event.setDateReceived(DateUtil.getDate());
            dispatcher.dispatchEvent(event);
            return;
        }

        throw new IOException("AMI welcome prompt is missing!");
    }

    /**
     * Reads line by line from the asterisk server, sets the protocol identifier (using a
     * generated {@link org.asteriskjava.manager.event.ProtocolIdentifierReceivedEvent}) as soon as it is
     * received and dispatches the received events and responses via the associated dispatcher.
     *
     * @see org.asteriskjava.manager.internal.Dispatcher#dispatchEvent(ManagerEvent)
     * @see org.asteriskjava.manager.internal.Dispatcher#dispatchResponse(ManagerResponse)
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
