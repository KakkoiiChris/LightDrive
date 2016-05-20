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
package net.alexanderdev.lightdrive.util.io;

import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;
import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static javax.sound.sampled.AudioSystem.getClip;
import static javax.sound.sampled.AudioSystem.getLine;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine.Info;

import net.alexanderdev.lightdrive.audio.Sound;

/**
 * A class for reading audio files from source folder.
 * 
 * @author Christian Bryce Alexander
 * @since May 25, 2015 | 9:42:21 PM
 */
public final class SoundIO {
	private static String path = "";

	/**
	 * Sets the path that will be prepended to the filename when loading an
	 * image from a file.
	 * 
	 * @param path
	 *            The path to be prepended
	 */
	public static void setPath(String path) {
		SoundIO.path = path;
	}

	/**
	 * Loads an MP3 file from a source folder. Doing so requires the inclusion
	 * of three additional libraries:
	 * <ul>
	 * <li>JLayer - <i>jl1.0.1.jar</i></li>
	 * <li>MP3 SPI - <i>mp3spi1.9.5.jar</i></li>
	 * <li>Tritonus - <i>tritonus_share.jar</i></li>
	 * </ul>
	 * All three libraries are transparent in functionality, and therefore do
	 * not require invocation by the user.
	 * 
	 * @param name
	 *            The name of the file to load
	 * @return The contents of the file loaded into a {@link Sound}
	 */
	public static Sound loadMP3(String name) {
		Clip clip = null;

		try {
			AudioInputStream ais = getAudioInputStream(SoundIO.class.getResourceAsStream(path + name + ".mp3"));

			AudioFormat baseFormat = ais.getFormat();

			Encoding encoding = PCM_SIGNED;

			float sampleRate = baseFormat.getSampleRate();

			int channels = baseFormat.getChannels();

			AudioFormat decoded = new AudioFormat(encoding, sampleRate, 16, channels, channels * 2, sampleRate, false);

			AudioInputStream dais = getAudioInputStream(decoded, ais);

			clip = getClip();

			clip.open(dais);
		}
		catch (Exception e) {
			try {
				AudioInputStream ais = getAudioInputStream(SoundIO.class.getResourceAsStream(name + ".mp3"));

				AudioFormat baseFormat = ais.getFormat();

				Encoding encoding = PCM_SIGNED;

				float sampleRate = baseFormat.getSampleRate();

				int channels = baseFormat.getChannels();

				AudioFormat decoded = new AudioFormat(encoding, sampleRate, 16, channels, channels * 2, sampleRate,
				    false);

				AudioInputStream dais = getAudioInputStream(decoded, ais);

				clip = getClip();

				clip.open(dais);
			}
			catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return new Sound(clip);
	}

	/**
	 * Loads an WAV file from a source folder.
	 * 
	 * @param name
	 *            The name of the file to load
	 * @return The contents of the file loaded into a {@link Sound}
	 */
	public static Sound loadWAV(String name) {
		Clip clip = null;

		try {
			AudioInputStream ais = getAudioInputStream(SoundIO.class.getResourceAsStream(path + name + ".wav"));

			AudioFormat format = ais.getFormat();

			Info info = new Info(Clip.class, format);

			clip = (Clip) getLine(info);

			clip.open(ais);
		}
		catch (Exception e) {
			try {
				AudioInputStream ais = getAudioInputStream(SoundIO.class.getResourceAsStream(name + ".wav"));

				AudioFormat format = ais.getFormat();

				Info info = new Info(Clip.class, format);

				clip = (Clip) getLine(info);

				clip.open(ais);
			}
			catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return new Sound(clip);
	}
}