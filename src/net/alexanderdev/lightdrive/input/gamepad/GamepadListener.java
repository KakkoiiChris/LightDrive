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
package net.alexanderdev.lightdrive.input.gamepad;

import net.alexanderdev.lightdrive.state.State;
import net.alexanderdev.lightdrive.view.Viewable;

/**
 * Interface to be used in conjunction with a {@link State} to handle
 * {@link Gamepad} input.
 * 
 * @author Christian Bryce Alexander
 * @since Apr 29, 2015 | 9:12:12 PM
 */
@FunctionalInterface
public interface GamepadListener {
	/**
	 * Enables direct use of the {@link Viewable}'s game pads
	 * 
	 * @param gamepad
	 *            A gamepad from the {@link Viewable}
	 */
	public void gamepadInput(Gamepad gamepad);
}