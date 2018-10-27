package de.tudarmstadt.informatik.fop.breakout.ui.states;

import javax.swing.JOptionPane;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.ui.constants.OurGameParameters;
import de.tudarmstadt.informatik.fop.breakout.ui.highscore.Score;
import de.tudarmstadt.informatik.fop.breakout.ui.settings.Design;
import de.tudarmstadt.informatik.fop.breakout.ui.settings.GameData;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.basicevents.KeyDownEvent;

/**
 * This class represents the game won window where a message is shown as soon as
 * all the blocks were destroyed in the gameplay window.
 * 
 * @author Andrés Fraga Trespalacios, Farooq Khalil, Sebastian Sahner, </br>
 *         Timo Bochenko
 * 
 * @version 2.0 - removed debug
 */
public class GameWonState extends BasicGameState implements OurGameParameters {

	// VARIABLES
	/** ID for this BasicGameState. */
	private int stateID;

	/** The associated entity manager. */
	private StateBasedEntityManager entityManager;

	// CONSTRUCTOR
	/**
	 * Creates a new GameWonState.
	 * 
	 * @param sid
	 *            the stateID
	 */
	public GameWonState(int sid) {
		stateID = sid;
		entityManager = StateBasedEntityManager.getInstance();
	}

	// METHODS
	/** Initiates before the (first) run of this state. */
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

		// If arcade mode selected, currentState is this and level 9 was cleared
		if (GameData.gameMode.equals(ARCADE_ID) && game.getCurrentStateID() == 4 && GameData.level == 9) {
			// Show InputDialog for player's name
			String name = JOptionPane.showInputDialog("Please type your name");
			// Save score in highscoreList
			GameData.highscoreList.addNewScore(new Score(name, GameData.highscoreTime, GameData.highscoreBlocks));
			GameData.highscoreBlocks = 0;
			GameData.highscoreTime = 0;
		}

		// ********* BACKGROUND *********
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, Design.backgroundEntity);

		// ********* GAMEWON *********
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, Design.gameWonEntity);

		// ********* ENTER *********
		// Create entity
		Entity enterEntity = new Entity(ENTER_ID);
		// Create event
		KeyDownEvent keyEvent = new KeyDownEvent(Input.KEY_ENTER);
		// Add action to event
		keyEvent.addAction(new Action() {
			@Override
			public void update(GameContainer container, StateBasedGame game, int delta, Component component) {
				if (GameData.level == 9)
					GameData.level = 1;
				else
					GameData.level++;

				if (!GameData.gameMode.equals(SURVIVAL_ID))
					GameData.lives = 3;

				if (!GameData.gameMode.equals(RANDOM_ID) && GameData.level == 9)
					// ChangeStateInitAction
					game.enterState(MAINMENU_STATE);
				else
					// ChangeStateInitAction
					game.enterState(GAMEPLAY_STATE);
				try {
					game.init(container);
				} catch (SlickException e) {
					System.err.println("Cannot initiate container.");
					e.printStackTrace();
				}
			}
		});
		// Add event to entity
		enterEntity.addComponent(keyEvent);
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, enterEntity);
	}

	/** Render this state to the game's graphics context. */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		// EntityManager renders all entities
		entityManager.renderEntities(container, game, g);

		// Draw message with commands
		g.drawString(GAME_ENDED_COMMANDS, WINDOW_WIDTH / 2 - g.getFont().getWidth(GAME_ENDED_COMMANDS) / 2,
				WINDOW_HEIGHT * 0.925f);
	}

	/** Update the state's logic based on the amount of time thats passed. */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

		// EntityManager updates all entities
		entityManager.updateEntities(container, game, delta);
	}

	@Override
	public int getID() {
		return stateID;
	}
}