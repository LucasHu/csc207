package game;

/**
 * A generic class of ArrayGrid<T> defines the basic properties and methods of
 * ArrayGrid<T>. This class implements the generic interface of Grid<T>. This
 * class has three more instances: The theGrid: every time the vacuum cleans
 * Dirt or DustBall, the score is incremented. The numRows: the number of rows
 * of the defined gird. The numColumns: the number of columns of the defined
 * grid.
 * 
 * @author lucasminghu
 * @param <T>
 *            a generic class T.
 */
public class ArrayGrid<T> implements Grid<T> {

	private T[][] theGrid; // theGrid is a generic array of object type T.

	private int numRows; // the row of the grid.

	private int numColumns; // the columns of the grid.

	/**
	 * Creates an ArrayGrid corresponding to the given numRows and numColumns.
	 * 
	 * @param numRows
	 *            the number of rows of the defined grid.
	 * @param numColumns
	 *            the number of columns of the defined grid.
	 */
	@SuppressWarnings("unchecked")
	public ArrayGrid(int numRows, int numColumns) {
		this.numRows = numRows;
		this.numColumns = numColumns;
		this.theGrid = (T[][]) new Object[numRows][numColumns]; // each cell on
																// the grid is
																// an object of
																// T.
	}

	/**
	 * Sets a particular cell on the grid to an object of type T.
	 */
	public void setCell(int row, int column, T item) {
		this.theGrid[row][column] = item;
	}

	/**
	 * Returns the what is on a particular cell of the grid.
	 * 
	 * @return an object of T on the particular position on the grid.
	 */
	public T getCell(int row, int column) {
		return this.theGrid[row][column];
	}

	/**
	 * Returns the number of rows the grid has.
	 * 
	 * @return an integer which is equal to the number of rows of the grid.
	 */
	public int getNumRows() {
		return this.numRows;
	}

	/**
	 * Returns the number of columns the grid has.
	 * 
	 * @return an integer which is equal to the number of columns of the grid.
	 */
	public int getNumColumns() {
		return this.numColumns;
	}

	/**
	 * Returns a boolean that correspond to if the given object is equal to the
	 * caller ArrayGrid. This method compares four things: 1. if the given
	 * object is an ArrayGrid. 2. if the numRows of the object is equal to the
	 * caller ArrayGrid. 3. if the numColumns of the object is equal to the
	 * caller ArrayGrid. 4. if the contents of the object is equal to the caller
	 * ArrayGrid.
	 */
	@SuppressWarnings("unchecked") // Suppresses unchecked T.
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof ArrayGrid)) {
			return false;
		} else if (this.numRows != ((ArrayGrid<T>) other).getNumRows()) {
			return false;
		} else if (this.numColumns != ((ArrayGrid<T>) other).getNumColumns()) {
			return false;
		} else {
			return this.theGrid.toString()
					.equals(((ArrayGrid<T>) other).toString());
		}
	}

	/**
	 * Returns the string representation of the Grid.
	 */
	@Override
	public String toString() {
		String gridlayout = "";
		for (int idx = 0; idx < this.numRows; idx = idx + 1) {
			for (int idx2 = 0; idx < this.numColumns; idx = idx + 1) {
				gridlayout = gridlayout + theGrid[idx][idx2].toString();
			}
			gridlayout = gridlayout + "\n"; // the end of each row is \n.
		}
		return gridlayout;
	}

}
