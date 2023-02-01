package org.software.hopkins.matrix;

import java.util.List;

/**
 * A general purpose two-dimensional m by n Integer matrix
 * where m is the number of rows and n the number of columns.
 * (HS stands for Hopkins Software).
 */
public interface HSMatrix extends Cloneable, MatrixSummable,
		MatrixSubtractable, MatrixTransposable {
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
	 * Sets the row in the matrix with the given index to a given new row.
	 * @param index of the row to set
	 * @param newRow to use to set the row in the matrix with the given index
	 */
	void setRow(int index, List<Float> newRow);

	/**
	 * Get the entry value at the given row and column.
	 * @param row the entry's row index
	 * @param column the entry's column index
	 * @return the value of the entry
	 */
	Float getEntry(int row, int column);

	/**
	 * Set the entry's value at the given row and column.
	 * @param row the entry's row index
	 * @param column the entry's column index
	 */
	void setEntry(int row, int column, Float value);

	/**
	 * Sets the values of the matrix to incremental values from the given start number.
	 * @param start with this value at matrix[0][0] and increment subsequent values
	 *              going from left to right and top to bottom.
	 */
	void setValuesIncrementedFrom(Float start);

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
	 * Sets every value of the matrix to the given value.
	 * @param value the value given with which to fill the matrix
	 */
	void fill(Float value);

	/**
	 * Make an independent copy of this matrix.
	 * @return a deep copy of this matrix.
	 */
	HSMatrix clone();

	/**
	 * Set the values in the column with the given index for this matrix.
	 * If the length of the values list is less than the matrix column,
	 * the method sets as many of the values as it can up to the number of values.
	 * If the length of the values list is greater than the matrix column.,
	 * the method only sets the values that it can up to the number of rows.
	 *
	 * @param colIndex - the index of the column of this matrix to set.
	 * @param values to assign this column.
	 */
	void setColumn(int colIndex, List<Float> values);

	/**
	 * Get the list of values of the column of this matrix with the given index.
	 *
	 * @param colIndex - index of the column with the values to get.
	 * @return the list of values for this column.
	 */
	List<Float> getColumn(int colIndex);
}
