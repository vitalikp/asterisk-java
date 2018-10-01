package org.asteriskjava.manager.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Locale;

import org.asteriskjava.util.Log;
import org.asteriskjava.util.LogFactory;

class ClassMap<C> extends HashMap<String, Class<? extends C>>
{
	private static final long serialVersionUID = 1l;

	protected static final Log log = LogFactory.getLog(ClassMap.class);

	private final String suffix;
	private final Class<C> baseClass;
	private final Class<?> params[];

	public ClassMap(String suffix, Class<C> baseClass, Class<?> params[])
	{
		this.suffix = suffix.toLowerCase(Locale.ENGLISH);
		this.baseClass = baseClass;
		this.params = params;
	}

	protected void check(Class<? extends C> cls)
	{
		Constructor<? extends C> constructor;

		if (!baseClass.isAssignableFrom(cls))
			throw new IllegalArgumentException(String.format("%s is not a %s", cls, baseClass));

		if ((cls.getModifiers() & Modifier.ABSTRACT) != 0)
			throw new IllegalArgumentException(cls + " is abstract");

		try
		{
			constructor = cls.getConstructor(params);
		}
		catch (NoSuchMethodException ex)
		{
			throw new IllegalArgumentException(cls + " has no usable constructor");
		}

		if ((constructor.getModifiers() & Modifier.PUBLIC) == 0)
			throw new IllegalArgumentException(cls + " has no public default constructor");
	}

	public void regClass(Class<? extends C> cls)
	{
		String className;
		String type;

		check(cls);

		className = cls.getSimpleName();
		type = className.toLowerCase(Locale.ENGLISH);

		if (type.endsWith(suffix))
			type = type.substring(0, type.length() - suffix.length());

		put(type, cls);

		log.debug(String.format("Registered %s type '%s' (%s)", suffix, type, cls));
	}
}
