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
package net.alexanderdev.lightdrive.audio;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * A class which encapsulates a {@link Clip}, and allows for simple playback and
 * volume/pan control of an audio file.
 * 
 * @author Christian Bryce Alexander
 * @since May 25, 2015 | 8:06:38 PM
 */
public class Sound {
	private Clip clip;

	private FloatControl masterGain;
	private FloatControl pan;

	private SoundListener listener;
	private boolean isListening;

	/**
	 * Creates a {@link Sound} with the provided {@link Clip}.
	 * 
	 * @param clip
	 *            The {@link Sound} to associate with this {@link Sound}
	 */
	public Sound(Clip clip) {
		this.clip = clip;

		masterGain = pan = null;

		if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN))
			masterGain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		if (clip.isControlSupported(FloatControl.Type.PAN))
			pan = (FloatControl) clip.getControl(FloatControl.Type.PAN);

		listener = null;
		isListening = false;
	}

	/**
	 * Plays this {@link Sound} from beginning to end once, and runs the
	 * {@link SoundListener}'s {@link Thread} if it has been set.
	 */
	public void play() {
		if (clip == null || isPlaying())
			return;

		stop();

		clip.setFramePosition(0);

		if (listener != null)
			startListening();

		clip.start();
	}

	/**
	 * Loops this {@link Sound} from beginning to end continuously, and runs the
	 * {@link SoundListener}'s {@link Thread} if it has been set.
	 */
	public void loop() {
		if (clip == null || isPlaying())
			return;

		stop();

		clip.setFramePosition(0);

		if (listener != null)
			startListening();

		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	/**
	 * Stops this {@link Sound} from playing, regardless of whether or not it
	 * was played or looped, and stops the {@link SoundListener}'s
	 * {@link Thread}.
	 */
	public void stop() {
		if (clip == null || !isPlaying())
			return;

		if (listener != null)
			stopListening();

		if (clip.isRunning())
			clip.stop();
	}

	/**
	 * @return {@code true} if the {@link Clip} is currently running,
	 *         {@code false} otherwise
	 */
	public boolean isPlaying() {
		return clip.isRunning();
	}

	/**
	 * Sets the volume, or master gain, for this {@link Sound}. The minimum
	 * value is -80f, and the maximum is 6.0206f.
	 * 
	 * @param volume
	 *            The volume to set
	 */
	public void setVolume(float volume) {
		if (masterGain != null)
			masterGain.setValue(volume);
	}

	/**
	 * Sets the perceived horizontal position of this {@link Sound}. The minimum
	 * value is -1f (left only), and the maximum is 1f (right only).
	 * 
	 * @param position
	 *            The pan position to set
	 */
	public void setPan(float position) {
		if (pan != null)
			pan.setValue(position);
	}

	/**
	 * Sets the {@link SoundListener} for this {@link Sound}.
	 * 
	 * @param listener
	 *            The listener to set
	 */
	public void setListener(SoundListener listener) {
		this.listener = listener;
	}

	private long getPosition() {
		return clip.getMicrosecondPosition();
	}

	private long getLength() {
		return clip.getMicrosecondLength();
	}

	private void startListening() {
		if (!isListening) {
			isListening = true;

			new Thread(() -> {
				while (isListening && isPlaying())
					listener.listen((float) ((double) getPosition() / (double) getLength()));

				isListening = false;
			}).start();
		}
	}

	private void stopListening() {
		isListening = false;
	}

	/**
	 * Stops this {@link Sound} and releases all resources associated with it.
	 */
	public void close() {
		stop();

		clip.drain();
		clip.close();
	}
}