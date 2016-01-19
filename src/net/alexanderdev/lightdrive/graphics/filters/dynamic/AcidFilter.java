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