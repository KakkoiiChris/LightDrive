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
package net.alexanderdev.lightdrive.util.math.geom.path;

/**
 * An enumeration which lists a set of standard easing equations, with their
 * respective algorithms.
 * 
 * @author Christian Bryce Alexander
 * @since May 28, 2016, 4:06:52 AM
 */
public enum EaseAlgorithm {
    /**
     * Follows a linear curve. Change in value is constant.
     */
	LINEAR          ((t, d, s, c) -> c * t / d + s),
	/**
	 * Follows a quadratic curve. Change in value accelerates as time increases.
	 */
	QUADRATIC_IN    ((t, d, s, c) -> {
		t /= d;
		return c * t * t + s;
	}),
	/**
	 * Follows a quadratic curve. Change in value decelerates as time increases.
	 */
	QUADRATIC_OUT   ((t, d, s, c) -> {
		t /= d;
		return -c * t * (t - 2) + s;
	}),
	/**
	 * Follows a quadratic curve. Change in value accelerates half way, and then
	 * decelerates.
	 */
	QUADRATIC_BOTH  ((t, d, s, c) -> {
		t /= d / 2;
		if (t < 1)
			return c / 2 * t * t + s;
		t--;
		return -c / 2 * (t * (t - 2) - 1) + s;
	}),
	/**
	 * Follows a cubic curve. Change in value accelerates as time increases.
	 */
	CUBIC_IN        ((t, d, s, c) -> {
		t /= d;
		return c * t * t * t + s;
	}),
	/**
	 * Follows a cubic curve. Change in value decelerates as time increases.
	 */
	CUBIC_OUT       ((t, d, s, c) -> {
		t /= d;
		t--;
		return c * (t * t * t + 1) + s;
	}),
	/**
	 * Follows a cubic curve. Change in value accelerates half way, and then
	 * decelerates.
	 */
	CUBIC_BOTH      ((t, d, s, c) -> {
		t /= d / 2;
		if (t < 1)
			return c / 2 * t * t * t + s;
		t -= 2;
		return c / 2 * (t * t * t + 2) + s;
	}),
	/**
	 * Follows a quartic curve. Change in value accelerates as time increases.
	 */
	QUARTIC_IN      ((t, d, s, c) -> {
		t /= d;
		return c * t * t * t * t + s;
	}),
	/**
	 * Follows a quartic curve. Change in value decelerates as time increases.
	 */
	QUARTIC_OUT     ((t, d, s, c) -> {
		t /= d;
		t--;
		return -c * (t * t * t * t - 1) + s;
	}),
	/**
	 * Follows a quartic curve. Change in value accelerates half way, and then
	 * decelerates.
	 */
	QUARTIC_BOTH    ((t, d, s, c) -> {
		t /= d / 2;
		if (t < 1)
			return c / 2 * t * t * t * t + s;
		t -= 2;
		return -c / 2 * (t * t * t * t - 2) + s;
	}),
	/**
	 * Follows a quintic curve. Change in value accelerates as time increases.
	 */
	QUINTIC_IN      ((t, d, s, c) -> {
		t /= d;
		return c * t * t * t * t * t + s;
	}),
	/**
	 * Follows a quintic curve. Change in value decelerates as time increases.
	 */
	QUINTIC_OUT     ((t, d, s, c) -> {
		t /= d;
		t--;
		return c * (t * t * t * t * t + 1) + s;
	}),
	/**
	 * Follows a quintic curve. Change in value accelerates half way, and then
	 * decelerates.
	 */
	QUINTIC_BOTH    ((t, d, s, c) -> {
		t /= d / 2;
		if (t < 1)
			return c / 2 * t * t * t * t * t + s;
		t -= 2;
		return c / 2 * (t * t * t * t * t + 2) + s;
	}),
	/**
	 * Follows a sinusoidal curve. Change in value accelerates as time increases.
	 */
	SINUSOIDAL_IN   ((t, d, s, c) -> -c * Math.cos(t / d * (Math.PI / 2)) + c + s),
	/**
	 * Follows a sinusoidal curve. Change in value decelerates as time increases.
	 */
	SINUSOIDAL_OUT  ((t, d, s, c) -> c * Math.sin(t / d * (Math.PI / 2)) + s),
	/**
	 * Follows a sinusoidal curve. Change in value accelerates half way, and then
	 * decelerates.
	 */
	SINUSOIDAL_BOTH ((t, d, s, c) -> -c / 2 * (Math.cos(Math.PI * t / d) - 1) + s),
	/**
	 * Follows an exponential curve. Change in value accelerates as time increases.
	 */
	EXPONENTIAL_IN  ((t, d, s, c) -> c * Math.pow(2, 10 * (t / d - 1)) + s),
	/**
	 * Follows an exponential curve. Change in value decelerates as time increases.
	 */
	EXPONENTIAL_OUT ((t, d, s, c) -> c * (-Math.pow(2, -10 * t / d) + 1) + s),
	/**
	 * Follows an exponential curve. Change in value accelerates half way, and then
	 * decelerates.
	 */
	EXPONENTIAL_BOTH((t, d, s, c) -> {
		t /= d / 2;
		if (t < 1)
			return c / 2 * Math.pow(2, 10 * (t - 1)) + s;
		t--;
		return c / 2 * (-Math.pow(2, -10 * t) + 2) + s;
	}),
	/**
	 * Follows a circular curve. Change in value accelerates as time increases.
	 */
	CIRCULAR_IN     ((t, d, s, c) -> {
		t /= d;
		return -c * (Math.sqrt(1 - t * t) - 1) + s;
	}),
	/**
	 * Follows a circular curve. Change in value decelerates as time increases.
	 */
	CIRCULAR_OUT    ((t, d, s, c) -> {
		t /= d;
		t--;
		return c * Math.sqrt(1 - t * t) + s;
	}),
	/**
	 * Follows a circular curve. Change in value accelerates half way, and then
	 * decelerates.
	 */
	CIRCULAR_BOTH   ((t, d, s, c) -> {
		t /= d / 2;
		if (t < 1)
			return -c / 2 * (Math.sqrt(1 - t * t) - 1) + s;
		t -= 2;
		return c / 2 * (Math.sqrt(1 - t * t) + 1) + s;
	});

	private EaseOp operation;

	private EaseAlgorithm(EaseOp operation) {
		this.operation = operation;
	}

	/**
	 * @return The {@link EaseOp} that implements this algorithm
	 */
	public EaseOp getOperation() {
		return operation;
	}
}