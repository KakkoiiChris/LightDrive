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

import net.alexanderdev.lightdrive.graphics.GrayscaleMode;
import net.alexanderdev.lightdrive.util.ArraysS;
import net.alexanderdev.lightdrive.util.Pixel;
import net.alexanderdev.lightdrive.util.math.MathS;

/**
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 5:26:09 AM
 */
public class GrayscaleFilter implements FilterS {
	private GrayscaleMode mode;

	public GrayscaleFilter(GrayscaleMode mode) {
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