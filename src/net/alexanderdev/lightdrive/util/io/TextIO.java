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
package net.alexanderdev.lightdrive.util.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * A class for reading and witing plain text files line by line.
 * 
 * @author Christian Bryce Alexander
 * @since Apr 9, 2015 | 1:47:53 AM
 */
public class TextIO {
	/**
	 * Reads in a text file line by line
	 * 
	 * @param path
	 *            Path to the file to read in
	 * 
	 * @return A {@code String[]} containing each individual line of text
	 */
	public static String[] readFile(String path) {
		ArrayList<String> lines = new ArrayList<>();

		InputStream inStream = TextIO.class.getResourceAsStream(path);

		BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));

		String line;

		try {
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		return lines.toArray(new String[lines.size()]);
	}

	/**
	 * Writes a text file line by line
	 * 
	 * @param path
	 *            Path to the file to write
	 * 
	 * @param lines
	 *            Lines of text to write to the file
	 */
	public static void writeFile(String path, String[] lines) {
		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "utf-8"));

			for (String line : lines) {
				writer.write(line);
				writer.newLine();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				writer.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}