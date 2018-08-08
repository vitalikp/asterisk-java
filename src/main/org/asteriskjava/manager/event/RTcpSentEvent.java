package org.asteriskjava.manager.event;

public class RTcpSentEvent extends ManagerEvent
{
	private static final long serialVersionUID = 1l;

	private String to;
	private Long ourSSrc;
	private String sentNTP;
	private Long sentRTP;
	private Long sentPackets;
	private Long sentOctets;
	private Integer fractionLost;
	private Long cumulativeLoss;
	private Double iaJitter;
	private Long theirLastSR;
	private String dlSR;

	public RTcpSentEvent(Object source)
	{
		super(source);
	}

	public String getTo()
	{
		return to;
	}

	public void setTo(String to)
	{
		this.to = to;
	}

	public Long getOurSSrc()
	{
		return ourSSrc;
	}

	public void setOurSSrc(Long ourSSrc)
	{
		this.ourSSrc = ourSSrc;
	}

	public String getSentNTP()
	{
		return sentNTP;
	}

	public void setSentNTP(String sentNTP)
	{
		this.sentNTP = sentNTP;
	}

	public Long getSentRTP()
	{
		return sentRTP;
	}

	public void setSentRTP(Long sentRTP)
	{
		this.sentRTP = sentRTP;
	}

	public Long getSentPackets()
	{
		return sentPackets;
	}

	public void setSentPackets(Long sentPackets)
	{
		this.sentPackets = sentPackets;
	}

	public Long getSentOctets()
	{
		return sentOctets;
	}

	public void setSentOctets(Long sentOctets)
	{
		this.sentOctets = sentOctets;
	}

	public Integer getFractionLost()
	{
		return fractionLost;
	}

	public void setFractionLost(Integer fractionLost)
	{
		this.fractionLost = fractionLost;
	}

	public Long getCumulativeLoss()
	{
		return cumulativeLoss;
	}

	public void setCumulativeLoss(Long cumulativeLoss)
	{
		this.cumulativeLoss = cumulativeLoss;
	}

	public Double getIaJitter()
	{
		return iaJitter;
	}

	public void setIaJitter(Double iaJitter)
	{
		this.iaJitter = iaJitter;
	}

	public Long getTheirLastSR()
	{
		return theirLastSR;
	}

	public void setTheirLastSR(Long theirLastSR)
	{
		this.theirLastSR = theirLastSR;
	}

	public String getDlSR()
	{
		return dlSR;
	}

	public void setDlSR(String dlSR)
	{
		this.dlSR = dlSR;
	}
}
