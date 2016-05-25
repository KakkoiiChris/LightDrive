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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import net.alexanderdev.lightdrive.audio.Sound;
import net.alexanderdev.lightdrive.graphics.Sprite;
import net.alexanderdev.lightdrive.util.Time;

/**
 * A class which provides a visualization of base resource loading progress as a
 * splash screen. Base resources include {@link Sprite}s, {@link Font} names,
 * and {@link Sound}s. All other resources are based off of these three.
 * 
 * @author Christian Bryce Alexander
 * @since May 24, 2016, 2:24:32 PM
 */
public class ResourceLoader extends JFrame {
	private static final long serialVersionUID = 8493474044583968037L;

	private static final List<String> BMP_PATHS = new ArrayList<>();
	private static final List<String> GIF_PATHS = new ArrayList<>();
	private static final List<String> JPEG_PATHS = new ArrayList<>();
	private static final List<String> PNG_PATHS = new ArrayList<>();
	private static final List<String> TIFF_PATHS = new ArrayList<>();
	private static final Map<String, Sprite> SPRITES = new HashMap<>();

	private static final List<String> TTF_PATHS = new ArrayList<>();
	private static final List<String> OTF_PATHS = new ArrayList<>();
	private static final List<String> TYPE1_PATHS = new ArrayList<>();
	private static final Map<String, Font> FONTS = new HashMap<>();

	private static final List<String> MP3_PATHS = new ArrayList<>();
	private static final List<String> WAV_PATHS = new ArrayList<>();
	private static final Map<String, Sound> SOUNDS = new HashMap<>();

	private static int resourceCount = 0;

	private static JLabel splash;
	private static JProgressBar progress;

	private ResourceLoader() {
		super("LightDrive Resource Loader");

		setLayout(new BorderLayout());

		splash = new JLabel();
		setSplashImage(SpriteIO.loadPNG("/img/lightdrive/splash640x480"));

		add(splash, BorderLayout.CENTER);

		progress = new JProgressBar();
		progress.setStringPainted(true);

		add(progress, BorderLayout.SOUTH);

		setResizable(false);
		setUndecorated(true);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Sets the {@link Image} for the {@link ImageIcon} that appears on the
	 * splash screen, and resizes the splash screen appropriately.
	 * 
	 * @param image
	 *            the splash image to set
	 */
	public static void setSplashImage(Image image) {
		splash.setIcon(new ImageIcon(SpriteIO.loadPNG("/img/lightdrive/splash640x480")));

		Dimension d = new Dimension(image.getWidth(null), image.getHeight(null));

		splash.setMinimumSize(d);
		splash.setPreferredSize(d);
		splash.setMaximumSize(d);
	}

	/**
	 * Sets the root of the relative path to the images folder to load from.
	 * 
	 * @param path
	 *            The root path to set
	 */
	public static void setSpriteRoot(String path) {
		SpriteIO.setPath(path);
	}

	/**
	 * Sets the root of the relative path to the fonts folder to load from.
	 * 
	 * @param path
	 *            The root path to set
	 */
	public static void setFontRoot(String path) {
		FontIO.setPath(path);
	}

	/**
	 * Sets the root of the relative path to the sounds folder to load from.
	 * 
	 * @param path
	 *            The root path to set
	 */
	public static void setSoundRoot(String path) {
		SoundIO.setPath(path);
	}

	/**
	 * Registers a varargs list of file names of BMP images that are to be
	 * loaded when {@link ResourceLoader#load(boolean)} is invoked.
	 * 
	 * @param names
	 *            The names of the files to register
	 */
	public static void registerBMPs(String... names) {
		for (String name : names)
			BMP_PATHS.add(name);

		resourceCount += names.length;
	}

	/**
	 * Registers a varargs list of file names of GIF images that are to be
	 * loaded when {@link ResourceLoader#load(boolean)} is invoked.
	 * 
	 * @param names
	 *            The names of the files to register
	 */
	public static void registerGIFs(String... names) {
		for (String name : names)
			GIF_PATHS.add(name);

		resourceCount += names.length;
	}

	/**
	 * Registers a varargs list of file names of JPEG images that are to be
	 * loaded when {@link ResourceLoader#load(boolean)} is invoked.
	 * 
	 * @param names
	 *            The names of the files to register
	 */
	public static void registerJPEGs(String... names) {
		for (String name : names)
			JPEG_PATHS.add(name);

		resourceCount += names.length;
	}

	/**
	 * Registers a varargs list of file names of PNG images that are to be
	 * loaded when {@link ResourceLoader#load(boolean)} is invoked.
	 * 
	 * @param names
	 *            The names of the files to register
	 */
	public static void registerPNGs(String... names) {
		for (String name : names)
			PNG_PATHS.add(name);

		resourceCount += names.length;
	}

	/**
	 * Registers a varargs list of file names of TIFF images that are to be
	 * loaded when {@link ResourceLoader#load(boolean)} is invoked.
	 * 
	 * @param names
	 *            The names of the files to register
	 */
	public static void registerTIFFs(String... names) {
		for (String name : names)
			TIFF_PATHS.add(name);

		resourceCount += names.length;
	}

	/**
	 * Registers a varargs list of file names of TTF fonts that are to be loaded
	 * when {@link ResourceLoader#load(boolean)} is invoked.
	 * 
	 * @param names
	 *            The names of the files to register
	 */
	public static void registerTTFs(String... names) {
		for (String name : names)
			TTF_PATHS.add(name);

		resourceCount += names.length;
	}

	/**
	 * Registers a varargs list of file names of OTF fonts that are to be loaded
	 * when {@link ResourceLoader#load(boolean)} is invoked.
	 * 
	 * @param names
	 *            The names of the files to register
	 */
	public static void registerOTFs(String... names) {
		for (String name : names)
			OTF_PATHS.add(name);

		resourceCount += names.length;
	}

	/**
	 * Registers a varargs list of file names of Type 1 fonts that are to be
	 * loaded when {@link ResourceLoader#load(boolean)} is invoked.
	 * 
	 * @param names
	 *            The names of the files to register
	 */
	public static void registerTYPE1s(String... names) {
		for (String name : names)
			TYPE1_PATHS.add(name);

		resourceCount += names.length;
	}

	/**
	 * Registers a varargs list of file names of MP3 sounds that are to be
	 * loaded when {@link ResourceLoader#load(boolean)} is invoked.
	 * 
	 * @param names
	 *            The names of the files to register
	 */
	public static void registerMP3s(String... names) {
		for (String name : names)
			MP3_PATHS.add(name);

		resourceCount += names.length;
	}

	/**
	 * Registers a varargs list of file names of WAV sounds that are to be
	 * loaded when {@link ResourceLoader#load(boolean)} is invoked.
	 * 
	 * @param names
	 *            The names of the files to register
	 */
	public static void registerWAVs(String... names) {
		for (String name : names)
			WAV_PATHS.add(name);

		resourceCount += names.length;
	}

	/**
	 * Retrieves a {@link Sprite} by name if it has been registered and loaded.
	 *
	 * @param name
	 *            The name of the {@link Sprite} to retrieve
	 * @return The named {@link Sprite}
	 */
	public static Sprite getSprite(String name) {
		return SPRITES.get(name);
	}

	/**
	 * Retrieves a {@link Font} by name if it has been registered and loaded.
	 *
	 * @param name
	 *            The name of the {@link Font} to retrieve
	 * @return The named {@link Font}
	 */
	public static Font getFont(String name) {
		return FONTS.get(name);
	}

	/**
	 * Retrieves a {@link Sound} by name if it has been registered and loaded.
	 *
	 * @param name
	 *            The name of the {@link Sound} to retrieve
	 * @return The named {@link Sound}
	 */
	public static Sound getSound(String name) {
		return SOUNDS.get(name);
	}

	/**
	 * Loads all registered resources into memory, and displays splash screen if
	 * specified.
	 *
	 * @param visible
	 *            Whether or not the splash screen loading visualization should
	 *            be displayed when loading the resources
	 */
	public static void load(boolean visible) {
		ResourceLoader loader = new ResourceLoader();

		loader.setVisible(visible);

		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		long start = Time.nsTime();

		int count = 0;

		for (String path : BMP_PATHS) {
			progress.setString("Loading Sprite '" + path + "'");

			String[] dir = path.split("(/|\\\\)");
			SPRITES.put(dir[dir.length - 1], SpriteIO.loadBMP(path));

			count++;
			progress.setValue((int) ((count / (double) resourceCount) * 100));
		}

		for (String path : GIF_PATHS) {
			progress.setString("Loading Sprite '" + path + "'");

			String[] dir = path.split("(/|\\\\)");
			SPRITES.put(dir[dir.length - 1], SpriteIO.loadGIF(path));

			count++;
			progress.setValue((int) ((count / (double) resourceCount) * 100));
		}

		for (String path : JPEG_PATHS) {
			progress.setString("Loading Sprite '" + path + "'");

			String[] dir = path.split("(/|\\\\)");
			SPRITES.put(dir[dir.length - 1], SpriteIO.loadJPEG(path));

			count++;
			progress.setValue((int) ((count / (double) resourceCount) * 100));
		}

		for (String path : PNG_PATHS) {
			progress.setString("Loading Sprite '" + path + "'");

			String[] dir = path.split("(/|\\\\)");
			SPRITES.put(dir[dir.length - 1], SpriteIO.loadPNG(path));

			count++;
			progress.setValue((int) ((count / (double) resourceCount) * 100));
		}

		for (String path : TIFF_PATHS) {
			progress.setString("Loading Sprite '" + path + "'");

			String[] dir = path.split("(/|\\\\)");
			SPRITES.put(dir[dir.length - 1], SpriteIO.loadTIFF(path));

			count++;
			progress.setValue((int) ((count / (double) resourceCount) * 100));
		}

		for (String path : TTF_PATHS) {
			progress.setString("Loading Font '" + path + "'");

			String[] dir = path.split("(/|\\\\)");
			FONTS.put(dir[dir.length - 1], FontIO.loadTTF(path));

			count++;
			progress.setValue((int) ((count / (double) resourceCount) * 100));
		}

		for (String path : OTF_PATHS) {
			progress.setString("Loading Font '" + path + "'");

			String[] dir = path.split("(/|\\\\)");
			FONTS.put(dir[dir.length - 1], FontIO.loadOTF(path));

			count++;
			progress.setValue((int) ((count / (double) resourceCount) * 100));
		}

		for (String path : TYPE1_PATHS) {
			progress.setString("Loading Font '" + path + "'");

			String[] dir = path.split("(/|\\\\)");
			FONTS.put(dir[dir.length - 1], FontIO.loadType1(path));

			count++;
			progress.setValue((int) ((count / (double) resourceCount) * 100));
		}

		for (String path : MP3_PATHS) {
			progress.setString("Loading Sound '" + path + "'");

			String[] dir = path.split("(/|\\\\)");
			SOUNDS.put(dir[dir.length - 1], SoundIO.loadMP3(path));

			count++;
			progress.setValue((int) ((count / (double) resourceCount) * 100));
		}

		for (String path : WAV_PATHS) {
			progress.setString("Loading Sound '" + path + "'");

			String[] dir = path.split("(/|\\\\)");
			SOUNDS.put(dir[dir.length - 1], SoundIO.loadWAV(path));

			count++;
			progress.setValue((int) ((count / (double) resourceCount) * 100));
		}

		long end = Time.nsTime();

		progress.setString(String.format("Resources loaded: %fs\n", (end - start) / 10e9d));

		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		loader.dispose();
	}

	/**
	 * Clears all loaded {@link Sprite}s and registered {@link Sprite} names.
	 */
	public static void deregisterSprites() {
		resourceCount -= SPRITES.size();

		BMP_PATHS.clear();
		GIF_PATHS.clear();
		JPEG_PATHS.clear();
		PNG_PATHS.clear();
		TIFF_PATHS.clear();
		SPRITES.clear();
	}

	/**
	 * Clears all loaded {@link Font}s and registered {@link Font} names.
	 */
	public static void deregisterFonts() {
		resourceCount -= FONTS.size();

		TTF_PATHS.clear();
		OTF_PATHS.clear();
		TYPE1_PATHS.clear();
		FONTS.clear();
	}

	/**
	 * Clears all loaded {@link Sound}s and registered {@link Sound} names.
	 */
	public static void deregisterSounds() {
		resourceCount -= SOUNDS.size();

		MP3_PATHS.clear();
		WAV_PATHS.clear();
		SOUNDS.clear();
	}

	/**
	 * Clears all loaded resources and registered resource names.
	 */
	public static void deregisterAll() {
		deregisterSprites();
		deregisterFonts();
		deregisterSounds();
	}

	/**
	 * @return {@code true} if no resources have been registered or loaded,
	 *         {@code false} otherwise
	 */
	public static boolean isEmpty() {
		boolean empty = true;

		empty &= BMP_PATHS.isEmpty();
		empty &= GIF_PATHS.isEmpty();
		empty &= JPEG_PATHS.isEmpty();
		empty &= PNG_PATHS.isEmpty();
		empty &= TIFF_PATHS.isEmpty();

		empty &= TTF_PATHS.isEmpty();
		empty &= OTF_PATHS.isEmpty();
		empty &= TYPE1_PATHS.isEmpty();

		empty &= MP3_PATHS.isEmpty();
		empty &= WAV_PATHS.isEmpty();

		empty &= SPRITES.isEmpty();
		empty &= FONTS.isEmpty();
		empty &= SOUNDS.isEmpty();

		return empty;
	}
}