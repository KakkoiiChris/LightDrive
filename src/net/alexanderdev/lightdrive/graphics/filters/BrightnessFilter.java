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
 * @since Dec 14, 2015, 4:18:47 AM
 */
public class BrightnessFilter implements FilterS {
	private float factor;

	public BrightnessFilter(float factor) {
		this.factor = MathS.clamp(factor, -1f, 1f);
	}

	@Override
	public void apply(int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			int[] argb = Pixel.splitIntARGB(pixels[i]);

			if (factor > 0) {
				argb[1] += (255 - argb[1]) * factor;
				argb[2] += (255 - argb[2]) * factor;
				argb[3] += (255 - argb[3]) * factor;
			}
			else {
				argb[1] -= argb[1] * -factor;
				argb[2] -= argb[2] * -factor;
				argb[3] -= argb[3] * -factor;
			}

			pixels[i] = Pixel.mergeARGB(argb);
		}
	}
}