package org.asteriskjava.manager.internal;

import java.util.HashMap;
import java.util.Locale;
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

		nullType = new ClassType<ManagerResponse>(ManagerResponse.class, null);
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

	private ManagerResponse newInstance(Map<String, String> props)
	{
		ClassType<ManagerResponse> clsType;
		ManagerResponse resp;
		String actionId;

		actionId = props.get("ActionID".toLowerCase(Locale.ENGLISH));
		if (actionId == null)
			return null;

		actionId = ManagerUtil.getInternalActionId(actionId);
		if (actionId == null)
			return null;

		clsType = classes.get(actionId.split("_")[0]);
		if (clsType == null)
			return null;

		resp = newInstance(clsType);

		setProps(resp, clsType, props);

		return resp;
	}

	public ManagerResponse buildResp(Map<String, String> props)
	{
		ManagerResponse resp;

		resp = newInstance(props);
		if (resp == null)
		{
			resp = newInstance(nullType);
			setProps(resp, nullType, props);
		}

		// clone this map as it is reused by the ManagerReader
		resp.setAttributes(new HashMap<String, String>(props));

		return resp;
	}
}
