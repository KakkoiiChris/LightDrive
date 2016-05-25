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
 * A simple wrapper class for {@link Ellipse2D.Double}.
 * 
 * @author Christian Bryce Alexander
 * @since Apr 26, 2015, 1:06:15 AM
 */
public class EllipseD extends Ellipse2D.Double implements Cloneable {
	private static final long serialVersionUID = -7534681747032782328L;

	/**
	 * Creates and ellipse shape of double floating point precision, at the
	 * origin, and with the specified size.
	 * 
	 * @param width
	 *            The width of the ellipse
	 * @param height
	 *            The height of the ellipse
	 */
	public EllipseD(double width, double height) {
		super(0, 0, width, height);
	}

	/**
	 * Creates an ellipse shape of double floating point precision, at the
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
	public EllipseD(double x, double y, double width, double height) {
		super(x, y, width, height);
	}

	/**
	 * @return A copy of this {@link EllipseD} rotated around the specified
	 *         point
	 */
	public Shape rotated(double theta, double anchorX, double anchorY) {
		return AffineTransform.getRotateInstance(theta, anchorX, anchorY).createTransformedShape(this);
	}

	/**
	 * @return A copy of this {@link EllipseD} rotated around the center
	 */
	public Shape rotated(double theta) {
		return rotated(theta, getCenterX(), getCenterY());
	}

	/**
	 * @return A copy of this {@link EllipseD} scaled
	 */
	public Shape scaled(double scaleX, double scaleY) {
		return AffineTransform.getScaleInstance(scaleX, scaleY).createTransformedShape(this);
	}

	/**
	 * @return A copy of this {@link EllipseD} sheared
	 */
	public Shape sheared(double shearX, double shearY) {
		return AffineTransform.getShearInstance(shearX, shearY).createTransformedShape(this);
	}

	/**
	 * @return A copy of this {@link EllipseD} translated
	 */
	public Shape translated(double translateX, double translateY) {
		return AffineTransform.getTranslateInstance(translateX, translateY).createTransformedShape(this);
	}

	@Override
	public EllipseD clone() {
		return new EllipseD(x, y, width, height);
	}
}