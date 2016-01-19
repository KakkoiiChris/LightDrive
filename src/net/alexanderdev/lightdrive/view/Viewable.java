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

import net.alexanderdev.lightdrive.InternalType;
import net.alexanderdev.lightdrive.graphics.filters.FilterS;
import net.alexanderdev.lightdrive.state.StateManager;

/**
 * @author Christian Bryce Alexander
 * @since Jan 19, 2016, 2:03:22 AM
 */
@InternalType
public interface Viewable {
	/**
	 * @return The {@link StateManager} associated with this game
	 *         {@code Viewable}
	 */
	public StateManager getManager();

	/**
	 * Associates a new or custom {@link StateManager} with this game
	 * {@code Viewable}.
	 *
	 * @param manager
	 *            The {@link StateManager} to associate
	 */
	public void setManager(StateManager manager);

	/**
	 * @return A {@link Rectangle} starting at the origin, with the unscaled
	 *         dimensions of this {@code Viewable}
	 */
	public Rectangle getViewBounds();

	/**
	 * @return The unscaled width of this {@code Viewable}
	 */
	public int getViewWidth();

	/**
	 * @return The unscaled height of this {@code Viewable}
	 */
	public int getViewHeight();

	/**
	 * @return The scale factor of this {@code Viewable}
	 */
	public int getViewScale();

	/**
	 * Prepares this {@code Viewable}'s graphics and opens it up for gameplay.
	 */
	public void open();

	/**
	 * Cleans up any graphics resources associated with this {@code Viewable},
	 * closes it, and terminates the program.
	 */
	public void close();

	/**
	 * Adds a {@link FilterS} that will be applied to this {@code Viewable}'s
	 * context after rendering.
	 * 
	 * @param filter
	 *            The filter to add
	 */
	public void addFilter(FilterS filter);

	/**
	 * Removes a {@link FilterS} from being applied to this {@code Viewable}'s
	 * context after rendering.
	 * 
	 * @param filter
	 */
	public void removeFilter(FilterS filter);

	/**
	 * Clears all added {@link FilterS}'s from this {@code Viewable}.
	 */
	public void clearFilters();
}