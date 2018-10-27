package de.tudarmstadt.informatik.fop.breakout.ui.constants;

import org.newdawn.slick.geom.Vector2f;

import de.tudarmstadt.informatik.fop.breakout.constants.GameParameters;

/**
 * Class for holding the game parameters and constants e.g. entity IDs or image
 * paths
 * 
 * @author Andrés Fraga Trespalacios, Farooq Khalil, Sebastian Sahner, </br>
 *         Timo Bochenko
 * 
 * @version 2.5 - added game mode, renamed bubble background for grey bubble and
 *          bubble menu for black bubble
 */
public interface OurGameParameters extends GameParameters {

	// Additional Game States
	public static final int CREDITS_STATE = 3;
	public static final int GAMEWON_STATE = 4;
	public static final int GAMELOST_STATE = 5;
	public static final int GAMEMODE_STATE = 6;

	// Background
	public static final String GAME_BACKGROUND_IMAGE = "/images/game_background_2.png";
	public static final Vector2f BACKGROUND_POSITION = new Vector2f(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2);

	// Logo
	public static final String LOGO_ID = "logo";
	public static final String LOGO_IMAGE = "/images/logo.png";
	public static final Vector2f LOGO_POSITION = new Vector2f(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 7);
	public static final String LOGO_BALL_SPRITE = "/images/logo_ball_sprite.png";

	// Grey Bubble
	public static final String GREY_BUBBLE_ID = "greyBubble";
	public static final String GREY_BUBBLE_IMAGE = "/images/grey_bubble.png";
	public static final float GREY_BUBBLE_HEIGHT = 386;
	public static final Vector2f GREY_BUBBLE_POSITION = new Vector2f(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2 + 75);

	// Black Bubble
	public static final String BLACK_BUBBLE_ID = "blackBubble";
	public static final String BLACK_BUBBLE_IMAGE = "/images/black_bubble.png";
	public static final float BLACK_BUBBLE_WIDTH = 200;
	public static final float BLACK_BUBBLE_HEIGHT = 386;
	public static final Vector2f BLACK_BUBBLE_POSITION = new Vector2f(WINDOW_WIDTH / 5, GREY_BUBBLE_POSITION.getY());

	// Entry
	public static final String ENTRY_ID = "entry";
	public static final String ENTRY_IMAGE = "/images/entry.png";

	// Menus
	public static final String NEW_GAME_MENU_ID = "newGameMenu";
	public static final String GAMEPLAY_MENU_ID = "gameplayMenu";
	public static final String HIGHSCORE_MENU_ID = "highscoreMenu";
	public static final String CREDITS_MENU_ID = "creditsMenu";
	public static final String QUIT_MENU_ID = "quitMenu";
	public static final int MENU_DISTANCE = 60;

	// Info Box
	public static final Vector2f INFO_BOX_POSITION = new Vector2f(500, BLACK_BUBBLE_POSITION.getY());

	// Gameplay Info
	public static final String NEW_GAME_INFO_ID = "gameplayInfo";
	public static final String NEW_GAME_INFO_IMAGE = "/images/new_game_info.png";

	// Highscore Info
	public static final String HIGHSCORE_INFO_ID = "highscoreInfo";
	public static final String HIGHSCORE_INFO_IMAGE = "/images/highscore_info.png";

	// Credits Info
	public static final String CREDITS_INFO_ID = "creditsInfo";
	public static final String CREDITS_INFO_IMAGE = "/images/credits_info.png";

	// Quit Info
	public static final String QUIT_INFO_ID = "quitInfo";
	public static final String QUIT_INFO_IMAGE = "/images/quit_info.png";

	// Game Mode
	public static final String GAME_MODE_ID = "gameMode";
	public static final String GAME_MODE_IMAGE = "/images/game_mode.png";
	public static final String ARCADE_ID = "arcade";
	public static final String RANDOM_ID = "random";
	public static final String SURVIVAL_ID = "survival";

	// Highscore
	public static final String HIGHSCORE_ID = "highscore";
	public static final String HIGHSCORE_IMAGE = "/images/highscore.png";

	// Credits
	public static final String CREDITS_ID = "credits";
	public static final String CREDITS_IMAGE = "/images/credits.png";

	// Blocks
	public static final String BLOCK_4_IMAGE = "/images/block_4.png";
	public static final float BLOCK_WIDTH = 50;
	public static final float BLOCK_HEIGHT = 30;

	// Ball
	public static final double INITIAL_ANGLE = 270;
	public static final float BALL_WIDTH = 26;
	public static final float BALL_HEIGHT = 26;

	// Stick
	public static final float STICK_WIDTH = 130;
	public static final float STICK_HEIGHT = 26;
	public static final Vector2f INITIAL_STICK_POSITION = new Vector2f(WINDOW_WIDTH / 2,
			WINDOW_HEIGHT - STICK_HEIGHT / 2);

	// Items
	public static final String ITEM_ID = "item";
	public static final String ITEM_BIGGER_IMAGE = "images/bigger.png";
	public static final String ITEM_SMALLER_IMAGE = "/images/smaller.png";
	public static final String ITEM_FASTER_IMAGE = "/images/faster.png";
	public static final String ITEM_SLOWER_IMAGE = "/images/slower.png";

	// Space
	public static final String SPACE_ID = "space";

	// Enter
	public static final String ENTER_ID = "enter";

	// Game Won
	public static final String GAME_WON_ID = "gameWon";
	public static final String GAME_WON_IMAGE = "/images/game_won.png";
	public static final float GAME_WON_HEIGHT = 526;

	// Game Lost
	public static final String GAME_LOST_ID = "gameLost";
	public static final String GAME_LOST_IMAGE = "/images/game_lost.png";
	public static final float GAME_LOST_HEIGHT = GAME_WON_HEIGHT;

	// Lives
	public enum LifeType {
		First, Second, Third, Fourth, Fifth
	}

	public static final String LIFE_1_ID = "life1";
	public static final String LIFE_2_ID = "life2";
	public static final String LIFE_3_ID = "life3";
	public static final String LIFE_4_ID = "life4";
	public static final String LIFE_5_ID = "life5";
	public static final String LIFE_IMAGE = "/images/life.png";
	public static final float LIFE_WIDTH = 24;

	// Sound
	public static final String HIT_BLOCK_SOUND = "/sounds/hitBlock.wav";
	public static final String DESTROYED_BLOCK_SOUND = "/sounds/destroyedBlock.wav";
	public static final String HIT_STICK_SOUND = "/sounds/hitStick.wav";
	public static final String HEARTBEAT_SOUND = "/sounds/heartbeat.wav";
	public static final String GAME_LOST_SOUND = "/sounds/gameLost.wav";
	public static final String GAME_WON_SOUND = "/sounds/gameWon.wav";

	// Music
	public static final String MAIN_THEME = "/music/main.wav";
	public static final String GAMEPLAY_THEME = "/music/gameplay.wav";

	// Line Separator
	public static final String NEW_LINE = System.lineSeparator();

	// Info with possible commands
	public static final String GAME_ENDED_COMMANDS = new StringBuffer("PRESS ENTER TO CONTINUE").toString();
}