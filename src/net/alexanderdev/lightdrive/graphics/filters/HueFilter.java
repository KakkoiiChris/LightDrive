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
package net.alexanderdev.lightdrive.graphics.filters;

import java.awt.Color;

import net.alexanderdev.quixel.graphics.FilterS;
import net.alexanderdev.quixel.graphics.Pixel;

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