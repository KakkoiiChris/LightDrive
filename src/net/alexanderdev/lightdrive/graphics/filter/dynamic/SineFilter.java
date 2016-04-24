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
package net.alexanderdev.lightdrive.graphics.filter.dynamic;

import net.alexanderdev.lightdrive.graphics.Sprite;
import net.alexanderdev.lightdrive.graphics.filter.Filter;
import net.alexanderdev.lightdrive.util.ArraysS;
import net.alexanderdev.lightdrive.util.math.MathS;

/**
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 6:21:23 AM
 */
public class SineFilter implements Filter {
	private int t = 0;

	private double period;
	private double magnitude;

	/**
	 * A {@code SineFilter} with the specified parameters.
	 *
	 * @param scanSize
	 *            The width of the {@link Sprite} to be applied to (preferably)
	 * @param period
	 *            The width of the waves
	 * @param magnitude
	 *            The height of the waves
	 */
	public SineFilter(double period, double magnitude) {
		this.period = period;
		this.magnitude = magnitude;
	}

	@Override
	public void apply(int width, int height, int[] pixels) {
		int[] original = ArraysS.copy(pixels);

		for (int y = 0; y < pixels.length / width; y++) {
			for (int x = 0; x < width; x++) {
				int s = (int) (Math.sin((y + t) / period) * magnitude);

				pixels[x + y * width] = original[(MathS.wrap(x + s, 0, width - 1) + y * width) % pixels.length];
			}
		}

		t++;
	}
}