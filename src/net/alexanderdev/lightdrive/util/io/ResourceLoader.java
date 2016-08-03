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

import net.alexanderdev.lightdrive.Cleanable;
import net.alexanderdev.lightdrive.audio.Sound;
import net.alexanderdev.lightdrive.graphics.Sprite;
import net.alexanderdev.lightdrive.util.time.Time;

/**
 * A class which provides a visualization of base resource loading progress as a
 * splash screen. Base resources include {@link Sprite}s, {@link Font} names,
 * and {@link Sound}s. All other resources are based off of these three.
 * 
 * @author Christian Bryce Alexander
 * @since May 24, 2016, 2:24:32 PM
 */
public class ResourceLoader extends JFrame implements Cleanable {
	private static final long serialVersionUID = 8493474044583968037L;

	private final List<String> BMP_PATHS;
	private final List<String> GIF_PATHS;
	private final List<String> JPEG_PATHS;
	private final List<String> PNG_PATHS;
	private final List<String> TIFF_PATHS;

	private final Map<String, Sprite> SPRITES;

	private final List<String> TTF_PATHS;
	private final List<String> OTF_PATHS;
	private final List<String> TYPE1_PATHS;

	private final Map<String, Font> FONTS;

	private final List<String> MP3_PATHS;
	private final List<String> OGG_PATHS;
	private final List<String> WAV_PATHS;

	private final Map<String, Sound> SOUNDS;

	private int resourceCount = 0;

	private JLabel splash;
	private JProgressBar progress;

	public ResourceLoader() {
		super("LightDrive Resource Loader");

		BMP_PATHS = new ArrayList<>();
		GIF_PATHS = new ArrayList<>();
		JPEG_PATHS = new ArrayList<>();
		PNG_PATHS = new ArrayList<>();
		TIFF_PATHS = new ArrayList<>();

		SPRITES = new HashMap<>();

		TTF_PATHS = new ArrayList<>();
		OTF_PATHS = new ArrayList<>();
		TYPE1_PATHS = new ArrayList<>();

		FONTS = new HashMap<>();

		MP3_PATHS = new ArrayList<>();
		OGG_PATHS = new ArrayList<>();
		WAV_PATHS = new ArrayList<>();

		SOUNDS = new HashMap<>();

		resourceCount = 0;
	}

	/**
	 * Sets the {@link Image} for the {@link ImageIcon} that appears on the
	 * splash screen, and resizes the splash screen appropriately.
	 * 
	 * @param image
	 *            the splash image to set
	 */
	public void setSplashImage(Image image) {
		if (splash == null)
			splash = new JLabel();

		splash.setIcon(new ImageIcon(image));

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
	public void setSpriteRoot(String path) {
		SpriteIO.setPath(path);
	}

	/**
	 * Sets the root of the relative path to the fonts folder to load from.
	 * 
	 * @param path
	 *            The root path to set
	 */
	public void setFontRoot(String path) {
		FontIO.setPath(path);
	}

	/**
	 * Sets the root of the relative path to the sounds folder to load from.
	 * 
	 * @param path
	 *            The root path to set
	 */
	public void setSoundRoot(String path) {
		SoundIO.setPath(path);
	}

	/**
	 * Registers a varargs list of file names of BMP images that are to be
	 * loaded when {@link ResourceLoader#load(boolean)} is invoked.
	 * 
	 * @param names
	 *            The names of the files to register
	 */
	public void registerBMPs(String... names) {
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
	public void registerGIFs(String... names) {
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
	public void registerJPEGs(String... names) {
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
	public void registerPNGs(String... names) {
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
	public void registerTIFFs(String... names) {
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
	public void registerTTFs(String... names) {
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
	public void registerOTFs(String... names) {
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
	public void registerTYPE1s(String... names) {
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
	public void registerMP3s(String... names) {
		for (String name : names)
			MP3_PATHS.add(name);

		resourceCount += names.length;
	}

	/**
	 * Registers a varargs list of file names of Ogg Vorbis sounds that are to
	 * be loaded when {@link ResourceLoader#load(boolean)} is invoked.
	 * 
	 * @param names
	 *            The names of the files to register
	 */
	public void registerOGGs(String... names) {
		for (String name : names)
			OGG_PATHS.add(name);

		resourceCount += names.length;
	}

	/**
	 * Registers a varargs list of file names of WAV sounds that are to be
	 * loaded when {@link ResourceLoader#load(boolean)} is invoked.
	 * 
	 * @param names
	 *            The names of the files to register
	 */
	public void registerWAVs(String... names) {
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
	public Sprite getSprite(String name) {
		return SPRITES.get(name);
	}

	/**
	 * Retrieves a {@link Font} by name if it has been registered and loaded.
	 *
	 * @param name
	 *            The name of the {@link Font} to retrieve
	 * @return The named {@link Font}
	 */
	public Font getFont(String name) {
		return FONTS.get(name);
	}

	/**
	 * Retrieves a {@link Sound} by name if it has been registered and loaded.
	 *
	 * @param name
	 *            The name of the {@link Sound} to retrieve
	 * @return The named {@link Sound}
	 */
	public Sound getSound(String name) {
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
	public void load(boolean visible) {
		if (visible) {
			setLayout(new BorderLayout());

			if (splash == null) {
				splash = new JLabel();
				setSplashImage(SpriteIO.loadPNG("/img/lightdrive/splash640x480"));
			}

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

		for (String path : OGG_PATHS) {
			progress.setString("Loading Sound '" + path + "'");

			String[] dir = path.split("(/|\\\\)");
			SOUNDS.put(dir[dir.length - 1], SoundIO.loadOGG(path));

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

		progress.setString(String.format("Resources loaded: %fs\n", (end - start) * 10e-9));

		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		dispose();
	}

	/**
	 * Clears all loaded {@link Sprite}s and registered {@link Sprite} names.
	 */
	public void deregisterSprites() {
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
	public void deregisterFonts() {
		resourceCount -= FONTS.size();

		TTF_PATHS.clear();
		OTF_PATHS.clear();
		TYPE1_PATHS.clear();

		FONTS.clear();
	}

	/**
	 * Clears all loaded {@link Sound}s and registered {@link Sound} names.
	 */
	public void deregisterSounds() {
		resourceCount -= SOUNDS.size();

		MP3_PATHS.clear();
		WAV_PATHS.clear();

		SOUNDS.clear();
	}

	/**
	 * Clears all loaded resources and registered resource names.
	 */
	public void deregisterAll() {
		deregisterSprites();
		deregisterFonts();
		deregisterSounds();
	}

	/**
	 * @return {@code true} if no resources have been registered or loaded,
	 *         {@code false} otherwise
	 */
	public boolean isEmpty() {
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

	@Override
	public boolean cleanUp() {
		deregisterAll();
		return isEmpty();
	}
}