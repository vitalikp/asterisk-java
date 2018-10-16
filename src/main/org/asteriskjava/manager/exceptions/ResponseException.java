package org.asteriskjava.manager.exceptions;

import org.asteriskjava.manager.response.ManagerResponse;

public class ResponseException extends Exception
{
	private static final long serialVersionUID = 1l;

	public ResponseException(ManagerResponse response)
	{
		super(response.getMessage());
	}
}
