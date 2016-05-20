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

import java.awt.Color;

import net.alexanderdev.lightdrive.graphics.filter.AdjustFilter;
import net.alexanderdev.lightdrive.graphics.filter.Filter;
import net.alexanderdev.lightdrive.util.Pixel;

/**
 * A dynamic {@link AdjustFilter} whose values cycle through the rainbow.
 * 
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 6:25:07 AM
 */
public class SpectrumFilter extends AdjustFilter {
	private float dh;
	private float hue;

	/**
	 * A {@link SpectrumFilter} width the specified hue delta.
	 * 
	 * @param dh
	 *            The change in hue between {@link Filter} applications
	 */
	public SpectrumFilter(float dh) {
		super(1f, 0f, 0f);

		this.dh = dh;

		hue = 0f;
	}

	@Override
	public void apply(int width, int height, int[] pixels) {
		float[] argb = Pixel.splitFloatARGB(Color.HSBtoRGB(hue, 1f, 1f));

		this.r = argb[1];
		this.g = argb[2];
		this.b = argb[3];

		super.apply(width, height, pixels);

		hue += dh;
	}
}