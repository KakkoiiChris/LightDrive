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
 * A filter which modifies the percentage of RGB levels in the image, with
 * {@code 0f} being 0%, and {@code 1f} being 100%.
 * 
 * @author Christian Bryce Alexander
 * @since Jul 20, 2015 | 9:53:52 PM
 */
public class AdjustFilter implements ImageSFilter {
	private float rFactor, gFactor, bFactor;

	public AdjustFilter(float rFactor, float gFactor, float bFactor) {
		this.rFactor = clamp(rFactor, 0f, 1f);
		this.gFactor = clamp(gFactor, 0f, 1f);
		this.bFactor = clamp(bFactor, 0f, 1f);
	}

	@Override
	public void apply(int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			int[] argb = splitARGB(pixels[i]);

			int a = argb[0];
			int r = argb[1];
			int g = argb[2];
			int b = argb[3];

			r *= rFactor;
			g *= gFactor;
			b *= bFactor;

			pixels[i] = mergeARGB(a, r, g, b);
		}
	}
}