package de.tudarmstadt.informatik.fop.breakout.ui.settings;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import de.tudarmstadt.informatik.fop.breakout.ui.constants.OurGameParameters;

/**
 * This class represents the sounds played in the game.
 * 
 * @author Andrés Fraga Trespalacios, Farooq Khalil, Sebastian Sahner, </br>
 *         Timo Bochenko
 * 
 * @version 1.0
 */
public class Sounds implements OurGameParameters {

	// Variables
	/** Sound made when ball hits block. */
	public static Sound hitBlockSound;

	/** Sound made when ball destroys block. */
	public static Sound destroyedBlockSound;

	/** Sound made when ball hits Stick. */
	public static Sound hitStickSound;

	/** Sound made when 1 life left. */
	public static Sound heartBeatSound;

	/** Sound made when game lost. */
	public static Sound gameLostSound;

	/** Sound made when game won. */
	public static Sound gameWonSound;

	// CONSTRUCTOR
	/**
	 * Creates the sounds for the game.
	 * 
	 * @throws SlickException
	 *             generic Exception thrown by everything in the library
	 */
	public Sounds() throws SlickException {
		// Initiate sounds
		hitBlockSound = new Sound(HIT_BLOCK_SOUND);
		destroyedBlockSound = new Sound(DESTROYED_BLOCK_SOUND);
		hitStickSound = new Sound(HIT_STICK_SOUND);
		heartBeatSound = new Sound(HEARTBEAT_SOUND);
		gameLostSound = new Sound(GAME_LOST_SOUND);
		gameWonSound = new Sound(GAME_WON_SOUND);
	}
}
