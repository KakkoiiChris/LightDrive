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
 * A {@link Filter} which adjusts the percentage of the alpha channel in the
 * {@link Sprite}.
 * 
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 5:34:09 AM
 */
public class AlphaFilter implements Filter {
	private float alpha;

	/**
	 * Creates a new {@link AlphaFilter}.
	 * 
	 * @param alpha
	 *            The percentage of alpha to set
	 */
	public AlphaFilter(float alpha) {
		this.alpha = MathX.clamp(alpha, 0f, 1f);
	}

	/**
	 * Sets the alpha percentage.
	 * 
	 * @param alpha
	 *            The percentage of alpha to set
	 */
	public void setAlpha(float alpha) {
		this.alpha = MathX.clamp(alpha, 0f, 1f);
	}

	@Override
	public void apply(int width, int height, int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			float[] argb = Pixel.splitFloatARGB(pixels[i]);

			argb[0] *= alpha;

			pixels[i] = Pixel.mergeARGB(argb);
		}
	}
}