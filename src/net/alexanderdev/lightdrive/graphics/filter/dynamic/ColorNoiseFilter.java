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
package net.alexanderdev.lightdrive.graphics.filter.dynamic;

import net.alexanderdev.lightdrive.graphics.filter.Filter;
import net.alexanderdev.lightdrive.util.Pixel;
import net.alexanderdev.lightdrive.util.math.MathS;

/**
 * @author Christian Bryce Alexander
 * @since Jan 4, 2016, 10:11:24 PM
 */
public class ColorNoiseFilter implements Filter {
	private float intensity;

	public ColorNoiseFilter(float intensity) {
		this.intensity = intensity;
	}

	public void setIntensity(float intensity) {
		this.intensity = intensity;
	}

	@Override
	public void apply(int width, int height, int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			float[] argb = Pixel.splitFloatARGB(pixels[i]);

			argb[1] *= MathS.clamp(MathS.randomFloat(), 1 - intensity, 1);
			argb[2] *= MathS.clamp(MathS.randomFloat(), 1 - intensity, 1);
			argb[3] *= MathS.clamp(MathS.randomFloat(), 1 - intensity, 1);

			pixels[i] = Pixel.mergeARGB(argb);
		}
	}
}