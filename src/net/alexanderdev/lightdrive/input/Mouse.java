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

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import net.alexanderdev.lightdrive.InternalMethod;
import net.alexanderdev.lightdrive.view.Viewable;

/**
 * A class which encapsulates the {@code MouseListener},
 * {@code MouseMotionListener}, and {@code MouseWheelListener} interfaces and
 * enables more game-friendly functionality.
 * 
 * @author Christian Bryce Alexander
 * @since March 6, 2015 | 2:44:04 PM
 */
public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {
	public static final int ANY = -1;
	public static final int NONE = MouseEvent.NOBUTTON;
	public static final int LEFT = MouseEvent.BUTTON1;
	public static final int MIDDLE = MouseEvent.BUTTON2;
	public static final int RIGHT = MouseEvent.BUTTON3;

	private Viewable view;

	private final boolean[] BUTTONS = new boolean[4];

	private boolean[] buttonsLast;

	private boolean inDisplay;
	private boolean moving;
	private boolean dragging;
	private boolean controlDown;
	private boolean altDown;
	private boolean shiftDown;

	private Point point, lastPoint;

	private int wheelRotation;

	public Mouse(Viewable view) {
		this.view = view;

		buttonsLast = BUTTONS.clone();

		inDisplay = true;

		moving = dragging = false;

		wheelRotation = 0;

		point = new Point(0, 0);
		lastPoint = new Point(0, 0);
	}

	public boolean buttonPressed(int button) {
		if (button == ANY) {
			for (int i = 0; i < BUTTONS.length; i++)
				if (BUTTONS[i] && !buttonsLast[i])
					return true;
			return false;
		}
		return BUTTONS[button] && !buttonsLast[button];
	}

	public boolean buttonHeld(int button) {
		if (button == ANY) {
			for (int i = 0; i < BUTTONS.length; i++)
				if (BUTTONS[i])
					return true;
			return false;
		}
		return BUTTONS[button];
	}

	public boolean buttonReleased(int button) {
		if (button == ANY) {
			for (int i = 0; i < BUTTONS.length; i++)
				if (!BUTTONS[i] && buttonsLast[i])
					return true;
			return false;
		}
		return !BUTTONS[button] && buttonsLast[button];
	}

	public int getX() {
		return point.x;
	}

	public int getY() {
		return point.y;
	}

	public Point getPoint() {
		return point;
	}

	public int getDeltaX() {
		return point.x - lastPoint.x;
	}

	public int getDeltaY() {
		return point.y - lastPoint.y;
	}

	public boolean isInDisplay() {
		return inDisplay;
	}

	public boolean isMoving() {
		return moving;
	}

	public boolean isDragging() {
		return dragging;
	}

	public boolean isControlDown() {
		return controlDown;
	}

	public boolean isAltDown() {
		return altDown;
	}

	public boolean isShiftDown() {
		return shiftDown;
	}

	public int getWheelRotation() {
		return wheelRotation;
	}

	@InternalMethod
	public void update() {
		buttonsLast = BUTTONS.clone();

		if (moving && lastPoint.x == point.x && lastPoint.y == point.y)
			moving = false;

		lastPoint.x = point.x;
		lastPoint.y = point.y;

		wheelRotation = 0;
	}

	private void setModifiers(MouseEvent e) {
		controlDown = e.isControlDown();
		altDown = e.isAltDown();
		shiftDown = e.isShiftDown();
	}

	@Override
	@InternalMethod
	public void mouseClicked(MouseEvent e) {
		e.consume();
	}

	@Override
	@InternalMethod
	public void mousePressed(MouseEvent e) {
		BUTTONS[e.getButton()] = true;

		setModifiers(e);

		e.consume();
	}

	@Override
	@InternalMethod
	public void mouseReleased(MouseEvent e) {
		BUTTONS[e.getButton()] = false;

		setModifiers(e);

		dragging = false;

		e.consume();
	}

	@Override
	@InternalMethod
	public void mouseEntered(MouseEvent e) {
		inDisplay = true;

		e.consume();
	}

	@Override
	@InternalMethod
	public void mouseExited(MouseEvent e) {
		inDisplay = false;

		e.consume();
	}

	@Override
	@InternalMethod
	public void mouseMoved(MouseEvent e) {
		moving = true;

		point.x = e.getX() / view.getViewScale();
		point.y = e.getY() / view.getViewScale();

		e.consume();
	}

	@Override
	@InternalMethod
	public void mouseDragged(MouseEvent e) {
		dragging = true;

		point.x = e.getX() / view.getViewScale();
		point.y = e.getY() / view.getViewScale();

		e.consume();
	}

	@Override
	@InternalMethod
	public void mouseWheelMoved(MouseWheelEvent e) {
		wheelRotation += e.getWheelRotation();

		setModifiers(e);

		e.consume();
	}
}