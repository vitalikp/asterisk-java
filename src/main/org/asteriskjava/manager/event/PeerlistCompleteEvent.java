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
 * A PeerlistCompleteEvent is triggered after the details of all peers has been reported in response
 * to an SIPPeersAction or SIPShowPeerAction.<p>
 * Available since Asterisk 1.2
 * 
 * @see org.asteriskjava.manager.event.PeerEntryEvent
 * @see org.asteriskjava.manager.action.SIPPeersAction
 * @see org.asteriskjava.manager.action.SIPShowPeerAction
 * @author srt
 * @version $Id: PeerlistCompleteEvent.java 397 2006-05-26 12:13:32Z srt $
 * @since 0.2
 */
public class PeerlistCompleteEvent extends ResponseEvent
{
    /**
     * Serial version identifier
     */
    private static final long serialVersionUID = -1177773673509373296L;
    private Integer listItems;

    /**
     * Creates a new instance.
     * 
     * @param source
     */
    public PeerlistCompleteEvent(Object source)
    {
        super(source);
    }

    /**
     * Returns the number of PeerEvents that have been reported.
     * 
     * @return the number of PeerEvents that have been reported.
     */
    public Integer getListItems()
    {
        return listItems;
    }

    /**
     * Sets the number of PeerEvents that have been reported.
     * 
     * @param listItems the number of PeerEvents that have been reported.
     */
    public void setListItems(Integer listItems)
    {
        this.listItems = listItems;
    }
}
