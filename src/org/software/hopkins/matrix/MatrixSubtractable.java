package org.software.hopkins.matrix;

public interface MatrixSubtractable extends MatrixScalable, MatrixSummable {
	/**
	 * Returns a copy of this matrix minus the given matrix.
	 * @param matrix - a matrix of the same order as this one
	 * @return a copy of this matrix minus the given matrix.
	 */
	HSMatrix minus(HSMatrix matrix);

	/**
	 * Subtract the given matrix from this matrix.
	 * @param matrix - must be of the same order as this matrix
	 */
	void subtract(HSMatrix matrix);
}
