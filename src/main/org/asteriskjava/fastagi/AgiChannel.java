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

import java.io.IOException;

/**
 * Provides the functionality to send AgiCommands to Asterisk while handling an
 * AgiRequest.
 *
 * <p>This interface is supposed to be used by AgiScripts for interaction with the
 * Asterisk server.
 *
 * @author srt
 * @version $Id: AgiChannel.java 633 2007-03-25 17:02:18Z srt $
 */
public interface AgiChannel
{
	/**
	 * Returns the AGI request.
	 *
	 * @return the AGI request.
	 */
	AgiRequest getRequest();

	/**
	 * Returns the name of the channel.
	 *
	 * @return the name of the channel.
	 */
	String getName();

	/**
	 * Returns the unique id of the channel.
	 *
	 * @return the unique id of the channel.
	 */
	String getUniqueId();

	/**
	 * Answers the channel.
	 *
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	void answer() throws IOException;

	/**
	 * Hangs the channel up.
	 *
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	void hangup() throws IOException;

	/**
	 * Cause the channel to automatically hangup at the given number of seconds
	 * in the future.
	 *
	 * @param time the number of seconds before this channel is automatically
	 *            hung up.
	 *            <p>0 disables the autohangup feature.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	void setAutoHangup(int time) throws IOException;

	/**
	 * Sets the caller id on the current channel.
	 *
	 * @param callerId the raw caller id to set, for example "John Doe&lt;1234&gt;".
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	void setCallerId(String callerId) throws IOException;

	/**
	 * Plays music on hold from the default music on hold class.
	 *
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	void playMusicOnHold() throws IOException;

	/**
	 * Plays music on hold from the given music on hold class.
	 *
	 * @param musicOnHoldClass the music on hold class to play music from as
	 *            configures in Asterisk's <code>musiconhold.conf</code>.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	void playMusicOnHold(String musicOnHoldClass) throws IOException;

	/**
	 * Stops playing music on hold.
	 *
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	void stopMusicOnHold() throws IOException;

	/**
	 * Returns the status of the channel.
	 *
	 * <p>Return values:
	 * <ul>
	 * <li>0 Channel is down and available
	 * <li>1 Channel is down, but reserved
	 * <li>2 Channel is off hook
	 * <li>3 Digits (or equivalent) have been dialed
	 * <li>4 Line is ringing
	 * <li>5 Remote end is ringing
	 * <li>6 Line is up
	 * <li>7 Line is busy
	 * </ul>
	 *
	 * @return the status of the channel.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	int getChannelStatus() throws IOException;

	/**
	 * Plays the given file and waits for the user to enter DTMF digits until he
	 * presses '#'. The user may interrupt the streaming by starting to enter
	 * digits.
	 *
	 * @param file the name of the file to play
	 * @return a String containing the DTMF the user entered
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	String getData(String file) throws IOException;

	/**
	 * Plays the given file and waits for the user to enter DTMF digits until he
	 * presses '#' or the timeout occurs. The user may interrupt the streaming
	 * by starting to enter digits.
	 *
	 * @param file the name of the file to play
	 * @param timeout the timeout in milliseconds to wait for user input.
	 *            <p>0 means standard timeout value, -1 means "ludicrous time"
	 *            (essentially never times out).
	 * @return a String containing the DTMF the user entered
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	String getData(String file, long timeout) throws IOException;

	/**
	 * Plays the given file and waits for the user to enter DTMF digits until he
	 * presses '#' or the timeout occurs or the maximum number of digits has
	 * been entered. The user may interrupt the streaming by starting to enter
	 * digits.
	 *
	 * @param file the name of the file to play
	 * @param timeout the timeout in milliseconds to wait for user input.
	 *            <p>0 means standard timeout value, -1 means "ludicrous time"
	 *            (essentially never times out).
	 * @param maxDigits the maximum number of digits the user is allowed to
	 *            enter
	 * @return a String containing the DTMF the user entered
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	String getData(String file, long timeout, int maxDigits)
			throws IOException;

	/**
	 * Plays the given file, and waits for the user to press one of the given
	 * digits. If none of the escape digits is pressed while streaming the file
	 * it waits for the default timeout of 5 seconds still waiting for the user
	 * to press a digit.
	 *
	 * @param file the name of the file to stream, must not include extension.
	 * @param escapeDigits contains the digits that the user is expected to
	 *            press.
	 * @return the DTMF digit pressed or 0x0 if none was pressed.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	char getOption(String file, String escapeDigits) throws IOException;

	/**
	 * Plays the given file, and waits for the user to press one of the given
	 * digits. If none of the escape digits is pressed while streaming the file
	 * it waits for the specified timeout still waiting for the user to press a
	 * digit.
	 *
	 * @param file the name of the file to stream, must not include extension.
	 * @param escapeDigits contains the digits that the user is expected to
	 *            press.
	 * @param timeout the timeout in seconds to wait if none of the defined
	 *            escape digits was presses while streaming.
	 * @return the DTMF digit pressed or 0x0 if none was pressed.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	char getOption(String file, String escapeDigits, int timeout)
			throws IOException;

	/**
	 * Executes the given command.
	 *
	 * @param application the name of the application to execute, for example
	 *            "Dial".
	 * @return the return code of the application of -2 if the application was
	 *         not found.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	int exec(String application) throws IOException;

	/**
	 * Executes the given command.
	 *
	 * @param application the name of the application to execute, for example
	 *            "Dial".
	 * @param options the parameters to pass to the application, for example
	 *            "SIP/123".
	 * @return the return code of the application of -2 if the application was
	 *         not found.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	int exec(String application, String options) throws IOException;

	/**
	 * Plays back given filenames (do not put extension of wav/alaw etc).
	 * The playback command answer the channel.
	 *
	 * @param filenames name of the files to play.
	 *
	 * @return the return code of the application or
	 *         -2 if the application was not found.
	 * @throws IOException if there is an i/o problem.
	 */
	int playback(String ... filenames) throws IOException;

	/**
	 * Play an audio file while waiting for digits.
	 *
	 * @param filenames name of the files to play.
	 *
	 * @return the return code of the application or
	 *         -2 if the application was not found.
	 * @throws IOException if there is an i/o problem.
	 */
	int background(String ... filenames) throws IOException;

	/**
	 * Sets the context for continuation upon exiting the application.
	 *
	 * @param context the context for continuation upon exiting the application.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	void setContext(String context) throws IOException;

	/**
	 * Sets the extension for continuation upon exiting the application.
	 *
	 * @param extension the extension for continuation upon exiting the
	 *            application.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	void setExtension(String extension) throws IOException;

	/**
	 * Sets the priority or label for continuation upon exiting the application.
	 *
	 * @param priority the priority or label for continuation upon exiting the
	 *            application.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	void setPriority(String priority) throws IOException;

	/**
	 * Plays the given file.
	 *
	 * @param file name of the file to play.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	void streamFile(String file) throws IOException;

	/**
	 * Plays the given file and allows the user to escape by pressing one of the
	 * given digit.
	 *
	 * @param file name of the file to play.
	 * @param escapeDigits a String containing the DTMF digits that allow the
	 *            user to escape.
	 * @return the DTMF digit pressed or 0x0 if none was pressed.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	char streamFile(String file, String escapeDigits) throws IOException;

	/**
	 * Says the given digit string.
	 *
	 * @param digits the digit string to say.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	void sayDigits(String digits) throws IOException;

	/**
	 * Says the given number, returning early if any of the given DTMF number
	 * are received on the channel.
	 *
	 * @param digits the digit string to say.
	 * @param escapeDigits a String containing the DTMF digits that allow the
	 *            user to escape.
	 * @return the DTMF digit pressed or 0x0 if none was pressed.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	char sayDigits(String digits, String escapeDigits) throws IOException;

	/**
	 * Says the given number.
	 *
	 * @param number the number to say.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	void sayNumber(String number) throws IOException;

	/**
	 * Says the given number, returning early if any of the given DTMF number
	 * are received on the channel.
	 *
	 * @param number the number to say.
	 * @param escapeDigits a String containing the DTMF digits that allow the
	 *            user to escape.
	 * @return the DTMF digit pressed or 0x0 if none was pressed.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	char sayNumber(String number, String escapeDigits) throws IOException;

	/**
	 * Says the given character string with phonetics.
	 *
	 * @param text the text to say.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	void sayPhonetic(String text) throws IOException;

	/**
	 * Says the given character string with phonetics, returning early if any of
	 * the given DTMF number are received on the channel.
	 *
	 * @param text the text to say.
	 * @param escapeDigits a String containing the DTMF digits that allow the
	 *            user to escape.
	 * @return the DTMF digit pressed or 0x0 if none was pressed.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	char sayPhonetic(String text, String escapeDigits) throws IOException;

	/**
	 * Says the given character string.
	 *
	 * @param text the text to say.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	void sayAlpha(String text) throws IOException;

	/**
	 * Says the given character string, returning early if any of the given DTMF
	 * number are received on the channel.
	 *
	 * @param text the text to say.
	 * @param escapeDigits a String containing the DTMF digits that allow the
	 *            user to escape.
	 * @return the DTMF digit pressed or 0x0 if none was pressed.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	char sayAlpha(String text, String escapeDigits) throws IOException;

	/**
	 * Says the given time.
	 *
	 * @param time the time to say in seconds since 00:00:00 on January 1, 1970.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	void sayTime(long time) throws IOException;

	/**
	 * Says the given time, returning early if any of the given DTMF number are
	 * received on the channel.
	 *
	 * @param time the time to say in seconds since 00:00:00 on January 1, 1970.
	 * @param escapeDigits a String containing the DTMF digits that allow the
	 *            user to escape.
	 * @return the DTMF digit pressed or 0x0 if none was pressed.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	char sayTime(long time, String escapeDigits) throws IOException;

	/**
	 * Returns the value of the current channel variable.
	 *
	 * @param name the name of the variable to retrieve.
	 * @return the value of the given variable or <code>null</code> if not
	 *         set.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	String getVariable(String name) throws IOException;

	/**
	 * Sets the value of the current channel variable to a new value.
	 *
	 * @param name the name of the variable to retrieve.
	 * @param value the new value to set.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	void setVariable(String name, String value) throws IOException;

	/**
	 * Waits up to 'timeout' milliseconds to receive a DTMF digit.
	 *
	 * @param timeout timeout the milliseconds to wait for the channel to
	 *            receive a DTMF digit, -1 will wait forever.
	 * @return the DTMF digit pressed or 0x0 if none was pressed.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	char waitForDigit(int timeout) throws IOException;

	/**
	 * Returns the value of the current channel variable, unlike getVariable()
	 * this method understands complex variable names and builtin variables.
	 *
	 * <p>You can also use this method to use custom Asterisk functions. Syntax is
	 * "func(args)".
	 *
	 * <p>Available since Asterisk 1.2.
	 *
	 * @param name the name of the variable to retrieve.
	 * @return the value of the given variable or <code>null</code> if not
	 *         set.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	String getFullVariable(String name) throws IOException;

	/**
	 * Returns the value of the given channel variable.
	 *
	 * <p>Available since Asterisk 1.2.
	 *
	 * @param name the name of the variable to retrieve.
	 * @param channel the name of the channel.
	 * @return the value of the given variable or <code>null</code> if not
	 *         set.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	String getFullVariable(String name, String channel) throws IOException;

	/**
	 * Says the given time.
	 *
	 * <p>Available since Asterisk 1.2.
	 *
	 * @param time the time to say in seconds elapsed since 00:00:00 on January
	 *            1, 1970, Coordinated Universal Time (UTC)
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	void sayDateTime(long time) throws IOException;

	/**
	 * Says the given time and allows interruption by one of the given escape
	 * digits.
	 *
	 * <p>Available since Asterisk 1.2.
	 *
	 * @param time the time to say in seconds elapsed since 00:00:00 on January
	 *            1, 1970, Coordinated Universal Time (UTC)
	 * @param escapeDigits the digits that allow the user to interrupt this
	 *            command or <code>null</code> for none.
	 * @return the DTMF digit pressed or 0x0 if none was pressed.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	char sayDateTime(long time, String escapeDigits) throws IOException;

	/**
	 * Says the given time in the given format and allows interruption by one of
	 * the given escape digits.
	 *
	 * <p>Available since Asterisk 1.2.
	 *
	 * @param time the time to say in seconds elapsed since 00:00:00 on January
	 *            1, 1970, Coordinated Universal Time (UTC)
	 * @param escapeDigits the digits that allow the user to interrupt this
	 *            command or <code>null</code> for none.
	 * @param format the format the time should be said in
	 * @return the DTMF digit pressed or 0x0 if none was pressed.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	char sayDateTime(long time, String escapeDigits, String format)
			throws IOException;

	/**
	 * Says the given time in the given format and timezone and allows
	 * interruption by one of the given escape digits.
	 *
	 * <p>Available since Asterisk 1.2.
	 *
	 * @param time the time to say in seconds elapsed since 00:00:00 on January
	 *            1, 1970, Coordinated Universal Time (UTC)
	 * @param escapeDigits the digits that allow the user to interrupt this
	 *            command or <code>null</code> for none.
	 * @param format the format the time should be said in
	 * @param timezone the timezone to use when saying the time, for example
	 *            "UTC" or "Europe/Berlin".
	 * @return the DTMF digit pressed or 0x0 if none was pressed.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.2
	 */
	char sayDateTime(long time, String escapeDigits, String format,
			String timezone) throws IOException;

	/**
	 * Retrieves an entry in the Asterisk database for a given family and key.
	 *
	 * @param family the family of the entry to retrieve.
	 * @param key the key of the entry to retrieve.
	 * @return the value of the given family and key or <code>null</code> if there
	 *         is no such value.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.3
	 */
	String databaseGet(String family, String key) throws IOException;

	/**
	 * Adds or updates an entry in the Asterisk database for a given family, key,
	 * and value.
	 *
	 * @param family the family of the entry to add or update.
	 * @param key the key of the entry to add or update.
	 * @param value the new value of the entry.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.3
	 */
	void databasePut(String family, String key, String value) throws IOException;

	/**
	 * Deletes an entry in the Asterisk database for a given family and key.
	 *
	 * @param family the family of the entry to delete.
	 * @param key the key of the entry to delete.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.3
	 */
	void databaseDel(String family, String key) throws IOException;

	/**
	 * Deletes a whole family of entries in the Asterisk database.
	 *
	 * @param family the family to delete.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.3
	 */
	void databaseDelTree(String family) throws IOException;

	/**
	 * Deletes all entries of a given family in the Asterisk database that have a key
	 * that starts with a given prefix.
	 *
	 * @param family the family of the entries to delete.
	 * @param keytree the prefix of the keys of the entries to delete.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.3
	 */
	void databaseDelTree(String family, String keytree) throws IOException;

	/**
	 * Sends a message to the Asterisk console via the verbose message system.
	 *
	 * @param message the message to send.
	 * @param level the verbosity level to use. Must be in [1..4].
	 * @throws IOException if there is an i/o problem.
	 * @since 0.3
	 */
	void verbose(String message, int level) throws IOException;

	/**
	 * Record to a file until a given dtmf digit in the sequence is received.
	 *
	 * @param file the name of the file to stream, must not include extension.
	 * @param format the format of the file to be recorded, for example "wav".
	 * @param escapeDigits contains the digits that allow the user to end
	 *            recording.
	 * @param timeout the maximum record time in milliseconds, or -1 for no
	 *            timeout.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.3
	 */
	void recordFile(String file, String format, String escapeDigits,
			int timeout) throws IOException;

	/**
	 * Record to a file until a given dtmf digit in the sequence is received.
	 *
	 * @param file the name of the file to stream, must not include extension.
	 * @param format the format of the file to be recorded, for example "wav".
	 * @param escapeDigits contains the digits that allow the user to end
	 *            recording.
	 * @param timeout the maximum record time in milliseconds, or -1 for no
	 *            timeout.
	 * @param offset the offset samples to skip.
	 * @param beep <code>true</code> if a beep should be played before
	 *            recording.
	 * @param maxSilence The amount of silence (in seconds) to allow before
	 *            returning despite the lack of dtmf digits or reaching timeout.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.3
	 */
	void recordFile(String file, String format, String escapeDigits,
			int timeout, int offset, boolean beep, int maxSilence) throws IOException;

	/**
	 * Plays the given file allowing the user to control the streaming by
	 * using "#" for forward and "*" for rewind.
	 *
	 * @param file the name of the file to stream, must not include extension.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.3
	 */
	void controlStreamFile(String file) throws IOException;

	/**
	 * Plays the given file allowing the user to control the streaming by
	 * using "#" for forward and "*" for rewind. Pressing one of the escape
	 * digits stops streaming.
	 *
	 * @param file the name of the file to stream, must not include extension.
	 * @param escapeDigits contains the digits that allow the user to interrupt
	 *            this command.
	 * @return the DTMF digit pressed or 0x0 if none was pressed.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.3
	 */
	char controlStreamFile(String file, String escapeDigits) throws IOException;

	/**
	 * Plays the given file allowing the user to control the streaming by
	 * using "#" for forward and "*" for rewind. Pressing one of the escape
	 * digits stops streaming. The file is played starting at the indicated
	 * offset.
	 *
	 * @param file the name of the file to stream, must not include extension.
	 * @param escapeDigits contains the digits that allow the user to interrupt
	 *            this command. May be <code>null</code> if you don't want the
	 *            user to interrupt.
	 * @param offset the offset samples to skip before streaming.
	 * @return the DTMF digit pressed or 0x0 if none was pressed.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.3
	 */
	char controlStreamFile(String file, String escapeDigits, int offset) throws IOException;

	/**
	 * Plays the given file allowing the user to control the streaming by
	 * using forwardDigit for forward, rewindDigit for rewind and pauseDigit for pause.
	 * Pressing one of the escape digits stops streaming.
	 * The file is played starting at the indicated
	 * offset, use 0 to start at the beginning.
	 *
	 * @param file the name of the file to stream, must not include extension.
	 * @param escapeDigits contains the digits that allow the user to interrupt
	 *            this command. May be <code>null</code> if you don't want the
	 *            user to interrupt.
	 * @param offset the offset samples to skip before streaming, use 0 to start at the beginning.
	 * @param forwardDigit the digit for fast forward.
	 * @param rewindDigit the digit for rewind.
	 * @param pauseDigit the digit for pause and unpause.
	 * @return the DTMF digit pressed or 0x0 if none was pressed.
	 * @throws IOException if there is an i/o problem.
	 * @since 0.3
	 */
	char controlStreamFile(String file, String escapeDigits,
			int offset, String forwardDigit, String rewindDigit,
			String pauseDigit) throws IOException;
}
