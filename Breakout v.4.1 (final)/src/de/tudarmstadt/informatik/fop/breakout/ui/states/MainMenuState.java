package de.tudarmstadt.informatik.fop.breakout.ui.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.ui.Breakout;
import de.tudarmstadt.informatik.fop.breakout.ui.constants.OurGameParameters;
import de.tudarmstadt.informatik.fop.breakout.ui.helper.EntryEntity;
import de.tudarmstadt.informatik.fop.breakout.ui.helper.RenderedEntity;
import de.tudarmstadt.informatik.fop.breakout.ui.settings.Design;
import de.tudarmstadt.informatik.fop.breakout.ui.settings.Themes;
import eea.engine.action.Action;
import eea.engine.action.basicactions.ChangeStateAction;
import eea.engine.action.basicactions.QuitAction;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.basicevents.MouseEnteredEvent;

/**
 * This class represents the Main Menu window where the player can select
 * between different menus and see the "Credits ...".
 * 
 * @author Andrés Fraga Trespalacios, Farooq Khalil, Sebastian Sahner, </br>
 *         Timo Bochenko
 * 
 * @version 2.2 - removed debug
 */
public class MainMenuState extends BasicGameState implements OurGameParameters {

	// VARIABLES
	/** ID for this BasicGameState. */
	private int stateID;

	/** The associated entity manager. */
	private StateBasedEntityManager entityManager;

	// CONSTRUCTOR
	/**
	 * Creates a new MainMenuState.
	 * 
	 * @param sid
	 *            the stateID
	 */
	public MainMenuState(int sid) {
		stateID = sid;
		entityManager = StateBasedEntityManager.getInstance();
	}

	// METHODS
	/** Initiates before the (first) run of this state. */
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

		// ********* MUSIC *********
		// Activate Music
		if (!container.isMusicOn())
			container.setMusicOn(true);
		// Loop the theme
		Themes.mainTheme.loop(1f, 0.1f);

		// ********* BACKGROUND *********
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, Design.backgroundEntity);

		// ********* LOGO *********
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, Design.logoEntity);

		// ********* GREY BUBBLE *********
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, Design.greyBubbleEntity);

		// ********* BLACK BUBBLE *********
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, Design.blackBubbleEntity);

		// *******************************************************
		// *********************** ENTRIES ***********************
		// *******************************************************

		// ********* NEW GAME *********
		// Create Entity
		EntryEntity newGameEntryEntity = new EntryEntity(NEW_GAME_MENU_ID, new ChangeStateAction(GAMEMODE_STATE));
		// Set position for entity
		newGameEntryEntity.setPosition(new Vector2f(Design.blackBubbleEntity.getPosition().getX(),
				Design.blackBubbleEntity.getPosition().getY() - BLACK_BUBBLE_HEIGHT / 2 + MENU_DISTANCE / 1.5f));
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, newGameEntryEntity);

		// ********* HIGHSCORE *********
		// Create Entity
		EntryEntity highscoreEntryEntity = new EntryEntity(HIGHSCORE_MENU_ID, new ChangeStateAction(HIGHSCORE_STATE));
		// Set position for entity
		highscoreEntryEntity.setPosition(new Vector2f(newGameEntryEntity.getPosition().getX(),
				newGameEntryEntity.getPosition().getY() + MENU_DISTANCE));
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, highscoreEntryEntity);

		// ********* CREDITS *********
		// Create Entity
		EntryEntity creditsEntryEntity = new EntryEntity(CREDITS_MENU_ID, new ChangeStateAction(CREDITS_STATE));
		// Set position for entity
		creditsEntryEntity.setPosition(new Vector2f(highscoreEntryEntity.getPosition().getX(),
				highscoreEntryEntity.getPosition().getY() + MENU_DISTANCE));
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, creditsEntryEntity);

		// ********* QUIT *********
		// Create Entity
		EntryEntity quitEntryEntity = new EntryEntity(QUIT_MENU_ID, new QuitAction());
		// Set position for entity
		quitEntryEntity.setPosition(new Vector2f(creditsEntryEntity.getPosition().getX(),
				creditsEntryEntity.getPosition().getY() + MENU_DISTANCE));
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, quitEntryEntity);

		// *******************************************************
		// ******************** INFO ENTITIES ********************
		// *******************************************************

		// ********* NEW GAME INFO *********
		// Create infoBox
		Entity newGameInfoEntity = infoEntity(newGameEntryEntity, NEW_GAME_INFO_ID, Design.newGameInfoImage);
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, newGameInfoEntity);

		// ********* HIGHSCORE INFO *********
		// Create infoBox
		Entity highscoreInfoEntity = infoEntity(highscoreEntryEntity, HIGHSCORE_INFO_ID, Design.highscoreInfoImage);
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, highscoreInfoEntity);

		// ********* CREDITS INFO *********
		// Create InfoEntity
		Entity creditsInfoEntity = infoEntity(creditsEntryEntity, CREDITS_INFO_ID, Design.creditsInfoImage);
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, creditsInfoEntity);

		// ********* QUIT INFO *********
		// Create infoBox
		Entity quitInfoEntity = infoEntity(quitEntryEntity, QUIT_INFO_ID, Design.quitInfoImage);
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, quitInfoEntity);

		// Hide all info boxes
		hideAllBoxes();
	}

	/** Render this state to the game's graphics context. */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		// EntityManager renders all entities
		entityManager.renderEntities(container, game, g);

		// Draw animation
		Design.ballAnimation.draw(467, 12);

		// Draw menu text over entries
		g.drawString("Neues Spiel", entityManager.getEntity(stateID, NEW_GAME_MENU_ID).getPosition().getX() * 0.75f,
				entityManager.getEntity(stateID, NEW_GAME_MENU_ID).getPosition().getY() - 9);
		g.drawString("Highscore", entityManager.getEntity(stateID, HIGHSCORE_MENU_ID).getPosition().getX() * 0.75f,
				entityManager.getEntity(stateID, HIGHSCORE_MENU_ID).getPosition().getY() - 9);
		g.drawString("Credits...", entityManager.getEntity(stateID, CREDITS_MENU_ID).getPosition().getX() * 0.75f,
				entityManager.getEntity(stateID, CREDITS_MENU_ID).getPosition().getY() - 9);
		g.drawString("Beenden", entityManager.getEntity(stateID, QUIT_MENU_ID).getPosition().getX() * 0.75f,
				entityManager.getEntity(stateID, QUIT_MENU_ID).getPosition().getY() - 9);
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

	// HELPER
	/**
	 * Creates an infoEntity for the given menu entity.
	 * 
	 * @param MenuEntity
	 *            the given menu entity
	 * @param infoID
	 *            the ID for a new info entity
	 * @param infoImage
	 *            the image for the new info entity
	 * @return the infoEntity
	 * 
	 * @throws SlickException
	 *             generic Exception thrown by everything in the library
	 */
	public Entity infoEntity(Entity MenuEntity, String infoID, Image infoImage) throws SlickException {
		// Create entity
		Entity infoEntity = new RenderedEntity(infoID, new Vector2f(INFO_BOX_POSITION));
		// If debug not activated
		if (!Breakout.getDebug())
			// Add image to entity
			infoEntity.addComponent(new ImageRenderComponent(infoImage));
		// Create event
		MouseEnteredEvent hover = new MouseEnteredEvent();
		// Add action to event
		hover.addAction(new Action() {
			@Override
			public void update(GameContainer container, StateBasedGame game, int delta, Component event) {
				// Hides all the boxes in the entityManager, this included
				hideAllBoxes();
				// Makes this box visible
				infoEntity.setVisible(true);
			}
		});
		// Add event to entity
		MenuEntity.addComponent(hover);
		return infoEntity;
	}

	/**
	 * Hides all the info boxes that were added to the entityMager thus far.
	 */
	public void hideAllBoxes() {
		if (entityManager.hasEntity(stateID, NEW_GAME_INFO_ID))
			entityManager.getEntity(stateID, NEW_GAME_INFO_ID).setVisible(false);
		if (entityManager.hasEntity(stateID, HIGHSCORE_INFO_ID))
			entityManager.getEntity(stateID, HIGHSCORE_INFO_ID).setVisible(false);
		if (entityManager.hasEntity(stateID, CREDITS_INFO_ID))
			entityManager.getEntity(stateID, CREDITS_INFO_ID).setVisible(false);
		if (entityManager.hasEntity(stateID, QUIT_INFO_ID))
			entityManager.getEntity(stateID, QUIT_INFO_ID).setVisible(false);
	}
}