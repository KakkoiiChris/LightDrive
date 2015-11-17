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
package net.alexanderdev.lightdrive.input;

import static java.awt.event.MouseEvent.*;
import static net.alexanderdev.lightdrive.util.Time.*;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import net.alexanderdev.lightdrive.Internal;
import net.alexanderdev.lightdrive.media.graphics.Screen;
import net.alexanderdev.lightdrive.util.math.MathS;
import net.alexanderdev.lightdrive.util.math.geom.PointD;
import net.alexanderdev.lightdrive.util.math.geom.PointF;
import net.alexanderdev.lightdrive.util.math.geom.VectorD;
import net.alexanderdev.lightdrive.util.math.geom.VectorF;

/**
 * A class which encapsulates the {@code MouseListener},
 * {@code MouseMotionListener}, and {@code MouseWheelListener} interfaces and
 * enables more game-friendly functionality.
 * 
 * @author Christian Bryce Alexander
 * @since March 6, 2015 | 2:44:04 PM
 */
public final class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {
	private static final Button[] BUTTONS = new Button[16];

	public final Button LEFT_BUTTON = new Button(BUTTON1);
	public final Button MIDDLE_BUTTON = new Button(BUTTON2);
	public final Button RIGHT_BUTTON = new Button(BUTTON3);

	private Point prevPoint = new Point(0, 0);
	private Point point = new Point(0, 0);

	private boolean inScreen = false;
	private boolean isAltDown = false;
	private boolean isControlDown = false;
	private boolean isShiftDown = false;
	private boolean wheelRotating = false;
	private boolean dragging = false;

	private int wheelRotation = 0;

	private Screen screen;

	private Robot robot;

	{
		addButton(LEFT_BUTTON);
		addButton(MIDDLE_BUTTON);
		addButton(RIGHT_BUTTON);

		try {
			robot = new Robot();
		}
		catch (AWTException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a mouse which is linked to a {@code Display}
	 */
	public Mouse(Screen screen) {
		this.screen = screen;
	}

	/**
	 * Represents the current state of a single button on the mouse.
	 */
	public static class Button {
		private final long TIMEOUT = 50;

		private boolean held = false;
		private boolean pressed = false;
		private boolean released = false;

		private int number;

		private long actionTimer;

		/**
		 * Creates a button which is mapped to a int from the {@code MouseEvent}
		 * class
		 */
		public Button(int number) {
			this.number = number;
		}

		void toggle(boolean held) {
			if (held && !this.held) {
				pressed = true;
				actionTimer = msTime();
			}

			if (!held && this.held) {
				released = true;
				actionTimer = msTime();
			}

			this.held = held;
		}

		/**
		 * Will only return {@code true} the first time it is checked after a
		 * mouse button is pressed<br>
		 * Since mouse button states are continuous, this function simulates a
		 * {@code MouseEvent} firing only once<br>
		 * 
		 * @return {@code true} first time called after mouse button is pressed
		 *         <br>
		 *         {@code false} otherwise
		 */
		public boolean pressed() {
			if (pressed) {
				pressed = false;
				return msTime() - actionTimer < TIMEOUT;
			}
			return false;
		}

		/**
		 * Will only return {@code true} the first time it is checked after a
		 * mouse button is released<br>
		 * Since mouse button states are continuous, this function simulates a
		 * {@code MouseEvent} firing only once<br>
		 * <br>
		 * 
		 * @return {@code true} first time called after mouse button is released
		 *         <br>
		 *         {@code false} otherwise
		 */
		public boolean released() {
			if (released) {
				released = false;
				return msTime() - actionTimer < TIMEOUT;
			}
			return false;
		}

		/**
		 * @return {@code true} if the {@code Button} is currently being held
		 *         down<br>
		 *         {@code false} otherwise
		 */
		public boolean held() {
			return held;
		}

		/**
		 * @return The {@code MouseEvent} int mapped to this {@code Button}
		 */
		public int getNumber() {
			return number;
		}
	}

	/**
	 * Maps any valid {@code MouseEvent} int to a new {@code Button} by name
	 *
	 * @param name
	 *            A custom name for this new {@code Key}
	 * @param buttonNumber
	 *            A valid {@code MouseEvent} int
	 */
	public static void addButton(Button button) {
		BUTTONS[button.number] = button;
	}

	/**
	 * @return The location of the mouse cursor relative to the {@code Display}
	 */
	public Point getPoint() {
		return point;
	}

	private Point getMouseLocation() {
		Point point = MouseInfo.getPointerInfo().getLocation();
		Point location = screen.getLocationOnScreen();

		point.x -= location.x;
		point.y -= location.y;

		return point;
	}

	/**
	 * @return The X coordinate of the mouse cursor relative to the
	 *         {@code Display}
	 */
	public int getX() {
		return point.x;
	}

	/**
	 * @return The Y coordinate of the mouse cursor relative to the
	 *         {@code Display}
	 */
	public int getY() {
		return point.y;
	}

	/**
	 * @return The difference between the current and previous X coordinates of
	 *         the mouse cursor
	 */
	public int getDeltaX() {
		return point.x - prevPoint.x;
	}

	/**
	 * @return The difference between the current and previous Y coordinates of
	 *         the mouse cursor
	 */
	public int getDeltaY() {
		return point.y - prevPoint.y;
	}

	/**
	 * @return A {@code VectorF} pointing from the previous point to the current
	 *         point of the mouse cursor
	 */
	public VectorF getDeltaVectorF() {
		VectorF vec = new VectorF(prevPoint.x, prevPoint.y);

		PointF pt = new PointF(point.x, point.y);

		return vec.vectorTo(pt);
	}

	/**
	 * @return A {@code VectorD} pointing from the previous point to the current
	 *         point of the mouse cursor
	 */
	public VectorD getDeltaVectorD() {
		VectorD vec = new VectorD(prevPoint.x, prevPoint.y);

		PointD pt = new PointD(point.x, point.y);

		return vec.vectorTo(pt);
	}

	/**
	 * @return {@code true} if the mouse is currently moving<br>
	 *         {@code false} otherwise
	 */
	public boolean isMoving() {
		return !(getDeltaX() == 0 && getDeltaY() == 0);
	}

	/**
	 * @return {@code true} if the mouse is currently being dragged<br>
	 *         {@code false} otherwise
	 */
	public boolean isDragging() {
		return dragging;
	}

	/**
	 * @return {@code true} if the mouse cursor is within the bounds of the
	 *         {@code Display}<br>
	 *         {@code false} otherwise
	 */
	public boolean inScreen() {
		return inScreen;
	}

	/**
	 * @return {@code true} if the alt key was pressed during past mouse events
	 *         <br>
	 *         {@code false} otherwise
	 */
	public boolean isAltDown() {
		return isAltDown;
	}

	/**
	 * @return {@code true} if the control key was pressed during past mouse
	 *         events<br>
	 *         {@code false} otherwise
	 */
	public boolean isControlDown() {
		return isControlDown;
	}

	/**
	 * @return {@code true} if the shift key was pressed during past mouse
	 *         events<br>
	 *         {@code false} otherwise
	 */
	public boolean isShiftDown() {
		return isShiftDown;
	}

	/**
	 * @return The amount of clicks the mouse wheel has made since the last
	 *         update
	 */
	public int getWheelRotation() {
		if (!wheelRotating)
			wheelRotation = 0;

		wheelRotating = false;

		return wheelRotation;
	}

	@Internal
	public void update() {
		prevPoint.setLocation(point);

		point = getMouseLocation();

		point.x /= screen.getScale();
		point.y /= screen.getScale();

		if (screen.cursorLocked()) {
			int xMin = point.x;
			int xMax = xMin + screen.getWidth();
			int yMin = point.y;
			int yMax = yMin + screen.getHeight();

			robot.mouseMove(MathS.clamp(xMin + getX(), xMin, xMax), MathS.clamp(yMin + getY(), yMin, yMax));
		}
	}

	private void setModifiers(MouseEvent e) {
		isAltDown = e.isAltDown();
		isControlDown = e.isControlDown();
		isShiftDown = e.isShiftDown();
	}

	@Internal
	@Override
	public void mouseClicked(MouseEvent e) {
		setModifiers(e);
	}

	@Internal
	@Override
	public void mousePressed(MouseEvent e) {
		int number = e.getButton();

		BUTTONS[number].toggle(true);

		point.setLocation(e.getPoint());

		setModifiers(e);
	}

	@Internal
	@Override
	public void mouseReleased(MouseEvent e) {
		int number = e.getButton();

		BUTTONS[number].toggle(false);

		point.setLocation(e.getPoint());

		dragging = false;

		setModifiers(e);
	}

	@Internal
	@Override
	public void mouseEntered(MouseEvent e) {
		inScreen = true;

		setModifiers(e);
	}

	@Internal
	@Override
	public void mouseExited(MouseEvent e) {
		inScreen = false;

		setModifiers(e);
	}

	@Internal
	@Override
	public void mouseMoved(MouseEvent e) {
		setModifiers(e);
	}

	@Internal
	@Override
	public void mouseDragged(MouseEvent e) {
		dragging = true;

		setModifiers(e);
	}

	@Internal
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		wheelRotation = e.getWheelRotation();

		wheelRotating = true;

		setModifiers(e);
	}
}