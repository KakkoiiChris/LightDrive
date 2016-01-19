/*************************************************************
 *     _______  __   __  ___   __   __  _______  ___         *
 *    |       ||  | |  ||   | |  |_|  ||       ||   |        *
 *    |   _   ||  | |  ||   | |       ||    ___||   |        *
 *    |  | |  ||  |_|  ||   | |       ||   |___ |   |        *
 *    |  |_|  ||       ||   |  |     | |    ___||   |___     *
 *    |      | |       ||   | |   _   ||   |___ |       |    *
 *    |____||_||_______||___| |__| |__||_______||_______|    *
 *     _________________________________________________     *
 *    |_________________JAVA_GAME_LIBRARY_______________|    *
 *                                                           *
 * Copyright © 2015, Christian Bryce Alexander               *
 *************************************************************/
package net.alexanderdev.lightdrive.graphics.filters;

import net.alexanderdev.quixel.graphics.ColorS;
import net.alexanderdev.quixel.graphics.FilterS;
import net.alexanderdev.quixel.graphics.Pixel;

/**
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 3:59:04 AM
 */
public class AdjustFilter implements FilterS {
	protected float r, g, b;

	public AdjustFilter(ColorS color) {
		this.r = color.red   / 255f;
		this.g = color.green / 255f;
		this.b = color.blue  / 255f;
	}

	public AdjustFilter(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	@Override
	public void apply(int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			float[] vals = Pixel.splitFloatARGB(pixels[i]);

			vals[1] *= r;
			vals[2] *= g;
			vals[3] *= b;

			pixels[i] = Pixel.mergeARGB(vals);
		}
	}
}