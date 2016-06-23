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

import java.io.IOException;

import org.asteriskjava.manager.action.ManagerAction;
import org.asteriskjava.util.SocketConnectionFacade;


/**
 * The ManagerWriter transforms actions using an ActionBuilder and sends them to
 * the asterisk server.<p>
 * This class is intended to be used only by the DefaultManagerConnection.
 * 
 * @see org.asteriskjava.manager.internal.ActionBuilder
 * @see org.asteriskjava.manager.DefaultManagerConnection
 * @author srt
 * @version $Id: ManagerWriter.java 397 2006-05-26 12:13:32Z srt $
 */
public interface ManagerWriter
{
    /**
     * Sets the socket to use for writing to Asterisk.
     * 
     * @param socket the socket to use for writing to Asterisk.
     */
    void setSocket(final SocketConnectionFacade socket);

    /**
     * Sends the given action to the asterisk server.
     * 
     * @param action the action to send to the asterisk server.
     * @param internalActionId the internal action id to add.
     * @throws IOException if there is a problem sending the action.
     */
    void sendAction(final ManagerAction action, String internalActionId) throws IOException;
}
