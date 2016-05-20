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
package net.alexanderdev.lightdrive.input.gamepad;

import net.alexanderdev.lightdrive.InternalMethod;
import net.alexanderdev.lightdrive.util.math.geom.VectorF;
import net.java.games.input.Component;
import net.java.games.input.Controller;

/**
 * A class which encapsulates a {@link Controller} of type
 * {@link Controller.Type#GAMEPAD}, and polls all appropriate {@link Component}s
 * on its own thread.
 * 
 * @author Christian Bryce Alexander
 * @since Apr 29, 2015 | 9:11:58 PM
 */
public class Gamepad implements Runnable {
	private Controller controller;

	private int number;

	private float deadZone;

	private Thread thread;
	private boolean running;

	private final boolean[] BUTTONS = new boolean[14];
	private boolean[] buttonsLast;

	private final VectorF LEFT_STICK = new VectorF();
	private VectorF leftStickLast;

	private final VectorF RIGHT_STICK = new VectorF();
	private VectorF rightStickLast;

	private float triggers;
	private float triggersLast;

	/**
	 * Creates a {@link Gamepad} with an associated {@link Controller} and
	 * {@code number}.
	 * 
	 * @param controller
	 *            The {@link Controller} to associate with this {@link Gamepad}
	 * @param number
	 *            The {@code number} to associate with this {@link Gamepad}
	 */
	public Gamepad(Controller controller, int number) {
		this.controller = controller;
		this.number = number;

		deadZone = 0.2f;

		buttonsLast = BUTTONS.clone();

		leftStickLast = LEFT_STICK.clone();
		rightStickLast = RIGHT_STICK.clone();

		triggers = 0f;
		triggersLast = triggers;
	}

	/**
	 * @return The number associated with this {@link Gamepad}
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @return The dead zone of this {@link Gamepad}
	 */
	public float getDeadZone() {
		return deadZone;
	}

	/**
	 * Sets the dead zone for this {@link Gamepad}'s sticks and triggers.
	 * 
	 * @param deadZone
	 *            The dead zone to set
	 */
	public void setDeadZone(float deadZone) {
		this.deadZone = deadZone;
	}

	/**
	 * @return {@code true} if the specified {@link Button} equals
	 *         {@link Gamepad#ANY} and any button has been pressed, or if the
	 *         specified {@link Button} is associated with a button that has
	 *         been pressed, and {@code false} otherwise
	 */
	public boolean buttonPressed(Button button) {
		return BUTTONS[button.ordinal()] && !buttonsLast[button.ordinal()];
	}

	public boolean anyButtonPressed() {
		for (int i = 0; i < BUTTONS.length; i++)
			if (BUTTONS[i] && !buttonsLast[i])
				return true;
		return false;
	}

	/**
	 * @return {@code true} if the specified {@link Button} equals
	 *         {@link Gamepad#ANY} and any button is being held, or if the
	 *         specified {@link Button} is associated with a button that is
	 *         being held, and {@code false} otherwise
	 */
	public boolean buttonHeld(Button button) {
		return BUTTONS[button.ordinal()];
	}

	public boolean anyButtonHeld() {
		for (int i = 0; i < BUTTONS.length; i++)
			if (BUTTONS[i])
				return true;
		return false;
	}

	/**
	 * @return {@code true} if the specified {@link Button} equals
	 *         {@link Gamepad#ANY} and any button has been released, or if the
	 *         specified {@link Button} is associated with a button that has
	 *         been released, and {@code false} otherwise
	 */
	public boolean buttonReleased(Button button) {
		return !BUTTONS[button.ordinal()] && buttonsLast[button.ordinal()];
	}

	public boolean anyButtonReleased() {
		for (int i = 0; i < BUTTONS.length; i++)
			if (!BUTTONS[i] && buttonsLast[i])
				return true;
		return false;
	}

	/**
	 * @return The horizontal position of the left stick as a value between -1f
	 *         and 1f
	 */
	public float getLeftStickX() {
		if (LEFT_STICK.length() < deadZone && LEFT_STICK.length() > -deadZone)
			return 0f;
		return LEFT_STICK.x;
	}

	/**
	 * @return The change in horizontal position of the left stick as a value
	 *         between -1f and 1f
	 */
	public float getLeftStickDeltaX() {
		return LEFT_STICK.x - leftStickLast.x;
	}

	/**
	 * @return The vertical position of the left stick as a value between -1f
	 *         and 1f
	 */
	public float getLeftStickY() {
		if (LEFT_STICK.length() < deadZone && LEFT_STICK.length() > -deadZone)
			return 0f;
		return LEFT_STICK.y;
	}

	/**
	 * @return The change in vertical position of the left stick as a value
	 *         between -1f and 1f
	 */
	public float getLeftStickDeltaY() {
		return LEFT_STICK.y - leftStickLast.y;
	}

	/**
	 * @return The positions of the left stick as a {@link VectorF}
	 */
	public VectorF getLeftStickVector() {
		if (LEFT_STICK.length() < deadZone && LEFT_STICK.length() > -deadZone)
			return new VectorF(0f, 0f);
		return LEFT_STICK;
	}

	/**
	 * @return The horizontal position of the right stick as a value between -1f
	 *         and 1f
	 */
	public float getRightStickX() {
		if (RIGHT_STICK.length() < deadZone && RIGHT_STICK.length() > -deadZone)
			return 0f;
		return RIGHT_STICK.x;
	}

	/**
	 * @return The change in horizontal position of the right stick as a value
	 *         between -1f and 1f
	 */
	public float getRightStickDeltaX() {
		return RIGHT_STICK.x - rightStickLast.x;
	}

	/**
	 * @return The vertical position of the right stick as a value between -1f
	 *         and 1f
	 */
	public float getRightStickY() {
		if (RIGHT_STICK.length() < deadZone && RIGHT_STICK.length() > -deadZone)
			return 0f;
		return RIGHT_STICK.y;
	}

	/**
	 * @return The change in vertical position of the right stick as a value
	 *         between -1f and 1f
	 */
	public float getRightStickDeltaY() {
		return RIGHT_STICK.y - rightStickLast.y;
	}

	/**
	 * @return The positions of the right stick as a {@link VectorF}
	 */
	public VectorF getRightStickVector() {
		if (RIGHT_STICK.length() < deadZone && RIGHT_STICK.length() > -deadZone)
			return new VectorF(0f, 0f);
		return RIGHT_STICK;
	}

	/**
	 * <b>NOTE: With the {@link Controller} class, the triggers are considered
	 * as one axis, and therefore, cannot register magnitudes separately.</b>
	 * 
	 * @return The magnitude of the left trigger as a value between 0f and 1f
	 */
	public float getLeftTrigger() {
		if (triggers < deadZone)
			return 0f;
		return triggers;
	}

	/**
	 * <b>NOTE: With the {@link Controller} class, the triggers are considered
	 * as one axis, and therefore, cannot register magnitudes separately.</b>
	 * 
	 * @return The change in the magnitude of the left trigger as a value
	 *         between 0f and 1f
	 */
	public float getLeftTriggerDelta() {
		if (triggers < 0f)
			return 0f;
		return triggers - triggersLast;
	}

	/**
	 * <b>NOTE: With the {@link Controller} class, the triggers are considered
	 * as one axis, and therefore, cannot register magnitudes separately.</b>
	 * 
	 * @return The magnitude of the right trigger as a value between 0f and 1f
	 */
	public float getRightTrigger() {
		if (triggers > -deadZone)
			return 0f;
		return -triggers;
	}

	/**
	 * <b>NOTE: With the {@link Controller} class, the triggers are considered
	 * as one axis, and therefore, cannot register magnitudes separately.</b>
	 * 
	 * @return The change in the magnitude of the right trigger as a value
	 *         between 0f and 1f
	 */
	public float getRightTriggerDelta() {
		if (triggers > 0f)
			return 0f;
		return -triggers - triggersLast;
	}

	@InternalMethod
	public synchronized void start() {
		if (running)
			return;

		running = true;

		thread = new Thread(this, "light_drive_gamepad_" + number);

		thread.start();
	}

	@InternalMethod
	public synchronized void stop() {
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

	@InternalMethod
	public void update() {
		buttonsLast = BUTTONS.clone();

		leftStickLast = LEFT_STICK.clone();
		rightStickLast = RIGHT_STICK.clone();

		triggersLast = triggers;
	}

	@Override
	@InternalMethod
	public void run() {
		while (running) {
			if (!controller.poll()) {
				System.err.println("LIGHT DRIVE ERROR: CONTROLLER DISCONNECTED: " + controller.getName());
				running = false;
				continue;
			}

			for (Component component : controller.getComponents()) {
				String identifier = component.getIdentifier().getName();

				float data = component.getPollData();

				if (identifier.equals("x")) {
					LEFT_STICK.x = data;
				}
				else if (identifier.equals("y")) {
					LEFT_STICK.y = data;
				}
				else if (identifier.equals("rx")) {
					RIGHT_STICK.x = data;
				}
				else if (identifier.equals("ry")) {
					RIGHT_STICK.y = data;
				}
				else if (identifier.equals("z")) {
					triggers = data;
				}
				else if (identifier.equals("pov")) {
					if (data == 0f) {
						BUTTONS[Button.UP.ordinal()] = false;
						BUTTONS[Button.RIGHT.ordinal()] = false;
						BUTTONS[Button.DOWN.ordinal()] = false;
						BUTTONS[Button.LEFT.ordinal()] = false;
					}
					else if (data == 0.125f) {
						BUTTONS[Button.UP.ordinal()] = true;
						BUTTONS[Button.RIGHT.ordinal()] = false;
						BUTTONS[Button.DOWN.ordinal()] = false;
						BUTTONS[Button.LEFT.ordinal()] = true;
					}
					else if (data == 0.25f) {
						BUTTONS[Button.UP.ordinal()] = true;
						BUTTONS[Button.RIGHT.ordinal()] = false;
						BUTTONS[Button.DOWN.ordinal()] = false;
						BUTTONS[Button.LEFT.ordinal()] = false;
					}
					else if (data == 0.375f) {
						BUTTONS[Button.UP.ordinal()] = true;
						BUTTONS[Button.RIGHT.ordinal()] = true;
						BUTTONS[Button.DOWN.ordinal()] = false;
						BUTTONS[Button.LEFT.ordinal()] = false;
					}
					else if (data == 0.5f) {
						BUTTONS[Button.UP.ordinal()] = false;
						BUTTONS[Button.RIGHT.ordinal()] = true;
						BUTTONS[Button.DOWN.ordinal()] = false;
						BUTTONS[Button.LEFT.ordinal()] = false;
					}
					else if (data == 0.625f) {
						BUTTONS[Button.UP.ordinal()] = false;
						BUTTONS[Button.RIGHT.ordinal()] = true;
						BUTTONS[Button.DOWN.ordinal()] = true;
						BUTTONS[Button.LEFT.ordinal()] = false;
					}
					else if (data == 0.75f) {
						BUTTONS[Button.UP.ordinal()] = false;
						BUTTONS[Button.RIGHT.ordinal()] = false;
						BUTTONS[Button.DOWN.ordinal()] = true;
						BUTTONS[Button.LEFT.ordinal()] = false;
					}
					else if (data == 0.875f) {
						BUTTONS[Button.UP.ordinal()] = false;
						BUTTONS[Button.RIGHT.ordinal()] = false;
						BUTTONS[Button.DOWN.ordinal()] = true;
						BUTTONS[Button.LEFT.ordinal()] = true;
					}
					else if (data == 1f) {
						BUTTONS[Button.UP.ordinal()] = false;
						BUTTONS[Button.RIGHT.ordinal()] = false;
						BUTTONS[Button.DOWN.ordinal()] = false;
						BUTTONS[Button.LEFT.ordinal()] = true;
					}
				}
				else {
					BUTTONS[Integer.parseInt(identifier)] = (data == 1f);
				}
			}
		}
	}
}