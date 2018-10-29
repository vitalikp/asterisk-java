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

/**
 * The Dispatcher defines the interface used for communication between
 * ManagerConnection and ManagerReader.
 *
 * <p>Do not use this interface in your code, it is intended to be used only by the
 * DefaultManagerConnection and its ManagerReader.
 *
 * @author srt
 * @version $Id: Dispatcher.java 421 2006-05-28 15:02:14Z srt $
 */
interface Dispatcher
{
    /**
     * This method is called by the reader whenever a ManagerEvent is received.
     * The event is dispatched to all registered ManagerEventHandlers.
     *
     * @param event the event received by the reader
     * @see ManagerReader
     */
    void dispatchEvent(ManagerEvent event);

    void onPrompt(String protoId);

    void onDisconnect();

    void dispatch(Packet packet);
}
