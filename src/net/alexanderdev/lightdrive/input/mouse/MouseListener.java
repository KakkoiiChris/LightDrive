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
package net.alexanderdev.lightdrive.input.mouse;

import net.alexanderdev.lightdrive.state.State;
import net.alexanderdev.lightdrive.view.Viewable;

/**
 * Interface to be used in conjunction with a {@link State} to handle
 * {@link Mouse} input.
 * 
 * @author Christian Bryce Alexander
 * @since March 8, 2015, 1:15:57 AM
 */
@FunctionalInterface
public interface MouseListener {
	/**
	 * Enables direct use of the {@link Viewable}'s mouse.
	 * 
	 * @param mouse
	 *            The {@link Viewable}'s mouse
	 */
	public void mouseInput(Mouse mouse);
}