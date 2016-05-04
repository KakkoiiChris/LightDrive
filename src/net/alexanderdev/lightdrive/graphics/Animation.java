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
 * A class to hanlde image animations
 * 
 * @author Christian Bryce Alexander
 * @since Apr 24, 2015 | 11:50:16 PM
 */
public class Animation implements Cloneable {
	/**
	 * There are four styles of animation, all of which alter the appearance of
	 * the animation.
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

	private int currentFrame, step;

	private long delay, timer;

	private boolean playing;

	/**
	 * Creates a standard {@code Animation}.
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

		currentFrame = 0;

		playing = false;
	}

	/**
	 * Creates an {@code Animation} with the default style, {@link Style#LOOP}.
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
	 * Creates an {@code Animation} with the default step of {@code 1}.
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
	 * Creates an {@code Animation} with the default step and style.
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
		switch (style) {
			case LOOP:
			default:
				if (currentFrame + step >= frames.length)
					currentFrame = 0;
				else if (currentFrame + step <= -1)
					currentFrame = frames.length - 1;
				else
					currentFrame += step;
				break;
			case BOUNCE:
				if (currentFrame + step < 0 || currentFrame + step >= frames.length)
					step = -step;
				currentFrame += step;
				break;
			case ONCE:
				if (currentFrame + step >= frames.length)
					currentFrame = frames.length - 1;
				else if (currentFrame + step <= -1)
					currentFrame = 0;
				else
					currentFrame += step;
				break;
			case RANDOM:
				currentFrame = MathX.randomInt(frames.length);
				break;
		}
	}

	/**
	 * @return The current animation frame
	 */
	public Sprite getFrame() {
		return frames[currentFrame];
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
		return currentFrame;
	}

	/**
	 * Sets the index of this {@code Animation}.
	 * 
	 * @param The
	 *            index to set
	 */
	public void setFrameIndex(int i) {
		if (i >= 0 && i < frames.length)
			currentFrame = i;
		else
			currentFrame = 0;
	}

	/**
	 * @return The delay of this {@code Animation}
	 */
	public long getDelay() {
		return delay;
	}

	/**
	 * Sets the delay of this {@code Animation}.
	 *
	 * @param delay
	 *            The delay to set
	 */
	public void setDelay(long delay) {
		this.delay = delay;
	}

	/**
	 * @return The step amount of this {@code Animation}
	 */
	public int getStep() {
		return step;
	}

	/**
	 * Sets the step amount for this {@code Animation}.
	 *
	 * @param step
	 *            The step amount to set
	 */
	public void setStep(int step) {
		this.step = step;
	}

	/**
	 * @return The length of this animation
	 */
	public int getLength() {
		return frames.length;
	}

	/**
	 * Sets this animation to start running, and updates the timer.
	 */
	public void play() {
		if (!playing) {
			timer = Time.msTime();

			playing = true;
		}
	}

	/**
	 * Sets the animation to stop running.
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
	 * Sets the animation to stop running, and sets the current frame to the
	 * beginning.
	 */
	public void stop() {
		if (playing) {
			playing = false;

			currentFrame = 0;
		}
	}

	/**
	 * Sets the current frame to the beginning.
	 */
	public void reset() {
		currentFrame = 0;
	}

	/**
	 * Reverses the direction of this {@code Animation}, if applicable.
	 */
	public void reverse() {
		step = -step;
	}

	public boolean isPlaying() {
		return playing;
	}

	@Override
	public Animation clone() {
		Sprite[] newFrames = new Sprite[frames.length];

		for (int i = 0; i < frames.length; i++)
			newFrames[i] = frames[i].clone();

		return new Animation(newFrames, delay, step, style);
	}
}