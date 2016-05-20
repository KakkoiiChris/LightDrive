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
package net.alexanderdev.lightdrive.graphics.filter;

import net.alexanderdev.lightdrive.graphics.GrayscaleMode;

/**
 * @author Christian Bryce Alexander
 * @since May 18, 2016, 1:30:03 PM
 */
public class CyanotypeFilter extends GrayscaleFilter {
	private AdjustFilter cyanotype;
	private BrightnessFilter bright;

	public CyanotypeFilter(GrayscaleMode mode) {
		super(mode);

		cyanotype = new AdjustFilter(0xff4e8aef);

		bright = new BrightnessFilter(0.1f);
	}

	@Override
	public void apply(int width, int height, int[] pixels) {
		super.apply(width, height, pixels);

		cyanotype.apply(width, height, pixels);

		bright.apply(width, height, pixels);
	}
}