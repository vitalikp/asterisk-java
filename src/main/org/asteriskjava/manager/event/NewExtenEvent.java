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
 * A NewExtenEvent is triggered when a channel is connected to a new extension.
 *
 * <p>It is implemented in <code>pbx.c</code>
 *
 * @author srt
 * @version $Id: NewExtenEvent.java 397 2006-05-26 12:13:32Z srt $
 */
public class NewExtenEvent extends ManagerEvent
{
    /**
     * Serializable version identifier
     */
    static final long serialVersionUID = -467486409866099387L;

    private String uniqueId;
    private String context;
    private String extension;
    private String application;
    private String appData;
    private Integer priority;
    private String channel;

    public NewExtenEvent(Object source)
    {
        super(source);
    }

    /**
     * Returns the unique id of the channel.
     *
     * @return the unique id of the channel.
     */
    public String getUniqueId()
    {
        return uniqueId;
    }

    /**
     * Sets the unique id of the channel.
     *
     * @param uniqueId the unique id of the channel.
     */
    public void setUniqueId(String uniqueId)
    {
        this.uniqueId = uniqueId;
    }

    /**
     * Returns the name of the application that is executed.
     *
     * @return the name of the application.
     */
    public String getApplication()
    {
        return application;
    }

    /**
     * Sets the name of the application that is executed.
     *
     * @param application the name of the application.
     */
    public void setApplication(String application)
    {
        this.application = application;
    }

    /**
     * Returns the parameters passed to the application that is executed. The parameters are
     * separated by a '|' character.
     *
     * @return the parameters.
     */
    public String getAppData()
    {
        return appData;
    }

    /**
     * Sets the parameters passed to the application that is executed.
     *
     * @param appData the parameters.
     */
    public void setAppData(String appData)
    {
        this.appData = appData;
    }

    /**
     * Returns the name of the channel.
     *
     * @return the name of the channel.
     */
    public String getChannel()
    {
        return channel;
    }

    /**
     * Sets the name of the channel.
     *
     * @param channel the name of the channel.
     */
    public void setChannel(String channel)
    {
        this.channel = channel;
    }

    /**
     * Returns the name of the context of the connected extension.
     *
     * @return the name of the context.
     */
    public String getContext()
    {
        return context;
    }

    /**
     * Sets the name of the context of the connected extension.
     *
     * @param context the name of the context.
     */
    public void setContext(String context)
    {
        this.context = context;
    }

    /**
     * Returns the extension.
     *
     * @return the extension.
     */
    public String getExtension()
    {
        return extension;
    }

    /**
     * Sets the extension.
     *
     * @param extension the extension.
     */
    public void setExtension(String extension)
    {
        this.extension = extension;
    }

    /**
     * Returns the priority.
     *
     * @return the priority.
     */
    public Integer getPriority()
    {
        return priority;
    }

    /**
     * Sets the priority.
     *
     * @param priority the priority.
     */
    public void setPriority(Integer priority)
    {
        this.priority = priority;
    }
}
