package org.asteriskjava.manager.event;

public class RTcpReceivedEvent extends ManagerEvent
{
	private static final long serialVersionUID = 1l;

	private String from;
	private String pt;
	private Integer receptionReports;
	private Long senderSSRC;
	private Long fractionLost;
	private Long packetsLost;
	private Long highestSequence;
	private Long sequenceNumberCycles;
	private Long iaJitter;
	private String lastSR;
	private String dlSR;
	private String rtt;

	public RTcpReceivedEvent(Object source)
	{
		super(source);
	}

	public String getFrom()
	{
		return from;
	}

	public void setFrom(String from)
	{
		this.from = from;
	}

	public String getPt()
	{
		return pt;
	}

	public void setPt(String pt)
	{
		this.pt = pt;
	}

	public Integer getReceptionReports()
	{
		return receptionReports;
	}

	public void setReceptionReports(Integer receptionReports)
	{
		this.receptionReports = receptionReports;
	}

	public Long getSenderSSRC()
	{
		return senderSSRC;
	}

	public void setSenderSSRC(Long senderSSRC)
	{
		this.senderSSRC = senderSSRC;
	}

	public Long getFractionLost()
	{
		return fractionLost;
	}

	public void setFractionLost(Long fractionLost)
	{
		this.fractionLost = fractionLost;
	}

	public Long getPacketsLost()
	{
		return packetsLost;
	}

	public void setPacketsLost(Long packetsLost)
	{
		this.packetsLost = packetsLost;
	}

	public Long getHighestSequence()
	{
		return highestSequence;
	}

	public void setHighestSequence(Long highestSequence)
	{
		this.highestSequence = highestSequence;
	}

	public Long getSequenceNumberCycles()
	{
		return sequenceNumberCycles;
	}

	public void setSequenceNumberCycles(Long sequenceNumberCycles)
	{
		this.sequenceNumberCycles = sequenceNumberCycles;
	}

	public Long getIaJitter()
	{
		return iaJitter;
	}

	public void setIaJitter(Long iaJitter)
	{
		this.iaJitter = iaJitter;
	}

	public String getLastSR()
	{
		return lastSR;
	}

	public void setLastSR(String lastSR)
	{
		this.lastSR = lastSR;
	}

	public String getDlSR()
	{
		return dlSR;
	}

	public void setDlSR(String dlSR)
	{
		this.dlSR = dlSR;
	}

	public String getRtt()
	{
		return rtt;
	}

	public void setRtt(String rtt)
	{
		this.rtt = rtt;
	}
}
