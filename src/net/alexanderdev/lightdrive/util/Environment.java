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

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

/**
 * @author Christian Bryce Alexander
 * @since Nov 16, 2015, 9:04:10 PM
 */
public class Environment {
	private static GraphicsEnvironment graphicsEnvironment;
	private static GraphicsDevice[] graphicsDevices;
	private static DisplayMode defaultDisplayMode;

	static {
		graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		graphicsDevices = graphicsEnvironment.getScreenDevices();
		defaultDisplayMode = graphicsEnvironment.getDefaultScreenDevice().getDisplayMode();
	}

	public static int getPhysicalWidth() {
		return defaultDisplayMode.getWidth();
	}

	public static int getPhysicalHeight() {
		return defaultDisplayMode.getHeight();
	}

	public static Dimension getPhysicalSize() {
		return new Dimension(defaultDisplayMode.getWidth(), defaultDisplayMode.getHeight());
	}

	public static int getPhysicalBitDepth() {
		return defaultDisplayMode.getBitDepth();
	}

	public static int getPhysicalRefreshRate() {
		return defaultDisplayMode.getRefreshRate();
	}

	public static int getPhysicalDeviceCount() {
		return graphicsDevices.length;
	}

	public static int getPhysicalWidth(int n) {
		return getDisplayMode(n).getWidth();
	}

	public static int getPhysicalHeight(int n) {
		return getDisplayMode(n).getHeight();
	}

	public static Dimension getPhysicalSize(int n) {
		return new Dimension(getDisplayMode(n).getWidth(), getDisplayMode(n).getHeight());
	}

	public static int getPhysicalBitDepth(int n) {
		return getDisplayMode(n).getBitDepth();
	}

	public static int getPhysicalRefreshRate(int n) {
		return getDisplayMode(n).getRefreshRate();
	}

	private static DisplayMode getDisplayMode(int n) {
		return graphicsDevices[n].getDisplayMode();
	}
}