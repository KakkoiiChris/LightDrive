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
package net.alexanderdev.lightdrive.state;

import java.util.HashMap;
import java.util.Map;

import net.alexanderdev.lightdrive.Internal;
import net.alexanderdev.lightdrive.input.Gamepad;
import net.alexanderdev.lightdrive.input.Keyboard;
import net.alexanderdev.lightdrive.input.Mouse;
import net.alexanderdev.lightdrive.media.graphics.Display;
import net.alexanderdev.lightdrive.media.graphics.GraphicsS;
import net.alexanderdev.lightdrive.media.graphics.Renderable;

/**
 * A class that the display uses to separate different states of the game from
 * each other, and allows for ease of switching between them
 * 
 * @author Christian Bryce Alexander
 * @since March 6, 2015 | 3:03:32 AM
 */
public class StateManager implements Renderable {
	private final String DEFAULT = "@ld_default_state";
	
	private Map<String, State> states = new HashMap<>();

	private String currentState = DEFAULT;

	private Display display;
	
	public StateManager(Display display) {
		this.display = display;

		addState(DEFAULT, new DefaultState());
	}

	private State getCurrentState() {
		return states.get(currentState);
	}

	/**
	 * Registers a {@code GameState} with this {@code StateManager} by mapping
	 * it to a given name.
	 * 
	 * @param name
	 *            The name to map the {@code GameState} to
	 * @param state
	 *            The {@code GameState} to register with this
	 *            {@code StateManager}
	 */
	public void addState(String name, State state) {
		if (states.containsKey(DEFAULT))
			states.remove(DEFAULT);

		state.setManager(this);
		
		states.put(name, state);
	}

	/**
	 * Sets the first {@code GameState} this {@code StateManager} will handle
	 * 
	 * @param name
	 *            The name of the {@code GameState} to set
	 */
	public void setInitialState(String name) {
		currentState = name;
	}

	/**
	 * Switches the {@code GameState} this {@code StateManager} handles
	 * 
	 * @param name
	 *            The name of the {@code GameState} to switch to
	 */
	public void switchState(String name) {
		if (currentState.equals(name))
			return;
		
		State state;

		if ((state = getCurrentState()) != null) {
			state.switchOut();
		}

		currentState = name;

		if ((state = getCurrentState()) != null) {
			state.switchIn();
		}
	}

	public Display getDisplay() {
		return display;
	}

	@Internal
	public final void init() {
		State state;

		if ((state = getCurrentState()) != null) {
			state.switchIn();
		}
	}

	@Internal
	@Override
	public final void update(double delta) {
		State state;

		if ((state = getCurrentState()) != null) {
			state.update(delta);
		}
	}

	@Internal
	@Override
	public final void render(GraphicsS g) {
		State state;

		if ((state = getCurrentState()) != null) {
			state.render(g);
		}
	}

	@Internal
	public final void keyboardInput(Keyboard keyboard) {
		State state;

		if ((state = getCurrentState()) != null) {
			state.keyboardInput(keyboard);
		}
	}

	@Internal
	public final void mouseInput(Mouse mouse) {
		State state;

		if ((state = getCurrentState()) != null) {
			state.mouseInput(mouse);
		}
	}

	@Internal
	public final void gamepadInput(Gamepad gamepad) {
		State state;

		if ((state = getCurrentState()) != null) {
			state.gamepadInput(gamepad);
		}
	}
}