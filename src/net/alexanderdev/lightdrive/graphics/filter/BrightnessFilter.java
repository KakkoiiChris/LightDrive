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
package net.alexanderdev.lightdrive.graphics.filter;

import net.alexanderdev.lightdrive.graphics.Sprite;
import net.alexanderdev.lightdrive.util.Pixel;
import net.alexanderdev.lightdrive.util.math.MathX;

/**
 * A {@link Filter} which applies a brightness effect to the {@link Sprite}.
 * 
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 4:18:47 AM
 */
public class BrightnessFilter implements Filter {
	protected float brightness;

	/**
	 * Creates a new {@link BrightnessFilter}.
	 *
	 * @param brightness
	 *            The amount of brightness ({@code -1f} is full black,
	 *            {@code 1f} is full white)
	 */
	public BrightnessFilter(float brightness) {
		this.brightness = MathX.clamp(brightness, -1f, 1f);
	}

	/**
	 * Sets the brightness factor.
	 *
	 * @param brightness
	 *            The amount of brightness ({@code -1f} is full black,
	 *            {@code 1f} is full white)
	 */
	public void setBrightness(float brightness) {
		this.brightness = MathX.clamp(brightness, -1f, 1f);
	}

	@Override
	public void apply(int width, int height, int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			int[] argb = Pixel.splitIntARGB(pixels[i]);

			if (brightness > 0) {
				argb[1] += (255 - argb[1]) * brightness;
				argb[2] += (255 - argb[2]) * brightness;
				argb[3] += (255 - argb[3]) * brightness;
			}
			else {
				argb[1] -= argb[1] * -brightness;
				argb[2] -= argb[2] * -brightness;
				argb[3] -= argb[3] * -brightness;
			}

			pixels[i] = Pixel.mergeARGB(argb);
		}
	}
}