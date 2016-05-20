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

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

/**
 * A class for reading and writing XML files from a source folder.
 * 
 * @author Christian Bryce Alexander
 * @since Aug 17, 2015, 5:30:38 PM
 */
public class XMLIO {
	private static String path = "";

	/**
	 * Sets the path that will be prepended to the filename when loading an XML
	 * document from a file.
	 * 
	 * @param path
	 *            The path to be prepended
	 */
	public static void setPath(String path) {
		XMLIO.path = path;
	}

	/**
	 * @return An empty XML {@link Document}.
	 */
	public static Document create() {
		DocumentBuilder builder = null;

		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		Document xml = builder.newDocument();

		return xml;
	}

	/**
	 * @param name
	 *            The name of the file to read
	 * @return The contents of an XML file in a {@link Document}.
	 */
	public static Document read(String name) {
		DocumentBuilder builder = null;

		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		Document xml = null;

		try {
			xml = builder.parse(XMLIO.class.getResourceAsStream(path + name + ".xml"));
		}
		catch (Exception e) {
			try {
				xml = builder.parse(XMLIO.class.getResourceAsStream(name + ".xml"));
			}
			catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		xml.normalize();

		return xml;
	}

	/**
	 * Writes a {@link Document} as an XML file somewhere at the specified
	 * location in the file system.
	 * 
	 * @param doc
	 *            The {@link Document} to write
	 * @param loc
	 *            The absolute path to write to
	 */
	public static void write(Document doc, String loc) {
		Transformer transformer = null;

		try {
			transformer = TransformerFactory.newInstance().newTransformer();
		}
		catch (TransformerConfigurationException | TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}

		DOMSource source = new DOMSource(doc);

		StreamResult result = new StreamResult(new File(loc));

		try {
			transformer.transform(source, result);
		}
		catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}