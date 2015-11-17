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
import net.alexanderdev.lightdrive.input.Controllable;
import net.alexanderdev.lightdrive.input.Gamepad;
import net.alexanderdev.lightdrive.input.Keyboard;
import net.alexanderdev.lightdrive.input.Mouse;
import net.alexanderdev.lightdrive.media.graphics.GraphicsS;
import net.alexanderdev.lightdrive.media.graphics.Renderable;
import net.alexanderdev.lightdrive.media.graphics.Screen;

/**
 * A class that the display uses to separate different states of the game from
 * each other, and allows for ease of switching between them
 * 
 * @author Christian Bryce Alexander
 * @since March 6, 2015 | 3:03:32 AM
 */
public class StateManager implements Renderable, Controllable {
	private final String DEFAULT = "@ld_default_state";

	private final Map<String, State> STATES = new HashMap<>();

	private State currState;

	private Screen screen;

	public StateManager(Screen screen) {
		this.screen = screen;

		addState(DEFAULT, new DefaultState());

		currState = STATES.get(DEFAULT);
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
		if (STATES.containsKey(DEFAULT))
			STATES.remove(DEFAULT);

		state.setManager(this);

		STATES.put(name, state);
	}

	/**
	 * Sets the first {@code GameState} this {@code StateManager} will handle
	 * 
	 * @param name
	 *            The name of the {@code GameState} to set
	 */
	public void setInitialState(String name) {
		if (STATES.get(name) != null)
			currState = STATES.get(name);
	}

	/**
	 * Switches the {@code GameState} this {@code StateManager} handles
	 * 
	 * @param name
	 *            The name of the {@code GameState} to switch to
	 */
	public void switchState(String name) {
		currState.switchOut();

		if (STATES.get(name) != null)
			currState = STATES.get(name);

		currState.switchIn();
	}

	public Screen getScreen() {
		return screen;
	}

	@Internal
	public final void init() {
		currState.switchIn();
	}

	@Internal
	@Override
	public final void update(double delta) {
		currState.update(delta);
	}

	@Internal
	@Override
	public final void render(GraphicsS g) {
		currState.render(g);
	}

	@Internal
	@Override
	public final void keyboardInput(Keyboard keyboard) {
		currState.keyboardInput(keyboard);
	}

	@Internal
	@Override
	public final void mouseInput(Mouse mouse) {
		currState.mouseInput(mouse);
	}

	@Internal
	@Override
	public final void gamepadInput(Gamepad gamepad) {
		currState.gamepadInput(gamepad);
	}
}