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

import org.asteriskjava.manager.event.AgentCallbackLoginEvent;
import org.asteriskjava.manager.event.AgentCallbackLogoffEvent;
import org.asteriskjava.manager.event.AgentCalledEvent;
import org.asteriskjava.manager.event.AgentCompleteEvent;
import org.asteriskjava.manager.event.AgentConnectEvent;
import org.asteriskjava.manager.event.AgentDumpEvent;
import org.asteriskjava.manager.event.AgentLoginEvent;
import org.asteriskjava.manager.event.AgentLogoffEvent;
import org.asteriskjava.manager.event.AgentsCompleteEvent;
import org.asteriskjava.manager.event.AgentsEvent;
import org.asteriskjava.manager.event.AlarmClearEvent;
import org.asteriskjava.manager.event.AlarmEvent;
import org.asteriskjava.manager.event.BridgeEvent;
import org.asteriskjava.manager.event.CdrEvent;
import org.asteriskjava.manager.event.ChannelReloadEvent;
import org.asteriskjava.manager.event.DbGetResponseEvent;
import org.asteriskjava.manager.event.DialEvent;
import org.asteriskjava.manager.event.DndStateEvent;
import org.asteriskjava.manager.event.ExtensionStatusEvent;
import org.asteriskjava.manager.event.FaxReceivedEvent;
import org.asteriskjava.manager.event.FullyBootedEvent;
import org.asteriskjava.manager.event.HangupEvent;
import org.asteriskjava.manager.event.HoldEvent;
import org.asteriskjava.manager.event.HoldedCallEvent;
import org.asteriskjava.manager.event.JoinEvent;
import org.asteriskjava.manager.event.LeaveEvent;
import org.asteriskjava.manager.event.LinkEvent;
import org.asteriskjava.manager.event.LogChannelEvent;
import org.asteriskjava.manager.event.ManagerEvent;
import org.asteriskjava.manager.event.MeetMeJoinEvent;
import org.asteriskjava.manager.event.MeetMeLeaveEvent;
import org.asteriskjava.manager.event.MeetMeMuteEvent;
import org.asteriskjava.manager.event.MeetMeStopTalkingEvent;
import org.asteriskjava.manager.event.MeetMeTalkingEvent;
import org.asteriskjava.manager.event.MessageWaitingEvent;
import org.asteriskjava.manager.event.NewAccountCodeEvent;
import org.asteriskjava.manager.event.NewCallerIdEvent;
import org.asteriskjava.manager.event.NewChannelEvent;
import org.asteriskjava.manager.event.NewExtenEvent;
import org.asteriskjava.manager.event.NewStateEvent;
import org.asteriskjava.manager.event.OriginateFailureEvent;
import org.asteriskjava.manager.event.OriginateResponseEvent;
import org.asteriskjava.manager.event.OriginateSuccessEvent;
import org.asteriskjava.manager.event.ParkedCallEvent;
import org.asteriskjava.manager.event.ParkedCallGiveUpEvent;
import org.asteriskjava.manager.event.ParkedCallTimeOutEvent;
import org.asteriskjava.manager.event.ParkedCallsCompleteEvent;
import org.asteriskjava.manager.event.PeerEntryEvent;
import org.asteriskjava.manager.event.PeerStatusEvent;
import org.asteriskjava.manager.event.PeerlistCompleteEvent;
import org.asteriskjava.manager.event.QueueCallerAbandonEvent;
import org.asteriskjava.manager.event.QueueEntryEvent;
import org.asteriskjava.manager.event.QueueMemberAddedEvent;
import org.asteriskjava.manager.event.QueueMemberEvent;
import org.asteriskjava.manager.event.QueueMemberPausedEvent;
import org.asteriskjava.manager.event.QueueMemberRemovedEvent;
import org.asteriskjava.manager.event.QueueMemberStatusEvent;
import org.asteriskjava.manager.event.QueueParamsEvent;
import org.asteriskjava.manager.event.QueueStatusCompleteEvent;
import org.asteriskjava.manager.event.QueueSummaryCompleteEvent;
import org.asteriskjava.manager.event.QueueSummaryEvent;
import org.asteriskjava.manager.event.RTcpReceivedEvent;
import org.asteriskjava.manager.event.RTcpSentEvent;
import org.asteriskjava.manager.event.RegistryEvent;
import org.asteriskjava.manager.event.ReloadEvent;
import org.asteriskjava.manager.event.RenameEvent;
import org.asteriskjava.manager.event.ResponseEvent;
import org.asteriskjava.manager.event.ShutdownEvent;
import org.asteriskjava.manager.event.StatusCompleteEvent;
import org.asteriskjava.manager.event.StatusEvent;
import org.asteriskjava.manager.event.UnholdEvent;
import org.asteriskjava.manager.event.UnlinkEvent;
import org.asteriskjava.manager.event.UnparkedCallEvent;
import org.asteriskjava.manager.event.ZapShowChannelsCompleteEvent;
import org.asteriskjava.manager.event.ZapShowChannelsEvent;

/**
 * Default implementation of the EventBuilder interface.
 *
 * @see org.asteriskjava.manager.event.ManagerEvent
 * @author srt
 * @version $Id: EventBuilderImpl.java 815 2007-07-01 20:30:31Z srt $
 */
class EventBuilderImpl
{
    private EventClassMap classMap;

    EventBuilderImpl()
    {
        classMap = new EventClassMap();
        registerBuiltinEventClasses();
    }

    @SuppressWarnings("deprecation")
    private void registerBuiltinEventClasses()
    {
        registerEventClass(AgentCallbackLoginEvent.class);
        registerEventClass(AgentCallbackLogoffEvent.class);
        registerEventClass(AgentCalledEvent.class);
        registerEventClass(AgentConnectEvent.class);
        registerEventClass(AgentCompleteEvent.class);
        registerEventClass(AgentDumpEvent.class);
        registerEventClass(AgentLoginEvent.class);
        registerEventClass(AgentLogoffEvent.class);
        registerEventClass(AgentsEvent.class);
        registerEventClass(AgentsCompleteEvent.class);
        registerEventClass(AlarmEvent.class);
        registerEventClass(AlarmClearEvent.class);
        registerEventClass(BridgeEvent.class);
        registerEventClass(CdrEvent.class);
        registerEventClass(ChannelReloadEvent.class);
        registerEventClass(DbGetResponseEvent.class);
        registerEventClass(DialEvent.class);
        registerEventClass(DndStateEvent.class);
        registerEventClass(ExtensionStatusEvent.class);
        registerEventClass(FaxReceivedEvent.class);
        registerEventClass(FullyBootedEvent.class);
        registerEventClass(HangupEvent.class);
        registerEventClass(HoldedCallEvent.class);
        registerEventClass(HoldEvent.class);
        registerEventClass(JoinEvent.class);
        registerEventClass(LeaveEvent.class);
        registerEventClass(LinkEvent.class);
        registerEventClass(LogChannelEvent.class);
        registerEventClass(MeetMeJoinEvent.class);
        registerEventClass(MeetMeLeaveEvent.class);
        registerEventClass(MeetMeMuteEvent.class);
        registerEventClass(MeetMeTalkingEvent.class);
        registerEventClass(MeetMeStopTalkingEvent.class);
        registerEventClass(MessageWaitingEvent.class);
        registerEventClass(NewAccountCodeEvent.class);
        registerEventClass(NewCallerIdEvent.class);
        registerEventClass(NewChannelEvent.class);
        registerEventClass(NewExtenEvent.class);
        registerEventClass(NewStateEvent.class);
        registerEventClass(OriginateFailureEvent.class);
        registerEventClass(OriginateSuccessEvent.class);
        registerEventClass(OriginateResponseEvent.class);
        registerEventClass(ParkedCallGiveUpEvent.class);
        registerEventClass(ParkedCallEvent.class);
        registerEventClass(ParkedCallTimeOutEvent.class);
        registerEventClass(ParkedCallsCompleteEvent.class);
        registerEventClass(PeerEntryEvent.class);
        registerEventClass(PeerlistCompleteEvent.class);
        registerEventClass(PeerStatusEvent.class);
        registerEventClass(QueueCallerAbandonEvent.class);
        registerEventClass(QueueEntryEvent.class);
        registerEventClass(QueueMemberAddedEvent.class);
        registerEventClass(QueueMemberEvent.class);
        registerEventClass(QueueMemberPausedEvent.class);
        registerEventClass(QueueMemberRemovedEvent.class);
        registerEventClass(QueueMemberStatusEvent.class);
        registerEventClass(QueueParamsEvent.class);
        registerEventClass(QueueStatusCompleteEvent.class);
        registerEventClass(QueueSummaryCompleteEvent.class);
        registerEventClass(QueueSummaryEvent.class);
        registerEventClass(RegistryEvent.class);
        registerEventClass(ReloadEvent.class);
        registerEventClass(RenameEvent.class);
        registerEventClass(RTcpReceivedEvent.class);
        registerEventClass(RTcpSentEvent.class);
        registerEventClass(ShutdownEvent.class);
        registerEventClass(StatusEvent.class);
        registerEventClass(StatusCompleteEvent.class);
        registerEventClass(UnholdEvent.class);
        registerEventClass(UnlinkEvent.class);
        registerEventClass(UnparkedCallEvent.class);
        registerEventClass(ZapShowChannelsEvent.class);
        registerEventClass(ZapShowChannelsCompleteEvent.class);
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
