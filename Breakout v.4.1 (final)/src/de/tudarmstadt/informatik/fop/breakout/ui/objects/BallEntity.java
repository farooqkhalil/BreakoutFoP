package de.tudarmstadt.informatik.fop.breakout.ui.objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import de.tudarmstadt.informatik.fop.breakout.ui.Breakout;
import de.tudarmstadt.informatik.fop.breakout.ui.constants.OurGameParameters;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;

/**
 * This class represents a ball entity.
 * 
 * @author Andrés Fraga Trespalacios, Farooq Khalil, Sebastian Sahner, </br>
 *         Timo Bochenko
 * 
 * @version 1.4 - added destroyed blocks
 */
public class BallEntity extends Entity implements OurGameParameters {

	// VARIABLES
	/** Counter for blocks the ball destroyed. */
	private int destroyedBlocks;

	// CONSTRUCTOR
	/**
	 * Creates a new ball entity.
	 * 
	 * @param entityID
	 *            the ID of the entity
	 * @throws SlickException
	 *             generic Exception thrown by everything in the library
	 */
	public BallEntity(String entityID) throws SlickException {
		super(entityID);
		// If debug not activated
		if (!Breakout.getDebug()) {
			// Add image to entity
			addComponent(new ImageRenderComponent(new Image(BALL_IMAGE)));
		}
		// Set position for entity
		setPosition(new Vector2f(WINDOW_WIDTH / 2, WINDOW_HEIGHT - STICK_HEIGHT - getSize().getY() / 2));
	}

	// GETTER & METHODS
	/**
	 * @return the number of destroyed blocks
	 */
	public int getDestroyedBlocks() {
		return destroyedBlocks;
	}

	/**
	 * Increases the number of destroyed blocks by 1.
	 */
	public void increaseDestroyedBlocks() {
		destroyedBlocks++;
	}
}