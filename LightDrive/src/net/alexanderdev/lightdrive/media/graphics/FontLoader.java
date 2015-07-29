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
package net.alexanderdev.lightdrive.media.graphics;

import static java.awt.Font.TRUETYPE_FONT;
import static java.awt.Font.createFont;
import static java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

/**
 * @author Christian Bryce Alexander
 * @since May 22, 2015 | 11:51:04 AM
 */
public final class FontLoader {
	private static String path = "";

	/**
	 * When loading multiple fonts from a certain folder, you can set a relative
	 * path to that folder, and just use the fonts name when loading them
	 * 
	 * @param path
	 *            The relative path to load fonts from
	 */
	public static void setRelativePath(String path) {
		FontLoader.path = path;
	}

	public static void loadTTF(String filename) {
		try {
			Font font = createFont(TRUETYPE_FONT, FontLoader.class.getResourceAsStream(path + filename + ".ttf"));
			
			getLocalGraphicsEnvironment().registerFont(font);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
	}
}