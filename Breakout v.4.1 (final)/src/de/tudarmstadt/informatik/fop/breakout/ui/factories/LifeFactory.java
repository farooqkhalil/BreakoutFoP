package de.tudarmstadt.informatik.fop.breakout.ui.factories;

import org.newdawn.slick.geom.Vector2f;

import de.tudarmstadt.informatik.fop.breakout.ui.constants.OurGameParameters;
import de.tudarmstadt.informatik.fop.breakout.ui.settings.Design;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.interfaces.IEntityFactory;

/**
 * Factory for creating lives of the player. The player can have a total of 5
 * lives. In the beginning the player has 3 lives, the other two are not
 * visible. The number of lives can be increased with special items and
 * decreased if the ball leaves the bottom of the screen.
 * 
 * @author Andrés Fraga Trespalacios, Farooq Khalil, Sebastian Sahner, </br>
 *         Timo Bochenko
 * 
 * @version 1.0
 */
public class LifeFactory implements IEntityFactory, OurGameParameters {

	/** The type of life (First, Second etc.). */
	private LifeType type;

	/**
	 * Factory Constructor
	 * 
	 * @param type
	 *            determines the type of a created life (First, Second, Third,
	 *            Fourth or Fifth)
	 */
	public LifeFactory(LifeType type) {
		this.type = type;
	}

	@Override
	public Entity createEntity() {
		// Create entity
		Entity lifeEntity;

		// Initiate entity and add position/visibility
		switch (type) {
		case First:
			lifeEntity = new Entity(LIFE_1_ID);
			lifeEntity.setPosition(new Vector2f(22, WINDOW_HEIGHT / 12));
			break;
		case Second:
			lifeEntity = new Entity(LIFE_2_ID);
			lifeEntity.setPosition(new Vector2f(22 + LIFE_WIDTH, WINDOW_HEIGHT / 12));
			break;
		case Third:
			lifeEntity = new Entity(LIFE_3_ID);
			lifeEntity.setPosition(new Vector2f(22 + 2 * LIFE_WIDTH, WINDOW_HEIGHT / 12));
			break;
		case Fourth:
			lifeEntity = new Entity(LIFE_4_ID);
			lifeEntity.setPosition(new Vector2f(22 + 3 * LIFE_WIDTH, WINDOW_HEIGHT / 12));
			lifeEntity.setVisible(false);
			break;
		case Fifth:
			lifeEntity = new Entity(LIFE_5_ID);
			lifeEntity.setPosition(new Vector2f(22 + 4 * LIFE_WIDTH, WINDOW_HEIGHT / 12));
			lifeEntity.setVisible(false);
			break;
		default:
			return null;
		}

		// Add image to entity
		lifeEntity.addComponent(new ImageRenderComponent(Design.lifeImage));
		return lifeEntity;
	}
}