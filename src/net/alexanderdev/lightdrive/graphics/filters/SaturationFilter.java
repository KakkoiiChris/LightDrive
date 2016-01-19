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
 * @since Dec 14, 2015, 5:56:57 AM
 */
public class SaturationFilter implements FilterS {
	private float factor;

	public SaturationFilter(float factor) {
		this.factor = MathS.clamp(factor, -1f, 1f);
	}

	@Override
	public void apply(int[] pixels) {
		if (factor == 0f)
			return;

		for (int i = 0; i < pixels.length; i++) {
			int[] argb = Pixel.splitIntARGB(pixels[i]);

			if (factor > 0) {
				int rLim = argb[1] >= 128 ? 255 : 0;
				int gLim = argb[2] >= 128 ? 255 : 0;
				int bLim = argb[3] >= 128 ? 255 : 0;

				int rDiff = rLim - argb[1];
				int gDiff = gLim - argb[2];
				int bDiff = bLim - argb[3];

				argb[1] += rDiff * factor;
				argb[2] += gDiff * factor;
				argb[3] += bDiff * factor;
			}
			else {
				int avg = (int) MathS.average((float) argb[1], (float) argb[2], (float) argb[3]);

				int rDiff = avg - argb[1];
				int gDiff = avg - argb[2];
				int bDiff = avg - argb[3];

				argb[1] += rDiff * -factor;
				argb[2] += gDiff * -factor;
				argb[3] += bDiff * -factor;
			}

			pixels[i] = Pixel.mergeARGB(argb);
		}
	}
}