package org.asteriskjava.manager.internal;

import org.asteriskjava.manager.event.ManagerEvent;

class EventClassMap extends ClassMap<ManagerEvent>
{
	private static final long serialVersionUID = 1l;

	public EventClassMap()
	{
		super("Event", ManagerEvent.class, new Class[]{Object.class});
	}
}
