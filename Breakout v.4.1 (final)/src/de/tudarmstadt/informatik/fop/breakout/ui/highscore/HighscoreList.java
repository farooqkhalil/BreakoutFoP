package de.tudarmstadt.informatik.fop.breakout.ui.highscore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

import de.tudarmstadt.informatik.fop.breakout.ui.constants.OurGameParameters;

/**
 * This class represents the highscorelist of the arcade mode.
 * 
 * @author Andrés Fraga Trespalacios, Farooq Khalil, Sebastian Sahner, </br>
 *         Timo Bochenko
 * 
 * @version 1.0
 */
public class HighscoreList implements OurGameParameters {

	// VARIABLES
	/** Maximum number of scores. */
	private int maximum;

	/** The highscoreList. */
	public static LinkedList<Score> highscoreList;

	/** OutputStream for writing highscore file. */
	private ObjectOutputStream output;

	/** InputStream for reading in highscore file. */
	private ObjectInputStream input;

	// CONSTRUCTOR
	/**
	 * Creates the highscoreList for arcade mode.
	 */
	public HighscoreList() {
		// Initiate maximum
		maximum = 10;
		// Create highscoreList
		highscoreList = new LinkedList<Score>();
	}

	// METHODS
	/**
	 * Adds a new score to the highscoreList.
	 * 
	 * @param score
	 *            the given score
	 */
	public void addNewScore(Score score) {
		// Add the given score
		highscoreList.add(score);
		// Sort the list
		sortList();
		// If size of list is greater than maximum
		if (highscoreList.size() > maximum) {
			// Remove scores ranked greater than maximum
			for (int i = maximum; i < highscoreList.size(); i++) {
				highscoreList.remove(i);
			}
		}
		// Write the given score into the highscore file
		updateScoreFile();
	}

	/** Clears the List. */
	public void clearList() {
		// Clear the highscoreList
		highscoreList.clear();
		// Update the scores
		updateScoreFile();
	}

	/** Sorts the highscoreList with a comparator. */
	private void sortList() {
		// Create comparator
		MyComp comparator = new MyComp();
		// Sort the list
		Collections.sort(highscoreList, comparator);
	}

	/**
	 * @return the sorted highscoreList
	 */
	public LinkedList<Score> getList() {
		// Sort the highscorelist
		sortList();
		// return the highscorelist
		return highscoreList;
	}

	/** Loads the HIGHSCORE_FILE. */
	@SuppressWarnings("unchecked")
	public void loadScoreFile() {
		// Read file and save in scanner
		Scanner scanner = null;
		try {
			scanner = new Scanner(new FileReader(HIGHSCORE_FILE));
		} catch (FileNotFoundException e) {
			System.err.println("Cannot find HIGHSCORE_FILE.");
			e.printStackTrace();
		}
		// If HIGHSCORE_FILE is empty
		if (!scanner.hasNextLine())
			// Don't compute the following lines and return instead
			return;
		// Try to initiate input
		try {
			input = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
			highscoreList = (LinkedList<Score>) input.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Cannot initiate input.");
			e.printStackTrace();
		}
		// Flush and close streams
		finally {
			try {
				if (output != null) {
					output.flush();
					output.close();
					input.close();
				}
			} catch (IOException e) {
				System.err.println("Cannot flush or close streams.");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Updates the highscore file.
	 */
	public void updateScoreFile() {
		try {
			output = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
			output.writeObject(highscoreList);
		} catch (FileNotFoundException e) {
			System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
		} catch (IOException e) {
			System.out.println("[Update] IO Error: " + e.getMessage());
		} finally {
			try {
				if (output != null) {
					output.flush();
					output.close();
				}
			} catch (IOException e) {
				System.out.println("[Update] Error: " + e.getMessage());
			}
		}
	}

	// HIGHSCORE COLUMNS
	/**
	 * @return the listed names in the highscoreList
	 */
	public String getListedNames() {
		// Create String for names
		String nameString = "";
		// Sort the list
		sortList();
		// Add names to String
		for (int i = 0; i < highscoreList.size(); i++) {
			nameString += highscoreList.get(i).getPlayerName() + "\n";
			if (i > maximum)
				break;
		}
		return nameString;
	}

	/**
	 * @return the listed blocks in the highscoreList
	 */
	public String getListedBlocks() {
		// Create String for blocks
		String blocksString = "";
		// Sort the list
		sortList();
		// Add blocks to String
		for (int i = 0; i < highscoreList.size(); i++) {
			blocksString += highscoreList.get(i).getNumberOfDestroyedBlocks() + "\n";
			if (i > maximum)
				break;
		}
		return blocksString;
	}

	/**
	 * @return the listed time in the highscoreList
	 */
	public String getListedTime() {
		// Create String for time
		String timeString = "";
		// Sort the list
		sortList();
		// Add time to String
		for (int i = 0; i < highscoreList.size(); i++) {
			timeString += (int) highscoreList.get(i).getElapsedTime() + "\n";
			if (i > maximum)
				break;
		}
		return timeString;
	}

	// COMPARATOR
	/**
	 * This class represents our comparator for the highscoreList.
	 */
	class MyComp implements Comparator<Score> {

		@Override
		public int compare(Score h1, Score h2) {
			if (h1.getNumberOfDestroyedBlocks() < h2.getNumberOfDestroyedBlocks()) {
				return 1;
			} else if (h1.getNumberOfDestroyedBlocks() == h2.getNumberOfDestroyedBlocks()) {
				if (h1.getElapsedTime() > h2.getElapsedTime())
					return 1;
				return -1;
			} else {
				return -1;
			}
		}
	}
}