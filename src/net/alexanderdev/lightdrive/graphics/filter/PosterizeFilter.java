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
 * A {@link Filter} which applies a posterized effect to the {@link Sprite}.
 * 
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 5:49:43 AM
 */
public class PosterizeFilter implements Filter {
	private int levels;

	/**
	 * Creates a new {@link PosterizeFilter}.
	 *
	 * @param levels
	 *            The amount of values per channel
	 */
	public PosterizeFilter(int levels) {
		this.levels = MathX.clamp(levels - 1, 1, 255);
	}

	/**
	 * Sets the posterization levels.
	 *
	 * @param levels
	 *            The amount of values per channel
	 */
	public void setLevels(int levels) {
		this.levels = MathX.clamp(levels - 1, 1, 255);
	}

	@Override
	public void apply(int width, int height, int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			int[] argb = Pixel.splitIntARGB(pixels[i]);

			argb[1] /= 255 / levels;
			argb[1] *= 255 / levels;

			argb[2] /= 255 / levels;
			argb[2] *= 255 / levels;

			argb[3] /= 255 / levels;
			argb[3] *= 255 / levels;

			pixels[i] = Pixel.mergeARGB(argb);
		}
	}
}