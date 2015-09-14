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

import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.KEY_TEXT_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_OFF;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;
import static java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_OFF;
import static java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_ON;
import static net.alexanderdev.lightdrive.util.Time.msTime;
import static net.alexanderdev.lightdrive.util.Time.nsTime;

import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.MemoryImageSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.alexanderdev.lightdrive.Internal;
import net.alexanderdev.lightdrive.input.Gamepad;
import net.alexanderdev.lightdrive.input.GamepadFinder;
import net.alexanderdev.lightdrive.input.Keyboard;
import net.alexanderdev.lightdrive.input.Mouse;
import net.alexanderdev.lightdrive.media.graphics.filters.ImageSFilter;
import net.alexanderdev.lightdrive.state.StateManager;

/**
 * The main display class, and the core of any game. This class contains the
 * game loop, a state manager, and controls handling.
 * 
 * @author Christian Bryce Alexander
 * @since March 6, 2015 | 2:15:37 AM
 */
public class Screen extends Canvas implements Runnable {
	private static final long serialVersionUID = -8708004611699503479L;

	private static final int DEFAULT_WIDTH  = 640;
	private static final int DEFAULT_HEIGHT = 480;
	private static final int DEFAULT_SCALE  = 1;

	public static final int NONE             = 0x0;
	public static final int ANTIALIAS_SHAPES = 0x1;
	public static final int ANTIALIAS_TEXT   = 0x2;
	public static final int BOTH             = 0x3;

	/**
	 * The default system cursor
	 */
	public static final Cursor DEFAULT_CURSOR   = new Cursor(Cursor.DEFAULT_CURSOR);

	/**
	 * The crosshair cursor
	 */
	public static final Cursor CROSSHAIR_CURSOR = new Cursor(Cursor.CROSSHAIR_CURSOR);

	/**
	 * The loading cursor
	 */
	public static final Cursor WAIT_CURSOR      = new Cursor(Cursor.WAIT_CURSOR);

	/**
	 * The hand cursor
	 */
	public static final Cursor HAND_CURSOR      = new Cursor(Cursor.HAND_CURSOR);

	/**
	 * The movement cursor
	 */
	public static final Cursor MOVE_CURSOR      = new Cursor(Cursor.MOVE_CURSOR);

	/**
	 * An invisible cursor
	 */
	public static final Cursor NO_CURSOR;

	private Keyboard  keyboard = null;
	private Mouse     mouse    = null;
	private Gamepad[] gamepads = null;

	private int rWidth;
	private int rHeight;
	private int scale;

	private Thread thread;
	private ImageS screen;

	private boolean running      = false;
	private boolean lockCursorIn = false;
	private boolean useKeyboard  = false;
	private boolean useMouse     = false;
	private boolean useGamepad   = false;

	private Map<RenderingHints.Key, Object> renderHints = new HashMap<>();

	private List<ImageSFilter> filters = new ArrayList<>();

	private StateManager manager = new StateManager(this);

	static {
		int[] pixels = new int[16 * 16];

		Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(16, 16, pixels, 0, 16));

		NO_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), "invisible");

		System.setProperty("sun.java2d.opengl", "True");

		// System.load(new File("/jinput-dx8.dll").getAbsolutePath());
		// System.load(new File("/jinput-dx8_64.dll").getAbsolutePath());
		// System.load(new File("/jinput-raw.dll").getAbsolutePath());
		// System.load(new File("/jinput-raw_64.dll").getAbsolutePath());
	}

	/**
	 * A default 640 x 480 {@code Display}, with a default title and icon
	 */
	public Screen() {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_SCALE);
	}

	/**
	 * A fully custom {@code Display}
	 * 
	 * @param width
	 *            The width to set
	 * @param height
	 *            The height to set
	 * @param title
	 *            The title to set
	 * @param icon
	 *            The icon to set
	 */
	public Screen(int width, int height) {
		this(width, height, DEFAULT_SCALE);
	}

	/**
	 * A fully custom {@code Display}
	 * 
	 * @param width
	 *            The width to set
	 * @param height
	 *            The height to set
	 * @param scale
	 *            The scale of the pixels
	 * @param title
	 *            The title to set
	 * @param icon
	 *            The icon to set
	 */
	public Screen(int width, int height, int scale) {
		this.rWidth = width;
		this.rHeight = height;
		this.scale = scale;

		setSize(width, height, scale);

		screen = new ImageS(rWidth, rHeight);
	}

	/**
	 * Sets the minimum, maximum, and preferred sizes of this {@code Display}
	 * 
	 * @param width
	 *            The width to set
	 * @param height
	 *            The height to set
	 * @param scale
	 *            The scale of the pixels
	 */
	public void setSize(int width, int height, int scale) {
		Dimension dim = new Dimension(width * scale, height * scale);

		setMinimumSize(dim);
		setPreferredSize(dim);
		setMaximumSize(dim);
	}

	/**
	 * Sets this {@code Display}'s {@code StateManager}
	 * 
	 * @param manager
	 *            The {@code StateManager} to set
	 */
	public void setManager(StateManager manager) {
		this.manager = manager;
	}

	/**
	 * @return This {@code Display}'s {@code StateManager}
	 */
	public StateManager getManager() {
		return manager;
	}

	/**
	 * @return The unscaled size of this {@code Display} in pixels
	 */
	public Dimension getScreenSize() {
		return new Dimension(rWidth, rHeight);
	}

	/**
	 * @return The unscaled bounds of this {@code Display} in pixels
	 */
	public Rectangle getScreenBounds() {
		return new Rectangle(0, 0, rWidth, rHeight);
	}

	/**
	 * @return The scale of this {@code Display}
	 */
	public int getScale() {
		return scale;
	}

	@Internal
	public boolean cursorLocked() {
		return lockCursorIn;
	}

	/**
	 * Saves a screenshot of this {@code Display}
	 * 
	 * @param path
	 *            The path at which to save the screenshot
	 * @param format
	 *            The format to save the image as
	 * @param name
	 *            The name to save the image as
	 */
	public void takeScreenshot(String path, String format, String name) {
		Screenshot.setRelativePath(path);

		ImageS ss = new ImageS(screen.getWidth() * scale, screen.getHeight() * scale);

		Graphics g = ss.getGraphics();
		g.drawImage(screen, 0, 0, ss.getWidth(), ss.getHeight(), null);
		g.dispose();

		Screenshot.createScreenshot(ss, format, name);
	}

	public void enableAntialiasing(int mode, boolean enable) {
		if ((mode & ANTIALIAS_SHAPES) == ANTIALIAS_SHAPES)
			if (enable)
				renderHints.put(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
			else
				renderHints.put(KEY_ANTIALIASING, VALUE_ANTIALIAS_OFF);

		if ((mode & ANTIALIAS_TEXT) == ANTIALIAS_TEXT)
			if (enable)
				renderHints.put(KEY_TEXT_ANTIALIASING, VALUE_TEXT_ANTIALIAS_ON);
			else
				renderHints.put(KEY_TEXT_ANTIALIASING, VALUE_TEXT_ANTIALIAS_OFF);
	}

	public void enableCursorLock(boolean enable) {
		lockCursorIn = enable;
	}

	public void enableKeyboard(boolean enable) {
		if (enable) {
			if (keyboard == null)
				keyboard = new Keyboard();

			addKeyListener(keyboard);
		} else {
			if (keyboard != null)
				removeKeyListener(keyboard);
		}

		useKeyboard = enable;
	}

	public void enableMouse(boolean enable) {
		if (enable) {
			if (mouse == null)
				mouse = new Mouse(this);

			addMouseListener(mouse);
			addMouseMotionListener(mouse);
			addMouseWheelListener(mouse);
		} else {
			if (mouse != null) {
				removeMouseListener(mouse);
				removeMouseMotionListener(mouse);
				removeMouseWheelListener(mouse);
			}
		}

		useMouse = enable;
	}

	public void enableGamepads(boolean enable) {
		if (enable) {
			if (gamepads == null)
				gamepads = GamepadFinder.getGamepads();

			for (Gamepad gp : gamepads)
				gp.start();
		} else {
			if (gamepads != null)
				for (Gamepad gp : gamepads)
					gp.stop();
		}

		useGamepad = enable;
	}

	/**
	 * Adds an {@code ImageSFilter}, which is applied to the entire
	 * {@code Display}'s image before rendering.
	 * 
	 * @param filter
	 *            The filter type to apply
	 */
	public void addFilter(ImageSFilter filter) {
		filters.add(filter);
	}

	public void removeFilter(ImageSFilter filter) {
		filters.remove(filter);
	}

	public void clearFilters() {
		filters.clear();
	}

	/**
	 * Opens this {@code Display} and starts the {@code Thread} that will run it
	 */
	public void open() {
		requestFocus();

		start();

		manager.init();
	}

	/**
	 * Closes this {@code Display} and joins the {@code Thread} that is running
	 * it
	 */
	public void close() {
		if (gamepads != null)
			for (Gamepad gamepad : gamepads)
				gamepad.stop();

		stop();
	}

	private synchronized void start() {
		if (running)
			return;

		running = true;

		thread = new Thread(this, "ld_screen_game_loop");

		thread.start();
	}

	private synchronized void stop() {
		if (!running)
			return;

		running = false;

		System.exit(0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Internal
	@Override
	public void run() {
		long last = nsTime();
		long timer = msTime();

		final double nanos = 1000000000.0 / 60.0;

		double delta = 0;

		int updates = 0;
		int frames = 0;

		while (running) {
			boolean rendering = false;

			long now = nsTime();
			delta += (now - last) / nanos;
			last = now;

			while (delta >= 1) {
				update(delta);
				updates++;
				rendering = true;
				delta--;
			}

			if (rendering) {
				render();
				frames++;
			}

			if (msTime() - timer >= 1000) {
				timer += 1000;

				if (getParent() instanceof Display) {
					((Display) getParent()).setUFC(updates, frames);
				}

				updates = 0;
				frames = 0;
			}
		}
	}

	private void update(double delta) {
		if (useKeyboard)
			manager.keyboardInput(keyboard);

		if (useMouse) {
			mouse.update();

			manager.mouseInput(mouse);
		}

		if (useGamepad)
			for (Gamepad gamepad : gamepads)
				manager.gamepadInput(gamepad);

		manager.update(delta);
	}

	private void render() {
		GraphicsS g = new GraphicsS((Graphics2D) screen.getGraphics());

		g.setRenderingHints(renderHints);
		g.setColor(0);
		g.fill(getScreenBounds());

		manager.render(g);

		g.dispose();

		BufferStrategy bs = getBufferStrategy();

		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g2 = bs.getDrawGraphics();

		g2.drawImage(screen.filtered(getFilterList()), 0, 0, rWidth * scale, rHeight * scale, null);

		g2.dispose();

		bs.show();
	}

	private ImageSFilter[] getFilterList() {
		return filters.toArray(new ImageSFilter[filters.size()]);
	}
}