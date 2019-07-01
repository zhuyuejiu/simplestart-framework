package org.simplestartframwork.context.impl.parser;

import java.util.HashMap;
import java.util.Map;

public class ParserParam {

	public static final String CONFIG_CLASS_TYPES = "config_class_types";
	public static final String OBJECT_CONTEXT = "object_context";
	public static final String BASE_PACKAGES_NAME ="base_package_name ";
	public static final String ALL_COMPONENT_CLASS_TYPES ="all_component_class_types";

	private static final Map<String, Object> PARAM_MAP = new HashMap<>();

	@SuppressWarnings("unchecked")
	protected static <T> T get(String key, Class<T> dataClassType) {

		Object object = PARAM_MAP.get(key);
		return (T) object;
	}

	protected static void set(String key, Object value) {
		PARAM_MAP.put(key, value);
	}

}
