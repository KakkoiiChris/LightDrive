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

import net.alexanderdev.lightdrive.graphics.GrayscaleMode;
import net.alexanderdev.lightdrive.graphics.Sprite;
import net.alexanderdev.lightdrive.util.Pixel;

/**
 * A {@link Filter} which applies a gray-scale effect to the {@link Sprite}.
 * 
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 5:26:09 AM
 */
public class GrayscaleFilter implements Filter {
	private GrayscaleMode mode;

	/**
	 * Creates a new {@link GrayscaleFilter}.
	 *
	 * @param mode
	 *            The gray-scale mode to use
	 */
	public GrayscaleFilter(GrayscaleMode mode) {
		this.mode = mode;
	}

	/**
	 * Sets the {@link GrayscaleMode}.
	 *
	 * @param mode
	 *            The gray-scale mode to use
	 */
	public void setMode(GrayscaleMode mode) {
		this.mode = mode;
	}

	@Override
	public void apply(int width, int height, final int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			int[] argb = Pixel.splitIntARGB(pixels[i]);

			int value = mode.getOperation().apply(argb[1], argb[2], argb[3]);

			pixels[i] = Pixel.mergeARGB(argb[0], value, value, value);
		}
	}
}