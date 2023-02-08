package org.software.hopkins.matrix.future;

import org.software.hopkins.matrix.Matrix;

public class MutableMatrix extends Matrix {
	/**
	 * Constructor
	 * Create a new matrix of m rows and n columns.
	 *
	 * @param mRows the number of matrix rows
	 * @param nCols the number of matrix columns
	 *              <p>
	 *              Takes O(nm) time in O(nm) space.
	 */
	public MutableMatrix(int mRows, int nCols) {
		super(mRows, nCols);
	}
}
