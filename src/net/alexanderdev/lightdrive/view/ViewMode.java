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
package net.alexanderdev.lightdrive.view;

/**
 * A class which encapsulates information about resolution and update rate for
 * use with any {@link Viewable}.
 * 
 * @author Christian Bryce Alexander
 * @since May 19, 2016, 8:14:47 PM
 */
public class ViewMode {
	private int width, height;
	private double ups;

	/**
	 * Creates a new {@link ViewMode} with the specified parameters.
	 *
	 * @param width
	 *            The width of this {@link ViewMode}
	 * @param height
	 *            The height of this {@link ViewMode}
	 * @param ups
	 *            The update rate of this {@link ViewMode}
	 */
	public ViewMode(int width, int height, double ups) {
		this.width = width;
		this.height = height;
		this.ups = ups;
	}

	/**
	 * @return The width of this {@link ViewMode}
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return The height of this {@link ViewMode}
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return The update rate of this {@link ViewMode}
	 */
	public double getUPS() {
		return ups;
	}
}