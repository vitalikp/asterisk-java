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
 * A QueueEntryEvent is triggered in response to a QueueStatusAction and
 * contains information about an entry in a queue.
 *
 * <p>It is implemented in <code>apps/app_queue.c</code>
 *
 * @see org.asteriskjava.manager.action.QueueStatusAction
 * @author srt
 * @version $Id: QueueEntryEvent.java 397 2006-05-26 12:13:32Z srt $
 */
public class QueueEntryEvent extends ResponseEvent
{
    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = 3419114730595151514L;
    private String queue;
    private Integer position;
    private String channel;
    private String callerId;
    private String callerIdName;
    private Long wait;

    public QueueEntryEvent(Object source)
    {
        super(source);
    }

    /**
     * Returns the name of the queue that contains this entry.
     *
     * @return the name of the queue.
     */
    public String getQueue()
    {
        return queue;
    }

    /**
     * Sets the name of the queue that contains this entry.
     *
     * @param queue the name of the queue.
     */
    public void setQueue(String queue)
    {
        this.queue = queue;
    }

    /**
     * Returns the position of this entry in the queue.
     *
     * @return the position of this entry.
     */
    public Integer getPosition()
    {
        return position;
    }

    /**
     * Sets the position of this entry in the queue.
     *
     * @param position the position of this entry.
     */
    public void setPosition(Integer position)
    {
        this.position = position;
    }

    /**
     * Returns the name of the channel of this entry.
     *
     * @return the name of the channel.
     */
    public String getChannel()
    {
        return channel;
    }

    /**
     * Sets the name of the channel of this entry.
     *
     * @param channel the name of the channel.
     */
    public void setChannel(String channel)
    {
        this.channel = channel;
    }

    /**
     * Returns the the Caller*ID number of this entry.
     *
     * @return the the Caller*ID number of this entry.
     */
    public String getCallerId()
    {
        return callerId;
    }

    /**
     * Sets the the Caller*ID number of this entry.
     *
     * @param callerId the the Caller*ID number of this entry.
     */
    public void setCallerId(String callerId)
    {
        this.callerId = callerId;
    }

    /**
     * Returns the Caller*ID name of this entry.
     *
     * @return the Caller*ID name of this entry.
     * @since 0.2
     */
    public String getCallerIdName()
    {
        return callerIdName;
    }

    /**
     * Sets the Caller*ID name of this entry.
     *
     * @param callerIdName the Caller*ID name of this entry.
     * @since 0.2
     */
    public void setCallerIdName(String callerIdName)
    {
        this.callerIdName = callerIdName;
    }

    /**
     * Returns the number of seconds this entry has spent in the queue.
     *
     * @return the number of seconds.
     */
    public Long getWait()
    {
        return wait;
    }

    /**
     * Sets the number of seconds this entry has spent in the queue.
     *
     * @param wait the number of seconds.
     */
    public void setWait(Long wait)
    {
        this.wait = wait;
    }
}
