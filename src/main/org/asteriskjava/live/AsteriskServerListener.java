package org.asteriskjava.live;

/**
 * You can register an AsteriskServerListener with an
 * {@link org.asteriskjava.live.AsteriskServer} to be notified about new
 * channels and MeetMe users.
 *
 * <p>Usually it is better to extend {@link AbstractAsteriskServerListener} than to
 * implement this interface directly as additional methods will probably be added
 * in future versions of Asterisk-Java.
 *
 * @author srt
 * @version $Id: AsteriskServerListener.java 453 2006-06-25 09:07:23Z srt $
 * @since 0.3
 */
public interface AsteriskServerListener
{
    /**
     * Called whenever a new channel appears on the Asterisk server.
     *
     * @param channel the new channel.
     */
    void onNewAsteriskChannel(AsteriskChannel channel);

    /**
     * Called whenever a user joins a {@link MeetMeRoom}.
     *
     * @param user the user that joined.
     */
    void onNewMeetMeUser(MeetMeUser user);
}
