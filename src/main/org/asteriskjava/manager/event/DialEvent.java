/*
 *  Copyright 2004-2006 Stefan Reuter
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.asteriskjava.manager.event;

/**
 * A dial event is triggered whenever a phone attempts to dial someone.
 *
 * <p>This event is implemented in <code>apps/app_dial.c</code>.
 *
 * <p>Available since Asterisk 1.2.
 *
 * @author Asteria Solutions Group, Inc. <a href="http://www.asteriasgi.com">www.asteriasgi.com</a>
 * @version $Id: DialEvent.java 397 2006-05-26 12:13:32Z srt $
 * @since 0.2
 */
public class DialEvent extends ManagerEvent
{
    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = 3258130241292417336L;

    private String subEvent;

    /**
     * The name of the source channel.
     */
    private String channel;

    /**
     * The name of the destination channel.
     */
    private String destination;

    /**
     * The new Caller*ID Num.
     */
    private String callerIdNum;

    /**
     * The new Caller*ID Name.
     */
    private String callerIdName;

    private String connectedLineNum;

    private String connectedLineName;

    /**
     * The unique id of the source channel.
     */
    private String uniqueId;

    /**
     * The unique id of the destination channel.
     */
    private String destUniqueId;

    private String dialStatus;

    private String dialstring;

    public DialEvent(Object source)
    {
        super(source);
    }

    public String getSubEvent()
	{
		return subEvent;
	}

	public void setSubEvent(String subEvent)
	{
		this.subEvent = subEvent;
	}

	/**
     * Returns the name of the source channel.
     *
     * @return the name of the source channel.
     */
    public String getChannel()
    {
        return channel;
    }

    /**
     * Sets the name of the source channel.
     *
     * @param channel the name of the source channel.
     */
    public void setChannel(String channel)
    {
        this.channel = channel;
    }

    /**
     * Returns the name of the destination channel.
     *
     * @return the name of the destination channel.
     */
    public String getDestination()
    {
        return destination;
    }

    /**
     * Sets the name of the destination channel.
     *
     * @param destination the name of the destination channel.
     */
    public void setDestination(String destination)
    {
        this.destination = destination;
    }

    /**
     * Returns the Caller*ID Num.
     *
     * @return the Caller*ID Num or "&lt;unknown&gt;" if none has been set.
     */
    public String getCallerIdNum()
    {
        return callerIdNum;
    }

    /**
     * Sets the caller*ID Num.
     *
     * @param callerIdNum the caller*ID Num.
     */
    public void setCallerIdNum(String callerIdNum)
    {
        this.callerIdNum = callerIdNum;
    }

    /**
     * Returns the Caller*ID Name.
     *
     * @return the Caller*ID Name or "&lt;unknown&gt;" if none has been set.
     */
    public String getCallerIdName()
    {
        return callerIdName;
    }

    /**
     * Sets the Caller*Id Name.
     *
     * @param callerIdName the Caller*Id Name to set.
     */
    public void setCallerIdName(String callerIdName)
    {
        this.callerIdName = callerIdName;
    }

    public String getConnectedLineNum()
	{
		return connectedLineNum;
	}

	public void setConnectedLineNum(String connectedLineNum)
	{
		this.connectedLineNum = connectedLineNum;
	}

	public String getConnectedLineName()
	{
		return connectedLineName;
	}

	public void setConnectedLineName(String connectedLineName)
	{
		this.connectedLineName = connectedLineName;
	}

	/**
     * Returns the unique ID of the source channel.
     *
     * @return the unique ID of the source channel.
     */
    public String getUniqueId()
    {
        return uniqueId;
    }

    /**
     * Sets the unique ID of the source channel.
     *
     * @param uniqueId the unique ID of the source channel.
     */
    public void setUniqueId(String uniqueId)
    {
        this.uniqueId = uniqueId;
    }

    /**
     * Returns the unique ID of the destination channel.
     *
     * @return the unique ID of the destination channel.
     */
    public String getDestUniqueId()
    {
        return destUniqueId;
    }

    /**
     * Sets the unique ID of the destination channel.
     *
     * @param destUniqueId the unique ID of the destination channel.
     */
    public void setDestUniqueId(String destUniqueId)
    {
        this.destUniqueId = destUniqueId;
    }

	public String getDialStatus()
	{
		return dialStatus;
	}

	public void setDialStatus(String dialStatus)
	{
		this.dialStatus = dialStatus;
	}

	public String getDialstring()
	{
		return dialstring;
	}

	public void setDialstring(String dialstring)
	{
		this.dialstring = dialstring;
	}
}
