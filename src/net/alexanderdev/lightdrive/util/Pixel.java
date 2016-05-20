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
package net.alexanderdev.lightdrive.util;

/**
 * A utility class which provides for a multitude of advanced color operations.
 * 
 * @author Christian Bryce Alexander
 * @since Dec 13, 2015, 2:29:53 AM
 */
public class Pixel {
	/**
	 * Blends each color together based on the alpha channels.
	 *
	 * @param colors
	 *            The colors to blend together
	 * @return The colors blended
	 */
	public static int alphaBlendARGB(int... colors) {
		if (colors.length == 0)
			return 0;

		if (colors.length == 1)
			return colors[0];

		float[] target = splitFloatARGB(colors[0]);

		for (int i = 1; i < colors.length; i++) {
			float[] blend = Pixel.splitFloatARGB(colors[i]);

			target[1] = (blend[1] * blend[0]) + (target[1] * (1f - blend[0]));
			target[2] = (blend[2] * blend[0]) + (target[2] * (1f - blend[0]));
			target[3] = (blend[3] * blend[0]) + (target[3] * (1f - blend[0]));
		}

		return Pixel.mergeARGB(target);
	}

	/**
	 * Sets the alpha channel of specified color integer value.
	 * 
	 * @param color
	 *            The color to alter
	 * @param alpha
	 *            The alpha channel to set
	 * @return The color with its alpha channel changed
	 */
	public static int setAlpha(int color, float alpha) {
		float[] argb = splitFloatARGB(color);

		argb[0] = alpha;

		return mergeARGB(argb);
	}

	/**
	 * Sets the red channel of specified color integer value.
	 * 
	 * @param color
	 *            The color to alter
	 * @param red
	 *            The red channel to set
	 * @return The color with its red channel changed
	 */
	public static int setRed(int color, float red) {
		float[] argb = splitFloatARGB(color);

		argb[1] = red;

		return mergeARGB(argb);
	}

	/**
	 * Sets the green channel of specified color integer value.
	 * 
	 * @param color
	 *            The color to alter
	 * @param green
	 *            The green channel to set
	 * @return The color with its green channel changed
	 */
	public static int setGreen(int color, float green) {
		float[] argb = splitFloatARGB(color);

		argb[2] = green;

		return mergeARGB(argb);
	}

	/**
	 * Sets the blue channel of specified color integer value.
	 * 
	 * @param color
	 *            The color to alter
	 * @param blue
	 *            The blue channel to set
	 * @return The color with its blue channel changed
	 */
	public static int setBlue(int color, float blue) {
		float[] argb = splitFloatARGB(color);

		argb[3] = blue;

		return mergeARGB(argb);
	}

	/**
	 * Merges four float channels into a single integer value.
	 * 
	 * @param a
	 *            The alpha channel
	 * @param r
	 *            The red channel
	 * @param g
	 *            The green channel
	 * @param b
	 *            The blue channel
	 * @return The four channels merged
	 */
	public static int mergeARGB(float a, float r, float g, float b) {
		return ((int) (a * 255f) << 24) | ((int) (r * 255f) << 16) | ((int) (g * 255f) << 8) | (int) (b * 255f);
	}

	/**
	 * Merges the array of float channels into a single integer value.
	 *
	 * @param channels
	 *            An array containing all four color channels as floats
	 * @return The four channels merged
	 */
	public static int mergeARGB(float[] channels) {
		return ((int) (channels[0] * 255f) << 24) | ((int) (channels[1] * 255f) << 16)
				| ((int) (channels[2] * 255f) << 8) | (int) (channels[3] * 255f);
	}

	/**
	 * Merges four int channels into a single integer value.
	 *
	 * @param a
	 *            The alpha channel
	 * @param r
	 *            The red channel
	 * @param g
	 *            The green channel
	 * @param b
	 *            The blue channel
	 * @return The four channels merged
	 */
	public static int mergeARGB(int a, int r, int g, int b) {
		return (a << 24) | (r << 16) | (g << 8) | b;
	}

	/**
	 * Merges the array of int channels into a single integer value.
	 *
	 * @param channels
	 *            An array containing all four color channels as ints
	 * @return The four channels merged
	 */
	public static int mergeARGB(int[] channels) {
		return (channels[0] << 24) | (channels[1] << 16) | (channels[2] << 8) | channels[3];
	}

	/**
	 * Splits an integer into an float array of all four channels.
	 * 
	 * @param color
	 *            The color to split
	 * @return The channels split
	 */
	public static float[] splitFloatARGB(int color) {
		float a = getFloatAlpha(color);
		float r = getFloatRed(color);
		float g = getFloatGreen(color);
		float b = getFloatBlue(color);

		return new float[] {
				a, r, g, b
		};
	}

	/**
	 * Splits an integer into an int array of all four channels.
	 * 
	 * @param color
	 *            The color to split
	 * @return The channels split
	 */
	public static int[] splitIntARGB(int color) {
		int a = getIntAlpha(color);
		int r = getIntRed(color);
		int g = getIntGreen(color);
		int b = getIntBlue(color);

		return new int[] {
				a, r, g, b
		};
	}

	/**
	 * @param color
	 *            The color to extract the channel from
	 * @return The alpha channel of {@code color} as an integer value between 0
	 *         and 255
	 */
	public static int getIntAlpha(int color) {
		return (color >> 24) & 0xff;
	}

	/**
	 * @param color
	 *            The color to extract the channel from
	 * @return The red channel of {@code color} as an integer value between 0
	 *         and 255
	 */
	public static int getIntRed(int color) {
		return (color >> 16) & 0xff;
	}

	/**
	 * @param color
	 *            The color to extract the channel from
	 * @return The green channel of {@code color} as an integer value between 0
	 *         and 255
	 */
	public static int getIntGreen(int color) {
		return (color >> 8) & 0xff;
	}

	/**
	 * @param color
	 *            The color to extract the channel from
	 * @return The blue channel of {@code color} as an integer value between 0
	 *         and 255
	 */
	public static int getIntBlue(int color) {
		return color & 0xff;
	}

	/**
	 * @param color
	 *            The color to extract the channel from
	 * @return The alpha channel of {@code color} as an float value between 0f
	 *         and 1f
	 */
	public static float getFloatAlpha(int color) {
		return getIntAlpha(color) / 255f;
	}

	/**
	 * @param color
	 *            The color to extract the channel from
	 * @return The red channel of {@code color} as an float value between 0f and
	 *         1f
	 */
	public static float getFloatRed(int color) {
		return getIntRed(color) / 255f;
	}

	/**
	 * @param color
	 *            The color to extract the channel from
	 * @return The green channel of {@code color} as an float value between 0f
	 *         and 1f
	 */
	public static float getFloatGreen(int color) {
		return getIntGreen(color) / 255f;
	}

	/**
	 * @param color
	 *            The color to extract the channel from
	 * @return The blue channel of {@code color} as an float value between 0f
	 *         and 1f
	 */
	public static float getFloatBlue(int color) {
		return getIntBlue(color) / 255f;
	}
}