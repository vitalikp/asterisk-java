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
package org.asteriskjava.manager.internal;

import java.util.HashMap;
import java.util.Map;

import org.asteriskjava.manager.response.GetConfigResponse;
import org.asteriskjava.manager.response.ChallengeResponse;
import org.asteriskjava.manager.response.CoreSettingsResponse;
import org.asteriskjava.manager.response.CoreStatusResponse;
import org.asteriskjava.manager.response.ExtensionStateResponse;
import org.asteriskjava.manager.response.MailboxCountResponse;
import org.asteriskjava.manager.response.MailboxStatusResponse;
import org.asteriskjava.manager.response.ManagerResponse;
import org.asteriskjava.util.AstUtil;
import org.asteriskjava.util.DateUtil;

/**
 * Default implementation of the ResponseBuilder interface.
 *
 * @see org.asteriskjava.manager.response.ManagerResponse
 * @author srt
 * @version $Id: ResponseBuilderImpl.java 614 2007-03-18 23:42:10Z srt $
 */
class ResponseBuilderImpl implements ResponseBuilder
{
    /**
     * Constructs an instance of ManagerResponse based on a map of attributes.
     *
     * @param attributes the attributes and their values. The keys of this map must be all lower
     * case.
     * @return the response with the given attributes.
     */
    public ManagerResponse buildResponse(final Map<String, String> attributes)
    {
        ManagerResponse response;
        String responseType;

        responseType = attributes.get("response");

        // determine type
        if (attributes.containsKey("challenge"))
        {
            final ChallengeResponse challengeResponse = new ChallengeResponse();
            challengeResponse.setChallenge((String) attributes.get("challenge"));
            response = challengeResponse;
        }
        else if (attributes.containsKey("mailbox") && attributes.containsKey("waiting"))
        {
            final MailboxStatusResponse mailboxStatusResponse = new MailboxStatusResponse();
            mailboxStatusResponse.setMailbox((String) attributes.get("mailbox"));

            if ("1".equals((String) attributes.get("waiting")))
            {
                mailboxStatusResponse.setWaiting(Boolean.TRUE);
            }
            else
            {
                mailboxStatusResponse.setWaiting(Boolean.FALSE);
            }

            response = mailboxStatusResponse;
        }
        else if (attributes.containsKey("mailbox") && attributes.containsKey("newmessages")
                && attributes.containsKey("oldmessages"))
        {
            final MailboxCountResponse mailboxCountResponse = new MailboxCountResponse();
            mailboxCountResponse.setMailbox((String) attributes.get("mailbox"));
            mailboxCountResponse.setNewMessages(Integer.valueOf((String) attributes.get("newmessages")));
            mailboxCountResponse.setOldMessages(Integer.valueOf((String) attributes.get("oldmessages")));
            response = mailboxCountResponse;
        }
        else if (attributes.containsKey("exten") && attributes.containsKey("context") && attributes.containsKey("hint")
                && attributes.containsKey("status"))
        {
            final ExtensionStateResponse extensionStateResponse = new ExtensionStateResponse();
            extensionStateResponse.setExten((String) attributes.get("exten"));
            extensionStateResponse.setContext((String) attributes.get("context"));
            extensionStateResponse.setHint((String) attributes.get("hint"));
            extensionStateResponse.setStatus(Integer.valueOf((String) attributes.get("status")));
            response = extensionStateResponse;
        }
        else if (attributes.containsKey("asteriskversion"))
        {
            CoreSettingsResponse coreResp;

            coreResp = new CoreSettingsResponse();
            coreResp.setAmiVer(attributes.get("amiversion"));
            coreResp.setAstVer(attributes.get("asteriskversion"));
            coreResp.setSysName(attributes.get("systemname"));
            coreResp.setMaxCalls(Integer.valueOf(attributes.get("coremaxcalls")));
            coreResp.setMaxLoadAvg(Double.valueOf(attributes.get("coremaxloadavg")));
            coreResp.setRunUser(attributes.get("corerunuser"));
            coreResp.setRunGroup(attributes.get("corerungroup"));
            coreResp.setMaxFilehandles(Integer.valueOf(attributes.get("coremaxfilehandles")));
            coreResp.setRealtimeEnabled(AstUtil.isTrue(attributes.get("corerealtimeenabled")));
            coreResp.setCdrEnabled(AstUtil.isTrue(attributes.get("corecdrenabled")));
            coreResp.setHttpEnabled(AstUtil.isTrue(attributes.get("corehttpenabled")));

            response = coreResp;
        }
        else if (attributes.containsKey("corestartupdate"))
        {
            String strDate;

            response = new CoreStatusResponse();
            strDate = attributes.get("corestartupdate") + " " + attributes.get("corestartuptime");
            ((CoreStatusResponse)response).setStartupDate(DateUtil.parseDateTime(strDate));
            strDate = attributes.get("corereloaddate") + " " + attributes.get("corereloadtime");
            ((CoreStatusResponse)response).setReloadDate(DateUtil.parseDateTime(strDate));
            ((CoreStatusResponse)response).setCurrentCalls(Integer.valueOf(attributes.get("corecurrentcalls")));
        }
        else if(attributes.containsKey("line-000000-000000"))
        {
        	// this attribute will be there if the file has any lines at all
        	response = new GetConfigResponse();
        }
        else
        {
            response = new ManagerResponse();
        }

        // fill known attributes
        response.setResponse(responseType);

        // clone this map as it is reused by the ManagerReader
        response.setAttributes(new HashMap<String, String>(attributes));

        if (attributes.containsKey("actionid"))
        {
            response.setActionId(attributes.get("actionid"));
        }
        if (attributes.containsKey("message"))
        {
            response.setMessage(attributes.get("message"));
        }

        return response;
    }
}
