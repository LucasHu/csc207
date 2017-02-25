package sprites;

/**
 * A subclass of Sprite defines the basic properties and methods of
 * CleanHallway.
 */
public class CleanHallway extends Sprite {

	/**
	 * Creates a new CleanHallway corresponds to the given symbol, row and
	 * column.
	 * 
	 * @param symbol
	 *            the character symbol of the CleanHallway.
	 * @param row
	 *            the row number of the CleanHallway.
	 * @param column
	 *            the column of the CleanHallway.
	 */
	public CleanHallway(char symbol, int row, int column) {
		super(symbol, row, column); // inherit the superclass
	}
}
