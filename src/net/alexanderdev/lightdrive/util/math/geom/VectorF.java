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
 * @since Apr 13, 2015 | 3:08:10 PM
 */
public strictfp class VectorF extends Point2D.Float implements Comparable<Object> {
	private static final long serialVersionUID = -1490482930677885575L;

	/**
	 * {@code VectorF} with specified magnitudes
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
	 * {@code VectorF} with magnitudes copied from {@code vec}
	 * 
	 * @param vec
	 *            The {@code VectorF} to be copied
	 */
	public VectorF(VectorF vec) {
		this(vec.x, vec.y);
	}

	/**
	 * For converting a {@code VectorD} into a {@code VectorF}
	 * 
	 * @param vec
	 *            The {@code VectorF} to be copied
	 */
	public VectorF(VectorD vec) {
		this((float) vec.x, (float) vec.y);
	}

	/**
	 * Default {@code VectorF} with no magnitude
	 */
	public VectorF() {
		this(0f, 0f);
	}

	/**
	 * Adds the components of {@code vec} to this {@code VectorF}
	 * 
	 * @param vec
	 *            The {@code VectorF} to be added
	 */
	public void add(VectorF vec) {
		x += vec.x;
		y += vec.y;
	}

	/**
	 * Subtracts the components of {@code vec} from this {@code VectorF}
	 * 
	 * @param vec
	 *            The {@code VectorF} to be subtracted
	 */
	public void subtract(VectorF vec) {
		x -= vec.x;
		y -= vec.y;
	}

	/**
	 * Multiplies the components of this {@code VectorF} by {@code vec}
	 * 
	 * @param vec
	 *            The {@code VectorF} to be multiplied by
	 */
	public void multiply(VectorF vec) {
		x *= vec.x;
		y *= vec.y;
	}

	/**
	 * Divides the components of this {@code VectorF} by {@code vec}
	 * 
	 * @param vec
	 *            The {@code VectorF} to be divided by
	 */
	public void divide(VectorF vec) {
		x /= vec.x;
		y /= vec.y;
	}

	/**
	 * Adds a {@code scalar} to both components of this {@code VectorF}
	 * 
	 * @param scalar
	 *            Scalar to be added
	 */
	public void addScalar(float scalar) {
		x += scalar;
		y += scalar;
	}

	/**
	 * Subtracts a {@code scalar} from both components of this {@code VectorF}
	 * 
	 * @param scalar
	 *            Scalar to be subtracted
	 */
	public void subtractScalar(float scalar) {
		x -= scalar;
		y -= scalar;
	}

	/**
	 * Multiplies both components of this {@code VectorF} by a {@code scalar}
	 * 
	 * @param scalar
	 *            Scalar to be multiplied by
	 */
	public void multiplyScalar(float scalar) {
		x *= scalar;
		y *= scalar;
	}

	/**
	 * Divides both components of this {@code VectorF} by a {@code scalar}
	 * 
	 * @param scalar
	 *            Scalar to be divided by
	 */
	public void divideScalar(float scalar) {
		x /= scalar;
		y /= scalar;
	}

	/**
	 * Turns this {@code VectorF} into a unit vector in the same direction
	 */
	public void normalize() {
		divideScalar(length());
	}

	/**
	 * Sets both components of this {@code VectorF} to zero
	 */
	public void zero() {
		x = y = 0f;
	}

	/**
	 * Reverses the x component of this {@code VectorF}
	 */
	public void reverseX() {
		x = -x;
	}

	/**
	 * Reverses the y component of this {@code VectorF}
	 */
	public void reverseY() {
		y = -y;
	}

	/**
	 * Reverses both components of this {@code VectorF}
	 */
	public void reverse() {
		reverseX();
		reverseY();
	}

	/**
	 * Rotates this {@code VectorF} by the radian amount {@code angle}
	 * 
	 * @param angle
	 *            An amount in radians to rotate this {@code VectorF} by
	 */
	public void rotate(float angle) {
		float len = length();

		this.x = (float) (x * cos(angle) - y * sin(angle));
		this.y = (float) (x * sin(angle) + y * cos(angle));

		setLength(len);
	}

	/**
	 * Changes the length of this {@code VectorF}
	 * 
	 * @param length
	 *            The length for this {@code VectorF} to be resized to
	 */
	public void setLength(float length) {
		normalize();

		multiplyScalar(length);
	}

	/**
	 * The dot product of two {@code VectorF}'s
	 * 
	 * @param vec
	 *            The second {@code VectorF}
	 * 
	 * @return The dot product of this {@code VectorF} and {@code vec}
	 */
	public float dot(VectorF vec) {
		return x * vec.x + y * vec.y;
	}

	/**
	 * The cross product of two {@code VectorF}'s
	 * 
	 * @param vec
	 *            The second {@code VectorF}
	 * 
	 * @return The cross product of this {@code VectorF} and {@code vec}
	 */
	public float cross(VectorF vec) {
		return x * vec.y - y * vec.x;
	}

	/**
	 * @return The length of this {@code VectorF}
	 */
	public float length() {
		return (float) sqrt(pow(x, 2) + pow(y, 2));
	}

	/**
	 * @return The angle of this {@code VectorF}
	 */
	public float angle() {
		return (float) atan2(y, x);
	}

	/**
	 * @param The
	 *            {@code VectorD} to point towards
	 * 
	 * @return A {@code VectorD} from this {@code VectorD} to {@code vec}
	 */
	public VectorF vectorTo(VectorF vec) {
		return new VectorF(-(x - vec.x), -(y - vec.y));
	}

	/**
	 * @param The
	 *            {@code VectorD} to point towards
	 * 
	 * @return A {@code VectorD} from this {@code VectorD} to {@code vec}
	 */
	public VectorF vectorTo(VectorD vec) {
		return new VectorF((float) -(x - vec.x), (float) -(y - vec.y));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return String.format("%s: (%d, %d), Length: %d", getClass().toString(), x, y, length());
	}

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Object o) {
		return 0;
	}
}