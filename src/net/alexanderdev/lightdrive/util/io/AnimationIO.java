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

import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageInputStream;

import net.alexanderdev.lightdrive.graphics.Animation;
import net.alexanderdev.lightdrive.graphics.Sprite;

/**
 * @author Christian Bryce Alexander
 * @since May 3, 2016, 2:59:35 AM
 */
public class AnimationIO {
	private static String path = "";

	public static void setPath(String path) {
		AnimationIO.path = path;
	}

	public static Animation loadAnimatedGIF(String name) {
		Animation animation = null;

		try {
			ImageReader reader = ImageIO.getImageReadersByFormatName("gif").next();

			InputStream input = SpriteIO.class.getResourceAsStream(path + name + ".gif");

			ImageInputStream stream = ImageIO.createImageInputStream(input);

			reader.setInput(stream);

			Sprite[] frames = new Sprite[reader.getNumImages(true)];

			for (int i = 0; i < frames.length; i++)
				frames[i] = new Sprite(reader.read(i));

			IIOMetadata imageMetaData = reader.getImageMetadata(0);

			String metaFormatName = imageMetaData.getNativeMetadataFormatName();

			IIOMetadataNode root = (IIOMetadataNode) imageMetaData.getAsTree(metaFormatName);

			IIOMetadataNode gceNode = getNode(root, "GraphicControlExtension");

			long delay = Long.parseLong(gceNode.getAttribute("delayTime")) * 10;

			animation = new Animation(frames, delay);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return animation;
	}

	private static IIOMetadataNode getNode(IIOMetadataNode rootNode, String nodeName) {
		int nNodes = rootNode.getLength();

		for (int i = 0; i < nNodes; i++) {
			if (rootNode.item(i).getNodeName().compareToIgnoreCase(nodeName) == 0) {
				return ((IIOMetadataNode) rootNode.item(i));
			}
		}

		IIOMetadataNode node = new IIOMetadataNode(nodeName);

		rootNode.appendChild(node);

		return node;
	}
}