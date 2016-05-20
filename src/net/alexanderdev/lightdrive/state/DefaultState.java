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

import net.alexanderdev.lightdrive.InternalType;
import net.alexanderdev.lightdrive.graphics.GraphicsX;
import net.alexanderdev.lightdrive.graphics.Sprite;
import net.alexanderdev.lightdrive.util.io.SpriteIO;

/**
 * A {@link State} which serves as both a placeholder, so that no null checks
 * have to be made if no states have been added to the {@link StateManager}, and
 * as a sort of "Hello World" visualization, to test that the library functions
 * properly.
 * 
 * @author Christian Bryce Alexander
 * @since May 19, 2015 | 11:23:47 PM
 */
@InternalType
public final class DefaultState extends State {
	private Sprite splash;

	public DefaultState() {
		SpriteIO.setPath("/img/lightdrive/");
		splash = SpriteIO.loadPNG("splash640x480");
	}

	@Override
	public void switchTo(Object... argv) {
	}

	@Override
	public void switchFrom() {
	}

	@Override
	public void update(double delta) {
	}

	@Override
	public void render(GraphicsX g) {
		g.drawImage(splash, getManager().getView().getViewBounds());
	}
}