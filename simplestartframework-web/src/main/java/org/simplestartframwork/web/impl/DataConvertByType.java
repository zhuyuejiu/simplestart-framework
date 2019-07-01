package org.simplestartframwork.web.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataConvertByType {

	public static Object converte(Class<?> typeClass, String value) {
		System.out.println(value);

		if (String.class.isAssignableFrom(typeClass)) {
			// 4.判断基础数据类型的String类型
			return value;
		} else if (Long.class.isAssignableFrom(typeClass)) {
			// 5.判断基础数据类型的Long类型
			if (value != null && !"".equals(value)) {
				return Long.valueOf(value);
			}

		} else if (Integer.class.isAssignableFrom(typeClass)) {
			// 6.判断基础数据类型的Integer类型
			if (value != null && !"".equals(value)) {
				return Integer.valueOf(value);
			}

		} else if (Double.class.isAssignableFrom(typeClass)) {
			// 7.判断基础数据类型的Double类型
			if (value != null && !"".equals(value)) {
				return Double.valueOf(value);
			}

		} else if (Float.class.isAssignableFrom(typeClass)) {
			// 8.判断基础数据类型的Float类型
			if (value != null && !"".equals(value)) {
				return Float.valueOf(value);
			}

		} else if (Boolean.class.isAssignableFrom(typeClass)) {
			// 9.判断基础数据类型的Boolean类型
			if (value != null && !"".equals(value)) {
				return Boolean.valueOf(value);
			}

		} else if (Character.class.isAssignableFrom(typeClass)) {
			// 10.判断基础数据类型的Character类型
			if (value != null && !"".equals(value)) {
				String s = new String(value);
				return s.toCharArray()[0];
			}

		} else if (Short.class.isAssignableFrom(typeClass)) {
			// 11.判断基础数据类型的Short类型
			if (value != null && !"".equals(value)) {
				return Short.valueOf(value);
			}

		} else if (Byte.class.isAssignableFrom(typeClass)) {
			// 12.判断基础数据类型的Byte类型
			if (value != null && !"".equals(value)) {
				return Byte.valueOf(value);
			}
		} else if (long.class.isAssignableFrom(typeClass)) {
			// 5.判断基础数据类型的long类型
			if (value != null && !"".equals(value)) {
				return Long.valueOf(value);
			} else {
				return 0;
			}

		} else if (int.class.isAssignableFrom(typeClass)) {
			// 6.判断基础数据类型的int类型
			if (value != null && !"".equals(value)) {
				return Integer.valueOf(value);
			} else {
				return 0;
			}

		} else if (double.class.isAssignableFrom(typeClass)) {
			// 7.判断基础数据类型的double类型
			if (value != null && !"".equals(value)) {
				return Double.valueOf(value);
			} else {
				return 0.0;
			}

		} else if (float.class.isAssignableFrom(typeClass)) {
			// 8.判断基础数据类型的float类型
			if (value != null && !"".equals(value)) {
				return Float.valueOf(value);
			} else {
				return 0.0f;
			}

		} else if (boolean.class.isAssignableFrom(typeClass)) {
			// 9.判断基础数据类型的boolean类型
			if (value != null && !"".equals(value)) {
				return Boolean.valueOf(value);
			} else {
				return false;
			}

		} else if (char.class.isAssignableFrom(typeClass)) {

			// 10.判断基础数据类型的char类型
			if (value != null && !"".equals(value)) {
				return value.toCharArray()[0];
			} else {
				return ' ';
			}

		} else if (short.class.isAssignableFrom(typeClass)) {
			// 11.判断基础数据类型的short类型
			if (value != null && !"".equals(value)) {
				return Short.valueOf(value);
			} else {
				return 0;
			}

		} else if (byte.class.isAssignableFrom(typeClass)) {

			// 12.判断基础数据类型的byte类型
			if (value != null && !"".equals(value)) {
				return Byte.valueOf(value);
			} else {
				return 0;
			}

		}else if(Date.class.isAssignableFrom(typeClass)){
			String pattern=null;
			if (value != null && !"".equals(value)) {
				if (value.lastIndexOf(":") > 0) {
					pattern = "yyyy-MM-dd hh:mm:ss";
				} else {
					pattern = "yyyy-MM-dd";
				}
				SimpleDateFormat format = new SimpleDateFormat(pattern);

				try {
					return format.parse(value);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
