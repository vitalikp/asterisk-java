package org.asteriskjava.manager.internal;

import java.lang.reflect.Constructor;

class ClassType<C>
{
	private final Class<? extends C> cls;
	private final Class<?> params[];

	public ClassType(Class<? extends C> cls,  Class<?> []params)
	{
		this.cls = cls;
		this.params = params;
	}

	public String getName()
	{
		return cls.getName();
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

	public String toString()
	{
		return cls.getName();
	}
}
