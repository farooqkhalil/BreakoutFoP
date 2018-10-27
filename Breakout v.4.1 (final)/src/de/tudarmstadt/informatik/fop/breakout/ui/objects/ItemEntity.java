package de.tudarmstadt.informatik.fop.breakout.ui.objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import de.tudarmstadt.informatik.fop.breakout.ui.constants.OurGameParameters;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;

/**
 * This class represents an item entity.
 * 
 * @author Andrés Fraga Trespalacios, Farooq Khalil, Sebastian Sahner, </br>
 *         Timo Bochenko
 * 
 * @version 1.0
 */
public class ItemEntity extends Entity implements OurGameParameters {

	// VARIABLES
	/** String for the type of item. */
	public String itemKind;

	// CONSTRUCTOR
	/**
	 * Creates a new item entity.
	 * 
	 * @param entityID
	 *            the ID of the entity
	 * @param itemKind
	 *            determines the type of item
	 */
	public ItemEntity(String entityID, String itemKind) {
		super(entityID);
		// Save ItemKind
		this.itemKind = itemKind;
		// Create image
		Image itemImage = itemImage();
		// Add image to entity
		addComponent(new ImageRenderComponent(itemImage));
	}

	// GETTER
	/**
	 * @return the itemKind
	 */
	public String getItemKind() {
		return itemKind;
	}

	// METHOD
	/**
	 * @return an itemImage according to the itemKind
	 */
	private Image itemImage() {
		// Create image
		Image itemImage = null;

		// Set image according to itemKind
		switch (itemKind) {
		case "bigger":
			try {
				itemImage = new Image(ITEM_BIGGER_IMAGE);
			} catch (SlickException e) {
				System.err.println("Cannot find \"/images/bigger.png\".");
				e.printStackTrace();
			}
			break;
		case "smaller":
			try {
				itemImage = new Image(ITEM_SMALLER_IMAGE);
			} catch (SlickException e) {
				System.err.println("Cannot find \"/images/smaller.png\".");
				e.printStackTrace();
			}
			break;
		case "faster":
			try {
				itemImage = new Image(ITEM_FASTER_IMAGE);
			} catch (SlickException e) {
				System.err.println("Cannot find \"/images/faster.png\".");
				e.printStackTrace();
			}
			break;
		case "slower":
			try {
				itemImage = new Image(ITEM_SLOWER_IMAGE);
			} catch (SlickException e) {
				System.err.println("Cannot find \"/images/slower.png\".");
				e.printStackTrace();
			}
			break;
		case "life":
			try {
				itemImage = new Image(LIFE_IMAGE).getScaledCopy(2f);
			} catch (SlickException e) {
				System.err.println("Cannot find \"/images/life.png\".");
				e.printStackTrace();
			}
			break;
		}
		return itemImage;
	}
}