package ui;

import java.util.Scanner;
import game.VacuumGame;

/**
 * A class of TextUI that provides a text UI and implements the interface of UI.
 * This class is responsible for the following operations: 1. Reads the input
 * from the keyboard. 2. Displays the winner message, which shows who is the
 * winner and how much score the winner collects.
 * 
 * @author lucasminghu
 */
public class TextUI implements UI {

	private VacuumGame game; // the game is the class of VacuumGame, where all
	// actions.

	/**
	 * Crates a new TextUI corresponds to the given VacuumGame.
	 * 
	 * @param game
	 *            the class of VacuumGame, where all actions happen.
	 */
	public TextUI(VacuumGame game) {

		this.game = game;

	}

	/**
	 * Reads input from keyboard. Moves the vacuum on the grid based on the
	 * keyboard input. Prints out the grid.
	 */
	public void launchGame() {

		int numRows = this.game.getNumRows(); // Reads the number of rows.
		int numCols = this.game.getNumColumns(); // Reads the number of columns.

		// Uses a loop to read the current grid, and prints out.
		for (int i = 0; i < numRows; i = i + 1) {
			for (int j = 0; j < numCols; j = j + 1) {
				System.out.print(this.game.getSprite(i, j).toString());
			}
			System.out.println();
		}

		// Uses scanner to read input from keyboard.
		Scanner keyboard = new Scanner(System.in);

		// Checks if the game is over or not, if not, moves the vacuum based on
		// the input.
		while (!this.game.gameOver()) {

			char myint = keyboard.next().charAt(0);

			this.game.move(myint); // Moves the vacuum based on the keyboard
									// input.

			// Loops thorough the grid to print out the grid.
			for (int i = 0; i < numRows; i = i + 1) {
				for (int j = 0; j < numCols; j = j + 1) {
					System.out.print(this.game.getSprite(i, j).toString());
				}
				System.out.println();
			}

		}
		keyboard.close(); // Closes the keyboard.

	}

	/**
	 * Displays the winner ID and the score the winner collects.
	 */
	public void displayWinner() {

		if (!this.game.gameOver()) {
			return;
		}

		int won = this.game.getWinner();
		String message;

		if (won == 1) {
			message = "Congratulations Player 1! "
					+ "You won the game with a score of "
					+ this.game.getVacuumOne().getScore() + ".";
		} else {
			message = "Congratulations Player 2! "
					+ "You won the game with a score of "
					+ this.game.getVacuumTwo().getScore() + ".";
		}

		System.out.println(message);
	}

}
