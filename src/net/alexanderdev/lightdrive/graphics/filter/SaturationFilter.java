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

import net.alexanderdev.lightdrive.util.Pixel;
import net.alexanderdev.lightdrive.util.math.MathX;

/**
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 5:56:57 AM
 */
public class SaturationFilter implements Filter {
	private float factor;

	public SaturationFilter(float factor) {
		this.factor = MathX.clamp(factor, -1f, 1f);
	}

	@Override
	public void apply(int width, int height, int[] pixels) {
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
				int avg = (int) MathX.average((float) argb[1], (float) argb[2], (float) argb[3]);

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