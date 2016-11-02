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
 * A NewChannelEvent is triggered when a new channel is created.<p>
 * It is implemented in <code>channel.c</code>
 * 
 * @author srt
 * @version $Id: NewChannelEvent.java 476 2006-07-14 11:45:58Z srt $
 */
public class NewChannelEvent extends AbstractChannelStateEvent
{
    /**
     * Serializable version identifier
     */
    static final long serialVersionUID = -4503396901506287549L;

    private Integer channelState;
    private String channelStateDesc;

    private String accountCode;

    private String exten;
    private String context;

    /**
     * @param source
     */
    public NewChannelEvent(Object source)
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

	/**
     * Returns the account number that is usually used to identify the party to bill for the call.<p>
     * Corresponds to CDR field <code>accountcode</code>.
     * 
     * @return the account number.
     */
    public String getAccountCode()
    {
        return accountCode;
    }

    /**
     * Sets the account number.
     * 
     * @param accountCode the account number.
     */
    public void setAccountCode(String accountCode)
    {
        this.accountCode = accountCode;
    }

    /**
     * Returns the extension.
     */
    public String getExten()
    {
        return exten;
    }

    /**
     * Sets the extension.
     */
    public void setExten(String exten)
    {
        this.exten = exten;
    }

    /**
     * Returns the context of the extension.
     */
    public String getContext()
    {
        return context;
    }

    /**
     * Sets the context of the extension.
     */
    public void setContext(String context)
    {
        this.context = context;
    }
}
