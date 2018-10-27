package de.tudarmstadt.informatik.fop.breakout.ui.helper;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.ui.settings.GameData;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.event.ANDEvent;
import eea.engine.event.Event;
import eea.engine.event.basicevents.MouseClickedEvent;
import eea.engine.event.basicevents.MouseEnteredEvent;

/**
 * This class represents the entry entities in GameModeState.
 * 
 * @author Andrés Fraga Trespalacios, Farooq Khalil, Sebastian Sahner, </br>
 *         Timo Bochenko
 * 
 * @version 1.1 - removed theme loop in onClickAction()
 */
public class GameModeEntryEntity extends EntryEntity {

	// CONSTRUCTOR
	/**
	 * Creates a new GameModeEntity.
	 * 
	 * @param entityID
	 *            the ID of the entity
	 * 
	 * @throws SlickException
	 *             generic Exception thrown by everything in the library
	 */
	public GameModeEntryEntity(String entityID) throws SlickException {
		super(entityID);
		// Add onClickAction
		addComponent(onClickAction(entityID));
	}

	/**
	 * Creates an onClick event based on the given gameMode and adds actions.
	 * 
	 * @param gameMode
	 *            the given gameMode
	 * @return the onClick event
	 */
	private Event onClickAction(String gameMode) {
		// Create event
		ANDEvent onClick = new ANDEvent(new MouseEnteredEvent(), new MouseClickedEvent());
		// Add action to event
		onClick.addAction(new Action() {
			@Override
			public void update(GameContainer container, StateBasedGame game, int delta, Component component) {
				// Set the gameMode
				GameData.gameMode = gameMode;
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
		return onClick;
	}
}