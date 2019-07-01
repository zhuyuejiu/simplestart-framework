package org.simplestartframwork.context.utils;

import org.simplestartframework.core.utils.NamedConversionUtils;
import org.simplestartframwork.context.Context;
import org.simplestartframwork.context.impl.AnntationContextManager;


public class ContextUtils {

	public static void insertObject(String key, Class<?> classTypes) {
		try {
			Object object = classTypes.newInstance();
			ContextUtils.insertObject(key, object);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void insertObjectByDefaultName(Class<?> classTypes) {
		try {
			Object object = classTypes.newInstance();
			ContextUtils.insertObjectByDefaultName(object);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void insertObjectByDefaultName(Object object) {
		Context context = AnntationContextManager.getCurrentContext();
		String key = NamedConversionUtils.classNameToObjectName(object.getClass().getSimpleName());
		context.addObject(key, object);
	}

	public static void insertObject(String key, Object object) {
		Context context = AnntationContextManager.getCurrentContext();
		context.addObject(key, object);
	}

	public static void removeObject(String objectName) {
		Context context = AnntationContextManager.getCurrentContext();
		context.removeObject(objectName);
	}

}
