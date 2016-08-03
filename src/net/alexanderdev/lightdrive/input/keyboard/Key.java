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
package net.alexanderdev.lightdrive.input.keyboard;

import java.awt.event.KeyEvent;

/**
 * An enum which lists all the keyboard keys in logical order.
 * 
 * @author Christian Bryce Alexander
 * @since May 3, 2016, 2:43:54 AM
 */
public enum Key {
	CANCEL       (KeyEvent.VK_CANCEL),
	BACK_SPACE   (KeyEvent.VK_BACK_SPACE),
	TAB          (KeyEvent.VK_TAB),
	ENTER        (KeyEvent.VK_ENTER),
	CLEAR        (KeyEvent.VK_CLEAR),
	SHIFT        (KeyEvent.VK_SHIFT),
	CONTROL      (KeyEvent.VK_CONTROL),
	ALT          (KeyEvent.VK_ALT),
	PAUSE        (KeyEvent.VK_PAUSE),
	ESCAPE       (KeyEvent.VK_ESCAPE),
	SPACE        (KeyEvent.VK_SPACE),
	PAGE_UP      (KeyEvent.VK_PAGE_UP),
	PAGE_DOWN    (KeyEvent.VK_PAGE_DOWN),
	END          (KeyEvent.VK_END),
	HOME         (KeyEvent.VK_HOME),
	LEFT         (KeyEvent.VK_LEFT),
	UP           (KeyEvent.VK_UP),
	RIGHT        (KeyEvent.VK_RIGHT),
	DOWN         (KeyEvent.VK_DOWN),
	COMMA        (KeyEvent.VK_COMMA),
	MINUS        (KeyEvent.VK_MINUS),
	PERIOD       (KeyEvent.VK_PERIOD),
	SLASH        (KeyEvent.VK_SLASH),
	ZERO         (KeyEvent.VK_0),
	ONE          (KeyEvent.VK_1),
	TWO          (KeyEvent.VK_2),
	THREE        (KeyEvent.VK_3),
	FOUR         (KeyEvent.VK_4),
	FIVE         (KeyEvent.VK_5),
	SIX          (KeyEvent.VK_6),
	SEVEN        (KeyEvent.VK_7),
	EIGHT        (KeyEvent.VK_8),
	NINE         (KeyEvent.VK_9),
	SEMICOLON    (KeyEvent.VK_SEMICOLON),
	EQUALS       (KeyEvent.VK_EQUALS),
	A            (KeyEvent.VK_A),
	B            (KeyEvent.VK_B),
	C            (KeyEvent.VK_C),
	D            (KeyEvent.VK_D),
	E            (KeyEvent.VK_E),
	F            (KeyEvent.VK_F),
	G            (KeyEvent.VK_G),
	H            (KeyEvent.VK_H),
	I            (KeyEvent.VK_I),
	J            (KeyEvent.VK_J),
	K            (KeyEvent.VK_K),
	L            (KeyEvent.VK_L),
	M            (KeyEvent.VK_M),
	N            (KeyEvent.VK_N),
	O            (KeyEvent.VK_O),
	P            (KeyEvent.VK_P),
	Q            (KeyEvent.VK_Q),
	R            (KeyEvent.VK_R),
	S            (KeyEvent.VK_S),
	T            (KeyEvent.VK_T),
	U            (KeyEvent.VK_U),
	V            (KeyEvent.VK_V),
	W            (KeyEvent.VK_W),
	X            (KeyEvent.VK_X),
	Y            (KeyEvent.VK_Y),
	Z            (KeyEvent.VK_Z),
	OPEN_BRACKET (KeyEvent.VK_OPEN_BRACKET),
	BACK_SLASH   (KeyEvent.VK_BACK_SLASH),
	CLOSE_BRACKET(KeyEvent.VK_CLOSE_BRACKET),
	NUMPAD0      (KeyEvent.VK_NUMPAD0),
	NUMPAD1      (KeyEvent.VK_NUMPAD1),
	NUMPAD2      (KeyEvent.VK_NUMPAD2),
	NUMPAD3      (KeyEvent.VK_NUMPAD3),
	NUMPAD4      (KeyEvent.VK_NUMPAD4),
	NUMPAD5      (KeyEvent.VK_NUMPAD5),
	NUMPAD6      (KeyEvent.VK_NUMPAD6),
	NUMPAD7      (KeyEvent.VK_NUMPAD7),
	NUMPAD8      (KeyEvent.VK_NUMPAD8),
	NUMPAD9      (KeyEvent.VK_NUMPAD9),
	MULTIPLY     (KeyEvent.VK_MULTIPLY),
	ADD          (KeyEvent.VK_ADD),
	SEPARATOR    (KeyEvent.VK_SEPARATOR),
	SUBTRACT     (KeyEvent.VK_SUBTRACT),
	DECIMAL      (KeyEvent.VK_DECIMAL),
	DIVIDE       (KeyEvent.VK_DIVIDE),
	F1           (KeyEvent.VK_F1),
	F2           (KeyEvent.VK_F2),
	F3           (KeyEvent.VK_F3),
	F4           (KeyEvent.VK_F4),
	F5           (KeyEvent.VK_F5),
	F6           (KeyEvent.VK_F6),
	F7           (KeyEvent.VK_F7),
	F8           (KeyEvent.VK_F8),
	F9           (KeyEvent.VK_F9),
	F10          (KeyEvent.VK_F10),
	F11          (KeyEvent.VK_F11),
	F12          (KeyEvent.VK_F12),
	DELETE       (KeyEvent.VK_DELETE),
	AMPERSAND    (KeyEvent.VK_AMPERSAND),
	ASTERISK     (KeyEvent.VK_ASTERISK),
	QUOTEDBL     (KeyEvent.VK_QUOTEDBL),
	LESS         (KeyEvent.VK_LESS),
	GREATER      (KeyEvent.VK_GREATER),
	BRACELEFT    (KeyEvent.VK_BRACELEFT),
	BRACERIGHT   (KeyEvent.VK_BRACERIGHT),
	BACK_QUOTE   (KeyEvent.VK_BACK_QUOTE),
	QUOTE        (KeyEvent.VK_QUOTE),
	KP_UP        (KeyEvent.VK_KP_UP),
	KP_DOWN      (KeyEvent.VK_KP_DOWN),
	KP_LEFT      (KeyEvent.VK_KP_LEFT),
	KP_RIGHT     (KeyEvent.VK_KP_RIGHT);

	public int code;

	private Key(int keyCode) {
		this.code = keyCode;
	}

	public int keyCode() {
		return code;
	}
}