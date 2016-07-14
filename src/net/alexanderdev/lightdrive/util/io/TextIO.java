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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for reading and writing plain text files line by line from a source
 * folder.
 * 
 * @author Christian Bryce Alexander
 * @since Apr 9, 2015, 1:47:53 AM
 */
public class TextIO {
	private static String path = "";

	/**
	 * Sets the path that will be prepended to the filename when loading a text
	 * document from a file.
	 * 
	 * @param path
	 *            The path to be prepended
	 */
	public static void setPath(String path) {
		TextIO.path = path;
	}

	/**
	 * Reads a text file line by line.
	 * 
	 * @param name
	 *            The name of the file to read
	 * @return A {@link String}{@code []} containing each individual line of
	 *         text
	 */
	public static String[] readResourceFile(String name) {
		List<String> lines = new ArrayList<>();

		InputStream inStream = TextIO.class.getResourceAsStream(path + name);

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
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		return lines.toArray(new String[lines.size()]);
	}

	public static String[] readFile(String name) {
		List<String> lines = new ArrayList<>();

		File file = new File(name);

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		}
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

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
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		return lines.toArray(new String[lines.size()]);
	}

	/**
	 * Writes a text file line by line.
	 * 
	 * @param path
	 *            Path to the file to write
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
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}