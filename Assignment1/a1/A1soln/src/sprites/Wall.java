package sprites;

/**
 * A subclass of Sprite defines the basic properties and methods of Wall.
 * 
 * @author lucasminghu
 */
public class Wall extends Sprite {

	/**
	 * Creates a new Wall corresponds to the given symbol, row and column.
	 * 
	 * @param symbol
	 *            the character symbol of the Wall.
	 * @param row
	 *            the row number of the Wall.
	 * @param column
	 *            the column of the Wall.
	 */
	public Wall(char symbol, int row, int column) {
		super(symbol, row, column); // inherit the superclass
	}

}
