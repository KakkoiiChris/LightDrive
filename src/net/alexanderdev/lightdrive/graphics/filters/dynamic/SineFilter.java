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
package net.alexanderdev.lightdrive.graphics.filters.dynamic;

import net.alexanderdev.lightdrive.graphics.filters.FilterS;
import net.alexanderdev.lightdrive.util.ArraysS;
import net.alexanderdev.lightdrive.util.math.MathS;

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