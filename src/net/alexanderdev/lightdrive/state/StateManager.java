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
import java.util.Optional;

import net.alexanderdev.lightdrive.Cleanable;
import net.alexanderdev.lightdrive.InternalMethod;
import net.alexanderdev.lightdrive.graphics.GraphicsX;
import net.alexanderdev.lightdrive.graphics.Renderable;
import net.alexanderdev.lightdrive.input.Controllable;
import net.alexanderdev.lightdrive.input.gamepad.Gamepad;
import net.alexanderdev.lightdrive.input.keyboard.Keyboard;
import net.alexanderdev.lightdrive.input.mouse.Mouse;
import net.alexanderdev.lightdrive.util.UID;
import net.alexanderdev.lightdrive.view.Viewable;

/**
 * A class used by all {@link Viewable}s to separate the logic of different
 * {@link State}s of the game from each other, while allowing them to be
 * switched instantaneously, and to briefly communicate with each other.
 * 
 * @author Christian Bryce Alexander
 * @since March 6, 2015, 3:03:32 AM
 */
public class StateManager implements Cleanable, Controllable, Renderable {
	protected final Map<String, State> STATES = new HashMap<>();

	protected Optional<State> currentState;

	protected Viewable view;

	/**
	 * Associates a {@link StateManager} with the specified {@link Viewable}.
	 * 
	 * @param view
	 *            The {@link Viewable} to associate with this
	 *            {@link StateManager}
	 */
	public StateManager(Viewable view) {
		this.view = view;

		currentState = Optional.empty();
	}

	/**
	 * @return The {@link Viewable} associated with this {@link StateManager}
	 */
	public Viewable getView() {
		return view;
	}

	/**
	 * Maps the specified {@link State} to the specified {@code name}.
	 * 
	 * @param name
	 *            The name to map the state to
	 * @param state
	 *            The {@link State} to add
	 */
	public void addState(String name, State state) {
		state.setManager(this);

		if (!currentState.isPresent())
			currentState = Optional.of(state);

		STATES.put(name.toLowerCase(), state);
	}

	/**
	 * Maps the specified {@link State} to a guaranteed unique identifier.
	 *
	 * @param state
	 *            The {@link State} to add
	 * @return The unique identifier that it was mapped to
	 */
	public String addState(State state) {
		String uid = UID.generateUUID();

		addState(uid, state);

		return uid;
	}

	@InternalMethod
	public final void init() {
		currentState.ifPresent(s -> s.enter());
	}

	/**
	 * Swaps the current {@link State} for another {@link State} as specified by
	 * {@code name}, and calls the new {@link State}'s
	 * {@link State#enter(Object...)} method.
	 * 
	 * @param name
	 *            The name of the {@link State} to switch to
	 * @param argv
	 *            A varargs list of {@link Object}s to pass to the next state
	 */
	public void goToState(String name, Object... argv) {
		currentState.ifPresent(s -> s.exit());

		currentState = Optional.ofNullable(STATES.get(name.toLowerCase()));

		currentState.ifPresent(s -> s.enter(argv));
	}

	@Override
	@InternalMethod
	public final void keyboardInput(Keyboard keyboard) {
		currentState.ifPresent(s -> s.keyboardInput(keyboard));
	}

	@Override
	@InternalMethod
	public final void mouseInput(Mouse mouse) {
		currentState.ifPresent(s -> s.mouseInput(mouse));
	}

	@Override
	@InternalMethod
	public final void gamepadInput(Gamepad gamepad) {
		currentState.ifPresent(s -> s.gamepadInput(gamepad));
	}

	@Override
	@InternalMethod
	public void update(double delta) {
		currentState.ifPresent(s -> s.update(delta));
	}

	@Override
	@InternalMethod
	public void render(GraphicsX g) {
		currentState.ifPresent(s -> s.render(g));
	}

	@Override
	public boolean cleanUp() {
		boolean success = true;

		for (String key : STATES.keySet())
			success &= STATES.get(key).cleanUp();

		return success;
	}
}