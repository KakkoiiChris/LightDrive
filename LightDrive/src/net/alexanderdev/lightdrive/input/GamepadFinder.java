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

import java.util.ArrayList;
import java.util.List;

import net.alexanderdev.lightdrive.Internal;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

/**
 * Used by the {@code Display} to find any connected gamepads, and add them for
 * event listening
 * 
 * @author Christian Bryce Alexander
 * @since Apr 29, 2015 | 9:35:08 PM
 */
public final class GamepadFinder {
	private static boolean found = false;

	@Internal
	public static Gamepad[] getGamepads() {
		Controller[] allControllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

		if (allControllers.length > 0)
			found = true;

		List<Gamepad> gamepads = new ArrayList<>();

		int number = 0;

		for (int i = 0; i < allControllers.length; i++)
			if (allControllers[i].getType() == Controller.Type.GAMEPAD)
				gamepads.add(new Gamepad(allControllers[i], number++));

		return gamepads.toArray(new Gamepad[gamepads.size()]);
	}

	public static boolean foundGamepads() {
		return found;
	}
}