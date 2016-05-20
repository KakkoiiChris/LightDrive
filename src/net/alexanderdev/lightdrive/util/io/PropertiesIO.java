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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * A class for reading and writing preference/property files from a source
 * folder. Only one file is loaded into memory at a time.
 * 
 * @author Christian Bryce Alexander
 * @since May 26, 2015 | 7:26:54 AM
 */
public class PropertiesIO {
	private static final Properties PROPERTIES = new Properties();

	private static String path = "";

	/**
	 * Sets the path that will be prepended to the filename when loading
	 * properties from a file.
	 * 
	 * @param path
	 *            The path to be prepended
	 */
	public static void setPath(String path) {
		PropertiesIO.path = path;
	}

	/**
	 * Loads a new properties file into memory.
	 * 
	 * @param name
	 *            The name of the file to load
	 */
	public void load(String name) {
		try {
			PROPERTIES.load(new FileInputStream(path + name + ".txt"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param key
	 *            The name of the property to retrieve
	 * @return The named property stored in memory
	 */
	public String getProperty(String key) {
		return PROPERTIES.getProperty(key);
	}

	/**
	 * Sets the specified key to the specified value.
	 */
	public void setProperty(String key, String val) {
		PROPERTIES.setProperty(key, val);
	}

	/**
	 * Stores the properties in memory to a file.
	 * 
	 * @param name
	 *            The name of the file to store
	 */
	public void store(String name) {
		try {
			PROPERTIES.store(new FileOutputStream(path + name + ".txt"), null);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}