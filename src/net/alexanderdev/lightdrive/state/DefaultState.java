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

import net.alexanderdev.lightdrive.graphics.GraphicsS;
import net.alexanderdev.lightdrive.graphics.Sprite;
import net.alexanderdev.lightdrive.graphics.SpriteIO;

/**
 * @author Christian Bryce Alexander
 * @since May 19, 2015 | 11:23:47 PM
 */
public final class DefaultState extends State {
	private Sprite splash;

	public DefaultState() {
		splash = SpriteIO.loadPNG("/img/splash640x480");
	}

	@Override
	public void switchTo(Object... argv) {
	}

	@Override
	public void update(double delta) {
	}

	@Override
	public void render(GraphicsS g) {
		g.drawCenteredImage(splash, getManager().getView().getViewBounds());
	}
}