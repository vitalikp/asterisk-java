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
package org.asteriskjava.manager.response;

import java.util.List;

/**
 * Response that is received when sending a CommandAction.
 *
 * <p>Asterisk's handling of the command action is generally quite hairy. It sends a "Response:
 * Follows" line followed by the raw output of the command including empty lines. At the end of the
 * command output a line containing "--END COMMAND--" is sent. The reader parses this response into
 * a CommandResponse object to hide these details.
 *
 * @see org.asteriskjava.manager.action.CommandAction
 *
 * @author srt
 * @version $Id: CommandResponse.java 458 2006-07-05 22:32:02Z srt $
 */
public class CommandResponse extends ManagerResponse
{
    /**
     * Serial version identifier
     */
    private static final long serialVersionUID = -350763332794275049L;

    protected List<String> result;

    /**
     * Returns a List containing strings representing the lines returned by the CLI command.
     *
     * @return a List containing strings representing the lines returned by the CLI command.
     */
    public List<String> getResult()
    {
        return result;
    }

    /**
     * Sets the result.
     *
     * @param result the result.
     */
    public void setResult(List<String> result)
    {
        this.result = result;
    }
}
