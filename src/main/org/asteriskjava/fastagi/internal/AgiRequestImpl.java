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
package org.asteriskjava.fastagi.internal;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.util.AstUtil;

/**
 * Default implementation of the AGIRequest interface.
 *
 * @author srt
 * @version $Id: AgiRequestImpl.java 729 2007-05-26 05:16:57Z sprior $
 */
public class AgiRequestImpl implements Serializable, AgiRequest
{
	/**
	 * Serial version identifier.
	 */
	private static final long serialVersionUID = 3257001047145789496L;

	/**
	 * Socket data
	 */
	private InetAddress localAddress;
	private int localPort;
	private InetAddress remoteAddress;
	private int remotePort;

	/**
	 * Request data
	 */
	private String script;

	private boolean callerIdCreated;
	private String rawCallerId;

	private Map<String, String> request = new HashMap<String, String>();

	/**
	 * A map assigning the values of a parameter (an array of Strings) to the
	 * name of the parameter.
	 */
	private final Map<String, String> parameterMap = new HashMap<String, String>();

	/**
	 * Creates a new AGIRequestImpl.
	 */
	AgiRequestImpl()
	{

	}

	void set(String name, String value)
	{
		if ("network_script".equals(name))
		{
			script = value;
			return;
		}

		if (name.startsWith("arg_"))
		{
			int pos = value.indexOf('=');
			if (pos > 0)
			{
				name = value.substring(0, pos).trim();
				value = value.substring(pos + 1).trim();
			}

			setParam(name, value);
			return;
		}

		request.put(name, value);
	}

	void setParam(String name, String value)
	{
		parameterMap.put(name, value);
	}

	public InetAddress getLocalAddress()
	{
		return localAddress;
	}

	void setLocalAddress(InetAddress localAddress)
	{
		this.localAddress = localAddress;
	}

	public int getLocalPort()
	{
		return localPort;
	}

	void setLocalPort(int localPort)
	{
		this.localPort = localPort;
	}

	public InetAddress getRemoteAddress()
	{
		return remoteAddress;
	}

	void setRemoteAddress(InetAddress remoteAddress)
	{
		this.remoteAddress = remoteAddress;
	}

	public int getRemotePort()
	{
		return remotePort;
	}

	void setRemotePort(int remotePort)
	{
		this.remotePort = remotePort;
	}

	/**
	 * Returns the name of the script to execute.
	 *
	 * @return the name of the script to execute.
	 */
	public synchronized String getScript()
	{
		return script;
	}

	/**
	 * Returns the full URL of the request in the form
	 * agi://host[:port][/script].
	 *
	 * @return the full URL of the request in the form
	 *         agi://host[:port][/script].
	 */
	public String getRequestURL()
	{
		return request.get("request");
	}

	/**
	 * Returns the name of the channel.
	 *
	 * @return the name of the channel.
	 */
	public String getChannel()
	{
		return request.get("channel");
	}

	public String getLanguage()
	{
		return request.get("language");
	}

	public String getType()
	{
		return request.get("type");
	}

	/**
	 * Returns the unique id of the channel.
	 *
	 * @return the unique id of the channel.
	 */
	public String getUniqueId()
	{
		return request.get("uniqueid");
	}

	public String getVersion()
	{
		return request.get("version");
	}

	public String getCallerId()
	{
		return getCallerIdNumber();
	}

	public String getCallerIdNumber()
	{
		String callerIdName;
		String callerId;

		callerIdName = request.get("calleridname");
		callerId = request.get("callerid");
		if (callerIdName != null)
		{
			// Asterisk 1.2
			if (callerId == null || "unknown".equals(callerId))
				return null;

			return callerId;
		}

		// Asterisk 1.0
		return getCallerId10();
	}

	public String getCallerIdName()
	{
		String callerIdName;

		callerIdName = request.get("calleridname");
		if (callerIdName != null)
		{
			// Asterisk 1.2
			if ("unknown".equals(callerIdName))
				return null;

			return callerIdName;
		}

		// Asterisk 1.0
		return getCallerIdName10();
	}

	/**
	 * Returns the Caller*ID number using Asterisk 1.0 logic.
	 *
	 * @return the Caller*ID number
	 */
	private synchronized String getCallerId10()
	{
		final String[] parsedCallerId;

		if (!callerIdCreated)
		{
			rawCallerId = request.get("callerid");
			callerIdCreated = true;
		}

		parsedCallerId = AstUtil.parseCallerId(rawCallerId);
		if (parsedCallerId[1] == null)
			return parsedCallerId[0];

		return parsedCallerId[1];
	}

	/**
	 * Returns the Caller*ID name using Asterisk 1.0 logic.
	 *
	 * @return the Caller*ID name
	 */
	private synchronized String getCallerIdName10()
	{
		if (!callerIdCreated)
		{
			rawCallerId = request.get("callerid");
			callerIdCreated = true;
		}

		if (!callerIdCreated)
		{
			rawCallerId = request.get("callerid");
			callerIdCreated = true;
		}

		return AstUtil.parseCallerId(rawCallerId)[0];
	}

	public Integer getCallingPres()
	{
		if (request.get("callingpres") == null)
			return null;

		try
		{
			return Integer.valueOf(request.get("callingpres"));
		}
		catch (NumberFormatException e)
		{
			return null;
		}
	}

	public Integer getCallingAni2()
	{
		if (request.get("callingani2") == null)
			return null;

		try
		{
			return Integer.valueOf(request.get("callingani2"));
		}
		catch (NumberFormatException e)
		{
			return null;
		}
	}

	public Integer getCallingTon()
	{
		if (request.get("callington") == null)
			return null;

		try
		{
			return Integer.valueOf(request.get("callington"));
		}
		catch (NumberFormatException e)
		{
			return null;
		}
	}

	public Integer getCallingTns()
	{
		if (request.get("callingtns") == null)
			return null;

		try
		{
			return Integer.valueOf(request.get("callingtns"));
		}
		catch (NumberFormatException e)
		{
			return null;
		}
	}

	public String getDnid()
	{
		String dnid;

		dnid = request.get("dnid");

		if (dnid == null || "unknown".equals(dnid))
			return null;

		return dnid;
	}

	public String getRdnis()
	{
		String rdnis;

		rdnis = request.get("rdnis");

		if (rdnis == null || "unknown".equals(rdnis))
			return null;

		return rdnis;
	}

	public String getContext()
	{
		return request.get("context");
	}

	public String getExtension()
	{
		return request.get("extension");
	}

	public Integer getPriority()
	{
		if (request.get("priority") != null)
			return Integer.valueOf(request.get("priority"));

		return null;
	}

	public Boolean getEnhanced()
	{
		if (request.get("enhanced") != null)
		{
			if ("1.0".equals(request.get("enhanced")))
				return Boolean.TRUE;

			return Boolean.FALSE;
		}

		return null;
	}

	public String getAccountCode()
	{
		return request.get("accountcode");
	}

	public String getParameter(String name)
	{
		return parameterMap.get(name);
	}

	public String toString()
	{
		StringBuffer sb;

		sb = new StringBuffer("AgiRequest[");
		sb.append("script='" + getScript() + "',");
		sb.append("requestURL='" + getRequestURL() + "',");
		sb.append("channel='" + getChannel() + "',");
		sb.append("uniqueId='" + getUniqueId() + "',");
		sb.append("version='" + getVersion() + "',");
		sb.append("type='" + getType() + "',");
		sb.append("language='" + getLanguage() + "',");
		sb.append("callerId='" + getCallerId() + "',");
		sb.append("callerIdName='" + getCallerIdName() + "',");
		sb.append("dnid='" + getDnid() + "',");
		sb.append("rdnis='" + getRdnis() + "',");
		sb.append("context='" + getContext() + "',");
		sb.append("extension='" + getExtension() + "',");
		sb.append("priority='" + getPriority() + "',");
		sb.append("enhanced='" + getEnhanced() + "',");
		sb.append("accountCode='" + getAccountCode() + "',");
		sb.append("systemHashcode=" + System.identityHashCode(this));
		sb.append("]");

		return sb.toString();
	}
}
