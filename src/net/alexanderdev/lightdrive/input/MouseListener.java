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
 * {@code Mouse} input
 * 
 * @author Christian Bryce Alexander
 * @since March 8, 2015 | 1:15:57 AM
 */
@FunctionalInterface
public interface MouseListener {
	/**
	 * Enables direct use of the {@code Display}'s mouse
	 * 
	 * @param mouse
	 *            The {@code Display}'s mouse
	 */
	public void mouseInput(Mouse mouse);
}