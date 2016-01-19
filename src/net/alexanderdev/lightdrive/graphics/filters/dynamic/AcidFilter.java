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

/**
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 4:13:30 AM
 */
public class AcidFilter implements FilterS {
	private long n;

	private int scanSize;

	public AcidFilter(int scanSize) {
		this.scanSize = scanSize;
		
		n = 0;
	}

	@Override
	public void apply(int[] pixels) {
		for (int i = 0; i < pixels.length; i++)
			pixels[i] += pixels[(int) ((i + n) % pixels.length)] + i;

		n += scanSize - 1;
	}
}