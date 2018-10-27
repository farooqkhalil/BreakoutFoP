package de.tudarmstadt.informatik.fop.breakout.ui;

import org.newdawn.slick.AppGameContainer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.ui.constants.OurGameParameters;
import de.tudarmstadt.informatik.fop.breakout.ui.settings.Design;
import de.tudarmstadt.informatik.fop.breakout.ui.settings.GameData;
import de.tudarmstadt.informatik.fop.breakout.ui.settings.Sounds;
import de.tudarmstadt.informatik.fop.breakout.ui.settings.Themes;
import de.tudarmstadt.informatik.fop.breakout.ui.states.CreditsState;
import de.tudarmstadt.informatik.fop.breakout.ui.states.GameLostState;
import de.tudarmstadt.informatik.fop.breakout.ui.states.GameModeState;
import de.tudarmstadt.informatik.fop.breakout.ui.states.GameWonState;
import de.tudarmstadt.informatik.fop.breakout.ui.states.GameplayState;
import de.tudarmstadt.informatik.fop.breakout.ui.states.HighscoreState;
import de.tudarmstadt.informatik.fop.breakout.ui.states.MainMenuState;
import eea.engine.entity.StateBasedEntityManager;

/**
 * This class starts the game "Breakout". It has 3 states foor the menu, the
 * actual game and the highscore.
 * 
 * @author Andrés Fraga Trespalacios, Farooq Khalil, Sebastian Sahner, </br>
 *         Timo Bochenko
 * 
 * @version 1.6 - moved lives to GameData initiated Sounds and GameData
 */
public class Breakout extends StateBasedGame implements OurGameParameters {

	// VARIABLES
	/** Used to define wheter debug mode is activated. */
	private static boolean debug;

	// CONSTRUCTOR
	/**
	 * Creates a new Breakout instance.
	 * 
	 * @param debug
	 *            if true, runs in debug mode
	 */
	public Breakout(boolean debug) {
		super("Breakout");
		Breakout.debug = debug;
		// Initiate GameData
		new GameData();
	}

	// GETTER & METHODS
	/**
	 * @return wether debug mode is active or not.
	 */
	public static boolean getDebug() {
		return debug;
	}

	/**
	 * Starts the game.
	 * 
	 * @param args
	 *            not used
	 * @throws SlickException
	 *             generic Exception thrown by everything in the library
	 */
	public static void main(String[] args) throws SlickException {

		// Set the library path depending on the operating system
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/native/windows");
		} else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
			System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/native/macosx");
		} else {
			System.setProperty("org.lwjgl.librarypath",
					System.getProperty("user.dir") + "/native/" + System.getProperty("os.name").toLowerCase());
		}

		// Add this StateBasedGame to an AppGameContainer
		AppGameContainer app = new AppGameContainer(new Breakout(false));

		// Set the display mode and frame rate
		app.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, false);
		app.setTargetFrameRate(FRAME_RATE);

		// Now start the game!
		app.start();
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {

		// Initiate Design
		new Design();
		// Initiate Acoustic
		new Sounds();
		new Themes();

		// Add the game states (the first added state will be started initially)
		addState(new MainMenuState(MAINMENU_STATE));
		addState(new GameplayState(GAMEPLAY_STATE));
		addState(new HighscoreState(HIGHSCORE_STATE));
		addState(new CreditsState(CREDITS_STATE));
		addState(new GameWonState(GAMEWON_STATE));
		addState(new GameLostState(GAMELOST_STATE));
		addState(new GameModeState(GAMEMODE_STATE));

		// Add the states to the StateBasedEntityManager
		StateBasedEntityManager.getInstance().addState(MAINMENU_STATE);
		StateBasedEntityManager.getInstance().addState(GAMEPLAY_STATE);
		StateBasedEntityManager.getInstance().addState(HIGHSCORE_STATE);
		StateBasedEntityManager.getInstance().addState(CREDITS_STATE);
		StateBasedEntityManager.getInstance().addState(GAMEWON_STATE);
		StateBasedEntityManager.getInstance().addState(GAMELOST_STATE);
		StateBasedEntityManager.getInstance().addState(GAMEMODE_STATE);
	}
}