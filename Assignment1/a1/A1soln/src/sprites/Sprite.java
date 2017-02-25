package sprites;

/**
 * An abstract super class defines the basic properties and methods of Sprite.
 * This class is a superclass, and this class is responsible for performing the
 * following operations: 1. Get the character symbol of the sprite. 2. Get the
 * row number of the sprite. 3. Get the column number of the sprite. 4. Get the
 * symbol of the sprite in a string format.
 * 
 * @author lucasminghu
 */
public abstract class Sprite {

	protected char symbol; // the symbol of the sprite

	protected int row; // the row of the sprite

	protected int column; // the row of the sprite

	/**
	 * Creates a new Sprite corresponds to the given symbol, row and column.
	 * 
	 * @param symbol
	 *            the character symbol of the sprite.
	 * @param row
	 *            the row of the sprite on the grid.
	 * @param column
	 *            the column of the sprite on the grid.
	 */
	public Sprite(char symbol, int row, int column) {
		this.symbol = symbol;
		this.row = row;
		this.column = column;
	}

	/**
	 * Returns the symbol of the sprite.
	 * 
	 * @return the symbol of the sprite as a character.
	 */
	public char getSymbol() {
		return this.symbol;
	}

	/**
	 * Returns the row number of the sprite.
	 * 
	 * @return the row number of the sprite as an integer.
	 */
	public int getRow() {
		return this.row;
	}

	/**
	 * Returns the column number of the sprite.
	 * 
	 * @return the column number of the sprite as an integer.
	 */
	public int getColumn() {
		return this.column;
	}

	@Override
	/**
	 * Return the symbol of the sprite
	 * 
	 * @return the symbol of the sprite as a string.
	 */
	public String toString() {
		return Character.toString(this.symbol);
	}

}
