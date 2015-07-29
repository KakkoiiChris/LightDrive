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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.MemoryImageSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

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
public class Display extends Canvas implements Runnable {
	private static final long serialVersionUID = -8708004611699503479L;

	private static final int    DEFAULT_WIDTH  = 640;
	private static final int    DEFAULT_HEIGHT = 480;
	private static final int    DEFAULT_SCALE  = 1;
	private static final String DEFAULT_TITLE  = "LightDrive";
	private static final Image  DEFAULT_ICON   = ImageSLoader.loadPNG("/img/icon");

	/**
	 * Enables antialiasing of primitives and shapes
	 */
	public static final int ANTIALIAS_SHAPES        = 0x01;

	/**
	 * Enables antialiasing of text
	 */
	public static final int ANTIALIAS_TEXT          = 0x02;

	/**
	 * Includes an update/frame counter in the title bar
	 */
	public static final int SHOW_UPDATE_FPS_COUNTER = 0x04;

	/**
	 * Enables limiting the cursor to the bounds of the {@code Display}
	 */
	public static final int LOCK_CURSOR_IN          = 0x08;

	/**
	 * Enables use of keyboard input
	 */
	public static final int USE_KEYBOARD            = 0x10;

	/**
	 * Enables use of mouse input
	 */
	public static final int USE_MOUSE               = 0x20;

	/**
	 * Enables use of gamepad input
	 */
	public static final int USE_GAMEPAD             = 0x40;

	/**
	 * Enables the border and title bar around the {@code Display}
	 */
	public static final int SHOW_FRAME              = 0x80;

	/**
	 * Enables all properties
	 */
	public static final int ALL                     = 0xff;

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

	private int    rWidth;
	private int    rHeight;
	private int    scale;
	private String title;
	private Image  icon;

	private JFrame frame;
	private Thread thread;
	private ImageS screen;

	private boolean running              = false;
	private boolean showUFC              = false;
	private boolean lockCursorIn         = false;
	private boolean useKeyboard          = false;
	private boolean useMouse             = false;
	private boolean useGamepad           = false;
	private boolean initFrameUndecorated = false;
	private boolean frameRedecorating    = false;

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
	public Display() {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_SCALE, DEFAULT_TITLE, DEFAULT_ICON);
	}

	/**
	 * A custom sized {@code Display}, with a default resolution, title, and
	 * icon
	 * 
	 * @param width
	 *            The width to set
	 * @param height
	 *            The height to set
	 */
	public Display(int width, int height) {
		this(width, height, DEFAULT_SCALE, DEFAULT_TITLE, DEFAULT_ICON);
	}

	/**
	 * A default 640 x 480 {@code Display}, with a custom title and a default
	 * icon
	 * 
	 * @param title
	 *            The title to set
	 */
	public Display(String title) {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_SCALE, title, DEFAULT_ICON);
	}

	/**
	 * A default 640 x 480 {@code Display}, with a default title and a custom
	 * icon
	 * 
	 * @param icon
	 *            The icon to set
	 */
	public Display(Image icon) {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_SCALE, DEFAULT_TITLE, icon);
	}

	/**
	 * A custom sized and titled {@code Display}, with a default resolution and
	 * icon
	 * 
	 * @param width
	 *            The width to set
	 * @param height
	 *            The height to set
	 * @param title
	 *            The title to set
	 */
	public Display(int width, int height, String title) {
		this(width, height, DEFAULT_SCALE, title, DEFAULT_ICON);
	}

	/**
	 * A custom sized and iconed {@code Display}, with a default title
	 * 
	 * @param width
	 *            The width to set
	 * @param height
	 *            The height to set
	 * @param icon
	 *            The icon to set
	 */
	public Display(int width, int height, Image icon) {
		this(width, height, DEFAULT_SCALE, DEFAULT_TITLE, icon);
	}

	/**
	 * A default 640 x 480 {@code Display}, with a custom title and icon
	 * 
	 * @param title
	 *            The title to set
	 * @param icon
	 *            The icon to set
	 */
	public Display(String title, Image icon) {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_SCALE, title, icon);
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
	public Display(int width, int height, String title, Image icon) {
		this(width, height, DEFAULT_SCALE, title, icon);
	}

	/**
	 * A custom sized {@code Display}, with a default resolution, title, and
	 * icon
	 * 
	 * @param width
	 *            The width to set
	 * @param height
	 *            The height to set
	 * @param scale
	 *            The scale of the pixels
	 */
	public Display(int width, int height, int scale) {
		this(width, height, scale, DEFAULT_TITLE, DEFAULT_ICON);
	}

	/**
	 * A custom sized and titled {@code Display}, with a default resolution and
	 * icon
	 * 
	 * @param width
	 *            The width to set
	 * @param height
	 *            The height to set
	 * @param scale
	 *            The scale of the pixels
	 * @param title
	 *            The title to set
	 */
	public Display(int width, int height, int scale, String title) {
		this(width, height, scale, title, DEFAULT_ICON);
	}

	/**
	 * A custom sized and iconed {@code Display}, with a default title
	 * 
	 * @param width
	 *            The width to set
	 * @param height
	 *            The height to set
	 * @param scale
	 *            The scale of the pixels
	 * @param icon
	 *            The icon to set
	 */
	public Display(int width, int height, int scale, Image icon) {
		this(width, height, scale, DEFAULT_TITLE, icon);
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
	public Display(int width, int height, int scale, String title, Image icon) {
		this.rWidth  = width;
		this.rHeight = height;
		this.scale   = scale;

		setSize(width, height, scale);
		setTitle(title);
		setIcon(icon);

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
	 * Sets the title for this {@code Display}'s {@code JFrame}
	 * 
	 * @param title
	 *            The title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Sets the icon for this {@code Display}'s {@code JFrame}
	 * 
	 * @param icon
	 *            The icon to set
	 */
	public void setIcon(Image icon) {
		this.icon = icon;
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

	/**
	 * Enables or disables one or more of several properties:
	 * 
	 * <pre>
	 * public static final int ANTIALIAS_SHAPES = 0x01;
	 * public static final int ANTIALIAS_TEXT = 0x02;
	 * public static final int SHOW_UPDATE_FPS_COUNTER = 0x04;
	 * public static final int LOCK_CURSOR_IN = 0x08;
	 * public static final int USE_KEYBOARD = 0x10;
	 * public static final int USE_MOUSE = 0x20;
	 * public static final int USE_GAMEPAD = 0x40;
	 * public static final int SHOW_FRAME = 0x80;
	 * public static final int ALL = 0xff;
	 * </pre>
	 * 
	 * These properties are bit flags, meaning that you can chain multiple
	 * properties in one call to this method
	 * 
	 * <pre>
	 * display.enableProperty(ANTIALIAS_SHAPES | USE_MOUSE | USE_KEYBLOARD, true);
	 * </pre>
	 */
	public void enableProperties(int properties, boolean enable) {
		if ((properties & ANTIALIAS_SHAPES) == ANTIALIAS_SHAPES)
			if (enable)
				renderHints.put(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
			else
				renderHints.put(KEY_ANTIALIASING, VALUE_ANTIALIAS_OFF);

		if ((properties & ANTIALIAS_TEXT) == ANTIALIAS_TEXT)
			if (enable)
				renderHints.put(KEY_TEXT_ANTIALIASING, VALUE_TEXT_ANTIALIAS_ON);
			else
				renderHints.put(KEY_TEXT_ANTIALIASING, VALUE_TEXT_ANTIALIAS_OFF);

		if ((properties & SHOW_UPDATE_FPS_COUNTER) == SHOW_UPDATE_FPS_COUNTER)
			showUFC = enable;

		if ((properties & LOCK_CURSOR_IN) == LOCK_CURSOR_IN)
			lockCursorIn = enable;

		if ((properties & USE_KEYBOARD) == USE_KEYBOARD) {
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

		if ((properties & USE_MOUSE) == USE_MOUSE) {
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

		if ((properties & USE_GAMEPAD) == USE_GAMEPAD) {
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

		if ((properties & SHOW_FRAME) == SHOW_FRAME) {
			if (frame != null) {
				frameRedecorating = true;

				frame.setVisible(false);
				frame.dispose();
				frame.setUndecorated(!enable);
				frame.setVisible(true);

				frameRedecorating = false;
			} else {
				initFrameUndecorated = !enable;
			}
		}
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
		String initTitle = showUFC ? title + "  |  UPS: 60, FPS: 60" : title;

		frame = new JFrame(initTitle);
		
		frame.setIconImage(icon);
		frame.add(this);
		frame.setFocusable(false);
		frame.setResizable(false);
		frame.setUndecorated(initFrameUndecorated);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				close();
			}
		});
		frame.setVisible(true);

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

		thread = new Thread(this, "ld_display_game_loop");

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
		long last  = nsTime();
		long timer = msTime();

		final double nanos = 1000000000.0 / 60.0;

		double delta = 0;

		int updates = 0;
		int frames  = 0;

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

				if (showUFC)
					frame.setTitle(String.format("%s  |  UPS: %d, FPS: %d", title, updates, frames));

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
		if (!frameRedecorating) {
			GraphicsS g = new GraphicsS((Graphics2D) screen.getGraphics());
			
			g.setRenderingHints(renderHints);
			g.setColor(0);
			g.fill(getScreenBounds());

			manager.render(g);

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
	}

	private ImageSFilter[] getFilterList() {
		return filters.toArray(new ImageSFilter[filters.size()]);
	}
}