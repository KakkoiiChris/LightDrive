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

import static net.alexanderdev.quixel.graphics.Pixel.*;
import static net.alexanderdev.quixel.util.math.MathS.*;

import net.alexanderdev.quixel.graphics.FilterS;

/**
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 6:13:01 AM
 */
public class SepiaFilter implements FilterS {
	@Override
	public void apply(int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			float[] argb = splitFloatARGB(pixels[i]);

			float or = (argb[1] * 0.393f) + (argb[2] * 0.769f) + (argb[3] * 0.189f);
			float og = (argb[1] * 0.349f) + (argb[2] * 0.686f) + (argb[3] * 0.168f);
			float ob = (argb[1] * 0.272f) + (argb[2] * 0.534f) + (argb[3] * 0.131f);

			pixels[i] = mergeARGB(argb[0], clamp(or, 0f, 1f), clamp(og, 0f, 1f), clamp(ob, 0f, 1f));
		}
	}
}