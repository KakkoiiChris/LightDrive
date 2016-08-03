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

import java.awt.Rectangle;

import net.alexanderdev.lightdrive.Cleanable;
import net.alexanderdev.lightdrive.InternalType;
import net.alexanderdev.lightdrive.graphics.Sprite;
import net.alexanderdev.lightdrive.state.StateManager;

/**
 * An interface which defines the common methods that all standard game
 * {@link Viewable}s should have.
 * 
 * @author Christian Bryce Alexander
 * @since Jan 19, 2016, 2:03:22 AM
 */
@InternalType
public interface Viewable extends Cleanable {
	/**
	 * @return The {@link StateManager} associated with this game
	 *         {@link Viewable}
	 */
	public StateManager getManager();

	/**
	 * Associates a new or custom {@link StateManager} with this game
	 * {@link Viewable}.
	 *
	 * @param manager
	 *            The {@link StateManager} to associate
	 */
	public void setManager(StateManager manager);

	/**
	 * @return A {@link Rectangle} starting at the origin, with the unscaled
	 *         dimensions of this {@link Viewable}
	 */
	public Rectangle getViewBounds();

	/**
	 * @return The unscaled width of this {@link Viewable}
	 */
	public int getViewWidth();

	/**
	 * @return The unscaled height of this {@link Viewable}
	 */
	public int getViewHeight();

	/**
	 * @return The scale factor of this {@link Viewable}
	 */
	public int getViewScale();

	/**
	 * @return The {@link Sprite} context used for rendering
	 */
	public Sprite getContext();

	public int getUpdateCount();

	public int getFrameCount();

	/**
	 * Prepares this {@link Viewable}'s graphics and opens it up for game play.
	 */
	public void open();

	/**
	 * Cleans up any graphics resources associated with this {@link Viewable},
	 * closes it, and terminates the program.
	 */
	public void close();
}