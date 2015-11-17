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
import static net.alexanderdev.lightdrive.util.ArraysS.*;
import static net.alexanderdev.lightdrive.util.math.MathS.*;

/**
 * @author Christian Bryce Alexander
 * @since Jul 20, 2015 | 11:25:13 PM
 */
public class GrayscaleFilter implements ImageSFilter {
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
			int[] argb = splitARGB(pixels[i]);

			int a = argb[0];
			int r = argb[1];
			int g = argb[2];
			int b = argb[3];

			int value = 0;

			switch (mode) {
				case AVERAGE:
					value = (int) average(r, g, b);
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
					value = (int) average(min(r, g, b), max(r, g, b));
					break;
				case LUMINOSITY:
					value = (int) clamp((r * 0.2126) + (g * 0.7152) + (b * 0.0722), 0, 255);
					break;
				case MAX_DECOMP:
					value = max(r, g, b);
					break;
				case MID_DECOMP:
					value = sort(r, g, b)[1];
					break;
				case MIN_DECOMP:
					value = min(r, g, b);
					break;
			}

			pixels[i] = mergeARGB(a, value, value, value);
		}
	}
}