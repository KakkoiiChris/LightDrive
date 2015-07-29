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
 * @since Jul 28, 2015 | 12:25:47 AM
 */
public class UnevenSpriteDivisionException extends SpriteSheetException {
	private static final long serialVersionUID = -6420195603381235570L;

	/**
	 * @param message
	 */
	public UnevenSpriteDivisionException(String dim, int value, int limit) {
		super(String.format("Sprite %s %d does not divide evenly into sprite sheet of %s %d.", dim, value, dim, limit));
	}
}