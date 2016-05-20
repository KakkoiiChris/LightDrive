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

import net.alexanderdev.lightdrive.util.ArraysX;
import net.alexanderdev.lightdrive.util.math.MathX;

/**
 * An enumeration of most standard gray-scale modes, and their respective
 * algorithms, in the form of {@link GrayscaleOp}s.
 * 
 * @author Christian Bryce Alexander
 * @since Jan 19, 2016, 12:30:37 PM
 */
public enum GrayscaleMode {
	/**
	 * Averages all three channels together.
	 */
	AVERAGE      ((r, g, b) -> (int) MathX.average(r, g, b)),
	/**
	 * Just the red channel.
	 */
	CHANNEL_RED  ((r, g, b) -> r),
	/**
	 * Just the green channel.
	 */
	CHANNEL_GREEN((r, g, b) -> g),
	/**
	 * Just the blue channel.
	 */
	CHANNEL_BLUE ((r, g, b) -> b),
	/**
	 * Averages the minimum and maximum channels together.
	 */
	LIGHTNESS    ((r, g, b) -> (int) MathX.average(MathX.min(r, g, b), MathX.max(r, g, b))),
	/**
	 * Sums percentages of each channel together based on how the human eye
	 * perceives color.
	 */
	LUMINOSITY   ((r, g, b) -> (int) MathX.clamp((r * 0.2126f) + (g * 0.7152f) + (b * 0.0722f), 0, 255)),
	/**
	 * Just the maximum channel.
	 */
	MAX_DECOMP   ((r, g, b) -> MathX.max(r, g, b)),
	/**
	 * Just the middle channel.
	 */
	MID_DECOMP   ((r, g, b) -> ArraysX.sort(r, g, b)[1]),
	/**
	 * Just the minimum channel.
	 */
	MIN_DECOMP   ((r, g, b) -> MathX.min(r, g, b));

	private GrayscaleOp operation;

	private GrayscaleMode(GrayscaleOp operation) {
		this.operation = operation;
	}

	/**
	 * @return The {@link GrayscaleOp} associated with this standard
	 *         {@code GrayscaleMode}
	 */
	public GrayscaleOp getOperation() {
		return operation;
	}
}