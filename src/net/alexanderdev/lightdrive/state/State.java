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

import java.util.ArrayList;
import java.util.List;

import net.alexanderdev.lightdrive.Cleanable;
import net.alexanderdev.lightdrive.InternalMethod;
import net.alexanderdev.lightdrive.graphics.Renderable;
import net.alexanderdev.lightdrive.input.Controllable;
import net.alexanderdev.lightdrive.input.gamepad.Gamepad;
import net.alexanderdev.lightdrive.input.gamepad.GamepadListener;
import net.alexanderdev.lightdrive.input.keyboard.Keyboard;
import net.alexanderdev.lightdrive.input.keyboard.KeyboardListener;
import net.alexanderdev.lightdrive.input.mouse.Mouse;
import net.alexanderdev.lightdrive.input.mouse.MouseListener;
import net.alexanderdev.lightdrive.view.Viewable;

/**
 * A class which holds the logic for representing a particular <i>'state'</i> in
 * a game.
 * 
 * @author Christian Bryce Alexander
 * @since March 6, 2015, 3:03:42 AM
 */
public abstract class State implements Cleanable, Controllable, Renderable {
	private StateManager manager;

	private List<KeyboardListener> keyboardListeners = new ArrayList<>();

	private List<MouseListener> mouseListeners = new ArrayList<>();

	private List<GamepadListener> gamepadListeners = new ArrayList<>();

	/**
	 * @return The {@link StateManager} associated with this {@link State}
	 */
	public final StateManager getManager() {
		return manager;
	}

	/**
	 * Associates a {@link StateManager} with this {@link State}.
	 * 
	 * @param manager
	 *            The {@link StateManager} to associate
	 */
	@InternalMethod
	public final void setManager(StateManager manager) {
		this.manager = manager;
	}

	/**
	 * Adds an instance of {@link KeyboardListener} to this {@link State}.
	 *
	 * @param kl
	 *            The {@link KeyboardListener} to add
	 */
	public final void addKeyboardListener(KeyboardListener kl) {
		keyboardListeners.add(kl);
	}

	/**
	 * Removes an instance of {@link KeyboardListener} from this {@link State}.
	 *
	 * @param kl
	 *            The {@link KeyboardListener} to remove
	 */
	public final void removeKeyboardListener(KeyboardListener kl) {
		keyboardListeners.remove(kl);
	}

	/**
	 * Removes all {@link KeyboardListener} instances from this {@link State}.
	 */
	public final void clearKeyboardListeners() {
		keyboardListeners.clear();
	}

	/**
	 * Runs all {@link KeyboardListener}s on the specified {@link Keyboard}.
	 * 
	 * @param keyboard
	 *            The {@link StateManager}'s {@link Viewable}'s {@link Keyboard}
	 */
	@InternalMethod
	public final void keyboardInput(Keyboard keyboard) {
		if (keyboardListeners != null)
			for (int i = 0; i < keyboardListeners.size(); i++)
				keyboardListeners.get(i).keyboardInput(keyboard);
	}

	/**
	 * Adds an instance of {@link MouseListener} to this {@link State}.
	 *
	 * @param ml
	 *            The {@link MouseListener} to add
	 */
	public final void addMouseListener(MouseListener ml) {
		mouseListeners.add(ml);
	}

	/**
	 * Removes an instance of {@link MouseListener} from this {@link State}.
	 *
	 * @param ml
	 *            The {@link MouseListener} to remove
	 */
	public final void removeMouseListener(MouseListener ml) {
		mouseListeners.remove(ml);
	}

	/**
	 * Removes all {@link MouseListener} instances from this {@link State}.
	 */
	public final void clearMouseListeners() {
		mouseListeners.clear();
	}

	/**
	 * Runs all {@link MouseListener}s on the specified {@link Mouse}.
	 * 
	 * @param mouse
	 *            The {@link StateManager}'s {@link Viewable}'s {@link Mouse}
	 */
	@InternalMethod
	public final void mouseInput(Mouse mouse) {
		if (mouseListeners != null)
			for (int i = 0; i < mouseListeners.size(); i++)
				mouseListeners.get(i).mouseInput(mouse);
	}

	/**
	 * Adds an instance of {@link GamepadListener} to this {@link State}.
	 *
	 * @param gl
	 *            The {@link GamepadListener} to add
	 */
	public final void addGamepadListener(GamepadListener gl) {
		gamepadListeners.add(gl);
	}

	/**
	 * Removes an instance of {@link GamepadListener} from this {@link State}.
	 *
	 * @param gl
	 *            The {@link GamepadListener} to remove
	 */
	public final void removeGamepadListener(GamepadListener gl) {
		gamepadListeners.remove(gl);
	}

	/**
	 * Removes all {@link GamepadListener} instances from this {@link State}.
	 */
	public final void clearGamepadListeners() {
		gamepadListeners.clear();
	}

	/**
	 * Runs all {@link GamepadListener}s on the specified {@link Gamepad}.
	 * 
	 * @param gamepad
	 *            The {@link StateManager}'s {@link Viewable}'s {@link Gamepad}
	 */
	@InternalMethod
	public final void gamepadInput(Gamepad gamepad) {
		if (gamepadListeners != null)
			for (int i = 0; i < gamepadListeners.size(); i++)
				gamepadListeners.get(i).gamepadInput(gamepad);
	}

	/**
	 * Called internally whenever the associated {@link StateManager} is told to
	 * switch to this {@link State} from another {@link State}.
	 * 
	 * @param argv
	 *            A varargs list of {@link Object}s passed through by the
	 *            {@link StateManager} when it is told to switch states.
	 */
	@InternalMethod
	public abstract void enter(Object... argv);

	/**
	 * Called internally whenever the associated {@link StateManager} is told to
	 * switch from this {@link State} to another {@link State}.
	 */
	@InternalMethod
	public abstract void exit();

	@Override
	public boolean cleanUp() {
		return true;
	}
}