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

/**
 * @author Christian Bryce Alexander
 * @since Jan 6, 2016, 6:08:49 PM
 */
public class ReplaceFilter implements Filter {
	private int[] map;
	private int[] colors;

	/**
	 * Creates a new {@link ReplaceFilter}.
	 * 
	 * @param map
	 * @param colors
	 */
	public ReplaceFilter(int[] map, int[] colors) {
		if (map.length != colors.length) {
			System.err.println("LIGHT DRIVE ERROR: Mismatching arrays.");
			Thread.dumpStack();
		}

		this.map = map;
		this.colors = colors;
	}

	/**
	 * Sets the colors to be replaced.
	 *
	 * @param map
	 *            The colors to be replaced
	 */
	public void setMap(int[] map) {
		this.map = map;
	}

	/**
	 * Sets the colors to replace with.
	 *
	 * @param colors
	 *            The colors to replace with
	 */
	public void setColors(int[] colors) {
		this.colors = colors;
	}

	@Override
	public void apply(int width, int height, int[] pixels) {
		for (int i = 0; i < pixels.length; i++)
			for (int c = 0; c < map.length; c++)
				if (pixels[i] == map[c])
					pixels[i] = colors[c];
	}
}