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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Christian Bryce Alexander
 * @since May 16, 2015 | 3:13:06 PM
 */
public final class Screenshot {
	private static String path;

	/**
	 * Sets the path to save screenshots to
	 */
	public static void setRelativePath(String path) {
		if (!path.endsWith("\\"))
			path += "\\";

		Screenshot.path = path;
	}

	/**
	 * Saves a screenshot
	 */
	public static void createScreenshot(BufferedImage image, String name, String format) {
		try {
			File outputfile = new File(path + name + "." + format);
			ImageIO.write(image, format, outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}