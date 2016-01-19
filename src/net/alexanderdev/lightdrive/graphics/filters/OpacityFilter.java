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

import net.alexanderdev.quixel.graphics.FilterS;
import net.alexanderdev.quixel.graphics.Pixel;
import net.alexanderdev.quixel.util.math.MathS;

/**
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 5:34:09 AM
 */
public class OpacityFilter implements FilterS {
	private float factor;

	public OpacityFilter(float factor) {
		this.factor = MathS.clamp(factor, 0f, 1f);
	}

	@Override
	public void apply(int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			float[] argb = Pixel.splitFloatARGB(pixels[i]);

			argb[0] *= factor;

			pixels[i] = Pixel.mergeARGB(argb);
		}
	}
}