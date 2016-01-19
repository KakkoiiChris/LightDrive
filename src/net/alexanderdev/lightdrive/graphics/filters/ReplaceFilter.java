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
package net.alexanderdev.lightdrive.graphics.filters;

/**
 * @author Christian Bryce Alexander
 * @since Jan 6, 2016, 6:08:49 PM
 */
public class ReplaceFilter implements FilterS {
	private int[][] pairs;

	public ReplaceFilter(int[]... pairs) {
		for (int[] pair : pairs)
			if (pair.length != 2) {
				System.err
					.println("QUIXEL ERROR: Invalid pairs for replacement filter (pairs must be arrays of length 2).");
				Thread.dumpStack();
			}

		this.pairs = pairs;

		System.out.println(pairs.length);
		System.out.println(pairs[0].length);
	}

	@Override
	public void apply(int[] pixels) {
		for (int i = 0; i < pixels.length; i++)
			for (int p = 0; p < pairs.length; p++)
				if (pixels[i] == pairs[p][0])
					pixels[i] = pairs[p][1];
	}
}