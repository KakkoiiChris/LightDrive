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
package net.alexanderdev.lightdrive.util.math.geom.path;

/**
 * A {@link FunctionalInterface} which defines an algorithm to be used with
 * easing motions.
 * 
 * @author Christian Bryce Alexander
 * @since May 28, 2016, 4:01:30 AM
 */
@FunctionalInterface
public interface EaseOp {
	/**
	 * Calculates the value at a specific time during an easing motion.
	 * 
	 * @param t
	 *            The current time (in any unit)
	 * @param d
	 *            The duration of motion (in the same unit as t)
	 * @param s
	 *            The start value being eased
	 * @param c
	 *            The difference between the start and end values
	 * @return The value at the motion at the given time
	 */
	public double ease(double t, double d, double s, double c);
}