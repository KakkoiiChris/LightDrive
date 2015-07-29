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
package net.alexanderdev.lightdrive.exception;

/**
 * @author Christian Bryce Alexander
 * @since Jul 28, 2015 | 12:32:28 AM
 */
public class SpriteOutOfBoundsException extends SpriteSheetException {
	private static final long serialVersionUID = 2942058005164028876L;

	/**
	 * @param message
	 */
	public SpriteOutOfBoundsException(int x, int y, String coord) {
		super(String.format("Sprite at (%d, %d) is out of bounds. Invalid value for %s.", x, y, coord));
	}
}