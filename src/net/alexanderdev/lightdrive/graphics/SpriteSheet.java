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

/**
 * A class to create a convenient collection of images from one image
 * 
 * @author Christian Bryce Alexander
 * @since March 12, 2015 | 6:17:05 PM
 */
public class SpriteSheet {
	private Sprite[][] sprites;

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
	public SpriteSheet(Sprite sheet, int spriteWidth, int spriteHeight) {
		this.width = sheet.getWidth();
		this.height = sheet.getHeight();
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;

		if (width < spriteWidth)
			throw new IllegalArgumentException(String
				.format("LIGHT DRIVE ERROR: Sprites of width %d cannot be generated from an image of width %d.", spriteWidth, width));

		if (height < spriteHeight)
			throw new IllegalArgumentException(String
				.format("LIGHT DRIVE ERROR: Sprites of height %d cannot be generated from an image of height %d.", spriteHeight, height));

		if (width % spriteWidth != 0) {
			System.err.printf("LIGHT DRIVE WARNING: Sprites of width %d will not divide an image of width %d evenly.\n",
				spriteWidth, width);
			Thread.dumpStack();
		}

		if (height % spriteHeight != 0) {
			System.err.printf("LIGHT DRIVE WARNING: Sprites of height %d will not divide an image of height %d evenly.\n",
				spriteHeight, height);
			Thread.dumpStack();
		}

		this.cols = width / spriteWidth;
		this.rows = height / spriteHeight;

		sprites = new Sprite[rows][cols];

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
	public Sprite getSprite(int r, int c) {
		if (r < 0 || r >= rows)
			throw new ArrayIndexOutOfBoundsException(r);

		if (c < 0 || c >= cols)
			throw new ArrayIndexOutOfBoundsException(c);

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
	public Sprite getSprite(int i) {
		int r = i / cols;
		int c = i % cols;

		return getSprite(r, c);
	}

	public Sprite[] getSprites(int start, int end) {
		Sprite[] sprites = new Sprite[(end - start) + 1];

		for (int i = 0; i < sprites.length; i++)
			sprites[i] = getSprite(start + i);

		return sprites;
	}

	public Sprite[] getSequentialSprites() {
		Sprite[] frames = new Sprite[rows * cols];

		for (int i = 0; i < frames.length; i++)
			frames[i] = getSprite(i);

		return frames;
	}
}