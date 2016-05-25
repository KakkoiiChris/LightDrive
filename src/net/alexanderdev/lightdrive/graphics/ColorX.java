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

import net.alexanderdev.lightdrive.util.Pixel;
import net.alexanderdev.lightdrive.util.math.MathX;

/**
 * An extension of Java's own {@code Color} class, which contains constants for
 * all defined CSS3 colors, as well as a set of methods for unique color
 * modifications that build upon Java's {@link Color#brighter()} and
 * {@link Color#darker()}.
 * 
 * @author Christian Bryce Alexander
 * @since April 7, 2015, 7:28:12 PM
 */
public class ColorX extends Color implements Cloneable, Comparable<ColorX> {
	private static final long serialVersionUID = 8589259317799782373L;

	/**
	 * The color <b><span style=
	 * "background: #f00; border: 1px solid black;" >&nbsp; Red
	 * &nbsp;</span></b> in the ARGB color space
	 */
	public static final ColorX RED = new ColorX(0xffff0000);

	/**
	 * The color <b><span style=
	 * "background: #f80; border: 1px solid black;" >&nbsp; Orange
	 * &nbsp;</span></b> in the ARGB color space
	 */
	public static final ColorX ORANGE = new ColorX(0xffff8800);

	/**
	 * The color <b><span style=
	 * "background: #ff0; border: 1px solid black;" >&nbsp; Yellow
	 * &nbsp;</span></b> in the ARGB color space
	 */
	public static final ColorX YELLOW = new ColorX(0xffffff00);

	/**
	 * The color <b><span style=
	 * "background: #8f0; border: 1px solid black;" >&nbsp; Lime
	 * &nbsp;</span></b> in the ARGB color space
	 */
	public static final ColorX LIME = new ColorX(0xff88ff00);

	/**
	 * The color <b><span style=
	 * "background: #0f0; border: 1px solid black;" >&nbsp; Green
	 * &nbsp;</span></b> in the ARGB color space
	 */
	public static final ColorX GREEN = new ColorX(0xff00ff00);

	/**
	 * The color <b><span style=
	 * "background: #0f8; border: 1px solid black;" >&nbsp; Aqua
	 * &nbsp;</span></b> in the ARGB color space
	 */
	public static final ColorX AQUA = new ColorX(0xff00ff88);

	/**
	 * The color <b><span style=
	 * "background: #0ff; border: 1px solid black;" >&nbsp; Cyan
	 * &nbsp;</span></b> in the ARGB color space
	 */
	public static final ColorX CYAN = new ColorX(0xff00ffff);

	/**
	 * The color <b><span style=
	 * "background: #08f; border: 1px solid black;" >&nbsp; Sky
	 * &nbsp;</span></b> in the ARGB color space
	 */
	public static final ColorX SKY = new ColorX(0xff0088ff);

	/**
	 * The color <b><span style=
	 * "color: white; background: #00f; border: 1px solid black;">&nbsp; Blue
	 * &nbsp;</span></b> in the ARGB color space
	 */
	public static final ColorX BLUE = new ColorX(0xff0000ff);

	/**
	 * The color <b><span style=
	 * "background: #80f; border: 1px solid black;" >&nbsp; Purple
	 * &nbsp;</span></b> in the ARGB color space
	 */
	public static final ColorX PURPLE = new ColorX(0xff8800ff);

	/**
	 * The color <b><span style=
	 * "background: #f0f; border: 1px solid black;" >&nbsp; Magenta
	 * &nbsp;</span></b> in the ARGB color space
	 */
	public static final ColorX MAGENTA = new ColorX(0xffff00ff);

	/**
	 * The color <b><span style=
	 * "background: #f08; border: 1px solid black;" >&nbsp; Rose
	 * &nbsp;</span></b> in the ARGB color space
	 */
	public static final ColorX ROSE = new ColorX(0xffff0088);

	/**
	 * The color <b><span style=
	 * "background: #fff; border: 1px solid black;" >&nbsp; White
	 * &nbsp;</span></b> in the ARGB color space
	 */
	public static final ColorX WHITE = new ColorX(0xffffffff);

	/**
	 * The color <b><span style=
	 * "background: #888; border: 1px solid black;" >&nbsp; Gray
	 * &nbsp;</span></b> in the ARGB color space
	 */
	public static final ColorX GRAY = new ColorX(0xff888888);

	/**
	 * The color <b><span style=
	 * "color: white; background: #000; border: 1px solid black;">&nbsp; Black
	 * &nbsp;</span></b> in the ARGB color space
	 */
	public static final ColorX BLACK = new ColorX(0xff000000);

	/**
	 * Creates a {@code ColorX} from the specified ARGB integer value.
	 *
	 * @param color
	 *            An integer of the '0xAARRGGBB' format
	 */
	public ColorX(int color) {
		super(color);
	}

	/**
	 * Creates a {@code ColorX} from the specified integer red, green, and blue
	 * channels between 0 and 255, with a default alpha of 255.
	 * 
	 * @param r
	 *            The red channel to set
	 * @param g
	 *            The green channel to set
	 * @param b
	 *            The blue channel to set
	 */
	public ColorX(int r, int g, int b) {
		super(r, g, b, 255);
	}

	/**
	 * Creates a {@code ColorX} from the specified integer alpha, red, green,
	 * and blue channels between 0 and 255.
	 * 
	 * @param a
	 *            The alpha channel to set
	 * @param r
	 *            The red channel to set
	 * @param g
	 *            The green channel to set
	 * @param b
	 *            The blue channel to set
	 */
	public ColorX(int a, int r, int g, int b) {
		super(r, g, b, a);
	}

	/**
	 * Creates a {@code ColorX} from the specified floating point red, green,
	 * and blue channels between 0f and 1f, with a default alpha of 1f.
	 * 
	 * @param r
	 *            The red channel to set
	 * @param g
	 *            The green channel to set
	 * @param b
	 *            The blue channel to set
	 */
	public ColorX(float r, float g, float b) {
		super(r, g, b, 1f);
	}

	/**
	 * Creates a {@code ColorX} from the specified floating point alpha, red,
	 * green, and blue channels between 0f and 1f.
	 * 
	 * @param a
	 *            The alpha channel to set
	 * @param r
	 *            The red channel to set
	 * @param g
	 *            The green channel to set
	 * @param b
	 *            The blue channel to set
	 */
	public ColorX(float a, float r, float g, float b) {
		super(r, g, b, a);
	}

	/**
	 * @return A color with a random red, green, and blue channel
	 */
	public static ColorX random() {
		return new ColorX(1f, MathX.randomFloat(), MathX.randomFloat(), MathX.randomFloat());
	}

	/**
	 * @return The alpha channel as a float between 0f and 1f
	 */
	public float getFloatAlpha() {
		return (float) getAlpha() / 255f;
	}

	/**
	 * @return The red channel as a float between 0f and 1f
	 */
	public float getFloatRed() {
		return (float) getRed() / 255f;
	}

	/**
	 * @return The green channel as a float between 0f and 1f
	 */
	public float getFloatGreen() {
		return (float) getGreen() / 255f;
	}

	/**
	 * @return The blue channel as a float between 0f and 1f
	 */
	public float getFloatBlue() {
		return (float) getBlue() / 255f;
	}

	/**
	 * @return All channels as an integer of the format '0xAARRGGBB'
	 */
	public int getARGB() {
		return Pixel.mergeARGB(getAlpha(), getRed(), getGreen(), getBlue());
	}

	/**
	 * Gets the integer value of this {@code ColorX} as if its lightness were
	 * altered. Negative lightness tends towards full black (0xff000000),
	 * whereas positive lightness tends towards full white (0xffffffff).
	 * 
	 * @param lightness
	 *            The lightness of the new color
	 * @return The value of this {@code ColorX} with its lightness altered
	 */
	public int ofLightness(float lightness) {
		if (lightness == 0f)
			return getARGB();

		lightness = MathX.clamp(lightness, -1f, 1f);

		float r, g, b;

		if (lightness > 0) {
			r = getRed() + (255 - getRed()) * lightness;
			g = getGreen() + (255 - getGreen()) * lightness;
			b = getBlue() + (255 - getBlue()) * lightness;
		}
		else {
			r = getRed() - (getRed() * -lightness);
			g = getGreen() - (getGreen() * -lightness);
			b = getBlue() - (getBlue() * -lightness);
		}

		return Pixel.mergeARGB(getAlpha(), r, g, b);
	}

	/**
	 * @param lightness
	 *            The lightness of the new color
	 * @return A new instance of {@code ColorX}, with its values based on
	 *         {@link ColorX#ofLightness(float)}
	 */
	public ColorX colorOfLightness(float lightness) {
		return new ColorX(ofLightness(lightness));
	}

	/**
	 * Gets the integer value of this {@link ColorX} as if its saturation were
	 * altered. Negative saturation tends towards average gray
	 * {@code ((r + g + b) /
	 * 3)}, whereas positive saturation tends towards the nearest extreme
	 * {@code (0 or
	 * 255)}.
	 * 
	 * @param saturation
	 *            The saturation of the new color
	 * @return The value of this {@link ColorX} with its saturation altered
	 */
	public int ofSaturation(float saturation) {
		if (saturation == 0f)
			return getARGB();

		saturation = MathX.clamp(saturation, -1f, 1f);

		float r, g, b;

		if (saturation > 0) {
			float rLim = getRed() >= 0.5f ? 1f : 0f;
			float gLim = getGreen() >= 0.5f ? 1f : 0f;
			float bLim = getBlue() >= 0.5f ? 1f : 0f;

			float rDiff = rLim - getRed();
			float gDiff = gLim - getGreen();
			float bDiff = bLim - getBlue();

			r = getRed() + rDiff * saturation;
			g = getGreen() + gDiff * saturation;
			b = getBlue() + bDiff * saturation;
		}
		else {
			float avg = (float) MathX.average(getRed(), getGreen(), getBlue());

			float rDiff = avg - getRed();
			float gDiff = avg - getGreen();
			float bDiff = avg - getBlue();

			r = getRed() + rDiff * -saturation;
			g = getGreen() + gDiff * -saturation;
			b = getBlue() + bDiff * -saturation;
		}

		return Pixel.mergeARGB(getAlpha(), r, g, b);
	}

	/**
	 * @param saturation
	 *            The saturation of the new color
	 * @return A new instance of {@link ColorX}, with its values based on
	 *         {@link ColorX#ofSaturation(float)}
	 */
	public ColorX colorOfSaturation(float saturation) {
		return new ColorX(ofSaturation(saturation));
	}

	/**
	 * Gets the integer value of this {@link ColorX} as if its values were
	 * tending toward inversion. A factor of 0f means no inversion. A factor of
	 * 1f means full inversion. Any other factor tends towards the inverted
	 * values.
	 * 
	 * @param factor
	 *            The inversion of the new color's values
	 * @return The value of this {@link ColorX} with its values slightly
	 *         inverted
	 */
	public int ofInversion(float factor) {
		if (factor == 0f)
			return getARGB();

		if (factor == 1f)
			return Pixel.mergeARGB(getAlpha(), 1f - getRed(), 1f - getGreen(), 1f - getBlue());

		factor = MathX.clamp(factor, 0f, 1f);

		float r, g, b;

		float rLim = 1f - getRed();
		float gLim = 1f - getGreen();
		float bLim = 1f - getBlue();

		r = getRed() - ((getRed() - rLim) * factor);
		g = getGreen() - ((getGreen() - gLim) * factor);
		b = getBlue() - ((getBlue() - bLim) * factor);

		return Pixel.mergeARGB(getAlpha(), r, g, b);
	}

	/**
	 * @param factor
	 *            The inversion of the new color's values
	 * @return A new instance of {@link ColorX}, with its values based on
	 *         {@link ColorX#ofInversion(float)}
	 */
	public ColorX colorOfInversion(float factor) {
		return new ColorX(ofInversion(factor));
	}

	/**
	 * Gets the integer value of this {@link ColorX} as if its values were fully
	 * inverted.
	 * 
	 * @return The inverse value of this {@link ColorX}
	 */
	public int inverted() {
		return ofInversion(1f);
	}

	/**
	 * @return A new instance of {@link ColorX}, with its values based on
	 *         {@link ColorX#inverted()}
	 */
	public ColorX invertedColorX() {
		return new ColorX(inverted());
	}

	/**
	 * Gets the integer value of this {@link ColorX} as if its values were
	 * adjusted based on the specified percentages.
	 * 
	 * @param r
	 *            The percentage of red to use
	 * @param g
	 *            The percentage of green to use
	 * @param b
	 *            The percentage of blue to use
	 * @return The adjusted value of this {@link ColorX}
	 */
	public int ofAdjustment(float r, float g, float b) {
		return Pixel.mergeARGB(getAlpha(), getRed() * r, getGreen() * g, getBlue() * b);
	}

	/**
	 * @param r
	 *            The percentage of red to use
	 * @param g
	 *            The percentage of green to use
	 * @param b
	 *            The percentage of blue to use
	 * @return A new instance of {@link ColorX}, with its values based on
	 *         {@link ColorX#ofAdjustment(float, float, float)}
	 */
	public ColorX colorOfAdjustment(float r, float g, float b) {
		return new ColorX(ofAdjustment(r, g, b));
	}

	/**
	 * Gets the integer value of the gray-scale of this {@link ColorX}, based on
	 * the specified {@link GrayscaleMode}.
	 * 
	 * @param mode
	 *            The gray-scale mode to apply
	 * @return The gray-scale version of this {@link ColorX}
	 */
	public int ofGrayscale(GrayscaleMode mode) {
		float value = mode.getOperation().apply(getRed(), getGreen(), getBlue()) / 255f;

		return Pixel.mergeARGB(getAlpha(), value, value, value);
	}

	/**
	 * @param mode
	 *            The gray-scale mode to apply
	 * @return A new instance of {@link ColorX}, with its values based on
	 *         {@link ColorX#ofGrayscale(GrayscaleMode)}
	 */
	public ColorX colorOfGrayscale(GrayscaleMode mode) {
		return new ColorX(ofGrayscale(mode));
	}

	/**
	 * @return The hexadecimal representation of the integer value of this
	 *         {@link ColorX}
	 */
	public String toHex() {
		return Integer.toHexString(getARGB());
	}

	@Override
	public ColorX clone() {
		return new ColorX(getARGB());
	}

	@Override
	public int compareTo(ColorX c) {
		int dr = Math.abs(getRed() - c.getRed());
		int dg = Math.abs(getGreen() - c.getGreen());
		int db = Math.abs(getBlue() - c.getBlue());

		return MathX.max(dr, dg, db);
	}
}