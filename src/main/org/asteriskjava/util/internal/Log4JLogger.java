/*
 * Copyright 2001-2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.asteriskjava.util.internal;

import java.io.Serializable;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.Level;
import org.asteriskjava.util.Log;

/**
 * Implementation of {@link Log} that maps directly to a Log4J <strong>Logger</strong>.
 *
 * <p>Initial configuration of the corresponding Logger instances should be done in
 * the usual manner, as outlined in the Log4J documentation.
 *
 * <p>More or less "stolen" from Apache's commons-logging.
 *
 * @author <a href="mailto:sanders@apache.org">Scott Sanders</a>
 * @author Rod Waldhoff
 * @author Robert Burrell Donkin
 * @version $Id: Log4JLogger.java 457 2006-07-05 21:59:38Z srt $
 */
public class Log4JLogger implements Log, Serializable
{

    // ------------------------------------------------------------- Attributes

    /**
     * The serial version identifier.
     */
    private static final long serialVersionUID = 3545240215095883829L;

    /** The fully qualified name of the Log4JLogger class. */
    private static final String FQCN = Log4JLogger.class.getName();

    private static final boolean IS12 = Priority.class.isAssignableFrom(Level.class);

    /** Log to this logger */
    private transient Logger logger = null;  // NOPMD by srt on 7/5/06 11:18 PM

    /** Logger name */
    private String name = null;

    // ------------------------------------------------------------ Constructor

    public Log4JLogger()
    {
    }

    /**
     * Base constructor.
     *
     * @param clazz class.
     */
    public Log4JLogger(Class clazz)
    {
        this.name = clazz.getName();
        this.logger = getLogger();
    }

    // --------------------------------------------------------- Implementation

    /**
     * Log a message to the Log4j Logger with <code>DEBUG</code> priority.
     *
     * @param message message.
     */
    public void debug(Object message)
    {
        if (IS12)
        {
            getLogger().log(FQCN, (Priority) Level.DEBUG, message, null);
        }
        else
        {
            getLogger().log(FQCN, Level.DEBUG, message, null);
        }
    }

    /**
     * Log a message to the Log4j Logger with <code>INFO</code> priority.
     *
     * @param message message.
     */
    public void info(Object message)
    {
        if (IS12)
        {
            getLogger().log(FQCN, (Priority) Level.INFO, message, null);
        }
        else
        {
            getLogger().log(FQCN, Level.INFO, message, null);
        }
    }

    /**
     * Log a message to the Log4j Logger with <code>WARN</code> priority.
     *
     * @param message message.
     */
    public void warn(Object message)
    {
        if (IS12)
        {
            getLogger().log(FQCN, (Priority) Level.WARN, message, null);
        }
        else
        {
            getLogger().log(FQCN, Level.WARN, message, null);
        }
    }

    /**
     * Log an error to the Log4j Logger with <code>WARN</code> priority.
     *
     * @param message message.
     * @param t exception.
     */
    public void warn(Object message, Throwable t)
    {
        if (IS12)
        {
            getLogger().log(FQCN, (Priority) Level.WARN, message, t);
        }
        else
        {
            getLogger().log(FQCN, Level.WARN, message, t);
        }
    }

    /**
     * Log a message to the Log4j Logger with <code>ERROR</code> priority.
     *
     * @param message message.
     */
    public void error(Object message)
    {
        if (IS12)
        {
            getLogger().log(FQCN, (Priority) Level.ERROR, message, null);
        }
        else
        {
            getLogger().log(FQCN, Level.ERROR, message, null);
        }
    }

    /**
     * Log an error to the Log4j Logger with <code>ERROR</code> priority.
     *
     * @param message message.
     * @param t exception.
     */
    public void error(Object message, Throwable t)
    {
        if (IS12)
        {
            getLogger().log(FQCN, (Priority) Level.ERROR, message, t);
        }
        else
        {
            getLogger().log(FQCN, Level.ERROR, message, t);
        }
    }

    /**
     * Return the native Logger instance we are using.
     *
     * @return the native Logger instance.
     */
    public final Logger getLogger()
    {
        if (logger == null)
        {
            logger = Logger.getLogger(name);
        }
        return (this.logger);
    }
}
