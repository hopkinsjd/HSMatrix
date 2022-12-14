package org.software.hopkins.matrix;

public interface MatrixSummable {
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
	 * If this matrix and the given matrix are of the same order (same number of rows and columns)
	 * Adds the given matrix to this matrix.
	 * Throws an IllegalArgumentException if the given matrix is not of the same order as this matrix.
	 * @param matrix a given matrix to add to this matrix
	 * Runs in O(nm) time and O(1) space.
	 */
	void add(HSMatrix matrix);

	/**
	 * Get the sum of a given row of this matrix.
	 * @param rowIndex the given row's index
	 * @return the sum of the given row.
	 */
	Integer sumRow(int rowIndex);

	/**
	 * Get the sum of the given column of this matrix.
	 * @param colIndex the given column's index
	 * @return the sum of the given column
	 */
	Integer sumColumn(int colIndex);
}
