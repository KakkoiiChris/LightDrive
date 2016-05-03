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

import static javax.sound.sampled.AudioFormat.Encoding.*;
import static javax.sound.sampled.AudioSystem.*;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine.Info;

import net.alexanderdev.lightdrive.audio.Sound;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @author Christian Bryce Alexander
 * @since May 25, 2015 | 9:42:21 PM
 */
public final class SoundIO {
	private static String path = "";

	/**
	 * When loading multiple fonts from a certain folder, you can set a relative
	 * path to that folder, and just use the fonts name when loading them
	 * 
	 * @param path
	 *            The relative path to load sounds from
	 */
	public static void setRelativePath(String path) {
		SoundIO.path = path;
	}

	public static Sound loadMP3(String filename) {
		Clip clip = null;

		try {
			AudioInputStream ais = getAudioInputStream(SoundIO.class.getResourceAsStream(path + filename + ".mp3"));

			AudioFormat baseFormat = ais.getFormat();

			Encoding encoding = PCM_SIGNED;

			float sampleRate = baseFormat.getSampleRate();

			int channels = baseFormat.getChannels();

			AudioFormat decoded = new AudioFormat(encoding, sampleRate, 16, channels, channels * 2, sampleRate, false);

			AudioInputStream dais = getAudioInputStream(decoded, ais);

			clip = getClip();

			clip.open(dais);
		}
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}

		return new Sound(clip);
	}

	public static Sound loadWAV(String filename) {
		Clip clip = null;

		try {
			AudioInputStream ais = getAudioInputStream(SoundIO.class.getResourceAsStream(path + filename + ".wav"));

			AudioFormat format = ais.getFormat();

			Info info = new Info(Clip.class, format);

			clip = (Clip) getLine(info);

			clip.open(ais);
		}
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}

		return new Sound(clip);
	}
}