package de.tudarmstadt.informatik.fop.breakout.ui.settings;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import de.tudarmstadt.informatik.fop.breakout.ui.constants.OurGameParameters;

/**
 * This class represents the themes played in the game.
 * 
 * @author Andrés Fraga Trespalacios, Farooq Khalil, Sebastian Sahner, </br>
 *         Timo Bochenko
 * 
 * @version 1.1 - removed playing theme
 */
public class Themes implements OurGameParameters {

	/** The main theme. */
	public static Music mainTheme;

	/** The game theme. */
	public static Music gameplayTheme;

	/**
	 * Creates the themes for the game.
	 * 
	 * @throws SlickException
	 *             generic Exception thrown by everything in the library
	 */
	public Themes() throws SlickException {
		// Initiate themes
		mainTheme = new Music(MAIN_THEME);
		gameplayTheme = new Music(GAMEPLAY_THEME);
	}
}