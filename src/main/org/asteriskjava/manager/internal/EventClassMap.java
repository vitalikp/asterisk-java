package org.asteriskjava.manager.internal;

import java.util.Locale;
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
import org.asteriskjava.manager.event.ShutdownEvent;
import org.asteriskjava.manager.event.StatusCompleteEvent;
import org.asteriskjava.manager.event.StatusEvent;
import org.asteriskjava.manager.event.UnholdEvent;
import org.asteriskjava.manager.event.UnlinkEvent;
import org.asteriskjava.manager.event.UnparkedCallEvent;
import org.asteriskjava.manager.event.UserEvent;
import org.asteriskjava.manager.event.ZapShowChannelsCompleteEvent;
import org.asteriskjava.manager.event.ZapShowChannelsEvent;

class EventClassMap extends ClassMap<ManagerEvent>
{
	private final Object source;

	public EventClassMap(Object source)
	{
		super("Event", ManagerEvent.class, new Class[]{Object.class});
		this.source = source;

		regEventClasses();
	}

	@SuppressWarnings("deprecation")
	private void regEventClasses()
	{
		regClass(AgentCallbackLoginEvent.class);
		regClass(AgentCallbackLogoffEvent.class);
		regClass(AgentCalledEvent.class);
		regClass(AgentConnectEvent.class);
		regClass(AgentCompleteEvent.class);
		regClass(AgentDumpEvent.class);
		regClass(AgentLoginEvent.class);
		regClass(AgentLogoffEvent.class);
		regClass(AgentsEvent.class);
		regClass(AgentsCompleteEvent.class);
		regClass(AlarmEvent.class);
		regClass(AlarmClearEvent.class);
		regClass(BridgeEvent.class);
		regClass(CdrEvent.class);
		regClass(ChannelReloadEvent.class);
		regClass(DbGetResponseEvent.class);
		regClass(DialEvent.class);
		regClass(DndStateEvent.class);
		regClass(ExtensionStatusEvent.class);
		regClass(FaxReceivedEvent.class);
		regClass(FullyBootedEvent.class);
		regClass(HangupEvent.class);
		regClass(HoldedCallEvent.class);
		regClass(HoldEvent.class);
		regClass(JoinEvent.class);
		regClass(LeaveEvent.class);
		regClass(LinkEvent.class);
		regClass(LogChannelEvent.class);
		regClass(MeetMeJoinEvent.class);
		regClass(MeetMeLeaveEvent.class);
		regClass(MeetMeMuteEvent.class);
		regClass(MeetMeTalkingEvent.class);
		regClass(MeetMeStopTalkingEvent.class);
		regClass(MessageWaitingEvent.class);
		regClass(NewAccountCodeEvent.class);
		regClass(NewCallerIdEvent.class);
		regClass(NewChannelEvent.class);
		regClass(NewExtenEvent.class);
		regClass(NewStateEvent.class);
		regClass(OriginateFailureEvent.class);
		regClass(OriginateSuccessEvent.class);
		regClass(OriginateResponseEvent.class);
		regClass(ParkedCallGiveUpEvent.class);
		regClass(ParkedCallEvent.class);
		regClass(ParkedCallTimeOutEvent.class);
		regClass(ParkedCallsCompleteEvent.class);
		regClass(PeerEntryEvent.class);
		regClass(PeerlistCompleteEvent.class);
		regClass(PeerStatusEvent.class);
		regClass(QueueCallerAbandonEvent.class);
		regClass(QueueEntryEvent.class);
		regClass(QueueMemberAddedEvent.class);
		regClass(QueueMemberEvent.class);
		regClass(QueueMemberPausedEvent.class);
		regClass(QueueMemberRemovedEvent.class);
		regClass(QueueMemberStatusEvent.class);
		regClass(QueueParamsEvent.class);
		regClass(QueueStatusCompleteEvent.class);
		regClass(QueueSummaryCompleteEvent.class);
		regClass(QueueSummaryEvent.class);
		regClass(RegistryEvent.class);
		regClass(ReloadEvent.class);
		regClass(RenameEvent.class);
		regClass(RTcpReceivedEvent.class);
		regClass(RTcpSentEvent.class);
		regClass(ShutdownEvent.class);
		regClass(StatusEvent.class);
		regClass(StatusCompleteEvent.class);
		regClass(UnholdEvent.class);
		regClass(UnlinkEvent.class);
		regClass(UnparkedCallEvent.class);
		regClass(ZapShowChannelsEvent.class);
		regClass(ZapShowChannelsCompleteEvent.class);
	}

	protected void regClass(String type, Class<? extends ManagerEvent> cls)
	{
		if (UserEvent.class.isAssignableFrom(cls) && !type.startsWith("userevent"))
			type = "userevent" + type;

		super.regClass(type, cls);
	}

	private ClassType<ManagerEvent> get(Map<String, String> attrs)
	{
		ClassType<ManagerEvent> clsType;
		String type;

		type = attrs.get("event");
		if (type == null)
		{
			log.error("No event type in properties");
			return null;
		}
		type = type.toLowerCase(Locale.ENGLISH);

		// Change in Asterisk 1.4 where the name of the UserEvent is sent as property instead
		// of the event name (AJ-48)
		if ("userevent".equals(type))
		{
			if (attrs.get("userevent") == null)
			{
				log.error("No user event type in properties");
				return null;
			}

			type += attrs.get("userevent").toLowerCase(Locale.ENGLISH);
		}

		clsType = classes.get(type);
		if (clsType == null)
			log.info(String.format("No class registered for event type '%s', attributes: %s", type, attrs));

		return clsType;
	}

	private void setProps(ManagerEvent event, ClassType<ManagerEvent> clsType, Map<String, String> props)
	{
		String value;

		for (String name: props.keySet())
		{
			if ("event".equals(name))
				continue;

			/*
			 * The source property needs special handling as it is already
			 * defined in java.util.EventObject (the base class of
			 * ManagerEvent), so we have to translate it.
			 */
			if ("source".equals(name))
				name = "src";

			value = props.get(name);

			// ResponseEvents are sent in response to a ManagerAction if the
			// response contains lots of data. They include the actionId of
			// the corresponding ManagerAction.
			if ("actionID".equalsIgnoreCase(name))
			{
				try
				{
					clsType.setProp(event, "internal" + name, ManagerUtil.getInternalActionId(value));
					value = ManagerUtil.stripInternalActionId(value);
				}
				catch (Exception e)
				{
					log.error(String.format("Unable to set property '%s' to '%s' on %s: %s", "internal" + name, value, event.getClass().getName(), e.getMessage()), e.getCause());
				}
			}

			try
			{
				clsType.setProp(event, name, value);
			}
			catch (Exception e)
			{
				log.error(String.format("Unable to set property '%s' to '%s' on %s: %s", name, value, event.getClass().getName(), e.getMessage()), e.getCause());
			}
		}
	}

	public ManagerEvent newInstance(Map<String, String> attrs)
	{
		ClassType<ManagerEvent> clsType;
		ManagerEvent event;

		clsType = get(attrs);
		if (clsType == null)
			return null;

		event = super.newInstance(clsType, source);
		if (event == null)
			return null;

		setProps(event, clsType, attrs);

		return event;
	}
}
