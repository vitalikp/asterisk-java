package org.asteriskjava.manager.event;

public class FullyBootedEvent extends ManagerEvent
{
	private static final long serialVersionUID = 1l;

	private String status;

	public FullyBootedEvent(Object source)
	{
		super(source);
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}
}
