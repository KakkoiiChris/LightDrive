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
package net.alexanderdev.lightdrive.input.mouse;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import net.alexanderdev.lightdrive.InternalMethod;
import net.alexanderdev.lightdrive.view.Viewable;

/**
 * A class which encapsulates the {@link MouseListener},
 * {@link MouseMotionListener}, and {@link MouseWheelListener} interfaces and
 * enables more game-friendly functionality.
 * 
 * @author Christian Bryce Alexander
 * @since March 6, 2015, 2:44:04 PM
 */
public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {
	private Viewable view;

	private final boolean[] BUTTONS = new boolean[Button.values().length];

	private boolean[] buttonsLast;

	private boolean withinView;
	private boolean moving;
	private boolean dragging;
	private boolean controlDown;
	private boolean altDown;
	private boolean shiftDown;

	private Point point, lastPoint;

	private int wheelRotation;

	/**
	 * Creates a {@link Mouse} associated with the specified {@link Viewable}.
	 * 
	 * @param view
	 *            The {@link Viewable} to associate with this {@link Mouse}
	 */
	public Mouse(Viewable view) {
		this.view = view;

		buttonsLast = BUTTONS.clone();

		withinView = true;

		moving = false;
		dragging = false;
		controlDown = false;
		altDown = false;
		shiftDown = false;

		wheelRotation = 0;

		point = new Point(0, 0);
		lastPoint = new Point(0, 0);
	}

	/**
	 * @param button
	 *            The mouse button to check
	 * @return {@code true} if the button associated with the specified
	 *         {@link Button} 's ordinal has been pressed, {@code false}
	 *         otherwise
	 */
	public boolean buttonPressed(Button button) {
		return BUTTONS[button.ordinal()] && !buttonsLast[button.ordinal()];
	}

	/**
	 * @return {@code true} if any button has been pressed, {@code false}
	 *         otherwise
	 */
	public boolean anyButtonPressed() {
		for (int i = 0; i < BUTTONS.length; i++)
			if (BUTTONS[i] && !buttonsLast[i])
				return true;
		return false;
	}

	/**
	 * @param button
	 *            The mouse button to check
	 * @return {@code true} if the button associated with the specified
	 *         {@link Button} 's ordinal is being held, {@code false} otherwise
	 */
	public boolean buttonHeld(Button button) {
		return BUTTONS[button.ordinal()];
	}

	/**
	 * @return {@code true} if any button is being held, {@code false} otherwise
	 */
	public boolean anyButtonHeld() {
		for (int i = 0; i < BUTTONS.length; i++)
			if (BUTTONS[i])
				return true;
		return false;
	}

	/**
	 * @param button
	 *            The mouse button to check
	 * @return {@code true} if the button associated with the specified
	 *         {@link Button} 's ordinal has been released, {@code false}
	 *         otherwise
	 */
	public boolean buttonReleased(Button button) {
		return !BUTTONS[button.ordinal()] && buttonsLast[button.ordinal()];
	}

	/**
	 * @return {@code true} if any button has been released, {@code false}
	 *         otherwise
	 */
	public boolean anyButtonReleased() {
		for (int i = 0; i < BUTTONS.length; i++)
			if (!BUTTONS[i] && buttonsLast[i])
				return true;
		return false;
	}

	/**
	 * @return The per-pixel x coordinate of the mouse relative to the
	 *         associated {@link Viewable}
	 */
	public int getX() {
		return point.x;
	}

	/**
	 * @return The per-pixel y coordinate of the mouse relative to the
	 *         associated {@link Viewable}
	 */
	public int getY() {
		return point.y;
	}

	/**
	 * @return The per-pixel coordinates of the mouse relative to the associated
	 *         {@link Viewable}
	 */
	public Point getPoint() {
		return point;
	}

	/**
	 * @return The per-pixel change in the x coordinate of the mouse relative to
	 *         the associated {@link Viewable}
	 */
	public int getDeltaX() {
		return point.x - lastPoint.x;
	}

	/**
	 * @return The per-pixel change in the y coordinate of the mouse relative to
	 *         the associated {@link Viewable}
	 */
	public int getDeltaY() {
		return point.y - lastPoint.y;
	}

	/**
	 * @return {@code true} if the mouse pointer is withing the bounds of the
	 *         associated {@link Viewable}, {@code false} otherwise
	 */
	public boolean isWithinView() {
		return withinView;
	}

	/**
	 * @return {@code true} if the mouse pointer is moving, {@code false}
	 *         otherwise
	 */
	public boolean isMoving() {
		return moving;
	}

	/**
	 * @return {@code true} if the mouse pointer is moving while a button is
	 *         held, {@code false} otherwise
	 */
	public boolean isDragging() {
		return dragging;
	}

	/**
	 * @return {@code true} if the ctrl key is held simultaneously,
	 *         {@code false} otherwise
	 */
	public boolean isControlDown() {
		return controlDown;
	}

	/**
	 * @return {@code true} if the alt key is held simultaneously, {@code false}
	 *         otherwise
	 */
	public boolean isAltDown() {
		return altDown;
	}

	/**
	 * @return {@code true} if the shift key is held simultaneously,
	 *         {@code false} otherwise
	 */
	public boolean isShiftDown() {
		return shiftDown;
	}

	/**
	 * @return The amount of clicks the mouse wheel has moved since the last
	 *         update
	 */
	public int getWheelRotation() {
		return wheelRotation;
	}

	@InternalMethod
	public void update() {
		buttonsLast = BUTTONS.clone();

		if (moving && lastPoint.x == point.x && lastPoint.y == point.y)
			moving = dragging = false;

		lastPoint.x = point.x;
		lastPoint.y = point.y;

		wheelRotation = 0;
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
		withinView = true;

		e.consume();
	}

	@Override
	@InternalMethod
	public void mouseExited(MouseEvent e) {
		withinView = false;

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

	private void setModifiers(MouseEvent e) {
		controlDown = e.isControlDown();
		altDown = e.isAltDown();
		shiftDown = e.isShiftDown();
	}
}