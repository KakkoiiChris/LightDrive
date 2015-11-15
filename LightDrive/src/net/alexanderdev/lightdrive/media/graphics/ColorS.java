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
package net.alexanderdev.lightdrive.media.graphics;

import static net.alexanderdev.lightdrive.util.math.MathS.*;

import java.awt.Color;
import java.awt.color.ColorSpace;

/**
 * An extension of Java's own {@code Color} class, which contains constants for
 * all defined CSS3 colors, as well as a set of methods for unique color
 * modifications that build upon Java's {@code Color.brighter();} and
 * {@code Color.darker();}
 * 
 * @author Christian Bryce Alexander
 * @since April 7, 2015 | 7:28:12 PM
 */
public class ColorS extends Color {
	private static final long serialVersionUID = -115181968328911306L;

	/**
	 * Color constant for the CSS3 color definition "Alice Blue"
	 */
	public static final ColorS ALICE_BLUE = new ColorS(0xF0F8FF);

	/**
	 * Color constant for the CSS3 color definition "Antique White"
	 */
	public static final ColorS ANTIQUE_WHITE = new ColorS(0xFAEBD7);

	/**
	 * Color constant for the CSS3 color definition "Aqua"
	 */
	public static final ColorS AQUA = new ColorS(0x00FFFF);

	/**
	 * Color constant for the CSS3 color definition "Aquamarine"
	 */
	public static final ColorS AQUAMARINE = new ColorS(0x7FFFD4);

	/**
	 * Color constant for the CSS3 color definition "Azure"
	 */
	public static final ColorS AZURE = new ColorS(0xF0FFFF);

	/**
	 * Color constant for the CSS3 color definition "Beige"
	 */
	public static final ColorS BEIGE = new ColorS(0xF5F5DC);

	/**
	 * Color constant for the CSS3 color definition "Bisque"
	 */
	public static final ColorS BISQUE = new ColorS(0xFFE4C4);

	/**
	 * Color constant for the CSS3 color definition "Black"
	 */
	public static final ColorS BLACK = new ColorS(0x000000);

	/**
	 * Color constant for the CSS3 color definition "Blanched Almond"
	 */
	public static final ColorS BLANCHED_ALMOND = new ColorS(0xFFEBCD);

	/**
	 * Color constant for the CSS3 color definition "Blue"
	 */
	public static final ColorS BLUE = new ColorS(0x0000FF);

	/**
	 * Color constant for the CSS3 color definition "Blue Violet"
	 */
	public static final ColorS BLUE_VIOLET = new ColorS(0x8A2BE2);

	/**
	 * Color constant for the CSS3 color definition "Brown"
	 */
	public static final ColorS BROWN = new ColorS(0xA52A2A);

	/**
	 * Color constant for the CSS3 color definition "Burly Wood"
	 */
	public static final ColorS BURLY_WOOD = new ColorS(0xDEB887);

	/**
	 * Color constant for the CSS3 color definition "Cadet Blue"
	 */
	public static final ColorS CADET_BLUE = new ColorS(0x5F9EA0);

	/**
	 * Color constant for the CSS3 color definition "Chartreuse"
	 */
	public static final ColorS CHARTREUSE = new ColorS(0x7FFF00);

	/**
	 * Color constant for the CSS3 color definition "Chocolate"
	 */
	public static final ColorS CHOCOLATE = new ColorS(0xD2691E);

	/**
	 * Color constant for the CSS3 color definition "Coral"
	 */
	public static final ColorS CORAL = new ColorS(0xFF7F50);

	/**
	 * Color constant for the CSS3 color definition "Cornflower Blue"
	 */
	public static final ColorS CORNFLOWER_BLUE = new ColorS(0x6495ED);

	/**
	 * Color constant for the CSS3 color definition "Cornsilk"
	 */
	public static final ColorS CORNSILK = new ColorS(0xFFF8DC);

	/**
	 * Color constant for the CSS3 color definition "Crimson"
	 */
	public static final ColorS CRIMSON = new ColorS(0xDC143C);

	/**
	 * Color constant for the CSS3 color definition "Cyan"
	 */
	public static final ColorS CYAN = new ColorS(0x00FFFF);

	/**
	 * Color constant for the CSS3 color definition "Dark Blue"
	 */
	public static final ColorS DARK_BLUE = new ColorS(0x00008B);

	/**
	 * Color constant for the CSS3 color definition "Dark Cyan"
	 */
	public static final ColorS DARK_CYAN = new ColorS(0x008B8B);

	/**
	 * Color constant for the CSS3 color definition "Dark Golden Rod"
	 */
	public static final ColorS DARK_GOLDEN_ROD = new ColorS(0xB8860B);

	/**
	 * Color constant for the CSS3 color definition "Dark Gray"
	 */
	public static final ColorS DARK_GRAY = new ColorS(0xA9A9A9);

	/**
	 * Color constant for the CSS3 color definition "Dark Green"
	 */
	public static final ColorS DARK_GREEN = new ColorS(0x006400);

	/**
	 * Color constant for the CSS3 color definition "Dark Khaki"
	 */
	public static final ColorS DARK_KHAKI = new ColorS(0xBDB76B);

	/**
	 * Color constant for the CSS3 color definition "Dark Magenta"
	 */
	public static final ColorS DARK_MAGENTA = new ColorS(0x8B008B);

	/**
	 * Color constant for the CSS3 color definition "Dark Olive Green"
	 */
	public static final ColorS DARK_OLIVE_GREEN = new ColorS(0x556B2F);

	/**
	 * Color constant for the CSS3 color definition "Dark Orange"
	 */
	public static final ColorS DARK_ORANGE = new ColorS(0xFF8C00);

	/**
	 * Color constant for the CSS3 color definition "Dark Orchid"
	 */
	public static final ColorS DARK_ORCHID = new ColorS(0x9932CC);

	/**
	 * Color constant for the CSS3 color definition "Dark Red"
	 */
	public static final ColorS DARK_RED = new ColorS(0x8B0000);

	/**
	 * Color constant for the CSS3 color definition "Dark Salmon"
	 */
	public static final ColorS DARK_SALMON = new ColorS(0xE9967A);

	/**
	 * Color constant for the CSS3 color definition "Dark Sea Green"
	 */
	public static final ColorS DARK_SEA_GREEN = new ColorS(0x8FBC8F);

	/**
	 * Color constant for the CSS3 color definition "Dark Slate Blue"
	 */
	public static final ColorS DARK_SLATE_BLUE = new ColorS(0x483D8B);

	/**
	 * Color constant for the CSS3 color definition "Dark Slate Gray"
	 */
	public static final ColorS DARK_SLATE_GRAY = new ColorS(0x2F4F4F);

	/**
	 * Color constant for the CSS3 color definition "Dark Turquiose"
	 */
	public static final ColorS DARK_TURQUOISE = new ColorS(0x00CED1);

	/**
	 * Color constant for the CSS3 color definition "Dark Violet"
	 */
	public static final ColorS DARK_VIOLET = new ColorS(0x9400D3);

	/**
	 * Color constant for the CSS3 color definition "Deep Pink"
	 */
	public static final ColorS DEEP_PINK = new ColorS(0xFF1493);

	/**
	 * Color constant for the CSS3 color definition "Deep Sky Blue"
	 */
	public static final ColorS DEEP_SKY_BLUE = new ColorS(0x00BFFF);

	/**
	 * Color constant for the CSS3 color definition "Dim Gray"
	 */
	public static final ColorS DIM_GRAY = new ColorS(0x696969);

	/**
	 * Color constant for the CSS3 color definition "Dodger Blue"
	 */
	public static final ColorS DODGER_BLUE = new ColorS(0x1E90FF);

	/**
	 * Color constant for the CSS3 color definition "Fire Brick"
	 */
	public static final ColorS FIRE_BRICK = new ColorS(0xB22222);

	/**
	 * Color constant for the CSS3 color definition "Floral White"
	 */
	public static final ColorS FLORAL_WHITE = new ColorS(0xFFFAF0);

	/**
	 * Color constant for the CSS3 color definition "Forest Green"
	 */
	public static final ColorS FOREST_GREEN = new ColorS(0x228B22);

	/**
	 * Color constant for the CSS3 color definition "Fuchsia"
	 */
	public static final ColorS FUCHSIA = new ColorS(0xFF00FF);

	/**
	 * Color constant for the CSS3 color definition "Gainsboro"
	 */
	public static final ColorS GAINSBORO = new ColorS(0xDCDCDC);

	/**
	 * Color constant for the CSS3 color definition "Ghost White"
	 */
	public static final ColorS GHOST_WHITE = new ColorS(0xF8F8FF);

	/**
	 * Color constant for the CSS3 color definition "Gold"
	 */
	public static final ColorS GOLD = new ColorS(0xFFD700);

	/**
	 * Color constant for the CSS3 color definition "Golden Rod"
	 */
	public static final ColorS GOLDEN_ROD = new ColorS(0xDAA520);

	/**
	 * Color constant for the CSS3 color definition "Gray"
	 */
	public static final ColorS GRAY = new ColorS(0x808080);

	/**
	 * Color constant for the CSS3 color definition "Green"
	 */
	public static final ColorS GREEN = new ColorS(0x008000);

	/**
	 * Color constant for the CSS3 color definition "Green Yellow"
	 */
	public static final ColorS GREEN_YELLOW = new ColorS(0xADFF2F);

	/**
	 * Color constant for the CSS3 color definition "Honey Dew"
	 */
	public static final ColorS HONEY_DEW = new ColorS(0xF0FFF0);

	/**
	 * Color constant for the CSS3 color definition "Hot Pink"
	 */
	public static final ColorS HOT_PINK = new ColorS(0xFF69B4);

	/**
	 * Color constant for the CSS3 color definition "Indian Red"
	 */
	public static final ColorS INDIAN_RED = new ColorS(0xCD5C5C);

	/**
	 * Color constant for the CSS3 color definition "Indigo"
	 */
	public static final ColorS INDIGO = new ColorS(0x4B0082);

	/**
	 * Color constant for the CSS3 color definition "Ivory"
	 */
	public static final ColorS IVORY = new ColorS(0xFFFFF0);

	/**
	 * Color constant for the CSS3 color definition "Khaki"
	 */
	public static final ColorS KHAKI = new ColorS(0xF0E68C);

	/**
	 * Color constant for the CSS3 color definition "Lavender"
	 */
	public static final ColorS LAVENDER = new ColorS(0xE6E6FA);

	/**
	 * Color constant for the CSS3 color definition "Lavender Blush"
	 */
	public static final ColorS LAVENDER_BLUSH = new ColorS(0xFFF0F5);

	/**
	 * Color constant for the CSS3 color definition "Lawn Green"
	 */
	public static final ColorS LAWN_GREEN = new ColorS(0x7CFC00);

	/**
	 * Color constant for the CSS3 color definition "Lemon Chiffon"
	 */
	public static final ColorS LEMON_CHIFFON = new ColorS(0xFFFACD);

	/**
	 * Color constant for the CSS3 color definition "Light Blue"
	 */
	public static final ColorS LIGHT_BLUE = new ColorS(0xADD8E6);

	/**
	 * Color constant for the CSS3 color definition "Light Coral"
	 */
	public static final ColorS LIGHT_CORAL = new ColorS(0xF08080);

	/**
	 * Color constant for the CSS3 color definition "Light Cyan"
	 */
	public static final ColorS LIGHT_CYAN = new ColorS(0xE0FFFF);

	/**
	 * Color constant for the CSS3 color definition "Light Golden Rod Yellow"
	 */
	public static final ColorS LIGHT_GOLDEN_ROD_YELLOW = new ColorS(0xFAFAD2);

	/**
	 * Color constant for the CSS3 color definition "Light Gray"
	 */
	public static final ColorS LIGHT_GRAY = new ColorS(0xD3D3D3);

	/**
	 * Color constant for the CSS3 color definition "Light Green"
	 */
	public static final ColorS LIGHT_GREEN = new ColorS(0x90EE90);

	/**
	 * Color constant for the CSS3 color definition "Light Pink"
	 */
	public static final ColorS LIGHT_PINK = new ColorS(0xFFB6C1);

	/**
	 * Color constant for the CSS3 color definition "Light Salmon"
	 */
	public static final ColorS LIGHT_SALMON = new ColorS(0xFFA07A);

	/**
	 * Color constant for the CSS3 color definition "Light Sea Green"
	 */
	public static final ColorS LIGHT_SEA_GREEN = new ColorS(0x20B2AA);

	/**
	 * Color constant for the CSS3 color definition "Light Sky Blue"
	 */
	public static final ColorS LIGHT_SKY_BLUE = new ColorS(0x87CEFA);

	/**
	 * Color constant for the CSS3 color definition "Light Slate Gray"
	 */
	public static final ColorS LIGHT_SLATE_GRAY = new ColorS(0x778899);

	/**
	 * Color constant for the CSS3 color definition "Light Steel Blue"
	 */
	public static final ColorS LIGHT_STEEL_BLUE = new ColorS(0xB0C4DE);

	/**
	 * Color constant for the CSS3 color definition "Light Yellow"
	 */
	public static final ColorS LIGHT_YELLOW = new ColorS(0xFFFFE0);

	/**
	 * Color constant for the CSS3 color definition "Lime"
	 */
	public static final ColorS LIME = new ColorS(0x00FF00);

	/**
	 * Color constant for the CSS3 color definition "Lime Green"
	 */
	public static final ColorS LIME_GREEN = new ColorS(0x32CD32);

	/**
	 * Color constant for the CSS3 color definition "Linen"
	 */
	public static final ColorS LINEN = new ColorS(0xFAF0E6);

	/**
	 * Color constant for the CSS3 color definition "Magenta"
	 */
	public static final ColorS MAGENTA = new ColorS(0xFF00FF);

	/**
	 * Color constant for the CSS3 color definition "Maroon"
	 */
	public static final ColorS MAROON = new ColorS(0x800000);

	/**
	 * Color constant for the CSS3 color definition "Medium AquaMarine"
	 */
	public static final ColorS MEDIUM_AQUA_MARINE = new ColorS(0x66CDAA);

	/**
	 * Color constant for the CSS3 color definition "Medium Blue"
	 */
	public static final ColorS MEDIUM_BLUE = new ColorS(0x0000CD);

	/**
	 * Color constant for the CSS3 color definition "Medium Orchid"
	 */
	public static final ColorS MEDIUM_ORCHID = new ColorS(0xBA55D3);

	/**
	 * Color constant for the CSS3 color definition "Medium Purple"
	 */
	public static final ColorS MEDIUM_PURPLE = new ColorS(0x9370DB);

	/**
	 * Color constant for the CSS3 color definition "Medium Sea Green"
	 */
	public static final ColorS MEDIUM_SEA_GREEN = new ColorS(0x3CB371);

	/**
	 * Color constant for the CSS3 color definition "Medium Slate Blue"
	 */
	public static final ColorS MEDIUM_SLATE_BLUE = new ColorS(0x7B68EE);

	/**
	 * Color constant for the CSS3 color definition "Medium Spring Green"
	 */
	public static final ColorS MEDIUM_SPRING_GREEN = new ColorS(0x00FA9A);

	/**
	 * Color constant for the CSS3 color definition "Medium Turquiose"
	 */
	public static final ColorS MEDIUM_TURQUOISE = new ColorS(0x48D1CC);

	/**
	 * Color constant for the CSS3 color definition "Medium Violet Red"
	 */
	public static final ColorS MEDIUM_VIOLET_RED = new ColorS(0xC71585);

	/**
	 * Color constant for the CSS3 color definition "Midnight Blue"
	 */
	public static final ColorS MIDNIGHT_BLUE = new ColorS(0x191970);

	/**
	 * Color constant for the CSS3 color definition "Mint Cream"
	 */
	public static final ColorS MINT_CREAM = new ColorS(0xF5FFFA);

	/**
	 * Color constant for the CSS3 color definition "Misty Rose"
	 */
	public static final ColorS MISTY_ROSE = new ColorS(0xFFE4E1);

	/**
	 * Color constant for the CSS3 color definition "Moccasin"
	 */
	public static final ColorS MOCCASIN = new ColorS(0xFFE4B5);

	/**
	 * Color constant for the CSS3 color definition "Navajo White"
	 */
	public static final ColorS NAVAJO_WHITE = new ColorS(0xFFDEAD);

	/**
	 * Color constant for the CSS3 color definition "Navy"
	 */
	public static final ColorS NAVY = new ColorS(0x000080);

	/**
	 * Color constant for the CSS3 color definition "Old Lace"
	 */
	public static final ColorS OLD_LACE = new ColorS(0xFDF5E6);

	/**
	 * Color constant for the CSS3 color definition "Olive"
	 */
	public static final ColorS OLIVE = new ColorS(0x808000);

	/**
	 * Color constant for the CSS3 color definition "Olive Drab"
	 */
	public static final ColorS OLIVE_DRAB = new ColorS(0x6B8E23);

	/**
	 * Color constant for the CSS3 color definition "Orange"
	 */
	public static final ColorS ORANGE = new ColorS(0xFFA500);

	/**
	 * Color constant for the CSS3 color definition "Orange Red"
	 */
	public static final ColorS ORANGE_RED = new ColorS(0xFF4500);

	/**
	 * Color constant for the CSS3 color definition "Orchid"
	 */
	public static final ColorS ORCHID = new ColorS(0xDA70D6);

	/**
	 * Color constant for the CSS3 color definition "Pale Golden Rod"
	 */
	public static final ColorS PALE_GOLDEN_ROD = new ColorS(0xEEE8AA);

	/**
	 * Color constant for the CSS3 color definition "Pale Green"
	 */
	public static final ColorS PALE_GREEN = new ColorS(0x98FB98);

	/**
	 * Color constant for the CSS3 color definition "Pale Turquoise"
	 */
	public static final ColorS PALE_TURQUOISE = new ColorS(0xAFEEEE);

	/**
	 * Color constant for the CSS3 color definition "Pale Violet Red"
	 */
	public static final ColorS PALE_VIOLET_RED = new ColorS(0xDB7093);

	/**
	 * Color constant for the CSS3 color definition "Papaya Whip"
	 */
	public static final ColorS PAPAYA_WHIP = new ColorS(0xFFEFD5);

	/**
	 * Color constant for the CSS3 color definition "Peach Puff"
	 */
	public static final ColorS PEACH_PUFF = new ColorS(0xFFDAB9);

	/**
	 * Color constant for the CSS3 color definition "Peru"
	 */
	public static final ColorS PERU = new ColorS(0xCD853F);

	/**
	 * Color constant for the CSS3 color definition "Pink"
	 */
	public static final ColorS PINK = new ColorS(0xFFC0CB);

	/**
	 * Color constant for the CSS3 color definition "Plum"
	 */
	public static final ColorS PLUM = new ColorS(0xDDA0DD);

	/**
	 * Color constant for the CSS3 color definition "Powder Blue"
	 */
	public static final ColorS POWDER_BLUE = new ColorS(0xB0E0E6);

	/**
	 * Color constant for the CSS3 color definition "Purple"
	 */
	public static final ColorS PURPLE = new ColorS(0x800080);

	/**
	 * Color constant for the CSS3 color definition "Rebecca Purple"
	 */
	public static final ColorS REBECCA_PURPLE = new ColorS(0x663399);

	/**
	 * Color constant for the CSS3 color definition "Red"
	 */
	public static final ColorS RED = new ColorS(0xFF0000);

	/**
	 * Color constant for the CSS3 color definition "Rosy Brown"
	 */
	public static final ColorS ROSY_BROWN = new ColorS(0xBC8F8F);

	/**
	 * Color constant for the CSS3 color definition "Royal Blue"
	 */
	public static final ColorS ROYAL_BLUE = new ColorS(0x4169E1);

	/**
	 * Color constant for the CSS3 color definition "Saddle Brown"
	 */
	public static final ColorS SADDLE_BROWN = new ColorS(0x8B4513);

	/**
	 * Color constant for the CSS3 color definition "Salmon"
	 */
	public static final ColorS SALMON = new ColorS(0xFA8072);

	/**
	 * Color constant for the CSS3 color definition "Sandy Brown"
	 */
	public static final ColorS SANDY_BROWN = new ColorS(0xF4A460);

	/**
	 * Color constant for the CSS3 color definition "Sea Green"
	 */
	public static final ColorS SEA_GREEN = new ColorS(0x2E8B57);

	/**
	 * Color constant for the CSS3 color definition "Sea Shell"
	 */
	public static final ColorS SEA_SHELL = new ColorS(0xFFF5EE);

	/**
	 * Color constant for the CSS3 color definition "Sienna"
	 */
	public static final ColorS SIENNA = new ColorS(0xA0522D);

	/**
	 * Color constant for the CSS3 color definition "Silver"
	 */
	public static final ColorS SILVER = new ColorS(0xC0C0C0);

	/**
	 * Color constant for the CSS3 color definition "Sky Blue"
	 */
	public static final ColorS SKY_BLUE = new ColorS(0x87CEEB);

	/**
	 * Color constant for the CSS3 color definition "Slate Blue"
	 */
	public static final ColorS SLATE_BLUE = new ColorS(0x6A5ACD);

	/**
	 * Color constant for the CSS3 color definition "Slate Gray"
	 */
	public static final ColorS SLATE_GRAY = new ColorS(0x708090);

	/**
	 * Color constant for the CSS3 color definition "Snow"
	 */
	public static final ColorS SNOW = new ColorS(0xFFFAFA);

	/**
	 * Color constant for the CSS3 color definition "Spring Green"
	 */
	public static final ColorS SPRING_GREEN = new ColorS(0x00FF7F);

	/**
	 * Color constant for the CSS3 color definition "Steel Blue"
	 */
	public static final ColorS STEEL_BLUE = new ColorS(0x4682B4);

	/**
	 * Color constant for the CSS3 color definition "Tan"
	 */
	public static final ColorS TAN = new ColorS(0xD2B48C);

	/**
	 * Color constant for the CSS3 color definition "Teal"
	 */
	public static final ColorS TEAL = new ColorS(0x008080);

	/**
	 * Color constant for the CSS3 color definition "Thistle"
	 */
	public static final ColorS THISTLE = new ColorS(0xD8BFD8);

	/**
	 * Color constant for the CSS3 color definition "Tomato"
	 */
	public static final ColorS TOMATO = new ColorS(0xFF6347);

	/**
	 * Color constant for the CSS3 color definition "Turquoise"
	 */
	public static final ColorS TURQUOISE = new ColorS(0x40E0D0);

	/**
	 * Color constant for the CSS3 color definition "Violet"
	 */
	public static final ColorS VIOLET = new ColorS(0xEE82EE);

	/**
	 * Color constant for the CSS3 color definition "Wheat"
	 */
	public static final ColorS WHEAT = new ColorS(0xF5DEB3);

	/**
	 * Color constant for the CSS3 color definition "White"
	 */
	public static final ColorS WHITE = new ColorS(0xFFFFFF);

	/**
	 * Color constant for the CSS3 color definition "White Smoke"
	 */
	public static final ColorS WHITE_SMOKE = new ColorS(0xF5F5F5);

	/**
	 * Color constant for the CSS3 color definition "Yellow"
	 */
	public static final ColorS YELLOW = new ColorS(0xFFFF00);

	/**
	 * Color constant for the CSS3 color definition "Yellow Green"
	 */
	public static final ColorS YELLOW_GREEN = new ColorS(0x9ACD32);

	/**
	 * Inherited from Java's {@code Color} class
	 * 
	 * @see java.awt.Color
	 */
	public ColorS(int r, int g, int b) {
		super(r, g, b);
	}

	/**
	 * Inherited from Java's {@code Color} class
	 * 
	 * @see java.awt.Color
	 */
	public ColorS(int r, int g, int b, int a) {
		super(r, g, b, a);
	}

	/**
	 * Inherited from Java's {@code Color} class
	 * 
	 * @see java.awt.Color
	 */
	public ColorS(int rgb) {
		super(rgb);
	}

	/**
	 * Inherited from Java's {@code Color} class
	 * 
	 * @see java.awt.Color
	 */
	public ColorS(int rgba, boolean hasalpha) {
		super(rgba, hasalpha);
	}

	/**
	 * Inherited from Java's {@code Color} class
	 * 
	 * @see java.awt.Color
	 */
	public ColorS(float r, float g, float b) {
		super(r, g, b);
	}

	/**
	 * Inherited from Java's {@code Color} class
	 * 
	 * @see java.awt.Color
	 */
	public ColorS(float r, float g, float b, float a) {
		super(r, g, b, a);
	}

	/**
	 * Inherited from Java's {@code Color} class
	 * 
	 * @see java.awt.Color
	 */
	public ColorS(ColorSpace cspace, float components[], float alpha) {
		super(cspace, components, alpha);
	}

	public static int mergeARGB(int a, int r, int g, int b) {
		return ((a & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | ((b & 0xFF) << 0);
	}

	public static int[] splitARGB(int argb) {
		int[] components = new int[4];

		components[0] = (argb >> 24) & 0xff;
		components[1] = (argb >> 16) & 0xff;
		components[2] = (argb >> 8) & 0xff;
		components[3] = argb & 0xff;

		return components;
	}

	/**
	 * Creates a new {@code ColorS}, based on the values of this {@code ColorS},
	 * that tends more towards white as {@code factor} reaches 1f
	 *
	 * @param factor
	 *            A value between 0 and 1 inclusive
	 * 
	 * @return A color that tends towards white as {@code factor} reaches 1
	 */
	public ColorS brighter(float factor) {
		int r = getRed();
		int g = getGreen();
		int b = getBlue();

		factor = clamp(factor, 0f, 1f);

		r += (255 - r) * factor;
		g += (255 - g) * factor;
		b += (255 - b) * factor;

		return new ColorS(r, g, b, getAlpha());
	}

	/**
	 * Creates a new {@code ColorS}, based on the values of this {@code ColorS},
	 * that tends more towards black as {@code factor} reaches 1f
	 *
	 * @param factor
	 *            A value between 0 and 1 inclusive
	 * 
	 * @return A color that tends towards black as {@code factor} reaches 1
	 */
	public ColorS darker(float factor) {
		int r = getRed();
		int g = getGreen();
		int b = getBlue();

		factor = clamp(factor, 0f, 1f);

		r -= r * factor;
		g -= g * factor;
		b -= b * factor;

		return new ColorS(r, g, b, getAlpha());
	}

	/**
	 * Creates a new {@code ColorS}, based on the values of this {@code ColorS},
	 * whose values tend more towards an average gray as {@code factor} reaches
	 * 1f
	 *
	 * @param factor
	 *            A value between 0 and 1 inclusive
	 * 
	 * @return A color whose values tend towards average gray as {@code factor}
	 *         reaches 1
	 */
	public ColorS desaturated(float factor) {
		int r = getRed();
		int g = getGreen();
		int b = getBlue();

		int avg = (int) average((float) r, (float) g, (float) b);

		int rDiff = avg - r;
		int gDiff = avg - g;
		int bDiff = avg - b;

		factor = clamp(factor, 0f, 1f);

		r += rDiff * factor;
		g += gDiff * factor;
		b += bDiff * factor;

		return new ColorS(r, g, b, getAlpha());
	}

	/**
	 * Creates a new {@code ColorS}, based on the values of this {@code ColorS},
	 * whose RGB values tend more towards the nearest extreme value (0, 255) as
	 * {@code factor} reaches 1f
	 *
	 * @param factor
	 *            A value between 0 and 1 inclusive
	 * 
	 * @return A color whose RGB values tend more towards the nearest extreme
	 *         value as {@code factor} reaches 1
	 */
	public ColorS oversaturated(float factor) {
		int r = getRed();
		int g = getGreen();
		int b = getBlue();

		int rLim = r >= 128 ? 255 : 0;
		int gLim = g >= 128 ? 255 : 0;
		int bLim = b >= 128 ? 255 : 0;

		int rDiff = rLim - r;
		int gDiff = gLim - g;
		int bDiff = bLim - b;

		factor = clamp(factor, 0f, 1f);

		r += rDiff * factor;
		g += gDiff * factor;
		b += bDiff * factor;

		return new ColorS(r, g, b, getAlpha());
	}

	/**
	 * Creates a new {@code ColorS}, based on the values of this {@code ColorS},
	 * whose RGB values are inverted
	 * 
	 * @return The inverse of this color
	 */
	public ColorS inverted() {
		return new ColorS(255 - getRed(), 255 - getGreen(), 255 - getBlue(), getAlpha());
	}

	/**
	 * Creates a new {@code ColorS}, based on the values of this {@code ColorS},
	 * whose RGB values are set to the average of the three
	 * 
	 * @return The average grayscale color
	 */
	public ColorS grayscale() {
		return desaturated(1f);
	}

	/**
	 * Creates a new {@code ColorS}, based on the values of this {@code ColorS},
	 * whose RGB values are a percentage of the original values as specified by
	 * {@code factorR}, {@code factorG}, and {@code factorB}
	 * 
	 * @param factorR
	 *            Amount of original red in color
	 * @param factorG
	 *            Amount of original green in color
	 * @param factorB
	 *            Amount of original blue in color
	 * 
	 * @return A level-adjusted {@code ColorS}
	 */
	public ColorS adjusted(float factorR, float factorG, float factorB) {
		int r = getRed();
		int g = getGreen();
		int b = getBlue();

		factorR = clamp(factorR, 0f, 1f);
		factorG = clamp(factorG, 0f, 1f);
		factorB = clamp(factorB, 0f, 1f);

		r *= factorR;
		g *= factorG;
		b *= factorB;

		return new ColorS(r, g, b, getAlpha());
	}

	/**
	 * Creates a new {@code ColorS}, based on the values of this {@code ColorS},
	 * with an alpha value as specified by {@code factor}
	 * 
	 * @param factor
	 *            The alpha of the new {@code ColorS}
	 * 
	 * @return A transparent version of this {@code ColorS}
	 */
	public ColorS transparent(float factor) {
		factor = clamp(factor, 0f, 1f);

		int a = (int) (getAlpha() * factor);

		return new ColorS(getRed(), getGreen(), getBlue(), a);
	}

	/**
	 * @return An HTML/CSS compatible hex value for this {@code ColorS}
	 */
	public String toHex() {
		return "#" + Integer.toHexString(getRGB()).toUpperCase();
	}
}