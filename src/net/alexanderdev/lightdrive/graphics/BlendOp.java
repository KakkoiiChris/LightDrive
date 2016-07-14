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

/**
 * A {@link FunctionalInterface} which is used in color blend operations, and
 * defines the rule(s) for determining the values.
 * 
 * @author Christian Bryce Alexander
 * @since May 16, 2016, 6:47:09 PM
 */
@FunctionalInterface
public interface BlendOp {
	/**
	 * Applies this {@link BlendOp}'s algorithm with the specified values.
	 * 
	 * @param target
	 *            The channel to serve as the destination
	 * @param blend
	 *            The channel to blend into the target
	 * @return The blended value of the channels
	 */
	public float apply(float target, float blend);
}