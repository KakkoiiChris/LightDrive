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

import net.alexanderdev.lightdrive.util.math.MathX;

/**
 * An enumeration for specifying a blend mode for certain color operations.
 * Encapsulates a single {@link BlendOp}, which can be easily retrieved.
 * 
 * @author Christian Bryce Alexander
 * @since May 16, 2016, 6:46:30 PM
 */
public enum BlendMode {
	/**
	 * No blending occurs. Blend color is written directly to image. 
	 */
	NORMAL      ((target, blend) -> blend),
	/**
	 * The Photoshop Darken algorithm.
	 */
	DARKEN      ((target, blend) -> Math.min(target, blend)),
	/**
	 * The Photoshop Multiply algorithm.
	 */
	MULTIPLY    ((target, blend) -> target * blend),
	/**
	 * The Photoshop Color Burn algorithm.
	 */
	COLOR_BURN  ((target, blend) -> MathX.clamp(1f - (1f - target) / blend, 0f, 1f)),
	/**
	 * The Photoshop Linear Burn algorithm.
	 */
	LINEAR_BURN ((target, blend) -> MathX.clamp(target + blend - 1f, 0f, 1f)),
	/**
	 * The Photoshop Lighten algorithm.
	 */
	LIGHTEN     ((target, blend) -> Math.max(target, blend)),
	/**
	 * The Photoshop Screen algorithm.
	 */
	SCREEN      ((target, blend) -> 1f - (1f - target) * (1f - blend)),
	/**
	 * The Photoshop Color Dodge algorithm.
	 */
	COLOR_DODGE ((target, blend) -> MathX.clamp(target / (1f - blend), 0f, 1f)),
	/**
	 * The Photoshop Linear Dodge algorithm.
	 */
	LINEAR_DODGE((target, blend) -> MathX.clamp(target + blend, 0f, 1f)),
	/**
	 * The Photoshop Overlay algorithm.
	 */
	OVERLAY     ((target, blend) -> (target > 0.5f ? 1f : 0f) * (1f - (1f - 2f * (target - 0.5f)) * (1f - blend)) + (target <= 0.5f ? 1f : 0f) * ((2f * target) * blend)),
	/**
	 * The Photoshop Soft Light algorithm.
	 */
	SOFT_LIGHT  ((target, blend) -> (blend > 0.5f ? 1f : 0f) * (1f - (1f - target) * (1f - (blend - 0.5f)))	+ (blend <= 0.5f ? 1f : 0f) * (target * (blend + 0.5f))),
	/**
	 * The Photoshop Hard Light algorithm.
	 */
	HARD_LIGHT  ((target, blend) -> (blend > 0.5 ? 1f : 0f) * (1f - (1f - target) * (1f - 2f * (blend - 0.5f)))	+ (blend <= 0.5f ? 1f : 0f) * (target * (2f * blend))),
	/**
	 * The Photoshop Vivid Light algorithm.
	 */
	VIVID_LIGHT ((target, blend) -> (blend > 0.5f ? 1f : 0f) * (1f - (1f - target) / (2f * (blend - 0.5f))) + (blend <= 0.5f ? 1f : 0f) * (target / (1f - 2f * blend))),
	/**
	 * The Photoshop Linear Light algorithm.
	 */
	LINEAR_LIGHT((target, blend) -> MathX.clamp((blend > 0.5f ? 1f : 0f) * (target + 2f * (blend - 0.5f)) + (blend <= 0.5f ? 1f : 0f) * (target + 2f * blend - 1f), 0f, 1f)),
	/**
	 * The Photoshop Pin Light algorithm.
	 */
	PIN_LIGHT   ((target, blend) -> (blend > 0.5f ? 1f : 0f) * (Math.max(target, 2f * (blend - 0.5f))) + (blend <= 0.5f ? 1f : 0f) * (Math.min(target, 2f * blend))),
	/**
	 * The Photoshop Difference algorithm.
	 */
	DIFFERENCE  ((target, blend) -> Math.abs(target - blend)),
	/**
	 * The Photoshop Exclusion algorithm.
	 */
	EXCLUSION   ((target, blend) -> 0.5f - 2f * (target - 0.5f) * (blend - 0.5f));

	private BlendOp operation;

	private BlendMode(BlendOp operation) {
		this.operation = operation;
	}

	/**
	 * @return The {@link BlendOp} associated with this standard
	 *         {@code BlendMode}
	 */
	public BlendOp getOperation() {
		return operation;
	}
}