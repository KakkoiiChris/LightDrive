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

import net.alexanderdev.lightdrive.InternalMethod;
import net.alexanderdev.lightdrive.graphics.GraphicsS;
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
	private static final String DEFAULT_STATE = "quixel_default_state";

	private final Map<String, State> STATES = new HashMap<>();

	private State currentState;

	private Viewable view;

	public StateManager(Viewable view) {
		this.view = view;

		addState(DEFAULT_STATE, new DefaultState());

		setInitialState(DEFAULT_STATE);
	}

	public Viewable getView() {
		return view;
	}

	public void addState(String name, State state) {
		state.setManager(this);

		STATES.put(name, state);
	}

	public String addState(State state) {
		String uid = UID.generateUUID();

		addState(uid, state);

		return uid;
	}

	public void setInitialState(String name) {
		currentState = STATES.get(name);
	}

	public void switchState(String name) {
		if (currentState != null)
			currentState.switchOut();

		currentState = STATES.get(name);

		if (currentState != null)
			currentState.switchIn();
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

	@InternalMethod
	public final void init() {
		if (currentState != null)
			currentState.switchIn();
	}

	@Override
	public void update(double delta) {
		if (currentState != null)
			currentState.update(delta);
	}

	@Override
	public void render(GraphicsS g) {
		if (currentState != null)
			currentState.render(g);
	}
}