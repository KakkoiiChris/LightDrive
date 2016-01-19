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
 * @author Christian Bryce Alexander
 * @since Dec 11, 2015, 2:33:44 AM
 */
public class Pixel {
	public static int blendARGB(int... colors) {
		if (colors.length == 0)
			return 0;
		
		if (colors.length == 1)
			return colors[0];

		float[] ca = splitFloatARGB(colors[0]);

		for (int i = 1; i < colors.length; i++) {
			float[] cb = splitFloatARGB(colors[i]);

			for (int j = 0; j < ca.length; j++)
				ca[j] *= cb[j];
		}

		return mergeARGB(ca);
	}
	
	public static int blendRGB(int... colors) {
		if (colors.length == 0)
			return 0;
		
		if (colors.length == 1)
			return colors[0];

		float[] ca = splitFloatARGB(colors[0]);

		for (int i = 1; i < colors.length; i++) {
			float[] cb = splitFloatARGB(colors[i]);

			for (int j = 1; j < ca.length; j++)
				ca[j] *= cb[j];
		}

		return mergeARGB(ca);
	}

	public static int mergeARGB(float a, float r, float g, float b) {
		return ((int) (a * 255f) << 24)
			 | ((int) (r * 255f) << 16)
			 | ((int) (g * 255f) <<  8)
			 |  (int) (b * 255f);
	}

	public static int mergeARGB(float[] components) {
		return ((int) (components[0] * 255f) << 24)
			 | ((int) (components[1] * 255f) << 16)
			 | ((int) (components[2] * 255f) <<  8)
			 |  (int) (components[3] * 255f);
	}

	public static int mergeARGB(int a, int r, int g, int b) {
		return (a << 24) 
			 | (r << 16) 
			 | (g <<  8) 
			 |  b;
	}

	public static int mergeARGB(int[] components) {
		return (components[0] << 24) 
			 | (components[1] << 16) 
			 | (components[2] <<  8) 
			 |  components[3];
	}

	public static float[] splitFloatARGB(int color) {
		float a = ((color >> 24) & 0xff) / 255f;
		float r = ((color >> 16) & 0xff) / 255f;
		float g = ((color >>  8) & 0xff) / 255f;
		float b = ( color        & 0xff) / 255f;

		return new float[] {
			a, r, g, b
		};
	}

	public static int[] splitIntARGB(int color) {
		int a = (color >> 24) & 0xff;
		int r = (color >> 16) & 0xff;
		int g = (color >>  8) & 0xff;
		int b =  color        & 0xff;

		return new int[] {
			a, r, g, b
		};
	}
}