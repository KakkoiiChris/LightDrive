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

import java.io.PrintStream;
import java.util.Locale;

/**
 * @author Christian Bryce Alexander
 * @since Jun 12, 2016, 12:37:25 AM
 */
public class Debug {
	private static boolean enabled = false;

	public static void enable() {
		enabled = true;
	}
	
	public static boolean isEnabled() {
		return enabled;
	}

	public static void print(boolean b) {
		if (enabled)
			System.out.print(b);
	}

	public static void print(char c) {
		if (enabled)
			System.out.print(c);
	}

	public static void print(int i) {
		if (enabled)
			System.out.print(i);
	}

	public static void print(long l) {
		if (enabled)
			System.out.print(l);
	}

	public static void print(float f) {
		if (enabled)
			System.out.print(f);
	}

	public static void print(double d) {
		if (enabled)
			System.out.print(d);
	}

	public static void print(char s[]) {
		if (enabled)
			System.out.print(s);
	}

	public static void print(String s) {
		if (enabled)
			System.out.print(s);
	}

	public static void print(Object obj) {
		if (enabled)
			System.out.print(obj);
	}

	public static void newLine() {
		if (enabled)
			System.out.println();
	}

	public static void printLine(boolean x) {
		if (enabled)
			System.out.println(x);
	}

	public static void printLine(char x) {
		if (enabled)
			System.out.println(x);
	}

	public static void printLine(int x) {
		if (enabled)
			System.out.println(x);
	}

	public static void printLine(long x) {
		if (enabled)
			System.out.println(x);
	}

	public static void printLine(float x) {
		if (enabled)
			System.out.println(x);
	}

	public static void printLine(double x) {
		if (enabled)
			System.out.println(x);
	}

	public static void printLine(char x[]) {
		if (enabled)
			System.out.println(x);
	}

	public static void printLine(String x) {
		if (enabled)
			System.out.println(x);
	}

	public static void printLine(Object x) {
		if (enabled)
			System.out.println(x);
	}

	public static PrintStream printFormatted(String format, Object... args) {
		if (enabled)
			return System.out.printf(format, args);
		return null;
	}

	public static PrintStream printFormatted(Locale l, String format, Object... args) {
		if (enabled)
			return System.out.printf(l, format, args);
		return null;
	}
}