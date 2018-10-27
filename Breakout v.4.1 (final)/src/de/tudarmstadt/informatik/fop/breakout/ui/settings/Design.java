package de.tudarmstadt.informatik.fop.breakout.ui.settings;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

import de.tudarmstadt.informatik.fop.breakout.ui.Breakout;
import de.tudarmstadt.informatik.fop.breakout.ui.constants.OurGameParameters;
import de.tudarmstadt.informatik.fop.breakout.ui.helper.RenderedEntity;
import eea.engine.component.render.ImageRenderComponent;

/**
 * This class represents the frequently used design components of the game.
 * 
 * @author Andrés Fraga Trespalacios, Farooq Khalil, Sebastian Sahner, </br>
 *         Timo Bochenko
 * 
 * @version 1.0
 */
public class Design implements OurGameParameters {

	// IMAGES
	/** The image for highscore. */
	public static Image highscoreImage;

	/** The image for credits. */
	public static Image creditsImage;

	/** The image for game mode. */
	public static Image gameModeImage;

	/** The image for game won. */
	public static Image gameWonImage;

	/** The image for game lost. */
	public static Image gameLostImage;

	/** The image for background. */
	public static Image backgroundImage;

	/** The image for game background. */
	public static Image gameBackgroundImage;

	/** The image for logo. */
	public static Image logoImage;

	/** The image for the grey bubble. */
	public static Image greyBubbleImage;

	/** The image for the black bubble. */
	public static Image blackBubbleImage;

	/** The image for the black bubbles in game mode. */
	public static Image blackBubbleGMImage;

	/** The image for the entries. */
	public static Image entryImage;

	/** The image for new game info. */
	public static Image newGameInfoImage;

	/** The image for highscore info. */
	public static Image highscoreInfoImage;

	/** The image for credits info. */
	public static Image creditsInfoImage;

	/** The image for quit info. */
	public static Image quitInfoImage;

	/** The image of the stick. */
	public static Image stickImage;

	/** The image of the lifes. */
	public static Image lifeImage;

	/** The image for pause. */
	public static Image pauseImage;

	// RENDERED ENTITIES
	/** The credits entity. */
	public static RenderedEntity creditsEntity;

	/** The highscore entity. */
	public static RenderedEntity highscoreEntity;

	/** The game mode entity. */
	public static RenderedEntity gameModeEntity;

	/** The game won entity. */
	public static RenderedEntity gameWonEntity;

	/** The game lost entity. */
	public static RenderedEntity gameLostEntity;

	/** The background entity. */
	public static RenderedEntity backgroundEntity;

	/** The game background entity. */
	public static RenderedEntity gameBackgroundEntity;

	/** The logo entity. */
	public static RenderedEntity logoEntity;

	/** The grey bubble entity. */
	public static RenderedEntity greyBubbleEntity;

	/** The black bubble entity. */
	public static RenderedEntity blackBubbleEntity;

	/** The left black bubble entity in GameModeState. */
	public static RenderedEntity leftBubbleEntity;

	/** The centered black bubble entity in GameModeState. */
	public static RenderedEntity centerBubbleEntity;

	/** The right black bubble entity in GameModeState. */
	public static RenderedEntity rightBubbleEntity;

	/** The pause entity. */
	public static RenderedEntity pauseEntity;

	// BALL ANIMATION
	/** Ball spritesheet. */
	private SpriteSheet ballSheet;

	/** Ball animation. */
	public static Animation ballAnimation;

	// CONSTRUCTOR
	/**
	 * Creates the Design for the game.
	 * 
	 * @throws SlickException
	 *             generic Exception thrown by everything in the library
	 */
	public Design() throws SlickException {
		// Initiate RenderedEntities;
		initiateRenderedEntites();
		// If debug not activated
		if (!Breakout.getDebug()) {
			// Initiate images
			initiateImages();
			// Add image to entites
			addImageToEntites();
			// Initiate ball animation
			initiateBallAnimation();
		}
	}

	// METHODS
	/**
	 * Initiates the images.
	 * 
	 * @throws SlickException
	 *             generic Exception thrown by everything in the library
	 */
	private void initiateImages() throws SlickException {
		highscoreImage = new Image(HIGHSCORE_IMAGE);
		creditsImage = new Image(CREDITS_IMAGE);
		gameWonImage = new Image(GAME_WON_IMAGE);
		gameLostImage = new Image(GAME_LOST_IMAGE);
		backgroundImage = new Image(BACKGROUND_IMAGE);
		gameBackgroundImage = new Image(GAME_BACKGROUND_IMAGE);
		logoImage = new Image(LOGO_IMAGE);
		greyBubbleImage = new Image(GREY_BUBBLE_IMAGE);
		blackBubbleImage = new Image(BLACK_BUBBLE_IMAGE);
		blackBubbleGMImage = new Image(BLACK_BUBBLE_IMAGE).getScaledCopy(200, 300);
		entryImage = new Image(ENTRY_IMAGE);
		newGameInfoImage = new Image(NEW_GAME_INFO_IMAGE);
		highscoreInfoImage = new Image(HIGHSCORE_INFO_IMAGE);
		creditsInfoImage = new Image(CREDITS_INFO_IMAGE);
		quitInfoImage = new Image(QUIT_INFO_IMAGE);
		gameModeImage = new Image(GAME_MODE_IMAGE);
		stickImage = new Image(STICK_IMAGE);
		lifeImage = new Image(LIFE_IMAGE);
		pauseImage = new Image(PAUSE_IMAGE);
	}

	/**
	 * Initites the ball animation.
	 * 
	 * @throws SlickException
	 *             generic Exception thrown by everything in the library
	 */
	private void initiateBallAnimation() throws SlickException {
		// Source, width/height of frames in sprite
		ballSheet = new SpriteSheet(LOGO_BALL_SPRITE, 100, 120);
		// Speed of animation
		ballAnimation = new Animation(ballSheet, 115);
	}

	/**
	 * Initiates the frequently used RenderedEntities.
	 * 
	 * @throws SlickException
	 *             generic Exception thrown by everything in the library
	 */
	private void initiateRenderedEntites() throws SlickException {
		highscoreEntity = new RenderedEntity(HIGHSCORE_ID, GREY_BUBBLE_POSITION);
		creditsEntity = new RenderedEntity(CREDITS_ID, GREY_BUBBLE_POSITION);
		gameModeEntity = new RenderedEntity(GAME_MODE_ID, GREY_BUBBLE_POSITION);
		gameWonEntity = new RenderedEntity(GAME_WON_ID, new Vector2f(WINDOW_WIDTH / 2, GAME_WON_HEIGHT / 2));
		gameLostEntity = new RenderedEntity(GAME_LOST_ID, new Vector2f(WINDOW_WIDTH / 2, GAME_LOST_HEIGHT / 2));
		backgroundEntity = new RenderedEntity(BACKGROUND_ID, BACKGROUND_POSITION);
		gameBackgroundEntity = new RenderedEntity(BACKGROUND_ID, BACKGROUND_POSITION);
		logoEntity = new RenderedEntity(LOGO_ID, LOGO_POSITION);
		greyBubbleEntity = new RenderedEntity(GREY_BUBBLE_ID, new Vector2f(GREY_BUBBLE_POSITION));
		blackBubbleEntity = new RenderedEntity(BLACK_BUBBLE_ID, BLACK_BUBBLE_POSITION);
		leftBubbleEntity = new RenderedEntity(BLACK_BUBBLE_ID,
				new Vector2f(WINDOW_WIDTH / 2 - BLACK_BUBBLE_WIDTH - 20, BLACK_BUBBLE_POSITION.getY() + 23));
		centerBubbleEntity = new RenderedEntity(BLACK_BUBBLE_ID,
				new Vector2f(WINDOW_WIDTH / 2, BLACK_BUBBLE_POSITION.getY() + 23));
		rightBubbleEntity = new RenderedEntity(BLACK_BUBBLE_ID,
				new Vector2f(WINDOW_WIDTH / 2 + BLACK_BUBBLE_WIDTH + 20, BLACK_BUBBLE_POSITION.getY() + 23));
		pauseEntity = new RenderedEntity(PAUSE_ID, BACKGROUND_POSITION);
	}

	/**
	 * Adds the images to the entities.
	 * 
	 * @throws SlickException
	 *             generic Exception thrown by everything in the library
	 */
	private void addImageToEntites() throws SlickException {
		highscoreEntity.addComponent(new ImageRenderComponent(highscoreImage));
		creditsEntity.addComponent(new ImageRenderComponent(creditsImage));
		gameModeEntity.addComponent(new ImageRenderComponent(gameModeImage));
		gameWonEntity.addComponent(new ImageRenderComponent(gameWonImage));
		gameLostEntity.addComponent(new ImageRenderComponent(gameLostImage));
		backgroundEntity.addComponent(new ImageRenderComponent(backgroundImage));
		gameBackgroundEntity.addComponent(new ImageRenderComponent(gameBackgroundImage));
		logoEntity.addComponent(new ImageRenderComponent(logoImage));
		greyBubbleEntity.addComponent(new ImageRenderComponent(greyBubbleImage));
		blackBubbleEntity.addComponent(new ImageRenderComponent(blackBubbleImage));
		leftBubbleEntity.addComponent(new ImageRenderComponent(blackBubbleGMImage));
		centerBubbleEntity.addComponent(new ImageRenderComponent(blackBubbleGMImage));
		rightBubbleEntity.addComponent(new ImageRenderComponent(blackBubbleGMImage));
		pauseEntity.addComponent(new ImageRenderComponent(pauseImage));
	}
}