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

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import net.alexanderdev.lightdrive.graphics.Sprite;

/**
 * A static class to easily load images from a project source folder
 * 
 * @author Christian Bryce Alexander
 * @since March 12, 2015 | 6:25:06 PM
 */
public class SpriteIO {
	private static String path = "";

	/**
	 * Sets the path that will be prepended to the filename when loading an
	 * image from a file.
	 * 
	 * @param path
	 *            The path to be prepended
	 */
	public static void setPath(String path) {
		SpriteIO.path = path;
	}

	/**
	 * Loads an image from the specified path to a {@link Sprite}. The extension
	 * is automatically appended.
	 * 
	 * @param name
	 *            The name of the image to load
	 */
	public static Sprite loadPNG(String name) {
		return load(name, "png");
	}

	/**
	 * Loads an image from the specified path to a {@link Sprite}. The extension
	 * is automatically appended.
	 * 
	 * @param name
	 *            The name of the image to load
	 */
	public static Sprite loadTIFF(String name) {
		return load(name, "tif", "tiff");
	}

	/**
	 * Loads an image from the specified path to a {@link Sprite}. The extension
	 * is automatically appended.
	 * 
	 * @param name
	 *            The name of the image to load
	 */
	public static Sprite loadGIF(String name) {
		return load(name, "gif");
	}

	/**
	 * Loads an image from the specified path to a {@link Sprite}. The extension
	 * is automatically appended.
	 * 
	 * @param name
	 *            The name of the image to load
	 */
	public static Sprite loadJPEG(String name) {
		return load(name, "jpeg", "jpg", "jif", "jfif");
	}

	/**
	 * Loads an image from the specified path to a {@link Sprite}. The extension
	 * is automatically appended.
	 * 
	 * @param name
	 *            The name of the image to load
	 */
	public static Sprite loadBMP(String name) {
		return load(name, "bmp");
	}

	/**
	 * Loads an animated GIF from the specified path, and extracts its frames
	 * into an array of {@link Sprite}s.
	 * 
	 * @param name
	 *            The name of the image to load
	 */
	public static Sprite[] loadFramesFromGIF(String name) {
		Sprite[] frames = null;

		try {
			ImageReader reader = ImageIO.getImageReadersByFormatName("gif").next();

			InputStream input = SpriteIO.class.getResourceAsStream(path + name + ".gif");

			ImageInputStream stream = ImageIO.createImageInputStream(input);

			reader.setInput(stream);

			frames = new Sprite[reader.getNumImages(true)];

			for (int i = 0; i < frames.length; i++)
				frames[i] = new Sprite(reader.read(i));
		}
		catch (IOException ex) {
		}

		return frames;
	}

	private static Sprite load(String name, String... extensions) {
		BufferedImage image = null;

		for (String extension : extensions) {
			try {
				image = ImageIO.read(SpriteIO.class.getResource(path + name + "." + extension));
			}
			catch (Exception e) {
				try {
					image = ImageIO.read(SpriteIO.class.getResource(name + "." + extension));
				}
				catch (Exception e2) {
					e2.printStackTrace();
				}
			}

			if (image == null)
				try {
					image = ImageIO.read(SpriteIO.class.getResource(name + "." + extension));
				}
				catch (Exception e2) {
					e2.printStackTrace();
				}
			else
				break;
		}

		return new Sprite(image);
	}
}