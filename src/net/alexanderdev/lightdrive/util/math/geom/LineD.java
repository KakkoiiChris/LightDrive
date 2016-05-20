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

import java.awt.geom.Line2D;

/**
 * A simple wrapper class for {@link Line2D.Double}.
 * 
 * @author Christian Bryce Alexander
 * @since Jul 6, 2015 | 1:42:29 AM
 */
public class LineD extends Line2D.Double implements Cloneable {
	private static final long serialVersionUID = 8782250485132923391L;

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
	public LineD(double x0, double y0, double x1, double y1) {
		super(x0, y0, x1, y1);
	}

	/**
	 * @param p0
	 *            The coordinates of the first point
	 * @param p1
	 *            The coordinates of the second point
	 */
	public LineD(VectorD p0, VectorD p1) {
		super(p0.x, p0.y, p1.x, p1.y);
	}

	@Override
	public LineD clone() {
		return new LineD(x1, y1, x2, y2);
	}
}