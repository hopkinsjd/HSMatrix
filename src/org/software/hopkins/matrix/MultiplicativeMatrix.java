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
	 * Multiplies this mxn matrix A by the given nxp matrix B (A rows == B columns) and
	 * changing this matrix to a mxp product matrix C.
	 * @param matrix - a matrix with the same number of columns as this matrix has rows.
	 */
	void multiplyBy(HSMatrix matrix);
}
