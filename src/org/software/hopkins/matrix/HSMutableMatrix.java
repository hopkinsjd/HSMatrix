package org.software.hopkins.matrix;

import java.util.List;

public interface HSMutableMatrix extends HSMatrix {
	/**
	 * Set the entry's value at the given row and column.
	 * @param row the entry's row index
	 * @param column the entry's column index
	 */
	void setEntry(int row, int column, Float value);

	/**
	 * Sets every value of the matrix to the given value.
	 * @param value the value given with which to fill the matrix
	 */
	void fill(Float value);

	/**
	 * Sets the values of the matrix to incremental values from the given start number.
	 * @param start with this value at matrix[0][0] and increment subsequent values
	 *              going from left to right and top to bottom.
	 */
	void setValuesIncrementedFrom(Float start);

	/**
	 * Sets the row in the matrix with the given index to a given new row.
	 * @param index of the row to set
	 * @param newRow to use to set the row in the matrix with the given index
	 */
	void setRow(int index, List<Float> newRow);

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
	 * If this matrix and the given matrix are of the same order (same number of rows and columns)
	 * Adds the given matrix to this matrix.
	 * Throws an IllegalArgumentException if the given matrix is not of the same order as this matrix.
	 * @param matrix a given matrix to add to this matrix
	 * Runs in O(nm) time and O(1) space.
	 */
	void add(HSMatrix matrix);

	/**
	 * Subtract the given matrix from this matrix.
	 * @param matrix - must be of the same order as this matrix
	 */
	void subtract(HSMatrix matrix);

	/**
	 * Scale this matrix by the given scalar number.
	 * @param scalar - a real number
	 */
	void scaleBy(float scalar);

	/**
	 * Multiplies this mxn matrix A by the given nxp matrix B (A rows == B columns) and
	 * changing this matrix to a mxp product matrix C.
	 * @param matrix - a matrix with the same number of columns as this matrix has rows.
	 */
	void multiplyBy(HSMatrix matrix);

	/**
	 * Inverts or transposes this matrix so that its columns and rows are interchanged.
	 */
	void invert();

}
