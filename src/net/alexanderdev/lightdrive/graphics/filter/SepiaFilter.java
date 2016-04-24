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
package net.alexanderdev.lightdrive.graphics.filter;

import net.alexanderdev.lightdrive.util.Pixel;
import net.alexanderdev.lightdrive.util.math.MathS;

/**
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 6:13:01 AM
 */
public class SepiaFilter implements Filter {
	@Override
	public void apply(int width, int height, int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			float[] argb = Pixel.splitFloatARGB(pixels[i]);

			float or = (argb[1] * 0.393f) + (argb[2] * 0.769f) + (argb[3] * 0.189f);
			float og = (argb[1] * 0.349f) + (argb[2] * 0.686f) + (argb[3] * 0.168f);
			float ob = (argb[1] * 0.272f) + (argb[2] * 0.534f) + (argb[3] * 0.131f);

			pixels[i] = Pixel.mergeARGB(argb[0], MathS.clamp(or, 0f, 1f), MathS.clamp(og, 0f, 1f),
				MathS.clamp(ob, 0f, 1f));
		}
	}
}