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
package net.alexanderdev.lightdrive.media.graphics;

import static net.alexanderdev.lightdrive.util.Time.*;
import static net.alexanderdev.lightdrive.util.math.MathS.*;

/**
 * A class to hanlde image animations
 * 
 * @author Christian Bryce Alexander
 * @since Apr 24, 2015 | 11:50:16 PM
 */
public class Animation {
	public static enum Style {
		ONCE,
		LOOP,
		OSCILLATE,
		RANDOM
	}

	private ImageS[] frames;

	private int frameWidth;
	private int frameHeight;
	private int currFrame;
	private int step;

	private long delay;
	private long timer;

	private boolean playing;

	private Style style;

	/**
	 * Creates an {@code Animation} from an image with one row of frames
	 * 
	 * @param strip
	 *            The image from which to generate the {@code Animation}
	 * @param frameWidth
	 *            The width of all the frames
	 * @param delay
	 *            The frame rate of the {@code Animation}
	 * @param style
	 *            The way the {@code Animation} executes
	 */
	public Animation(ImageS strip, int frameWidth, long delay, Style style) {
		this.frameWidth = frameWidth;
		this.frameHeight = strip.getHeight();
		this.delay = delay;
		this.style = style;

		frames = new ImageS[strip.getWidth() / frameWidth];

		for (int i = 0; i < frames.length; i++) {
			frames[i] = strip.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
		}

		step = 1;

		currFrame = 0;

		playing = false;
	}

	public Animation(ImageS[] frames, long delay, Style style) {
		this.frames = frames;
		this.delay = delay;
		this.style = style;

		frameWidth = frames[0].getWidth();
		frameHeight = frames[0].getHeight();

		step = 1;

		currFrame = 0;

		playing = false;
	}

	public Animation(Animation animation) {
		this(animation.getFrames(), animation.getDelay(), animation.getStyle());
	}

	public ImageS[] getFrames() {
		return frames;
	}

	public long getDelay() {
		return delay;
	}

	public Style getStyle() {
		return style;
	}
	
	/**
	 * @return the currFrame
	 */
	public int getCurrFrame() {
		return currFrame;
	}

	/**
	 * Starts the updating of the animation
	 */
	public void start() {
		if (!playing) {
			timer = msTime();

			playing = true;
		}
	}

	/**
	 * Pauses the updating of the animation
	 */
	public void pause() {
		if (playing)
			playing = false;
	}

	/**
	 * Stops the updating of the animation
	 */
	public void stop() {
		if (playing) {
			playing = false;

			reset();
		}
	}

	public void reset() {
		setToFrame(0);
	}

	public void setToFrame(int i) {
		if (i >= 0 && i < frames.length)
			currFrame = i;
		else
			currFrame = 0;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public void reverse() {
		step = -step;
	}

	public void step() {
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
			case ONCE:
				if (currFrame + step >= frames.length)
					currFrame = frames.length - 1;
				else if (currFrame + step <= -1)
					currFrame = 0;
				else
					currFrame += step;
				break;
			case OSCILLATE:
				if (currFrame + step < 0 || currFrame + step >= frames.length)
					step = -step;
				currFrame += step;
				break;
			case RANDOM:
				currFrame = randomInt(frames.length);
				break;
		}
	}

	public void update() {
		while (playing && msTime() - timer >= delay) {
			step();
			timer += delay;
		}
	}

	/**
	 * @return The current frame of the {@code Animation}
	 */
	public ImageS getFrame() {
		return frames[currFrame];
	}

	/**
	 * @return The width of the {@code Animation} frames
	 */
	public int getFrameWidth() {
		return frameWidth;
	}

	/**
	 * @return The height of the {@code Animation} frames
	 */
	public int getFrameHeight() {
		return frameHeight;
	}

	/**
	 * @return {@code true} if the {@code Animation} is playing, {@code false}
	 *         otherwise
	 */
	public boolean isPlaying() {
		return playing;
	}
}