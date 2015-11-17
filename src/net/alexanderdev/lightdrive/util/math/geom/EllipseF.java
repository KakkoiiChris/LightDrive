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
 * @author Christian Bryce Alexander
 * @since Apr 26, 2015 | 1:02:33 AM
 */
public class EllipseF extends Ellipse2D.Float {
	private static final long serialVersionUID = -6768308190202483835L;

	public EllipseF(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	public Shape rotated(float theta, float anchorX, float anchorY) {
		return AffineTransform.getRotateInstance(theta, anchorX, anchorY).createTransformedShape(this);
	}

	public Shape rotated(float theta) {
		return rotated(theta, (float) getCenterX(), (float) getCenterY());
	}

	public Shape scaled(float scaleX, float scaleY) {
		return AffineTransform.getScaleInstance(scaleX, scaleY).createTransformedShape(this);
	}

	public Shape sheared(float shearX, float shearY) {
		return AffineTransform.getShearInstance(shearX, shearY).createTransformedShape(this);
	}

	public Shape translated(float translateX, float translateY) {
		return AffineTransform.getTranslateInstance(translateX, translateY).createTransformedShape(this);
	}
}