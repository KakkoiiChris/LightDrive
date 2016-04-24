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

import net.alexanderdev.lightdrive.graphics.ColorS;
import net.alexanderdev.lightdrive.util.Pixel;

/**
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 3:59:04 AM
 */
public class AdjustFilter implements Filter {
	protected float r, g, b;

	/**
	 * An {@code AdjustFilter} with its percentages based off of a color.
	 */
	public AdjustFilter(ColorS color) {
		this.r = color.getFloatRed();
		this.g = color.getFloatGreen();
		this.b = color.getFloatBlue();
	}

	/**
	 * An {@code AdjustFilter} with the specified percentages.
	 */
	public AdjustFilter(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public void setValues(int color) {
		r = Pixel.getFloatRed(color);
		g = Pixel.getFloatGreen(color);
		b = Pixel.getFloatBlue(color);
	}

	@Override
	public void apply(int width, int height, int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			float[] vals = Pixel.splitFloatARGB(pixels[i]);

			vals[1] *= r;
			vals[2] *= g;
			vals[3] *= b;

			pixels[i] = Pixel.mergeARGB(vals);
		}
	}
}