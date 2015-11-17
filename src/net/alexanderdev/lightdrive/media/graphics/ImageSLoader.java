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
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A static class to easily load images from a project source folder
 * 
 * @author Christian Bryce Alexander
 * @since March 12, 2015 | 6:25:06 PM
 */
public final class ImageSLoader {
	private static String path = "";

	/**
	 * When loading multiple images from a certain folder, you can set a
	 * relative path to that folder, and just use the images' names when loading
	 * them
	 * 
	 * @param path
	 *            The relative path to load images from
	 */
	public static void setRelativePath(String path) {
		ImageSLoader.path = path;
	}

	private static ImageS load(String filename) {
		BufferedImage image = null;

		try {
			image = ImageIO.read(ImageSLoader.class.getResource(path + filename));
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return new ImageS(image);
	}

	/**
	 * Loads a PNG image from a source folder
	 *
	 * @param imageName
	 *            The path to the image to load
	 * 
	 * @return A loaded {@code BufferedImage}
	 */
	public static ImageS loadPNG(String filename) {
		return load(filename + ".png");
	}

	/**
	 * Loads a GIF image from a source folder
	 *
	 * @param imageName
	 *            The path to the image to load
	 * 
	 * @return A loaded {@code BufferedImage}
	 */
	public static ImageS loadGIF(String filename) {
		return load(filename + ".gif");
	}

	/**
	 * Loads JPG/JPEG image from a source folder
	 *
	 * @param imageName
	 *            The path to the image to load
	 * 
	 * @return A loaded {@code BufferedImage}
	 */
	public static ImageS loadJPEG(String filename) {
		ImageS image;

		if ((image = load(filename + ".jpg")) == null)
			image = load(filename + ".jpeg");

		return image;
	}

	/**
	 * Loads a BMP image from a source folder
	 *
	 * @param imageName
	 *            The path to the image to load
	 * 
	 * @return A loaded {@code BufferedImage}
	 */
	public static ImageS loadBMP(String filename) {
		return load(filename + ".bmp");
	}
}