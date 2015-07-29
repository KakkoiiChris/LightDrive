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
 * @author Christian Bryce Alexander
 * @since Jul 27, 2015 | 10:27:21 PM
 */
public class PosterizeFilter implements ImageSFilter {
	private int levels;

	public PosterizeFilter(int levels) {
		this.levels = clamp(levels, 1, 255);
	}

	@Override
	public void apply(int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			int[] argb = splitARGB(pixels[i]);
			
			int a = argb[0];
			int r = argb[1];
			int g = argb[2];
			int b = argb[3];

			r /= levels;
			r *= levels;
			
			g /= levels;
			g *= levels;
			
			b /= levels;
			b *= levels;

			pixels[i] = mergeARGB(a, r, g, b);
		}
	}
}