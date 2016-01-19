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
import net.alexanderdev.quixel.util.ArraysS;
import net.alexanderdev.quixel.util.math.MathS;

/**
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 5:26:09 AM
 */
public class GrayscaleFilter implements FilterS {
	public static enum Mode {
		AVERAGE,
		CHANNEL_RED,
		CHANNEL_GREEN,
		CHANNEL_BLUE,
		LIGHTNESS,
		LUMINOSITY,
		MAX_DECOMP,
		MID_DECOMP,
		MIN_DECOMP
	}

	private Mode mode;

	public GrayscaleFilter(Mode mode) {
		this.mode = mode;
	}

	@Override
	public void apply(int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			int[] argb = Pixel.splitIntARGB(pixels[i]);

			int r = argb[1];
			int g = argb[2];
			int b = argb[3];

			int value = 0;

			switch (mode) {
				case AVERAGE:
					value = (int) MathS.average(r, g, b);
					break;
				case CHANNEL_RED:
					value = r;
					break;
				case CHANNEL_GREEN:
					value = g;
					break;
				case CHANNEL_BLUE:
					value = b;
					break;
				case LIGHTNESS:
					value = (int) MathS.average(MathS.min(r, g, b), MathS.max(r, g, b));
					break;
				case LUMINOSITY:
					value = (int) MathS.clamp((r * 0.2126) + (g * 0.7152) + (b * 0.0722), 0, 255);
					break;
				case MAX_DECOMP:
					value = MathS.max(r, g, b);
					break;
				case MID_DECOMP:
					value = ArraysS.sort(r, g, b)[1];
					break;
				case MIN_DECOMP:
					value = MathS.min(r, g, b);
					break;
			}

			pixels[i] = Pixel.mergeARGB(argb[0], value, value, value);
		}
	}
}