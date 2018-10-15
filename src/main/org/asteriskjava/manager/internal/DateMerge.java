package org.asteriskjava.manager.internal;

import java.util.Locale;
import java.util.Map;
import java.util.function.BiFunction;

public class DateMerge implements BiFunction<String, String, String>
{
	public void merge(Map<String, String> props, String name1, String name2)
	{
		String value;

		value = props.remove(name2.toLowerCase(Locale.ENGLISH));
		if (value == null)
			return;

		props.merge(name1.toLowerCase(Locale.ENGLISH), value, this);
	}

	public String apply(String date, String time)
	{
		return date + " " + time;
	}
}
