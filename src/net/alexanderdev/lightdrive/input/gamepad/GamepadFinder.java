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
package net.alexanderdev.lightdrive.input.gamepad;

import java.util.ArrayList;
import java.util.List;

import net.alexanderdev.lightdrive.InternalMethod;
import net.alexanderdev.lightdrive.InternalType;
import net.alexanderdev.lightdrive.view.Viewable;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

/**
 * Used by the {@link Viewable} to find any connected {@link Gamepad}s.
 * 
 * @author Christian Bryce Alexander
 * @since Apr 29, 2015, 9:35:08 PM
 */
@InternalType
public final class GamepadFinder {
	private static Gamepad[] gamepads = null;

	@InternalMethod
	public static Gamepad[] getGamepads() {
		Controller[] allControllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

		if (allControllers.length == 0)
			return null;

		List<Gamepad> allGamepads = new ArrayList<>();

		int number = 0;

		for (int i = 0; i < allControllers.length; i++)
			if (allControllers[i].getType() == Controller.Type.GAMEPAD)
				allGamepads.add(new Gamepad(allControllers[i], number++));

		gamepads = allGamepads.toArray(new Gamepad[allGamepads.size()]);

		return gamepads;
	}

	/**
	 * @return {@code true} if any game pads were connected when polling for
	 *         them, {@code false} otherwise
	 */
	public static boolean foundGamepads() {
		return gamepads != null;
	}

	/**
	 * Sets the dead zone for all currently connected game pads' triggers and
	 * sticks.
	 * 
	 * @param deadZone
	 *            The dead zone to set
	 */
	public static void setDeadZones(float deadZone) {
		for (Gamepad gp : gamepads)
			gp.setDeadZone(deadZone);
	}
}