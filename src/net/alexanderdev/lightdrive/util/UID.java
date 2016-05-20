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
package net.alexanderdev.lightdrive.util;

import static net.alexanderdev.lightdrive.util.math.MathX.randomBoolean;
import static net.alexanderdev.lightdrive.util.math.MathX.randomInt;

import java.util.LinkedList;
import java.util.List;

import net.alexanderdev.lightdrive.InternalMethod;
import net.alexanderdev.lightdrive.InternalType;
import net.alexanderdev.lightdrive.state.State;
import net.alexanderdev.lightdrive.state.StateManager;

/**
 * A utility class which generates <b>U</b>nique <b>ID</b>entifiers for use by
 * the {@link StateManager} when anonymous {@link State}s are added.
 * 
 * @author Christian Bryce Alexander
 * @since Jan 19, 2016, 11:52:17 AM
 */
@InternalType
public class UID {
	private static final List<String> GENERATED_UIDS = new LinkedList<>();

	/**
	 * @return A unique and random 32 character {@link String} consisting of upper and
	 *         lower case English letters and numbers.
	 */
	@InternalMethod
	public static String generateUUID() {
		String uid;

		do {
			uid = "";

			for (int i = 0; i < 32; i++)
				if (randomBoolean())
					if (randomBoolean())
						uid += (char) randomInt('A', 'Z');
					else
						uid += (char) randomInt('a', 'z');
				else
					uid += (char) randomInt('0', '9');
		}
		while (isGenerated(uid));

		GENERATED_UIDS.add(uid);

		return uid;
	}

	private static boolean isGenerated(String uid) {
		for (String genID : GENERATED_UIDS)
			if (genID.equals(uid))
				return true;
		return false;
	}
}