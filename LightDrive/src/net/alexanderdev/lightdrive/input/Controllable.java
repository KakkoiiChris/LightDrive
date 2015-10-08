package net.alexanderdev.lightdrive.input;

/**
 * @author Christian Bryce Alexander
 * @since Sep 15, 2015, 11:26:38 AM
 */
public interface Controllable {
	public void keyboardInput(Keyboard keyboard);

	public void mouseInput(Mouse mouse);

	public void gamepadInput(Gamepad gamepad);
}
