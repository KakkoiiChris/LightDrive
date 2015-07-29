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

/**
 * A dynamic filter which applies a psychodelic effect to an image.
 * 
 * @author Christian Bryce Alexander
 * @since Jul 20, 2015 | 10:35:12 PM
 */
public class AcidFilter implements ImageSFilter {
	private long n = 0;

	private int scanSize;

	public AcidFilter(int scanSize) {
		this.scanSize = scanSize;
	}

	@Override
	public void apply(int[] pixels) {
		for (int i = 0; i < pixels.length; i++)
			pixels[i] += pixels[(int) ((i + n) % pixels.length)] + i;

		n += scanSize - 1;
	}
}