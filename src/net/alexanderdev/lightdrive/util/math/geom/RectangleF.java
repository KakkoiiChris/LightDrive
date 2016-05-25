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

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

/**
 * A simple wrapper class for {@link Rectangle2D.Float}.
 * 
 * @author Christian Bryce Alexander
 * @since Jun 5, 2015, 5:35:32 PM
 */
public class RectangleF extends Rectangle2D.Float implements Cloneable {
	private static final long serialVersionUID = -6901034585863979972L;

	/**
	 * Creates a rectangle shape of single floating point precision, at the
	 * origin, and with the specified size.
	 * 
	 * @param width
	 *            The width of the rectangle
	 * @param height
	 *            The height of the rectangle
	 */
	public RectangleF(float width, float height) {
		super(0, 0, width, height);
	}

	/**
	 * Creates a rectangle shape of single floating point precision, at the
	 * specified position, and with the specified size.
	 * 
	 * @param x
	 *            The top-left-most x coordinate of the rectangle
	 * @param y
	 *            The top-left-most y coordinate of the rectangle
	 * @param width
	 *            The width of the rectangle
	 * @param height
	 *            The height of the rectangle
	 */
	public RectangleF(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	/**
	 * @return A copy of this {@link RectangleF} rotated around the specified
	 *         point
	 */
	public Shape rotated(float theta, float anchorX, float anchorY) {
		return AffineTransform.getRotateInstance(theta, anchorX, anchorY).createTransformedShape(this);
	}

	/**
	 * @return A copy of this {@link RectangleF} rotated around the center
	 */
	public Shape rotated(float theta) {
		return rotated(theta, (float) getCenterX(), (float) getCenterY());
	}

	/**
	 * @return A copy of this {@link RectangleF} scaled
	 */
	public Shape scaled(float scaleX, float scaleY) {
		return AffineTransform.getScaleInstance(scaleX, scaleY).createTransformedShape(this);
	}

	/**
	 * @return A copy of this {@link RectangleF} sheared
	 */
	public Shape sheared(float shearX, float shearY) {
		return AffineTransform.getShearInstance(shearX, shearY).createTransformedShape(this);
	}

	/**
	 * @return A copy of this {@link RectangleF} translated
	 */
	public Shape translated(float translateX, float translateY) {
		return AffineTransform.getTranslateInstance(translateX, translateY).createTransformedShape(this);
	}

	@Override
	public RectangleF clone() {
		return new RectangleF(x, y, width, height);
	}
}