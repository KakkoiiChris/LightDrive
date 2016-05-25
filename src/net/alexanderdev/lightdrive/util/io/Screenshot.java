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

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.alexanderdev.lightdrive.graphics.Sprite;
import net.alexanderdev.lightdrive.view.Viewable;

/**
 * A class used to save screenshots of any {@link Viewable}.
 * 
 * @author Christian Bryce Alexander
 * @since May 16, 2015, 3:13:06 PM
 */
public final class Screenshot {
	private static String path;

	/**
	 * Sets the absolute path to save screenshots to.
	 * 
	 * @param path
	 *            The path to set
	 */
	public static void setPath(String path) {
		if (!path.endsWith("\\"))
			path += "\\";

		Screenshot.path = path;
	}

	/**
	 * Writes the {@link Sprite} context of any {@link Viewable} as a PNG to the
	 * specified folder.
	 * 
	 * @param view
	 *            The {@link Viewable} to take a screenshot of
	 * @param name
	 *            The name to save the screenshot as
	 */
	public static void createScreenshot(Viewable view, String name) {
		try {
			File outputfile = new File(path + name + ".png");
			ImageIO.write(view.getContext(), "png", outputfile);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}