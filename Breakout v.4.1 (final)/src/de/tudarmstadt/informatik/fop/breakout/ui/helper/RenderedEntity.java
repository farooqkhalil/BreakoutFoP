package de.tudarmstadt.informatik.fop.breakout.ui.helper;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import eea.engine.entity.Entity;

/**
 * This class represents an entity that can be rendered.
 * 
 * @author Andrés Fraga Trespalacios, Farooq Khalil, Sebastian Sahner, </br>
 *         Timo Bochenko
 * 
 * @version 1.0
 */
public class RenderedEntity extends Entity {

	// CONSTRUCTOR
	/**
	 * Creates a new rendered entity.
	 * 
	 * @param entityID
	 *            the ID of the entity
	 * @param position
	 *            the position of the entity
	 * @throws SlickException
	 *             generic Exception thrown by everything in the library
	 */
	public RenderedEntity(String entityID, Vector2f position) throws SlickException {
		super(entityID);
		// Set position for entity
		setPosition(new Vector2f(position));
	}
}