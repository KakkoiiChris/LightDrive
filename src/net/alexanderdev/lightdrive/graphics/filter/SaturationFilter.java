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
 * A {@link Filter} which applies an desaturation or oversaturation effect to
 * the {@link Sprite}.
 * 
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 5:56:57 AM
 */
public class SaturationFilter implements Filter {
	private float saturation;

	/**
	 * Creates a new {@link SaturationFilter}.
	 *
	 * @param saturation
	 *            The amount of saturation ({@code -1f} is fully desaturated,
	 *            {@code 1f} is fully oversaturated)
	 */
	public SaturationFilter(float saturation) {
		this.saturation = MathX.clamp(saturation, -1f, 1f);
	}

	/**
	 * Sets the saturation factor.
	 *
	 * @param saturation
	 *            The amount of saturation ({@code -1f} is fully desaturated,
	 *            {@code 1f} is fully oversaturated)
	 */
	public void setFactor(float saturation) {
		this.saturation = MathX.clamp(saturation, -1f, 1f);
	}

	@Override
	public void apply(int width, int height, final int[] pixels) {
		if (saturation == 0f)
			return;

		for (int i = 0; i < pixels.length; i++) {
			float[] argb = Pixel.splitFloatARGB(pixels[i]);

			if (saturation > 0) {
				float rLim = argb[1] >= 0.5f ? 1f : 0f;
				float gLim = argb[2] >= 0.5f ? 1f : 0f;
				float bLim = argb[3] >= 0.5f ? 1f : 0f;

				float rDiff = rLim - argb[1];
				float gDiff = gLim - argb[2];
				float bDiff = bLim - argb[3];

				argb[1] += rDiff * saturation;
				argb[2] += gDiff * saturation;
				argb[3] += bDiff * saturation;
			}
			else {
				float avg = (float) MathX.average(argb[1], argb[2], argb[3]);

				float rDiff = avg - argb[1];
				float gDiff = avg - argb[2];
				float bDiff = avg - argb[3];

				argb[1] += rDiff * -saturation;
				argb[2] += gDiff * -saturation;
				argb[3] += bDiff * -saturation;
			}

			pixels[i] = Pixel.mergeARGB(argb);
		}
	}
}