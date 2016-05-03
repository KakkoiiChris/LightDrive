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

import java.awt.Color;

import net.alexanderdev.lightdrive.util.ArraysX;
import net.alexanderdev.lightdrive.util.Pixel;
import net.alexanderdev.lightdrive.util.math.MathX;

/**
 * An extension of Java's own {@code Color} class, which contains constants for
 * all defined CSS3 colors, as well as a set of methods for unique color
 * modifications that build upon Java's {@code Color.brighter();} and
 * {@code Color.darker();}
 * 
 * @author Christian Bryce Alexander
 * @since April 7, 2015 | 7:28:12 PM
 */
public class ColorX extends Color {
	private static final long serialVersionUID = -1851689902505547716L;

	public static ColorX RED = new ColorX(0xffff0000);
	public static ColorX ORANGE = new ColorX(0xffff8800);
	public static ColorX YELLOW = new ColorX(0xffffff00);
	public static ColorX LIME = new ColorX(0xff88ff00);
	public static ColorX GREEN = new ColorX(0xff00ff00);
	public static ColorX AQUA = new ColorX(0xff00ff88);
	public static ColorX CYAN = new ColorX(0xff00ffff);
	public static ColorX SKY = new ColorX(0xff0088ff);
	public static ColorX BLUE = new ColorX(0xff0000ff);
	public static ColorX PURPLE = new ColorX(0xff8800ff);
	public static ColorX MAGENTA = new ColorX(0xffff00ff);
	public static ColorX ROSE = new ColorX(0xffff0088);
	public static ColorX WHITE = new ColorX(0xffffffff);
	public static ColorX GRAY = new ColorX(0xff888888);
	public static ColorX BLACK = new ColorX(0xff000000);

	private float alpha, red, green, blue;

	public ColorX(int color) {
		super(Pixel.getIntRed(color), Pixel.getIntGreen(color), Pixel.getIntBlue(color), Pixel.getIntAlpha(color));
	}

	public ColorX(int r, int g, int b) {
		super(r, g, b, 255);
	}

	public ColorX(int a, int r, int g, int b) {
		super(r, g, b, a);
	}

	public ColorX(float r, float g, float b) {
		super(r, g, b, 1f);
	}

	public ColorX(float a, float r, float g, float b) {
		super(r, g, b, a);
	}

	public int getARGB() {
		return Pixel.mergeARGB(alpha, red, green, blue);
	}

	public float getFloatAlpha() {
		return alpha;
	}

	public float getFloatRed() {
		return red;
	}

	public float getFloatGreen() {
		return green;
	}

	public float getFloatBlue() {
		return blue;
	}

	public int getIntAlpha() {
		return (int) (alpha * 255f);
	}

	public int getIntRed() {
		return (int) (red * 255f);
	}

	public int getIntGreen() {
		return (int) (green * 255f);
	}

	public int getIntBlue() {
		return (int) (blue * 255f);
	}

	public int ofBrightness(float brightness) {
		if (brightness == 0f)
			return getARGB();

		brightness = MathX.clamp(brightness, -1f, 1f);

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

	public ColorX colorOfBrightness(float brightness) {
		return new ColorX(ofBrightness(brightness));
	}

	public int ofSaturation(float saturation) {
		if (saturation == 0f)
			return getARGB();

		saturation = MathX.clamp(saturation, -1f, 1f);

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
			float avg = (float) MathX.average(red, green, blue);

			float rDiff = avg - red;
			float gDiff = avg - green;
			float bDiff = avg - blue;

			r = red + rDiff * -saturation;
			g = green + gDiff * -saturation;
			b = blue + bDiff * -saturation;
		}

		return Pixel.mergeARGB(alpha, r, g, b);
	}

	public ColorX colorOfSaturation(float saturation) {
		return new ColorX(ofSaturation(saturation));
	}

	public int ofInversion(float factor) {
		if (factor == 0f)
			return getARGB();

		if (factor == 1f)
			return Pixel.mergeARGB(alpha, 1f - red, 1f - green, 1f - blue);

		factor = MathX.clamp(factor, 0f, 1f);

		float r, g, b;

		float rLim = 1f - red;
		float gLim = 1f - green;
		float bLim = 1f - blue;

		r = red - ((red - rLim) * factor);
		g = green - ((green - gLim) * factor);
		b = blue - ((blue - bLim) * factor);

		return Pixel.mergeARGB(alpha, r, g, b);
	}

	public ColorX colorOfInversion(float factor) {
		return new ColorX(ofInversion(factor));
	}

	public int inverted() {
		return ofInversion(1f);
	}

	public ColorX invertedColor() {
		return new ColorX(inverted());
	}

	public int ofGrayscale(GrayscaleMode mode) {
		float value = 0;

		switch (mode) {
			case AVERAGE:
				value = (float) MathX.average(red, green, blue);
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
				value = (float) MathX.average(MathX.min(red, green, blue), MathX.max(red, green, blue));
				break;
			case LUMINOSITY:
				value = MathX.clamp((red * 0.2126f) + (green * 0.7152f) + (blue * 0.0722f), 0f, 1f);
				break;
			case MAX_DECOMP:
				value = MathX.max(red, green, blue);
				break;
			case MID_DECOMP:
				value = ArraysX.sort(red, green, blue)[1];
				break;
			case MIN_DECOMP:
				value = MathX.min(red, green, blue);
				break;
		}

		return Pixel.mergeARGB(alpha, value, value, value);
	}

	public ColorX colorOfGrayscale(GrayscaleMode mode) {
		return new ColorX(ofGrayscale(mode));
	}

	public int ofAdjustment(float pr, float pg, float pb) {
		return Pixel.mergeARGB(alpha, red * pr, green * pg, blue * pb);
	}

	public ColorX colorOfAdjustment(float pr, float pg, float pb) {
		return new ColorX(ofAdjustment(pr, pg, pb));
	}
}