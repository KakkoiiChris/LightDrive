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
package net.alexanderdev.lightdrive.util;

/**
 * A collection of time keeping methods.
 * 
 * @author Christian Bryce Alexander
 * @since Jun 5, 2015 | 1:27:45 AM
 */
public final class Time {
	/**
	 * @return The current system time in milliseconds
	 */
	public static long msTime() {
		return System.currentTimeMillis();
	}

	/**
	 * @return The current system time in nanoseconds
	 */
	public static long nsTime() {
		return System.nanoTime();
	}
}