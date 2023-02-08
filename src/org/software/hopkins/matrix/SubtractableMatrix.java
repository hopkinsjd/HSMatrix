package org.software.hopkins.matrix;

public interface SubtractableMatrix extends SummableMatrix {
	/**
	 * Returns a copy of this matrix minus the given matrix.
	 * @param matrix - a matrix of the same order as this one
	 * @return a copy of this matrix minus the given matrix.
	 */
	HSMatrix minus(HSMatrix matrix);

	/**
	 * Returns the negative of this matrix.
	 * @return a copy of this matrix, scaled by -1.0.
	 */
	HSMatrix negative();
}
