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
package net.alexanderdev.lightdrive.graphics;

import net.alexanderdev.lightdrive.util.Time;
import net.alexanderdev.lightdrive.util.math.MathX;

/**
 * A class which represents a single {@link Sprite} based animation.
 * 
 * @author Christian Bryce Alexander
 * @since Apr 24, 2015, 11:50:16 PM
 */
public class Animation implements Cloneable {
	/**
	 * Lists four styles of animation, all of which alter the order of
	 * appearance of the frames in the {@code Animation}.
	 */
	public static enum Style {
	    /**
	     * Moves to one end of the frames, and then loops to the opposite end.
	     */
		LOOP,
		/**
		 * Moves to one end of the frames, and then reverses direction.
		 */
		BOUNCE,
		/**
		 * Moves to one end of the frames, and stays there.
		 */
		ONCE,
		/**
		 * Moves to a random frame every time.
		 */
		RANDOM;
	}

	private Sprite[] frames;

	private Style style;

	private int currFrame, prevFrame, step;

	private long delay, timer;

	private boolean playing;

	/**
	 * Creates a standard {@link Animation}.
	 * 
	 * @param frames
	 *            The animation frames
	 * @param delay
	 *            The amount of milliseconds betwen frames
	 * @param style
	 *            The style of the animation
	 */
	public Animation(Sprite[] frames, long delay, int step, Style style) {
		this.frames = frames;
		this.delay = delay;
		this.step = step;
		this.style = style;

		currFrame = 0;
		prevFrame = -1;

		playing = false;
	}

	/**
	 * Creates an {@link Animation} with the default style, {@link Style#LOOP}.
	 * 
	 * @param frames
	 *            The animation frames
	 * @param delay
	 *            The amount of milliseconds betwen frames
	 */
	public Animation(Sprite[] frames, long delay, int step) {
		this(frames, delay, step, Style.LOOP);
	}

	/**
	 * Creates an {@link Animation} with the default step of {@code 1}.
	 * 
	 * @param frames
	 *            The animation frames
	 * @param delay
	 *            The amount of milliseconds betwen frames
	 */
	public Animation(Sprite[] frames, long delay, Style style) {
		this(frames, delay, 1, style);
	}

	/**
	 * Creates an {@link Animation} with the default step and style.
	 * 
	 * @param frames
	 *            The animation frames
	 * @param delay
	 *            The amount of milliseconds betwen frames
	 */
	public Animation(Sprite[] frames, long delay) {
		this(frames, delay, 1, Style.LOOP);
	}

	/**
	 * Steps the animation if the amount of milliseconds since last update has
	 * exceeded the specified delay.
	 */
	public void update() {
		while (playing && Time.msTime() - timer >= delay) {
			step();
			timer += delay;
		}
	}

	/**
	 * Sets the next frame based on the specified animation {@link Style}.
	 */
	public void step() {
		prevFrame = currFrame;

		switch (style) {
			case LOOP:
			default:
				if (currFrame + step >= frames.length)
					currFrame = 0;
				else if (currFrame + step <= -1)
					currFrame = frames.length - 1;
				else
					currFrame += step;
				break;
			case BOUNCE:
				if (currFrame + step < 0 || currFrame + step >= frames.length)
					step = -step;
				currFrame += step;
				break;
			case ONCE:
				if (currFrame + step >= frames.length)
					currFrame = frames.length - 1;
				else if (currFrame + step <= -1)
					currFrame = 0;
				else
					currFrame += step;
				break;
			case RANDOM:
				currFrame = MathX.randomInt(frames.length);
				break;
		}
	}

	/**
	 * @return The current animation frame
	 */
	public Sprite getFrame() {
		return frames[currFrame];
	}

	/**
	 * @return All of the animation frames
	 */
	public Sprite[] getFrames() {
		return frames;
	}

	/**
	 * @return The index of the current animation frame
	 */
	public int getFrameIndex() {
		return currFrame;
	}

	/**
	 * Sets the index of this {@link Animation}.
	 * 
	 * @param The
	 *            index to set
	 */
	public void setFrameIndex(int i) {
		if (i >= 0 && i < frames.length)
			currFrame = i;
		else
			currFrame = 0;
	}

	/**
	 * @return The delay of this {@link Animation}
	 */
	public long getDelay() {
		return delay;
	}

	/**
	 * Sets the delay of this {@link Animation}.
	 *
	 * @param delay
	 *            The delay to set
	 */
	public void setDelay(long delay) {
		this.delay = delay;
	}

	/**
	 * @return The step amount of this {@link Animation}
	 */
	public int getStep() {
		return step;
	}

	/**
	 * Sets the step amount for this {@link Animation}.
	 *
	 * @param step
	 *            The step amount to set
	 */
	public void setStep(int step) {
		this.step = step;
	}

	/**
	 * @return The length of this {@link Animation}
	 */
	public int getLength() {
		return frames.length;
	}

	/**
	 * Sets this {@link Animation} to start running, and updates the timer.
	 */
	public void play() {
		if (!playing) {
			timer = Time.msTime();

			playing = true;
		}
	}

	/**
	 * Sets the {@link Animation} to stop running.
	 */
	public void pause() {
		if (playing)
			playing = false;
	}

	public void toggle() {
		if (playing)
			pause();
		else
			play();
	}

	/**
	 * Sets the {@link Animation} to stop running, and sets the current frame to
	 * the beginning.
	 */
	public void stop() {
		if (playing) {
			playing = false;

			currFrame = 0;
			prevFrame = -1;
		}
	}

	/**
	 * Sets the current frame to the beginning.
	 */
	public void reset() {
		currFrame = 0;
		prevFrame = -1;
	}

	/**
	 * Reverses the direction of this {@link Animation}, if applicable.
	 */
	public void reverse() {
		step = -step;
	}

	/**
	 * @return {@code true} if it is playing, {@code false} otherwise
	 */
	public boolean isPlaying() {
		return playing;
	}

	/**
	 * @return {@code true} if this {@link Animation} is of style
	 *         {@link Style#ONCE}, and it is on the first or last frame,
	 *         {@code false} otherwise
	 */
	public boolean isDone() {
		return style.equals(Style.ONCE) && prevFrame == currFrame;
	}

	@Override
	public Animation clone() {
		Sprite[] newFrames = new Sprite[frames.length];

		for (int i = 0; i < frames.length; i++)
			newFrames[i] = frames[i].clone();

		return new Animation(newFrames, delay, step, style);
	}
}