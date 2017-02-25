package sprites;

/**
 * A subclass of Sprite defines the basic properties and methods of Dirt. This
 * class has one extra instance: the value of the Dirt. this class also has one
 * extra methods: return the value of the Dirt.
 * 
 * @author lucasminghu
 *
 */
public class Dirt extends Sprite {

	protected int value; // the value of the Dirt.

	/**
	 * Creates a new Dirt corresponds to the given symbol, row, column and
	 * value.
	 * 
	 * @param symbol
	 *            the character symbol of the Dirt.
	 * @param row
	 *            the row number of the Dirt.
	 * @param column
	 *            the column of the Dirt.
	 * @param value
	 *            the value of the Dirt.
	 */
	public Dirt(char symbol, int row, int column, int value) {

		super(symbol, row, column); // inherit the superclass.

		this.value = value; // only Dirt and DustBall has value.

	}

	/**
	 * Returns the value of the Dirt.
	 * 
	 * @return the value of the Dirt as an integer.
	 */
	public int getValue() {

		return this.value;

	}
}
