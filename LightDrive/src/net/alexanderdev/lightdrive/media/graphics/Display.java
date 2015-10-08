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
package net.alexanderdev.lightdrive.media.graphics;

import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import net.alexanderdev.lightdrive.Internal;

/**
 * The main display class, and the core of any game. This class contains the
 * game loop, a state manager, and controls handling.
 * 
 * @author Christian Bryce Alexander
 * @since March 6, 2015 | 2:15:37 AM
 */
public class Display extends JFrame {
	private static final long serialVersionUID = -8708004611699503479L;

	private static final String DEFAULT_TITLE = "LightDrive";
	private static final Image DEFAULT_ICON = ImageSLoader.loadPNG("/img/icon");

	private String title;
	private Image icon;

	private boolean showUFC = false;
	private boolean initFrameUndecorated = false;

	private Screen screen;

	/**
	 * A default 640 x 480 {@code Display}, with a default title and icon
	 */
	public Display() {
		this(new Screen(), DEFAULT_TITLE, DEFAULT_ICON);
	}

	public Display(int width, int height) {
		this(new Screen(width, height), DEFAULT_TITLE, DEFAULT_ICON);
	}

	public Display(int width, int height, int scale) {
		this(new Screen(width, height, scale), DEFAULT_TITLE, DEFAULT_ICON);
	}

	public Display(String title) {
		this(new Screen(), title, DEFAULT_ICON);
	}

	public Display(Image icon) {
		this(new Screen(), DEFAULT_TITLE, icon);
	}

	public Display(String title, Image icon) {
		this(new Screen(), title, icon);
	}

	public Display(int width, int height, String title) {
		this(new Screen(width, height), title, DEFAULT_ICON);
	}

	public Display(int width, int height, int scale, String title) {
		this(new Screen(width, height, scale), title, DEFAULT_ICON);
	}

	public Display(int width, int height, Image icon) {
		this(new Screen(width, height), DEFAULT_TITLE, icon);
	}

	public Display(int width, int height, int scale, Image icon) {
		this(new Screen(width, height, scale), DEFAULT_TITLE, icon);
	}

	public Display(int width, int height, String title, Image icon) {
		this(new Screen(width, height), title, icon);
	}

	public Display(int width, int height, int scale, String title, Image icon) {
		this(new Screen(width, height, scale), title, icon);
	}

	public Display(Screen screen) {
		this(screen, DEFAULT_TITLE, DEFAULT_ICON);
	}

	public Display(Screen screen, String title) {
		this(screen, title, DEFAULT_ICON);
	}

	public Display(Screen screen, Image icon) {
		this(screen, DEFAULT_TITLE, icon);
	}

	public Display(Screen screen, String title, Image icon) {
		this.screen = screen;
		this.title = title;
		this.icon = icon;

		screen.setDisplay(this);
	}

	public Screen getScreen() {
		return screen;
	}

	public void showUpdateFrameCounter(boolean show) {
		showUFC = show;
	}

	public void showFrame(boolean show) {
		setVisible(false);
		dispose();
		setUndecorated(!show);
		setVisible(true);
	}

	@Internal
	public void setUFC(int updates, int frames) {
		if (showUFC)
			setTitle(String.format(title + "  |  UPS: %s, FPS: %s", updates, frames));
	}

	/**
	 * Opens this {@code Display} and starts the {@code Thread} that will run it
	 */
	public void open() {
		String initTitle = showUFC ? title + "  |  UPS: 60, FPS: 60" : title;

		setTitle(initTitle);
		setIconImage(icon);
		add(screen);
		setFocusable(false);
		setResizable(false);
		setUndecorated(initFrameUndecorated);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				close();
			}
		});
		setVisible(true);

		screen.open();
	}

	/**
	 * Closes this {@code Display} and joins the {@code Thread} that is running
	 * it
	 */
	public void close() {
		screen.close();
	}
}