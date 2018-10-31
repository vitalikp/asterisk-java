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
package org.asteriskjava.manager.action;

import org.asteriskjava.manager.event.DbGetResponseEvent;

/**
 * Retrieves an entry in the Asterisk database for a given family and key.
 *
 * <p>If an entry is found a DbGetResponseEvent is sent by Asterisk containing the
 * value, otherwise a ManagerError indicates that no entry matches.
 *
 * <p>Available since Asterisk 1.2
 *
 * @see org.asteriskjava.manager.event.DbGetResponseEvent
 * @author srt
 * @version $Id: DbGetAction.java 729 2007-05-26 05:16:57Z sprior $
 * @since 0.2
 */
public class DbGetAction extends AbstractManagerAction
        implements
            EventGeneratingAction
{
    /**
     * Serial version identifier
     */
    private static final long serialVersionUID = 921037572305993779L;
    private String family;
    private String key;

    /**
     * Creates a new empty DbGetAction.
     */
    public DbGetAction()
    {

    }

    /**
     * Creates a new DbGetAction that retrieves the value of the database entry
     * with the given key in the given family.
     *
     * @param family the family of the key
     * @param key the key of the entry to retrieve
     * @since 0.2
     */
    public DbGetAction(String family, String key)
    {
        this.family = family;
        this.key = key;
    }

    public String getAction()
    {
        return "DBGet";
    }

    /**
     * Returns the family of the key.
     *
     * @return the family of the key.
     */
    public String getFamily()
    {
        return family;
    }

    /**
     * Sets the family of the key.
     *
     * @param family the family of the key.
     */
    public void setFamily(String family)
    {
        this.family = family;
    }

    /**
     * Returns the the key of the entry to retrieve.
     *
     * @return the key of the entry to retrieve.
     */
    public String getKey()
    {
        return key;
    }

    /**
     * Sets the key of the entry to retrieve.
     *
     * @param key the key of the entry to retrieve.
     */
    public void setKey(String key)
    {
        this.key = key;
    }

    public Class getActionCompleteEventClass()
    {
        return DbGetResponseEvent.class;
    }
}
