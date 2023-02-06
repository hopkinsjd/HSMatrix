package org.software.hopkins.matrix;

public interface SubtractableMatrix extends ScalableMatrix, SummableMatrix {
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
