package de.tudarmstadt.informatik.fop.breakout.ui.objects;

import org.lwjgl.Sys;

import eea.engine.entity.Entity;

/**
 * This class represents the timer which counts the seconds a player needed to
 * finish the game.
 * 
 * @author Andrés Fraga Trespalacios, Farooq Khalil, Sebastian Sahner, </br>
 *         Timo Bochenko
 * 
 * @version 1.1 - made Timer into an entity, deleted reset() since not needed
 */
public class TimerEntity extends Entity {

	// VARIABLES
	/** The start time. */
	private long startTime;

	/** The end time. */
	private long endTime;

	/** The elapsed time. */
	private long elapsedTime;

	/** Whether the timer is running. */
	private boolean isRunning;

	// CONSTRUCTOR
	/**
	 * Creates a new timer entity
	 * 
	 * @param entityID
	 *            the ID of the entity
	 */
	public TimerEntity(String entityID) {
		super(entityID);
	}

	// GETTER
	/**
	 * @return the elapsed time in seconds
	 */
	public Long getElapsedTimeInSecs() {
		if (isRunning) {
			elapsedTime = ((Sys.getTime() - startTime) / 1000);
		} else {
			elapsedTime = ((endTime - startTime) / 1000);
		}
		return elapsedTime;
	}

	/**
	 * @return whether the timer is running
	 */
	public boolean isRunning() {
		return isRunning;
	}

	// METHODS
	/** Starts the timer. */
	public void start() {
		startTime = Sys.getTime();
		isRunning = true;
	}

	/** Stops the timer. */
	public void stop() {
		endTime = Sys.getTime();
		isRunning = false;
	}

	/** Resumes the stopped timer. */
	public void resume() {
		startTime = Sys.getTime() - elapsedTime * 1000;
		isRunning = true;
	}
}