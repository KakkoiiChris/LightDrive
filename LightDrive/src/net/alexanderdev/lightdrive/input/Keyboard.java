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

import static net.alexanderdev.lightdrive.util.Time.msTime;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import net.alexanderdev.lightdrive.Internal;

/**
 * A class which encapsulates the {@code KeyListener} interface, and enables
 * more game-friendly functionality.
 * 
 * @author Christian Bryce Alexander
 * @since March 6, 2015 | 2:47:47 AM
 */
public final class Keyboard implements KeyListener {
	private static final Map<Integer, String> KEY_NAMES = new HashMap<>();
	private static final Map<String,  Key>    ALL_KEYS  = new HashMap<>();

	public final Key ESCAPE     = new Key(KeyEvent.VK_ESCAPE);
	public final Key DELETE     = new Key(KeyEvent.VK_DELETE);
	public final Key BACK_SPACE = new Key(KeyEvent.VK_BACK_SPACE);
	public final Key TAB        = new Key(KeyEvent.VK_TAB);
	public final Key SHIFT      = new Key(KeyEvent.VK_SHIFT);
	public final Key CONTROL    = new Key(KeyEvent.VK_CONTROL);
	public final Key ALT        = new Key(KeyEvent.VK_ALT);
	public final Key SPACE      = new Key(KeyEvent.VK_SPACE);
	public final Key ENTER      = new Key(KeyEvent.VK_ENTER);

	public final Key BACK_QUOTE = new Key(KeyEvent.VK_BACK_QUOTE);

	public final Key F1  = new Key(KeyEvent.VK_F1);
	public final Key F2  = new Key(KeyEvent.VK_F2);
	public final Key F3  = new Key(KeyEvent.VK_F3);
	public final Key F4  = new Key(KeyEvent.VK_F4);
	public final Key F5  = new Key(KeyEvent.VK_F5);
	public final Key F6  = new Key(KeyEvent.VK_F6);
	public final Key F7  = new Key(KeyEvent.VK_F7);
	public final Key F8  = new Key(KeyEvent.VK_F8);
	public final Key F9  = new Key(KeyEvent.VK_F9);
	public final Key F10 = new Key(KeyEvent.VK_F10);
	public final Key F11 = new Key(KeyEvent.VK_F11);
	public final Key F12 = new Key(KeyEvent.VK_F12);

	public final Key ZERO  = new Key(KeyEvent.VK_0);
	public final Key ONE   = new Key(KeyEvent.VK_1);
	public final Key TWO   = new Key(KeyEvent.VK_2);
	public final Key THREE = new Key(KeyEvent.VK_3);
	public final Key FOUR  = new Key(KeyEvent.VK_4);
	public final Key FIVE  = new Key(KeyEvent.VK_5);
	public final Key SIX   = new Key(KeyEvent.VK_6);
	public final Key SEVEN = new Key(KeyEvent.VK_7);
	public final Key EIGHT = new Key(KeyEvent.VK_8);
	public final Key NINE  = new Key(KeyEvent.VK_9);

	public final Key A = new Key(KeyEvent.VK_A);
	public final Key B = new Key(KeyEvent.VK_B);
	public final Key C = new Key(KeyEvent.VK_C);
	public final Key D = new Key(KeyEvent.VK_D);
	public final Key E = new Key(KeyEvent.VK_E);
	public final Key F = new Key(KeyEvent.VK_F);
	public final Key G = new Key(KeyEvent.VK_G);
	public final Key H = new Key(KeyEvent.VK_H);
	public final Key I = new Key(KeyEvent.VK_I);
	public final Key J = new Key(KeyEvent.VK_J);
	public final Key K = new Key(KeyEvent.VK_K);
	public final Key L = new Key(KeyEvent.VK_L);
	public final Key M = new Key(KeyEvent.VK_M);
	public final Key N = new Key(KeyEvent.VK_N);
	public final Key O = new Key(KeyEvent.VK_O);
	public final Key P = new Key(KeyEvent.VK_P);
	public final Key Q = new Key(KeyEvent.VK_Q);
	public final Key R = new Key(KeyEvent.VK_R);
	public final Key S = new Key(KeyEvent.VK_S);
	public final Key T = new Key(KeyEvent.VK_T);
	public final Key U = new Key(KeyEvent.VK_U);
	public final Key V = new Key(KeyEvent.VK_V);
	public final Key W = new Key(KeyEvent.VK_W);
	public final Key X = new Key(KeyEvent.VK_X);
	public final Key Y = new Key(KeyEvent.VK_Y);
	public final Key Z = new Key(KeyEvent.VK_Z);

	public final Key UP    = new Key(KeyEvent.VK_UP);
	public final Key DOWN  = new Key(KeyEvent.VK_DOWN);
	public final Key LEFT  = new Key(KeyEvent.VK_LEFT);
	public final Key RIGHT = new Key(KeyEvent.VK_RIGHT);

	private static final long SCAN_TIMEOUT = 50;
	
	private static long lastScan;
	
	private static int keyCodeScanned = KeyEvent.VK_UNDEFINED;
	
	{
		addKey("ESCAPE",     ESCAPE);
		addKey("DELETE",     DELETE);
		addKey("BACK_SPACE", BACK_SPACE);
		addKey("TAB",        TAB);
		addKey("SHIFT",      SHIFT);
		addKey("CONTROL",    CONTROL);
		addKey("ALT",        ALT);
		addKey("SPACE",      SPACE);
		addKey("ENTER",      ENTER);

		addKey("BACK_QUOTE", BACK_QUOTE);

		addKey("F1",  F1);
		addKey("F2",  F2);
		addKey("F3",  F3);
		addKey("F4",  F4);
		addKey("F5",  F5);
		addKey("F6",  F6);
		addKey("F7",  F7);
		addKey("F8",  F8);
		addKey("F9",  F9);
		addKey("F10", F10);
		addKey("F11", F11);
		addKey("F12", F12);

		addKey("ZERO",  ZERO);
		addKey("ONE",   ONE);
		addKey("TWO",   TWO);
		addKey("THREE", THREE);
		addKey("FOUR",  FOUR);
		addKey("FIVE",  FIVE);
		addKey("SIX",   SIX);
		addKey("SEVEN", SEVEN);
		addKey("EIGHT", EIGHT);
		addKey("NINE",  NINE);

		addKey("A", A);
		addKey("B", B);
		addKey("C", C);
		addKey("D", D);
		addKey("E", E);
		addKey("F", F);
		addKey("G", G);
		addKey("H", H);
		addKey("I", I);
		addKey("J", J);
		addKey("K", K);
		addKey("L", L);
		addKey("M", M);
		addKey("N", N);
		addKey("O", O);
		addKey("P", P);
		addKey("Q", Q);
		addKey("R", R);
		addKey("S", S);
		addKey("T", T);
		addKey("U", U);
		addKey("V", V);
		addKey("W", W);
		addKey("X", X);
		addKey("Y", Y);
		addKey("Z", Z);

		addKey("UP",    UP);
		addKey("DOWN",  DOWN);
		addKey("LEFT",  LEFT);
		addKey("RIGHT", RIGHT);
	}

	/**
	 * Represents the current state of single key on the keyboard.
	 */
	public static class Key {
		private final long TIMEOUT = 50;

		private boolean held     = false;
		private boolean pressed  = false;
		private boolean released = false;

		private int keyCode;

		private long actionTimer;

		/**
		 * Creates a key which is mapped toa key code from the {@code KeyEvent}
		 * class
		 */
		public Key(int keyCode) {
			this.keyCode = keyCode;
		}

		void toggle(boolean held) {
			if (held && !this.held) {
				pressed = true;
				actionTimer = msTime();
			}

			if (!held && this.held) {
				released = true;
				actionTimer = msTime();
			}

			this.held = held;
		}

		/**
		 * Will only return {@code true} the first time it is checked after a
		 * key is pressed.<br>
		 * Since key states are continuous, this function simulates a
		 * {@code KeyEvent} firing only once.<br>
		 * 
		 * @return {@code true} first time called after a key is pressed<br>
		 *         {@code false} otherwise
		 */
		public boolean pressed() {
			if (pressed) {
				pressed = false;
				return msTime() - actionTimer < TIMEOUT;
			}
			return false;
		}

		/**
		 * Will only return {@code true} the first time it is checked after a
		 * key is released.<br>
		 * Since key states are continuous, this function simulates a
		 * {@code KeyEvent} firing only once.<br>
		 * 
		 * @return {@code true} first time called after a key is released<br>
		 *         {@code false} otherwise
		 */
		public boolean released() {
			if (released) {
				released = false;
				return msTime() - actionTimer < TIMEOUT;
			}
			return false;
		}

		/**
		 * @return {@code true} if the {@code Key} is currently being held down
		 *         <br>
		 *         {@code false} otherwise
		 */
		public boolean held() {
			return held;
		}

		/**
		 * @return The {@code KeyEvent} key code mapped to this {@code Key}
		 */
		public int getKeyCode() {
			return keyCode;
		}
	}

	/**
	 * Maps any valid {@code KeyEvent} key code to a new {@code Key} by name
	 *
	 * @param name
	 *            A custom name for this new {@code Key}
	 * @param keyCode
	 *            A valid {@code KeyEvent} key code
	 */
	public static void addKey(String name, int keyCode) {
		KEY_NAMES.put(keyCode, name);
		ALL_KEYS.put(name, new Key(keyCode));
	}

	public static void addKey(String name, Key key) {
		KEY_NAMES.put(key.keyCode, name);
		ALL_KEYS.put(name, key);
	}

	/**
	 * Retrieves a {@code Key} from the {@code Keyboard}
	 * 
	 * @param name
	 *            The name of the {@code Key} to retrieve
	 * @return A named {@code Key}
	 */
	public Key getKey(String name) {
		return ALL_KEYS.get(name);
	}

	public static int scanKeyCode() {
		if (keyCodeScanned != 0x0 && msTime() - lastScan <= SCAN_TIMEOUT) {
			int code = keyCodeScanned;
			keyCodeScanned = 0x0;
			return code;
		}
		return -1;
	}

	@Internal
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		keyCodeScanned = code;
		lastScan = msTime();

		ALL_KEYS.get(KEY_NAMES.get(code)).toggle(true);

		e.consume();
	}

	@Internal
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		ALL_KEYS.get(KEY_NAMES.get(code)).toggle(false);

		e.consume();
	}

	@Internal
	@Override
	public void keyTyped(KeyEvent e) {
		e.consume();
	}
}