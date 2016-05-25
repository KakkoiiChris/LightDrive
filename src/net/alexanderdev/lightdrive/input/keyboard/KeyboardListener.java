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
package net.alexanderdev.lightdrive.input.keyboard;

import net.alexanderdev.lightdrive.state.State;
import net.alexanderdev.lightdrive.view.Viewable;

/**
 * Interface to be used in conjunction with a {@link State} to handle
 * {@link Keyboard} input.
 * 
 * @author Christian Bryce Alexander
 * @since March 8, 2015, 1:15:17 AM
 */
@FunctionalInterface
public interface KeyboardListener {
	/**
	 * Enables direct use of the {@link Viewable}'s keyboard.
	 * 
	 * @param keyboard
	 *            The {@link Viewable}'s keyboard
	 */
	public void keyboardInput(Keyboard keyboard);
}