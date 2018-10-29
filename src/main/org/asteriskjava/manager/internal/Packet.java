package org.asteriskjava.manager.internal;

import java.util.Date;
import java.util.Map;

public interface Packet
{
	PackType getPackType();

	String getType();

	String getID();

	String getPrivilege();

	Date getDateReceived();

	Map<String, String> getProps();

	String getOutput();
}
