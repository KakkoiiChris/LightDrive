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
 * A {@link Filter} which applies a simple 3x3 box blur to the {@link Sprite}.
 * 
 * @author Christian Bryce Alexander
 * @since Apr 23, 2016, 9:12:14 PM
 */
public class BlurFilter implements Filter {
	/**
	 * An evenly distributed 3x3 kernel.
	 */
	public static final float[][] SMALL_KERNEL = {
	    {
	        1f / 9f, 1f / 9f, 1f / 9f
		}, {
		    1f / 9f, 1f / 9f, 1f / 9f
		}, {
		    1f / 9f, 1f / 9f, 1f / 9f
		}
	};
	/**
	 * An evenly distributed 5x5 kernel.
	 */
	public static final float[][] MEDIUM_KERNEL = {
	    {
	        1f / 25f, 1f / 25f, 1f / 25f, 1f / 25f, 1f / 25f
		}, {
		    1f / 25f, 1f / 25f, 1f / 25f, 1f / 25f, 1f / 25f
		}, {
		    1f / 25f, 1f / 25f, 1f / 25f, 1f / 25f, 1f / 25f
		}, {
		    1f / 25f, 1f / 25f, 1f / 25f, 1f / 25f, 1f / 25f
		}, {
		    1f / 25f, 1f / 25f, 1f / 25f, 1f / 25f, 1f / 25f
		}
	};
	/**
	 * An evenly distributed 7x7 kernel.
	 */
	public static final float[][] LARGE_KERNEL = {
	    {
	        1 / 49, 1 / 49, 1 / 49, 1 / 49, 1 / 49, 1 / 49, 1 / 49
		}, {
		    1 / 49, 1 / 49, 1 / 49, 1 / 49, 1 / 49, 1 / 49, 1 / 49
		}, {
		    1 / 49, 1 / 49, 1 / 49, 1 / 49, 1 / 49, 1 / 49, 1 / 49
		}, {
		    1 / 49, 1 / 49, 1 / 49, 1 / 49, 1 / 49, 1 / 49, 1 / 49
		}, {
		    1 / 49, 1 / 49, 1 / 49, 1 / 49, 1 / 49, 1 / 49, 1 / 49
		}, {
		    1 / 49, 1 / 49, 1 / 49, 1 / 49, 1 / 49, 1 / 49, 1 / 49
		}, {
		    1 / 49, 1 / 49, 1 / 49, 1 / 49, 1 / 49, 1 / 49, 1 / 49
		},
	};

	private float[][] kernel;

	/**
	 * Creates a new {@link BlurFilter} with the specified blurring kernel.
	 *
	 * @param kernel
	 *            The kernel to set
	 */
	public BlurFilter(float[][] kernel) {
		this.kernel = kernel;
	}

	/**
	 * Sets the blurring kernel for this {@link BlurFilter}.
	 *
	 * @param kernel
	 *            The kernel to set
	 */
	public void setKernel(float[][] kernel) {
	}

	@Override
	public void apply(int width, int height, final int[] pixels) {
		int[] newPixels = new int[pixels.length];

		for (int y = 0; y < pixels.length / width; y++) {
			for (int x = 0; x < width; x++) {
				float rTotal = 0f;
				float gTotal = 0f;
				float bTotal = 0f;

				for (int i = 0; i < kernel.length; i++) {
					for (int j = 0; j < kernel.length; j++) {
						int xloc = x + i - (kernel.length / 2);
						int yloc = y + j - (kernel.length / 2);

						int loc = MathX.clamp(xloc + yloc * width, 0, pixels.length - 1);

						float[] channels = Pixel.splitFloatARGB(pixels[loc]);

						rTotal += channels[1] * kernel[i][j];
						gTotal += channels[2] * kernel[i][j];
						bTotal += channels[3] * kernel[i][j];
					}
				}

				rTotal = MathX.clamp(rTotal, 0f, 1f);
				gTotal = MathX.clamp(gTotal, 0f, 1f);
				bTotal = MathX.clamp(bTotal, 0f, 1f);

				newPixels[x + y * width] = Pixel.mergeARGB(1f, rTotal, gTotal, bTotal);
			}
		}

		System.arraycopy(newPixels, 0, pixels, 0, pixels.length);
	}
}