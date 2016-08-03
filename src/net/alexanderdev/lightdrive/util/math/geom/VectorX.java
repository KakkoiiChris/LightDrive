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

import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

import java.awt.geom.Point2D;

/**
 * A class which represents a 2D vector with float precision. It contains
 * methods for most all vector algebra operations.
 * 
 * @author Christian Bryce Alexander
 * @since Apr 13, 2015, 3:08:10 PM
 */
public strictfp class VectorX extends Point2D.Double implements Cloneable, Comparable<Object> {
	private static final long serialVersionUID = -1490482930677885575L;

	/**
	 * Creates a default {@link VectorX} with no magnitude.
	 */
	public VectorX() {
		this(0.0, 0.0);
	}

	/**
	 * Creates a {@link VectorX} with the specified magnitudes.
	 * 
	 * @param x
	 *            The x magnitude
	 * @param y
	 *            The y magnitude
	 */
	public VectorX(double x, double y) {
		super(x, y);
	}

	/**
	 * Adds the components of {@code vec} to this {@link VectorX}.
	 * 
	 * @param vec
	 *            The {@link VectorX} to be added
	 */
	public void add(VectorX vec) {
		x += vec.x;
		y += vec.y;
	}

	/**
	 * Adds a {@code scalar} to both components of this {@link VectorX}.
	 * 
	 * @param scalar
	 *            Scalar to be added
	 */
	public void add(double scalar) {
		x += scalar;
		y += scalar;
	}

	/**
	 * Subtracts the components of {@code vec} from this {@link VectorX}.
	 * 
	 * @param vec
	 *            The {@link VectorX} to be subtracted
	 */
	public void subtract(VectorX vec) {
		x -= vec.x;
		y -= vec.y;
	}

	/**
	 * Subtracts a {@code scalar} from both components of this {@link VectorX}.
	 * 
	 * @param scalar
	 *            Scalar to be subtracted
	 */
	public void subtract(double scalar) {
		x -= scalar;
		y -= scalar;
	}

	/**
	 * Multiplies the components of this {@link VectorX} by {@code vec}'s
	 * components.
	 * 
	 * @param vec
	 *            The {@link VectorX} to be multiplied by
	 */
	public void multiply(VectorX vec) {
		x *= vec.x;
		y *= vec.y;
	}

	/**
	 * Multiplies both components of this {@link VectorX} by a {@code scalar}.
	 * 
	 * @param scalar
	 *            Scalar to be multiplied by
	 */
	public void multiply(double scalar) {
		x *= scalar;
		y *= scalar;
	}

	/**
	 * Divides the components of this {@link VectorX} by {@code vec}'s
	 * components.
	 * 
	 * @param vec
	 *            The {@link VectorX} to be divided by
	 */
	public void divide(VectorX vec) {
		x /= vec.x;
		y /= vec.y;
	}

	/**
	 * Divides both components of this {@link VectorX} by a {@code scalar}.
	 * 
	 * @param scalar
	 *            Scalar to be divided by
	 */
	public void divide(double scalar) {
		x /= scalar;
		y /= scalar;
	}

	/**
	 * Turns this {@link VectorX} into a unit vector in the same direction.
	 */
	public void normalize() {
		divide(length());
	}

	/**
	 * Sets both components of this {@link VectorX} to zero.
	 */
	public void zero() {
		x = y = 0d;
	}

	/**
	 * Reverses the x component of this {@link VectorX}.
	 */
	public void reverseX() {
		x = -x;
	}

	/**
	 * Reverses the y component of this {@link VectorX}.
	 */
	public void reverseY() {
		y = -y;
	}

	/**
	 * Reverses both components of this {@link VectorX}.
	 */
	public void reverse() {
		reverseX();
		reverseY();
	}

	/**
	 * Rotates this {@link VectorX} by the radian amount {@code angle}.
	 * 
	 * @param angle
	 *            An amount in radians to rotate this {@link VectorX} by
	 */
	public void rotate(double angle) {
		double len = length();

		this.x = x * cos(angle) - y * sin(angle);
		this.y = x * sin(angle) + y * cos(angle);

		setLength(len);
	}

	/**
	 * Changes the length of this {@link VectorX}.
	 * 
	 * @param length
	 *            The length for this {@link VectorX} to be resized to
	 */
	public void setLength(double length) {
		normalize();

		multiply(length);
	}

	/**
	 * The dot product of two {@link VectorX}'s.
	 * 
	 * @param vec
	 *            The second {@link VectorX}
	 * 
	 * @return The dot product of this {@link VectorX} and {@code vec}
	 */
	public double dot(VectorX vec) {
		return x * vec.x + y * vec.y;
	}

	/**
	 * The cross product of two {@link VectorX}'s.
	 * 
	 * @param vec
	 *            The second {@link VectorX}
	 * 
	 * @return The cross product of this {@link VectorX} and {@code vec}
	 */
	public double cross(VectorX vec) {
		return x * vec.y - y * vec.x;
	}

	/**
	 * @return The length of this {@link VectorX}
	 */
	public double length() {
		return sqrt(pow(x, 2) + pow(y, 2));
	}

	/**
	 * @return The angle of this {@link VectorX}
	 */
	public double angle() {
		return atan2(y, x);
	}

	/**
	 * @param vec
	 *            The {@link VectorX} to point towards
	 * 
	 * @return A {@link VectorX} from this {@link VectorX} to {@code vec}
	 */
	public VectorX vectorTo(VectorX vec) {
		return new VectorX(-(x - vec.x), -(y - vec.y));
	}

	@Override
	public String toString() {
		return String.format("%s: (%d, %d), Length: %d", getClass().toString(), x, y, length());
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		else if (o instanceof VectorX) {
			VectorX vec = (VectorX) o;
			return (vec.x == this.x) && (vec.y == this.y);
		}
		return false;
	}

	@Override
	public VectorX clone() {
		return new VectorX(x, y);
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}
}