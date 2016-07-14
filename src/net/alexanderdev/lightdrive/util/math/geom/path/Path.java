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

import java.awt.geom.Point2D;

import net.alexanderdev.lightdrive.util.math.geom.VectorD;
import net.alexanderdev.lightdrive.util.time.Time;

/**
 * @author Christian Bryce Alexander
 * @since May 31, 2016, 12:12:13 AM
 */
public class Path {
	private EaseAlgorithm ex, ey;

	private Point2D start, end;

	private long duration, timer;

	public Path(EaseAlgorithm ex, EaseAlgorithm ey, Point2D start, Point2D end, long duration) {
		this.ex = ex;
		this.ey = ey;

		this.start = start;
		this.end = end;

		this.duration = duration;

		timer = Time.msTime();
	}

	public void reset() {
		timer = Time.msTime();
	}

	public EaseAlgorithm getEaseX() {
		return ex;
	}

	public void setEaseX(EaseAlgorithm ex) {
		this.ex = ex;
	}

	public EaseAlgorithm getEaseY() {
		return ey;
	}

	public void setEaseY(EaseAlgorithm ey) {
		this.ey = ey;
	}

	public Point2D getStart() {
		return start;
	}

	public void setStart(Point2D start) {
		this.start = start;
	}

	public Point2D getEnd() {
		return end;
	}

	public void setEnd(Point2D end) {
		this.end = end;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public long getMSElapsed() {
		return Math.min(Time.msTime() - timer, duration);
	}

	public VectorD getPoint() {
		VectorD vec = new VectorD();

		long elapsed = getMSElapsed();

		vec.x = ex.getOperation().ease(elapsed, duration, start.getX(), end.getX() - start.getX());
		vec.y = ey.getOperation().ease(elapsed, duration, start.getY(), end.getY() - start.getY());

		return vec;
	}
}