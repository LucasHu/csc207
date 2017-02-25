package game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import sprites.CleanHallway;
import sprites.Dirt;
import sprites.Dumpster;
import sprites.DustBall;
import sprites.Sprite;
import sprites.Vacuum;
import sprites.Wall;

/**
 * A class that represents the basic functionality of the vacuum game. This
 * class is responsible for performing the following operations: 1. At creation,
 * it initializes the instance variables used to store the current state of the
 * game. 2. When a move is specified, it checks if it is a legal move and makes
 * the move if it is legal. 3. It reports information about the current state of
 * the game when asked.
 */
public class VacuumGame {

	// a random number generator to move the DustBalls
	private Random random;

	// the grid
	private Grid<Sprite> grid;

	// the first player
	private Vacuum vacuum1;

	/// the second player
	private Vacuum vacuum2;

	// the dirt (both static dirt and mobile dust balls)
	private List<Dirt> dirts;

	// the dumpsters
	private List<Dumpster> dumpsters;

	/**
	 * Creates a new VacuumGame that corresponds to the given input text file.
	 * Assumes that the input file has one or more lines of equal lengths, and
	 * that each character in it (other than newline) is a character that
	 * represents one of the spites in this game.
	 * 
	 * @param layoutFileName
	 *            path to the input grid file
	 */
	public VacuumGame(String layoutFileName) throws IOException {
		this.dirts = new ArrayList<Dirt>();
		this.dumpsters = new ArrayList<Dumpster>();
		this.random = new Random();

		// open the file, read the contents, and determine
		// dimensions of the grid
		int[] dimensions = getDimensions(layoutFileName);
		this.grid = new ArrayGrid<Sprite>(dimensions[0], dimensions[1]);

		// open the file again, read the contents, and store them in grid
		Scanner sc = new Scanner(new File(layoutFileName));

		// INITIALIZE THE GRID HERE

		// Initializes a local variable to record the row number.
		int row = 0;

		// a while loop reads each row of the grid until the last row.
		while (sc.hasNextLine()) {

			String line = sc.nextLine();

			// a for loop read each cell of the row.
			for (int idx = 0; idx < dimensions[1]; idx = idx + 1) {

				// Transfers the string to character and matched to
				// the character in the Constants.
				if (line.substring(idx, idx + 1).charAt(0) == Constants.DIRT) {

					this.grid.setCell(row, idx, new Dirt(Constants.DIRT, row,
							idx, Constants.DIRT_SCORE));

					// Adds Dirt to the dirts list.
					this.dirts.add(new Dirt(Constants.DIRT, row, idx,
							Constants.DIRT_SCORE));

				} else if (line.substring(idx, idx + 1)
						.charAt(0) == Constants.CLEAN) {

					this.grid.setCell(row, idx,
							new CleanHallway(Constants.CLEAN, row, idx));

				} else if (line.substring(idx, idx + 1)
						.charAt(0) == Constants.WALL) {

					this.grid.setCell(row, idx,
							new Wall(Constants.WALL, row, idx));

				} else if (line.substring(idx, idx + 1)
						.charAt(0) == Constants.DUST_BALL) {

					this.grid.setCell(row, idx,
							new DustBall(Constants.DUST_BALL, row, idx,
									Constants.DUST_BALL_SCORE));

					// Adds DustBall to the dirts list.
					this.dirts.add(new DustBall(Constants.DUST_BALL, row, idx,
							Constants.DUST_BALL_SCORE));

				} else if (line.substring(idx, idx + 1)
						.charAt(0) == Constants.DUMPSTER) {

					this.grid.setCell(row, idx,
							new Dumpster(Constants.DUMPSTER, row, idx));

				} else if (line.substring(idx, idx + 1)
						.charAt(0) == Constants.P1) {

					this.grid.setCell(row, idx, new Vacuum(Constants.P1, row,
							idx, Constants.CAPACITY));

					// Sets vacuum1 corresponds to given P1 character, row,
					// idx of the cell on the row and capacity.
					this.vacuum1 = new Vacuum(Constants.P1, row, idx,
							Constants.CAPACITY);

					// Initializes the Under of vacuum1.
					this.vacuum1.setUnder(
							new CleanHallway(Constants.CLEAN, row, idx));

				} else if (line.substring(idx, idx + 1)
						.charAt(0) == Constants.P2) {

					this.grid.setCell(row, idx, new Vacuum(Constants.P2, row,
							idx, Constants.CAPACITY));

					// Sets vacuum2 corresponds to given P1 character, row,
					// idx of the cell on the row and capacity.
					this.vacuum2 = new Vacuum(Constants.P2, row, idx,
							Constants.CAPACITY);

					// Initializes the Under of vacuum2.
					this.vacuum2.setUnder(
							new CleanHallway(Constants.CLEAN, row, idx));

				}
			}

			// row increases by 1 after each for loop.
			row = row + 1;
		}

		// close the scanner.
		sc.close(); // close the scanner.
	}

	/**
	 * Returns the dimensions of the grid in the file named layoutFileName.
	 * 
	 * @param layoutFileName
	 *            path of the input grid file
	 * @return an array [numRows, numCols], where numRows is the number of rows
	 *         and numCols is the number of columns in the grid that corresponds
	 *         to the given input grid file
	 * @throws IOException
	 */
	private int[] getDimensions(String layoutFileName) throws IOException {

		Scanner sc = new Scanner(new File(layoutFileName));

		// find the number of columns
		String nextLine = sc.nextLine();
		int numCols = nextLine.length();

		int numRows = 1;

		// find the number of rows
		while (sc.hasNext()) {
			numRows++;
			nextLine = sc.nextLine();
		}

		sc.close();
		return new int[] { numRows, numCols };
	}

	/**
	 * Returns vacuum1 of the game.
	 * 
	 * @return an Vacuum that represents vacuum1.
	 */
	public Vacuum getVacuumOne() {
		return this.vacuum1;
	}

	/**
	 * Returns vacuum2 of the game.
	 * 
	 * @return an Vacuum that represents vacuum2.
	 */
	public Vacuum getVacuumTwo() {
		return this.vacuum2;
	}

	/**
	 * Returns the number of rows of the grid.
	 * 
	 * @return an integer that is the number of rows of the grid.
	 */
	public int getNumRows() {
		return this.grid.getNumRows();
	}

	/**
	 * Returns the number of columns of the grid.
	 * 
	 * @return an integer that is the number of columns of the grid.
	 */
	public int getNumColumns() {
		return this.grid.getNumColumns();
	}

	/**
	 * Returns the Sprite object on a particular position of the grid.
	 * 
	 * @param i
	 *            is the row number on the grid.
	 * @param j
	 *            is the column number on the grid.
	 * @return an Sprite object which sits on the particular position on the
	 *         grid.
	 */
	public Sprite getSprite(int i, int j) {
		return this.grid.getCell(i, j);
	}

	/**
	 * Returns the Grid of the caller.
	 * 
	 * @return a Grid of the caller.
	 */
	public Grid<Sprite> getGrid() {
		return grid;
	}

	/**
	 * Determines if a movement is legal or illegal based on the input. Returns
	 * false, if the movement is illegal. Returns true, if the movement is
	 * legal. If the movement is legal, call helper function Player_move_able.
	 * 
	 * @param nextMove
	 *            a character input from the keyboard.
	 * @return a boolean based on the movement is legal or illegal.
	 */
	public boolean move(char nextMove) {

		// if the input is to make vacuum1 moves to left.
		if (nextMove == Constants.P1_LEFT) {

			// Gets the row number of vacuum1.
			int vaccum_row = this.vacuum1.getRow();

			// Gets the column number of vacuum1.
			int vaccum_column = this.vacuum1.getColumn();

			// the new_row of vacuum1 are not changed.
			int new_row = vaccum_row;

			// the new_row of vacuum1 will be reduced by 1.
			int new_column = vaccum_column - 1;

			// Sets opposite_player as the character representation of vacuum2.
			char opposite_player = Constants.P2;

			// if vacuum1 resides on the left edge of the grid:
			// returns false.
			if (vaccum_column == 0) {

				return false;

			} else {

				// else, call helper function Player_move_able with
				// the given parameters.
				return Player_move_able(new_row, new_column, vaccum_row,
						vaccum_column, this.vacuum1, opposite_player);

			}

			// if the input is to make vacuum1 moves to the right.
		} else if (nextMove == Constants.P1_RIGHT) {

			// Gets the row number of vacuum1.
			int vaccum_row = this.vacuum1.getRow();

			// Gets the column number of vacuum1.
			int vaccum_column = this.vacuum1.getColumn();

			// the new_row of vacuum1 are not changed.
			int new_row = vaccum_row;

			// the new_column of vacuum1 will be increased by 1.
			int new_column = vaccum_column + 1;

			// Sets opposite_player as the character representation of vacuum2.
			char opposite_player = Constants.P2;

			// if vacuum1 resides on the right edge of the grid:
			// the index of right edged column is actually the
			// number of Columns - 1, returns false.
			if (vaccum_column == this.grid.getNumColumns() - 1) {

				return false;

			} else {

				// else, call helper function Player_move_able with
				// the given parameters.
				return Player_move_able(new_row, new_column, vaccum_row,
						vaccum_column, this.vacuum1, opposite_player);

			}

			// if the input is to make vacuum1 moves up.
		} else if (nextMove == Constants.P1_UP) {

			// Gets the row number of vacuum1.
			int vaccum_row = this.vacuum1.getRow();

			// Gets the column number of vacuum1.
			int vaccum_column = this.vacuum1.getColumn();

			// the new_row of vacuum1 will be reduced by 1.
			int new_row = vaccum_row - 1;

			// the new_column of vacuum1 are not changed.
			int new_column = vaccum_column;

			// Sets opposite_player as the character representation of vacuum2.
			char opposite_player = Constants.P2;

			// if vacuum1 resides on the top edge of the grid:
			// returns false.
			if (vaccum_row == 0) {

				return false;

			} else {

				// else, call helper function Player_move_able with
				// the given parameters.
				return Player_move_able(new_row, new_column, vaccum_row,
						vaccum_column, this.vacuum1, opposite_player);

			}

			// if the input is to make vacuum1 moves down.
		} else if (nextMove == Constants.P1_DOWN) {

			// Gets the row number of vacuum1.
			int vaccum_row = this.vacuum1.getRow();

			// Gets the row number of vacuum2.
			int vaccum_column = this.vacuum1.getColumn();

			// the new_row of vacuum1 will be increased by 1.
			int new_row = vaccum_row + 1;

			// the new_column of vacuum1 are not changed.
			int new_column = vaccum_column;

			// Sets opposite_player as the character representation of vacuum2.
			char opposite_player = Constants.P2;

			// if vacuum1 resides on the bottom edge of the grid:
			// the index of bottom edged column is actually
			// the number of rows - 1, return false.
			if (vaccum_row == this.grid.getNumRows() - 1) {

				return false;

			} else {

				// else, call helper function Player_move_able with
				// the given parameters.
				return Player_move_able(new_row, new_column, vaccum_row,
						vaccum_column, this.vacuum1, opposite_player);
			}

			// if the input is to make vacuum2 moves to left.
		} else if (nextMove == Constants.P2_LEFT) {

			// Gets the row number of vacuum2.
			int vaccum_row = this.vacuum2.getRow();

			// Gets the column number of vacuum2.
			int vaccum_column = this.vacuum2.getColumn();

			// the new_column of vacuum2 are not changed.
			int new_row = vaccum_row;

			// the new_column of vacuum2 will be reduced by 1.
			int new_column = vaccum_column - 1;

			// Sets opposite_player as the character representation of vacuum1.
			char opposite_player = Constants.P1;

			// if vacuum2 resides on the left edge of the grid:
			// returns false.
			if (vaccum_column == 0) {

				return false;

			} else {
				// else, call helper function Player_move_able
				// with the given parameters.
				return Player_move_able(new_row, new_column, vaccum_row,
						vaccum_column, this.vacuum2, opposite_player);

			}

			// if the input is to make vacuum2 moves to right.
		} else if (nextMove == Constants.P2_RIGHT) {

			// Gets the row number of vacuum2.
			int vaccum_row = this.vacuum2.getRow();

			// Gets the column number of vacuum2.
			int vaccum_column = this.vacuum2.getColumn();

			// the new_row of vacuum2 are not changed.
			int new_row = vaccum_row;

			// the new_column of vacuum2 will be increased by 1.
			int new_column = vaccum_column + 1;

			// Sets opposite_player as the character representation of vacuum1.
			char opposite_player = Constants.P1;

			// if vacuum2 resides on the right edge of the grid:
			// the index of right edged column is actually
			// the number of column - 1, return false.
			if (vaccum_column == this.grid.getNumColumns() - 1) {

				return false;

			} else {

				// else, call helper function Player_move_able
				// with the given parameters.
				return Player_move_able(new_row, new_column, vaccum_row,
						vaccum_column, this.vacuum2, opposite_player);

			}

			// if the input is to make vacuum2 moves up.
		} else if (nextMove == Constants.P2_UP) {

			// Gets the row number of vacuum2.
			int vaccum_row = this.vacuum2.getRow();

			// Gets the column number of vacuum2.
			int vaccum_column = this.vacuum2.getColumn();

			// the new_row of vacuum2 will be reduced by 1.
			int new_row = vaccum_row - 1;

			// the new_column of vacuum2 are not changed.
			int new_column = vaccum_column;

			// Sets opposite_player as the character representation of vacuum1.
			char opposite_player = Constants.P1;

			// if vacuum2 resides on the top edge of the grid:
			// returns false.
			if (vaccum_row == 0) {

				return false;

			} else {

				// else, call helper function Player_move_able
				// with the given parameters.
				return Player_move_able(new_row, new_column, vaccum_row,
						vaccum_column, this.vacuum2, opposite_player);

			}

			// if the input is to make vacuum2 moves down.
		} else if (nextMove == Constants.P2_DOWN) {

			// Gets the row number of vacuum2.
			int vaccum_row = this.vacuum2.getRow();

			// Gets the column number of vacuum2.
			int vaccum_column = this.vacuum2.getColumn();

			// the new_row of vacuum2 will be increased by 1.
			int new_row = vaccum_row + 1;

			// the new_column of vacuum2 are not changed.
			int new_column = vaccum_column;

			// Sets opposite_player as the character representation of vacuum1.
			char opposite_player = Constants.P1;

			// if vacuum2 resides on the bottom edge of the grid:
			// the index of bottom edged column is
			// actually the number of row - 1, return false
			if (vaccum_row == this.grid.getNumRows() - 1) {

				return false;

			} else {

				// else, call helper function Player_move_able with
				// the given parameters.
				return Player_move_able(new_row, new_column, vaccum_row,
						vaccum_column, this.vacuum2, opposite_player);

			}

		} else {

			// if the move is invalid, return false
			return false;

		}

	}

	/**
	 * Returns true if the game is over, otherwise false.
	 * 
	 * @return a boolean represents if the game is over or not. The decision is
	 *         made by checking the size of dirts list.
	 */
	public boolean gameOver() {

		// if nothing left in the dirts list, then the game is over.
		if (this.dirts.size() == 0) {

			return true;

		} else {

			return false;

		}
	}

	/**
	 * Returns the ID of the player who wins the game.
	 * 
	 * @return an integer that represents the ID of the player. if vacuum1 has
	 *         more score than vacuum2, then returns 1, otherwise, returns 2.
	 */
	public int getWinner() {

		if (this.vacuum1.getScore() > this.vacuum2.getScore()) {

			return 1;

		} else {

			return 2;

		}
	}

	/**
	 * A helper method, which processes the following operations: 1. Decides if
	 * the cell that vacuum tries to move to is a Wall or another vacuum, if so,
	 * returns false. 2. Decides if the vacuum can clean the cell, if the cell
	 * the vacuum tries to move to is a Wall or another vacuum. If so, clean it,
	 * otherwise, leave it.
	 * 
	 * @param new_row
	 *            the new row index of the moving vacuum after movement.
	 * @param new_column
	 *            the column row index of the moving vacuum after movement.
	 * @param vaccum_row
	 *            the row index of the moving vacuum.
	 * @param vaccum_column
	 *            the column index of the moving vacuum.
	 * @param current_one
	 *            the moving vacuum.
	 * @param opposite_player
	 *            the character representation of the other vacuum.
	 * @return a boolean that represents if a vacuum can move to another cell.
	 */
	private boolean Player_move_able(int new_row, int new_column,
			int vaccum_row, int vaccum_column, Vacuum current_one,
			char opposite_player) {

		// if tries to move to a wall or another vacuum,
		// the movement is invalid, return false.
		if (this.grid.getCell(new_row, new_column).getSymbol() == Constants.WALL
				|| this.grid.getCell(new_row, new_column)
						.getSymbol() == opposite_player) {

			return false;

		} else {

			// if the position vacuum tries to move to is a Dumpster,
			// empty the vacuum.
			if (this.grid.getCell(new_row, new_column)
					.getSymbol() == Constants.DUMPSTER) {

				// Sets the cell of where the moving vacuum originally resides
				// to
				// the object under the vacuum.
				this.grid.setCell(vaccum_row, vaccum_column,
						current_one.getUnder());

				// Moves the vacuum to a new position.
				current_one.moveTo(new_row, new_column);

				// Sets the object under the moved vacuum to the object
				// originally resides at the new position.
				current_one.setUnder(this.grid.getCell(new_row, new_column));

				// Sets the cell of the grid at the new position to the moving
				// vacuum.
				this.grid.setCell(new_row, new_column, current_one);

				// Empties the moving vacuum.
				current_one.empty();

				// Moves Dustballs randomly.
				DustBall_move();

				return true;
			}

			// if the position vacuum tries to move to is a Dirt.
			else if (this.grid.getCell(new_row, new_column)
					.getSymbol() == Constants.DIRT) {

				// if the vacuum is able to clean the Dirt:
				// removes the Dirt from the dirts list.
				// sets a CleanHallway under the moved vacuum.
				// The fullness and score of the vacuum will also be changed,
				// as specified in clean method.
				if (current_one.clean(Constants.DIRT_SCORE)) {

					// Sets the cell of where the moving vacuum originally
					// resides to the object under the vacuum.
					this.grid.setCell(vaccum_row, vaccum_column,
							current_one.getUnder());

					// Removes the Dirt from the dirts list.
					remove_dirts(Constants.DIRT, new_row, new_column);

					// Sets the cell of the grid at the new position to the a
					// CleanHallway.
					this.grid.setCell(new_row, new_column, new CleanHallway(
							Constants.CLEAN, new_row, new_column));

					// Moves the vacuum to a new position.
					current_one.moveTo(new_row, new_column);

					// Sets the object under the moved vacuum to the object
					// originally resides at the new position.
					current_one
							.setUnder(this.grid.getCell(new_row, new_column));

					// Sets the cell of the grid at the new position to the
					// moving vacuum.
					this.grid.setCell(new_row, new_column, current_one);

				} else {

					// Sets the cell of where the moving vacuum originally
					// resides to the object under the vacuum.
					this.grid.setCell(vaccum_row, vaccum_column,
							current_one.getUnder());

					// Moves the vacuum to a new position.
					current_one.moveTo(new_row, new_column);

					// Sets the object under the moved vacuum to the object
					// originally resides at the new position.
					current_one
							.setUnder(this.grid.getCell(new_row, new_column));

					// Sets the cell of the grid at the new position to the
					// moving vacuum.
					this.grid.setCell(new_row, new_column, current_one);
				}

				// Moves Dustballs randomly.
				DustBall_move();

				// always return true, because a vacuum can always move onto a
				// Dirt.
				return true;
			}

			// if the vacuum is able to clean the DustBall:
			// removes the DustBall from the dirts list.
			// sets a CleanHallway under the moved vacuum.
			// The fullness and score of the vacuum will also be changed,
			// as specified in clean method.
			else if (this.grid.getCell(new_row, new_column)
					.getSymbol() == Constants.DUST_BALL) {

				if (current_one.clean(Constants.DUST_BALL_SCORE)) {

					// Sets the cell of where the moving vacuum originally
					// resides to the object under the vacuum.
					this.grid.setCell(vaccum_row, vaccum_column,
							current_one.getUnder());

					// Removes the DustBall from the dirts list.
					remove_dirts(Constants.DUST_BALL, new_row, new_column);

					// Sets the cell of the grid at the new position to the a
					// CleanHallway.
					this.grid.setCell(new_row, new_column, new CleanHallway(
							Constants.CLEAN, new_row, new_column));

					// Moves the vacuum to a new position.
					current_one.moveTo(new_row, new_column);

					// Sets the object under the moved vacuum to the object
					// originally resides at the new position.
					current_one
							.setUnder(this.grid.getCell(new_row, new_column));

					// Sets the cell of the grid at the new position to the
					// moving vacuum.
					this.grid.setCell(new_row, new_column, current_one);

				} else {

					// Sets the cell of where the moving vacuum originally
					// resides to the object under the vacuum.
					this.grid.setCell(vaccum_row, vaccum_column,
							current_one.getUnder());

					// Moves the vacuum to a new position.
					current_one.moveTo(new_row, new_column);

					// Sets the object under the moved vacuum to the object
					// originally resides at the new position.
					current_one
							.setUnder(this.grid.getCell(new_row, new_column));

					// Sets the cell of the grid at the new position to the
					// moving vacuum.
					this.grid.setCell(new_row, new_column, current_one);

				}

				// Moves Dustballs randomly.
				DustBall_move();

				// always return true, because a vacuum can always move onto a
				// DustBall.
				return true;

				// The rest situation is the moving to cell is a CleanHallway.
			} else {

				// Sets the cell of where the moving vacuum originally resides
				// to the object under the vacuum.
				this.grid.setCell(vaccum_row, vaccum_column,
						current_one.getUnder());

				// Moves the vacuum to a new position.
				current_one.moveTo(new_row, new_column);

				// Sets the object under the moved vacuum to the object
				// originally resides at the new position.
				current_one.setUnder(this.grid.getCell(new_row, new_column));

				// Sets the cell of the grid at the new position to the moving
				// vacuum.
				this.grid.setCell(new_row, new_column, current_one);

				// Moves Dustballs randomly.
				DustBall_move();

				// always return true, because a vacuum can always move onto a
				// CleanHallway.
				return true;
			}
		}

	}

	/**
	 * A helper method helps move DustBall randomly. This methods mainly
	 * processes the following operations: 1. Checks if there are any DustBalls
	 * left. 2. Determines what directions are available for the DustBall to
	 * move. 3. Leaves a Dirt on the original cell after DustBall moves away. 4.
	 * If two DustBalls meet, they merge to one DustBall.
	 */
	private void DustBall_move() {

		// Creates an ArrayList to copy this.dirts.
		List<Dirt> copied_dirt_list = new ArrayList<Dirt>();

		// Copies this.dirts by a loop.
		for (Dirt item : this.dirts) {

			copied_dirt_list.add(item);

		}

		// Loops through the dirts list.
		for (Object o : copied_dirt_list) {

			// Find the object is belong to DustBall
			if (o instanceof DustBall) {

				// Gets row number of the DustBall.
				int ball_row = ((DustBall) o).getRow();

				// Gets column number of the DustBall.
				int ball_column = ((DustBall) o).getColumn();

				// Creates a string based ArrayList to store all the possible
				// directions.
				List<String> myStringList_direction = new ArrayList<String>();

				// Tests if moving to left is valid.
				if (ball_column != 0) {
					if (this.grid.getCell(ball_row, ball_column - 1)
							.getSymbol() == Constants.DIRT
							|| this.grid.getCell(ball_row, ball_column - 1)
									.getSymbol() == Constants.CLEAN
							|| this.grid.getCell(ball_row, ball_column - 1)
									.getSymbol() == Constants.DUST_BALL) {
						myStringList_direction.add("LEFT");
					}
				}

				// Tests if moving to right is valid.
				if (ball_column != this.grid.getNumColumns() - 1) {
					if (this.grid.getCell(ball_row, ball_column + 1)
							.getSymbol() == Constants.DIRT
							|| this.grid.getCell(ball_row, ball_column + 1)
									.getSymbol() == Constants.CLEAN
							|| this.grid.getCell(ball_row, ball_column + 1)
									.getSymbol() == Constants.DUST_BALL) {
						myStringList_direction.add("RIGHT");
					}
				}

				// Tests if moving up is valid.
				if (ball_row != 0) {
					if (this.grid.getCell(ball_row - 1, ball_column)
							.getSymbol() == Constants.DIRT
							|| this.grid.getCell(ball_row - 1, ball_column)
									.getSymbol() == Constants.CLEAN
							|| this.grid.getCell(ball_row - 1, ball_column)
									.getSymbol() == Constants.DUST_BALL) {
						myStringList_direction.add("UP");
					}
				}

				// Tests if moving to down is valid.
				if (ball_row != this.grid.getNumRows() - 1) {

					if (this.grid.getCell(ball_row + 1, ball_column)
							.getSymbol() == Constants.DIRT
							|| this.grid.getCell(ball_row + 1, ball_column)
									.getSymbol() == Constants.CLEAN
							|| this.grid.getCell(ball_row + 1, ball_column)
									.getSymbol() == Constants.DUST_BALL) {
						myStringList_direction.add("DOWN");
					}
				}

				// If there are more than one possible directions, then moves
				// the DustBall randomly
				if (myStringList_direction.size() > 0) {

					// Chooses one possible direction randomly.
					int i = random.nextInt(myStringList_direction.size());

					int new_ball_row;

					int new_ball_column;

					// Sets the new row number and new column number based on
					// the randomly chosen direction.
					if (myStringList_direction.get(i) == "UP") {
						new_ball_row = ball_row - 1;
						new_ball_column = ball_column;
					} else if (myStringList_direction.get(i) == "DOWN") {
						new_ball_row = ball_row + 1;
						new_ball_column = ball_column;
					} else if (myStringList_direction.get(i) == "LEFT") {
						new_ball_row = ball_row;
						new_ball_column = ball_column - 1;
					} else {
						new_ball_row = ball_row;
						new_ball_column = ball_column + 1;
					}

					// Moves the DustBall to the new cell.
					((DustBall) o).moveTo(new_ball_row, new_ball_column);

					// Sets the cell that the DustBall originally residues to a
					// Dirt,
					// if the original cell is not occupied by a vacuum.
					if (this.grid.getCell(ball_row, ball_column)
							.getSymbol() != Constants.P1
							&& this.grid.getCell(ball_row, ball_column)
									.getSymbol() != Constants.P2) {
						this.grid.setCell(ball_row, ball_column,
								new Dirt(Constants.DIRT, ball_row, ball_column,
										Constants.DIRT_SCORE));
					}

					// Sets the vacuum under to a Dirt,
					// if the original cell is occupied by a vacuum.
					if (this.grid.getCell(ball_row, ball_column)
							.getSymbol() == Constants.P1) {
						this.vacuum1.setUnder(new Dirt(Constants.DIRT, ball_row,
								ball_column, Constants.DIRT_SCORE));
					} else if (this.grid.getCell(ball_row, ball_column)
							.getSymbol() == Constants.P2) {
						this.vacuum2.setUnder(new Dirt(Constants.DIRT, ball_row,
								ball_column, Constants.DIRT_SCORE));
					}

					// Adds the Dirt to this.dirts.
					this.dirts.add(new Dirt(Constants.DIRT, ball_row,
							ball_column, Constants.DIRT_SCORE));

					// Removes Dirt or DustBall from the.dirts list,
					// if the new cell the DustBall moves to happens to be a
					// Dirt or DustBall.
					if (this.grid.getCell(new_ball_row, new_ball_column)
							.getSymbol() == Constants.DIRT) {

						remove_dirts(Constants.DIRT, new_ball_row,
								new_ball_column);

					} else if (this.grid.getCell(new_ball_row, new_ball_column)
							.getSymbol() == Constants.DUST_BALL) {

						remove_dirts(Constants.DUST_BALL, new_ball_row,
								new_ball_column);
					}

					// Sets the cell that the DustBall moves to to the DustBall.
					this.grid.setCell(new_ball_row, new_ball_column,
							((DustBall) o));

				}
			}
		}
	}

	/**
	 * A helper method helps remove Dirt or DustBall from the.dirts ArrayList.
	 * 
	 * @param Symbol
	 *            a character represents the Dirt or DustBall.
	 * @param row
	 *            an integer represents the row that the Dirt or DustBall
	 *            residues.
	 * @param column
	 *            an integer represents the column that the Dirt or DustBall
	 *            residues.
	 */
	private void remove_dirts(char Symbol, int row, int column) {

		// Loops through this.dirts to find the matched Dirt or DustBall.
		// Removes the found Dirt or DustBall from this.dirts.
		for (int i = 0; i < this.dirts.size(); i = i + 1) {

			Dirt temp = this.dirts.get(i);

			if (temp.getSymbol() == Symbol && temp.getRow() == row
					&& temp.getColumn() == column) {

				this.dirts.remove(i);

			}
		}
	}

}
