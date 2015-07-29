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

import static net.alexanderdev.lightdrive.media.graphics.ColorS.mergeARGB;
import static net.alexanderdev.lightdrive.media.graphics.ColorS.splitARGB;
import static net.alexanderdev.lightdrive.util.math.MathS.clamp;

/**
 * A filter which alters the visual brightness of the image, with {@code -1f}
 * being fully dark (black), and {@code 1f} being fully bright (white).
 * 
 * @author Christian Bryce Alexander
 * @since Jul 20, 2015 | 9:30:16 PM
 */
public class BrightnessFilter implements ImageSFilter {
	private float factor;

	public BrightnessFilter(float factor) {
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
				r += (255 - r) * factor;
				g += (255 - g) * factor;
				b += (255 - b) * factor;
			} else {
				r -= r * -factor;
				g -= g * -factor;
				b -= b * -factor;
			}

			pixels[i] = mergeARGB(a, r, g, b);
		}
	}
}