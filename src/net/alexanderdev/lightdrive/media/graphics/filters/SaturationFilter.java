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
package net.alexanderdev.lightdrive.media.graphics.filters;

import static net.alexanderdev.lightdrive.media.graphics.ColorS.*;
import static net.alexanderdev.lightdrive.util.math.MathS.*;

/**
 * A filter that applies a saturation effect to the image, with {@code -1f}
 * being fully desaturated, and {@code 1f} being fully oversaturated.
 * 
 * @author Christian Bryce Alexander
 * @since Jul 20, 2015 | 10:18:27 PM
 */
public class SaturationFilter implements ImageSFilter {
	private float factor;

	public SaturationFilter(float factor) {
		this.factor = clamp(factor, -1f, 1f);
	}

	@Override
	public void apply(int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			int[] argb = splitARGB(pixels[i]);
			int a = argb[0];
			int r = argb[1];
			int g = argb[2];
			int b = argb[3];

			if (factor > 0) {
				int rLim = r >= 128 ? 255 : 0;
				int gLim = g >= 128 ? 255 : 0;
				int bLim = b >= 128 ? 255 : 0;

				int rDiff = rLim - r;
				int gDiff = gLim - g;
				int bDiff = bLim - b;

				r += rDiff * factor;
				g += gDiff * factor;
				b += bDiff * factor;
			}
			else {
				int avg = (int) average((float) r, (float) g, (float) b);

				int rDiff = avg - r;
				int gDiff = avg - g;
				int bDiff = avg - b;

				r += rDiff * -factor;
				g += gDiff * -factor;
				b += bDiff * -factor;
			}

			pixels[i] = mergeARGB(a, r, g, b);
		}
	}
}