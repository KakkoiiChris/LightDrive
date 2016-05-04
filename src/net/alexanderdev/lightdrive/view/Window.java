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

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.alexanderdev.lightdrive.graphics.GraphicsX;
import net.alexanderdev.lightdrive.graphics.Sprite;
import net.alexanderdev.lightdrive.graphics.filter.Filter;
import net.alexanderdev.lightdrive.input.Gamepad;
import net.alexanderdev.lightdrive.input.GamepadFinder;
import net.alexanderdev.lightdrive.input.Keyboard;
import net.alexanderdev.lightdrive.input.Mouse;
import net.alexanderdev.lightdrive.state.State;
import net.alexanderdev.lightdrive.state.StateManager;
import net.alexanderdev.lightdrive.util.Time;

/**
 * The main display class, and the core of any game. This class contains the
 * game loop, a state manager, and controls handling.
 * 
 * @author Christian Bryce Alexander
 * @since March 6, 2015 | 2:15:37 AM
 */
public class Window extends Canvas implements Viewable, Runnable {
	private static final long serialVersionUID = -8708004611699503479L;

	/**
	 * The default width for a {@code Display}.
	 */
	public static final int DEFAULT_WIDTH = 640;
	/**
	 * The default height for a {@code Display}.
	 */
	public static final int DEFAULT_HEIGHT = 480;
	/**
	 * The default scale for a {@code Display}.
	 */
	public static final int DEFAULT_SCALE = 1;
	/**
	 * The max frames per second that a {@code Display} runs at.
	 */
	public static final double DEFAULT_FPS = 60.0;

	public static final int ANTIALIAS_NONE = 0x0;
	public static final int ANTIALIAS_SHAPES = 0x1;
	public static final int ANTIALIAS_TEXT = 0x2;
	public static final int ANTIALIAS_BOTH = 0x3;

	private int width;
	private int height;
	private int scale;

	private double fps;

	private boolean running;
	private Thread thread;

	private Sprite context;

	private List<Filter> filters;

	private GraphicsX gx;
	private Graphics g;
	private BufferStrategy bs;

	private Map<RenderingHints.Key, Object> renderHints;

	private Keyboard keyboard;
	private Mouse mouse;
	private Gamepad[] gamepads;

	private boolean keyboardEnabled;
	private boolean mouseEnabled;
	private boolean gamepadsEnabled;

	private StateManager manager;

	static {
		// System.load(new File("/jinput-dx8.dll").getAbsolutePath());
		// System.load(new File("/jinput-dx8_64.dll").getAbsolutePath());
		// System.load(new File("/jinput-raw.dll").getAbsolutePath());
		// System.load(new File("/jinput-raw_64.dll").getAbsolutePath());
	}

	public Window(int width, int height) {
		this(width, height, DEFAULT_SCALE, DEFAULT_FPS);
	}

	public Window(int width, int height, int scale) {
		this(width, height, scale, DEFAULT_FPS);
	}

	public Window(double fps) {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_SCALE, fps);
	}

	public Window(int width, int height, double fps) {
		this(width, height, DEFAULT_SCALE, fps);
	}

	public Window(int width, int height, int scale, double fps) {
		this.width = width;
		this.height = height;
		this.scale = scale;

		this.fps = fps;

		keyboardEnabled = false;
		mouseEnabled = false;
		gamepadsEnabled = false;

		renderHints = new HashMap<>();
	}

	/**
	 * Enables the use of the {@link Keyboard} by the {@link StateManager} and
	 * its respective {@link State}s. It is disabled by default.
	 */
	public void enableKeyboard() {
		keyboardEnabled = true;
	}

	/**
	 * Enables the use of the {@link Mouse} by the {@link StateManager} and its
	 * respective {@link State}s. It is disabled by default.
	 */
	public void enableMouse() {
		mouseEnabled = true;
	}

	public void enableGamepads() {
		gamepadsEnabled = true;
	}

	public void enableAntialiasing(int mode) {
		if ((mode | ANTIALIAS_SHAPES) == ANTIALIAS_SHAPES)
			renderHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		else
			renderHints.remove(RenderingHints.KEY_ANTIALIASING);

		if ((mode | ANTIALIAS_TEXT) == ANTIALIAS_TEXT)
			renderHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		else
			renderHints.remove(RenderingHints.KEY_TEXT_ANTIALIASING);
	}

	@Override
	public StateManager getManager() {
		return manager;
	}

	@Override
	public void setManager(StateManager manager) {
		this.manager = manager;
	}

	@Override
	public Rectangle getViewBounds() {
		return new Rectangle(0, 0, width, height);
	}

	@Override
	public int getViewWidth() {
		return width;
	}

	@Override
	public int getViewHeight() {
		return height;
	}

	@Override
	public int getViewScale() {
		return scale;
	}

	@Override
	public final void open() {
		Dimension d = new Dimension(width * scale, height * scale);

		this.setPreferredSize(d);
		this.setMinimumSize(d);
		this.setMaximumSize(d);

		if (keyboardEnabled) {
			keyboard = new Keyboard();

			this.addKeyListener(keyboard);
		}

		if (mouseEnabled) {
			mouse = new Mouse(this);

			this.addMouseListener(mouse);
			this.addMouseMotionListener(mouse);
			this.addMouseWheelListener(mouse);
		}

		if (gamepadsEnabled) {
			gamepads = GamepadFinder.getGamepads();

			for (Gamepad gp : gamepads)
				gp.start();
		}

		this.requestFocus();

		initGraphics();

		manager.init();

		start();

		// Debugger.printLine("QUIXEL SCREEN STATS");
		// Debugger.printLine(" - Pixel Resolution: " + width + "x" + height);
		// Debugger.printLine(" - Actual Resolution: " + width * scale + "x" +
		// height * scale);
		// Debugger.printLine(" - Keyboard: " + (keyboardEnabled ? "En" : "Dis")
		// + "abled");
		// Debugger.printLine(" - Mouse: " + (mouseEnabled ? "En" : "Dis") +
		// "abled");
	}

	private void initGraphics() {
		System.setProperty("sun.java2d.d3d", "True");
		System.setProperty("sun.java2d.opengl", "True");

		context = new Sprite(width, height);

		gx = new GraphicsX((Graphics2D) context.getGraphics());
		gx.setRenderingHints(renderHints);

		this.createBufferStrategy(3);
		bs = this.getBufferStrategy();
		g = bs.getDrawGraphics();
	}

	@Override
	public final void close() {
		stop();
	}

	private final synchronized void start() {
		if (running)
			return;

		running = true;

		thread = new Thread(this, "light_drive_game_loop");

		thread.start();
	}

	private final synchronized void stop() {
		if (!running)
			return;

		running = false;

		cleanUp();

		try {
			thread.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		long last = Time.nsTime();

		final double NS = 1000000000.0 / fps;

		double delta = 0;

		while (running) {
			long now = Time.nsTime();
			delta += (now - last) / NS;
			last = now;

			boolean shouldRender = false;

			while (delta >= 1) {
				update(delta);
				shouldRender = true;
				delta--;
			}

			if (shouldRender)
				render();
		}
	}

	/**
	 * Handles all updates, from keyboard and mouse input, to
	 * {@link StateManager} and {@link State} update logic.
	 * 
	 * @param delta
	 *            The delta time between this update and the last
	 */
	public void update(double delta) {
		if (keyboardEnabled) {
			manager.keyboardInput(keyboard);
			keyboard.update();
		}

		if (mouseEnabled) {
			manager.mouseInput(mouse);
			mouse.update();
		}

		if (gamepadsEnabled) {
			for (int i = 0; i < gamepads.length; i++) {
				manager.gamepadInput(gamepads[i]);
				// gamepads[i].update();
			}
		}

		manager.update(delta);
	}

	/**
	 * Handles all graphics, from the main {@link GraphicsX} context rendering
	 * and filtering, to the final {@link Graphics} draw that makes it visible
	 * on the {@link Canvas}'s {@link BufferStrategy}.
	 */
	public void render() {
		gx.clearRect(0, 0, width, height);

		manager.render(gx);

		if (!filters.isEmpty())
			context.filter(getFilterList());

		g.drawImage(context, 0, 0, getWidth(), getHeight(), null);

		bs.show();
	}

	private Filter[] getFilterList() {
		return filters.toArray(new Filter[filters.size()]);
	}

	@Override
	public void addFilter(Filter filter) {
		filters.add(filter);
	}

	@Override
	public void removeFilter(Filter filter) {
		filters.remove(filter);
	}

	@Override
	public void clearFilters() {
		filters.clear();
	}

	private void cleanUp() {
		gx.dispose();
		g.dispose();
		bs.dispose();
		context.flush();
	}
}