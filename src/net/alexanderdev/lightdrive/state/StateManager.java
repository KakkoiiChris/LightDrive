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
	protected static final String DEFAULT_STATE = "light_drive_default_state";

	protected final Map<String, State> STATES = new HashMap<>();

	protected State currentState;

	protected Viewable view;

	protected boolean initialized;

	/**
	 * Associates a {@link StateManager} with the specified {@link Viewable}.
	 * 
	 * @param view
	 *            The {@link Viewable} to associate with this
	 *            {@link StateManager}
	 */
	public StateManager(Viewable view) {
		this.view = view;

		addState(DEFAULT_STATE, new DefaultState());

		setInitialState(DEFAULT_STATE);
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

		STATES.put(name, state);
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

	/**
	 * Sets the {@link State} that will appear first when the associated
	 * {@link Viewable} is opened. Cannot be set again once this
	 * {@link StateManager} has been initialized internally by the associated
	 * {@link Viewable}.
	 * 
	 * @param name
	 *            The name of the {@link State} to set as current
	 */
	public void setInitialState(String name) {
		if (!initialized)
			currentState = STATES.get(name);
	}

	@InternalMethod
	public final void init() {
		currentState.switchTo();

		initialized = true;
	}

	/**
	 * Swaps the current {@link State} for another {@link State} as specified by
	 * {@code name}, and calls the new {@link State}'s
	 * {@link State#switchTo(Object...)} method.
	 * 
	 * @param name
	 *            The name of the {@link State} to switch to
	 * @param argv
	 *            A varargs list of {@link Object}s to pass to the next state
	 */
	public void switchState(String name, Object... argv) {
		currentState.switchFrom();

		currentState = STATES.get(name);

		currentState.switchTo(argv);
	}

	@Override
	@InternalMethod
	public final void keyboardInput(Keyboard keyboard) {
		currentState.keyboardInput(keyboard);
	}

	@Override
	@InternalMethod
	public final void mouseInput(Mouse mouse) {
		currentState.mouseInput(mouse);
	}

	@Override
	@InternalMethod
	public final void gamepadInput(Gamepad gamepad) {
		currentState.gamepadInput(gamepad);
	}

	@Override
	@InternalMethod
	public void update(double delta) {
		currentState.update(delta);
	}

	@Override
	@InternalMethod
	public void render(GraphicsX g) {
		currentState.render(g);
	}

	@Override
	@InternalMethod
	public void cleanUp() {
		for (String key : STATES.keySet())
			STATES.get(key).cleanUp();

		STATES.clear();
	}
}