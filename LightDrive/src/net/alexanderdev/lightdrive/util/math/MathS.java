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

import java.util.Random;

/**
 * A collection of useful, convenient mathematic functions.
 * 
 * @author Christian Bryce Alexander
 * @since March 12, 2015 | 7:29:02 PM
 */
public final strictfp class MathS {
	private static Random random = new Random();

	/**
	 * Finds the average of a list of bytes
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
	 * Finds the average of a list of doubles
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
	 * Finds the average of a list of floats
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
	 * Finds the average of a list of ints
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
	 * Finds the average of a list of longs
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
	 * Finds the average of a list of shorts
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
	 * Clamps a {@code byte} value between two numbers
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
		return value < min ? min : value > max ? max : value;
	}

	/**
	 * Clamps a {@code double} value between two numbers
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
		return value < min ? min : value > max ? max : value;
	}

	/**
	 * Clamps a {@code float} value between two numbers
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
		return value < min ? min : value > max ? max : value;
	}

	/**
	 * Clamps a {@code int} value between two numbers
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
		return value < min ? min : value > max ? max : value;
	}

	/**
	 * Clamps a {@code long} value between two numbers
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
		return value < min ? min : value > max ? max : value;
	}

	/**
	 * Clamps a {@code short} value between two numbers
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
		return value < min ? min : value > max ? max : value;
	}

	public static byte max(byte... bytes) {
		byte max = bytes[0];

		for (byte b : bytes)
			if (b > max)
				max = b;

		return max;
	}

	public static double max(double... doubles) {
		double max = doubles[0];

		for (double d : doubles)
			if (d > max)
				max = d;

		return max;
	}

	public static float max(float... floats) {
		float max = floats[0];

		for (float f : floats)
			if (f > max)
				max = f;

		return max;
	}

	public static int max(int... ints) {
		int max = ints[0];

		for (int i : ints)
			if (i > max)
				max = i;

		return max;
	}

	public static long max(long... longs) {
		long max = longs[0];

		for (long l : longs)
			if (l > max)
				max = l;

		return max;
	}

	public static short max(short... shorts) {
		short max = shorts[0];

		for (short s : shorts)
			if (s > max)
				max = s;

		return max;
	}

	public static byte min(byte... bytes) {
		byte min = bytes[0];

		for (byte b : bytes)
			if (b < min)
				min = b;

		return min;
	}

	public static double min(double... doubles) {
		double min = doubles[0];

		for (double d : doubles)
			if (d < min)
				min = d;

		return min;
	}

	public static float min(float... floats) {
		float min = floats[0];

		for (float f : floats)
			if (f < min)
				min = f;

		return min;
	}

	public static int min(int... ints) {
		int min = ints[0];

		for (int i : ints)
			if (i < min)
				min = i;

		return min;
	}

	public static long min(long... longs) {
		long min = longs[0];

		for (long l : longs)
			if (l < min)
				min = l;

		return min;
	}

	public static short min(short... shorts) {
		short min = shorts[0];

		for (short s : shorts)
			if (s < min)
				min = s;

		return min;
	}

	/**
	 * @return {@code true} if the parameters have opposite signs, {@code false}
	 *         otherwise
	 */
	public static boolean oppositeSigns(byte a, byte b) {
		return (a < 0 && b > 0) || (a > 0 && b < 0);
	}

	/**
	 * @return {@code true} if the parameters have opposite signs, {@code false}
	 *         otherwise
	 */
	public static boolean oppositeSigns(double a, double b) {
		return (a < 0 && b > 0) || (a > 0 && b < 0);
	}

	/**
	 * @return {@code true} if the parameters have opposite signs, {@code false}
	 *         otherwise
	 */
	public static boolean oppositeSigns(float a, float b) {
		return (a < 0 && b > 0) || (a > 0 && b < 0);
	}

	/**
	 * @return {@code true} if the parameters have opposite signs, {@code false}
	 *         otherwise
	 */
	public static boolean oppositeSigns(int a, int b) {
		return (a < 0 && b > 0) || (a > 0 && b < 0);
	}

	/**
	 * @return {@code true} if the parameters have opposite signs, {@code false}
	 *         otherwise
	 */
	public static boolean oppositeSigns(long a, long b) {
		return (a < 0 && b > 0) || (a > 0 && b < 0);
	}

	/**
	 * @return {@code true} if the parameters have opposite signs, {@code false}
	 *         otherwise
	 */
	public static boolean oppositeSigns(short a, short b) {
		return (a < 0 && b > 0) || (a > 0 && b < 0);
	}

	/**
	 * Static wrapper method for {@code java.util.Random}'s
	 * {@code nextBoolean()}
	 * 
	 * @see java.util.Random#nextBoolean()
	 * 
	 * @return A random boolean
	 */
	public static boolean randomBoolean() {
		return random.nextBoolean();
	}

	/**
	 * Taken from {@code java.util.Random}'s {@code nextInt()}, then cast to a
	 * byte
	 * 
	 * @return A random byte
	 */
	public static byte randomByte() {
		return (byte) randomInt();
	}

	/**
	 * Static wrapper method for {@code java.util.Random}'s
	 * {@code randomBytes()}
	 * 
	 * @see java.util.Random#randomBytes()
	 * 
	 * @param numberOf
	 *            How many bytes to be generated
	 * 
	 * @return A random array of bytes of size {@code numberOf}
	 */
	public static byte[] randomBytes(int numberOf) {
		byte[] bytes = new byte[numberOf];

		random.nextBytes(bytes);

		return bytes;
	}

	/**
	 * Static wrapper method for {@code java.util.Random}'s {@code nextDouble()}
	 * 
	 * @see java.util.Random#nextDouble()
	 * 
	 * @return A random double
	 */
	public static double randomDouble() {
		return random.nextDouble();
	}

	/**
	 * Static wrapper method for {@code java.util.Random}'s {@code nextFloat()}
	 * 
	 * @see java.util.Random#nextFloat()
	 * 
	 * @return A random float
	 */
	public static float randomFloat() {
		return random.nextFloat();
	}

	/**
	 * Static wrapper method for {@code java.util.Random}'s
	 * {@code nextGaussian()}
	 * 
	 * @see java.util.Random#nextGaussian()
	 * 
	 * @return A random gaussian double
	 */
	public static double randomGaussian() {
		return random.nextGaussian();
	}

	/**
	 * Static wrapper method for {@code java.util.Random}'s {@code randomInt()}
	 * 
	 * @see java.util.Random#randomInt()
	 * 
	 * @return A random int
	 */
	public static int randomInt() {
		return random.nextInt();
	}

	/**
	 * Static wrapper method for {@code java.util.Random}'s
	 * {@code nextInt(int bound)}
	 * 
	 * @see java.util.Random#nextInt(int bound)
	 * 
	 * @param bound
	 *            The maximum (exclusive) value to be generated
	 * 
	 * @return A random int between 0 and {@code bound} (exclusive)
	 */
	public static int randomInt(int bound) {
		return random.nextInt(bound);
	}

	/**
	 * Based on {@code java.util.Random}'s {@code randomInt(int bound)},
	 * generates an int between two bounds
	 * 
	 * @param min
	 *            Smallest number to generate (inclusive)
	 * @param max
	 *            Largest number to generate (inclusive)
	 * 
	 * @return A random int between min and max inclusive
	 */
	public static int randomInt(int min, int max) {
		if (min < max)
			if (min < 0 && max > 0)
				return random.nextInt(Math.abs(min) + Math.abs(max) + 1) + min;

			else if (min < 0 && max < 0)
				return -(random.nextInt((-min - -max) + 1) + -max);

			else if (min > 0 && max > 0)
				return random.nextInt((max - min) + 1) + min;

		return 0;
	}

	/**
	 * Static wrapper method for {@code java.util.Random}'s {@code nextLong()}
	 * 
	 * @see java.util.Random#nextLong()
	 * 
	 * @return A random long
	 */
	public static long randomLong() {
		return random.nextLong();
	}

	/**
	 * Taken from {@code java.util.Random}'s {@code nextInt()}, then cast to a
	 * short
	 * 
	 * @return A random short
	 */
	public static short randomShort() {
		return (short) randomInt();
	}
}