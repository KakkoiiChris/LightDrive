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

import net.alexanderdev.lightdrive.util.Pixel;
import net.alexanderdev.lightdrive.util.math.MathX;

/**
 * @author Christian Bryce Alexander
 * @since May 18, 2016, 1:47:11 PM
 */
public class ContrastFilter implements Filter {
	private float contrast;

	public ContrastFilter(float contrast) {
		this.contrast = Math.max(contrast, 0);
	}
	
	public void setContrast(float contrast) {
		this.contrast = contrast;
	}

	@Override
	public void apply(int width, int height, int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			float[] argb = Pixel.splitFloatARGB(pixels[i]);

			for (int c = 1; c < argb.length; c++) {
				argb[c] = MathX.clamp(contrast * (argb[c] - 0.5f) + 0.5f, 0f, 1f);
			}

			pixels[i] = Pixel.mergeARGB(argb);
		}
	}
}