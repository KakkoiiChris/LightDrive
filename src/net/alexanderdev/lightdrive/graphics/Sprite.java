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
package net.alexanderdev.lightdrive.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import net.alexanderdev.lightdrive.graphics.filter.Filter;
import net.alexanderdev.lightdrive.util.Pixel;

/**
 * A wrapper class for {@link BufferedImage} that contains multiple methods for
 * advanced color manipulation.
 * 
 * @author Christian Bryce Alexander
 * @since Apr 15, 2015, 12:22:40 AM
 */
public class Sprite extends BufferedImage implements Cloneable {
	private int[] pixels;

	/**
	 * Creates a new {@link Sprite} from the {@link BufferedImage}.
	 *
	 * @param image
	 *            The image to copy
	 */
	public Sprite(BufferedImage image) {
		super(image.getWidth(), image.getHeight(), TYPE_INT_ARGB);

		Graphics g = getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();

		pixels = ((DataBufferInt) this.getRaster().getDataBuffer()).getData();
	}

	/**
	 * Creates a blank {@link Sprite} of the specified size.
	 * 
	 * @param width
	 *            The width of the image to create
	 * @param height
	 *            The height of the image to create
	 */
	public Sprite(int width, int height) {
		super(width, height, TYPE_INT_ARGB);

		pixels = ((DataBufferInt) this.getRaster().getDataBuffer()).getData();
	}

	/**
	 * Sets all the pixels in the {@link Sprite} to the specified color.
	 * 
	 * @param color
	 *            The color to set
	 */
	public void clear(int color) {
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = color;
	}

	/**
	 * Sets all the pixels in the {@link Sprite} to 0x00000000.
	 */
	public void clear() {
		clear(0);
	}

	/**
	 * @param x
	 *            The x coordinate to check
	 * @param y
	 *            The y coordinate to check
	 * @return The pixel value at the specified coordinates
	 */
	public int getPixel(int x, int y) {
		return pixels[x + y * getWidth()];
	}

	/**
	 * Sets the pixel in this image at the given x and y coordinates to the
	 * given color
	 * 
	 * @param x
	 *            The x coordinate to set
	 * @param y
	 *            The y coordinate to set
	 * @param color
	 *            Integer rgb value to set the pixel to
	 */
	public void setPixel(int x, int y, int color) {
		pixels[x + y * getWidth()] = color;
	}

	/**
	 * Sets the pixel in this image at the given index of this {@link Sprite}'s
	 * pixel data buffer to the given color
	 * 
	 * @param i
	 *            The index of the pixel data buffer to set
	 * @param color
	 *            Integer rgb value to set the pixel to
	 */
	public void setPixel(int i, int color) {
		pixels[i] = color;
	}

	/**
	 * Apply a filter to this {@link Sprite}'s pixels.
	 *
	 * @param filters
	 *            A varargs list of filters to apply
	 */
	public void filter(Filter... filters) {
		for (Filter filter : filters)
			filter.apply(getWidth(), getHeight(), pixels);
	}

	/**
	 * @param filters
	 *            A varargs list of filters to apply
	 * @return A filtered copy of this {@link Sprite}
	 */
	public Sprite filtered(Filter... filters) {
		Sprite copy = clone();

		copy.filter(filters);

		return copy;
	}

	/**
	 * Blends another {@link Sprite} with this {@link Sprite}.
	 * 
	 * @param sprite
	 *            The {@link Sprite} to blend
	 * @param mode
	 *            The {@link BlendMode} to use
	 */
	public void blend(Sprite sprite, BlendMode mode) {
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				pixels[x + y * getWidth()] = Pixel.blendRGB(mode, pixels[x + y * getWidth()],
				    sprite.pixels[(x % sprite.getWidth()) + (y % sprite.getHeight()) * getWidth()]);
			}
		}
	}

	/**
	 * @param sprite
	 *            The {@link Sprite} to blend
	 * @param mode
	 *            The {@link BlendMode} to use
	 * @return A blended copy of this {@link Sprite}
	 */
	public Sprite blended(Sprite sprite, BlendMode mode) {
		Sprite copy = clone();

		copy.blend(sprite, mode);

		return copy;
	}

	@Override
	public Sprite getSubimage(int x, int y, int width, int height) {
		return new Sprite(super.getSubimage(x, y, width, height));
	}

	@Override
	public Sprite clone() {
		return new Sprite(this);
	}
}