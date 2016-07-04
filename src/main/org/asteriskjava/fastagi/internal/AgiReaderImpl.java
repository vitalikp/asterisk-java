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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.asteriskjava.fastagi.AgiHangupException;
import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.fastagi.reply.AgiReply;
import org.asteriskjava.util.SocketConnectionFacade;

/**
 * Default implementation of the AgiReader implementation.
 * 
 * @author srt
 * @version $Id: AgiReaderImpl.java 580 2006-12-27 22:21:49Z srt $
 */
public class AgiReaderImpl implements AgiReader
{
    private final SocketConnectionFacade socket;

    public AgiReaderImpl(SocketConnectionFacade socket)
    {
        this.socket = socket;
    }

    public AgiRequest readRequest() throws IOException
    {
        AgiRequestImpl request;
        String line;
        List<String> lines;

        lines = new ArrayList<String>();

        while ((line = socket.readLine()) != null)
        {
            if (line.length() == 0)
            {
                break;
            }

            lines.add(line);
        }

        request = new AgiRequestImpl(lines);
        request.setLocalAddress(socket.getLocalAddress());
        request.setLocalPort(socket.getLocalPort());
        request.setRemoteAddress(socket.getRemoteAddress());
        request.setRemotePort(socket.getRemotePort());

        return request;
    }

    public AgiReply readReply() throws IOException
    {
        AgiReply reply;
        List<String> lines;
        String line;

        lines = new ArrayList<String>();

        line = socket.readLine();

        if (line == null)
        {
            throw new AgiHangupException();
        }

        lines.add(line);

        // read synopsis and usage if statuscode is 520
        if (line.startsWith(Integer.toString(AgiReply.SC_INVALID_COMMAND_SYNTAX)))
        {
            while ((line = socket.readLine()) != null)
            {
                lines.add(line);
                if (line.startsWith(Integer.toString(AgiReply.SC_INVALID_COMMAND_SYNTAX)))
                {
                    break;
                }
            }
        }

        reply = new AgiReplyImpl(lines);

        return reply;
    }
}
