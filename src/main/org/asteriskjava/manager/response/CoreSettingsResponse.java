package org.asteriskjava.manager.response;

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

	public void setAmiVer(String amiVer)
	{
		this.amiVer = amiVer;
	}

	public String getAstVer()
	{
		return astVer;
	}

	public void setAstVer(String astVer)
	{
		this.astVer = astVer;
	}

	public String getSysName()
	{
		return sysName;
	}

	public void setSysName(String sysName)
	{
		this.sysName = sysName;
	}

	public Integer getMaxCalls()
	{
		return maxCalls;
	}

	public void setMaxCalls(Integer maxCalls)
	{
		this.maxCalls = maxCalls;
	}

	public Double getMaxLoadAvg()
	{
		return maxLoadAvg;
	}

	public void setMaxLoadAvg(Double maxLoadAvg)
	{
		this.maxLoadAvg = maxLoadAvg;
	}

	public String getRunUser()
	{
		return runUser;
	}

	public void setRunUser(String runUser)
	{
		this.runUser = runUser;
	}

	public String getRunGroup()
	{
		return runGroup;
	}

	public void setRunGroup(String runGroup)
	{
		this.runGroup = runGroup;
	}

	public Integer getMaxFilehandles()
	{
		return maxFilehandles;
	}

	public void setMaxFilehandles(Integer maxFilehandles)
	{
		this.maxFilehandles = maxFilehandles;
	}

	public Boolean getRealtimeEnabled()
	{
		return realtimeEnabled;
	}

	public void setRealtimeEnabled(Boolean realtimeEnabled)
	{
		this.realtimeEnabled = realtimeEnabled;
	}

	public Boolean getCdrEnabled()
	{
		return cdrEnabled;
	}

	public void setCdrEnabled(Boolean cdrEnabled)
	{
		this.cdrEnabled = cdrEnabled;
	}

	public Boolean getHttpEnabled()
	{
		return httpEnabled;
	}

	public void setHttpEnabled(Boolean httpEnabled)
	{
		this.httpEnabled = httpEnabled;
	}
}