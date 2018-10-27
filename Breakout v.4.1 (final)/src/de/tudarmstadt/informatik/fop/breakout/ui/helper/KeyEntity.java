package de.tudarmstadt.informatik.fop.breakout.ui.helper;

import de.tudarmstadt.informatik.fop.breakout.ui.constants.OurGameParameters;
import eea.engine.action.Action;
import eea.engine.entity.Entity;
import eea.engine.event.basicevents.KeyDownEvent;

/**
 * This class represents a key entity which has a KeyPressedEvent and the
 * according action.
 * 
 * @author Andrés Fraga Trespalacios, Farooq Khalil, Sebastian Sahner, </br>
 *         Timo Bochenko
 * 
 * @version 1.3 - removed contructor with theme change
 */
public class KeyEntity extends Entity implements OurGameParameters {

	// CONSTRUCTOR
	/**
	 * Creates a new KeyEntity
	 * 
	 * @param entityID
	 *            the ID of the entity
	 * @param keyID
	 *            the ID of the input
	 * @param act
	 *            the action that should be done when key is pressed
	 */
	public KeyEntity(String entityID, int keyID, Action act) {
		super(entityID);
		// Create event
		KeyDownEvent keyEvent = new KeyDownEvent(keyID);
		// Add action to event
		keyEvent.addAction(act);
		// Add event to entity
		addComponent(keyEvent);
	}
}