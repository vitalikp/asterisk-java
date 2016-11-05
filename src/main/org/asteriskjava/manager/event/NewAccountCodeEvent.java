package org.asteriskjava.manager.event;

public class NewAccountCodeEvent extends AbstractChannelEvent
{
	private static final long serialVersionUID = 1l;

	private String accountCode;
	private String oldAccountCode;

	public NewAccountCodeEvent(Object source)
	{
		super(source);
	}

	/**
     * Returns the account number that is usually used to identify the party to bill for the call.
     *
     * <p>Corresponds to CDR field <code>accountcode</code>.
     *
     * @return the account number.
     */
    public String getAccountCode()
    {
        return accountCode;
    }

    /**
     * Sets the account number.
     *
     * @param accountCode the account number.
     */
    public void setAccountCode(String accountCode)
    {
        this.accountCode = accountCode;
    }

    /**
     * Returns the old account number that is usually used to identify the party to bill for the call.
     *
     * <p>Corresponds to CDR field <code>accountcode</code>.
     *
     * @return the old account number.
     */
	public String getOldAccountCode()
	{
		return oldAccountCode;
	}

	/**
     * Sets the old account number.
     *
     * @param oldAccountCode the old account number.
     */
	public void setOldAccountCode(String oldAccountCode)
	{
		this.oldAccountCode = oldAccountCode;
	}
}
