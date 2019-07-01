package org.simplestartframwork.web.utils;

/**
 * 编写一个数据工具类
 * 
 * @author ranger
 * @date 2017-11-09
 */
public class ArrayUtils {

	/**
	 * 将字符串数组转成Long类型数组
	 * 
	 * @param arr
	 * @return
	 */
	public static long[] strToBasicLong(String[] arr) {
		long[] vals = new long[arr.length];
		for (int i = 0; i < arr.length; i++) {
			String str = arr[i];
			if (str == null || "".equals(str)) {
				vals[i] = 0;
			} else {
				vals[i] = Long.valueOf(str);
			}
		}
		return vals;

	}

	/**
	 * 将字符串数组转成Integer类型数组
	 * 
	 * @param arr
	 * @return
	 */
	public static int[] strToBasicInteger(String[] arr) {
		int[] vals = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			String str = arr[i];
			if (str == null || "".equals(str)) {
				vals[i] = 0;
			} else {
				vals[i] = Integer.valueOf(str);
			}
		}
		return vals;

	}

	/**
	 * 将字符串数组转成Double类型数组
	 * 
	 * @param arr
	 * @return
	 */
	public static double[] strToBasicDouble(String[] arr) {
		double[] vals = new double[arr.length];
		for (int i = 0; i < arr.length; i++) {
			String str = arr[i];
			if (str == null || "".equals(str)) {
				vals[i] = 0.0;
			} else {
				vals[i] = Double.valueOf(str);
			}
		}
		return vals;

	}

	/**
	 * 将字符串数组转成Float类型数组
	 * 
	 * @param arr
	 * @return
	 */
	public static float[] strToBasicFloat(String[] arr) {
		float[] vals = new float[arr.length];
		for (int i = 0; i < arr.length; i++) {
			String str = arr[i];
			if (str == null || "".equals(str)) {
				vals[i] = 0.0f;
			} else {
				vals[i] = Float.valueOf(str);
			}
		}
		return vals;

	}

	/**
	 * 将字符串数组转成Short类型数组
	 * 
	 * @param arr
	 * @return
	 */
	public static short[] strToBasicShort(String[] arr) {
		short[] vals = new short[arr.length];
		for (int i = 0; i < arr.length; i++) {
			String str = arr[i];
			if (str == null || "".equals(str)) {
				vals[i] = 0;
			} else {
				vals[i] = Short.valueOf(str);
			}
		}
		return vals;

	}

	/**
	 * 将字符串数组转成Byte类型数组
	 * 
	 * @param arr
	 * @return
	 */
	public static byte[] strToBasicByte(String[] arr) {
		byte[] vals = new byte[arr.length];
		for (int i = 0; i < arr.length; i++) {
			String str = arr[i];
			if (str == null || "".equals(str)) {
				vals[i] = 0;
			} else {
				vals[i] = Byte.valueOf(str);
			}
		}
		return vals;

	}

	/**
	 * 将字符串数组转成Boolean类型数组
	 * 
	 * @param arr
	 * @return
	 */
	public static boolean[] strToBasicBoolean(String[] arr) {
		boolean[] vals = new boolean[arr.length];
		for (int i = 0; i < arr.length; i++) {
			String str = arr[i];
			if (str == null || "".equals(str)) {
				vals[i] = false;
			} else {
				vals[i] = Boolean.valueOf(str);
			}
		}
		return vals;

	}

	/**
	 * 将字符串数组转成Boolean类型数组
	 * 
	 * @param arr
	 * @return
	 */
	public static char[] strToBasicCharacter(String[] arr) {
		char[] vals = new char[arr.length];
		for (int i = 0; i < arr.length; i++) {
			String str = arr[i];
			if (str == null || "".equals(str)) {
				vals[i] = ' ';
			} else {
				vals[i] = str.toCharArray()[0];
			}
		}
		return vals;

	}

	/**
	 * 将字符串数组转成Long类型数组
	 * 
	 * @param arr
	 * @return
	 */
	public static Long[] strToLong(String[] arr) {
		Long[] vals = new Long[arr.length];
		for (int i = 0; i < arr.length; i++) {
			String str = arr[i];
			vals[i] = Long.valueOf(str);
		}
		return vals;

	}

	/**
	 * 将字符串数组转成Integer类型数组
	 * 
	 * @param arr
	 * @return
	 */
	public static Integer[] strToInteger(String[] arr) {
		Integer[] vals = new Integer[arr.length];
		for (int i = 0; i < arr.length; i++) {
			String str = arr[i];
			vals[i] = Integer.valueOf(str);
		}
		return vals;

	}

	/**
	 * 将字符串数组转成Double类型数组
	 * 
	 * @param arr
	 * @return
	 */
	public static Double[] strToDouble(String[] arr) {
		Double[] vals = new Double[arr.length];
		for (int i = 0; i < arr.length; i++) {
			String str = arr[i];
			vals[i] = Double.valueOf(str);
		}
		return vals;

	}

	/**
	 * 将字符串数组转成Float类型数组
	 * 
	 * @param arr
	 * @return
	 */
	public static Float[] strToFloat(String[] arr) {
		Float[] vals = new Float[arr.length];
		for (int i = 0; i < arr.length; i++) {
			String str = arr[i];
			vals[i] = Float.valueOf(str);
		}
		return vals;

	}

	/**
	 * 将字符串数组转成Short类型数组
	 * 
	 * @param arr
	 * @return
	 */
	public static Short[] strToShort(String[] arr) {
		Short[] vals = new Short[arr.length];
		for (int i = 0; i < arr.length; i++) {
			String str = arr[i];
			vals[i] = Short.valueOf(str);
		}
		return vals;

	}

	/**
	 * 将字符串数组转成Byte类型数组
	 * 
	 * @param arr
	 * @return
	 */
	public static Byte[] strToByte(String[] arr) {
		Byte[] vals = new Byte[arr.length];
		for (int i = 0; i < arr.length; i++) {
			String str = arr[i];
			vals[i] = Byte.valueOf(str);
		}
		return vals;

	}

	/**
	 * 将字符串数组转成Boolean类型数组
	 * 
	 * @param arr
	 * @return
	 */
	public static Boolean[] strToBoolean(String[] arr) {
		Boolean[] vals = new Boolean[arr.length];
		for (int i = 0; i < arr.length; i++) {
			String str = arr[i];
			vals[i] = Boolean.valueOf(str);
		}
		return vals;

	}

	/**
	 * 将字符串数组转成Boolean类型数组
	 * 
	 * @param arr
	 * @return
	 */
	public static Character[] strToCharacter(String[] arr) {
		Character[] vals = new Character[arr.length];
		for (int i = 0; i < arr.length; i++) {
			String str = arr[i];
			vals[i] = str.toCharArray()[0];
		}
		return vals;

	}

}
