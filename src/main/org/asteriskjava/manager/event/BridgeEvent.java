package org.asteriskjava.manager.event;

public class BridgeEvent extends ManagerEvent
{
	private static final long serialVersionUID = 1l;

	private String bridgeState;
	private String bridgeType;

	/**
     * The name of the channel 1.
     */
    private String channel1;

    /**
     * The name of the channel 2.
     */
    private String channel2;

    /**
     * The unique id of the channel 1.
     */
    private String uniqueId1;

    /**
     * The unique id of the channel 2.
     */
    private String uniqueId2;

    private String callerId1;

    private String callerId2;

	public BridgeEvent(Object source)
	{
		super(source);
	}

	public String getBridgeState()
	{
		return bridgeState;
	}

	public void setBridgeState(String bridgeState)
	{
		this.bridgeState = bridgeState;
	}

	public String getBridgeType()
	{
		return bridgeType;
	}

	public void setBridgeType(String bridgeType)
	{
		this.bridgeType = bridgeType;
	}

	/**
     * Returns the name of the channel 1.
     *
     * @return the name of the channel 1.
     */
    public String getChannel1()
    {
        return channel1;
    }

    /**
     * Sets the name of the channel 1.
     *
     * @param channel1 the name of the channel 1.
     */
    public void setChannel1(String channel1)
    {
        this.channel1 = channel1;
    }

    /**
     * Returns the name of the channel 2.
     *
     * @return the name of the channel 2.
     */
    public String getChannel2()
    {
        return channel2;
    }

    /**
     * Sets the name of the channel 2.
     *
     * @param channel2 the name of the channel 2.
     */
    public void setChannel2(String channel2)
    {
        this.channel2 = channel2;
    }

    /**
     * Returns the unique id of the channel 1.
     *
     * @return the unique id of the channel 1.
     */
    public String getUniqueId1()
    {
        return uniqueId1;
    }

    /**
     * Sets the unique id of the channel 1.
     *
     * @param uniqueId1 the unique id of the channel 1.
     */
    public void setUniqueId1(String uniqueId1)
    {
        this.uniqueId1 = uniqueId1;
    }

    /**
     * Returns the unique id of the channel 2.
     *
     * @return the unique id of the channel 2.
     */
    public String getUniqueId2()
    {
        return uniqueId2;
    }

    /**
     * Sets the unique id of the channel 2.
     *
     * @param uniqueId2 the unique id of the channel 2.
     */
    public void setUniqueId2(String uniqueId2)
    {
        this.uniqueId2 = uniqueId2;
    }

    public String getCallerId1()
    {
        return callerId1;
    }

    public void setCallerId1(String callerId1)
    {
        this.callerId1 = callerId1;
    }

	public String getCallerId2()
	{
		return callerId2;
	}

	public void setCallerId2(String callerId2)
	{
		this.callerId2 = callerId2;
	}
}
