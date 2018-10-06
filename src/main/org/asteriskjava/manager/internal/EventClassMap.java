package org.asteriskjava.manager.internal;

import java.util.Locale;
import java.util.Map;

import org.asteriskjava.manager.event.ManagerEvent;
import org.asteriskjava.manager.event.UserEvent;

class EventClassMap extends ClassMap<ManagerEvent>
{
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

	public ManagerEvent newInstance(Map<String, String> attrs, Object source)
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
