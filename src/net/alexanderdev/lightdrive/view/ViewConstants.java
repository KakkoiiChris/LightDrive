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
package net.alexanderdev.lightdrive.view;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A collection of values and objects that are common across all
 * {@link Viewable}s.
 * 
 * @author Christian Bryce Alexander
 * @since May 19, 2016, 7:36:04 PM
 */
public class ViewConstants {
	//////// VIEWABLE CONSTRUCTOR DEFAULTS////////
	/**
	 * The default width for a {@link Display} or {@link Window}.
	 */
	public static final int DEFAULT_WIDTH = 640;
	/**
	 * The default height for a {@link Display} or {@link Window}.
	 */
	public static final int DEFAULT_HEIGHT = 480;
	/**
	 * The default scale for a {@link Viewable}.
	 */
	public static final int DEFAULT_SCALE = 1;
	/**
	 * The max updates per second that a {@link Viewable} runs at.
	 */
	public static final double DEFAULT_UPS = 60.0;
	/**
	 * The default title for both {@link Display}s and {@link Screen}s.
	 */
	public static final String DEFAULT_TITLE = "LightDrive: 2D Java Game Library";
	/**
	 * The default icon for both {@link Display}s and {@link Screen}s.
	 */
	public static final Image DEFAULT_ICON = loadDefaultIcon();

	//////// VIEW CONFIG PRESETS////////
	/**
	 * A {@link ViewMode} that emulates the feel of the Nintendo Game Boy.
	 */
	public static final ViewMode NGB = new ViewMode(160, 144, 59.70);
	/**
	 * A {@link ViewMode} that emulates the feel of the Nintendo Game Boy
	 * Advance.
	 */
	public static final ViewMode GBA = new ViewMode(240, 160, 59.73);
	/**
	 * A {@link ViewMode} that emulates the feel of the Nintendo Entertainment
	 * System.
	 */
	public static final ViewMode NES = new ViewMode(256, 240, 59.71);

	//////// CURSORS////////
	/**
	 * The default system cursor.
	 */
	public static final Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);
	/**
	 * The crosshair cursor
	 */
	public static final Cursor CROSSHAIR_CURSOR = new Cursor(Cursor.CROSSHAIR_CURSOR);
	/**
	 * The loading cursor
	 */
	public static final Cursor WAIT_CURSOR = new Cursor(Cursor.WAIT_CURSOR);
	/**
	 * The hand cursor
	 */
	public static final Cursor HAND_CURSOR = new Cursor(Cursor.HAND_CURSOR);
	/**
	 * The movement cursor
	 */
	public static final Cursor MOVE_CURSOR = new Cursor(Cursor.MOVE_CURSOR);
	/**
	 * An invisible cursor
	 */
	public static final Cursor NO_CURSOR;

	static {
		int[] pixels = new int[16 * 16];

		Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(16, 16, pixels, 0, 16));

		NO_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), "invisible");
	}

	private static Image loadDefaultIcon() {
		Image icon = null;

		try {
			icon = ImageIO.read(Display.class.getResource("/img/lightdrive/icon.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return icon;
	}
}