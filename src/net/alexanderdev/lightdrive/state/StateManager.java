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
import java.util.Stack;

import net.alexanderdev.lightdrive.InternalMethod;
import net.alexanderdev.lightdrive.graphics.GraphicsX;
import net.alexanderdev.lightdrive.graphics.Renderable;
import net.alexanderdev.lightdrive.input.Controllable;
import net.alexanderdev.lightdrive.input.Gamepad;
import net.alexanderdev.lightdrive.input.Keyboard;
import net.alexanderdev.lightdrive.input.Mouse;
import net.alexanderdev.lightdrive.util.UID;
import net.alexanderdev.lightdrive.view.Viewable;

/**
 * A class that the display uses to separate different states of the game from
 * each other, and allows for ease of switching between them
 * 
 * @author Christian Bryce Alexander
 * @since March 6, 2015 | 3:03:32 AM
 */
public class StateManager implements Renderable, Controllable {
	private static final String DEFAULT_STATE = "light_drive_default_state";

	private final Map<String, State> STATES = new HashMap<>();

	private final Stack<String> ORDER = new Stack<>();

	private State currentState;

	private Viewable view;

	private boolean initialized;

	/**
	 * Associates a {@link StateManager} with the specified {@link Viewable}.
	 * 
	 * @param view
	 *            The {@link Viewable} to associate with this
	 *            {@code StateManager}
	 */
	public StateManager(Viewable view) {
		this.view = view;

		addState(DEFAULT_STATE, new DefaultState());

		setInitialState(DEFAULT_STATE);
	}

	/**
	 * @return The {@link Viewable} associated with this {@code StateManager}
	 */
	public Viewable getView() {
		return view;
	}

	/**
	 * Maps the specified {@link State} to the specified {@code name}.
	 * 
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
	 * {@code StateManager} has been initialized internally by the associated
	 * {@link Viewable}.
	 * 
	 * @param name
	 *            The name of the {@link State} to set as current
	 */
	public void setInitialState(String name) {
		if (!initialized)
			currentState = STATES.get(name);

		ORDER.clear();
		ORDER.push(name);
	}

	/**
	 * Swaps the current {@link State} for another {@link State} as specified by
	 * {@code name}, and calls the new {@link State}'s
	 * {@link State#switchTo(Object...)} method.
	 * 
	 * @param name
	 *            The name of the {@link State} to switch to
	 */
	public void switchState(String name, Object... argv) {
		currentState = STATES.get(name);

		if (currentState != null)
			currentState.switchTo(argv);

		ORDER.push(name);
	}

	public void goToPreviousState() {
		ORDER.pop();

		currentState = STATES.get(ORDER.peek());

		if (currentState != null)
			currentState.switchTo();
	}

	/**
	 * Internally initializes this {@code StateManager} so it is prepared for
	 * the first iteration of the game loop.
	 */
	@InternalMethod
	public final void init() {
		if (currentState != null)
			currentState.switchTo();

		initialized = true;
	}

	@InternalMethod
	public final void keyboardInput(Keyboard keyboard) {
		if (currentState != null)
			currentState.keyboardInput(keyboard);
	}

	@InternalMethod
	public final void mouseInput(Mouse mouse) {
		if (currentState != null)
			currentState.mouseInput(mouse);
	}

	@InternalMethod
	public final void gamepadInput(Gamepad gamepad) {
		if (currentState != null)
			currentState.gamepadInput(gamepad);
	}

	@Override
	public void update(double delta) {
		if (currentState != null)
			currentState.update(delta);
	}

	@Override
	public void render(GraphicsX g) {
		if (currentState != null)
			currentState.render(g);
	}
}