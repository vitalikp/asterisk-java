package org.asteriskjava.fastagi.command;

public class BackgroundCommand extends AbstractAgiCommand
{
	private static final long serialVersionUID = 1l;

	private final String[] filenames;

	public BackgroundCommand(String ... filenames)
	{
		this.filenames = filenames;
	}

	@Override
	public String buildCommand()
	{
		StringBuilder cmd = new StringBuilder();

		cmd.append("EXEC BACKGROUND ");

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
