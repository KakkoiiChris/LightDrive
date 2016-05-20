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
 * @since Apr 15, 2015 | 12:22:40 AM
 */
public class Sprite extends BufferedImage implements Cloneable {
	private int[] pixels;

	/**
	 * Creates a new {@code Sprite} from the {@link BufferedImage}.
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
	 * Creates a blank {@code ImageS} of the specified size.
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

	public void clear(int color) {
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = color;
	}

	public void clear() {
		clear(0);
	}

	public int getPixel(int x, int y) {
		return pixels[x + y * getWidth()];
	}

	/**
	 * Sets the pixel in this image at the given x and y coordinates to the
	 * given color
	 * 
	 * @param x
	 *            X coordinate to set
	 * @param y
	 *            Y coordinate to set
	 * @param color
	 *            Integer rgb value to set the pixel to
	 */
	public final void setPixel(int x, int y, int color) {
		pixels[x + y * getWidth()] = color;
	}

	/**
	 * Sets the pixel in this image at the given index of this {@code ImageS}'s
	 * pixel data buffer to the given color
	 * 
	 * @param i
	 *            The index of the pixel data buffer to set
	 * @param color
	 *            Integer rgb value to set the pixel to
	 */
	public final void setPixel(int i, int color) {
		pixels[i] = color;
	}

	/**
	 * Apply a filter to this {@code Sprite}'s pixels.
	 *
	 * @param filters
	 *            A varargs list of filters to apply
	 */
	public final void filter(Filter... filters) {
		for (Filter filter : filters)
			filter.apply(getWidth(), getHeight(), pixels);
	}

	/**
	 * @param filters
	 *            A varargs list of filters to apply
	 * @return A filtered copy of this {@code Sprite}
	 */
	public final Sprite filtered(Filter... filters) {
		Sprite copy = clone();

		copy.filter(filters);

		return copy;
	}

	public final void blend(Sprite sprite, BlendMode mode) {
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				pixels[x + y * getWidth()] = blendRGB(mode, pixels[x + y * getWidth()],
				    sprite.pixels[(x % sprite.getWidth()) + (y % sprite.getHeight()) * getWidth()]);
			}
		}
	}

	public final Sprite blended(Sprite sprite, BlendMode mode) {
		Sprite copy = clone();

		copy.blend(sprite, mode);

		return copy;
	}

	private int blendRGB(BlendMode mode, int colorA, int colorB) {
		float[] target = Pixel.splitFloatARGB(colorA);

		float[] blend = Pixel.splitFloatARGB(colorB);

		for (int j = 1; j < target.length; j++)
			target[j] = mode.getOperation().apply(target[j], blend[j]);

		return Pixel.mergeARGB(target);
	}

	@Override
	public final Sprite getSubimage(int x, int y, int width, int height) {
		return new Sprite(super.getSubimage(x, y, width, height));
	}

	@Override
	public Sprite clone() {
		return new Sprite(this);
	}
}