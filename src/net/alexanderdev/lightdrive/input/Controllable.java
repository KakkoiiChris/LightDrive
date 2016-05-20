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
package net.alexanderdev.lightdrive.input;

import net.alexanderdev.lightdrive.input.gamepad.Gamepad;
import net.alexanderdev.lightdrive.input.keyboard.Keyboard;
import net.alexanderdev.lightdrive.input.mouse.Mouse;
import net.alexanderdev.lightdrive.view.Viewable;

/**
 * An interface which combines all three input listeners into one.
 * 
 * @author Christian Bryce Alexander
 * @since Sep 15, 2015, 11:26:38 AM
 */
public interface Controllable {
	/**
	 * Enables direct use of the {@link Viewable}'s keyboard.
	 * 
	 * @param keyboard
	 *            The {@link Viewable}'s keyboard
	 */
	public void keyboardInput(Keyboard keyboard);

	/**
	 * Enables direct use of the {@link Viewable}'s mouse.
	 * 
	 * @param mouse
	 *            The {@link Viewable}'s mouse
	 */
	public void mouseInput(Mouse mouse);

	/**
	 * Enables direct use of the {@link Viewable}'s game pads
	 * 
	 * @param gamepad
	 *            A gamepad from the {@link Viewable}
	 */
	public void gamepadInput(Gamepad gamepad);
}