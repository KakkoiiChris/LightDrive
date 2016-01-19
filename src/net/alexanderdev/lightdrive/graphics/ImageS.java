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

import net.alexanderdev.lightdrive.graphics.filters.FilterS;

/**
 * A wrapper class for {@code BufferedImage} that contains multiple methods for
 * advanced color manipulation
 * 
 * @author Christian Bryce Alexander
 * @since Apr 15, 2015 | 12:22:40 AM
 */
public class ImageS extends BufferedImage implements Cloneable {
	private int[] pixels;

	/**
	 * Creates a new {@code ImageS} from the {@code BufferedImage}.
	 *
	 * @param image
	 *            The image to copy
	 */
	public ImageS(BufferedImage image) {
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
	public ImageS(int width, int height) {
		super(width, height, TYPE_INT_ARGB);

		pixels = ((DataBufferInt) this.getRaster().getDataBuffer()).getData();
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
	 * {@inheritDoc}
	 */
	@Override
	public final ImageS getSubimage(int x, int y, int width, int height) {
		return new ImageS(super.getSubimage(x, y, width, height));
	}

	@Override
	public ImageS clone() {
		return null;
	}

	public final void filter(FilterS... filters) {
		for (FilterS filter : filters)
			filter.apply(pixels);
	}

	public final ImageS filtered(FilterS... filters) {
		ImageS copy = new ImageS(this);

		copy.filter(filters);

		return copy;
	}
}