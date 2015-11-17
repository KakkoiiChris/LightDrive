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
import static net.alexanderdev.lightdrive.util.math.MathS.*;

/**
 * A filter which applies a sepia tone to the image.
 * 
 * @author Christian Bryce Alexander
 * @since Jul 20, 2015 | 11:37:19 PM
 */
public class SepiaFilter implements ImageSFilter {
	@Override
	public void apply(int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			int[] argb = splitARGB(pixels[i]);
			int a = argb[0];
			int r = argb[1];
			int g = argb[2];
			int b = argb[3];

			int or = (int) ((r * 0.393) + (g * 0.769) + (b * 0.189));
			int og = (int) ((r * 0.349) + (g * 0.686) + (b * 0.168));
			int ob = (int) ((r * 0.272) + (g * 0.534) + (b * 0.131));

			pixels[i] = mergeARGB(a, clamp(or, 0, 255), clamp(og, 0, 255), clamp(ob, 0, 255));
		}
	}
}