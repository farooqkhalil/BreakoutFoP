package de.tudarmstadt.informatik.fop.breakout.ui.states;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.tudarmstadt.informatik.fop.breakout.ui.Breakout;
import de.tudarmstadt.informatik.fop.breakout.ui.constants.OurGameParameters;
import de.tudarmstadt.informatik.fop.breakout.ui.helper.RenderedEntity;
import de.tudarmstadt.informatik.fop.breakout.ui.objects.BallEntity;
import de.tudarmstadt.informatik.fop.breakout.ui.objects.BlockEntity;
import de.tudarmstadt.informatik.fop.breakout.ui.objects.ItemEntity;
import de.tudarmstadt.informatik.fop.breakout.ui.objects.LifeEntities;
import de.tudarmstadt.informatik.fop.breakout.ui.objects.StickEntity;
import de.tudarmstadt.informatik.fop.breakout.ui.objects.TimerEntity;
import de.tudarmstadt.informatik.fop.breakout.ui.settings.Design;
import de.tudarmstadt.informatik.fop.breakout.ui.settings.GameData;
import de.tudarmstadt.informatik.fop.breakout.ui.settings.Sounds;
import de.tudarmstadt.informatik.fop.breakout.ui.settings.Themes;
import eea.engine.action.Action;
import eea.engine.action.basicactions.MoveDownAction;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.basicevents.KeyDownEvent;
import eea.engine.event.basicevents.LoopEvent;

/**
 * This class represents the game window where a stick hits a ball in order to
 * break all the bricks in the upper part of the game window.
 * 
 * @author Andrés Fraga Trespalacios, Farooq Khalil, Sebastian Sahner, </br>
 *         Timo Bochenko
 * 
 * @version 3.4 - added highscore
 */
public class GameplayState extends BasicGameState implements OurGameParameters, LifeEntities {

	// STATE VARIABLES
	/** ID for this BasicGameState. */
	private int stateID;

	/** The associated entity manager. */
	private StateBasedEntityManager entityManager;

	// GAME VARIABLES
	/** Counter for the times space is down. */
	private int spaceCounter;

	/** Counter for the amount of blocks left. */
	private int blockCounter;

	/** The parsed map. */
	private int[] parsedMap;

	/** The source of the map file. */
	private String mapFile;

	// BALL DATA
	/** The ball entity. */
	private BallEntity ballEntity;

	/** The ball's x position. */
	private float xPosBall;

	/** The ball's y position. */
	private float yPosBall;

	/** The ball's speed vector. */
	private Vector2f speedVector;

	/** The X component of the ball's speed vector. */
	private float xComp;

	/** The Y component of the ball's speed vector. */
	private float yComp;

	// STICK DATA
	/** The stick entity. */
	private StickEntity stickEntity;

	/** The stick's x position. */
	private float xPosStick;

	/** The stick's y position. */
	private float yPosStick;

	// ITEM DATA
	/** The item entity */
	ItemEntity itemEntity;

	// CONSTRUCTOR
	/**
	 * Creates a new GameplayState.
	 * 
	 * @param sid
	 *            the stateID
	 */
	public GameplayState(int sid) {
		stateID = sid;
		entityManager = StateBasedEntityManager.getInstance();
		// Choose x component freely between 0 and -0.3f (INITIAL_BALL_SPEED)
		xComp = -0.2f;
		// Calculate y component with set of pythagoras
		yComp = -(float) Math.sqrt(Math.pow(INITIAL_BALL_SPEED, 2) - (float) Math.pow(xComp, 2));
		// Set the speedVector
		speedVector = new Vector2f(xComp, yComp);
		// Initiate mapFile
		mapFile = "maps/level" + GameData.level + ".map";
	}

	// METHODS
	/** Initiates before the (first) run of this state. */
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

		// ********* MUSIC *********
		// Unmute Music
		if (!container.isMusicOn())
			container.setMusicOn(true);
		// If previous state ID was bigger than 3
		if (game.getCurrentStateID() > 3)
			// Loop the theme
			Themes.gameplayTheme.loop(1f, 0.4f);

		// ********* BACKGROUND *********
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, Design.gameBackgroundEntity);

		// ********* MAP *********
		// Parse the map
		try {
			parsedMap = parseMap(mapFile);
		} catch (FileNotFoundException e) {
			System.err.println("Cannot find mapFile.");
			e.printStackTrace();
		}

		// ********* BLOCKS *********
		// Arrange blocks according to map
		for (int i = 0, j = 0; i < 160; i++) {
			// Define how many blocks are in 1 line
			int blocksPerLine = 16;
			// Now we need the ID to create the entity:
			// Create StringBuilder with the word "block"
			StringBuilder ID = new StringBuilder("block");
			// Append a consecutive number starting with 1
			ID.append(i + 1); // block1, block2, block3 ...
			// Create entity
			BlockEntity blockEntity = new BlockEntity(ID.toString(), parsedMap[i]);
			// If i is a multiple of 16
			if (i > 0 && i % blocksPerLine == 0)
				// increase j by 1
				j++;
			// Set the position according to block number
			if (i < blocksPerLine * (j + 1)) {
				blockEntity.setPosition(new Vector2f((i - j * blocksPerLine) * BLOCK_WIDTH + BLOCK_WIDTH / 2,
						j * BLOCK_HEIGHT + BLOCK_HEIGHT / 2));
				// Hand over entity to StateBasedEntityManager
				entityManager.addEntity(stateID, blockEntity);
			}
		}

		// ********* LIVES *********
		// Hand over entities to StateBasedEntityManager
		entityManager.addEntity(stateID, life1Entity);
		entityManager.addEntity(stateID, life2Entity);
		entityManager.addEntity(stateID, life3Entity);
		entityManager.addEntity(stateID, life4Entity);
		entityManager.addEntity(stateID, life5Entity);
		// Update the life images
		LifeEntities.updateLifeImgs();

		// ********* TIMER *********
		// Create entity
		TimerEntity timerEntity = new TimerEntity(STOP_WATCH_ID);
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, timerEntity);

		// ********* BALL *********
		// Create entity
		ballEntity = new BallEntity(BALL_ID);
		// Add updateBallPositions to entity
		ballEntity.addComponent(saveBallPosition());
		// Add ballMovement to entity
		ballEntity.addComponent(ballMovement());
		// Add leaveScreen to entity
		ballEntity.addComponent(leaveScreen());
		// Add borderCollision to entity
		ballEntity.addComponent(borderCollision());
		// Add stickCollision to entity
		ballEntity.addComponent(stickCollision());
		// Add blockCollision to entity
		ballEntity.addComponent(blockCollision());
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, ballEntity);

		// ********* STICK *********
		// Create entity
		stickEntity = new StickEntity(STICK_ID);
		// Add saveStickPositions to entity
		stickEntity.addComponent(saveStickPositions());
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, stickEntity);

		// ********* SPACE *********
		// Create entity
		Entity spaceEntity = new Entity(SPACE_ID);
		// Create event
		LoopEvent startGameEvent = new LoopEvent();
		// Add action to event
		startGameEvent.addAction(new Action() {
			@Override
			public void update(GameContainer container, StateBasedGame game, int delta, Component component) {
				// If game has not started and space is down
				if (!GameData.hasStarted && container.getInput().isKeyDown(Input.KEY_SPACE)) {
					// If it is the first time space is down
					if (spaceCounter == 0)
						// Start timer
						timerEntity.start();
					// If timer is not running (after life lost)
					if (!timerEntity.isRunning())
						// Resume timer
						timerEntity.resume();
					// Game started
					GameData.hasStarted = true;
					// Add 1 to spaceCounter
					++spaceCounter;
				}
			}
		});
		// Add startGameEvent to entity
		spaceEntity.addComponent(startGameEvent);
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, spaceEntity);

		// ********* PAUSE *********
		// Create entity
		RenderedEntity pauseEntity = Design.pauseEntity;
		// Set visibility for entity
		pauseEntity.setVisible(false);
		// Create Event
		LoopEvent pauseEvent = new LoopEvent();
		// Add action to event
		pauseEvent.addAction(new Action() {
			@Override
			public void update(GameContainer container, StateBasedGame game, int delta, Component component) {
				// If P is down
				if (container.getInput().isKeyPressed(Input.KEY_P)) {
					// If container is not paused
					if (!container.isPaused()) {
						// Set container as paused
						container.setPaused(true);
						// Set visibility for entity
						pauseEntity.setVisible(true);
						// If timer is running
						if (timerEntity.isRunning())
							// Stop timer
							timerEntity.stop();
						// Stop music
						container.setMusicOn(false);
					}
					// Else (container is paused)
					else {
						// Set container as not paused
						container.setPaused(false);
						// Set visibility for entity
						pauseEntity.setVisible(false);
						// Resume timer
						timerEntity.resume();
						// Play music
						container.setMusicOn(true);
					}
				}
			}
		});
		// Add pauseEvent to entity
		pauseEntity.addComponent(pauseEvent);
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, pauseEntity);

		// ********* ESCAPE *********
		// Create entity
		Entity escapeEntity = new Entity(ESCAPE_ID);
		// Create event
		KeyDownEvent keyEvent = new KeyDownEvent(Input.KEY_ESCAPE);
		// Add action to event
		keyEvent.addAction(new Action() {
			@Override
			public void update(GameContainer container, StateBasedGame game, int delta, Component component) {
				// Stop sound
				Sounds.heartBeatSound.stop();
				// ChangeStateInitAction
				game.enterState(MAINMENU_STATE);
				try {
					game.init(container);
				} catch (SlickException e) {
					System.err.println("Cannot initiate container.");
					e.printStackTrace();
				}
				// Reset level
				GameData.level = 1;
				// Reset lives
				GameData.lives = 3;
				// Update live images
				LifeEntities.updateLifeImgs();
				// Reset hasStarted
				GameData.hasStarted = false;
			}
		});
		// Add event to entity
		escapeEntity.addComponent(keyEvent);
		// Hand over entity to StateBasedEntityManager
		entityManager.addEntity(stateID, escapeEntity);
	}

	/** Render this state to the game's graphics context. */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		// EntityManager renders all entities
		entityManager.renderEntities(container, game, g);

		// ********* TIMER *********
		// Create StringBuilder with word "Timer: "
		StringBuilder sb = new StringBuilder("Timer:  ");
		// Append elapsedTime
		sb.append(GameData.elapsedTime);
		// Draw a String with elapsedTime
		g.drawString(sb.toString(), 12, 70);
		// Clear the StringBuilder
		sb.setLength(0);

		// ********* DESTROYED BLOCKS *********
		// Create StringBuilder with word "Blocks: "
		sb.append("Blocks: ");
		// Append destroyedBlocks
		sb.append(GameData.destroyedBlocks);
		// Draw a String with destroyedBlocks
		g.drawString(sb.toString(), 12, 90);
	}

	/** Update the state's logic based on the amount of time thats passed. */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

		// If debug not activated
		if (!Breakout.getDebug()) {
			// Update elapsedTime
			TimerEntity timer = (TimerEntity) entityManager.getEntity(stateID, STOP_WATCH_ID);
			GameData.elapsedTime = timer.getElapsedTimeInSecs();

			// Update destroyedBlocks
			GameData.destroyedBlocks = (int) ((BallEntity) entityManager.getEntity(stateID, BALL_ID))
					.getDestroyedBlocks();
		}

		// EntityManager updates all entities
		entityManager.updateEntities(container, game, delta);
	}

	@Override
	public int getID() {
		return stateID;
	}

	// HELPER
	/**
	 * Parse the map into a String Array.
	 * 
	 * @param filePath
	 *            the file path
	 * @return the parsed map as String Array
	 * @throws FileNotFoundException
	 *             exception trhown if file not found
	 */
	private int[] parseMap(String filePath) throws FileNotFoundException {
		// Read file and save in scanner
		Scanner scanner = new Scanner(new FileReader(filePath));
		// Create StringBuilder
		StringBuilder sb = new StringBuilder();
		// While scanner has a line
		while (scanner.hasNextLine())
			// Append the line to the StringBuilder
			sb.append(scanner.nextLine()).append(",");
		// Close the scanner
		scanner.close();
		// Make the Stringbuilder to a String and split the content whenever
		// there is a ",". The content is saved in a String Array
		String[] numbersAsString = sb.toString().split(",");
		// Create new int array with the length of the String Array
		int[] numbers = new int[numbersAsString.length];
		// Copy numbers from String to int Array
		for (int i = 0; i < numbersAsString.length; i++) {
			// If gameMode is random
			if (GameData.gameMode.equals(RANDOM_ID))
				// Choose a number between 0 and 4
				numbers[i] = new Random().nextInt(5);
			else
				// Else parse the String-Array
				numbers[i] = Integer.parseInt(numbersAsString[i]);
			// If the value in numbers is not 0
			if (numbers[i] != 0)
				// Add 1 to blockCounter
				blockCounter++;
		}
		return numbers;
	}

	/**
	 * @return a LoopEvent that saves ball position in xPosBall/yPosBall
	 */
	private LoopEvent saveBallPosition() {
		// Create Event
		LoopEvent loopEvent = new LoopEvent();
		// Add action to event
		loopEvent.addAction(new Action() {
			@Override
			public void update(GameContainer container, StateBasedGame game, int delta, Component component) {
				// Update x and y position of ball
				xPosBall = ballEntity.getPosition().getX();
				yPosBall = ballEntity.getPosition().getY();
			}
		});
		return loopEvent;
	}

	/**
	 * @return a LoopEvent that describes the ball movement in the game
	 */
	private LoopEvent ballMovement() {
		// Create Event
		LoopEvent loopEvent = new LoopEvent();
		// Add action to event
		loopEvent.addAction(new Action() {
			@Override
			public void update(GameContainer container, StateBasedGame game, int delta, Component component) {

				// If game has not started
				if (!GameData.hasStarted)
					// Move ball parallel to stick
					ballEntity.setPosition(new Vector2f(xPosStick, yPosStick - STICK_HEIGHT));

				// If game started
				if (GameData.hasStarted)
					// Keep ball moving according to speed vector
					ballEntity.setPosition(
							new Vector2f(xPosBall + speedVector.getX() * delta, yPosBall + speedVector.getY() * delta));
			}
		});
		return loopEvent;
	}

	/**
	 * @return a LoopEvent whith the actions to do when ball leaves the screen
	 */
	private LoopEvent leaveScreen() {
		// Create Event
		LoopEvent loopEvent = new LoopEvent();
		// Add action to event
		loopEvent.addAction(new Action() {
			@Override
			public void update(GameContainer container, StateBasedGame game, int delta, Component component) {
				// Get timer from entityManager
				TimerEntity timer = (TimerEntity) entityManager.getEntity(stateID, STOP_WATCH_ID);

				// If the ball leaves the screen
				if (yPosBall >= WINDOW_HEIGHT) {
					// Decrease lives
					--GameData.lives;
					// Update life images
					LifeEntities.updateLifeImgs();
					// If 1 live left
					if (GameData.lives == 1) {
						// Stop music
						container.setMusicOn(false);
						// Loop sound
						Sounds.heartBeatSound.loop();
					}
					// If no lives
					if (GameData.lives == 0) {
						// Stop loop
						Sounds.heartBeatSound.stop();
						// Play sound
						Sounds.gameLostSound.play();

						// Add elapsedTime to highscoreTime
						GameData.highscoreTime += timer.getElapsedTimeInSecs();

						// You lose
						game.enterState(GAMELOST_STATE);
					}
					// Reset positions/values
					stickEntity.setPosition(new Vector2f(INITIAL_STICK_POSITION));
					ballEntity.setPosition(new Vector2f(xPosStick, yPosStick - STICK_HEIGHT));
					GameData.hasStarted = false;
					timer.stop();
				}
			}
		});
		return loopEvent;
	}

	/**
	 * @return a LoopEvent with the reaction of the ball to border collisions
	 */
	private LoopEvent borderCollision() {
		// Create Event
		LoopEvent loopEvent = new LoopEvent();
		// Add action to event
		loopEvent.addAction(new Action() {
			@Override
			public void update(GameContainer container, StateBasedGame game, int delta, Component component) {

				if (GameData.hasStarted) {
					// Left border
					if (xPosBall < BALL_WIDTH / 2) {
						// Reset position for entity (fixes some issues)
						ballEntity.setPosition(new Vector2f(BALL_WIDTH / 2, yPosBall));
						// Set theta as reflection of incoming theta
						speedVector.setTheta(540 - speedVector.getTheta());
					}

					// Right border
					if (xPosBall > WINDOW_WIDTH - BALL_WIDTH / 2) {
						// Reset position for entity (fixes some issues)
						ballEntity.setPosition(new Vector2f(WINDOW_WIDTH - BALL_WIDTH / 2, yPosBall));
						// Set theta as reflection of incoming theta
						speedVector.setTheta(540 - speedVector.getTheta());
					}

					// Top border
					if (yPosBall < BALL_HEIGHT / 2) {
						// Reset position for entity (fixes some issues)
						ballEntity.setPosition(new Vector2f(xPosBall, BALL_HEIGHT / 2));
						// Set theta as reflection of incoming theta
						speedVector.setTheta(-speedVector.getTheta());
					}
				}
			}
		});
		return loopEvent;
	}

	/**
	 * @return a LoopEvent with the reaction of the ball to stick collisions
	 */
	private LoopEvent stickCollision() {

		// Create Event
		LoopEvent loopEvent = new LoopEvent();
		// Add action to event
		loopEvent.addAction(new Action() {
			@Override
			public void update(GameContainer container, StateBasedGame game, int delta, Component component) {
				// If debug not activated
				if (!Breakout.getDebug()) {
					// Safe the actual theta (angle) from speed vevtor
					double theta = speedVector.getTheta();

					// Devide stick surface into 11 areas
					float areaWidth = Design.stickImage.getWidth() / 11;
					// Areas are devided by imaginary boundaries starting from
					// left
					float bound1 = xPosStick - Design.stickImage.getWidth() / 2; // left
					float bound2 = bound1 + areaWidth;
					float bound3 = bound2 + areaWidth;
					float bound4 = bound3 + areaWidth;
					float bound5 = bound4 + areaWidth;
					float bound6 = bound5 + areaWidth;
					float bound7 = bound6 + areaWidth;
					float bound8 = bound7 + areaWidth;
					float bound9 = bound8 + areaWidth;
					float bound10 = bound9 + areaWidth;
					float bound11 = bound10 + areaWidth;
					float bound12 = bound11 + areaWidth;// right corner of stick
					// Create an outgoing angle with the initial angle as value
					double outgoingAngle = INITIAL_ANGLE;
					// Set the gradient (how strong the angle should be changed)
					int gradient = 5;

					// If ball hits stick
					if (ballEntity.collides(stickEntity)) {
						// if ball came from left
						if (theta <= 90)
							// outgoingAngle = reflection of incoming angle
							outgoingAngle = 360 - theta;
						// if ball came from right
						if (theta > 90 && theta < 180)
							// outgoingAngle = reflection of incoming angle
							outgoingAngle = theta + 2 * (180 - theta);

						// Set areas and adapt outgoingAngle according to
						// gradient
						if (xPosBall > bound1 && xPosBall < bound2) {
							speedVector.setTheta(outgoingAngle - 5 * gradient);
						}
						if (xPosBall > bound2 && xPosBall < bound3) {
							speedVector.setTheta(outgoingAngle - 4 * gradient);
						}
						if (xPosBall > bound3 && xPosBall < bound4) {
							speedVector.setTheta(outgoingAngle - 3 * gradient);
						}
						if (xPosBall > bound4 && xPosBall < bound5) {
							speedVector.setTheta(outgoingAngle - 2 * gradient);
						}
						if (xPosBall > bound5 && xPosBall < bound6) {
							speedVector.setTheta(outgoingAngle - gradient);
						}
						if (xPosBall > bound6 && xPosBall < bound7) {
							speedVector.setTheta(outgoingAngle);
						}
						if (xPosBall > bound7 && xPosBall < bound8) {
							speedVector.setTheta(outgoingAngle + gradient);
						}
						if (xPosBall > bound8 && xPosBall < bound9) {
							speedVector.setTheta(outgoingAngle + 2 * gradient);
						}
						if (xPosBall > bound9 && xPosBall < bound10) {
							speedVector.setTheta(outgoingAngle + 3 * gradient);
						}
						if (xPosBall > bound10 && xPosBall < bound11) {
							speedVector.setTheta(outgoingAngle + 4 * gradient);
						}
						if (xPosBall > bound11 && xPosBall < bound12) {
							speedVector.setTheta(outgoingAngle + 5 * gradient);
						}
						// If game has started
						if (GameData.hasStarted)
							// Play sound
							Sounds.hitStickSound.play();
					}
				}
			}
		});
		return loopEvent;
	}

	/**
	 * @return a LoopEvent with the reaction of the ball to block collisions
	 */
	private LoopEvent blockCollision() {
		// Create Event
		LoopEvent loopEvent = new LoopEvent();
		// Add action to event
		loopEvent.addAction(new Action() {
			@Override
			public void update(GameContainer container, StateBasedGame game, int delta, Component component) {

				// Start a for statement
				for (int i = 0; i < 160; i++) {
					// Create StringBuilder with the word "block"
					StringBuilder ID = new StringBuilder("block");
					// Append a consecutive number starting with 1
					ID.append(i + 1); // block1, block2, block3 ...
					// Get blockEntity from entityManager
					BlockEntity blockEntity = (BlockEntity) entityManager.getEntity(stateID, ID.toString());

					// If has started and blockCollision
					if (GameData.hasStarted && ballEntity.collides(blockEntity)) {

						// If ball is flying up
						if (speedVector.getTheta() > 180) {
							// If ball hits block in range of blocks width
							if (Math.abs(xPosBall - blockEntity.getPosition().getX()) <= BLOCK_WIDTH / 2
									+ BALL_WIDTH / 2)
								// Reflect the ball
								speedVector = new Vector2f(speedVector.getX(), -speedVector.getY());
							// If ball is left from block and came from left
							else if (xPosBall < blockEntity.getPosition().getX() && speedVector.getTheta() > 270)
								// Reflect the ball
								speedVector = new Vector2f(-speedVector.getX(), speedVector.getY());
							// If ball is right from block and came from right
							else if (xPosBall > blockEntity.getPosition().getX() && speedVector.getTheta() < 270) {
								// Reflect the ball
								speedVector = new Vector2f(-speedVector.getX(), speedVector.getY());
							}
						}

						// If ball is flying down
						if (yPosBall < blockEntity.getPosition().getY() && speedVector.getTheta() < 180) {
							// If ball hits block in range of blocks width
							if (Math.abs(xPosBall - blockEntity.getPosition().getX()) <= BLOCK_WIDTH / 2
									+ BALL_WIDTH / 2)
								// Reflect the ball
								speedVector = new Vector2f(speedVector.getX(), -speedVector.getY());
							// If ball is left from block and came from left
							else if (xPosBall < blockEntity.getPosition().getX() && speedVector.getTheta() < 90)
								// Reflect the ball
								speedVector = new Vector2f(-speedVector.getX(), speedVector.getY());
							// If ball is right from block && came from right
							else if (xPosBall > blockEntity.getPosition().getX()) {
								// Reflect the ball
								speedVector = new Vector2f(-speedVector.getX(), speedVector.getY());
							}
						}

						// Reduce hits left by 1
						blockEntity.setHitsLeft(blockEntity.getHitsLeft() - 1);
						// Update the blockImage depending on the hits left
						blockEntity.updateBlockImage();

						// If no more hits left
						if (blockEntity.getHitsLeft() == 0) {

							// Decide on item chance with random number
							int rand = new Random().nextInt(5) + 1;
							String itemString;
							switch (rand) {
							case 1:
								itemString = "smaller";
								break;
							case 2:
								itemString = "bigger";
								break;
							case 3:
								itemString = "faster";
								break;
							case 4:
								itemString = "slower";
								break;
							case 5:
								itemString = "life";
								break;
							default:
								itemString = null;
								break;
							}

							// If itemString is not null, no itemEntity exists
							if (itemString != null && !entityManager.hasEntity(stateID, ITEM_ID)) {
								// Create entity
								itemEntity = new ItemEntity(ITEM_ID, itemString);
								// Set position for entity
								itemEntity.setPosition(new Vector2f(blockEntity.getPosition()));
								// Hand over entity to StateBasedEntityManager
								entityManager.addEntity(stateID, itemEntity);

								// Create event
								LoopEvent moveDownEvent = new LoopEvent();
								// Add action to event
								moveDownEvent.addAction(new MoveDownAction(0.4f));
								// Add event to entity
								itemEntity.addComponent(moveDownEvent);

								// Create event
								LoopEvent checkCollision = new LoopEvent();

								// Add action to event
								checkCollision.addAction(new Action() {
									@Override
									public void update(GameContainer container, StateBasedGame game, int delta,
											Component component) {
										// If item hits stick
										if (itemEntity.collides(stickEntity)) {
											// React according to itemKind
											switch (itemEntity.getItemKind()) {
											case "smaller":
												if (Design.stickImage.getWidth() > 80)
													stickEntity.scaleWidth((1 / 1.5f));
												break;
											case "bigger":
												if (Design.stickImage.getWidth() < 200)
													stickEntity.scaleWidth(1.5f);
												break;
											case "faster":
												speedVector = new Vector2f(speedVector.getX() * 3 / 2,
														speedVector.getY() * 3 / 2);
												// Create Timer for item
												new java.util.Timer().schedule(new java.util.TimerTask() {
													public void run() {
														speedVector = new Vector2f(speedVector.getX() * 2 / 3,
																speedVector.getY() * 2 / 3);
													}
												}, 10000);
												break;
											case "slower":
												speedVector = new Vector2f(speedVector.getX() * 2 / 3,
														speedVector.getY() * 2 / 3);
												// Create Timer for item
												new java.util.Timer().schedule(new java.util.TimerTask() {
													public void run() {
														speedVector = new Vector2f(speedVector.getX() * 3 / 2,
																speedVector.getY() * 3 / 2);
													}
												}, 10000);
												break;
											case "life":
												// If lives are less than 5
												if (GameData.lives < 5)
													// Increase lives
													++GameData.lives;
												if (GameData.lives == 2) {
													Sounds.heartBeatSound.stop();
													// Unmute Music
													if (!container.isMusicOn())
														container.setMusicOn(true);
													// If previous stat ID was
													// bigger than 3
													if (game.getCurrentStateID() > 3)
														// Loop the theme
														Themes.gameplayTheme.loop(1f, 0.4f);
												}
												// Update life images
												LifeEntities.updateLifeImgs();
												break;
											}
											// Destroy the itemEntity
											entityManager.removeEntity(stateID, itemEntity);
										}
										// If item was not cought
										if (itemEntity.getPosition().getY() >= WINDOW_HEIGHT) {
											// Destroy the itemEntity
											entityManager.removeEntity(stateID, itemEntity);
										}
									}
								});
								// Add event to entity
								itemEntity.addComponent(checkCollision);
							}

							// Destroy the blockEntity
							entityManager.removeEntity(stateID, blockEntity);
							// Reduce block counter by 1
							blockCounter--;
							// Increase destroyed blocks by 1
							ballEntity.increaseDestroyedBlocks();
							// Increase HighscoreBlocks();
							GameData.highscoreBlocks++;
							// Play sound
							Sounds.destroyedBlockSound.play();
							// If no more blocks left
							if (blockCounter == 0) {
								// Stop loop
								Sounds.heartBeatSound.stop();
								// Play sound
								Sounds.gameWonSound.play();
								// Stop music
								container.setMusicOn(false);

								// Add elapsedTime to highscoreTime
								TimerEntity timer = (TimerEntity) entityManager.getEntity(stateID, STOP_WATCH_ID);
								GameData.highscoreTime += timer.getElapsedTimeInSecs();

								// You win
								game.enterState(GAMEWON_STATE);

								// Reset positions/values
								GameData.hasStarted = false;
							}
						} else
							// Play sound
							Sounds.hitBlockSound.play();
					}
				}
			}
		});
		return loopEvent;

	}

	/**
	 * @return a LoopEvent that saves stick position in xPosStick/yPosStick
	 */
	private LoopEvent saveStickPositions() {
		// Create Event
		LoopEvent loopEvent = new LoopEvent();
		// Add action to event
		loopEvent.addAction(new Action() {
			@Override
			public void update(GameContainer container, StateBasedGame game, int delta, Component component) {
				// Update x and y position of stick
				xPosStick = stickEntity.getPosition().getX();
				yPosStick = stickEntity.getPosition().getY();
			}
		});
		return loopEvent;
	}
}