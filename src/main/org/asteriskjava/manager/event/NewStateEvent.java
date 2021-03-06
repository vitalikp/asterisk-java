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
 * A NewStateEvent is triggered when the state of a channel has changed.
 *
 * <p>It is implemented in <code>channel.c</code>
 *
 * @author srt
 * @version $Id: NewStateEvent.java 476 2006-07-14 11:45:58Z srt $
 */
public class NewStateEvent extends AbstractChannelStateEvent
{
    /**
     * Serializable version identifier
     */
    static final long serialVersionUID = -3660049241052945802L;

    private Integer channelState;
    private String channelStateDesc;

    private String connectedLineNum;
    private String connectedLineName;

    public NewStateEvent(Object source)
    {
        super(source);
    }

    public Integer getChannelState()
	{
		return channelState;
	}

	public void setChannelState(Integer channelState)
	{
		this.channelState = channelState;
	}

	public String getChannelStateDesc()
	{
		return channelStateDesc;
	}

	public void setChannelStateDesc(String channelStateDesc)
	{
		this.channelStateDesc = channelStateDesc;
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
}
