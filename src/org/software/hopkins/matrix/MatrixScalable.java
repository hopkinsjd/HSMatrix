package org.software.hopkins.matrix;


public interface MatrixScalable {
	/**
	 * Multiply a copy of this matrix by the given scalar.
	 * @param scalar - a real number
	 * @return a copy of this HSMatrix with each element multiplied by the given scalar.
	 *
	 */
	HSMatrix times(Float scalar);

	/**
	 * Scale this matrix by the given scalar number.
	 * @param scalar - a real number
	 */
	void scaleBy(Float scalar);

	/**
	 * Returns the negative of this matrix.
	 * @return a copy of this matrix, scaled by -1.0.
	 */
	HSMatrix negative();
}
