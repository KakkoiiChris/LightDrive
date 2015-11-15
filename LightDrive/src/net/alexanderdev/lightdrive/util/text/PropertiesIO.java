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
package net.alexanderdev.lightdrive.util.text;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class for reading and writing preference/property files.
 * 
 * @author Christian Bryce Alexander
 * @since May 26, 2015 | 7:26:54 AM
 */
public class PropertiesIO {
	private final Properties PROPERTIES = new Properties();

	private String filename;

	public PropertiesIO(String filename) {
		this.filename = filename;
	}

	public void open() {
		try {
			PROPERTIES.load(new FileInputStream(filename));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String loadProperty(String key) {
		return PROPERTIES.getProperty(key);
	}

	public void saveProperty(String key, String value) {
		PROPERTIES.setProperty(key, value);
	}

	public void close() {
		try {
			PROPERTIES.store(new FileOutputStream(filename), null);
		}
		catch (IOException ex) {
			Logger.getLogger(PropertiesIO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}