package org.asteriskjava.manager.response;

import org.asteriskjava.manager.annotation.Property;

public class CoreSettingsResponse extends ManagerResponse
{
	private static final long serialVersionUID = 1l;

	private String amiVer;
	private String astVer;
	private String sysName;
	private Integer maxCalls;
	private Double maxLoadAvg;
	private String runUser;
	private String runGroup;
	private Integer maxFilehandles;
	private Boolean realtimeEnabled;
	private Boolean cdrEnabled;
	private Boolean httpEnabled;

	public String getAmiVer()
	{
		return amiVer;
	}

	@Property(name = "AMIversion")
	public void setAmiVer(String amiVer)
	{
		this.amiVer = amiVer;
	}

	public String getAstVer()
	{
		return astVer;
	}

	@Property(name = "AsteriskVersion")
	public void setAstVer(String astVer)
	{
		this.astVer = astVer;
	}

	public String getSysName()
	{
		return sysName;
	}

	@Property(name = "SystemName")
	public void setSysName(String sysName)
	{
		this.sysName = sysName;
	}

	public Integer getMaxCalls()
	{
		return maxCalls;
	}

	@Property(name = "CoreMaxCalls")
	public void setMaxCalls(Integer maxCalls)
	{
		this.maxCalls = maxCalls;
	}

	public Double getMaxLoadAvg()
	{
		return maxLoadAvg;
	}

	@Property(name = "CoreMaxLoadAvg")
	public void setMaxLoadAvg(Double maxLoadAvg)
	{
		this.maxLoadAvg = maxLoadAvg;
	}

	public String getRunUser()
	{
		return runUser;
	}

	@Property(name = "CoreRunUser")
	public void setRunUser(String runUser)
	{
		this.runUser = runUser;
	}

	public String getRunGroup()
	{
		return runGroup;
	}

	@Property(name = "CoreRunGroup")
	public void setRunGroup(String runGroup)
	{
		this.runGroup = runGroup;
	}

	public Integer getMaxFilehandles()
	{
		return maxFilehandles;
	}

	@Property(name = "CoreMaxFilehandles")
	public void setMaxFilehandles(Integer maxFilehandles)
	{
		this.maxFilehandles = maxFilehandles;
	}

	public Boolean getRealtimeEnabled()
	{
		return realtimeEnabled;
	}

	@Property(name = "CoreRealTimeEnabled")
	public void setRealtimeEnabled(Boolean realtimeEnabled)
	{
		this.realtimeEnabled = realtimeEnabled;
	}

	public Boolean getCdrEnabled()
	{
		return cdrEnabled;
	}

	@Property(name = "CoreCDRenabled")
	public void setCdrEnabled(Boolean cdrEnabled)
	{
		this.cdrEnabled = cdrEnabled;
	}

	public Boolean getHttpEnabled()
	{
		return httpEnabled;
	}

	@Property(name = "CoreHTTPenabled")
	public void setHttpEnabled(Boolean httpEnabled)
	{
		this.httpEnabled = httpEnabled;
	}
}