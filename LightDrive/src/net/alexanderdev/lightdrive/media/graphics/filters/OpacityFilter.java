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
 * A filter which modifies the alpha levels of the image, with {@code 0f} being
 * fully transparent, and {@code 1f} being fully opaque.
 * 
 * @author Christian Bryce Alexander
 * @since Jul 21, 2015 | 12:00:59 AM
 */
public class OpacityFilter implements ImageSFilter {
	private float factor;

	public OpacityFilter(float factor) {
		this.factor = clamp(factor, 0f, 1f);
	}

	@Override
	public void apply(int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			int[] argb = splitARGB(pixels[i]);
			
			int a = argb[0];
			int r = argb[1];
			int g = argb[2];
			int b = argb[3];

			a *= factor;

			pixels[i] = mergeARGB(a, r, g, b);
		}
	}
}