package org.asteriskjava.manager.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

class ClassType<C>
{
	private final Class<? extends C> cls;
	private final Class<?> params[];

	private final Map<String, Method> getters = new HashMap<String, Method>();
	private final Map<String, Method> setters = new HashMap<String, Method>();

	public ClassType(Class<? extends C> cls,  Class<?> []params)
	{
		this.cls = cls;
		this.params = params;

		init();
	}

	public String getName()
	{
		return cls.getName();
	}

	private void init()
	{
		Method[] methods;
		Method method;
		String name;
		int i;

		methods = cls.getMethods();

		i = 0;
		while (i < methods.length)
		{
			method = methods[i++];
			name = method.getName();

			if (name.startsWith("get"))
			{
				if (method.getParameterTypes().length != 0)
					continue;

				name = name.substring(3);
				name = name.toLowerCase(Locale.ENGLISH);

				getters.put(name, method);
			}

			if (name.startsWith("set"))
			{
				if (method.getParameterTypes().length != 1)
					continue;

				name = name.substring(3);
				name = name.toLowerCase(Locale.ENGLISH);

				setters.put(name, method);
			}
		}
	}

	public C newInstance(Object ... params)
		throws ReflectiveOperationException
	{
		Constructor<? extends C> constructor;

		constructor = cls.getConstructor(this.params);
		if (constructor == null)
			return null;

		return constructor.newInstance(params);
	}

	public void setProp(Object obj, String name, String value)
	{
		Method setter;

		setter = setters.get(name);
		if (setter == null)
			throw new RuntimeException("no setter");



		try
		{
			setter.invoke(obj, value);
		}
		catch (Exception e)
		{
			throw new RuntimeException("invoke setter is fail", e);
		}
	}

	public String toString()
	{
		return cls.getName();
	}
}
