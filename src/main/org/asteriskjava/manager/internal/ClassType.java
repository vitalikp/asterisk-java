package org.asteriskjava.manager.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.asteriskjava.manager.annotation.Property;
import org.asteriskjava.util.AstUtil;

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
		Property prop;
		String name;
		int i;

		methods = cls.getMethods();

		i = 0;
		while (i < methods.length)
		{
			method = methods[i++];
			name = method.getName();
			prop = method.getAnnotation(Property.class);

			if (name.startsWith("get"))
			{
				if (method.getParameterTypes().length != 0)
					continue;

				name = name.substring(3);
				if (prop != null && !prop.name().isEmpty())
					name = prop.name().toLowerCase(Locale.ENGLISH);
				name = name.toLowerCase(Locale.ENGLISH);

				getters.put(name, method);
			}

			if (name.startsWith("set"))
			{
				if (method.getParameterTypes().length != 1)
					continue;

				name = name.substring(3);
				if (prop != null && !prop.name().isEmpty())
					name = prop.name().toLowerCase(Locale.ENGLISH);
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

	private Object getValue(Class<?> type, String value)
		throws ReflectiveOperationException
	{
		if (Boolean.class == type || boolean.class == type)
			return AstUtil.isTrue(value);

		if (Integer.class == type || int.class == type)
			return Integer.valueOf(value);

		if (Long.class == type || long.class == type)
			return Long.valueOf(value);

		if (Float.class == type || float.class == type)
			return Float.valueOf(value);

		if (Double.class == type || double.class == type)
			return Double.valueOf(value);

		if (type.isAssignableFrom(String.class))
			return value;

		return type.getConstructor(new Class[]{String.class}).newInstance(value);
	}

	public void setProp(Object obj, String name, String value)
	{
		Method setter;
		Class<?> type;
		Object objVal;

		setter = setters.get(name);
		if (setter == null)
			throw new RuntimeException("no setter");

		type = setter.getParameterTypes()[0];

		try
		{
			objVal = getValue(type, value);
		}
		catch (Exception e)
		{
			throw new RuntimeException("unable to convert value to required type " + type, e);
		}

		try
		{
			setter.invoke(obj, objVal);
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
