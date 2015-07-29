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

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * A class with which to generate XML documents for parsing.
 * 
 * @author Christian Bryce Alexander
 * @since April 7, 2015 | 6:08:43 PM
 */
public class XML {
	private String loc;
	
	private Document xmlDoc;

	/**
	 * Generates an XML document for parsing
	 * 
	 * @param loc
	 *            The path to the XML document
	 * @param relative
	 *            {@code true} if relative path, {@code false} if absolute path
	 */
	public XML(String loc, boolean relative) throws ParserConfigurationException, SAXException, IOException {
		this.loc = loc;
		
		init(relative);
		
		xmlDoc.normalize();
	}

	private void init(boolean relative) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory builder = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder docBuilder = builder.newDocumentBuilder();

		if (relative) {
			xmlDoc = docBuilder.parse(this.getClass().getResourceAsStream(loc));
		} else {
			xmlDoc = docBuilder.parse(new File(loc));
		}
	}

	/**
	 * @return Specified file path
	 */
	public String getPath() {
		return loc;
	}

	/**
	 * @return Document generated from the file
	 */
	public Document getDocument() {
		return xmlDoc;
	}
}