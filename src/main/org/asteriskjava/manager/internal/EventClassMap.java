package org.asteriskjava.manager.internal;

import java.util.Locale;
import java.util.Map;

import org.asteriskjava.manager.event.ManagerEvent;
import org.asteriskjava.manager.event.UserEvent;

class EventClassMap extends ClassMap<ManagerEvent>
{
	private static final long serialVersionUID = 1l;

	public EventClassMap()
	{
		super("Event", ManagerEvent.class, new Class[]{Object.class});
	}

	protected void regClass(String type, Class<? extends ManagerEvent> cls)
	{
		if (UserEvent.class.isAssignableFrom(cls) && !type.startsWith("userevent"))
			type = "userevent" + type;

		super.regClass(type, cls);
	}

	public Class<? extends ManagerEvent> get(Map<String, String> attrs)
	{
		Class<? extends ManagerEvent> cls;
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

		cls = get(type);
		if (cls == null)
			log.info(String.format("No class registered for event type '%s', attributes: %s", type, attrs));

		return cls;
	}
}
