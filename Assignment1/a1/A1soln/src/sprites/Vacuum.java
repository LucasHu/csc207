package sprites;

import game.Constants;

/**
 * A subclass of Sprite defines the basic properties and methods of Vacuum. This
 * class implements the interface of Moveable. This class has four more
 * instances: The score: every time the vacuum cleans Dirt or DustBall, the
 * score is incremented. The fullness: every time the vacuum cleans Dirt or
 * DustBall, the fullness is incremented. The capacity: the maximal value of
 * fullness a vacuum can carry. The under: every Vacuum sits on top of another
 * Sprite excluding another Vacuum and Wall.
 * 
 * @author lucasminghu
 */
public class Vacuum extends Sprite implements Moveable {

	private int score = Constants.INIT_SCORE; // the score of the Vacuum
												// collects.

	private int fullness = 0; // the fullness of the Vacuum currently holds.

	private int capacity; // the capacity of the Vacuum has, which means the
							// maximal fullness of the Vacuum.

	private Sprite under; // the Sprite under the Vacuum.

	/**
	 * Creates a new Vacuum corresponds to the given symbol, row, column and
	 * capacity.
	 * 
	 * @param symbol
	 *            the character symbol of the Vacuum.
	 * @param row
	 *            the row number of the Vacuum.
	 * @param column
	 *            the column of the Vacuum.
	 * @param capacity
	 *            the capacity of the Vacuum.
	 */
	public Vacuum(char symbol, int row, int column, int capacity) {
		super(symbol, row, column);
		this.capacity = capacity;
	}

	/**
	 * Returns if the Vacuum can clean the Dirt or DustBall under it.
	 * 
	 * @param score
	 *            the score of the Dirt or DustBall under the Vacuum.
	 * @return true, if the Vacuum is able to clean the tile.
	 */
	public boolean clean(int score) {
		if (this.fullness == this.capacity) {
			return false;
		} else if (this.fullness + Constants.FULLNESS_INC > this.capacity) {
			return false;
		} else {
			this.score = this.score + score;
			this.fullness = this.fullness + Constants.FULLNESS_INC;
			return true;
		}
	}

	/**
	 * Moves the Vacuum to another position. Changes the row number of column of
	 * the Vacuum.
	 */
	public void moveTo(int row, int column) {
		this.row = row;
		this.column = column;
	}

	/**
	 * Empties the Vacuum. Changes the fullness of the Vacuum to 0.
	 */
	public void empty() {
		this.fullness = 0;
	}

	/**
	 * Returns the score of the Vacuum collects.
	 * 
	 * @return the score of the Vacuum collects as an integer.
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * Set the Sprite under the Vacuum.
	 * 
	 * @param under
	 *            theSprite under the Vacuum.
	 */
	public void setUnder(Sprite under) {
		this.under = under;
	}

	/**
	 * Return the Sprite under the Vacuum.
	 * 
	 * @return the Sprite under the Vacuum.
	 */
	public Sprite getUnder() {
		return under;
	}

}
