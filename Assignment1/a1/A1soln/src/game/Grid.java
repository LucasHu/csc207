package game;

/**
 * A generic interface defines a two dimensional grid of objects of type T. This
 * interface that lists the following methods: setCell, getCell, getNumRows,
 * getNumColmns, equals and toString. Classes implement this interface should
 * have all the methods.
 * 
 * @author lucasminghu
 * @param <T>
 *            a generic class T.
 */
public interface Grid<T> {

	public void setCell(int row, int column, T item);

	public T getCell(int row, int column);

	public int getNumRows();

	public int getNumColumns();

	public boolean equals(Object other);

	public String toString();
}
