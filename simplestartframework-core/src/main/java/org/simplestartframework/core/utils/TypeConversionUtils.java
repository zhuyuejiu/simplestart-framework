package org.simplestartframework.core.utils;



public class TypeConversionUtils {

	public static Object StringToBasic(Class<?> type, String value) {
		//1.判断如果类类型是一个基础数据类型，值是一个null，返回0
		if(type.isPrimitive()==true&&value==null) {
			return 0;
		}

		if (type.equals(int.class) || type.equals(Integer.class)) {
			return Integer.parseInt(value);
		} else if (type.equals(long.class) || type.equals(Long.class)) {
			return Long.parseLong(value);
		} else if (type.equals(float.class) || type.equals(Float.class)) {
			return Float.parseFloat(value);
		} else if (type.equals(double.class) || type.equals(Double.class)) {
			return Double.parseDouble(value);
			
		} else if (type.equals(short.class) || type.equals(Short.class)) {
			return Short.parseShort(value);
		} else if (type.equals(byte.class) || type.equals(Byte.class)) {
			return Byte.parseByte(value);
		} else if (type.equals(char.class) || type.equals(Character.class)) {
			String c = new String(value);
			return c.toCharArray()[0];
		} else if (type.equals(boolean.class) || type.equals(Boolean.class)) {
			return Boolean.parseBoolean(value);
		} else {
			return value;
		}
	}
	
	public static void main(String[] args) {
		Object object = TypeConversionUtils.StringToBasic(int.class, null);
		System.out.println(object);
	}

}
