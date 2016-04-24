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
 * @author Christian Bryce Alexander
 * @since Apr 23, 2016, 9:19:14 PM
 */
public class CSV {
	public static byte[] parseBytes(String line) {
		String[] data = parseStrings(line);

		byte[] bytes = new byte[data.length];

		for (int i = 0; i < data.length; i++)
			bytes[i] = Byte.parseByte(data[i]);

		return bytes;
	}
	
	public static byte[] parseBytes(String line, int radix) {
		String[] data = parseStrings(line);

		byte[] bytes = new byte[data.length];

		for (int i = 0; i < data.length; i++)
			bytes[i] = Byte.parseByte(data[i], radix);

		return bytes;
	}
	
	public static short[] parseShorts(String line) {
		String[] data = parseStrings(line);

		short[] shorts = new short[data.length];

		for (int i = 0; i < data.length; i++)
			shorts[i] = Short.parseShort(data[i]);

		return shorts;
	}
	
	public static short[] parseShorts(String line, int radix) {
		String[] data = parseStrings(line);

		short[] shorts = new short[data.length];

		for (int i = 0; i < data.length; i++)
			shorts[i] = Short.parseShort(data[i], radix);

		return shorts;
	}
	
	public static int[] parseInts(String line) {
		String[] data = parseStrings(line);

		int[] ints = new int[data.length];

		for (int i = 0; i < data.length; i++)
			ints[i] = Integer.parseInt(data[i]);

		return ints;
	}
	
	public static int[] parseInts(String line, int radix) {
		String[] data = parseStrings(line);

		int[] ints = new int[data.length];

		for (int i = 0; i < data.length; i++)
			ints[i] = Integer.parseInt(data[i], radix);

		return ints;
	}
	
	public static long[] parseLongs(String line) {
		String[] data = parseStrings(line);

		long[] longs = new long[data.length];

		for (int i = 0; i < data.length; i++)
			longs[i] = Long.parseLong(data[i]);

		return longs;
	}
	
	public static long[] parseLongs(String line, int radix) {
		String[] data = parseStrings(line);

		long[] longs = new long[data.length];

		for (int i = 0; i < data.length; i++)
			longs[i] = Long.parseLong(data[i], radix);

		return longs;
	}
	
	public static float[] parseFloats(String line) {
		String[] data = parseStrings(line);

		float[] floats = new float[data.length];

		for (int i = 0; i < data.length; i++)
			floats[i] = Float.parseFloat(data[i]);

		return floats;
	}
	
	public static double[] parseDoubles(String line) {
		String[] data = parseStrings(line);

		double[] doubles = new double[data.length];

		for (int i = 0; i < data.length; i++)
			doubles[i] = Double.parseDouble(data[i]);

		return doubles;
	}
	
	public static char[] parseChars(String line) {
		String[] data = parseStrings(line);

		char[] chars = new char[data.length];

		for (int i = 0; i < data.length; i++)
			chars[i] = data[i].charAt(0);

		return chars;
	}

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