/***********************************************************
 *   _     _       _       _   ____        _               *
 *  | |   |_|     | |     | | |  _ \      |_|              *
 *  | |    _  ___ | |__  _| |_| | | | ____ _ _   _  ___    *
 *  | |   | |/ _ \|  _ \|_   _| | | |/ ___| | \ / |/ _ \   *
 *  | |___| | |_| | | | | | | | |_| | |   | |\ V /|  ___|  *
 *  |_____|_|\__  |_| |_| |_| |____/|_|   |_| \_/  \___|   *
 *   _____   ___| |  ___________________________________   *
 *  |_____| |____/  |_________JAVA_GAME_LIBRARY_________|  *
 *                                                         *
 *                                                         *
 *  COPYRIGHT © 2015, Christian Bryce Alexander            *
 ***********************************************************/
package net.alexanderdev.lightdrive.util;

import static java.lang.System.arraycopy;

/**
 * @author Christian Bryce Alexander
 * @since Jul 28, 2015 | 1:33:15 PM
 */
public class ArraysS {
	public static boolean[] concat(boolean[]... arrays) {
		int length = 0;

		for (boolean[] array : arrays)
			length += array.length;

		boolean[] dst = new boolean[length];

		int i = 0;

		for (boolean[] array : arrays)
			for (boolean b : array)
				dst[i++] = b;

		return dst;
	}

	public static byte[] concat(byte[]... arrays) {
		int length = 0;

		for (byte[] array : arrays)
			length += array.length;

		byte[] dst = new byte[length];

		int n = 0;

		for (byte[] array : arrays)
			for (byte b : array)
				dst[n++] = b;

		return dst;
	}

	public static char[] concat(char[]... arrays) {
		int length = 0;

		for (char[] array : arrays)
			length += array.length;

		char[] dst = new char[length];

		int n = 0;

		for (char[] array : arrays)
			for (char c : array)
				dst[n++] = c;

		return dst;
	}

	public static double[] concat(double[]... arrays) {
		int length = 0;

		for (double[] array : arrays)
			length += array.length;

		double[] dst = new double[length];

		int n = 0;

		for (double[] array : arrays)
			for (double d : array)
				dst[n++] = d;

		return dst;
	}

	public static float[] concat(float[]... arrays) {
		int length = 0;

		for (float[] array : arrays)
			length += array.length;

		float[] dst = new float[length];

		int n = 0;

		for (float[] array : arrays)
			for (float f : array)
				dst[n++] = f;

		return dst;
	}

	public static int[] concat(int[]... arrays) {
		int length = 0;

		for (int[] array : arrays)
			length += array.length;

		int[] dst = new int[length];

		int n = 0;

		for (int[] array : arrays)
			for (int i : array)
				dst[n++] = i;

		return dst;
	}

	public static long[] concat(long[]... arrays) {
		int length = 0;

		for (long[] array : arrays)
			length += array.length;

		long[] dst = new long[length];

		int n = 0;

		for (long[] array : arrays)
			for (long l : array)
				dst[n++] = l;

		return dst;
	}

	public static Object[] concat(Object[]... arrays) {
		int length = 0;

		for (Object[] array : arrays)
			length += array.length;

		Object[] dst = new Object[length];

		int n = 0;

		for (Object[] array : arrays)
			for (Object o : array)
				dst[n++] = o;

		return dst;
	}

	public static short[] concat(short[]... arrays) {
		int length = 0;

		for (short[] array : arrays)
			length += array.length;

		short[] dst = new short[length];

		int n = 0;

		for (short[] array : arrays)
			for (short s : array)
				dst[n++] = s;

		return dst;
	}

	public static String[] concat(String[]... arrays) {
		int length = 0;

		for (String[] array : arrays)
			length += array.length;

		String[] dst = new String[length];

		int n = 0;

		for (String[] array : arrays)
			for (String s : array)
				dst[n++] = s;

		return dst;
	}

	public static boolean[] copy(boolean[] src) {
		boolean[] dst = new boolean[src.length];
		arraycopy(src, 0, dst, 0, src.length);
		return dst;
	}

	public static byte[] copy(byte[] src) {
		byte[] dst = new byte[src.length];
		arraycopy(src, 0, dst, 0, src.length);
		return dst;
	}

	public static char[] copy(char[] src) {
		char[] dst = new char[src.length];
		arraycopy(src, 0, dst, 0, src.length);
		return dst;
	}

	public static double[] copy(double[] src) {
		double[] dst = new double[src.length];
		arraycopy(src, 0, dst, 0, src.length);
		return dst;
	}

	public static float[] copy(float[] src) {
		float[] dst = new float[src.length];
		arraycopy(src, 0, dst, 0, src.length);
		return dst;
	}

	public static int[] copy(int[] src) {
		int[] dst = new int[src.length];
		arraycopy(src, 0, dst, 0, src.length);
		return dst;
	}

	public static long[] copy(long[] src) {
		long[] dst = new long[src.length];
		arraycopy(src, 0, dst, 0, src.length);
		return dst;
	}

	public static Object[] copy(Object[] src) {
		Object[] dst = new Object[src.length];
		arraycopy(src, 0, dst, 0, src.length);
		return dst;
	}

	public static short[] copy(short[] src) {
		short[] dst = new short[src.length];
		arraycopy(src, 0, dst, 0, src.length);
		return dst;
	}

	public static String[] copy(String[] src) {
		String[] dst = new String[src.length];
		arraycopy(src, 0, dst, 0, src.length);
		return dst;
	}

	public static boolean[] reverse(boolean[] src) {
		int a = 0;
		int b = src.length - 1;

		while (a < b) {
			boolean t = src[a];
			src[a] = src[b];
			src[b] = t;

			a++;
			b--;
		}

		return src;
	}

	public static byte[] reverse(byte[] src) {
		int a = 0;
		int b = src.length - 1;

		while (a < b) {
			byte t = src[a];
			src[a] = src[b];
			src[b] = t;

			a++;
			b--;
		}

		return src;
	}

	public static char[] reverse(char[] src) {
		int a = 0;
		int b = src.length - 1;

		while (a < b) {
			char t = src[a];
			src[a] = src[b];
			src[b] = t;

			a++;
			b--;
		}

		return src;
	}

	public static double[] reverse(double[] src) {
		int a = 0;
		int b = src.length - 1;

		while (a < b) {
			double t = src[a];
			src[a] = src[b];
			src[b] = t;

			a++;
			b--;
		}

		return src;
	}

	public static float[] reverse(float[] src) {
		int a = 0;
		int b = src.length - 1;

		while (a < b) {
			float t = src[a];
			src[a] = src[b];
			src[b] = t;

			a++;
			b--;
		}

		return src;
	}

	public static int[] reverse(int[] src) {
		int a = 0;
		int b = src.length - 1;

		while (a < b) {
			int t = src[a];
			src[a] = src[b];
			src[b] = t;

			a++;
			b--;
		}

		return src;
	}

	public static long[] reverse(long[] src) {
		int a = 0;
		int b = src.length - 1;

		while (a < b) {
			long t = src[a];
			src[a] = src[b];
			src[b] = t;

			a++;
			b--;
		}

		return src;
	}

	public static Object[] reverse(Object[] src) {
		int a = 0;
		int b = src.length - 1;

		while (a < b) {
			Object t = src[a];
			src[a] = src[b];
			src[b] = t;

			a++;
			b--;
		}

		return src;
	}

	public static short[] reverse(short[] src) {
		int a = 0;
		int b = src.length - 1;

		while (a < b) {
			short t = src[a];
			src[a] = src[b];
			src[b] = t;

			a++;
			b--;
		}

		return src;
	}

	public static String[] reverse(String[] src) {
		int a = 0;
		int b = src.length - 1;

		while (a < b) {
			String t = src[a];
			src[a] = src[b];
			src[b] = t;

			a++;
			b--;
		}

		return src;
	}

	public static byte[] sort(byte... bytes) {
		int min;
		int n = bytes.length;

		for (int a = 0; a < n - 1; a++) {
			min = a;
			for (int b = a + 1; b < n; b++) {
				if (bytes[b] < bytes[min]) {
					min = b;
				}
			}

			if (min != a) {
				byte t = bytes[a];
				bytes[a] = bytes[min];
				bytes[min] = t;
			}
		}

		return bytes;
	}

	public static char[] sort(char... chars) {
		int min;
		int n = chars.length;

		for (int a = 0; a < n - 1; a++) {
			min = a;
			for (int b = a + 1; b < n; b++) {
				if (chars[b] < chars[min]) {
					min = b;
				}
			}

			if (min != a) {
				char t = chars[a];
				chars[a] = chars[min];
				chars[min] = t;
			}
		}

		return chars;
	}

	public static double[] sort(double... doubles) {
		int min;
		int n = doubles.length;

		for (int a = 0; a < n - 1; a++) {
			min = a;
			for (int b = a + 1; b < n; b++) {
				if (doubles[b] < doubles[min]) {
					min = b;
				}
			}

			if (min != a) {
				double t = doubles[a];
				doubles[a] = doubles[min];
				doubles[min] = t;
			}
		}

		return doubles;
	}

	public static float[] sort(float... floats) {
		int min;
		int n = floats.length;

		for (int a = 0; a < n - 1; a++) {
			min = a;
			for (int b = a + 1; b < n; b++) {
				if (floats[b] < floats[min]) {
					min = b;
				}
			}

			if (min != a) {
				float t = floats[a];
				floats[a] = floats[min];
				floats[min] = t;
			}
		}

		return floats;
	}

	public static int[] sort(int... ints) {
		int min;
		int n = ints.length;

		for (int a = 0; a < n - 1; a++) {
			min = a;
			for (int b = a + 1; b < n; b++) {
				if (ints[b] < ints[min]) {
					min = b;
				}
			}

			if (min != a) {
				int t = ints[a];
				ints[a] = ints[min];
				ints[min] = t;
			}
		}

		return ints;
	}

	public static long[] sort(long... longs) {
		int min;
		int n = longs.length;

		for (int a = 0; a < n - 1; a++) {
			min = a;
			for (int b = a + 1; b < n; b++) {
				if (longs[b] < longs[min]) {
					min = b;
				}
			}

			if (min != a) {
				long t = longs[a];
				longs[a] = longs[min];
				longs[min] = t;
			}
		}

		return longs;
	}

	public static short[] sort(short... shorts) {
		int min;
		int n = shorts.length;

		for (int a = 0; a < n - 1; a++) {
			min = a;
			for (int b = a + 1; b < n; b++) {
				if (shorts[b] < shorts[min]) {
					min = b;
				}
			}

			if (min != a) {
				short t = shorts[a];
				shorts[a] = shorts[min];
				shorts[min] = t;
			}
		}

		return shorts;
	}

	public static String[] sort(String... strings) {
		int min;
		int n = strings.length;

		for (int a = 0; a < n - 1; a++) {
			min = a;
			for (int b = a + 1; b < n; b++) {
				if (strings[b].compareTo(strings[min]) < 0) {
					min = b;
				}
			}

			if (min != a) {
				String t = strings[a];
				strings[a] = strings[min];
				strings[min] = t;
			}
		}

		return strings;
	}
}