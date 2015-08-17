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
package net.alexanderdev.lightdrive.media.graphics;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
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

/**
 * A class which encapsulates the functionality of {@code Graphics2D}, while
 * adding methods for some convenient drawing abilities, such as drawing a
 * string centered in a rectangle, and setting the font name, style, and size
 * attributes individually.
 * 
 * @see java.awt.Graphics2D
 * 
 * @author Christian Bryce Alexander
 * @since Apr 24, 2015 | 2:32:23 AM
 */
public final class GraphicsS extends Graphics2D {
	public static enum TextAlign {
		CENTER,
		LEFT,
		RIGHT
	}

	private Graphics2D graphics;

	/**
	 * Creates a class to wrap around the given {@code Graphics2D} object.
	 * 
	 * @param graphics
	 *            The {@code Graphics2D} object to be used in drawing
	 */
	public GraphicsS(Graphics2D graphics) {
		this.graphics = graphics;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addRenderingHints(Map<?, ?> hints) {
		graphics.addRenderingHints(hints);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clearRect(int x, int y, int width, int height) {
		graphics.clearRect(x, y, width, height);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clip(Shape s) {
		graphics.clip(s);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clipRect(int x, int y, int width, int height) {
		graphics.clipRect(x, y, width, height);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void copyArea(int x, int y, int width, int height, int dx, int dy) {
		graphics.copyArea(x, y, width, height, dx, dy);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Graphics create() {
		return graphics.create();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		graphics.dispose();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void draw(Shape s) {
		graphics.draw(s);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void draw3DRect(int x, int y, int width, int height, boolean raised) {
		graphics.draw3DRect(x, y, width, height, raised);
	}

	/**
	 * Draws the given {@code String} horizontally {@code LEFT}, {@code CENTER},
	 * and {@code RIGHT} aligned within the {@code Rectangle}. Use
	 * {@code GraphicsS.TextAlign}.
	 * 
	 * 
	 * @param text
	 *            The text to draw
	 * @param bounds
	 *            The {@code Rectangle} to align the text in
	 * @param align
	 *            How to horizontally align the text
	 */
	public void drawAlignedString(String text, Rectangle bounds, TextAlign align) {
		FontMetrics fm = graphics.getFontMetrics();

		int x;

		switch (align) {
			case CENTER:
				x = bounds.x + (bounds.width - fm.stringWidth(text)) / 2;
				break;
			case LEFT:
				x = bounds.x;
				break;
			case RIGHT:
				x = bounds.x + bounds.width - fm.stringWidth(text);
				break;
			default:
				x = bounds.x;
				break;
		}

		int y = bounds.y + ((bounds.height - fm.getHeight()) / 2) + fm.getAscent();

		graphics.drawString(text, x, y);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		graphics.drawArc(x, y, width, height, startAngle, arcAngle);
	}

	/**
	 * Draws an image, scaled to fit centered within a rectangle.
	 * 
	 * @param img
	 *            The image to draw.
	 * @param bounds
	 *            The rectangle to center the image in.
	 */
	public void drawCenteredImage(Image img, Rectangle bounds) {
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
		} else if (ratioW < ratioH) {
			int nwi = (int) (wi / ratioH);
			
			draw = new Rectangle((wb / 2) - (nwi / 2), 0, nwi, hb);
		} else {
			draw = bounds;
		}

		drawImage(img, draw.x, draw.y, draw.width, draw.height);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawGlyphVector(GlyphVector g, float x, float y) {
		graphics.drawGlyphVector(g, x, y);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawImage(BufferedImage img, BufferedImageOp op, int x, int y) {
		graphics.drawImage(img, op, x, y);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean drawImage(Image img, AffineTransform xform) {
		return graphics.drawImage(img, xform, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean drawImage(Image img, AffineTransform xform, ImageObserver obs) {
		return graphics.drawImage(img, xform, obs);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean drawImage(Image img, int x, int y) {
		return graphics.drawImage(img, x, y, null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean drawImage(Image img, int x, int y, Color bgcolor) {
		return graphics.drawImage(img, x, y, bgcolor, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
		return graphics.drawImage(img, x, y, bgcolor, observer);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
		return graphics.drawImage(img, x, y, observer);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean drawImage(Image img, int x, int y, int width, int height) {
		return graphics.drawImage(img, x, y, width, height, null);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor) {
		return graphics.drawImage(img, x, y, width, height, bgcolor, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
		return graphics.drawImage(img, x, y, width, height, bgcolor, observer);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
		return graphics.drawImage(img, x, y, width, height, observer);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2) {
		return graphics.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
		Color bgcolor) {
		return graphics.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
		Color bgcolor, ImageObserver observer) {
		return graphics.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
		ImageObserver observer) {
		return graphics.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		graphics.drawLine(x1, y1, x2, y2);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawOval(int x, int y, int width, int height) {
		graphics.drawOval(x, y, width, height);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		graphics.drawPolygon(xPoints, yPoints, nPoints);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
		graphics.drawPolyline(xPoints, yPoints, nPoints);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawRenderableImage(RenderableImage img, AffineTransform xform) {
		graphics.drawRenderableImage(img, xform);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawRenderedImage(RenderedImage img, AffineTransform xform) {
		graphics.drawRenderedImage(img, xform);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		graphics.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawString(AttributedCharacterIterator iterator, float x, float y) {
		graphics.drawString(iterator, x, y);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawString(AttributedCharacterIterator iterator, int x, int y) {
		graphics.drawString(iterator, x, y);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawString(String str, float x, float y) {
		graphics.drawString(str, x, y);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawString(String str, int x, int y) {
		graphics.drawString(str, x, y);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fill(Shape s) {
		graphics.fill(s);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fill3DRect(int x, int y, int width, int height, boolean raised) {
		graphics.fill3DRect(x, y, width, height, raised);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		graphics.fillArc(x, y, width, height, startAngle, arcAngle);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillOval(int x, int y, int width, int height) {
		graphics.fillOval(x, y, width, height);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		graphics.fillPolygon(xPoints, yPoints, nPoints);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillRect(int x, int y, int width, int height) {
		graphics.fillRect(x, y, width, height);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		graphics.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Color getBackground() {
		return graphics.getBackground();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Shape getClip() {
		return graphics.getClip();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Rectangle getClipBounds() {
		return graphics.getClipBounds();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Color getColor() {
		return graphics.getColor();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Composite getComposite() {
		return graphics.getComposite();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GraphicsConfiguration getDeviceConfiguration() {
		return graphics.getDeviceConfiguration();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Font getFont() {
		return graphics.getFont();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FontMetrics getFontMetrics(Font f) {
		return graphics.getFontMetrics(f);
	}

	/**
	 * @return The name of the current font
	 */
	public String getFontName() {
		return getFont().getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FontRenderContext getFontRenderContext() {
		return graphics.getFontRenderContext();
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
	 * {@inheritDoc}
	 */
	@Override
	public Paint getPaint() {
		return graphics.getPaint();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getRenderingHint(Key hintKey) {
		return graphics.getRenderingHint(hintKey);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RenderingHints getRenderingHints() {
		return graphics.getRenderingHints();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Stroke getStroke() {
		return graphics.getStroke();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AffineTransform getTransform() {
		return graphics.getTransform();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hit(Rectangle rect, Shape s, boolean onStroke) {
		return graphics.hit(rect, s, onStroke);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void rotate(double theta) {
		graphics.rotate(theta);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void rotate(double theta, double x, double y) {
		graphics.rotate(theta, x, y);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void scale(double sx, double sy) {
		graphics.scale(sx, sy);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setBackground(Color color) {
		graphics.setBackground(color);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setClip(int x, int y, int width, int height) {
		graphics.setClip(x, y, width, height);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setClip(Shape clip) {
		graphics.setClip(clip);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setColor(Color c) {
		graphics.setColor(c);
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
	 * {@inheritDoc}
	 */
	@Override
	public void setComposite(Composite comp) {
		graphics.setComposite(comp);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setFont(Font font) {
		graphics.setFont(font);
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
	 * {@inheritDoc}
	 */
	@Override
	public void setPaint(Paint paint) {
		graphics.setPaint(paint);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPaintMode() {
		graphics.setPaintMode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setRenderingHint(Key hintKey, Object hintValue) {
		graphics.setRenderingHint(hintKey, hintValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setRenderingHints(Map<?, ?> hints) {
		graphics.setRenderingHints(hints);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setStroke(Stroke s) {
		graphics.setStroke(s);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTransform(AffineTransform Tx) {
		graphics.setTransform(Tx);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setXORMode(Color c1) {
		graphics.setXORMode(c1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void shear(double shx, double shy) {
		graphics.shear(shx, shy);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void transform(AffineTransform Tx) {
		graphics.transform(Tx);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void translate(double tx, double ty) {
		graphics.translate(tx, ty);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void translate(int x, int y) {
		graphics.translate(x, y);
	}
}