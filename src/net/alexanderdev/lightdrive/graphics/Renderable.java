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

import net.alexanderdev.lightdrive.view.Viewable;

/**
 * An interface that defines behaviors that any object that is visually
 * representable on a {@link Viewable} should implement.
 * 
 * @author Christian Bryce Alexander
 * @since Jun 11, 2015 | 2:49:57 AM
 */
public interface Renderable {
	/**
	 * Used to change the state of a renderable object.
	 * 
	 * @param delta
	 *            The amount of game loop cycles that have gone by since the
	 *            last cycle, 1.0 being a full cycle
	 */
	public void update(double delta);

	/**
	 * Used to draw a renderable object to the current {@link Viewable}.
	 * 
	 * @param g
	 *            The {@link GraphicsX} context used for rendering
	 */
	public void render(GraphicsX g);
}