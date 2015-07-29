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
package net.alexanderdev.lightdrive.media.audio;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * @author Christian Bryce Alexander
 * @since May 25, 2015 | 8:06:38 PM
 */
public class Sound {
	protected Clip clip;

	private FloatControl volume;

	private float currDB = 1f;
	private float targetDB = 0f;
	private float fadePerStep = 0.1f;

	private boolean fading = false;

	private Sound intro;

	private Thread introThread = null;

	public Sound(Clip clip) {
		this.clip = clip;
		
		volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	}

	/**
	 * Starts this {@code Sound} if it is not currently playing, and lets it
	 * play once
	 */
	public void play() {
		if (clip == null) {
			return;
		}

		stop();

		clip.setFramePosition(0);
		clip.start();
	}

	/**
	 * Starts this {@code Sound} if it is not currently playing, and lets it
	 * loop continuously
	 */
	public void loop() {
		if (clip == null) {
			return;
		}

		stop();

		clip.setFramePosition(0);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void loopWithIntro(Sound introSound) {
		intro = introSound;

		introThread = new Thread(() -> {
			intro.play();

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			while (intro.isPlaying()) {
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			this.loop();

			introThread = null;
		} , "ld_song_with_intro");

		introThread.start();
	}

	public void pause() {
		clip.stop();
	}

	/**
	 * Stops this {@code Sound} if it is currently playing
	 */
	public void stop() {
		if (introThread != null) {
			intro.stop();

			try {
				introThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if (clip.isRunning()) {
			clip.stop();
		}
	}

	/**
	 * Releases the resources held by this {@code Sound}
	 */
	public void close() {
		stop();

		clip.close();
	}

	/**
	 * @return {@code true} if this {@code Sound} is playing, {@code false}
	 *         otherwise
	 */
	public boolean isPlaying() {
		if (clip == null)
			return false;
		return clip.isRunning();
	}

	/**
	 * @return The current master gain of this {@code Sound}
	 */
	public float getVolume() {
		return currDB;
	}

	/**
	 * Sets the volume of this {@code Sound} to the value, which is a
	 * {@code double} between 0.0 and 1.0
	 * 
	 * @param value
	 *            The volume to set
	 */
	public void setVolume(double value) {
		try {
			float dB = (float) (Math.log(value) / Math.log(10.0) * 20.0);

			dB = Math.max(dB, -80f);

			volume.setValue(dB);
		} catch (ArithmeticException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Smoothly shifts to the target volume at a specified rate
	 *
	 * @param value
	 *            The target volume after the shift
	 * @param delay
	 *            The rate at which to shif the volume
	 */
	public void shiftVolumeTo(double value, int delay) {
		targetDB = (float) (Math.log(value) / Math.log(10.0) * 20.0);

		targetDB = Math.max(targetDB, -80f);

		if (!fading) {
			new Thread(new VolumeShifter(delay)).start();
		}
	}

	private class VolumeShifter implements Runnable {
		private int delay = 10;

		public VolumeShifter(int delay) {
			this.delay = delay;
		}

		@Override
		public void run() {
			fading = true;

			if (currDB > targetDB) {
				while (currDB > targetDB) {
					volume.setValue(currDB);
					currDB -= fadePerStep;

					try {
						Thread.sleep(delay);
					} catch (InterruptedException e) {
					}
				}
			} else if (currDB < targetDB) {
				while (currDB < targetDB) {
					volume.setValue(currDB);
					currDB += fadePerStep;

					try {
						Thread.sleep(delay);
					} catch (InterruptedException e) {
					}
				}
			}

			fading = false;
			currDB = targetDB;
		}
	}
}