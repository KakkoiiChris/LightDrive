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
import java.awt.geom.Ellipse2D;

/**
 * A simple wrapper class for {@link Ellipse2D.Float}.
 * 
 * @author Christian Bryce Alexander
 * @since Apr 26, 2015, 1:02:33 AM
 */
public class EllipseF extends Ellipse2D.Float implements Cloneable {
	private static final long serialVersionUID = -6768308190202483835L;

	/**
	 * Creates and ellipse shape of single floating point precision, at the
	 * origin, and with the specified size.
	 * 
	 * @param width
	 *            The width of the ellipse
	 * @param height
	 *            The height of the ellipse
	 */
	public EllipseF(float width, float height) {
		super(0, 0, width, height);
	}

	/**
	 * Creates an ellipse shape of single floating point precision, at the
	 * specified position, and with the specified size.
	 * 
	 * @param x
	 *            The top-left-most x coordinate of the ellipse
	 * @param y
	 *            The top-left-most y coordinate of the ellipse
	 * @param width
	 *            The width of the ellipse
	 * @param height
	 *            The height of the ellipse
	 */
	public EllipseF(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	/**
	 * @return A copy of this {@link EllipseF} rotated around the specified
	 *         point
	 */
	public Shape rotated(float theta, float anchorX, float anchorY) {
		return AffineTransform.getRotateInstance(theta, anchorX, anchorY).createTransformedShape(this);
	}

	/**
	 * @return A copy of this {@link EllipseF} rotated around the center
	 */
	public Shape rotated(float theta) {
		return rotated(theta, (float) getCenterX(), (float) getCenterY());
	}

	/**
	 * @return A copy of this {@link EllipseF} scaled
	 */
	public Shape scaled(float scaleX, float scaleY) {
		return AffineTransform.getScaleInstance(scaleX, scaleY).createTransformedShape(this);
	}

	/**
	 * @return A copy of this {@link EllipseF} sheared
	 */
	public Shape sheared(float shearX, float shearY) {
		return AffineTransform.getShearInstance(shearX, shearY).createTransformedShape(this);
	}

	/**
	 * @return A copy of this {@link EllipseF} translated
	 */
	public Shape translated(float translateX, float translateY) {
		return AffineTransform.getTranslateInstance(translateX, translateY).createTransformedShape(this);
	}

	@Override
	public EllipseF clone() {
		return new EllipseF(x, y, width, height);
	}
}