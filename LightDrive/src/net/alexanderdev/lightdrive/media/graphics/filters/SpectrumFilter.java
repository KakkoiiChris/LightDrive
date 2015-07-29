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

import static net.alexanderdev.lightdrive.media.graphics.ColorS.splitARGB;

import net.alexanderdev.lightdrive.media.graphics.ColorS;

/**
 * A dynamic filter which applies an {@code AdjustFilter} in the order of an RGB
 * spectrum.
 * 
 * @author Christian Bryce Alexander
 * @since Jul 24, 2015 | 2:16:45 PM
 */
public class SpectrumFilter implements ImageSFilter {
	private float dh;
	private float hue;

	public SpectrumFilter(float dh) {
		this.dh = dh;
		
		hue = 0f;
	}

	@Override
	public void apply(int[] pixels) {
		int color = ColorS.HSBtoRGB(hue, 1f, 1f);

		int[] values = splitARGB(color);

		float r = values[1] / 255f;
		float g = values[2] / 255f;
		float b = values[3] / 255f;

		new AdjustFilter(r, g, b).apply(pixels);

		hue += dh;
	}
}