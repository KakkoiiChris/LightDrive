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
import static net.alexanderdev.lightdrive.view.ViewConstants.DEFAULT_ICON;
import static net.alexanderdev.lightdrive.view.ViewConstants.DEFAULT_SCALE;
import static net.alexanderdev.lightdrive.view.ViewConstants.DEFAULT_TITLE;
import static net.alexanderdev.lightdrive.view.ViewConstants.DEFAULT_UPS;
import static net.alexanderdev.lightdrive.view.ViewConstants.DEFAULT_WIDTH;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import net.alexanderdev.lightdrive.InternalMethod;
import net.alexanderdev.lightdrive.graphics.GraphicsX;
import net.alexanderdev.lightdrive.graphics.Sprite;
import net.alexanderdev.lightdrive.graphics.filter.Filter;
import net.alexanderdev.lightdrive.input.gamepad.Gamepad;
import net.alexanderdev.lightdrive.input.gamepad.GamepadFinder;
import net.alexanderdev.lightdrive.input.keyboard.Keyboard;
import net.alexanderdev.lightdrive.input.mouse.Mouse;
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
public class Display extends Canvas implements Viewable, Runnable {
	private static final long serialVersionUID = -8708004611699503479L;

	private int width;
	private int height;
	private int scale;

	private double ups;

	private String title;

	private Image icon;

	private JFrame frame;

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
	private boolean ufcEnabled;
	private boolean frameRateLocked;

	private StateManager manager;

	static {
		// System.load(new File("/jinput-dx8.dll").getAbsolutePath());
		// System.load(new File("/jinput-dx8_64.dll").getAbsolutePath());
		// System.load(new File("/jinput-raw.dll").getAbsolutePath());
		// System.load(new File("/jinput-raw_64.dll").getAbsolutePath());
	}

	/**
	 * A {@link Display} with a default width, height, scale, update rate,
	 * title, and icon.
	 */
	public Display() {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_SCALE, DEFAULT_UPS, DEFAULT_TITLE, DEFAULT_ICON);
	}

	public Display(ViewMode mode) {
		this(mode.getWidth(), mode.getHeight(), DEFAULT_SCALE, mode.getUPS(), DEFAULT_TITLE, DEFAULT_ICON);
	}

	public Display(ViewMode mode, int scale) {
		this(mode.getWidth(), mode.getHeight(), scale, mode.getUPS(), DEFAULT_TITLE, DEFAULT_ICON);
	}

	public Display(ViewMode mode, String title) {
		this(mode.getWidth(), mode.getHeight(), DEFAULT_SCALE, mode.getUPS(), title, DEFAULT_ICON);
	}

	public Display(ViewMode mode, Image icon) {
		this(mode.getWidth(), mode.getHeight(), DEFAULT_SCALE, mode.getUPS(), DEFAULT_TITLE, icon);
	}

	public Display(ViewMode mode, int scale, String title) {
		this(mode.getWidth(), mode.getHeight(), scale, mode.getUPS(), title, DEFAULT_ICON);
	}

	public Display(ViewMode mode, int scale, Image icon) {
		this(mode.getWidth(), mode.getHeight(), scale, mode.getUPS(), DEFAULT_TITLE, icon);
	}

	public Display(ViewMode mode, String title, Image icon) {
		this(mode.getWidth(), mode.getHeight(), DEFAULT_SCALE, mode.getUPS(), title, icon);
	}

	public Display(ViewMode mode, int scale, String title, Image icon) {
		this(mode.getWidth(), mode.getHeight(), scale, mode.getUPS(), title, icon);
	}

	/**
	 * A {@link Display} with a user defined width and height, and a default
	 * scale, update rate, title, and icon.
	 * 
	 * @param width
	 *            The width of the {@link Display}
	 * @param height
	 *            The height of the {@link Display}
	 */
	public Display(int width, int height) {
		this(width, height, DEFAULT_SCALE, DEFAULT_UPS, DEFAULT_TITLE, DEFAULT_ICON);
	}

	/**
	 * A {@link Display} with a user defined update rate, and a default width,
	 * height, scale, title, and icon.
	 * 
	 * @param ups
	 *            The update rate of the {@link Display}
	 */
	public Display(double ups) {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_SCALE, ups, DEFAULT_TITLE, DEFAULT_ICON);
	}

	/**
	 * A {@link Display} with a user defined title, and a default width, height,
	 * scale, update rate, and icon.
	 * 
	 * @param title
	 *            The title for the {@link Display}'s {@link JFrame}
	 */
	public Display(String title) {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_SCALE, DEFAULT_UPS, title, DEFAULT_ICON);
	}

	/**
	 * A {@link Display} with a user defined icon, and a default width, height,
	 * scale, update rate, and title.
	 * 
	 * @param icon
	 *            The icon for the {@link Display}'s {@link JFrame}
	 */
	public Display(Image icon) {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_SCALE, DEFAULT_UPS, DEFAULT_TITLE, icon);
	}

	/**
	 * A {@link Display} with a user defined width, height, and update rate, and
	 * a default scale, title, and icon.
	 * 
	 * @param width
	 *            The width of the {@link Display}
	 * @param height
	 *            The height of the {@link Display}
	 * @param ups
	 *            The update rate of the {@link Display}
	 */
	public Display(int width, int height, double ups) {
		this(width, height, DEFAULT_SCALE, ups, DEFAULT_TITLE, DEFAULT_ICON);
	}

	/**
	 * A {@link Display} with a user defined width, height, and title, and a
	 * default scale, update rate, and icon.
	 * 
	 * @param width
	 *            The width of the {@link Display}
	 * @param height
	 *            The height of the {@link Display}
	 * @param title
	 *            The title for the {@link Display}'s {@link JFrame}
	 */
	public Display(int width, int height, String title) {
		this(width, height, DEFAULT_SCALE, DEFAULT_UPS, title, DEFAULT_ICON);
	}

	/**
	 * A {@link Display} with a user defined width, height, and icon, and a
	 * default scale, update rate, and title.
	 * 
	 * @param width
	 *            The width of the {@link Display}
	 * @param height
	 *            The height of the {@link Display}
	 * @param icon
	 *            The icon for the {@link Display}'s {@link JFrame}
	 */
	public Display(int width, int height, Image icon) {
		this(width, height, DEFAULT_SCALE, DEFAULT_UPS, DEFAULT_TITLE, icon);
	}

	/**
	 * A {@link Display} with a user defined width, height, update rate, and
	 * title, and a default scale and icon.
	 * 
	 * @param width
	 *            The width of the {@link Display}
	 * @param height
	 *            The height of the {@link Display}
	 * @param ups
	 *            The update rate of the {@link Display}
	 * @param title
	 *            The title for the {@link Display}'s {@link JFrame}
	 */
	public Display(int width, int height, double ups, String title) {
		this(width, height, DEFAULT_SCALE, ups, title, DEFAULT_ICON);
	}

	/**
	 * A {@link Display} with user defined width, height, update rate, and icon,
	 * and a default scale and title.
	 * 
	 * @param width
	 *            The width of the {@link Display}
	 * @param height
	 *            The height of the {@link Display}
	 * @param ups
	 *            The update rate of the {@link Display}
	 * @param icon
	 *            The icon for the {@link Display}'s {@link JFrame}
	 */
	public Display(int width, int height, double ups, Image icon) {
		this(width, height, DEFAULT_SCALE, ups, DEFAULT_TITLE, icon);
	}

	/**
	 * A {@link Display} with a user defined width, height, title, and icon, and
	 * a default scale and update rate.
	 * 
	 * @param width
	 *            The width of the {@link Display}
	 * @param height
	 *            The height of the {@link Display}
	 * @param title
	 *            The title for the {@link Display}'s {@link JFrame}
	 * @param icon
	 *            The icon for the {@link Display}'s {@link JFrame}
	 */
	public Display(int width, int height, String title, Image icon) {
		this(width, height, DEFAULT_SCALE, DEFAULT_UPS, title, icon);
	}

	/**
	 * A {@link Display} with a user defined width, height, update rate, title,
	 * and icon, and a default scale.
	 * 
	 * @param width
	 *            The width of the {@link Display}
	 * @param height
	 *            The height of the {@link Display}
	 * @param ups
	 *            The update rate of the {@link Display}
	 * @param title
	 *            The title for the {@link Display}'s {@link JFrame}
	 * @param icon
	 *            The icon for the {@link Display}'s {@link JFrame}
	 */
	public Display(int width, int height, double ups, String title, Image icon) {
		this(width, height, DEFAULT_SCALE, ups, title, icon);
	}

	/**
	 * A {@link Display} with a user defined width, height, and scale, and a
	 * default update rate, title, and icon.
	 * 
	 * @param width
	 *            The width of the {@link Display}
	 * @param height
	 *            The height of the {@link Display}
	 * @param scale
	 *            The scale factor of the {@link Display}
	 */
	public Display(int width, int height, int scale) {
		this(width, height, scale, DEFAULT_UPS, DEFAULT_TITLE, DEFAULT_ICON);
	}

	/**
	 * A {@link Display} with a user defined width, height, scale, and frame
	 * rate, and a default title, and icon.
	 * 
	 * @param width
	 *            The width of the {@link Display}
	 * @param height
	 *            The height of the {@link Display}
	 * @param scale
	 *            The scale factor of the {@link Display}
	 * @param ups
	 *            The update rate of the {@link Display}
	 */
	public Display(int width, int height, int scale, double ups) {
		this(width, height, scale, ups, DEFAULT_TITLE, DEFAULT_ICON);
	}

	/**
	 * A {@link Display} with a user defined width, height, scale, and title,
	 * and a default update rate and icon.
	 * 
	 * @param width
	 *            The width of the {@link Display}
	 * @param height
	 *            The height of the {@link Display}
	 * @param scale
	 *            The scale factor of the {@link Display}
	 * @param title
	 *            The title for the {@link Display}'s {@link JFrame}
	 */
	public Display(int width, int height, int scale, String title) {
		this(width, height, scale, DEFAULT_UPS, title, DEFAULT_ICON);
	}

	/**
	 * A {@link Display} with a user defined width, height, scale, and icon, and
	 * a default update rate and title.
	 * 
	 * @param width
	 *            The width of the {@link Display}
	 * @param height
	 *            The height of the {@link Display}
	 * @param scale
	 *            The scale factor of the {@link Display}
	 * @param icon
	 *            The icon for the {@link Display}'s {@link JFrame}
	 */
	public Display(int width, int height, int scale, Image icon) {
		this(width, height, scale, DEFAULT_UPS, DEFAULT_TITLE, icon);
	}

	/**
	 * A {@link Display} with a user defined width, height, scale, update rate,
	 * and title, and a default icon.
	 * 
	 * @param width
	 *            The width of the {@link Display}
	 * @param height
	 *            The height of the {@link Display}
	 * @param scale
	 *            The scale factor of the {@link Display}
	 * @param ups
	 *            The update rate of the {@link Display}
	 * @param title
	 *            The title for the {@link Display}'s {@link JFrame}
	 */
	public Display(int width, int height, int scale, double ups, String title) {
		this(width, height, scale, ups, title, DEFAULT_ICON);
	}

	/**
	 * A {@link Display} with a user defined width, height, scale, update rate,
	 * and icon, and a default title.
	 * 
	 * @param width
	 *            The width of the {@link Display}
	 * @param height
	 *            The height of the {@link Display}
	 * @param scale
	 *            The scale factor of the {@link Display}
	 * @param ups
	 *            The update rate of the {@link Display}
	 * @param icon
	 *            The icon for the {@link Display}'s {@link JFrame}
	 */
	public Display(int width, int height, int scale, double ups, Image icon) {
		this(width, height, scale, ups, DEFAULT_TITLE, icon);
	}

	/**
	 * A {@link Display} with a user defined width, height, scale, title, and
	 * icon, and a default update rate.
	 * 
	 * @param width
	 *            The width of the {@link Display}
	 * @param height
	 *            The height of the {@link Display}
	 * @param scale
	 *            The scale factor of the {@link Display}
	 * @param title
	 *            The title for the {@link Display}'s {@link JFrame}
	 * @param icon
	 *            The icon for the {@link Display}'s {@link JFrame}
	 */
	public Display(int width, int height, int scale, String title, Image icon) {
		this(width, height, scale, DEFAULT_UPS, title, icon);
	}

	/**
	 * A {@link Display} with a user defined width, height, scale, update rate,
	 * title, and icon.
	 * 
	 * @param width
	 *            The width of the {@link Display}
	 * @param height
	 *            The height of the {@link Display}
	 * @param scale
	 *            The scale factor of the {@link Display}
	 * @param ups
	 *            The update rate of the {@link Display}
	 * @param title
	 *            The title for the {@link Display}'s {@link JFrame}
	 * @param icon
	 *            The icon for the {@link Display}'s {@link JFrame}
	 */
	public Display(int width, int height, int scale, double ups, String title, Image icon) {
		this.width = width;
		this.height = height;
		this.scale = scale;
		this.ups = ups;
		this.title = title;
		this.icon = icon;

		ufcEnabled = false;
		frameRateLocked = false;
		keyboardEnabled = false;
		mouseEnabled = false;
		gamepadsEnabled = false;

		renderHints = new HashMap<>();

		manager = new StateManager(this);

		filters = new ArrayList<>();
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
	 * Enables the visualization of an update/frame counter in the title bar of
	 * this {@link Display}. It is disabled by default.
	 */
	public void enableUFC() {
		ufcEnabled = true;
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

		frame = new JFrame(title + (ufcEnabled ? "  |  UPS: 60, FPS: 60" : ""));
		frame.setIconImage(icon);
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(false);

		this.requestFocus();

		initGraphics();

		manager.init();

		frame.setVisible(true);

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

		this.createBufferStrategy(2);
		bs = this.getBufferStrategy();
		g = bs.getDrawGraphics();
	}

	@Override
	public final void close() {
		stop();
		System.exit(0);
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

		if (gamepadsEnabled)
			for (Gamepad gamepad : gamepads)
				gamepad.stop();

		running = false;

		cleanUp();
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
				updates++;
				shouldRender = true;
				delta--;
			}

			if (shouldRender) {
				render();
				frames++;
			}

			if (Time.msTime() - timer >= 1000) {
				if (ufcEnabled)
					frame.setTitle(String.format("%s | UPS: %d, FPS: %d", title, updates, frames));

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
				gamepads[i].update();
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