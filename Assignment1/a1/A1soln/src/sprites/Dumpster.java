package sprites;

/**
 * A subclass of Sprite defines the basic properties and methods of Dumpster.
 * 
 * @author lucasminghu
 */
public class Dumpster extends Sprite {

	/**
	 * Creates a new Dumpster corresponds to the given symbol, row and column.
	 * 
	 * @param symbol
	 *            the character symbol of the Dumpster.
	 * @param row
	 *            the row number of the Dumpster.
	 * @param column
	 *            the column of the Dumpster.
	 */
	public Dumpster(char symbol, int row, int column) {
		super(symbol, row, column); // inherit the superclass
	}
}
