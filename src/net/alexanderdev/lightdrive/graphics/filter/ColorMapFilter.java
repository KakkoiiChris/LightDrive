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

import net.alexanderdev.lightdrive.graphics.Sprite;
import net.alexanderdev.lightdrive.util.Pixel;
import net.alexanderdev.lightdrive.util.math.MathX;

/**
 * A {@link Filter} which replaces sequential shades of gray on the
 * {@link Sprite} with the specified colors.
 * 
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 5:53:27 AM
 */
public class ColorMapFilter implements Filter {
	private int[] values;

	/**
	 * A {@link ColorMapFilter} with the specified values.
	 * 
	 * @param values
	 *            The values to set
	 */
	public ColorMapFilter(int... values) {
		this.values = values;
	}

	/**
	 * Sets the values of this {@link ColorMapFilter}.
	 * 
	 * @param values
	 *            The values to set
	 */
	public void setValues(int... values) {
		this.values = values;
	}

	@Override
	public void apply(int width, int height, int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			int[] argb = Pixel.splitIntARGB(pixels[i]);

			if (argb[0] != 0xff)
				continue;

			int v = (int) ((MathX.average(argb[1], argb[2], argb[3]) + 1) / (256 / (values.length - 1)));

			pixels[i] = values[v];
		}
	}
}