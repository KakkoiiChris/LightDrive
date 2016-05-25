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
package net.alexanderdev.lightdrive.audio;

import javax.sound.sampled.Clip;

/**
 * An interface which enables code based on a sound clip's position, in order to
 * simulate reactionary code.
 * 
 * @author Christian Bryce Alexander
 * @since Nov 11, 2015, 2:23:23 PM
 */
@FunctionalInterface
public interface SoundListener {
	/**
	 * Performs actions based on the current position of a {@link Sound}'s
	 * {@link Clip}.
	 * 
	 * @param position
	 *            The position in the sound clip, from 0f being the beginning,
	 *            to 1f being the end
	 */
	public void listen(float position);
}