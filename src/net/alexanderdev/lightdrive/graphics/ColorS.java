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
package net.alexanderdev.lightdrive.graphics;

import net.alexanderdev.lightdrive.util.ArraysS;
import net.alexanderdev.lightdrive.util.Pixel;
import net.alexanderdev.lightdrive.util.math.MathS;

/**
 * An extension of Java's own {@code Color} class, which contains constants for
 * all defined CSS3 colors, as well as a set of methods for unique color
 * modifications that build upon Java's {@code Color.brighter();} and
 * {@code Color.darker();}
 * 
 * @author Christian Bryce Alexander
 * @since April 7, 2015 | 7:28:12 PM
 */
public class ColorS {
	public static ColorS RED = new ColorS(0xffff0000);
	public static ColorS ORANGE = new ColorS(0xffff8800);
	public static ColorS YELLOW = new ColorS(0xffffff00);
	public static ColorS LIME = new ColorS(0xff88ff00);
	public static ColorS GREEN = new ColorS(0xff00ff00);
	public static ColorS AQUA = new ColorS(0xff00ff88);
	public static ColorS CYAN = new ColorS(0xff00ffff);
	public static ColorS SKY = new ColorS(0xff0088ff);
	public static ColorS BLUE = new ColorS(0xff0000ff);
	public static ColorS PURPLE = new ColorS(0xff8800ff);
	public static ColorS MAGENTA = new ColorS(0xffff00ff);
	public static ColorS ROSE = new ColorS(0xffff0088);
	public static ColorS WHITE = new ColorS(0xffffffff);
	public static ColorS GRAY = new ColorS(0xff888888);
	public static ColorS BLACK = new ColorS(0xff000000);

	public float alpha, red, green, blue;

	public ColorS(int color) {
		float[] values = Pixel.splitFloatARGB(color);

		alpha = values[0];
		red = values[1];
		green = values[2];
		blue = values[3];
	}

	public ColorS(int r, int g, int b) {
		this(255, r, g, b);
	}

	public ColorS(int a, int r, int g, int b) {
		alpha = a / 255f;
		red = r / 255f;
		green = g / 255f;
		blue = b / 255f;
	}

	public ColorS(float r, float g, float b) {
		this(1f, r, g, b);
	}

	public ColorS(float a, float r, float g, float b) {
		alpha = a;
		red = r;
		green = g;
		blue = b;
	}

	public int getARGB() {
		return Pixel.mergeARGB(alpha, red, green, blue);
	}

	public int ofBrightness(float brightness) {
		if (brightness == 0f)
			return getARGB();

		brightness = MathS.clamp(brightness, -1f, 1f);

		float r, g, b;

		if (brightness > 0) {
			r = red + (255 - red) * brightness;
			g = green + (255 - green) * brightness;
			b = blue + (255 - blue) * brightness;
		}
		else {
			r = red - red * -brightness;
			g = green - green * -brightness;
			b = blue - blue * -brightness;
		}

		return Pixel.mergeARGB(alpha, r, g, b);
	}

	public ColorS colorOfBrightness(float brightness) {
		return new ColorS(ofBrightness(brightness));
	}

	public int ofSaturation(float saturation) {
		if (saturation == 0f)
			return getARGB();

		saturation = MathS.clamp(saturation, -1f, 1f);

		float r, g, b;

		if (saturation > 0) {
			float rLim = red >= 0.5f ? 1f : 0f;
			float gLim = green >= 0.5f ? 1f : 0f;
			float bLim = blue >= 0.5f ? 1f : 0f;

			float rDiff = rLim - red;
			float gDiff = gLim - green;
			float bDiff = bLim - blue;

			r = red + rDiff * saturation;
			g = green + gDiff * saturation;
			b = blue + bDiff * saturation;
		}
		else {
			float avg = (float) MathS.average(red, green, blue);

			float rDiff = avg - red;
			float gDiff = avg - green;
			float bDiff = avg - blue;

			r = red + rDiff * -saturation;
			g = green + gDiff * -saturation;
			b = blue + bDiff * -saturation;
		}

		return Pixel.mergeARGB(alpha, r, g, b);
	}

	public ColorS colorOfSaturation(float saturation) {
		return new ColorS(ofSaturation(saturation));
	}

	public int ofInversion(float factor) {
		if (factor == 0f)
			return getARGB();

		if (factor == 1f)
			return Pixel.mergeARGB(alpha, 1f - red, 1f - green, 1f - blue);

		factor = MathS.clamp(factor, 0f, 1f);

		float r, g, b;

		float rLim = 1f - red;
		float gLim = 1f - green;
		float bLim = 1f - blue;

		r = red - ((red - rLim) * factor);
		g = green - ((green - gLim) * factor);
		b = blue - ((blue - bLim) * factor);

		return Pixel.mergeARGB(alpha, r, g, b);
	}

	public ColorS colorOfInversion(float factor) {
		return new ColorS(ofInversion(factor));
	}

	public int inverted() {
		return ofInversion(1f);
	}

	public ColorS invertedColor() {
		return new ColorS(inverted());
	}

	public int ofGrayscale(GrayscaleMode mode) {
		float value = 0;

		switch (mode) {
			case AVERAGE:
				value = (float) MathS.average(red, green, blue);
				break;
			case CHANNEL_RED:
				value = red;
				break;
			case CHANNEL_GREEN:
				value = green;
				break;
			case CHANNEL_BLUE:
				value = blue;
				break;
			case LIGHTNESS:
				value = (float) MathS.average(MathS.min(red, green, blue), MathS.max(red, green, blue));
				break;
			case LUMINOSITY:
				value = MathS.clamp((red * 0.2126f) + (green * 0.7152f) + (blue * 0.0722f), 0f, 1f);
				break;
			case MAX_DECOMP:
				value = MathS.max(red, green, blue);
				break;
			case MID_DECOMP:
				value = ArraysS.sort(red, green, blue)[1];
				break;
			case MIN_DECOMP:
				value = MathS.min(red, green, blue);
				break;
		}

		return Pixel.mergeARGB(alpha, value, value, value);
	}

	public ColorS colorOfGrayscale(GrayscaleMode mode) {
		return new ColorS(ofGrayscale(mode));
	}
}