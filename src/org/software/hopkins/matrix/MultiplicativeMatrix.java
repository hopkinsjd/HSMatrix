package org.software.hopkins.matrix;

public interface MultiplicativeMatrix {
	/**
	 * Multiplies this mxn matrix A by the given nxp matrix B (A rows == B columns) and
	 * produces a mxp product matrix C. Does not change this matrix.
	 * @param matrix - a matrix with the same number of columns as this matrix has rows.
	 * @return - the resulting product matrix.
	 */
	HSMatrix times(HSMatrix matrix);

	/**
	 * Multiply a copy of this matrix by the given scalar.
	 * @param scalar - a real number
	 * @return a copy of this HSMatrix with each element multiplied by the given scalar.
	 *
	 */
	HSMatrix times(Float scalar);

}
