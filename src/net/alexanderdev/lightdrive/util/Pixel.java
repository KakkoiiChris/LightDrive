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
	public static int getColor(int a, int r, int g, int b) {
		return (a << 24) | (r << 16) | (g << 8) | b;
	}

	public static int getColor(float a, float r, float g, float b) {
		return getColor((int) (a * 0xff), (int) (r * 0xff), (int) (g * 0xff), (int) (b * 0xff));
	}

	public static int getAlpha(int color) {
		return (color >> 24) & 0xff;
	}

	public static int getRed(int color) {
		return (color >> 16) & 0xff;
	}

	public static int getGreen(int color) {
		return (color >> 8) & 0xff;
	}

	public static int getBlue(int color) {
		return color & 0xff;
	}
}