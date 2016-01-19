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

import net.alexanderdev.lightdrive.util.Pixel;
import net.alexanderdev.lightdrive.util.math.MathS;

/**
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 5:53:27 AM
 */
public class ColorMapFilter implements FilterS {
	private int[] values;

	public ColorMapFilter(int... values) {
		this.values = values;
	}

	public void setValues(int... values) {
		this.values = values;
	}

	@Override
	public void apply(int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			int[] argb = Pixel.splitIntARGB(pixels[i]);

			if (argb[0] != 0xff)
				continue;

			int v = (int) ((MathS.average(argb[1], argb[2], argb[3]) + 1) / (256 / (values.length - 1)));

			pixels[i] = values[v];
		}
	}
}