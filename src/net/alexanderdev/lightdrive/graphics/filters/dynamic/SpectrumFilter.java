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

import java.awt.Color;

import net.alexanderdev.quixel.graphics.Pixel;
import net.alexanderdev.quixel.graphics.filters.AdjustFilter;

/**
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 6:25:07 AM
 */
public class SpectrumFilter extends AdjustFilter {
	private float dh;
	private float hue;

	public SpectrumFilter(float dh) {
		super(1f, 1f, 1f);

		this.dh = dh;

		hue = 0f;
	}

	@Override
	public void apply(int[] pixels) {
		float[] argb = Pixel.splitFloatARGB(Color.HSBtoRGB(hue, 1f, 1f));

		this.r = argb[1];
		this.g = argb[2];
		this.b = argb[3];

		super.apply(pixels);

		hue += dh;
	}
}