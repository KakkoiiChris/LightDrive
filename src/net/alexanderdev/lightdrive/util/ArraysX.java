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
import static net.alexanderdev.lightdrive.util.math.MathX.randomInt;

/**
 * A collection of static methods to manipulate standard java arrays.
 * 
 * @author Christian Bryce Alexander
 * @since Jul 28, 2015, 1:33:15 PM
 */
public class ArraysX {
	/**
	 * @param arrays
	 *            The arrays to concatenate
	 * @return An array of booleans with the contents of each supplied array in
	 *         sequential order.
	 */
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

	/**
	 * @param arrays
	 *            The arrays to concatenate
	 * @return An array of bytes with the contents of each supplied array in
	 *         sequential order.
	 */
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

	/**
	 * @param arrays
	 *            The arrays to concatenate
	 * @return An array of chars with the contents of each supplied array in
	 *         sequential order.
	 */
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

	/**
	 * @param arrays
	 *            The arrays to concatenate
	 * @return An array of doubles with the contents of each supplied array in
	 *         sequential order.
	 */
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

	/**
	 * @param arrays
	 *            The arrays to concatenate
	 * @return An array of floats with the contents of each supplied array in
	 *         sequential order.
	 */
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

	/**
	 * @param arrays
	 *            The arrays to concatenate
	 * @return An array of ints with the contents of each supplied array in
	 *         sequential order.
	 */
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

	/**
	 * @param arrays
	 *            The arrays to concatenate
	 * @return An array of longs with the contents of each supplied array in
	 *         sequential order.
	 */
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

	/**
	 * @param arrays
	 *            The arrays to concatenate
	 * @return An array of {@link Object}s with the contents of each supplied
	 *         array in sequential order.
	 */
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

	/**
	 * @param arrays
	 *            The arrays to concatenate
	 * @return An array of shorts with the contents of each supplied array in
	 *         sequential order.
	 */
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

	/**
	 * @param arrays
	 *            The arrays to concatenate
	 * @return An array of {@link String}s with the contents of each supplied
	 *         array in sequential order.
	 */
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

	/**
	 * @param src
	 *            The source array to copy from
	 * @return A copy of the supplied varargs/array of booleans
	 */
	public static boolean[] copy(boolean... src) {
		boolean[] dst = new boolean[src.length];
		arraycopy(src, 0, dst, 0, src.length);
		return dst;
	}

	/**
	 * @param src
	 *            The source array to copy from
	 * @return A copy of the supplied varargs/array of bytes
	 */
	public static byte[] copy(byte... src) {
		byte[] dst = new byte[src.length];
		arraycopy(src, 0, dst, 0, src.length);
		return dst;
	}

	/**
	 * @param src
	 *            The source array to copy from
	 * @return A copy of the supplied varargs/array of chars
	 */
	public static char[] copy(char... src) {
		char[] dst = new char[src.length];
		arraycopy(src, 0, dst, 0, src.length);
		return dst;
	}

	/**
	 * @param src
	 *            The source array to copy from
	 * @return A copy of the supplied varargs/array of doubles
	 */
	public static double[] copy(double... src) {
		double[] dst = new double[src.length];
		arraycopy(src, 0, dst, 0, src.length);
		return dst;
	}

	/**
	 * @param src
	 *            The source array to copy from
	 * @return A copy of the supplied varargs/array of floats
	 */
	public static float[] copy(float... src) {
		float[] dst = new float[src.length];
		arraycopy(src, 0, dst, 0, src.length);
		return dst;
	}

	/**
	 * @param src
	 *            The source array to copy from
	 * @return A copy of the supplied varargs/array of ints
	 */
	public static int[] copy(int... src) {
		int[] dst = new int[src.length];
		arraycopy(src, 0, dst, 0, src.length);
		return dst;
	}

	/**
	 * @param src
	 *            The source array to copy from
	 * @return A copy of the supplied varargs/array of longs
	 */
	public static long[] copy(long... src) {
		long[] dst = new long[src.length];
		arraycopy(src, 0, dst, 0, src.length);
		return dst;
	}

	/**
	 * @param src
	 *            The source array to copy from
	 * @return A copy of the supplied varargs/array of {@link Object}s
	 */
	public static Object[] copy(Object... src) {
		Object[] dst = new Object[src.length];
		arraycopy(src, 0, dst, 0, src.length);
		return dst;
	}

	/**
	 * @param src
	 *            The source array to copy from
	 * @return A copy of the supplied varargs/array of shorts
	 */
	public static short[] copy(short... src) {
		short[] dst = new short[src.length];
		arraycopy(src, 0, dst, 0, src.length);
		return dst;
	}

	/**
	 * @param src
	 *            The source array to copy from
	 * @return A copy of the supplied varargs/array of {@link String}s
	 */
	public static String[] copy(String... src) {
		String[] dst = new String[src.length];
		arraycopy(src, 0, dst, 0, src.length);
		return dst;
	}

	/**
	 * @param start
	 *            The first byte in the list
	 * @param end
	 *            the last byte in the list
	 * @return A sequential list of bytes from {@code start} to {@code end}
	 *         inclusive.
	 */
	public static byte[] list(byte start, byte end) {
		byte[] list = new byte[end - start + 1];

		for (int i = 0; i < list.length; i++)
			list[i] = (byte) (i + start);

		return list;
	}

	/**
	 * @param start
	 *            The first value in the list
	 * @param end
	 *            The last value in the list
	 * @param step
	 *            The distance between sequential values
	 * @return A sequential list of every "{@code step}"th value from
	 *         {@code start} to {@code end} inclusive
	 */
	public static byte[] list(byte start, byte end, byte step) {
		byte[] list = new byte[(end - start + 1) / step];

		for (int i = 0; i < list.length; i += step)
			list[i] = (byte) (i + start);

		return list;
	}

	/**
	 * @param start
	 *            The first byte in the list
	 * @param end
	 *            the last byte in the list
	 * @return A sequential list of bytes from {@code start} to {@code end}
	 *         inclusive.
	 */
	public static int[] list(int start, int end) {
		int[] list = new int[end - start + 1];

		for (int i = 0; i < list.length; i++)
			list[i] = i + start;

		return list;
	}

	/**
	 * @param start
	 *            The first value in the list
	 * @param end
	 *            The last value in the list
	 * @param step
	 *            The distance between sequential values
	 * @return A sequential list of every "{@code step}"th value from
	 *         {@code start} to {@code end} inclusive
	 */
	public static int[] list(int start, int end, int step) {
		int[] list = new int[(end - start + 1) / step];

		for (int i = 0; i < list.length; i += step)
			list[i] = (int) (i + start);

		return list;
	}

	/**
	 * @param start
	 *            The first byte in the list
	 * @param end
	 *            the last byte in the list
	 * @return A sequential list of bytes from {@code start} to {@code end}
	 *         inclusive.
	 */
	public static long[] list(long start, long end) {
		long[] list = new long[(int) (end - start + 1)];

		for (int i = 0; i < list.length; i++)
			list[i] = i + start;

		return list;
	}

	/**
	 * @param start
	 *            The first value in the list
	 * @param end
	 *            The last value in the list
	 * @param step
	 *            The distance between sequential values
	 * @return A sequential list of every "{@code step}"th value from
	 *         {@code start} to {@code end} inclusive
	 */
	public static long[] list(long start, long end, long step) {
		long[] list = new long[(int) ((end - start + 1) / step)];

		for (int i = 0; i < list.length; i += step)
			list[i] = (long) (i + start);

		return list;
	}

	/**
	 * @param start
	 *            The first byte in the list
	 * @param end
	 *            the last byte in the list
	 * @return A sequential list of bytes from {@code start} to {@code end}
	 *         inclusive.
	 */
	public static short[] list(short start, short end) {
		short[] list = new short[end - start + 1];

		for (int i = 0; i < list.length; i++)
			list[i] = (short) (i + start);

		return list;
	}

	/**
	 * @param start
	 *            The first value in the list
	 * @param end
	 *            The last value in the list
	 * @param step
	 *            The distance between sequential values
	 * @return A sequential list of every "{@code step}"th value from
	 *         {@code start} to {@code end} inclusive
	 */
	public static short[] list(short start, short end, short step) {
		short[] list = new short[(end - start + 1) / step];

		for (int i = 0; i < list.length; i += step)
			list[i] = (short) (i + start);

		return list;
	}

	/**
	 * @param start
	 *            The first value in the list
	 * @param end
	 *            The last value in the list
	 * @param step
	 *            The distance between sequential values
	 * @return A sequential list of every "{@code step}"th value from
	 *         {@code start} to {@code end} inclusive
	 */
	public static float[] list(float start, float end, float step) {
		float[] list = new float[(int) ((end - start + 1) / step)];

		for (int i = 0; i < list.length; i += step)
			list[i] = (float) (i + start);

		return list;
	}

	/**
	 * @param start
	 *            The first value in the list
	 * @param end
	 *            The last value in the list
	 * @param step
	 *            The distance between sequential values
	 * @return A sequential list of every "{@code step}"th value from
	 *         {@code start} to {@code end} inclusive
	 */
	public static double[] list(double start, double end, double step) {
		double[] list = new double[(int) ((end - start + 1) / step)];

		for (int i = 0; i < list.length; i += step)
			list[i] = (double) (i + start);

		return list;
	}

	/**
	 * @param booleans
	 *            The array to remove from
	 * @param n
	 *            The index to remove
	 * @return A copy of the supplied array of booleans with the nth value
	 *         removed
	 */
	public static boolean[] remove(boolean[] booleans, int n) {
		boolean[] list = new boolean[booleans.length - 1];

		int t = 0;

		for (int i = 0; i < booleans.length; i++)
			if (i != n)
				list[t++] = booleans[i];

		return list;
	}

	/**
	 * @param bytes
	 *            The array to remove from
	 * @param n
	 *            The index to remove
	 * @return A copy of the supplied array of bytes with the nth value removed
	 */
	public static byte[] remove(byte[] bytes, int n) {
		byte[] list = new byte[bytes.length - 1];

		int t = 0;

		for (int i = 0; i < bytes.length; i++)
			if (i != n)
				list[t++] = bytes[i];

		return list;
	}

	/**
	 * @param chars
	 *            The array to remove from
	 * @param n
	 *            The index to remove
	 * @return A copy of the supplied array of chars with the nth value removed
	 */
	public static char[] remove(char[] chars, int n) {
		char[] list = new char[chars.length - 1];

		int t = 0;

		for (int i = 0; i < chars.length; i++)
			if (i != n)
				list[t++] = chars[i];

		return list;
	}

	/**
	 * @param doubles
	 *            The array to remove from
	 * @param n
	 *            The index to remove
	 * @return A copy of the supplied array of doubles with the nth value
	 *         removed
	 */
	public static double[] remove(double[] doubles, int n) {
		double[] list = new double[doubles.length - 1];

		int t = 0;

		for (int i = 0; i < doubles.length; i++)
			if (i != n)
				list[t++] = doubles[i];

		return list;
	}

	/**
	 * @param floats
	 *            The array to remove from
	 * @param n
	 *            The index to remove
	 * @return A copy of the supplied array of floats with the nth value removed
	 */
	public static float[] remove(float[] floats, int n) {
		float[] list = new float[floats.length - 1];

		int t = 0;

		for (int i = 0; i < floats.length; i++)
			if (i != n)
				list[t++] = floats[i];

		return list;
	}

	/**
	 * @param ints
	 *            The array to remove from
	 * @param n
	 *            The index to remove
	 * @return A copy of the supplied array of integers with the nth value
	 *         removed
	 */
	public static int[] remove(int[] ints, int n) {
		int[] list = new int[ints.length - 1];

		int t = 0;

		for (int i = 0; i < ints.length; i++)
			if (i != n)
				list[t++] = ints[i];

		return list;
	}

	/**
	 * @param longs
	 *            The array to remove from
	 * @param n
	 *            The index to remove
	 * @return A copy of the supplied array of longs with the nth value removed
	 */
	public static long[] remove(long[] longs, int n) {
		long[] list = new long[longs.length - 1];

		int t = 0;

		for (int i = 0; i < longs.length; i++)
			if (i != n)
				list[t++] = longs[i];

		return list;
	}

	/**
	 * @param objects
	 *            The array to remove from
	 * @param n
	 *            The index to remove
	 * @return A copy of the supplied array of {@link Object}s with the nth
	 *         value removed
	 */
	public static Object[] remove(Object[] objects, int n) {
		Object[] list = new Object[objects.length - 1];

		int t = 0;

		for (int i = 0; i < objects.length; i++)
			if (i != n)
				list[t++] = objects[i];

		return list;
	}

	/**
	 * @param shorts
	 *            The array to remove from
	 * @param n
	 *            The index to remove
	 * @return A copy of the supplied array of shorts with the nth value removed
	 */
	public static short[] remove(short[] shorts, int n) {
		short[] list = new short[shorts.length - 1];

		int t = 0;

		for (int i = 0; i < shorts.length; i++)
			if (i != n)
				list[t++] = shorts[i];

		return list;
	}

	/**
	 * @param strings
	 *            The array to remove from
	 * @param n
	 *            The index to remove
	 * @return A copy of the supplied array of {@link String}s with the nth
	 *         value removed
	 */
	public static String[] remove(String[] strings, int n) {
		String[] list = new String[strings.length - 1];

		int t = 0;

		for (int i = 0; i < strings.length; i++)
			if (i != n)
				list[t++] = strings[i];

		return list;
	}

	/**
	 * @param src
	 *            The source array to reverse
	 * @return A copy of the supplied varargs/array of booleans with its values
	 *         reversed
	 */
	public static boolean[] reverse(boolean... src) {
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

	/**
	 * @param src
	 *            The source array to reverse
	 * @return A copy of the supplied varargs/array of bytes with its values
	 *         reversed
	 */
	public static byte[] reverse(byte... src) {
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

	/**
	 * @param src
	 *            The source array to reverse
	 * @return A copy of the supplied varargs/array of chars with its values
	 *         reversed
	 */
	public static char[] reverse(char... src) {
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

	/**
	 * @param src
	 *            The source array to reverse
	 * @return A copy of the supplied array of doubles with its values reversed
	 */
	public static double[] reverse(double... src) {
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

	/**
	 * @param src
	 *            The source array to reverse
	 * @return A copy of the supplied varargs/array of floats with its values
	 *         reversed
	 */
	public static float[] reverse(float... src) {
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

	/**
	 * @param src
	 *            The source array to reverse
	 * @return A copy of the supplied varargs/array of ints with its values
	 *         reversed
	 */
	public static int[] reverse(int... src) {
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

	/**
	 * @param src
	 *            The source array to reverse
	 * @return A copy of the supplied varargs/array of longs with its values
	 *         reversed
	 */
	public static long[] reverse(long... src) {
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

	/**
	 * @param src
	 *            The source array to reverse
	 * @return A copy of the supplied varargs/array of {@link Object}s with its
	 *         values reversed
	 */
	public static Object[] reverse(Object... src) {
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

	/**
	 * @param src
	 *            The source array to reverse
	 * @return A copy of the supplied varargs/array of shorts with its values
	 *         reversed
	 */
	public static short[] reverse(short... src) {
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

	/**
	 * @param src
	 *            The source array to reverse
	 * @return A copy of the supplied varargs/array of {@link String}s with its
	 *         values reversed
	 */
	public static String[] reverse(String... src) {
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

	/**
	 * @param bytes
	 *            The array to shuffle
	 * @return A copy of the supplied varargs/array of bytes with its values
	 *         shuffled
	 */
	public static byte[] shuffle(byte... bytes) {
		for (int a = bytes.length - 1; a > 0; a--) {
			int b = randomInt(a);

			byte t = bytes[a];
			bytes[a] = bytes[b];
			bytes[b] = t;
		}

		return bytes;
	}

	/**
	 * @param chars
	 *            The array to shuffle
	 * @return A copy of the supplied varargs/array of chars with its values
	 *         shuffled
	 */
	public static char[] shuffle(char... chars) {
		for (int a = chars.length - 1; a > 0; a--) {
			int b = randomInt(a);

			char t = chars[a];
			chars[a] = chars[b];
			chars[b] = t;
		}

		return chars;
	}

	/**
	 * @param doubles
	 *            The array to shuffle
	 * @return A copy of the supplied varargs/array of doubles with its values
	 *         shuffled
	 */
	public static double[] shuffle(double... doubles) {
		for (int a = doubles.length - 1; a > 0; a--) {
			int b = randomInt(a);

			double t = doubles[a];
			doubles[a] = doubles[b];
			doubles[b] = t;
		}

		return doubles;
	}

	/**
	 * @param floats
	 *            The array to shuffle
	 * @return A copy of the supplied varargs/array of floats with its values
	 *         shuffled
	 */
	public static float[] shuffle(float... floats) {
		for (int a = floats.length - 1; a > 0; a--) {
			int b = randomInt(a);

			float t = floats[a];
			floats[a] = floats[b];
			floats[b] = t;
		}

		return floats;
	}

	/**
	 * @param ints
	 *            The array to shuffle
	 * @return A copy of the supplied varargs/array of integers with its values
	 *         shuffled
	 */
	public static int[] shuffle(int... ints) {
		for (int a = ints.length - 1; a > 0; a--) {
			int b = randomInt(a);

			int t = ints[a];
			ints[a] = ints[b];
			ints[b] = t;
		}

		return ints;
	}

	/**
	 * @param longs
	 *            The array to shuffle
	 * @return A copy of the supplied varargs/array of longs with its values
	 *         shuffled
	 */
	public static long[] shuffle(long... longs) {
		for (int a = longs.length - 1; a > 0; a--) {
			int b = randomInt(a);

			long t = longs[a];
			longs[a] = longs[b];
			longs[b] = t;
		}

		return longs;
	}

	/**
	 * @param objects
	 *            The array to shuffle
	 * @return A copy of the supplied varargs/array of {@link Object}s with its
	 *         values shuffled
	 */
	public static Object[] shuffle(Object... objects) {
		for (int a = objects.length - 1; a > 0; a--) {
			int b = randomInt(a);

			Object t = objects[a];
			objects[a] = objects[b];
			objects[b] = t;
		}

		return objects;
	}

	/**
	 * @param shorts
	 *            The array to shuffle
	 * @return A copy of the supplied varargs/array of shorts with its values
	 *         shuffled
	 */
	public static short[] shuffle(short... shorts) {
		for (int a = shorts.length - 1; a > 0; a--) {
			int b = randomInt(a);

			short t = shorts[a];
			shorts[a] = shorts[b];
			shorts[b] = t;
		}

		return shorts;
	}

	/**
	 * @param strings
	 *            The array to shuffle
	 * @return A copy of the supplied varargs/array of {@link String}s with its
	 *         values shuffled
	 */
	public static String[] shuffle(String... strings) {
		for (int a = strings.length - 1; a > 0; a--) {
			int b = randomInt(a);

			String t = strings[a];
			strings[a] = strings[b];
			strings[b] = t;
		}

		return strings;
	}

	/**
	 * @param bytes
	 *            The array to sort
	 * @return A copy of the supplied varargs/array of bytes with its values
	 *         sorted
	 */
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

	/**
	 * @param chars
	 *            The array to sort
	 * @return A copy of the supplied varargs/array of chars with its values
	 *         sorted
	 */
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

	/**
	 * @param doubles
	 *            The array to sort
	 * @return A copy of the supplied varargs/array of doubles with its values
	 *         sorted
	 */
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

	/**
	 * @param floats
	 *            The array to sort
	 * @return A copy of the supplied varargs/array of floats with its values
	 *         sorted
	 */
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

	/**
	 * @param ints
	 *            The array to sort
	 * @return A copy of the supplied varargs/array of integers with its values
	 *         sorted
	 */
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

	/**
	 * @param longs
	 *            The array to sort
	 * @return A copy of the supplied varargs/array of longs with its values
	 *         sorted
	 */
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

	/**
	 * @param shorts
	 *            The array to sort
	 * @return A copy of the supplied varargs/array of shorts with its values
	 *         sorted
	 */
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

	/**
	 * @param strings
	 *            The array to sort
	 * @return A copy of the supplied varargs/array of {@link String}s with its
	 *         values sorted
	 */
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