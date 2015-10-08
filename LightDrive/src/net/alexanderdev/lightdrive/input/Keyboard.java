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

import static net.alexanderdev.lightdrive.util.Time.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import net.alexanderdev.lightdrive.Internal;

/**
 * A class which encapsulates the {@code KeyListener} interface, and enables
 * more game-friendly functionality.
 * 
 * @author Christian Bryce Alexander
 * @since March 6, 2015 | 2:47:47 AM
 */
public final class Keyboard extends KeyAdapter {
	private static final Key[] KEYS = new Key[525];

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

	public Keyboard() {
		addKey(ESCAPE);
		addKey(DELETE);
		addKey(BACK_SPACE);
		addKey(TAB);
		addKey(SHIFT);
		addKey(CONTROL);
		addKey(ALT);
		addKey(SPACE);
		addKey(ENTER);

		addKey(BACK_QUOTE);

		addKey(F1);
		addKey(F2);
		addKey(F3);
		addKey(F4);
		addKey(F5);
		addKey(F6);
		addKey(F7);
		addKey(F8);
		addKey(F9);
		addKey(F10);
		addKey(F11);
		addKey(F12);

		addKey(ZERO);
		addKey(ONE);
		addKey(TWO);
		addKey(THREE);
		addKey(FOUR);
		addKey(FIVE);
		addKey(SIX);
		addKey(SEVEN);
		addKey(EIGHT);
		addKey(NINE);

		addKey(A);
		addKey(B);
		addKey(C);
		addKey(D);
		addKey(E);
		addKey(F);
		addKey(G);
		addKey(H);
		addKey(I);
		addKey(J);
		addKey(K);
		addKey(L);
		addKey(M);
		addKey(N);
		addKey(O);
		addKey(P);
		addKey(Q);
		addKey(R);
		addKey(S);
		addKey(T);
		addKey(U);
		addKey(V);
		addKey(W);
		addKey(X);
		addKey(Y);
		addKey(Z);

		addKey(UP);
		addKey(DOWN);
		addKey(LEFT);
		addKey(RIGHT);
	}

	/**
	 * Represents the current state of single key on the keyboard.
	 */
	public static class Key {
		private final long TIMEOUT = 50;

		private boolean held = false;
		private boolean pressed = false;
		private boolean released = false;

		private int keyCode;

		private long timer;

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
				timer = msTime();
			}

			if (!held && this.held) {
				released = true;
				timer = msTime();
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
				return msTime() - timer < TIMEOUT;
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
				return msTime() - timer < TIMEOUT;
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
	public static void addKey(Key key) {
		KEYS[key.keyCode] = key;
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

		KEYS[code].toggle(true);

		e.consume();
	}

	@Internal
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		KEYS[code].toggle(false);

		e.consume();
	}
}