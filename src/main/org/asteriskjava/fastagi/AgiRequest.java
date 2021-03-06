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
package org.asteriskjava.fastagi;

import java.net.InetAddress;

/**
 * Provides client request information to an {@link org.asteriskjava.fastagi.AgiScript}.
 *
 * <p>This includes information about the channel the script is invoked on and
 * parameters passed from the dialplan.
 *
 * @author srt
 * @version $Id: AgiRequest.java 429 2006-06-04 20:11:34Z srt $
 */
public interface AgiRequest
{
	/**
	 * Returns the local address this channel, that is the IP address of the AGI
	 * server.
	 *
	 * @return the local address this channel.
	 * @since 0.2
	 */
	InetAddress getLocalAddress();

	/**
	 * Returns the local port of this channel, that is the port the AGI server
	 * is listening on.
	 *
	 * @return the local port of this socket channel.
	 * @since 0.2
	 */
	int getLocalPort();

	/**
	 * Returns the remote address of this channel, that is the IP address of the
	 * Asterisk server.
	 *
	 * @return the remote address of this channel.
	 * @since 0.2
	 */
	InetAddress getRemoteAddress();

	/**
	 * Returns the remote port of this channel, that is the client port the
	 * Asterisk server is using for the AGI connection.
	 *
	 * @return the remote port of this channel.
	 * @since 0.2
	 */
	int getRemotePort();

	/**
	 * Returns the name of the script to execute including its full path.
	 *
	 * <p>This corresponds to the request url with protocol, host, port and
	 * parameters stripped off.
	 *
	 * @return the name of the script to execute.
	 */
	String getScript();

	/**
	 * Returns the full URL of the requestURL in the form
	 * agi://host[:port][/script][?param1=value1&amp;param2=value2].
	 *
	 * @return the full URL of the requestURL in the form
	 *         agi://host[:port][/script][?param1=value1&amp;param2=value2].
	 */
	String getRequestURL();

	/**
	 * Returns the name of the channel.
	 *
	 * @return the name of the channel.
	 */
	String getChannel();

	/**
	 * Returns the language set for the current channel, for example "en".
	 *
	 * @return the language set for the current channel, for example "en".
	 */
	String getLanguage();

	/**
	 * Returns the type of the channel, for example "SIP".
	 *
	 * @return the type of the channel, for example "SIP".
	 */
	String getType();

	/**
	 * Returns the unique id of the channel.
	 *
	 * @return the unique id of the channel.
	 */
	String getUniqueId();

	/**
	 * Returns the asterisk version.
	 *
	 * @return the asterisk version
	 */
	String getVersion();

	/**
	 * Returns the Caller*ID number, for example "1234".
	 *
	 * <p>Note: even with Asterisk 1.0 is contains only the numerical part
	 * of the Caller ID.
	 *
	 * @return the Caller*ID number, for example "1234", if no Caller*ID is set or it
	 *         is "unknown" <code>null</code> is returned.
	 * @deprecated will be removed in 0.4, use {@link #getCallerIdNumber()} instead.
	 */
	String getCallerId();

	/**
	 * Returns the Caller*ID number, for example "1234".
	 *
	 * <p>Note: even with Asterisk 1.0 is contains only the numerical part
	 * of the Caller ID.
	 *
	 * @return the Caller*ID number, for example "1234", if no Caller*ID is set or it
	 *         is "unknown" <code>null</code> is returned.
	 */
	String getCallerIdNumber();

	/**
	 * Returns the the Caller*ID Name, for example "John Doe".
	 *
	 * @return the the Caller*ID Name, for example "John Doe", if no Caller*ID
	 *         Name is set or it is "unknown" <code>null</code> is returned.
	 */
	String getCallerIdName();

	/**
	 * Returns the Callerid presentation/screening.
	 *
	 * <p>Available since Asterisk 1.2.
	 *
	 * @return the Callerid presentation/screening.
	 * @since 0.2
	 */
	Integer getCallingPres();

	/**
	 * Returns the Callerid ANI 2 (Info digits).
	 *
	 * <p>Available since Asterisk 1.2.
	 *
	 * @return the Callerid ANI 2 (Info digits).
	 * @since 0.2
	 */
	Integer getCallingAni2();

	/**
	 * Returns the Callerid Type of Number.
	 *
	 * <p>Available since Asterisk 1.2.
	 *
	 * @return the Callerid Type of Number.
	 * @since 0.2
	 */
	Integer getCallingTon();

	/**
	 * Returns the Callerid Transit Network Select.
	 *
	 * <p>Available since Asterisk 1.2.
	 *
	 * @return the Callerid Transit Network Select.
	 * @since 0.2
	 */
	Integer getCallingTns();

	/**
	 * Returns the number, that has been dialed by the user.
	 *
	 * @return the dialed number, if no DNID is available or it is "unknown"
	 *         <code>null</code> is returned.
	 */
	String getDnid();

	/**
	 * If this call has been forwarded, the number of the person doing the
	 * redirect is returned (Redirected dialed number identification service).
	 *
	 * <p>This is usually only available on PRI.
	 *
	 * @return the number of the person doing the redirect, , if no RDNIS is
	 *         available or it is "unknown" <code>null</code> is returned.
	 */
	String getRdnis();

	/**
	 * Returns the context in the dial plan from which the AGI script was
	 * called.
	 *
	 * @return the context in the dial plan from which the AGI script was
	 *         called.
	 */
	String getContext();

	/**
	 * Returns the extension in the dial plan from which the AGI script was
	 * called.
	 *
	 * @return the extension in the dial plan from which the AGI script was
	 *         called.
	 */
	String getExtension();

	/**
	 * Returns the priority of the dial plan entry the AGI script was
	 * called from.
	 *
	 * @return the priority of the dial plan entry the AGI script was
	 *         called from.
	 */
	Integer getPriority();

	/**
	 * Returns whether this agi is passed audio (EAGI - Enhanced AGI).
	 *
	 * <p>Enhanced AGI is currently not supported on FastAGI.
	 *
	 * @return Boolean.TRUE if this agi is passed audio, Boolean.FALSE
	 *         otherwise.
	 */
	Boolean getEnhanced();

	/**
	 * Returns the account code set for the call.
	 *
	 * @return the account code set for the call.
	 */
	String getAccountCode();

	/**
	 * Returns the value of a request parameter as a String, or
	 * <code>null</code> if the parameter does not exist.
	 *
	 * @param name the name of the parameter whose value is
	 *            requested.
	 * @return the value of the parameter.
	 */
	String getParameter(String name);
}
