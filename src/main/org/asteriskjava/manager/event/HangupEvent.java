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
 * A HangupEvent is triggered when a channel is hung up.
 *
 * <p>It is implemented in <code>channel.c</code>
 *
 * @author srt
 * @version $Id: HangupEvent.java 476 2006-07-14 11:45:58Z srt $
 */
public class HangupEvent extends AbstractChannelStateEvent
{
    /**
     * Serializable version identifier
     */
    static final long serialVersionUID = 650153034857116588L;

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

    private Integer cause;
    private String causeTxt;

    public HangupEvent(Object source)
    {
        super(source);
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
     * Returns the cause of the hangup.
     * @see org.asteriskjava.live.HangupCause
     */
    public Integer getCause()
    {
        return cause;
    }

    /**
     * Sets the cause of the hangup.
     *
     * @param cause the cause of the hangup.
     */
    public void setCause(Integer cause)
    {
        this.cause = cause;
    }

    /**
     * Returns the textual representation of the hangup cause.
     *
     * @return the textual representation of the hangup cause.
     * @since 0.2
     */
    public String getCauseTxt()
    {
        return causeTxt;
    }

    /**
     * Sets the textual representation of the hangup cause.
     *
     * @param causeTxt the textual representation of the hangup cause.
     * @since 0.2
     */
    public void setCauseTxt(String causeTxt)
    {
        this.causeTxt = causeTxt;
    }
}
