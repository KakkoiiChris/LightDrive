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

import net.alexanderdev.lightdrive.Internal;

/**
 * @author Christian Bryce Alexander
 * @since Jul 28, 2015 | 12:16:02 AM
 */
public class OversizedSpriteException extends SpriteSheetException {
	private static final long serialVersionUID = -5392836525940268050L;

	/**
	 * Exception thrown when the parameters sent to the spritesheet constructor
	 * would produce sprites that would be too big to fit inside the provided
	 * image.
	 * 
	 * @param message
	 */
	@Internal
	public OversizedSpriteException(String dim, int value) {
		super(String.format("This sprite sheet is too small to fit sprites of %s %d.", dim, value));
	}
}