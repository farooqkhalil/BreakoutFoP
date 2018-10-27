package de.tudarmstadt.informatik.fop.breakout.ui.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.ui.constants.OurGameParameters;
import de.tudarmstadt.informatik.fop.breakout.ui.helper.GameModeEntryEntity;
import de.tudarmstadt.informatik.fop.breakout.ui.helper.KeyEntity;
import de.tudarmstadt.informatik.fop.breakout.ui.settings.Design;
import eea.engine.action.basicactions.ChangeStateAction;
import eea.engine.entity.StateBasedEntityManager;

/**
 * This class represents the Game mode window where the player can select
 * between different game modes.
 * 
 * @author Andrés Fraga Trespalacios, Farooq Khalil, Sebastian Sahner, </br>
 *         Timo Bochenko
 * 
 * @version 1.0
 */
public class GameModeState extends BasicGameState implements OurGameParameters {

	// VARIABLES
	/** ID for this BasicGameState. */
	private int stateID;

	/** The associated entity manager. */
	private StateBasedEntityManager entityManager;

	// CONSTRUCTOR
	/**
	 * Creates a new GameModeState.
	 * 
	 * @param sid
	 *            the stateID
	 */
	public GameModeState(int sid) {
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

		// ********* BLACK BUBBLES *********
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, Design.leftBubbleEntity);
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, Design.centerBubbleEntity);
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, Design.rightBubbleEntity);

		// *******************************************************
		// ************************ MODES ************************
		// *******************************************************

		// ********* ARCADE *********
		// Create Entity
		GameModeEntryEntity arcadeModeEntity = new GameModeEntryEntity(ARCADE_ID);
		// Set position for entity
		arcadeModeEntity.setPosition(new Vector2f(Design.leftBubbleEntity.getPosition().getX(),
				Design.leftBubbleEntity.getPosition().getY() - 300 / 2 + MENU_DISTANCE / 1.5f));
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, arcadeModeEntity);

		// ********* RANDOM *********
		// Create Entity
		GameModeEntryEntity randomModeEntity = new GameModeEntryEntity(RANDOM_ID);
		// Set position for entity
		randomModeEntity.setPosition(new Vector2f(Design.centerBubbleEntity.getPosition().getX(),
				Design.centerBubbleEntity.getPosition().getY() - 300 / 2 + MENU_DISTANCE / 1.5f));
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, randomModeEntity);

		// ********* SURVIVAL *********
		// Create Entity
		GameModeEntryEntity survivalModeEntity = new GameModeEntryEntity(SURVIVAL_ID);
		// Set position for entity
		survivalModeEntity.setPosition(new Vector2f(Design.rightBubbleEntity.getPosition().getX(),
				Design.rightBubbleEntity.getPosition().getY() - 300 / 2 + MENU_DISTANCE / 1.5f));
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, survivalModeEntity);

		// ********* GAME MODE *********
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, Design.gameModeEntity);

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
	}

	/** Update the state's logic based on the amount of time thats passed. */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

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