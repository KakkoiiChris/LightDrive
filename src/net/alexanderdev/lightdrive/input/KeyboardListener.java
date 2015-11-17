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

/**
 * Interface to be used in conjunction with the {@code Display} to handle
 * {@code Keyboard} input
 * 
 * @author Christian Bryce Alexander
 * @since March 8, 2015 | 1:15:17 AM
 */
@FunctionalInterface
public interface KeyboardListener {
	/**
	 * Enables direct use of the {@code Display}'s keyboard
	 * 
	 * @param keyboard
	 *            The {@code Display}'s keyboard
	 */
	public void keyboardInput(Keyboard keyboard);
}