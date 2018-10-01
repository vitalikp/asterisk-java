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

		regClass(type, cls);
	}

	protected void regClass(String type, Class<? extends C> cls)
	{
		put(type, cls);

		log.debug(String.format("Registered %s type '%s' (%s)", suffix, type, cls));
	}

	public C newInstance(String type, Object ... params)
	{
		Class<? extends C> regClass;
		Constructor<? extends C> constructor;

		regClass = get(type);
		if (regClass == null)
			return null;

		try
		{
			constructor = regClass.getConstructor(this.params);
		}
		catch (NoSuchMethodException e)
		{
			log.error(String.format("Unable to get constructor of '%s':", regClass.getName()), e);
			return null;
		}

		try
		{
			return constructor.newInstance(params);
		}
		catch (Exception e)
		{
			log.error(String.format("Unable to create new instance of '%s':", regClass.getName()), e);
			return null;
		}
	}
}
