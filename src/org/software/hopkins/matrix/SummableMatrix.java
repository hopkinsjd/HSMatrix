package org.software.hopkins.matrix;

public interface SummableMatrix {
	/**
	 *	If this matrix and the given matrix are of the same order (same number of rows and columns),
	 *	returns a new matrix of the matrices two added together or null otherwise.
	 *	@param matrix a given matrix to add to this matrix
	 *	@return the sum of this matrix and the given one as a new Matrix of the same order or
	 *	null if they are not of the same order.
	 *	Runs in O(nm) time with O(nm) space
	 */
	HSMatrix plus(HSMatrix matrix);

	/**
	 * Get the sum of a given row of this matrix.
	 * @param rowIndex the given row's index
	 * @return the sum of the given row.
	 */
	Float sumRow(int rowIndex);

	/**
	 * Get the sum of the given column of this matrix.
	 * @param colIndex the given column's index
	 * @return the sum of the given column
	 */
	Float sumColumn(int colIndex);
}
