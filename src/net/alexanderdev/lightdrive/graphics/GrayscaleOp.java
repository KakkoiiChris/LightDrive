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
 * @author Christian Bryce Alexander
 * @since Apr 23, 2016, 8:58:27 PM
 */
public interface GrayscaleOp {
	/**
	 * Applies this {@code GrayscaleOperation}'s algorithm with the specified
	 * channels.
	 * 
	 * @param r
	 *            The red channel
	 * @param g
	 *            The green channel
	 * @param b
	 *            The blue channel
	 * @return The grayscale value of the channels
	 */
	public int apply(int r, int g, int b);
}