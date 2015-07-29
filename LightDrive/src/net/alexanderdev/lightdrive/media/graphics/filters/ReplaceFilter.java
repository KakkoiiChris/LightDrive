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

import static net.alexanderdev.lightdrive.util.math.MathS.average;

import net.alexanderdev.lightdrive.media.graphics.ColorS;

/**
 * A filter which replaces evenly distributed shades of gray with the specified
 * colors.
 * 
 * @author Christian Bryce Alexander
 * @since Jul 25, 2015 | 9:16:20 PM
 */
public class ReplaceFilter implements ImageSFilter {
	private int[] values;

	public ReplaceFilter(int... values) {
		this.values = values;
	}

	@Override
	public void apply(int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			int[] argb = ColorS.splitARGB(pixels[i]);

			int v = (int) (average(argb[1], argb[2], argb[3]) / (256 / (values.length - 1)));

			pixels[i] = values[v];
		}
	}
}