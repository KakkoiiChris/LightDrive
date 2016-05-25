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
public strictfp class VectorF extends Point2D.Float implements Cloneable, Comparable<Object> {
	private static final long serialVersionUID = -1490482930677885575L;

	/**
	 * Creates a default {@link VectorF} with no magnitude.
	 */
	public VectorF() {
		this(0f, 0f);
	}

	/**
	 * Creates a {@link VectorF} with the specified magnitudes.
	 * 
	 * @param x
	 *            The x magnitude
	 * @param y
	 *            The y magnitude
	 */
	public VectorF(float x, float y) {
		super(x, y);
	}

	/**
	 * Adds the components of {@code vec} to this {@link VectorF}.
	 * 
	 * @param vec
	 *            The {@link VectorF} to be added
	 */
	public void add(VectorF vec) {
		x += vec.x;
		y += vec.y;
	}

	/**
	 * Adds a {@code scalar} to both components of this {@link VectorF}.
	 * 
	 * @param scalar
	 *            Scalar to be added
	 */
	public void add(float scalar) {
		x += scalar;
		y += scalar;
	}

	/**
	 * Subtracts the components of {@code vec} from this {@link VectorF}.
	 * 
	 * @param vec
	 *            The {@link VectorF} to be subtracted
	 */
	public void subtract(VectorF vec) {
		x -= vec.x;
		y -= vec.y;
	}

	/**
	 * Subtracts a {@code scalar} from both components of this {@link VectorF}.
	 * 
	 * @param scalar
	 *            Scalar to be subtracted
	 */
	public void subtract(float scalar) {
		x -= scalar;
		y -= scalar;
	}

	/**
	 * Multiplies the components of this {@link VectorF} by {@code vec}.
	 * 
	 * @param vec
	 *            The {@link VectorF} to be multiplied by
	 */
	public void multiply(VectorF vec) {
		x *= vec.x;
		y *= vec.y;
	}

	/**
	 * Multiplies both components of this {@link VectorF} by a {@code scalar}.
	 * 
	 * @param scalar
	 *            Scalar to be multiplied by
	 */
	public void multiply(float scalar) {
		x *= scalar;
		y *= scalar;
	}

	/**
	 * Divides the components of this {@link VectorF} by {@code vec}'s
	 * components.
	 * 
	 * @param vec
	 *            The {@link VectorF} to be divided by
	 */
	public void divide(VectorF vec) {
		x /= vec.x;
		y /= vec.y;
	}

	/**
	 * Divides both components of this {@link VectorF} by a {@code scalar}.
	 * 
	 * @param scalar
	 *            Scalar to be divided by
	 */
	public void divide(float scalar) {
		x /= scalar;
		y /= scalar;
	}

	/**
	 * Turns this {@link VectorF} into a unit vector in the same direction.
	 */
	public void normalize() {
		divide(length());
	}

	/**
	 * Sets both components of this {@link VectorF} to zero.
	 */
	public void zero() {
		x = y = 0f;
	}

	/**
	 * Reverses the x component of this {@link VectorF}.
	 */
	public void reverseX() {
		x = -x;
	}

	/**
	 * Reverses the y component of this {@link VectorF}.
	 */
	public void reverseY() {
		y = -y;
	}

	/**
	 * Reverses both components of this {@link VectorF}.
	 */
	public void reverse() {
		reverseX();
		reverseY();
	}

	/**
	 * Rotates this {@link VectorF} by the radian amount {@code angle}.
	 * 
	 * @param angle
	 *            An amount in radians to rotate this {@link VectorF} by
	 */
	public void rotate(float angle) {
		float len = length();

		this.x = (float) (x * cos(angle) - y * sin(angle));
		this.y = (float) (x * sin(angle) + y * cos(angle));

		setLength(len);
	}

	/**
	 * Changes the length of this {@link VectorF}.
	 * 
	 * @param length
	 *            The length for this {@link VectorF} to be resized to
	 */
	public void setLength(float length) {
		normalize();

		multiply(length);
	}

	/**
	 * The dot product of two {@link VectorF}'s.
	 * 
	 * @param vec
	 *            The second {@link VectorF}
	 * 
	 * @return The dot product of this {@link VectorF} and {@code vec}
	 */
	public float dot(VectorF vec) {
		return x * vec.x + y * vec.y;
	}

	/**
	 * The cross product of two {@link VectorF}'s.
	 * 
	 * @param vec
	 *            The second {@link VectorF}
	 * 
	 * @return The cross product of this {@link VectorF} and {@code vec}
	 */
	public float cross(VectorF vec) {
		return x * vec.y - y * vec.x;
	}

	/**
	 * @return The length of this {@link VectorF}
	 */
	public float length() {
		return (float) sqrt(pow(x, 2) + pow(y, 2));
	}

	/**
	 * @return The angle of this {@link VectorF}
	 */
	public float angle() {
		return (float) atan2(y, x);
	}

	/**
	 * @param vec
	 *            The {@link VectorD} to point towards
	 * 
	 * @return A {@link VectorD} from this {@link VectorD} to {@code vec}
	 */
	public VectorF vectorTo(VectorF vec) {
		return new VectorF(-(x - vec.x), -(y - vec.y));
	}

	/**
	 * @param vec
	 *            The {@link VectorD} to point towards
	 * 
	 * @return A {@link VectorD} from this {@link VectorD} to {@code vec}
	 */
	public VectorF vectorTo(VectorD vec) {
		return new VectorF((float) -(x - vec.x), (float) -(y - vec.y));
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
		else if (o instanceof VectorF) {
			VectorF vec = (VectorF) o;
			return (vec.x == this.x) && (vec.y == this.y);
		}
		else if (o instanceof VectorD) {
			VectorD vec = (VectorD) o;
			return ((float) vec.x == this.x) && ((float) vec.y == this.y);
		}
		return false;
	}

	@Override
	public VectorF clone() {
		return new VectorF(x, y);
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}
}