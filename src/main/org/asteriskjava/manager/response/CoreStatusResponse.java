package org.asteriskjava.manager.response;

import java.util.Date;

import org.asteriskjava.manager.annotation.Property;

public class CoreStatusResponse extends ManagerResponse
{
	private static final long serialVersionUID = 1l;

	private Date startupDate;
	private Date reloadDate;
	private Integer currentCalls;

	public Date getStartupDate()
	{
		return startupDate;
	}

	@Property(name = "CoreStartupDate")
	public void setStartupDate(Date startupDate)
	{
		this.startupDate = startupDate;
	}

	public Date getReloadDate()
	{
		return reloadDate;
	}

	@Property(name = "CoreReloadDate")
	public void setReloadDate(Date reloadDate)
	{
		this.reloadDate = reloadDate;
	}

	public Integer getCurrentCalls()
	{
		return currentCalls;
	}

	@Property(name = "CoreCurrentCalls")
	public void setCurrentCalls(Integer currentCalls)
	{
		this.currentCalls = currentCalls;
	}
}
