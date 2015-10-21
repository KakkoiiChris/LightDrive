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

import net.alexanderdev.lightdrive.exception.OversizedSpriteException;
import net.alexanderdev.lightdrive.exception.SpriteOutOfBoundsException;
import net.alexanderdev.lightdrive.exception.SpriteSheetException;
import net.alexanderdev.lightdrive.exception.UnevenSpriteDivisionException;

/**
 * A class to create a convenient collection of images from one image
 * 
 * @author Christian Bryce Alexander
 * @since March 12, 2015 | 6:17:05 PM
 */
public class SpriteSheet {
	private ImageS[][] sprites;

	private int rows;
	private int cols;
	private int width;
	private int height;
	private int spriteWidth;
	private int spriteHeight;

	/**
	 * Creates a sprite sheet from the image {@code sheet} with sprites of the
	 * specified size
	 *
	 * @param sheet
	 *            The source image
	 * @param spriteWidth
	 *            Width of all sprites
	 * @param spriteHeight
	 *            Height of all sprites
	 * @throws SpriteSheetException
	 */
	public SpriteSheet(ImageS sheet, int spriteWidth, int spriteHeight) throws SpriteSheetException {
		this.width = sheet.getWidth();
		this.height = sheet.getHeight();
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;

		if (sheet.getWidth() < spriteWidth)
			throw new OversizedSpriteException("width", spriteWidth);

		if (sheet.getHeight() < spriteHeight)
			throw new OversizedSpriteException("height", spriteHeight);

		if (sheet.getWidth() % spriteWidth != 0)
			throw new UnevenSpriteDivisionException("width", spriteWidth, width);

		if (sheet.getHeight() % spriteHeight != 0)
			throw new UnevenSpriteDivisionException("height", spriteHeight, height);

		this.cols = sheet.getWidth() / spriteWidth;
		this.rows = sheet.getHeight() / spriteHeight;

		sprites = new ImageS[rows][cols];

		for (int r = 0; r < rows; r++)
			for (int c = 0; c < cols; c++)
				sprites[r][c] = sheet.getSubimage(c * spriteWidth, r * spriteHeight, spriteWidth, spriteHeight);
	}
	
	/**
	 * @return The number of rows of sprites in this {@code SpriteSheet}
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @return The number of columns of sprites in this {@code SpriteSheet}
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * @return The width of this {@code SpriteSheet}
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return The height of this {@code SpriteSheet}
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return The width of the sprites in this {@code SpriteSheet}
	 */
	public int getSpriteWidth() {
		return spriteWidth;
	}

	/**
	 * @return The height of the sprites in this {@code SpriteSheet}
	 */
	public int getSpriteHeight() {
		return spriteHeight;
	}

	/**
	 * @return The sprite at the specified row and column
	 * @throws SpriteOutOfBoundsException
	 */
	public ImageS getSprite(int r, int c) throws SpriteOutOfBoundsException {
		if (r < 0 || r >= rows)
			throw new SpriteOutOfBoundsException(c, r, "row");

		if (c < 0 || c >= cols)
			throw new SpriteOutOfBoundsException(c, r, "column");

		return sprites[r][c];
	}

	/**
	 * The value i works as such:<br>
	 * 
	 * <pre>
	 *    |   |   |
	 *  0 | 1 | 2 | 3
	 * ---|---|---|---
	 *  4 | 5 | 6 | 7
	 * ---|---|---|---
	 *  8 | 9 |10 |11
	 *    |   |   |
	 * </pre>
	 * 
	 * 
	 * @return The "i"th sprite on the sheet, which wraps around horizontally
	 * @throws SpriteOutOfBoundsException
	 */
	public ImageS getSprite(int i) throws SpriteOutOfBoundsException {
		int r = i / cols;
		int c = i % cols;

		return getSprite(r, c);
	}

	public ImageS[] getSprites(int start, int end) throws SpriteOutOfBoundsException {
		ImageS[] sprites = new ImageS[(end - start) + 1];

		for (int i = 0; i < sprites.length; i++)
			sprites[i] = getSprite(start + i);

		return sprites;
	}

	public ImageS[] getSequentialSprites() {
		ImageS[] frames = new ImageS[rows * cols];

		for (int i = 0; i < frames.length; i++) {
			try {
				frames[i] = getSprite(i);
			} catch (SpriteOutOfBoundsException e) {
				e.printStackTrace();
			}
		}

		return frames;
	}
}