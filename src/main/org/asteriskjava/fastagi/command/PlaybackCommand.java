package org.asteriskjava.fastagi.command;

public class PlaybackCommand extends AbstractAgiCommand
{
	private static final long serialVersionUID = 1l;

	private final String[] filenames;

	public PlaybackCommand(String[] filenames)
	{
		this.filenames = filenames;
	}

	public String buildCommand()
	{
		StringBuilder cmd = new StringBuilder();

		cmd.append("EXEC PLAYBACK ");

		int i = 0;

		cmd.append('"');
		while (i < filenames.length)
		{
			if (i > 0)
				cmd.append('&');
			cmd.append(filenames[i++]);
		}
		cmd.append('"');

		return cmd.toString();
	}
}
