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
 *  COPYRIGHT � 2015, Christian Bryce Alexander            *
 ***********************************************************/
package net.alexanderdev.lightdrive.util;

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import net.alexanderdev.lightdrive.view.Display;
import net.alexanderdev.lightdrive.view.Window;

/**
 * A collection of static methods for retrieving information about any physical
 * displays connected to your system.
 * 
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

	/**
	 * @return The width of the default display
	 */
	public static int getPhysicalWidth() {
		return defaultDisplayMode.getWidth();
	}

	/**
	 * @return The height of the default display
	 */
	public static int getPhysicalHeight() {
		return defaultDisplayMode.getHeight();
	}

	/**
	 * @return The dimensions of the default display
	 */
	public static Dimension getPhysicalSize() {
		return new Dimension(defaultDisplayMode.getWidth(), defaultDisplayMode.getHeight());
	}

	/**
	 * @return The bit depth of the default display
	 */
	public static int getPhysicalBitDepth() {
		return defaultDisplayMode.getBitDepth();
	}

	/**
	 * @return The refresh rate of the default display
	 */
	public static int getPhysicalRefreshRate() {
		return defaultDisplayMode.getRefreshRate();
	}

	/**
	 * @return The number of the displays currently connected
	 */
	public static int getPhysicalDeviceCount() {
		return graphicsDevices.length;
	}

	/**
	 * @return The width of the nth display
	 */
	public static int getPhysicalWidth(int n) {
		return getDisplayMode(n).getWidth();
	}

	/**
	 * @return The height of the nth display
	 */
	public static int getPhysicalHeight(int n) {
		return getDisplayMode(n).getHeight();
	}

	/**
	 * @return The dimensions of the nth display
	 */
	public static Dimension getPhysicalSize(int n) {
		return new Dimension(getDisplayMode(n).getWidth(), getDisplayMode(n).getHeight());
	}

	/**
	 * @return The bit depth of the nth display
	 */
	public static int getPhysicalBitDepth(int n) {
		return getDisplayMode(n).getBitDepth();
	}

	/**
	 * @return The refresh rate of the nth display
	 */
	public static int getPhysicalRefreshRate(int n) {
		return getDisplayMode(n).getRefreshRate();
	}

	/**
	 * @return The width of the nth display
	 */
	private static DisplayMode getDisplayMode(int n) {
		return graphicsDevices[n].getDisplayMode();
	}

	/**
	 * @return The maximum {@link Display}/{@link Window} scale so that it will fit on the
	 *         current monitor.
	 */
	public static int getMaximumScale(int width, int height) {
		int pw = getPhysicalWidth();
		int ph = getPhysicalHeight();

		int scale = 1;

		while (width * scale < pw && height * scale < ph) {
			scale++;
		}
		scale -= 2;

		return scale;
	}
}