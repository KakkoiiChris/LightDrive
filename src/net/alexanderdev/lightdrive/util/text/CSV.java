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
package net.alexanderdev.lightdrive.util.text;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of methods for parsing individual lines in a CSV file format.
 * 
 * @author Christian Bryce Alexander
 * @since Apr 23, 2016, 9:19:14 PM
 */
public class CSV {
	/**
	 * Process the line as a list of {@code byte}s in base ten.
	 *
	 * @param line
	 *            The line to parse
	 * @return An array of every {@code byte} parsed from the line
	 */
	public static byte[] parseBytes(String line) {
		String[] data = parseStrings(line);

		byte[] bytes = new byte[data.length];

		for (int i = 0; i < data.length; i++)
			bytes[i] = Byte.parseByte(data[i]);

		return bytes;
	}

	/**
	 * Process the line as a list of {@code byte}s in the specified base.
	 *
	 * @param line
	 *            The line to parse
	 * @return An array of every {@code byte} parsed from the line
	 */
	public static byte[] parseBytes(String line, int radix) {
		String[] data = parseStrings(line);

		byte[] bytes = new byte[data.length];

		for (int i = 0; i < data.length; i++)
			bytes[i] = Byte.parseByte(data[i], radix);

		return bytes;
	}

	/**
	 * Process the line as a list of {@code char}s in base ten.
	 *
	 * @param line
	 *            The line to parse
	 * @return An array of every {@code char} parsed from the line
	 */
	public static char[] parseChars(String line) {
		String[] data = parseStrings(line);

		char[] chars = new char[data.length];

		for (int i = 0; i < data.length; i++)
			chars[i] = data[i].charAt(0);

		return chars;
	}

	/**
	 * Process the line as a list of {@code double}s in base ten.
	 *
	 * @param line
	 *            The line to parse
	 * @return An array of every {@code double} parsed from the line
	 */
	public static double[] parseDoubles(String line) {
		String[] data = parseStrings(line);

		double[] doubles = new double[data.length];

		for (int i = 0; i < data.length; i++)
			doubles[i] = Double.parseDouble(data[i]);

		return doubles;
	}

	/**
	 * Process the line as a list of {@code float}s in base ten.
	 *
	 * @param line
	 *            The line to parse
	 * @return An array of every {@code float} parsed from the line
	 */
	public static float[] parseFloats(String line) {
		String[] data = parseStrings(line);

		float[] floats = new float[data.length];

		for (int i = 0; i < data.length; i++)
			floats[i] = Float.parseFloat(data[i]);

		return floats;
	}

	/**
	 * Process the line as a list of {@code int}s in base ten.
	 *
	 * @param line
	 *            The line to parse
	 * @return An array of every {@code int} parsed from the line
	 */
	public static int[] parseInts(String line) {
		String[] data = parseStrings(line);

		int[] ints = new int[data.length];

		for (int i = 0; i < data.length; i++)
			ints[i] = Integer.parseInt(data[i]);

		return ints;
	}

	/**
	 * Process the line as a list of {@code int}s in the specified base.
	 *
	 * @param line
	 *            The line to parse
	 * @return An array of every {@code int} parsed from the line
	 */
	public static int[] parseInts(String line, int radix) {
		String[] data = parseStrings(line);

		int[] ints = new int[data.length];

		for (int i = 0; i < data.length; i++)
			ints[i] = Integer.parseInt(data[i], radix);

		return ints;
	}

	/**
	 * Process the line as a list of {@code long}s in base ten.
	 *
	 * @param line
	 *            The line to parse
	 * @return An array of every {@code long} parsed from the line
	 */
	public static long[] parseLongs(String line) {
		String[] data = parseStrings(line);

		long[] longs = new long[data.length];

		for (int i = 0; i < data.length; i++)
			longs[i] = Long.parseLong(data[i]);

		return longs;
	}

	/**
	 * Process the line as a list of {@code long}s in the specified base.
	 *
	 * @param line
	 *            The line to parse
	 * @return An array of every {@code long} parsed from the line
	 */
	public static long[] parseLongs(String line, int radix) {
		String[] data = parseStrings(line);

		long[] longs = new long[data.length];

		for (int i = 0; i < data.length; i++)
			longs[i] = Long.parseLong(data[i], radix);

		return longs;
	}

	/**
	 * Process the line as a list of {@code short}s in base ten.
	 *
	 * @param line
	 *            The line to parse
	 * @return An array of every {@code short} parsed from the line
	 */
	public static short[] parseShorts(String line) {
		String[] data = parseStrings(line);

		short[] shorts = new short[data.length];

		for (int i = 0; i < data.length; i++)
			shorts[i] = Short.parseShort(data[i]);

		return shorts;
	}

	/**
	 * Process the line as a list of {@code short}s in the specified base.
	 *
	 * @param line
	 *            The line to parse
	 * @return An array of every {@code short} parsed from the line
	 */
	public static short[] parseShorts(String line, int radix) {
		String[] data = parseStrings(line);

		short[] shorts = new short[data.length];

		for (int i = 0; i < data.length; i++)
			shorts[i] = Short.parseShort(data[i], radix);

		return shorts;
	}

	/**
	 * Process the line as a list of {@link String}s.
	 *
	 * @param line
	 *            The line to parse
	 * @return An array of every {@link String} parsed from the line
	 */
	public static String[] parseStrings(String line) {
		List<String> data = new ArrayList<>();

		String token = "";

		boolean inSingleQuotes = false;
		boolean inDoubleQuotes = false;

		line += "\0";

		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);

			if (!inDoubleQuotes && c == '\'') {
				inSingleQuotes = !inSingleQuotes;
				continue;
			}

			if (!inSingleQuotes && c == '"') {
				inDoubleQuotes = !inDoubleQuotes;
				continue;
			}

			if (!inSingleQuotes && !inDoubleQuotes && (c == ',' || c == '\0')) {
				data.add(token);
				token = "";
				continue;
			}

			token += c;
		}

		return data.toArray(new String[data.size()]);
	}
}