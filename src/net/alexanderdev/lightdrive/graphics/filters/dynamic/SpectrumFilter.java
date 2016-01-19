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

import java.awt.Color;

import net.alexanderdev.lightdrive.graphics.filters.AdjustFilter;
import net.alexanderdev.lightdrive.util.Pixel;

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