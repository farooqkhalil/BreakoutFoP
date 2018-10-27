package de.tudarmstadt.informatik.fop.breakout.ui.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.ui.constants.OurGameParameters;
import de.tudarmstadt.informatik.fop.breakout.ui.helper.KeyEntity;
import de.tudarmstadt.informatik.fop.breakout.ui.settings.Design;
import de.tudarmstadt.informatik.fop.breakout.ui.settings.GameData;
import eea.engine.action.basicactions.ChangeStateAction;
import eea.engine.entity.StateBasedEntityManager;

/**
 * This class represents the Highscore window where the players with the highest
 * ranks are listed.
 * 
 * @author Andrés Fraga Trespalacios, Farooq Khalil, Sebastian Sahner, </br>
 *         Timo Bochenko
 * 
 * @version 2.1 - removed debug
 */
public class HighscoreState extends BasicGameState implements OurGameParameters {

	// VARIABLES
	/** ID for this BasicGameState. */
	private int stateID;

	/** The associated entity manager. */
	private StateBasedEntityManager entityManager;

	// CONSTRUCTOR
	/**
	 * Creates a new HighscoreState.
	 * 
	 * @param sid
	 *            the stateID
	 */
	public HighscoreState(int sid) {
		stateID = sid;
		entityManager = StateBasedEntityManager.getInstance();
	}

	// METHODS
	/** Initiates before the (first) run of this state. */
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

		// ********* BACKGROUND *********
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, Design.backgroundEntity);

		// ********* LOGO *********
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, Design.logoEntity);

		// ********* GREY BUBBLE *********
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, Design.greyBubbleEntity);

		// ********* HIGHSCORE *********
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, Design.highscoreEntity);

		// ********* ESCAPE *********
		// Create entity
		KeyEntity escapeEntity = new KeyEntity(ESCAPE_ID, Input.KEY_ESCAPE, new ChangeStateAction(MAINMENU_STATE));
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, escapeEntity);
	}

	/** Render this state to the game's graphics context. */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		// EntityManager renders all entities
		entityManager.renderEntities(container, game, g);

		// Draw animation
		Design.ballAnimation.draw(467, 12);

		// Draw highscoreList
		g.drawString(GameData.highscoreList.getListedNames(), 230, 280);
		g.drawString(GameData.highscoreList.getListedBlocks(), 435, 280);
		g.drawString(GameData.highscoreList.getListedTime(), 604, 280);
	}

	/** Update the state's logic based on the amount of time thats passed. */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

		// Add all scores to high score list
		GameData.highscoreList.loadScoreFile();
		
		// EntityManager updates all entities
		entityManager.updateEntities(container, game, delta);

		// Update animation
		Design.ballAnimation.update(delta);
	}

	@Override
	public int getID() {
		return stateID;
	}
}