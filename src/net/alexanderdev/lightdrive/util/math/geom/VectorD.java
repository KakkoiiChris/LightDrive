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
public strictfp class VectorD extends Point2D.Double implements Comparable<Object> {
	private static final long serialVersionUID = -1490482930677885575L;

	/**
	 * {@code VectorD} with specified magnitudes
	 * 
	 * @param x
	 *            The x magnitude
	 * @param y
	 *            The y magnitude
	 */
	public VectorD(double x, double y) {
		super(x, y);
	}

	/**
	 * {@code VectorD} with magnitudes copied from {@code vec}
	 * 
	 * @param vec
	 *            The {@code VectorD} to be copied
	 */
	public VectorD(VectorD vec) {
		this(vec.x, vec.y);
	}

	/**
	 * For converting a {@code VectorD} into a {@code VectorD}
	 * 
	 * @param vec
	 *            The {@code VectorF} to be copied
	 */
	public VectorD(VectorF vec) {
		this(vec.x, vec.y);
	}

	/**
	 * Default {@code VectorD} with no magnitude
	 */
	public VectorD() {
		this(0.0, 0.0);
	}

	/**
	 * Adds the components of {@code vec} to this {@code VectorD}
	 * 
	 * @param vec
	 *            The {@code VectorD} to be added
	 */
	public void add(VectorD vec) {
		x += vec.x;
		y += vec.y;
	}

	/**
	 * Subtracts the components of {@code vec} from this {@code VectorD}
	 * 
	 * @param vec
	 *            The {@code VectorD} to be subtracted
	 */
	public void subtract(VectorD vec) {
		x -= vec.x;
		y -= vec.y;
	}

	/**
	 * Multiplies the components of this {@code VectorD} by {@code vec}
	 * 
	 * @param vec
	 *            The {@code VectorD} to be multiplied by
	 */
	public void multiply(VectorD vec) {
		x *= vec.x;
		y *= vec.y;
	}

	/**
	 * Divides the components of this {@code VectorD} by {@code vec}
	 * 
	 * @param vec
	 *            The {@code VectorD} to be divided by
	 */
	public void divide(VectorD vec) {
		x /= vec.x;
		y /= vec.y;
	}

	/**
	 * Adds a {@code scalar} to both components of this {@code VectorD}
	 * 
	 * @param scalar
	 *            Scalar to be added
	 */
	public void addScalar(double scalar) {
		x += scalar;
		y += scalar;
	}

	/**
	 * Subtracts a {@code scalar} from both components of this {@code VectorD}
	 * 
	 * @param scalar
	 *            Scalar to be subtracted
	 */
	public void subtractScalar(double scalar) {
		x -= scalar;
		y -= scalar;
	}

	/**
	 * Multiplies both components of this {@code VectorD} by a {@code scalar}
	 * 
	 * @param scalar
	 *            Scalar to be multiplied by
	 */
	public void multiplyScalar(double scalar) {
		x *= scalar;
		y *= scalar;
	}

	/**
	 * Divides both components of this {@code VectorD} by a {@code scalar}
	 * 
	 * @param scalar
	 *            Scalar to be divided by
	 */
	public void divideScalar(double scalar) {
		x /= scalar;
		y /= scalar;
	}

	/**
	 * Turns this {@code VectorD} into a unit vector in the same direction
	 */
	public void normalize() {
		divideScalar(length());
	}

	/**
	 * Sets both components of this {@code VectorD} to zero
	 */
	public void zero() {
		x = y = 0d;
	}

	/**
	 * Reverses the x component of this {@code VectorD}
	 */
	public void reverseX() {
		x = -x;
	}

	/**
	 * Reverses the y component of this {@code VectorD}
	 */
	public void reverseY() {
		y = -y;
	}

	/**
	 * Reverses both components of this {@code VectorD}
	 */
	public void reverse() {
		reverseX();
		reverseY();
	}

	/**
	 * Rotates this {@code VectorD} by the radian amount {@code angle}
	 * 
	 * @param angle
	 *            An amount in radians to rotate this {@code VectorD} by
	 */
	public void rotate(double angle) {
		double len = length();

		this.x = x * cos(angle) - y * sin(angle);
		this.y = x * sin(angle) + y * cos(angle);

		setLength(len);
	}

	/**
	 * Changes the length of this {@code VectorD}
	 * 
	 * @param length
	 *            The length for this {@code VectorD} to be resized to
	 */
	public void setLength(double length) {
		normalize();

		multiplyScalar(length);
	}

	/**
	 * The dot product of two {@code VectorD}'s
	 * 
	 * @param vec
	 *            The second {@code VectorD}
	 * 
	 * @return The dot product of this {@code VectorD} and {@code vec}
	 */
	public double dot(VectorD vec) {
		return x * vec.x + y * vec.y;
	}

	/**
	 * The cross product of two {@code VectorD}'s
	 * 
	 * @param vec
	 *            The second {@code VectorD}
	 * 
	 * @return The cross product of this {@code VectorD} and {@code vec}
	 */
	public double cross(VectorD vec) {
		return x * vec.y - y * vec.x;
	}

	/**
	 * @return The length of this {@code VectorD}
	 */
	public double length() {
		return sqrt(pow(x, 2) + pow(y, 2));
	}

	/**
	 * @return The angle of this {@code VectorD}
	 */
	public double angle() {
		return atan2(y, x);
	}

	/**
	 * @param The
	 *            {@code VectorD} to point towards
	 * 
	 * @return A {@code VectorD} from this {@code VectorD} to {@code vec}
	 */
	public VectorD vectorTo(VectorF vec) {
		return new VectorD(-(x - vec.x), -(y - vec.y));
	}

	/**
	 * @param The
	 *            {@code VectorD} to point towards
	 * 
	 * @return A {@code VectorD} from this {@code VectorD} to {@code vec}
	 */
	public VectorD vectorTo(VectorD vec) {
		return new VectorD(-(x - vec.x), -(y - vec.y));
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
		else if (o instanceof VectorD) {
			VectorD vec = (VectorD) o;
			return (vec.x == this.x) && (vec.y == this.y);
		}
		else if (o instanceof VectorF) {
			VectorF vec = (VectorF) o;
			return (vec.x == this.x) && (vec.y == this.y);
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