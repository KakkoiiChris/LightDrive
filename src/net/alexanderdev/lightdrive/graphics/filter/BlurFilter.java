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
import net.alexanderdev.lightdrive.util.math.MathS;

/**
 * @author Christian Bryce Alexander
 * @since Apr 23, 2016, 9:12:14 PM
 */
public class BlurFilter implements Filter {
	private final float[][] KERNEL = {
		{
			1 / 9f, 1 / 9f, 1 / 9f
		}, {
			1 / 9f, 1 / 9f, 1 / 9f
		}, {
			1 / 9f, 1 / 9f, 1 / 9f
		}
	};

	public void apply(int width, int height, int[] pixels) {
		int[] newPixels = new int[pixels.length];

		for (int y = 0; y < pixels.length / width; y++) {
			for (int x = 0; x < width; x++) {
				float rtotal = 0f;
				float gtotal = 0f;
				float btotal = 0f;

				for (int i = 0; i < KERNEL.length; i++) {
					for (int j = 0; j < KERNEL.length; j++) {
						int xloc = x + i - (KERNEL.length / 2);
						int yloc = y + j - (KERNEL.length / 2);

						int loc = MathS.clamp(xloc + yloc * width, 0, pixels.length - 1);

						float[] channels = Pixel.splitFloatARGB(pixels[loc]);

						rtotal += channels[1] * KERNEL[i][j];
						gtotal += channels[2] * KERNEL[i][j];
						btotal += channels[3] * KERNEL[i][j];
					}
				}

				rtotal = MathS.clamp(rtotal, 0, 1);
				gtotal = MathS.clamp(gtotal, 0, 1);
				btotal = MathS.clamp(btotal, 0, 1);

				newPixels[x + y * width] = Pixel.mergeARGB(1, rtotal, gtotal, btotal);
			}
		}

		System.arraycopy(newPixels, 0, pixels, 0, pixels.length);
	}
}