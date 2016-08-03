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
package net.alexanderdev.lightdrive.graphics.filter;

import net.alexanderdev.lightdrive.graphics.Sprite;
import net.alexanderdev.lightdrive.util.Pixel;

/**
 * A {@link Filter} which adjusts the percentage of all three RGB channels in
 * the {@link Sprite}.
 * 
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 3:59:04 AM
 */
public class AdjustFilter implements Filter {
	protected float r, g, b;

	/**
	 * Creates a new {@link AdjustFilter} with its percentages based off of an
	 * integer color value.
	 * 
	 * @param color
	 *            The color to imitate
	 */
	public AdjustFilter(int color) {
		this.r = Pixel.getFloatRed(color);
		this.g = Pixel.getFloatGreen(color);
		this.b = Pixel.getFloatBlue(color);
	}

	/**
	 * Creates a new {@link AdjustFilter} with the specified percentages.
	 * 
	 * @param r
	 *            The percentage of red
	 * @param g
	 *            The percentage of green
	 * @param b
	 *            The percentage of blue
	 */
	public AdjustFilter(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	/**
	 * Sets the RGB channel percentages based on the specified integer color
	 * value.
	 * 
	 * @param color
	 *            The color to imitate
	 */
	public void setPercentages(int color) {
		r = Pixel.getFloatRed(color);
		g = Pixel.getFloatGreen(color);
		b = Pixel.getFloatBlue(color);
	}

	/**
	 * Sets the RGB channel percentages with the specified percentages.
	 * 
	 * @param r
	 *            The percentage of red
	 * @param g
	 *            The percentage of green
	 * @param b
	 *            The percentage of blue
	 */
	public void setPercentages(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	@Override
	public void apply(int width, int height, final int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			float[] vals = Pixel.splitFloatARGB(pixels[i]);

			vals[1] *= r;
			vals[2] *= g;
			vals[3] *= b;

			pixels[i] = Pixel.mergeARGB(vals);
		}
	}
}