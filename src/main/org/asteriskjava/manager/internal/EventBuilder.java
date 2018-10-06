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

import java.util.Map;

import org.asteriskjava.manager.event.ManagerEvent;
import org.asteriskjava.manager.event.ResponseEvent;

/**
 * Default implementation of the EventBuilder interface.
 *
 * @see org.asteriskjava.manager.event.ManagerEvent
 * @author srt
 * @version $Id: EventBuilder.java 815 2007-07-01 20:30:31Z srt $
 */
class EventBuilder
{
    private EventClassMap classMap;

    EventBuilder()
    {
        classMap = new EventClassMap();
    }

    @SuppressWarnings("unchecked")
    public final void registerEventClass(Class clazz)
    {
        classMap.regClass(clazz);
    }

    public ManagerEvent buildEvent(Object source, Map<String, String> attributes)
    {
        ManagerEvent event;

        event = classMap.newInstance(attributes, source);
        if (event == null)
            return null;

        // ResponseEvents are sent in response to a ManagerAction if the
        // response contains lots of data. They include the actionId of
        // the corresponding ManagerAction.
        if (event instanceof ResponseEvent)
        {
            ResponseEvent responseEvent;
            String actionId;

            responseEvent = (ResponseEvent) event;
            actionId = responseEvent.getActionId();
            if (actionId != null)
            {
                responseEvent.setActionId(ManagerUtil.stripInternalActionId(actionId));
                responseEvent.setInternalActionId(ManagerUtil.getInternalActionId(actionId));
            }
        }

        return event;
    }
}
