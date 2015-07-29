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
package net.alexanderdev.lightdrive.util.math.geom;

import java.awt.geom.Line2D.Float;

/**
 * A simple wrapper class for {@code Line2D.Float}
 * 
 * @author Christian Bryce Alexander
 * @since Jul 6, 2015 | 1:38:56 AM
 */
public class LineF extends Float {
	private static final long serialVersionUID = -496939973883568734L;

	/**
	 * @param x0
	 *            X coordinate of first point
	 * @param y0
	 *            Y coordinate of first point
	 * @param x1
	 *            X coordinate of second point
	 * @param y1
	 *            Y coordinate of second point
	 */
	public LineF(float x0, float y0, float x1, float y1) {
		super(x0, y0, x1, y1);
	}
}