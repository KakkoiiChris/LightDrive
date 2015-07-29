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
 * @author Christian Bryce Alexander
 * @since Jun 5, 2015 | 1:27:45 AM
 */
public final class Time {
	public static enum Unit {
		MS,
		NS
	}

	private Unit unit;
	private long first;
	private long last;

	public Time(Unit unit) {
		this.unit = unit;
	}

	public static long msTime() {
		return System.currentTimeMillis();
	}

	public static long nsTime() {
		return System.nanoTime();
	}

	public void start() {
		if (unit == Unit.MS)
			first = msTime();
		else if (unit == Unit.NS)
			first = nsTime();
	}

	public void stop() {
		if (unit == Unit.MS)
			last = msTime();
		else if (unit == Unit.NS)
			last = nsTime();
	}

	public long getDuration() {
		return last - first;
	}
}