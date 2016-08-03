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

import net.alexanderdev.lightdrive.util.math.geom.VectorX;
import net.java.games.input.Component;
import net.java.games.input.Controller;

/**
 * A class which encapsulates a {@link Controller} of type
 * {@link Controller.Type#GAMEPAD}, and polls all appropriate {@link Component}s
 * on its own thread.
 * 
 * @author Christian Bryce Alexander
 * @since Apr 29, 2015, 9:11:58 PM
 */
public class Gamepad implements Runnable {
	private static final VectorX ZERO = new VectorX();

	private Controller controller;

	private int number;

	private double deadZone;

	private Thread thread;
	private boolean running;

	private final boolean[] SWITCHES = new boolean[Switch.values().length];
	private boolean[] switchesLast;

	private final VectorX LEFT_STICK = new VectorX();
	private VectorX leftStickLast;

	private final VectorX RIGHT_STICK = new VectorX();
	private VectorX rightStickLast;

	private double triggers;
	private double triggersLast;

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

		deadZone = 0.2;

		switchesLast = SWITCHES.clone();

		leftStickLast = LEFT_STICK.clone();
		rightStickLast = RIGHT_STICK.clone();

		triggers = 0;
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
	public double getDeadZone() {
		return deadZone;
	}

	/**
	 * Sets the dead zone for this {@link Gamepad}'s sticks and triggers.
	 * 
	 * @param deadZone
	 *            The dead zone to set
	 */
	public void setDeadZone(double deadZone) {
		this.deadZone = deadZone;
	}

	/**
	 * @param zwitch
	 *            The game pad switch to check
	 * @return {@code true} if the switch associated with the specified
	 *         {@link Switch} 's ordinal has been pressed, {@code false}
	 *         otherwise
	 */
	public boolean pressed(Switch zwitch) {
		return SWITCHES[zwitch.ordinal()] && !switchesLast[zwitch.ordinal()];
	}

	/**
	 * @return {@code true} if any switch has been pressed, {@code false}
	 *         otherwise
	 */
	public boolean anyPressed() {
		for (int i = 0; i < SWITCHES.length; i++)
			if (SWITCHES[i] && !switchesLast[i])
				return true;
		return false;
	}

	/**
	 * @param zwitch
	 *            The game pad switch to check
	 * @return {@code true} if the switch associated with the specified
	 *         {@link Switch} 's ordinal is being held, {@code false} otherwise
	 */
	public boolean held(Switch zwitch) {
		return SWITCHES[zwitch.ordinal()];
	}

	/**
	 * @return {@code true} if any switch is being held, {@code false} otherwise
	 */
	public boolean anyHeld() {
		for (int i = 0; i < SWITCHES.length; i++)
			if (SWITCHES[i])
				return true;
		return false;
	}

	/**
	 * @param zwitch
	 *            The game pad switch to check
	 * @return {@code true} if the switch associated with the specified
	 *         {@link Switch} 's ordinal has been released, {@code false}
	 *         otherwise
	 */
	public boolean released(Switch zwitch) {
		return !SWITCHES[zwitch.ordinal()] && switchesLast[zwitch.ordinal()];
	}

	/**
	 * @return {@code true} if any key has been released, {@code false}
	 *         otherwise
	 */
	public boolean anyReleased() {
		for (int i = 0; i < SWITCHES.length; i++)
			if (!SWITCHES[i] && switchesLast[i])
				return true;
		return false;
	}

	/**
	 * @return The horizontal position of the left stick as a value between -1f
	 *         and 1f
	 */
	public double getLeftStickX() {
		if (LEFT_STICK.length() < deadZone && LEFT_STICK.length() > -deadZone)
			return 0;
		return LEFT_STICK.x;
	}

	/**
	 * @return The change in horizontal position of the left stick as a value
	 *         between -1f and 1f
	 */
	public double getLeftStickDeltaX() {
		return LEFT_STICK.x - leftStickLast.x;
	}

	/**
	 * @return The vertical position of the left stick as a value between -1f
	 *         and 1f
	 */
	public double getLeftStickY() {
		if (LEFT_STICK.length() < deadZone && LEFT_STICK.length() > -deadZone)
			return 0;
		return LEFT_STICK.y;
	}

	/**
	 * @return The change in vertical position of the left stick as a value
	 *         between -1f and 1f
	 */
	public double getLeftStickDeltaY() {
		return LEFT_STICK.y - leftStickLast.y;
	}

	/**
	 * @return The positions of the left stick as a {@link VectorF}
	 */
	public VectorX getLeftStickVector() {
		if (LEFT_STICK.length() < deadZone && LEFT_STICK.length() > -deadZone)
			return ZERO;
		return LEFT_STICK;
	}

	/**
	 * @return The horizontal position of the right stick as a value between -1f
	 *         and 1f
	 */
	public double getRightStickX() {
		if (RIGHT_STICK.length() < deadZone && RIGHT_STICK.length() > -deadZone)
			return 0;
		return RIGHT_STICK.x;
	}

	/**
	 * @return The change in horizontal position of the right stick as a value
	 *         between -1f and 1f
	 */
	public double getRightStickDeltaX() {
		return RIGHT_STICK.x - rightStickLast.x;
	}

	/**
	 * @return The vertical position of the right stick as a value between -1f
	 *         and 1f
	 */
	public double getRightStickY() {
		if (RIGHT_STICK.length() < deadZone && RIGHT_STICK.length() > -deadZone)
			return 0;
		return RIGHT_STICK.y;
	}

	/**
	 * @return The change in vertical position of the right stick as a value
	 *         between -1f and 1f
	 */
	public double getRightStickDeltaY() {
		return RIGHT_STICK.y - rightStickLast.y;
	}

	/**
	 * @return The positions of the right stick as a {@link VectorF}
	 */
	public VectorX getRightStickVector() {
		if (RIGHT_STICK.length() < deadZone && RIGHT_STICK.length() > -deadZone)
			return ZERO;
		return RIGHT_STICK;
	}

	/**
	 * <b>NOTE: With the {@link Controller} class, the triggers are considered
	 * as one axis, and therefore, cannot register magnitudes separately.</b>
	 * 
	 * @return The magnitude of the left trigger as a value between 0f and 1f
	 */
	public double getLeftTrigger() {
		if (triggers < deadZone)
			return 0;
		return triggers;
	}

	/**
	 * <b>NOTE: With the {@link Controller} class, the triggers are considered
	 * as one axis, and therefore, cannot register magnitudes separately.</b>
	 * 
	 * @return The change in the magnitude of the left trigger as a value
	 *         between 0f and 1f
	 */
	public double getLeftTriggerDelta() {
		if (triggers < 0)
			return 0;
		return triggers - triggersLast;
	}

	/**
	 * <b>NOTE: With the {@link Controller} class, the triggers are considered
	 * as one axis, and therefore, cannot register magnitudes separately.</b>
	 * 
	 * @return The magnitude of the right trigger as a value between 0f and 1f
	 */
	public double getRightTrigger() {
		if (triggers > -deadZone)
			return 0;
		return -triggers;
	}

	/**
	 * <b>NOTE: With the {@link Controller} class, the triggers are considered
	 * as one axis, and therefore, cannot register magnitudes separately.</b>
	 * 
	 * @return The change in the magnitude of the right trigger as a value
	 *         between 0f and 1f
	 */
	public double getRightTriggerDelta() {
		if (triggers > 0)
			return 0;
		return -triggers - triggersLast;
	}

	public synchronized void start() {
		if (running)
			return;

		running = true;

		thread = new Thread(this, "retrobytes_gamepad_" + number);

		thread.start();
	}

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

	public void update() {
		switchesLast = SWITCHES.clone();

		leftStickLast = LEFT_STICK.clone();
		rightStickLast = RIGHT_STICK.clone();

		triggersLast = triggers;
	}

	@Override
	public void run() {
		while (running) {
			if (!controller.poll()) {
				System.err.printf("RETROBYTES ERROR: CONTROLLER %d DISCONNECTED: %s\n", number, controller.getName());
				running = false;
				continue;
			}

			for (Component component : controller.getComponents()) {
				String identifier = component.getIdentifier().getName();

				double data = component.getPollData();

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
					if (data == 0) {
						SWITCHES[Switch.UP.ordinal()] = false;
						SWITCHES[Switch.RIGHT.ordinal()] = false;
						SWITCHES[Switch.DOWN.ordinal()] = false;
						SWITCHES[Switch.LEFT.ordinal()] = false;
					}
					else if (data == 0.125) {
						SWITCHES[Switch.UP.ordinal()] = true;
						SWITCHES[Switch.RIGHT.ordinal()] = false;
						SWITCHES[Switch.DOWN.ordinal()] = false;
						SWITCHES[Switch.LEFT.ordinal()] = true;
					}
					else if (data == 0.25) {
						SWITCHES[Switch.UP.ordinal()] = true;
						SWITCHES[Switch.RIGHT.ordinal()] = false;
						SWITCHES[Switch.DOWN.ordinal()] = false;
						SWITCHES[Switch.LEFT.ordinal()] = false;
					}
					else if (data == 0.375) {
						SWITCHES[Switch.UP.ordinal()] = true;
						SWITCHES[Switch.RIGHT.ordinal()] = true;
						SWITCHES[Switch.DOWN.ordinal()] = false;
						SWITCHES[Switch.LEFT.ordinal()] = false;
					}
					else if (data == 0.5) {
						SWITCHES[Switch.UP.ordinal()] = false;
						SWITCHES[Switch.RIGHT.ordinal()] = true;
						SWITCHES[Switch.DOWN.ordinal()] = false;
						SWITCHES[Switch.LEFT.ordinal()] = false;
					}
					else if (data == 0.625) {
						SWITCHES[Switch.UP.ordinal()] = false;
						SWITCHES[Switch.RIGHT.ordinal()] = true;
						SWITCHES[Switch.DOWN.ordinal()] = true;
						SWITCHES[Switch.LEFT.ordinal()] = false;
					}
					else if (data == 0.75) {
						SWITCHES[Switch.UP.ordinal()] = false;
						SWITCHES[Switch.RIGHT.ordinal()] = false;
						SWITCHES[Switch.DOWN.ordinal()] = true;
						SWITCHES[Switch.LEFT.ordinal()] = false;
					}
					else if (data == 0.875) {
						SWITCHES[Switch.UP.ordinal()] = false;
						SWITCHES[Switch.RIGHT.ordinal()] = false;
						SWITCHES[Switch.DOWN.ordinal()] = true;
						SWITCHES[Switch.LEFT.ordinal()] = true;
					}
					else if (data == 1) {
						SWITCHES[Switch.UP.ordinal()] = false;
						SWITCHES[Switch.RIGHT.ordinal()] = false;
						SWITCHES[Switch.DOWN.ordinal()] = false;
						SWITCHES[Switch.LEFT.ordinal()] = true;
					}
				}
				else {
					SWITCHES[Integer.parseInt(identifier)] = (data == 1);
				}
			}
		}
	}
}