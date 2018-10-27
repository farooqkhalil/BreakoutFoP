package de.tudarmstadt.informatik.fop.breakout.ui.objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import de.tudarmstadt.informatik.fop.breakout.interfaces.IHitable;
import de.tudarmstadt.informatik.fop.breakout.ui.Breakout;
import de.tudarmstadt.informatik.fop.breakout.ui.constants.OurGameParameters;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;

/**
 * This class represents a block entity.
 * 
 * @author Andrés Fraga Trespalacios, Farooq Khalil, Sebastian Sahner, </br>
 *         Timo Bochenko
 * 
 * @version 1.1 - made block to an entity, removed IEntityFacotry, added
 *          updateBlockImage()
 */
public class BlockEntity extends Entity implements IHitable, OurGameParameters {

	// VARIABLES
	/** The hits needed to destroy the block. */
	private int hits;

	/** The imageComponent of the block. */
	ImageRenderComponent imageComponent;

	// CONSTRUCTOR
	/**
	 * Creates a new block entity.
	 * 
	 * @param entityID
	 *            the ID of the entity
	 * @param hits
	 *            the hits needed to destroy the block
	 */
	public BlockEntity(String entityID, int hits) {
		super(entityID);
		// Save hits
		this.hits = hits;
		// If debug not activated
		if (!Breakout.getDebug()) {
			// Create image
			Image blockImage = blockImage();
			// If blockImage is not null
			if (blockImage != null) {
				// Create imageRenderComponent
				imageComponent = new ImageRenderComponent(blockImage);
				// Add component to entity
				addComponent(imageComponent);
			}
		}
	}

	@Override
	public void setHitsLeft(int value) {
		hits = value;

	}

	@Override
	public int getHitsLeft() {
		return hits;
	}

	@Override
	public void addHitsLeft(int value) {
		hits += value;

	}

	@Override
	public boolean hasHitsLeft() {
		if (hits > 0)
			return true;
		return false;
	}

	/**
	 * @return a blockImage according to the hits
	 */
	private Image blockImage() {

		// Create image
		Image blockImage = null;

		// Set image according to hits
		switch (hits) {
		case 1:
			try {
				blockImage = new Image(BLOCK_1_IMAGE);
			} catch (SlickException e) {
				System.err.println("Cannot find \"/images/block_1.png\".");
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				blockImage = new Image(BLOCK_2_IMAGE);
			} catch (SlickException e) {
				System.err.println("Cannot find \"/images/block_2.png\".");
				e.printStackTrace();
			}
			break;
		case 3:
			try {
				blockImage = new Image(BLOCK_3_IMAGE);
			} catch (SlickException e) {
				System.err.println("Cannot find \"/images/block_3.png\".");
				e.printStackTrace();
			}
			break;
		case 4:
			try {
				blockImage = new Image(BLOCK_4_IMAGE);
			} catch (SlickException e) {
				System.err.println("Cannot find \"/images/block_4.png\".");
				e.printStackTrace();
			}
			break;
		}
		return blockImage;
	}

	/**
	 * Updates the block image. Used after hits were reduced by 1.
	 */
	public void updateBlockImage() {
		removeComponent(imageComponent);
		imageComponent = new ImageRenderComponent(blockImage());
		addComponent(imageComponent);
	}
}