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
package net.alexanderdev.lightdrive;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Annotation used for indicating that a certain method should be considered
 * internal to the library, and that directly invoking such a method could have
 * unfavorable or unexpected results.
 * 
 * @author Christian Bryce Alexander
 * @since Jan 19, 2016, 2:01:36 AM
 */
@Target(ElementType.METHOD)
public @interface InternalMethod {
}