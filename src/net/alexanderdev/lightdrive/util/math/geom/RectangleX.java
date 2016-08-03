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
 * A simple wrapper class for {@link Rectangle2D.Double}.
 * 
 * @author Christian Bryce Alexander
 * @since Jun 5, 2015, 5:37:13 PM
 */
public class RectangleX extends Rectangle2D.Double implements Cloneable {
	private static final long serialVersionUID = 8026136184017414846L;

	/**
	 * Creates a rectangle shape of double floating point precision, at the
	 * origin, and with the specified size.
	 * 
	 * @param width
	 *            The width of the rectangle
	 * @param height
	 *            The height of the rectangle
	 */
	public RectangleX(double width, double height) {
		super(0, 0, width, height);
	}

	/**
	 * Creates a rectangle shape of double floating point precision, at the
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
	public RectangleX(double x, double y, double width, double height) {
		super(x, y, width, height);
	}

	public VectorX getPosition() {
		return new VectorX(x, y);
	}

	public VectorX getCenter() {
		return new VectorX(getCenterX(), getCenterY());
	}

	/**
	 * @return A copy of this {@link RectangleX} rotated around the specified
	 *         point
	 */
	public Shape rotated(double theta, double anchorX, double anchorY) {
		return AffineTransform.getRotateInstance(theta, anchorX, anchorY).createTransformedShape(this);
	}

	/**
	 * @return A copy of this {@link RectangleX} rotated around the center
	 */
	public Shape rotated(double theta) {
		return rotated(theta, getCenterX(), getCenterY());
	}

	/**
	 * @return A copy of this {@link RectangleX} scaled
	 */
	public Shape scaled(double scaleX, double scaleY) {
		return AffineTransform.getScaleInstance(scaleX, scaleY).createTransformedShape(this);
	}

	/**
	 * @return A copy of this {@link RectangleX} sheared
	 */
	public Shape sheared(double shearX, double shearY) {
		return AffineTransform.getShearInstance(shearX, shearY).createTransformedShape(this);
	}

	/**
	 * @return A copy of this {@link RectangleX} translated
	 */
	public Shape translated(double translateX, double translateY) {
		return AffineTransform.getTranslateInstance(translateX, translateY).createTransformedShape(this);
	}

	@Override
	public RectangleX clone() {
		return new RectangleX(x, y, width, height);
	}
}