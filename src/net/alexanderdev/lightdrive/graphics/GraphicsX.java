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
package net.alexanderdev.lightdrive.graphics;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

import net.alexanderdev.lightdrive.util.math.geom.RectangleF;

/**
 * A class which encapsulates the functionality of {@link Graphics2D}, while
 * adding or modifying methods for some convenient drawing abilities. Such
 * additions and modifications include:
 * <ul>
 * <li>Drawing text which is aligned relative to a {@link Rectangle}</li>
 * <li>Drawing text which takes new-line characters into consideration</li>
 * <li>Drawing text with it's characters spaced out</li>
 * <li>Drawing text from the top-left corner, instead of the baseline</li>
 * <li>Drawing images without the need for an {@link ImageObserver}</li>
 * <li>Drawing images that are scaled to fit within a {@link Rectangle}</li>
 * <li>Getting and setting {@link Font} attributes separately</li>
 * <li>Setting the color with a '0xAARRGGBB' formatted integer color value</li>
 * </ul>
 * 
 * @see java.awt.Graphics2D
 * 
 * @author Christian Bryce Alexander
 * @since Apr 24, 2015 | 2:32:23 AM
 */
public class GraphicsX {
	public static enum TextAlign {
		TOP_LEFT,
		TOP_CENTER,
		TOP_RIGHT,
		MID_LEFT,
		MID_CENTER,
		MID_RIGHT,
		BOTTOM_LEFT,
		BOTTOM_CENTER,
		BOTTOM_RIGHT;
	}

	private FontMetrics fm;

	private Graphics2D g;

	/**
	 * Creates a class to wrap around the given {@link Graphics2D} object.
	 * 
	 * @param g
	 *            The {@link Graphics2D} object to be used in drawing
	 */
	public GraphicsX(Graphics2D g) {
		this.g = g;

		fm = g.getFontMetrics();
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#addRenderingHints(Map)}.
	 */
	public void addRenderingHints(Map<?, ?> hints) {
		g.addRenderingHints(hints);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#clearRect(int, int, int, int)}.
	 */
	public void clearRect(int x, int y, int width, int height) {
		g.clearRect(x, y, width, height);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#clip(Shape)}.
	 */
	public void clip(Shape s) {
		g.clip(s);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#clipRect(int, int, int, int)}.
	 */
	public void clipRect(int x, int y, int width, int height) {
		g.clipRect(x, y, width, height);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#copyArea(int, int, int, int, int, int)}.
	 */
	public void copyArea(int x, int y, int width, int height, int dx, int dy) {
		g.copyArea(x, y, width, height, dx, dy);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#create()}.
	 */
	public Graphics create() {
		return g.create();
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#create(int, int, int, int)}.
	 */
	public Graphics create(int x, int y, int width, int height) {
		return g.create(x, y, width, height);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#dispose()}.
	 */
	public void dispose() {
		g.dispose();
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#draw(Shape)}.
	 */
	public void draw(Shape s) {
		g.draw(s);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#draw3DRect(int, int, int, int, boolean)}.
	 */
	public void draw3DRect(int x, int y, int width, int height, boolean raised) {
		g.draw3DRect(x, y, width, height, raised);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#drawArc(int, int, int, int, int, int)}.
	 */
	public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		g.drawArc(x, y, width, height, startAngle, arcAngle);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#drawBytes(byte[], int, int, int, int)}.
	 */
	public void drawBytes(byte data[], int offset, int length, int x, int y) {
		g.drawBytes(data, offset, length, x, y);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#drawChars(char[], int, int, int, int)}.
	 */
	public void drawChars(char data[], int offset, int length, int x, int y) {
		g.drawChars(data, offset, length, x, y);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#drawGlyphVector(GlyphVector, float, float)}.
	 */
	public void drawGlyphVector(GlyphVector g, float x, float y) {
		this.g.drawGlyphVector(g, x, y);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#drawImage(BufferedImage, BufferedImageOp, int, int)}.
	 */
	public void drawImage(BufferedImage img, BufferedImageOp op, int x, int y) {
		g.drawImage(img, op, x, y);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#drawImage(Image, AffineTransform, ImageObserver)} .
	 * <br>
	 * <br>
	 * As stated in the class description, this method is rewritten to remove
	 * the need for an {@link ImageObserver}.
	 */
	public void drawImage(Image img, AffineTransform xform) {
		g.drawImage(img, xform, null);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#drawImage(Image, int, int, ImageObserver)}.<br>
	 * <br>
	 * As stated in the class description, this method is rewritten to remove
	 * the need for an {@link ImageObserver}.
	 */
	public void drawImage(Image img, int x, int y) {
		g.drawImage(img, x, y, null);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#drawImage(Image, int, int, Color, ImageObserver)}.<br>
	 * <br>
	 * As stated in the class description, this method is rewritten to remove
	 * the need for an {@link ImageObserver}.
	 */
	public void drawImage(Image img, int x, int y, Color bgcolor) {
		g.drawImage(img, x, y, bgcolor, null);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#drawImage(Image, int, int, int, int, ImageObserver)}.<br>
	 * <br>
	 * As stated in the class description, this method is rewritten to remove
	 * the need for an {@link ImageObserver}.
	 */
	public void drawImage(Image img, int x, int y, int width, int height) {
		g.drawImage(img, x, y, width, height, null);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#drawImage(Image, int, int, int, int, Color, ImageObserver)}
	 * .<br>
	 * <br>
	 * As stated in the class description, this method is rewritten to remove
	 * the need for an {@link ImageObserver}.
	 */
	public void drawImage(Image img, int x, int y, int width, int height, Color bgcolor) {
		g.drawImage(img, x, y, width, height, bgcolor, null);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#drawImage(Image, int, int, int, int, int, int, int, int, ImageObserver)}
	 * .<br>
	 * <br>
	 * As stated in the class description, this method is rewritten to remove
	 * the need for an {@link ImageObserver}.
	 */
	public void drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2) {
		g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#drawImage(Image, int, int, int, int, int, int, int, int, Color, ImageObserver)}
	 * .<br>
	 * <br>
	 * As stated in the class description, this method is rewritten to remove
	 * the need for an {@link ImageObserver}.
	 */
	public void drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
	    Color bgcolor) {
		g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, null);
	}

	public void drawImage(Image img, Rectangle bounds) {
		Rectangle draw;

		int wi = img.getWidth(null);
		int hi = img.getHeight(null);
		int wb = bounds.width;
		int hb = bounds.height;

		double ratioW = (double) wi / (double) wb;
		double ratioH = (double) hi / (double) hb;

		if (ratioW > ratioH) {
			int nhi = (int) (hi / ratioW);

			draw = new Rectangle(0, (hb / 2) - (nhi / 2), wb, nhi);
		}
		else if (ratioW < ratioH) {
			int nwi = (int) (wi / ratioH);

			draw = new Rectangle((wb / 2) - (nwi / 2), 0, nwi, hb);
		}
		else {
			draw = bounds;
		}

		drawImage(img, draw.x, draw.y, draw.width, draw.height);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#drawLine(int, int, int, int)}.
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		g.drawLine(x1, y1, x2, y2);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#drawOval(int, int, int, int)}.
	 */
	public void drawOval(int x, int y, int width, int height) {
		g.drawOval(x, y, width, height);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#drawPolygon(int[], int[], int)}.
	 */
	public void drawPolygon(int xPoints[], int yPoints[], int nPoints) {
		g.drawPolygon(xPoints, yPoints, nPoints);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#drawPolygon(Polygon)}.
	 */
	public void drawPolygon(Polygon p) {
		g.drawPolygon(p);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#drawPolyline(int[], int[], int)}.
	 */
	public void drawPolyline(int xPoints[], int yPoints[], int nPoints) {
		g.drawPolyline(xPoints, yPoints, nPoints);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#drawRect(int, int, int, int)}.
	 */
	public void drawRect(int x, int y, int width, int height) {
		g.drawRect(x, y, width, height);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#drawRenderableImage(RenderableImage, AffineTransform)}.
	 */
	public void drawRenderableImage(RenderableImage img, AffineTransform xform) {
		g.drawRenderableImage(img, xform);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#drawRenderedImage(RenderedImage, AffineTransform)}.
	 */
	public void drawRenderedImage(RenderedImage img, AffineTransform xform) {
		g.drawRenderedImage(img, xform);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#drawRoundRect(int, int, int, int, int, int)}.
	 */
	public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		g.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#drawString(AttributedCharacterIterator, float, float)}.
	 * <br>
	 * <br>
	 * As stated in the class description, this method is rewritten so that text
	 * is drawn from the top left of the string, instead of from the baseline.
	 */
	public void drawString(AttributedCharacterIterator iterator, float x, float y) {
		g.drawString(iterator, x, y + fm.getAscent());
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#drawString(AttributedCharacterIterator, int, int)}.<br>
	 * <br>
	 * As stated in the class description, this method is rewritten so that text
	 * is drawn from the top left of the string, instead of from the baseline.
	 */
	public void drawString(AttributedCharacterIterator iterator, int x, int y) {
		g.drawString(iterator, x, y + fm.getAscent());
	}

	/**
	 * Draws a string as specified by
	 * {@link GraphicsX#drawString(String, float, float, float, float)}, with a
	 * default character spacing of 0.
	 */
	public void drawString(String str, float x, float y) {
		drawString(str, x, y, 0, 0);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#drawString(String, float, float)}.<br>
	 * <br>
	 * As stated in the class description, this method is rewritten so that text
	 * is drawn from the top left of the string, instead of from the baseline,
	 * all new-line characters cause the string to be drawn a line below the one
	 * before, and it incorporates vertical and horizontal spacing between
	 * characters.
	 */
	public void drawString(String str, float x, float y, float xs, float ys) {
		float ox = x;

		y += fm.getAscent();

		for (String line : str.split("\n")) {
			for (int i = 0; i < line.length(); i++) {
				g.drawString(line.charAt(i) + "", x, y);

				x += fm.stringWidth(line.charAt(i) + "") + xs;
			}

			y += fm.getHeight() + ys;
			x = ox;
		}
	}

	/**
	 * Draws a string as specified by
	 * {@link GraphicsX#drawString(String, int, int, int, int)}, with a default
	 * character spacing of 0.
	 */
	public void drawString(String str, int x, int y) {
		drawString(str, x, y, 0, 0);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#drawString(String, int, int)}.<br>
	 * <br>
	 * As stated in the class description, this method is rewritten so that text
	 * is drawn from the top left of the string, instead of from the baseline,
	 * all new-line characters cause the string to be drawn a line below the one
	 * before, and it incorporates vertical and horizontal spacing between
	 * characters.
	 */
	public void drawString(String str, int x, int y, int xs, int ys) {
		int ox = x;

		y += fm.getAscent();

		for (String line : str.split("\n")) {
			for (int i = 0; i < line.length(); i++) {
				g.drawString(line.charAt(i) + "", x, y);

				x += fm.stringWidth(line.charAt(i) + "") + xs;
			}

			y += fm.getHeight() + ys;
			x = ox;
		}
	}

	public void drawString(String str, Rectangle bounds) {
		drawString(str, bounds, TextAlign.MID_CENTER);
	}

	/**
	 * Draws the given {@link String} horizontally left, center, or right
	 * aligned within the {@link Rectangle}. Uses {@link GraphicsX.TextAlign}.
	 * 
	 * 
	 * @param str
	 *            The text to draw
	 * @param bounds
	 *            The {@link Rectangle} to align the text in
	 * @param align
	 *            How to horizontally align the text
	 */
	public void drawString(String str, Rectangle bounds, TextAlign align) {
		drawString(str, bounds, 0, 0, align);
	}

	public void drawString(String str, Rectangle bounds, int xs, int ys) {
		drawString(str, bounds, xs, ys, TextAlign.MID_CENTER);
	}

	/**
	 * Draws the given {@link String} horizontally left, center, or right
	 * aligned within the {@link Rectangle}. Uses {@link GraphicsX.TextAlign}.
	 * 
	 * 
	 * @param str
	 *            The text to draw
	 * @param bounds
	 *            The {@link Rectangle} to align the text in
	 * @param align
	 *            How to horizontally align the text
	 */
	public void drawString(String str, Rectangle bounds, int xs, int ys, TextAlign align) {
		int x = bounds.x;
		int y = bounds.y;

		String v = align.name().split("_")[0];
		String h = align.name().split("_")[1];

		String[] lines = str.split("\n");

		if (v.equals("TOP"))
			y = bounds.y;
		else if (v.equals("MID"))
			y = bounds.y + (bounds.height / 2) - ((fm.getHeight() * lines.length) / 2)
			    - ((ys * (lines.length - 1)) / 2);
		else if (v.equals("BOTTOM"))
			y = bounds.y + bounds.height - (fm.getHeight() * lines.length) - (ys * (lines.length - 1));

		for (String line : lines) {
			if (h.equals("LEFT"))
				x = bounds.x;
			else if (h.equals("CENTER"))
				x = bounds.x + (bounds.width / 2) - (fm.stringWidth(line) / 2) - ((xs * (line.length() - 1)) / 2);
			else if (h.equals("RIGHT"))
				x = bounds.x + bounds.width - fm.stringWidth(line) - (xs * (line.length() - 1));

			drawString(line, x, y, xs, 0);

			y += fm.getHeight() + ys;
		}
	}

	public void drawString(String str, RectangleF bounds) {
		drawString(str, bounds, TextAlign.MID_CENTER);
	}

	/**
	 * Draws the given {@link String} horizontally left, center, or right
	 * aligned within the {@link Rectangle}. Uses {@link GraphicsX.TextAlign}.
	 * 
	 * 
	 * @param str
	 *            The text to draw
	 * @param bounds
	 *            The {@link Rectangle} to align the text in
	 * @param align
	 *            How to horizontally align the text
	 */
	public void drawString(String str, RectangleF bounds, TextAlign align) {
		drawString(str, bounds, 0, 0, align);
	}

	public void drawString(String str, RectangleF bounds, float xs, float ys) {
		drawString(str, bounds, xs, ys, TextAlign.MID_CENTER);
	}

	/**
	 * Draws the given {@link String} horizontally left, center, or right
	 * aligned within the {@link Rectangle}. Uses {@link GraphicsX.TextAlign}.
	 * 
	 * 
	 * @param str
	 *            The text to draw
	 * @param bounds
	 *            The {@link Rectangle} to align the text in
	 * @param align
	 *            How to horizontally align the text
	 */
	public void drawString(String str, RectangleF bounds, float xs, float ys, TextAlign align) {
		float x = bounds.x;
		float y = bounds.y;

		String v = align.name().split("_")[0];
		String h = align.name().split("_")[1];

		String[] lines = str.split("\n");

		if (v.equals("TOP"))
			y = bounds.y;
		else if (v.equals("MID"))
			y = bounds.y + (bounds.height / 2) - ((fm.getHeight() * lines.length) / 2)
			    - ((ys * (lines.length - 1)) / 2);
		else if (v.equals("BOTTOM"))
			y = bounds.y + bounds.height - (fm.getHeight() * lines.length) - (ys * (lines.length - 1));

		for (String line : lines) {
			if (h.equals("LEFT"))
				x = bounds.x;
			else if (h.equals("CENTER"))
				x = bounds.x + (bounds.width / 2) - (fm.stringWidth(line) / 2) - ((xs * (line.length() - 1)) / 2);
			else if (h.equals("RIGHT"))
				x = bounds.x + bounds.width - fm.stringWidth(line) - (xs * (line.length() - 1));

			drawString(line, x, y, xs, 0);

			y += fm.getHeight() + ys;
		}
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#fill(Shape)}.
	 */
	public void fill(Shape s) {
		g.fill(s);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#fill3DRect(int, int, int, int, boolean)}.
	 */
	public void fill3DRect(int x, int y, int width, int height, boolean raised) {
		g.fill3DRect(x, y, width, height, raised);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#fillArc(int, int, int, int, int, int)}.
	 */
	public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		g.fillArc(x, y, width, height, startAngle, arcAngle);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#fillOval(int, int, int, int)}.
	 */
	public void fillOval(int x, int y, int width, int height) {
		g.fillOval(x, y, width, height);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#fillPolygon(int[], int[], int)}.
	 */
	public void fillPolygon(int xPoints[], int yPoints[], int nPoints) {
		g.fillPolygon(xPoints, yPoints, nPoints);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#fillPolygon(Polygon)}.
	 */
	public void fillPolygon(Polygon p) {
		g.fillPolygon(p);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#fillRect(int, int, int, int)}.
	 */
	public void fillRect(int x, int y, int width, int height) {
		g.fillRect(x, y, width, height);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#fillRoundRect(int, int, int, int, int, int)}.
	 */
	public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#finalize()}.
	 */
	@Override
	public void finalize() {
		g.finalize();
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#getBackground()}.
	 */
	public Color getBackground() {
		return g.getBackground();
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#getClip()}.
	 */
	public Shape getClip() {
		return g.getClip();
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#getClipBounds()}.
	 */
	public Rectangle getClipBounds() {
		return g.getClipBounds();
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#getClipBounds(Rectangle)}.
	 */
	public Rectangle getClipBounds(Rectangle r) {
		return g.getClipBounds(r);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#getColor()}.
	 */
	public Color getColor() {
		return g.getColor();
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#getComposite()}.
	 */
	public Composite getComposite() {
		return g.getComposite();
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#getDeviceConfiguration()}.
	 */
	public GraphicsConfiguration getDeviceConfiguration() {
		return g.getDeviceConfiguration();
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#getFont()}.
	 */
	public Font getFont() {
		return g.getFont();
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#getFontMetrics()}.
	 */
	public FontMetrics getFontMetrics() {
		return g.getFontMetrics();
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#getFontMetrics(Font)}.
	 */
	public FontMetrics getFontMetrics(Font f) {
		return g.getFontMetrics(f);
	}

	/**
	 * @return The name of the current font
	 */
	public String getFontName() {
		return getFont().getName();
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#getFontRenderContext()}.
	 */
	public FontRenderContext getFontRenderContext() {
		return g.getFontRenderContext();
	}

	/**
	 * @return The size of the current font
	 */
	public int getFontSize() {
		return getFont().getSize();
	}

	/**
	 * @return The style of the current font
	 */
	public int getFontStyle() {
		return getFont().getStyle();
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#getPaint()}.
	 */
	public Paint getPaint() {
		return g.getPaint();
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#getRenderingHint(Key)}.
	 */
	public Object getRenderingHint(Key hintKey) {
		return g.getRenderingHint(hintKey);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#getRenderingHints()}.
	 */
	public RenderingHints getRenderingHints() {
		return g.getRenderingHints();
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#getStroke()}.
	 */
	public Stroke getStroke() {
		return g.getStroke();
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#getTransform()}.
	 */
	public AffineTransform getTransform() {
		return g.getTransform();
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#hit(Rectangle, Shape, boolean)}.
	 */
	public boolean hit(Rectangle rect, Shape s, boolean onStroke) {
		return g.hit(rect, s, onStroke);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#hitClip(int, int, int, int)}.
	 */
	public boolean hitClip(int x, int y, int width, int height) {
		return g.hitClip(x, y, width, height);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#rotate(double)}.
	 */
	public void rotate(double theta) {
		g.rotate(theta);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#rotate(double, double, double)}.
	 */
	public void rotate(double theta, double x, double y) {
		g.rotate(theta, x, y);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#scale(double, double)}.
	 */
	public void scale(double sx, double sy) {
		g.scale(sx, sy);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#setBackground(Color)}.
	 */
	public void setBackground(Color color) {
		g.setBackground(color);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#setClip(int, int, int, int)}.
	 */
	public void setClip(int x, int y, int width, int height) {
		g.setClip(x, y, width, height);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#setClip(Shape)}.
	 */
	public void setClip(Shape clip) {
		g.setClip(clip);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#setColor(Color)}.
	 */
	public void setColor(Color c) {
		g.setColor(c);
	}

	/**
	 * Sets the color using an integer value
	 *
	 * @param c
	 *            The color value to set
	 */
	public void setColor(int c) {
		setColor(new Color(c));
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#setComposite(Composite)}.
	 */
	public void setComposite(Composite comp) {
		g.setComposite(comp);
	}

	/**
	 * Delegates to the method of the same name, in {@link Graphics2D#}.
	 */
	public void setFont(Font font) {
		g.setFont(font);

		fm = g.getFontMetrics();
	}

	/**
	 * Sets the name of the current font
	 *
	 * @param name
	 *            The name of the font to set
	 */
	public void setFontName(String name) {
		Font font = getFont();

		setFont(new Font(name, font.getStyle(), font.getSize()));
	}

	/**
	 * Sets the size of the current font
	 *
	 * @param size
	 *            The size of the font to set
	 */
	public void setFontSize(int size) {
		Font font = getFont();

		setFont(new Font(font.getName(), font.getStyle(), size));
	}

	/**
	 * Sets the style of the current font
	 *
	 * @param style
	 *            The style of the font to set
	 */
	public void setFontStyle(int style) {
		Font font = getFont();

		setFont(new Font(font.getName(), style, font.getSize()));
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#setPaint(Paint)}.
	 */
	public void setPaint(Paint paint) {
		g.setPaint(paint);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#setPaintMode()}.
	 */
	public void setPaintMode() {
		g.setPaintMode();
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#setRenderingHint(Key, Object)}.
	 */
	public void setRenderingHint(Key hintKey, Object hintValue) {
		g.setRenderingHint(hintKey, hintValue);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#setRenderingHints(Map)}.
	 */
	public void setRenderingHints(Map<?, ?> hints) {
		g.setRenderingHints(hints);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#setStroke(Stroke)}.
	 */
	public void setStroke(Stroke s) {
		g.setStroke(s);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#setTransform(AffineTransform)}.
	 */
	public void setTransform(AffineTransform Tx) {
		g.setTransform(Tx);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#setXORMode(Color)}.
	 */
	public void setXORMode(Color c1) {
		g.setXORMode(c1);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#shear(double, double)}.
	 */
	public void shear(double shx, double shy) {
		g.shear(shx, shy);
	}

	@Override
	public String toString() {
		return getClass().getName() + "[font=" + getFont() + ",color=" + getColor() + "]";
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#transform(AffineTransform)}.
	 */
	public void transform(AffineTransform Tx) {
		g.transform(Tx);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics2D#translate(double, double)}.
	 */
	public void translate(double tx, double ty) {
		g.translate(tx, ty);
	}

	/**
	 * Delegates to the method of the same name, found in
	 * {@link Graphics#translate(int, int)}.
	 */
	public void translate(int x, int y) {
		g.translate(x, y);
	}
}