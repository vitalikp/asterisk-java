package org.asteriskjava.manager.internal;

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
}
