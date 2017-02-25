package sprites;

/**
 * A subclass of Dirt defines the basic properties and methods of DustBall. This
 * class implements the interface of Moveable.
 * 
 * @author lucasminghu
 */
public class DustBall extends Dirt implements Moveable {

	/**
	 * Creates a new DustBall corresponds to the given symbol, row, column and
	 * value.
	 * 
	 * @param symbol
	 *            the character symbol of the DustBall.
	 * @param row
	 *            the row number of the DustBall.
	 * @param column
	 *            the column of the DustBall.
	 * @param value
	 *            the value of the DustBall.
	 */
	public DustBall(char symbol, int row, int column, int value) {
		super(symbol, row, column, value);
	}

	/**
	 * Moves the DustBall to another position. Changes the row number of column
	 * of the DustBall.
	 */
	public void moveTo(int row, int column) {
		this.row = row;
		this.column = column;
	}
}
