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
package net.alexanderdev.lightdrive.graphics.filters;

import net.alexanderdev.lightdrive.util.Pixel;
import net.alexanderdev.lightdrive.util.math.MathS;

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