package de.tudarmstadt.informatik.fop.breakout.ui.objects;

import eea.engine.entity.Entity;
import de.tudarmstadt.informatik.fop.breakout.ui.constants.OurGameParameters.LifeType;
import de.tudarmstadt.informatik.fop.breakout.ui.factories.LifeFactory;
import de.tudarmstadt.informatik.fop.breakout.ui.settings.GameData;

/**
 * This class represents the lives of the player.
 * 
 * @author Andrés Fraga Trespalacios, Farooq Khalil, Sebastian Sahner, </br>
 *         Timo Bochenko
 * 
 * @version 1.1 - added updateLifeImgs() from GameplayState
 */
public interface LifeEntities {

	// VARIABLES
	/** Life 1 entity. */
	public Entity life1Entity = new LifeFactory(LifeType.First).createEntity();

	/** Life 2 entity. */
	public Entity life2Entity = new LifeFactory(LifeType.Second).createEntity();

	/** Life 3 entity. */
	public Entity life3Entity = new LifeFactory(LifeType.Third).createEntity();

	/** Life 4 entity. */
	public Entity life4Entity = new LifeFactory(LifeType.Fourth).createEntity();

	/** Life 5 entity. */
	public Entity life5Entity = new LifeFactory(LifeType.Fifth).createEntity();

	/**
	 * Updates the life images by setting the visibility on/off.
	 */
	public static void updateLifeImgs() {
		switch (GameData.lives) {
		case 5:
			life5Entity.setVisible(true);
			break;
		case 4:
			life5Entity.setVisible(false);
			life4Entity.setVisible(true);
			break;
		case 3:
			life5Entity.setVisible(false);
			life4Entity.setVisible(false);
			life3Entity.setVisible(true);
			life2Entity.setVisible(true);
			break;
		case 2:
			life3Entity.setVisible(false);
			life2Entity.setVisible(true);
			break;
		case 1:
			life2Entity.setVisible(false);
			break;
		}
	}
}