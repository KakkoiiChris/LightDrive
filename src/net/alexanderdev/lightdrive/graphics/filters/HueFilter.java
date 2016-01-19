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
package net.alexanderdev.lightdrive.graphics.filters;

import java.awt.Color;

import net.alexanderdev.lightdrive.util.Pixel;

/**
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 5:44:48 AM
 */
public class HueFilter implements FilterS {
	private float hue = 0f;

	public HueFilter(float hue) {
		this.hue = hue;
	}

	public void setHue(float hue) {
		this.hue = hue;
	}

	@Override
	public void apply(int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			int[] argb = Pixel.splitIntARGB(pixels[i]);

			float[] values = Color.RGBtoHSB(argb[1], argb[2], argb[3], null);

			pixels[i] = Color.HSBtoRGB(values[0] + hue, values[1], values[2]);
		}
	}
}