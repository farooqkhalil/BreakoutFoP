package de.tudarmstadt.informatik.fop.breakout.ui.highscore;

import java.io.Serializable;

import de.tudarmstadt.informatik.fop.breakout.interfaces.IHighscoreEntry;

/**
 * This class represents the Score of the player.
 * 
 * @author Andrés Fraga Trespalacios, Farooq Khalil, Sebastian Sahner, </br>
 *         Timo Bochenko
 * 
 * @version 1.0
 */
public class Score implements IHighscoreEntry, Serializable {

	// VARIABLES
	/** Used for implementing Serializable. */
	private static final long serialVersionUID = 3029802064446482036L;

	/** The elapsed time. */
	private long time;

	/** The destroyed blocks. */
	private int blocks;

	/** The player's name. */
	private String name;

	// CONSTRUCTOR
	/**
	 * Creates a Score.
	 * 
	 * @param name
	 *            the player's name
	 * @param time
	 *            the elapsed time
	 * @param blocks
	 *            the destroyed blocks
	 */
	public Score(String name, long time, int blocks) {
		this.name = name;
		this.time = time;
		this.blocks = blocks;
	}

	// GETTER
	@Override
	public String getPlayerName() {
		return name;
	}

	@Override
	public int getNumberOfDestroyedBlocks() {
		return blocks;
	}

	@Override
	public float getElapsedTime() {
		return time;
	}

	@Override
	public double getPoints() {
		// Not used
		return 0;
	}
}