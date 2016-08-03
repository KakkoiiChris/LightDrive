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

import static net.alexanderdev.lightdrive.view.ViewConstants.DEFAULT_HEIGHT;
import static net.alexanderdev.lightdrive.view.ViewConstants.DEFAULT_SCALE;
import static net.alexanderdev.lightdrive.view.ViewConstants.DEFAULT_UPS;
import static net.alexanderdev.lightdrive.view.ViewConstants.DEFAULT_WIDTH;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.HashMap;
import java.util.Map;

import net.alexanderdev.lightdrive.InternalMethod;
import net.alexanderdev.lightdrive.graphics.GraphicsX;
import net.alexanderdev.lightdrive.graphics.Sprite;
import net.alexanderdev.lightdrive.input.gamepad.Gamepad;
import net.alexanderdev.lightdrive.input.gamepad.GamepadFinder;
import net.alexanderdev.lightdrive.input.keyboard.Keyboard;
import net.alexanderdev.lightdrive.input.mouse.Mouse;
import net.alexanderdev.lightdrive.state.State;
import net.alexanderdev.lightdrive.state.StateManager;
import net.alexanderdev.lightdrive.util.time.Time;

/**
 * A {@link Viewable} that consists of a stand-alone {@link Canvas}, intended
 * for integration with custom <b>AWT/Swing</b> user interfaces.
 * 
 * @author Christian Bryce Alexander
 * @since March 6, 2015, 2:15:37 AM
 */
public class Window extends Canvas implements Viewable, Runnable {
	private static final long serialVersionUID = -8708004611699503479L;

	private int width;
	private int height;
	private int scale;

	private double ups;

	private boolean running;
	private Thread thread;

	private Sprite context;

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
	private boolean frameRateLocked;

	private StateManager manager;

	private int lastUpdates;
	private int lastFrames;

	static {
		// System.load(new File("/jinput-dx8.dll").getAbsolutePath());
		// System.load(new File("/jinput-dx8_64.dll").getAbsolutePath());
		// System.load(new File("/jinput-raw.dll").getAbsolutePath());
		// System.load(new File("/jinput-raw_64.dll").getAbsolutePath());
	}

	/**
	 * A {@link Window} with default width,height,scale, and update rate.
	 */
	public Window() {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_SCALE, DEFAULT_UPS);
	}

	/**
	 * A {@link Window} with its width, height, and update rate specified by the
	 * provided {@link ViewMode}, and a default scale.
	 * 
	 * @param mode
	 *            The {@link ViewMode} of the {@link Window}
	 */
	public Window(ViewMode mode) {
		this(mode.getWidth(), mode.getHeight(), DEFAULT_SCALE, mode.getUPS());
	}

	/**
	 * A {@link Window} with its width, height, and update rate specified by the
	 * provided {@link ViewMode}, and a user defined scale.
	 * 
	 * @param mode
	 *            The {@link ViewMode} of the {@link Window}
	 * @param scale
	 *            The scale of the {@link Window}
	 */
	public Window(ViewMode mode, int scale) {
		this(mode.getWidth(), mode.getHeight(), scale, mode.getUPS());
	}

	/**
	 * A {@link Window} with a user defined width, and height, and a default
	 * scale and update rate.
	 * 
	 * @param width
	 *            The width of the {@link Window}
	 * @param height
	 *            The height of the {@link Window}
	 */
	public Window(int width, int height) {
		this(width, height, DEFAULT_SCALE, DEFAULT_UPS);
	}

	/**
	 * A {@link Window} with a user defined width, height, and scale, and a
	 * default update rate.
	 * 
	 * @param width
	 *            The width of the {@link Window}
	 * @param height
	 *            The height of the {@link Window}
	 * @param scale
	 *            The scale of the {@link Window}
	 */
	public Window(int width, int height, int scale) {
		this(width, height, scale, DEFAULT_UPS);
	}

	/**
	 * A {@link Window} with a user defined update rate, and a default width,
	 * height, and scale.
	 * 
	 * @param ups
	 *            The update rate of the {@link Window}
	 */
	public Window(double ups) {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_SCALE, ups);
	}

	/**
	 * A {@link Window} with a user defined width, height, and update rate, and
	 * a default scale.
	 * 
	 * @param width
	 *            The width of the {@link Window}
	 * @param height
	 *            The height of the {@link Window}
	 * @param ups
	 *            The update rate of the {@link Window}
	 */
	public Window(int width, int height, double ups) {
		this(width, height, DEFAULT_SCALE, ups);
	}

	/**
	 * A {@link Window} with a user defined width, height, scale, and update
	 * rate.
	 * 
	 * @param width
	 *            The width of the {@link Window}
	 * @param height
	 *            The height of the {@link Window}
	 * @param scale
	 *            The scale of the {@link Window}
	 * @param ups
	 *            The update rate of the {@link Window}
	 */
	public Window(int width, int height, int scale, double ups) {
		this.width = width;
		this.height = height;
		this.scale = scale;

		Dimension d = new Dimension(width * scale, height * scale);

		this.setMinimumSize(d);
		this.setPreferredSize(d);
		this.setMaximumSize(d);

		this.ups = ups;

		keyboardEnabled = false;
		mouseEnabled = false;
		gamepadsEnabled = false;
		frameRateLocked = false;

		renderHints = new HashMap<>();

		manager = new StateManager(this);
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

	/**
	 * Enables the use of {@link Gamepad}s by the {@link StateManager} and its
	 * respective {@link State}s. They are disabled by default.
	 */
	public void enableGamepads() {
		gamepadsEnabled = true;
	}

	/**
	 * Enables the anti-aliasing of graphical primitives. This is disabled by
	 * default.
	 */
	public void enableSmoothShapes() {
		renderHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}

	/**
	 * Enables the anti-aliasing of text. This is disabled by default.
	 */
	public void enableSmoothText() {
		renderHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	}

	/**
	 * Sets the frame rate to be locked to the update rate. Frame rates are
	 * unlocked by default.
	 */
	public void enableFrameRateLock() {
		frameRateLocked = true;
	}

	public void setViewMode(ViewMode mode) {
		this.width = mode.getWidth();
		this.height = mode.getHeight();
		this.ups = mode.getUPS();
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
	public Sprite getContext() {
		return context;
	}

	@Override
	public int getUpdateCount() {
		return lastUpdates;
	}

	@Override
	public int getFrameCount() {
		return lastFrames;
	}

	@Override
	public final void open() {
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
	}

	private void initGraphics() {
		System.setProperty("sun.java2d.d3d", "True");
		System.setProperty("sun.java2d.opengl", "True");
		System.setProperty("sun.java2d.translaccel", "True");
		System.setProperty("sun.java2d.ddforcevram", "True");

		context = new Sprite(width, height);

		gx = new GraphicsX((Graphics2D) context.getGraphics());
		gx.setRenderingHints(renderHints);

		createBufferStrategy(3);
		bs = getBufferStrategy();
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

		try {
			thread.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	@InternalMethod
	public void run() {
		long last = Time.nsTime();
		long timer = Time.msTime();

		final double NS = 1000000000.0 / ups;

		double delta = 0;

		int updates = 0;
		int frames = 0;

		while (running) {
			long now = Time.nsTime();
			delta += (now - last) / NS;
			last = now;

			boolean shouldRender = !frameRateLocked;

			if (delta >= 1) {
				update(delta);
				shouldRender = true;
				delta--;
			}

			if (shouldRender)
				render();

			if (Time.msTime() - timer >= 1000) {
				lastUpdates = updates;
				lastFrames = frames;

				updates = frames = 0;

				timer += 1000;
			}
		}
	}

	/**
	 * Handles all updates, from keyboard and mouse input, to
	 * {@link StateManager} and {@link State} update logic.
	 * 
	 * @param delta
	 *            The delta time between this update and the last
	 */
	@InternalMethod
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
	@InternalMethod
	public void render() {
		gx.clearRect(0, 0, width, height);

		manager.render(gx);

		g.drawImage(context, 0, 0, getWidth(), getHeight(), null);

		bs.show();
	}

	@Override
	public boolean cleanUp() {
		boolean success = true;

		context.flush();

		g.dispose();
		gx.dispose();

		bs.dispose();

		success &= manager.cleanUp();

		return success;
	}
}