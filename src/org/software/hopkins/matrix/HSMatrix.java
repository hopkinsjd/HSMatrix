package org.software.hopkins.matrix;

import java.util.List;

/**
 * A general purpose two-dimensional m by n Integer matrix
 * where m is the number of rows and n the number of columns.
 * (HS stands for Hopkins Software).
 */
public interface HSMatrix extends Cloneable, SummableMatrix,
		SubtractableMatrix, MultiplicativeMatrix {
	/**
	 * Get the number of rows in the matrix.
	 * @return the number of rows
	 */
	int rowSize();

	/**
	 * Get the number of columns in the matrix
	 * @return the number of columns
	 */
	int columnSize();

	/**
	 * Does the given matrix have the same number of
	 * rows and columns as this matrix?
	 * @param matrix the given matrix
	 * @return whether the given matrix has the same order
	 * as this matrix.
	 */
	boolean isSameOrder(HSMatrix matrix);

	/**
	 * Get the row in the matrix with the given index.
	 * @param index of the desired row
	 * @return the desired row
	 */
	List<Float> getRow(int index);

	/**
	 * Get the list of values of the column of this matrix with the given index.
	 *
	 * @param colIndex - index of the column with the values to get.
	 * @return the list of values for this column.
	 */
	List<Float> getColumn(int colIndex);

	/**
	 * Get the entry value at the given row and column.
	 * @param row the entry's row index
	 * @param column the entry's column index
	 * @return the value of the entry
	 */
	Float getEntry(int row, int column);

	/**
	 * Indicates whether the given matrix is equal to this matrix.
	 * Two matrices are equal if they are of the same order and
	 * if each entry in one equals the corresponding entry in
	 * the other.
	 * @param matrix the given matrix
	 * @return whether the given matrix is equal to this matrix
	 */
	boolean equals (HSMatrix matrix);

	/**
	 * Make an independent copy of this matrix.
	 * @return a deep copy of this matrix.
	 */
	HSMatrix clone();

	/**
	 * Returns a transposed/inverted copy of this matrix.
	 * @return a transposed copy of this HSMatrix.
	 */
	HSMatrix transpose();
}
