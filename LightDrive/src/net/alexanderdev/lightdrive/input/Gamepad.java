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

import static net.alexanderdev.lightdrive.util.Time.msTime;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.alexanderdev.lightdrive.Internal;
import net.alexanderdev.lightdrive.util.math.MathS;
import net.alexanderdev.lightdrive.util.math.geom.VectorF;
import net.java.games.input.Component;
import net.java.games.input.Controller;

/**
 * A class which encapsulates a JInput {@code Controller} into a gamepad-centric
 * polling {@code Thread}, and enables more game-friendly functionality.
 * 
 * @author Christian Bryce Alexander
 * @since Apr 29, 2015 | 9:11:58 PM
 */
public final class Gamepad implements Runnable {
	private final Map<String, String> BUTTON_NAMES = new HashMap<>();
	private final Map<String, Button> ALL_BUTTONS  = new HashMap<>();

	private final List<String> BUTTON_COMBO = new ArrayList<>();

	public final Button A_BUTTON     = new Button("0");
	public final Button B_BUTTON     = new Button("1");
	public final Button X_BUTTON     = new Button("2");
	public final Button Y_BUTTON     = new Button("3");
	public final Button LEFT_BUMPER  = new Button("4");
	public final Button RIGHT_BUMPER = new Button("5");
	public final Button BACK         = new Button("6");
	public final Button START        = new Button("7");
	public final Button LEFT_THUMB   = new Button("8");
	public final Button RIGHT_THUMB  = new Button("9");
	public final Button UP           = new Button("povUp");
	public final Button DOWN         = new Button("povDown");
	public final Button LEFT         = new Button("povLeft");
	public final Button RIGHT        = new Button("povRight");

	private final Map<String, String> STICK_NAMES = new HashMap<>();
	private final Map<String, Stick>  ALL_STICKS  = new HashMap<>();

	public final Stick LEFT_STICK  = new Stick("Axis");
	public final Stick RIGHT_STICK = new Stick("Rotation");

	private final Map<String, String>  TRIGGER_NAMES = new HashMap<>();
	private final Map<String, Trigger> ALL_TRIGGERS  = new HashMap<>();

	public final Trigger LEFT_TRIGGER  = new Trigger("leftTrig");
	public final Trigger RIGHT_TRIGGER = new Trigger("rightTrig");

	public static final String[] KONAMI_CODE = {
		// Gamepad.UP, Gamepad.UP, Gamepad.DOWN, Gamepad.DOWN, Gamepad.LEFT,
		// Gamepad.RIGHT, Gamepad.LEFT, Gamepad.RIGHT,
		// Gamepad.B, Gamepad.A, Gamepad.START
	};

	private Controller controller;

	private int number;

	private Thread thread;

	private boolean running = false;

	private float lastPovX = 0f;
	private float lastPovY = 0f;

	private static float deadZone = 0f;

	{
		addButton("a",            A_BUTTON);
		addButton("b",            B_BUTTON);
		addButton("x",            X_BUTTON);
		addButton("y",            Y_BUTTON);
		addButton("left bumper",  LEFT_BUMPER);
		addButton("right bumper", RIGHT_BUMPER);
		addButton("back",         BACK);
		addButton("start",        START);
		addButton("left thumb",   LEFT_THUMB);
		addButton("right thumb",  RIGHT_THUMB);

		addButton("pov up",    UP);
		addButton("pov down",  DOWN);
		addButton("pov left",  LEFT);
		addButton("pov right", RIGHT);

		addStick("left stick",  LEFT_STICK);
		addStick("right stick", RIGHT_STICK);

		addTrigger("left trigger",  LEFT_TRIGGER);
		addTrigger("right trigger", RIGHT_TRIGGER);
	}

	public Gamepad(Controller controller, int number) {
		this.controller = controller;

		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	/**
	 * Represents the current state of a single button on the gamepad.
	 */
	public static class Button {
		private final long TIMEOUT = 50;

		private boolean held     = false;
		private boolean pressed  = false;
		private boolean released = false;

		// private String name;
		private String identifier;

		private long actionTimer;

		/**
		 * Creates a button which is mapped to a specific identifier
		 * 
		 * @param name
		 *            Name of the button
		 * @param identifier
		 *            JInput component identifier
		 */
		public Button(String identifier) {
			this.identifier = identifier;
		}

		// @Internal
		// public void setName(String name) {
		// this.name = name;
		// }

		void toggle(boolean held) {
			if (held && !this.held) {
				pressed = true;
				actionTimer = msTime();
				// addToCombo(name);
			}

			if (!held && this.held) {
				released = true;
				actionTimer = msTime();
			}

			this.held = held;
		}

		/**
		 * Will only return {@code true} the first time it is checked after a
		 * button is pressed<br>
		 * Since button states are continuous, this function simulates a button
		 * event firing only once<br>
		 * 
		 * @return {@code true} first time called after the button is pressed
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
		 * button is released<br>
		 * Since button states are continuous, this function simulates a button
		 * event firing only once<br>
		 * 
		 * @return {@code true} first time called after the button is released
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
		 * @return The JInput identifier mapped to this {@code Button}
		 */
		public String getIdentifier() {
			return identifier;
		}
	}

	private void addButton(String name, Button button) {
		// button.setName(name);

		BUTTON_NAMES.put(button.getIdentifier(), name);

		ALL_BUTTONS.put(name, button);
	}

	/**
	 * Retrieves a {@code Button} from the {@code Gamepad}
	 * 
	 * @param name
	 *            The name of the {@code Button} to retrieve
	 * @return A named {@code Button}
	 */
	public Button getButton(String name) {
		return ALL_BUTTONS.get(name);
	}

	// private void addToCombo(String name) {
	// BUTTON_COMBO.add(name);
	// if (BUTTON_COMBO.size() >= 100)
	// clearCombo();
	// }

	/**
	 * Prints the current button combination entered by this {@code Gamepad} to
	 * the console
	 */
	public void showCombo() {
		boolean first = true;
		for (String name : BUTTON_COMBO) {
			if (!first)
				System.out.print(", ");
			else
				first = false;

			System.out.print(name);
		}
		System.out.println();
	}

	public boolean comboFollows(String[] buttonNames) {
		if (buttonNames.length < BUTTON_COMBO.size())
			return false;

		for (int i = 0; i < BUTTON_COMBO.size(); i++)
			if (!buttonNames[i].equals(BUTTON_COMBO.get(i)))
				return false;

		return true;
	}

	public boolean comboMatches(String[] buttonNames) {
		if (buttonNames.length != BUTTON_COMBO.size())
			return false;

		for (int i = 0; i < BUTTON_COMBO.size(); i++)
			if (!buttonNames[i].equals(BUTTON_COMBO.get(i)))
				return false;

		return true;
	}

	public int comboLength() {
		return BUTTON_COMBO.size();
	}

	public void clearCombo() {
		BUTTON_COMBO.clear();
	}

	public static class Stick {
		private VectorF vec = new VectorF(0f, 0f);

		private String identifier;

		public Stick(String identifier) {
			this.identifier = identifier;
		}

		public float getX() {
			if (vec.length() >= deadZone)
				return vec.x;
			return 0f;
		}

		public float getY() {
			if (vec.length() >= deadZone)
				return vec.y;
			return 0f;
		}

		public VectorF getVector() {
			if (vec.length() < deadZone)
				return new VectorF(0f, 0f);
			return vec;
		}

		public void setX(float x) {
			vec.x = x;

			if (vec.length() > 1f)
				vec.normalize();
		}

		public void setY(float y) {
			vec.y = y;

			if (vec.length() > 1f)
				vec.normalize();
		}

		public float direction() {
			if (vec.length() >= deadZone)
				return (float) (vec.angle() / Math.PI);
			return 0f;
		}

		public float magnitude() {
			if (vec.length() >= deadZone)
				return vec.length();
			return 0f;
		}

		public String getIdentifier() {
			return identifier;
		}
	}

	private void addStick(String name, Stick stick) {
		STICK_NAMES.put(stick.identifier, name);

		ALL_STICKS.put(name, stick);
	}

	public Stick getStick(String name) {
		return ALL_STICKS.get(name);
	}

	public static class Trigger {
		private String identifier;

		private float magnitude;

		public Trigger(String identifier) {
			this.identifier = identifier;
		}

		public void setMagnitude(float magnitude) {
			this.magnitude = MathS.clamp(magnitude, 0f, 1f);
		}

		public float magnitude() {
			if (magnitude >= deadZone)
				return magnitude;
			return 0f;
		}

		public String getIdentifier() {
			return identifier;
		}
	}

	private void addTrigger(String name, Trigger trigger) {
		TRIGGER_NAMES.put(trigger.identifier, name);

		ALL_TRIGGERS.put(name, trigger);
	}

	public Trigger getTrigger(String name) {
		return ALL_TRIGGERS.get(name);
	}

	/**
	 * Sets the dead zone for all gamepad sticks and triggers
	 *
	 * @param deadZone
	 *            The dead zone to set
	 */
	public static void setDeadZone(float deadZone) {
		Gamepad.deadZone = deadZone;
	}

	@Internal
	public synchronized void start() {
		if (running)
			return;

		running = true;

		thread = new Thread(this, "ld_gamepad_" + number);
		
		thread.start();
	}

	@Internal
	public synchronized void stop() {
		if (!running)
			return;

		running = false;

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Internal
	@Override
	public void run() {
		while (running) {
			if (!controller.poll()) {
				System.err.println("LD CONTROLLER DISCONNECTED: " + controller.getName());
				stop();
			}

			for (Component component : controller.getComponents()) {
				String name       = component.getName();
				String identifier = component.getIdentifier().getName();
				float  data       = component.getPollData();

				if (name.startsWith("Button")) {
					ALL_BUTTONS.get(BUTTON_NAMES.get(identifier)).toggle(data == 1.0);
				} else if (name.startsWith("Hat")) {
					Point pov = getPOV(data);

					if (pov.x != lastPovX || pov.y != lastPovY) {
						ALL_BUTTONS.get(BUTTON_NAMES.get("povUp")).toggle(pov.y == -1);
						ALL_BUTTONS.get(BUTTON_NAMES.get("povDown")).toggle(pov.y == 1);
						ALL_BUTTONS.get(BUTTON_NAMES.get("povLeft")).toggle(pov.x == -1);
						ALL_BUTTONS.get(BUTTON_NAMES.get("povRight")).toggle(pov.x == 1);

						lastPovX = pov.x;
						lastPovY = pov.y;
					}
				} else if (name.contains("Axis")) {
					if (name.contains("X"))
						ALL_STICKS.get(STICK_NAMES.get("Axis")).setX(data);
					else if (name.contains("Y"))
						ALL_STICKS.get(STICK_NAMES.get("Axis")).setY(data);
					else if (name.contains("Z"))
						if (data > 0f)
							ALL_TRIGGERS.get(TRIGGER_NAMES.get("leftTrig")).setMagnitude(Math.abs(data));
						else
							ALL_TRIGGERS.get(TRIGGER_NAMES.get("rightTrig")).setMagnitude(Math.abs(data));
				} else if (name.contains("Rotation")) {
					if (name.contains("X"))
						ALL_STICKS.get(STICK_NAMES.get("Rotation")).setX(data);
					else if (name.contains("Y"))
						ALL_STICKS.get(STICK_NAMES.get("Rotation")).setY(data);
				}
			}
		}
	}

	private Point getPOV(float pov) {
		Point p = new Point(0, 0);

		switch (String.valueOf(pov)) {
			case "0.125":
				p.x = -1;
				p.y = -1;
				break;
			case "0.25":
				p.x = 0;
				p.y = -1;
				break;
			case "0.375":
				p.x = 1;
				p.y = -1;
				break;
			case "0.5":
				p.x = 1;
				p.y = 0;
				break;
			case "0.625":
				p.x = 1;
				p.y = 1;
				break;
			case "0.75":
				p.x = 0;
				p.y = 1;
				break;
			case "0.875":
				p.x = -1;
				p.y = 1;
				break;
			case "1.0":
				p.x = -1;
				p.y = 0;
				break;
		}

		return p;
	}
}