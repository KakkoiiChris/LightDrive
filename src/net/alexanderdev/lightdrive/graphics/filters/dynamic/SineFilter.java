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
package net.alexanderdev.lightdrive.graphics.filters.dynamic;

import net.alexanderdev.quixel.graphics.FilterS;
import net.alexanderdev.quixel.util.ArraysS;
import net.alexanderdev.quixel.util.math.MathS;

/**
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 6:21:23 AM
 */
public class SineFilter implements FilterS {
	private int t = 0;

	private int scanSize;

	private double period;
	private double magnitude;

	public SineFilter(int scanSize, double period, double magnitude) {
		this.scanSize = scanSize;
		this.period = period;
		this.magnitude = magnitude;
	}

	@Override
	public void apply(int[] pixels) {
		int[] original = ArraysS.copy(pixels);

		for (int y = 0; y < pixels.length / scanSize; y++) {
			for (int x = 0; x < scanSize; x++) {
				int s = (int) (Math.sin((y + t) / period) * magnitude);

				pixels[x + y * scanSize] = original[(MathS.wrap(x + s, 0, scanSize - 1) + y * scanSize)
					% pixels.length];
			}
		}

		t++;
	}
}