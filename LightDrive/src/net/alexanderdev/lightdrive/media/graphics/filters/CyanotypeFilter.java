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
package net.alexanderdev.lightdrive.media.graphics.filters;

/**
 * A filter which applies a cyanotype tone to the image.
 * 
 * @author Christian Bryce Alexander
 * @since Jul 20, 2015 | 11:39:40 PM
 */
public class CyanotypeFilter extends GrayscaleFilter {
	private AdjustFilter cyanotype;

	public CyanotypeFilter() {
		super(Mode.AVERAGE);
		cyanotype = new AdjustFilter(0.5f, 0.7f, 1f);
	}

	@Override
	public void apply(int[] pixels) {
		super.apply(pixels);
		cyanotype.apply(pixels);
	}
}