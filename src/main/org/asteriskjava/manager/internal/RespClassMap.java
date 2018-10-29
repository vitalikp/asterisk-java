package org.asteriskjava.manager.internal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asteriskjava.manager.response.ChallengeResponse;
import org.asteriskjava.manager.response.CommandResponse;
import org.asteriskjava.manager.response.CoreSettingsResponse;
import org.asteriskjava.manager.response.CoreStatusResponse;
import org.asteriskjava.manager.response.ExtensionStateResponse;
import org.asteriskjava.manager.response.GetConfigResponse;
import org.asteriskjava.manager.response.MailboxCountResponse;
import org.asteriskjava.manager.response.MailboxStatusResponse;
import org.asteriskjava.manager.response.ManagerResponse;

public class RespClassMap extends ClassMap<ManagerResponse>
{
	private final DateMerge dateMerge = new DateMerge();

	private final ClassType<ManagerResponse> nullType;

	public RespClassMap()
	{
		super("Response", ManagerResponse.class, null);

		regEventClasses();

		nullType = new ClassType<ManagerResponse>(ManagerResponse.class);
	}

	private void regEventClasses()
	{
		regClass(ChallengeResponse.class);
		regClass(CommandResponse.class);
		regClass(CoreSettingsResponse.class);
		regClass(CoreStatusResponse.class);
		regClass(ExtensionStateResponse.class);
		regClass(GetConfigResponse.class);
		regClass(MailboxCountResponse.class);
		regClass(MailboxStatusResponse.class);
	}

	private void setProps(ManagerResponse resp, ClassType<ManagerResponse> clsType, Map<String, String> props)
	{
		String value;

		if (clsType.isClass(CoreStatusResponse.class))
		{
			dateMerge.merge(props, "CoreStartupDate", "CoreStartupTime");
			dateMerge.merge(props, "CoreReloadDate", "CoreReloadTime");
		}

		for (String name: props.keySet())
		{
			value = props.get(name);

			try
			{
				clsType.setProp(resp, name, value);
			}
			catch (Exception e)
			{
				log.error(String.format("Unable to set property '%s' to '%s' on %s: %s", name, value, resp.getClass().getName(), e.getMessage()), e.getCause());
			}
		}
	}

	private ManagerResponse newInstance(Packet packet)
	{
		ClassType<ManagerResponse> clsType;
		ManagerResponse resp;
		String actionId;

		actionId = packet.getID();
		if (actionId == null)
			return null;

		clsType = classes.get(actionId.split("_")[0]);
		if (clsType == null)
			return null;

		resp = newInstance(clsType);

		setProps(resp, clsType, packet.getProps());

		return resp;
	}

	public ManagerResponse buildResp(Packet packet)
	{
		ManagerResponse resp;

		resp = newInstance(packet);
		if (resp == null)
		{
			resp = newInstance(nullType);
			setProps(resp, nullType, packet.getProps());
		}

		// clone this map as it is reused by the ManagerReader
		resp.setAttributes(new HashMap<String, String>(packet.getProps()));

		if (resp instanceof CommandResponse)
		{
			List<String> result;

			result = Arrays.asList(packet.getOutput().split("\0"));
			((CommandResponse)resp).setResult(result);
		}

		if (packet.getID() == null)
			log.error("Unable to retrieve internalActionId from response: " + "actionId '" + resp.getActionId() + "':\n" + resp);

		return resp;
	}
}
