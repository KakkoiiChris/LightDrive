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
package net.alexanderdev.lightdrive.graphics.filter;

import net.alexanderdev.lightdrive.graphics.Sprite;

/**
 * A {@link FunctionalInterface} which is used to filter a {@link Sprite}, and
 * defines the rule(s) for determining the values.
 * 
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 12:29:10 AM
 */
@FunctionalInterface
public interface Filter {
	/**
	 * Applies this {@code Filter}'s algorithm with the specified values.
	 * 
	 * @param pixels
	 *            The pixel data of the {@link Sprite} to be filtered
	 * @return The blended value of the channels
	 */
	public void apply(int width, int height, int[] pixels);
}