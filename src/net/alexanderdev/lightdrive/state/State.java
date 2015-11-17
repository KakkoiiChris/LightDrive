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

import net.alexanderdev.lightdrive.Internal;
import net.alexanderdev.lightdrive.input.Gamepad;
import net.alexanderdev.lightdrive.input.GamepadListener;
import net.alexanderdev.lightdrive.input.Keyboard;
import net.alexanderdev.lightdrive.input.KeyboardListener;
import net.alexanderdev.lightdrive.input.Mouse;
import net.alexanderdev.lightdrive.input.MouseListener;
import net.alexanderdev.lightdrive.media.graphics.Renderable;

/**
 * A class which holds the logic for representing a particular state in a game
 * 
 * @author Christian Bryce Alexander
 * @since March 6, 2015 | 3:03:42 AM
 */
public abstract class State implements Renderable {
	private StateManager manager;

	private List<KeyboardListener> keyboardListeners = new ArrayList<>();

	private List<MouseListener> mouseListeners = new ArrayList<>();

	private List<GamepadListener> gamepadListeners = new ArrayList<>();

	public final StateManager getManager() {
		return manager;
	}

	@Internal
	public final void setManager(StateManager manager) {
		this.manager = manager;
	}

	/**
	 * Sets the interface that listens for keyboard input for this game state
	 *
	 * @param keyboardListener
	 *            Functional interface for handling the keyboard
	 */
	public final void addKeyboardListener(KeyboardListener kl) {
		keyboardListeners.add(kl);
	}

	public final void removeKeyboardListener(KeyboardListener kl) {
		keyboardListeners.remove(kl);
	}

	public final void clearKeyboardListeners() {
		keyboardListeners.clear();
	}

	/**
	 * Sets the interface that listens for mouse input for this game state
	 *
	 * @param mouseListener
	 *            Functional interface for handling the mouse
	 */
	public final void addMouseListener(MouseListener ml) {
		mouseListeners.add(ml);
	}

	public final void removeMouseListener(MouseListener ml) {
		mouseListeners.remove(ml);
	}

	public final void clearMouseListeners() {
		mouseListeners.clear();
	}

	/**
	 * Sets the interface that listens for gamepad input for this game state
	 *
	 * @param gamepadListener
	 *            Functional interface for handling the gamepad
	 */
	public final void addGamepadListener(GamepadListener gl) {
		gamepadListeners.add(gl);
	}

	public final void removeGamepadListener(GamepadListener gl) {
		gamepadListeners.remove(gl);
	}

	public final void clearGamepadListeners() {
		gamepadListeners.clear();
	}

	@Internal
	public final void keyboardInput(Keyboard keyboard) {
		if (keyboardListeners != null)
			for (KeyboardListener kl : keyboardListeners)
				kl.keyboardInput(keyboard);
	}

	@Internal
	public final void mouseInput(Mouse mouse) {
		if (mouseListeners != null)
			for (MouseListener ml : mouseListeners)
				ml.mouseInput(mouse);
	}

	@Internal
	public final void gamepadInput(Gamepad gamepad) {
		if (gamepadListeners != null)
			for (GamepadListener gl : gamepadListeners)
				gl.gamepadInput(gamepad);
	}

	/**
	 * Handles actions to be taken when the state manager switches into this
	 * game state
	 */
	@Internal
	public abstract void switchIn();

	/**
	 * Handles actions to be taken when the state manager switches out of this
	 * game state
	 */
	@Internal
	public abstract void switchOut();
}