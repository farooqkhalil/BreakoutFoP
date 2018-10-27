package de.tudarmstadt.informatik.fop.breakout.ui.settings;

import de.tudarmstadt.informatik.fop.breakout.ui.highscore.HighscoreList;

/**
 * This class represents the game data including lives etc..
 * 
 * @author Andrés Fraga Trespalacios, Farooq Khalil, Sebastian Sahner, </br>
 *         Timo Bochenko
 * 
 * @version 1.1 - added highscoreList and elapsedTime
 */
public class GameData {

	// VARIABLES
	/** The selected game mode. */
	public static String gameMode;

	/** Whether the game has started. */
	public static boolean hasStarted;

	/** The level of the map. */
	public static int level;

	/** The lives the player has. */
	public static int lives;

	/** The elapsed time. */
	public static long elapsedTime;

	/** The accumulated elapsed time of all the cleared maps. */
	public static long highscoreTime;

	/** Counter for blocks the ball destroyed. */
	public static int destroyedBlocks;

	/** The accumulated destroyed blocks of all the cleared maps. */
	public static int highscoreBlocks;

	/** The highscoreList. */
	public static HighscoreList highscoreList;

	// CONSTRUCTOR
	/**
	 * Creates a new game data.
	 */
	public GameData() {
		// Initiate Variables
		gameMode = "";
		hasStarted = false;
		level = 1;
		lives = 3;
		elapsedTime = 0;
		highscoreTime = 0;
		destroyedBlocks = 0;
		highscoreBlocks = 0;
		highscoreList = new HighscoreList();
	}
}
