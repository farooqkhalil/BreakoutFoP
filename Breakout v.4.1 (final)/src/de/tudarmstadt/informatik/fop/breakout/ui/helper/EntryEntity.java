package de.tudarmstadt.informatik.fop.breakout.ui.helper;

import org.newdawn.slick.SlickException;

import de.tudarmstadt.informatik.fop.breakout.ui.constants.OurGameParameters;
import de.tudarmstadt.informatik.fop.breakout.ui.settings.Design;
import eea.engine.action.Action;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.ANDEvent;
import eea.engine.event.Event;
import eea.engine.event.basicevents.MouseClickedEvent;
import eea.engine.event.basicevents.MouseEnteredEvent;

/**
 * This class represents a clickable entry entity.
 * 
 * @author Andrés Fraga Trespalacios, Farooq Khalil, Sebastian Sahner, </br>
 *         Timo Bochenko
 * 
 * @version 1.3 - renamed the class, removed constructur with theme change and
 *          according onClick-Method, added new constructor with a given String
 */
public class EntryEntity extends Entity implements OurGameParameters {

	// CONSTRUCTOR
	/**
	 * Creates a new EntryEntity.
	 * 
	 * @param entityID
	 *            the ID of the entity
	 * @param act
	 *            the action that should be done when entity is clicked on
	 * @throws SlickException
	 *             generic Exception thrown by everything in the library
	 */
	public EntryEntity(String entityID) throws SlickException {
		super(entityID);
		// Add image to entity
		addComponent(new ImageRenderComponent(Design.entryImage));
	}

	/**
	 * Creates a new EntryEntity.
	 * 
	 * @param entityID
	 *            the ID of the entity
	 * @param act
	 *            the action that should be done when entity is clicked on
	 * @throws SlickException
	 *             generic Exception thrown by everything in the library
	 */
	public EntryEntity(String entityID, Action act) throws SlickException {
		super(entityID);
		// Add image to entity
		addComponent(new ImageRenderComponent(Design.entryImage));
		// Add onClickAction
		addComponent(onClickAction(act));
	}

	// METHOD
	/**
	 * Creates an onClick event and adds an action to it.
	 * 
	 * @param act
	 *            the action to be performed
	 * @return the onClick event
	 */
	private Event onClickAction(Action act) {
		// Create event
		ANDEvent onClick = new ANDEvent(new MouseEnteredEvent(), new MouseClickedEvent());
		// Add action to event
		onClick.addAction(act);
		// return event
		return onClick;
	}
}