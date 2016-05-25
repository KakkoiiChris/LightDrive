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

import java.awt.Font;
import java.awt.GraphicsEnvironment;

/**
 * A class for registering new {@link Font}s from a source folder with the local
 * {@link GraphicsEnvironment}.
 * 
 * @author Christian Bryce Alexander
 * @since May 22, 2015 | 11:51:04 AM
 */
public final class FontIO {
	private static String path = "";

	/**
	 * Sets the path that will be prepended to the filename when loading an
	 * image from a file.
	 * 
	 * @param path
	 *            The path to be prepended
	 */
	public static void setPath(String path) {
		FontIO.path = path;
	}

	/**
	 * Registers a <b>T</b>rue <b>T</b>ype <b>F</b>ont file with the local
	 * {@link GraphicsEnvironment}.
	 * 
	 * @param name
	 *            The name of the font file to be loaded
	 * @return The full name of the font as registered by the
	 *         {@link GraphicsEnvironment}
	 */
	public static Font loadTTF(String name) {
		return load(name, Font.TRUETYPE_FONT, "ttf");
	}

	/**
	 * Registers an <b>O</b>pen <b>T</b>ype <b>F</b>ont file with the local
	 * {@link GraphicsEnvironment}.
	 * 
	 * @param name
	 *            The name of the font file to be loaded
	 * @return The full name of the font as registered by the
	 *         {@link GraphicsEnvironment}
	 */
	public static Font loadOTF(String name) {
		return load(name, Font.TRUETYPE_FONT, "otf");
	}

	/**
	 * Registers a <b>Type 1</b> Font file into the local
	 * {@link GraphicsEnvironment}.
	 * 
	 * @param name
	 *            The name of the font file to be loaded
	 * @return The full name of the font as registered by the
	 *         {@link GraphicsEnvironment}
	 */
	public static Font loadType1(String name) {
		return load(name, Font.TYPE1_FONT, "pbf", "pfa", "lwfn");
	}

	private static Font load(String name, int type, String... exts) {
		Font font = null;

		for (String ext : exts) {
			try {
				font = Font.createFont(type, FontIO.class.getResourceAsStream(path + name + "." + ext));

				GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
			}
			catch (Exception e) {
				try {
					font = Font.createFont(type, FontIO.class.getResourceAsStream(name + "." + ext));

					GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
				}
				catch (Exception e2) {
					e2.printStackTrace();
				}
			}

			if (font != null)
				break;
		}

		return font;
	}
}