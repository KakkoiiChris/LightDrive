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
	private KeyboardListener keyboardListener;

	private MouseListener mouseListener;

	private GamepadListener gamepadListener;

	private StateManager manager;

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
	public final void setKeyboardListener(KeyboardListener keyboardListener) {
		this.keyboardListener = keyboardListener;
	}

	/**
	 * Sets the interface that listens for mouse input for this game state
	 *
	 * @param mouseListener
	 *            Functional interface for handling the mouse
	 */
	public final void setMouseListener(MouseListener mouseListener) {
		this.mouseListener = mouseListener;
	}

	/**
	 * Sets the interface that listens for gamepad input for this game state
	 *
	 * @param gamepadListener
	 *            Functional interface for handling the gamepad
	 */
	public final void setGamepadListener(GamepadListener gamepadListener) {
		this.gamepadListener = gamepadListener;
	}

	public final StateManager getManager() {
		return manager;
	}

	/**
	 * Handles actions to be taken when the state manager switches into this
	 * game state
	 */
	public abstract void switchIn();

	/**
	 * Handles actions to be taken when the state manager switches out of this
	 * game state
	 */
	public abstract void switchOut();

	@Internal
	public final void keyboardInput(Keyboard keyboard) {
		if (keyboardListener != null) {
			keyboardListener.keyboardInput(keyboard);
		}
	}

	@Internal
	public final void mouseInput(Mouse mouse) {
		if (mouseListener != null) {
			mouseListener.mouseInput(mouse);
		}
	}

	@Internal
	public final void gamepadInput(Gamepad gamepad) {
		if (gamepadListener != null) {
			gamepadListener.gamepadInput(gamepad);
		}
	}
}