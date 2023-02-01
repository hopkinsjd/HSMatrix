package org.software.hopkins.matrix;

public interface MatrixTransposable {
	/**
	 * Returns a transposed/inverted copy of this matrix.
	 * @return a transposed copy of this HSMatrix.
	 */
	HSMatrix transpose();

	/**
	 * Inverts or transposes this matrix so that its columns and rows are interchanged.
	 */
	void invert();
}
