package org.asteriskjava.manager.internal;

import java.util.HashMap;
import java.util.Locale;

class ClassMap<C> extends HashMap<String, Class<? extends C>>
{
	private static final long serialVersionUID = 1l;

	private final String suffix;
	private final Class<C> baseClass;
	private final Class<?> params[];

	public ClassMap(String suffix, Class<C> baseClass, Class<?> params[])
	{
		this.suffix = suffix.toLowerCase(Locale.ENGLISH);
		this.baseClass = baseClass;
		this.params = params;
	}
}
