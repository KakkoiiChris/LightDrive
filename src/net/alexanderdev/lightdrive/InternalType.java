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
 * Annotation used for indicating that a certain type should be considered internal
 * to the library, and that directly instantiating or manipulating such a
 * type could have unfavorable results.
 * 
 * @author Christian Bryce Alexander
 * @since Jan 19, 2016, 2:01:24 AM
 */
@Target(ElementType.TYPE)
public @interface InternalType {
}