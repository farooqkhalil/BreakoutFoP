package de.tudarmstadt.informatik.fop.breakout.ui.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.ui.constants.OurGameParameters;
import de.tudarmstadt.informatik.fop.breakout.ui.settings.Design;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.Event;
import eea.engine.event.basicevents.KeyDownEvent;

/**
 * This class represents a stick entity.
 * 
 * @author Andrés Fraga Trespalacios, Farooq Khalil, Sebastian Sahner, </br>
 *         Timo Bochenko
 * 
 * @version 1.0
 */
public class StickEntity extends Entity implements OurGameParameters {

	// VARIABLES

	/** The imageComponent of the stick. */
	ImageRenderComponent imageComponent;

	// CONSTRUCTOR
	/**
	 * Creates a new stick entity.
	 * 
	 * @param entityID
	 *            the ID of the entity
	 * @throws SlickException
	 *             generic Exception thrown by everything in the library
	 */
	public StickEntity(String entityID) throws SlickException {
		super(entityID);
		// Create imageRenderComponent
		imageComponent = new ImageRenderComponent(Design.stickImage);
		// Add component to entity
		addComponent(new ImageRenderComponent(Design.stickImage));
		// Set position for entity
		setPosition(INITIAL_STICK_POSITION);
		// Add movements
		addComponent(moveLeft());
		addComponent(moveRight());
	}

	/**
	 * scales the width stick image.
	 * 
	 * @param scaleFactor
	 *            the new width
	 * 
	 * @throws SlickException
	 *             generic Exception thrown by everything in the library
	 */
	public void scaleWidth(float scaleFactor) {
		removeComponent(imageComponent);
		try {
			Design.stickImage = new Image(STICK_IMAGE).getScaledCopy((int) (Design.stickImage.getWidth() * scaleFactor),
					(int) STICK_HEIGHT);
		} catch (SlickException e) {
			System.err.println("Cannot find \"/images/stick.png\".");
			e.printStackTrace();
		}
		imageComponent = new ImageRenderComponent(Design.stickImage);
		addComponent(imageComponent);
	}

	// METHODS
	/**
	 * Adds a moveLeft action to a KeyDownEvent.
	 * 
	 * @return the modified event
	 */
	private Event moveLeft() {
		// Create move left event
		KeyDownEvent moveLeftEvent = new KeyDownEvent(Input.KEY_LEFT);
		// Add action to event
		moveLeftEvent.addAction(new Action() {
			@Override
			public void update(GameContainer container, StateBasedGame game, int delta, Component component) {
				// Move left as long as the screen is not left
				if (getPosition().getX() > Design.stickImage.getWidth() / 2) {
					setPosition(new Vector2f(getPosition().getX() - STICK_SPEED * delta, getPosition().getY()));
				}
			}
		});
		return moveLeftEvent;
	}

	/**
	 * Adds a moveRight action to a KeyDownEvent.
	 * 
	 * @return the modified event
	 */
	private Event moveRight() {
		// Create move right event
		KeyDownEvent moveRightEvent = new KeyDownEvent(Input.KEY_RIGHT);
		// Add action to event
		moveRightEvent.addAction(new Action() {
			@Override
			public void update(GameContainer container, StateBasedGame game, int delta, Component component) {
				// Move right as long as the screen is not left
				if (getPosition().getX() < WINDOW_WIDTH - Design.stickImage.getWidth() / 2) {
					setPosition(new Vector2f(getPosition().getX() + STICK_SPEED * delta, getPosition().getY()));
				}
			}
		});
		return moveRightEvent;
	}
}