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

/**
 * A filter which inverts the RGB values of each pixel.
 * 
 * @author Christian Bryce Alexander
 * @since Jul 20, 2015 | 10:45:32 PM
 */
public class InvertFilter implements ImageSFilter {
	@Override
	public void apply(int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			int[] argb = splitARGB(pixels[i]);

			int a = argb[0];
			int r = argb[1];
			int g = argb[2];
			int b = argb[3];

			pixels[i] = mergeARGB(a, 255 - r, 255 - g, 255 - b);
		}
	}
}