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

import net.alexanderdev.lightdrive.graphics.ColorS;
import net.alexanderdev.lightdrive.util.Pixel;

/**
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 3:59:04 AM
 */
public class AdjustFilter implements FilterS {
	protected float r, g, b;

	public AdjustFilter(ColorS color) {
		this.r = color.red / 255f;
		this.g = color.green / 255f;
		this.b = color.blue / 255f;
	}

	public AdjustFilter(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	@Override
	public void apply(int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			float[] vals = Pixel.splitFloatARGB(pixels[i]);

			vals[1] *= r;
			vals[2] *= g;
			vals[3] *= b;

			pixels[i] = Pixel.mergeARGB(vals);
		}
	}
}