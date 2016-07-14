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
package net.alexanderdev.lightdrive.util.time;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Christian Bryce Alexander
 * @since Jun 8, 2016, 4:21:27 PM
 */
public final class Stopwatch {
	private static final Map<String, Long> DURATIONS;
	private static final Map<String, Long> TIMERS;

	static {
		DURATIONS = new HashMap<>();
		TIMERS    = new HashMap<>();
	}

	public static void add(String name, long duration) {
		DURATIONS.put(name, duration);
	}

	public static void remove(String name) {
		DURATIONS.remove(name);
	}

	public static void start(String name) {
		reset(name);
	}

	public static void stop(String name) {
		TIMERS.remove(name);
	}

	public static void reset(String name) {
		TIMERS.put(name, Time.msTime());
	}

	public static boolean hasElapsed(String name) {
		return elapseCount(name) > 0;
	}

	public static int elapseCount(String name) {
		if (TIMERS.get(name) != null)
			return (int) ((Time.msTime() - TIMERS.get(name)) / DURATIONS.get(name));

		return -1;
	}

	public static void clear() {
		DURATIONS.clear();
		TIMERS.clear();
	}
}