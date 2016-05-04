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
package net.alexanderdev.lightdrive.util.math;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.alexanderdev.lightdrive.util.ArraysX;

/**
 * A collection of useful, convenient mathematic functions.
 * 
 * @author Christian Bryce Alexander
 * @since March 12, 2015 | 7:29:02 PM
 */
public final strictfp class MathX {
	private static final Random RANDOM = new Random();

	/**
	 * Finds the average of a varargs list of bytes.
	 * 
	 * @param values
	 *            The values to be averaged
	 * 
	 * @return The average of the values passed
	 */
	public static double average(byte... values) {
		double sum = 0;

		for (byte b : values)
			sum += b;

		return sum / values.length;
	}

	/**
	 * Finds the average of a varargs list of doubles.
	 * 
	 * @param values
	 *            The values to be averaged
	 * 
	 * @return The average of the values passed
	 */
	public static double average(double... values) {
		double sum = 0;

		for (double d : values)
			sum += d;

		return sum / values.length;
	}

	/**
	 * Finds the average of a varargs list of floats.
	 * 
	 * @param values
	 *            The values to be averaged
	 * 
	 * @return The average of the values passed
	 */
	public static double average(float... values) {
		double sum = 0;

		for (float f : values)
			sum += f;

		return sum / values.length;
	}

	/**
	 * Finds the average of a varargs list of ints.
	 * 
	 * @param values
	 *            The values to be averaged
	 * 
	 * @return The average of the values passed
	 */
	public static double average(int... values) {
		double sum = 0;

		for (int i : values)
			sum += i;

		return sum / values.length;
	}

	/**
	 * Finds the average of a varargs list of longs.
	 * 
	 * @param values
	 *            The values to be averaged
	 * 
	 * @return The average of the values passed
	 */
	public static double average(long... values) {
		double sum = 0;

		for (long l : values)
			sum += l;

		return sum / values.length;
	}

	/**
	 * Finds the average of a varargs list of shorts.
	 * 
	 * @param values
	 *            The values to be averaged
	 * 
	 * @return The average of the values passed
	 */
	public static double average(short... values) {
		double sum = 0;

		for (short s : values)
			sum += s;

		return (short) (sum / values.length);
	}

	/**
	 * Clamps a {@code byte} value between two others.
	 * 
	 * @param value
	 *            Number to be clamped
	 * @param min
	 *            Number for lower limit
	 * @param max
	 *            Number for upper limit
	 * 
	 * @return {@code min} if value is less than min<br>
	 *         {@code max} if value is greater than max<br>
	 *         {@code value} otherwise
	 */
	public static byte clamp(byte value, byte min, byte max) {
		return (value < min) ? min : (value > max) ? max : value;
	}

	/**
	 * Clamps a {@code double} value between two others.
	 * 
	 * @param value
	 *            Number to be clamped
	 * @param min
	 *            Number for lower limit
	 * @param max
	 *            Number for upper limit
	 * 
	 * @return {@code min} if value is less than min<br>
	 *         {@code max} if value is greater than max<br>
	 *         {@code value} otherwise
	 */
	public static double clamp(double value, double min, double max) {
		return (value < min) ? min : (value > max) ? max : value;
	}

	/**
	 * Clamps a {@code float} value between two others.
	 * 
	 * @param value
	 *            Number to be clamped
	 * @param min
	 *            Number for lower limit
	 * @param max
	 *            Number for upper limit
	 * 
	 * @return {@code min} if value is less than min<br>
	 *         {@code max} if value is greater than max<br>
	 *         {@code value} otherwise
	 */
	public static float clamp(float value, float min, float max) {
		return (value < min) ? min : (value > max) ? max : value;
	}

	/**
	 * Clamps a {@code int} value between two others.
	 * 
	 * @param value
	 *            Number to be clamped
	 * @param min
	 *            Number for lower limit
	 * @param max
	 *            Number for upper limit
	 * 
	 * @return {@code min} if value is less than min<br>
	 *         {@code max} if value is greater than max<br>
	 *         {@code value} otherwise
	 */
	public static int clamp(int value, int min, int max) {
		return (value < min) ? min : (value > max) ? max : value;
	}

	/**
	 * Clamps a {@code long} value between two others.
	 * 
	 * @param value
	 *            Number to be clamped
	 * @param min
	 *            Number for lower limit
	 * @param max
	 *            Number for upper limit
	 * 
	 * @return {@code min} if value is less than min<br>
	 *         {@code max} if value is greater than max<br>
	 *         {@code value} otherwise
	 */
	public static long clamp(long value, long min, long max) {
		return (value < min) ? min : (value > max) ? max : value;
	}

	/**
	 * Clamps a {@code short} value between two others.
	 * 
	 * @param value
	 *            Number to be clamped
	 * @param min
	 *            Number for lower limit
	 * @param max
	 *            Number for upper limit
	 * 
	 * @return {@code min} if value is less than min<br>
	 *         {@code max} if value is greater than max<br>
	 *         {@code value} otherwise
	 */
	public static short clamp(short value, short min, short max) {
		return (value < min) ? min : (value > max) ? max : value;
	}

	/**
	 * Finds the maximum of a varargs list of bytes.
	 * 
	 * @param bytes
	 *            The list of bytes to search through
	 * 
	 * @return The maximum {@code byte} in the list
	 */
	public static byte max(byte... bytes) {
		byte max = bytes[0];

		for (byte b : bytes)
			if (b > max)
				max = b;

		return max;
	}

	/**
	 * Finds the maximum of a varargs list of doubles.
	 * 
	 * @param doubles
	 *            The list of doubles to search through
	 * 
	 * @return The maximum {@code double} in the list
	 */
	public static double max(double... doubles) {
		double max = doubles[0];

		for (double d : doubles)
			if (d > max)
				max = d;

		return max;
	}

	/**
	 * Finds the maximum of a varargs list of floats.
	 * 
	 * @param floats
	 *            The list of floats to search through
	 * 
	 * @return The maximum {@code float} in the list
	 */
	public static float max(float... floats) {
		float max = floats[0];

		for (float f : floats)
			if (f > max)
				max = f;

		return max;
	}

	/**
	 * Finds the maximum of a varargs list of ints.
	 * 
	 * @param ints
	 *            The list of ints to search through
	 * 
	 * @return The maximum {@code int} in the list
	 */
	public static int max(int... ints) {
		int max = ints[0];

		for (int i : ints)
			if (i > max)
				max = i;

		return max;
	}

	/**
	 * Finds the maximum of a varargs list of longs.
	 * 
	 * @param longs
	 *            The list of longs to search through
	 * 
	 * @return The maximum {@code long} in the list
	 */
	public static long max(long... longs) {
		long max = longs[0];

		for (long l : longs)
			if (l > max)
				max = l;

		return max;
	}

	/**
	 * Finds the maximum of a varargs list of shorts.
	 * 
	 * @param shorts
	 *            The list of shorts to search through
	 * 
	 * @return The maximum {@code short} in the list
	 */
	public static short max(short... shorts) {
		short max = shorts[0];

		for (short s : shorts)
			if (s > max)
				max = s;

		return max;
	}

	public static double median(byte... values) {
		byte[] sorted = ArraysX.sort(values);

		if (sorted.length % 2 == 0)
			return (sorted[(sorted.length / 2) - 1] + sorted[sorted.length / 2]) / 2.0;
		else
			return sorted[sorted.length / 2];
	}

	public static double median(double... values) {
		double[] sorted = ArraysX.sort(values);

		if (sorted.length % 2 == 0)
			return (sorted[(sorted.length / 2) - 1] + sorted[sorted.length / 2]) / 2.0;
		else
			return sorted[sorted.length / 2];
	}

	public static double median(float... values) {
		float[] sorted = ArraysX.sort(values);

		if (sorted.length % 2 == 0)
			return (sorted[(sorted.length / 2) - 1] + sorted[sorted.length / 2]) / 2.0;
		else
			return sorted[sorted.length / 2];
	}

	public static double median(int... values) {
		int[] sorted = ArraysX.sort(values);

		if (sorted.length % 2 == 0)
			return (sorted[(sorted.length / 2) - 1] + sorted[sorted.length / 2]) / 2.0;
		else
			return sorted[sorted.length / 2];
	}

	public static double median(long... values) {
		long[] sorted = ArraysX.sort(values);

		if (sorted.length % 2 == 0)
			return (sorted[(sorted.length / 2) - 1] + sorted[sorted.length / 2]) / 2.0;
		else
			return sorted[sorted.length / 2];
	}

	public static double median(short... values) {
		short[] sorted = ArraysX.sort(values);

		if (sorted.length % 2 == 0)
			return (sorted[(sorted.length / 2) - 1] + sorted[sorted.length / 2]) / 2.0;
		else
			return sorted[sorted.length / 2];
	}

	/**
	 * Finds the minimum of a varargs list of bytes.
	 * 
	 * @param bytes
	 *            The list of bytes to search through
	 * 
	 * @return The minimum {@code byte} in the list
	 */
	public static byte min(byte... bytes) {
		byte min = bytes[0];

		for (byte b : bytes)
			if (b < min)
				min = b;

		return min;
	}

	/**
	 * Finds the minimum of a varargs list of doubles.
	 * 
	 * @param doubles
	 *            The list of doubles to search through
	 * 
	 * @return The minimum {@code double} in the list
	 */
	public static double min(double... doubles) {
		double min = doubles[0];

		for (double d : doubles)
			if (d < min)
				min = d;

		return min;
	}

	/**
	 * Finds the minimum of a varargs list of floats.
	 * 
	 * @param floats
	 *            The list of floats to search through
	 * 
	 * @return The minimum {@code float} in the list
	 */
	public static float min(float... floats) {
		float min = floats[0];

		for (float f : floats)
			if (f < min)
				min = f;

		return min;
	}

	/**
	 * Finds the minimum of a varargs list of ints.
	 * 
	 * @param ints
	 *            The list of ints to search through
	 * 
	 * @return The minimum {@code int} in the list
	 */
	public static int min(int... ints) {
		int min = ints[0];

		for (int i : ints)
			if (i < min)
				min = i;

		return min;
	}

	/**
	 * Finds the minimum of a varargs list of longs.
	 * 
	 * @param longs
	 *            The list of longs to search through
	 * 
	 * @return The minimum {@code long} in the list
	 */
	public static long min(long... longs) {
		long min = longs[0];

		for (long l : longs)
			if (l < min)
				min = l;

		return min;
	}

	/**
	 * Finds the minimum of a varargs list of shorts.
	 * 
	 * @param shorts
	 *            The list of shorts to search through
	 * 
	 * @return The minimum {@code short} in the list
	 */
	public static short min(short... shorts) {
		short min = shorts[0];

		for (short s : shorts)
			if (s < min)
				min = s;

		return min;
	}

	public static byte mode(byte... values) {
		Map<Byte, Byte> tally = new HashMap<>();

		for (int i = 0; i < values.length; i++) {
			if (tally.containsKey(values[i]))
				tally.put(values[i], (byte) (tally.get(i) + 1));
			else
				tally.put(values[i], (byte) 1);
		}

		byte max = tally.get(0);
		byte maxIndex = 0;

		for (Byte b : tally.keySet())
			if (tally.get(b) > max) {
				max = tally.get(b);
				maxIndex = b;
			}

		return maxIndex;
	}

	public static double mode(double... values) {
		Map<Double, Double> tally = new HashMap<>();

		for (int i = 0; i < values.length; i++) {
			if (tally.containsKey(values[i]))
				tally.put(values[i], tally.get(i) + 1);
			else
				tally.put(values[i], 1.0);
		}

		double max = tally.get(0);
		double maxIndex = 0;

		for (Double d : tally.keySet())
			if (tally.get(d) > max) {
				max = tally.get(d);
				maxIndex = d;
			}

		return maxIndex;
	}

	public static float mode(float... values) {
		Map<Float, Float> tally = new HashMap<>();

		for (int i = 0; i < values.length; i++) {
			if (tally.containsKey(values[i]))
				tally.put(values[i], tally.get(i) + 1f);
			else
				tally.put(values[i], 1f);
		}

		float max = tally.get(0);
		float maxIndex = 0;

		for (Float f : tally.keySet())
			if (tally.get(f) > max) {
				max = tally.get(f);
				maxIndex = f;
			}

		return maxIndex;
	}

	public static int mode(int... values) {
		Map<Integer, Integer> tally = new HashMap<>();

		for (int i = 0; i < values.length; i++) {
			if (tally.containsKey(values[i]))
				tally.put(values[i], tally.get(i) + 1);
			else
				tally.put(values[i], 1);
		}

		int max = tally.get(0);
		int maxIndex = 0;

		for (Integer i : tally.keySet())
			if (tally.get(i) > max) {
				max = tally.get(i);
				maxIndex = i;
			}

		return maxIndex;
	}

	public static long mode(long... values) {
		Map<Long, Long> tally = new HashMap<>();

		for (int i = 0; i < values.length; i++) {
			if (tally.containsKey(values[i]))
				tally.put(values[i], tally.get(i) + 1);
			else
				tally.put(values[i], (long) 1);
		}

		long max = tally.get(0);
		long maxIndex = 0;

		for (Long l : tally.keySet())
			if (tally.get(l) > max) {
				max = tally.get(l);
				maxIndex = l;
			}

		return maxIndex;
	}

	public static short mode(short... values) {
		Map<Short, Short> tally = new HashMap<>();

		for (int i = 0; i < values.length; i++) {
			if (tally.containsKey(values[i]))
				tally.put(values[i], (short) (tally.get(i) + 1));
			else
				tally.put(values[i], (short) 1);
		}

		short max = tally.get(0);
		short maxIndex = 0;

		for (Short s : tally.keySet())
			if (tally.get(s) > max) {
				max = tally.get(s);
				maxIndex = s;
			}

		return maxIndex;
	}

	/**
	 * Determines if two {@code byte}s have opposite signs from each other.
	 * 
	 * @param a
	 *            First of 2 {@code byte}s to compare
	 * @param b
	 *            Second of 2 {@code byte}s to compare
	 * @return {@code true} if {@code a} and {@code b} have opposite signs,
	 *         {@code false} otherwise
	 */
	public static boolean sameSign(byte a, byte b) {
		return (a < 0) == (b < 0);
	}

	/**
	 * Determines if two {@code double}s have opposite signs from each other.
	 * 
	 * @param a
	 *            First of 2 {@code double}s to compare
	 * @param b
	 *            Second of 2 {@code double}s to compare
	 * @return {@code true} if {@code a} and {@code b} have opposite signs,
	 *         {@code false} otherwise
	 */
	public static boolean sameSign(double a, double b) {
		return (a < 0) == (b < 0);
	}

	/**
	 * Determines if two {@code float}s have opposite signs from each other.
	 * 
	 * @param a
	 *            First of 2 {@code float}s to compare
	 * @param b
	 *            Second of 2 {@code float}s to compare
	 * @return {@code true} if {@code a} and {@code b} have opposite signs,
	 *         {@code false} otherwise
	 */
	public static boolean sameSign(float a, float b) {
		return (a < 0) == (b < 0);
	}

	/**
	 * Determines if two {@code int}s have opposite signs from each other.
	 * 
	 * @param a
	 *            First of 2 {@code int}s to compare
	 * @param b
	 *            Second of 2 {@code int}s to compare
	 * @return {@code true} if {@code a} and {@code b} have opposite signs,
	 *         {@code false} otherwise
	 */
	public static boolean sameSign(int a, int b) {
		return (a < 0) == (b < 0);
	}

	/**
	 * Determines if two {@code long}s have opposite signs from each other.
	 * 
	 * @param a
	 *            First of 2 {@code long}s to compare
	 * @param b
	 *            Second of 2 {@code long}s to compare
	 * @return {@code true} if {@code a} and {@code b} have opposite signs,
	 *         {@code false} otherwise
	 */
	public static boolean sameSign(long a, long b) {
		return (a < 0) == (b < 0);
	}

	/**
	 * Determines if two {@code short}s have opposite signs from each other.
	 * 
	 * @param a
	 *            First of 2 {@code short}s to compare
	 * @param b
	 *            Second of 2 {@code short}s to compare
	 * @return {@code true} if {@code a} and {@code b} have opposite signs,
	 *         {@code false} otherwise
	 */
	public static boolean sameSign(short a, short b) {
		return (a < 0) == (b < 0);
	}

	/**
	 * Static wrapper method for {@link Random}'s {@code nextBoolean()}.
	 * 
	 * @see java.util.Random#nextBoolean()
	 * 
	 * @return A random {@code boolean}
	 */
	public static boolean randomBoolean() {
		return RANDOM.nextBoolean();
	}

	/**
	 * Based on {@code java.util.Random}'s {@code nextInt()}, generates a random
	 * {@code int} turns it into a {@code byte}.
	 * 
	 * @return A random {@code byte}
	 */
	public static byte randomByte() {
		return (byte) (randomInt() % (Byte.MAX_VALUE + 1));
	}

	/**
	 * Static wrapper method for {@link Random}'s {@code nextBytes()}
	 * 
	 * @see java.util.Random#nextBytes()
	 * 
	 * @param numberOf
	 *            How many bytes to be generated
	 * 
	 * @return A random array of {@code byte}s of size {@code numberOf}
	 */
	public static byte[] randomBytes(int numberOf) {
		byte[] bytes = new byte[numberOf];

		RANDOM.nextBytes(bytes);

		return bytes;
	}

	/**
	 * Static wrapper method for {@link Random}'s {@code nextDouble()}
	 * 
	 * @see java.util.Random#nextDouble()
	 * 
	 * @return A random {@code double}
	 */
	public static double randomDouble() {
		return RANDOM.nextDouble();
	}

	/**
	 * Static wrapper method for {@link Random}'s {@code nextFloat()}
	 * 
	 * @see java.util.Random#nextFloat()
	 * 
	 * @return A random {@code float}
	 */
	public static float randomFloat() {
		return RANDOM.nextFloat();
	}

	/**
	 * Static wrapper method for {@link Random}'s {@code nextGaussian()}
	 * 
	 * @see java.util.Random#nextGaussian()
	 * 
	 * @return A random gaussian distributed {@code double}
	 */
	public static double randomGaussian() {
		return RANDOM.nextGaussian();
	}

	/**
	 * Static wrapper method for {@link Random}'s {@code nextInt()}
	 * 
	 * @see java.util.Random#randomInt()
	 * 
	 * @return A random {@code int}
	 */
	public static int randomInt() {
		return RANDOM.nextInt();
	}

	/**
	 * Static wrapper method for {@link Random}'s {@code nextInt(int bound)}
	 * 
	 * @see java.util.Random#nextInt(int bound)
	 * 
	 * @param bound
	 *            The maximum (exclusive) value to be generated
	 * 
	 * @return A random {@code int} between 0 and {@code bound} (exclusive)
	 */
	public static int randomInt(int bound) {
		return RANDOM.nextInt(bound);
	}

	/**
	 * Based on {@link Random}'s {@code nextInt(int bound)}, generates an
	 * {@code int} between two bounds
	 * 
	 * @param min
	 *            Smallest number to generate (inclusive)
	 * @param max
	 *            Largest number to generate (inclusive)
	 * 
	 * @return A random {@code int} between min and max inclusive
	 */
	public static int randomInt(int min, int max) {
		if (min == max)
			return min;

		if (min > max) {
			min ^= max;
			max ^= min;
			min ^= max;
		}

		if (min < 0 && max > 0)
			return RANDOM.nextInt(Math.abs(min) + Math.abs(max) + 1) + min;

		else if (min < 0 && max < 0)
			return -(RANDOM.nextInt((-min - -max) + 1) + -max);

		else if (min > 0 && max > 0)
			return RANDOM.nextInt((max - min) + 1) + min;

		return 0;
	}

	/**
	 * Static wrapper method for {@link Random}'s {@code nextLong()}
	 * 
	 * @see java.util.Random#nextLong()
	 * 
	 * @return A random {@code long}
	 */
	public static long randomLong() {
		return RANDOM.nextLong();
	}

	/**
	 * Based on {@link Random}'s {@code nextInt()}, generates a random
	 * {@code int} turns it into a {@code short}.
	 * 
	 * @return A random {@code short}
	 */
	public static short randomShort() {
		return (short) (randomInt() % (Short.MAX_VALUE + 1));
	}

	public static byte range(byte... values) {
		return (byte) (max(values) - min(values));
	}

	public static double range(double... values) {
		return max(values) - min(values);
	}

	public static float range(float... values) {
		return max(values) - min(values);
	}

	public static int range(int... values) {
		return max(values) - min(values);
	}

	public static long range(long... values) {
		return max(values) - min(values);
	}

	public static short range(short... values) {
		return (short) (max(values) - min(values));
	}

	public static float tween(float value, float target, float factor) {
		value += (target - value) * factor;
		return value;
	}

	public static double tween(double value, double target, double factor) {
		value += (target - value) * factor;
		return value;
	}

	/**
	 * Wraps a {@code byte} value around a min and max value, acting like a
	 * bidirectional modulus. When the value exceeds the min or max, it wraps to
	 * the opposite limit and continues in the same direction for the remainder,
	 * until the value falls between them (inclusively).
	 * 
	 * @param value
	 *            The value to be wrapped
	 * @param min
	 *            The minimum value
	 * @param max
	 *            The maximum value
	 * 
	 * @return A {@code byte} value wrapped around a min and max until it falls
	 *         between them
	 */
	public static byte wrap(byte value, byte min, byte max) {
		byte range = (byte) (max - min + 1);

		while (value < min)
			value += range;

		while (value > max)
			value -= range;

		return value;
	}

	/**
	 * Wraps a {@code double} value around a min and max value, acting like a
	 * bidirectional modulus. When the value exceeds the min or max, it wraps to
	 * the opposite limit and continues in the same direction for the remainder,
	 * until the value falls between them (inclusively).
	 * 
	 * @param value
	 *            The value to be wrapped
	 * @param min
	 *            The minimum value
	 * @param max
	 *            The maximum value
	 * 
	 * @return A {@code double} value wrapped around a min and max until it
	 *         falls between them
	 */
	public static double wrap(double value, double min, double max) {
		double range = max - min + 1;

		while (value < min)
			value += range;

		while (value > max)
			value -= range;

		return value;
	}

	/**
	 * Wraps a {@code float} value around a min and max value, acting like a
	 * bidirectional modulus. When the value exceeds the min or max, it wraps to
	 * the opposite limit and continues in the same direction for the remainder,
	 * until the value falls between them (inclusively).
	 * 
	 * @param value
	 *            The value to be wrapped
	 * @param min
	 *            The minimum value
	 * @param max
	 *            The maximum value
	 * 
	 * @return A {@code float} value wrapped around a min and max until it falls
	 *         between them
	 */
	public static float wrap(float value, float min, float max) {
		float range = max - min + 1;

		while (value < min)
			value += range;

		while (value > max)
			value -= range;

		return value;
	}

	/**
	 * Wraps a {@code int} value around a min and max value, acting like a
	 * bidirectional modulus. When the value exceeds the min or max, it wraps to
	 * the opposite limit and continues in the same direction for the remainder,
	 * until the value falls between them (inclusively).
	 * 
	 * @param value
	 *            The value to be wrapped
	 * @param min
	 *            The minimum value
	 * @param max
	 *            The maximum value
	 * 
	 * @return A {@code int} value wrapped around a min and max until it falls
	 *         between them
	 */
	public static int wrap(int value, int min, int max) {
		int range = max - min + 1;

		while (value < min)
			value += range;

		while (value > max)
			value -= range;

		return value;
	}

	/**
	 * Wraps a {@code long} value around a min and max value, acting like a
	 * bidirectional modulus. When the value exceeds the min or max, it wraps to
	 * the opposite limit and continues in the same direction for the remainder,
	 * until the value falls between them (inclusively).
	 * 
	 * @param value
	 *            The value to be wrapped
	 * @param min
	 *            The minimum value
	 * @param max
	 *            The maximum value
	 * 
	 * @return A {@code long} value wrapped around a min and max until it falls
	 *         between them
	 */
	public static long wrap(long value, long min, long max) {
		long range = max - min + 1;

		while (value < min)
			value += range;

		while (value > max)
			value -= range;

		return value;
	}

	/**
	 * Wraps a {@code short} value around a min and max value, acting like a
	 * bidirectional modulus. When the value exceeds the min or max, it wraps to
	 * the opposite limit and continues in the same direction for the remainder,
	 * until the value falls between them (inclusively).
	 * 
	 * @param value
	 *            The value to be wrapped
	 * @param min
	 *            The minimum value
	 * @param max
	 *            The maximum value
	 * 
	 * @return A {@code short} value wrapped around a min and max until it falls
	 *         between them
	 */
	public static short wrap(short value, short min, short max) {
		short range = (short) (max - min + 1);

		while (value < min)
			value += range;

		while (value > max)
			value -= range;

		return value;
	}
}