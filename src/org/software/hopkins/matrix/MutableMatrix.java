package org.software.hopkins.matrix;

import java.util.ArrayList;
import java.util.List;

public class MutableMatrix extends Matrix implements HSMutableMatrix {
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

	/**
	 * Set the entry's value at the given row and column.
	 *
	 * @param row    the entry's row index
	 * @param column the entry's column index
	 */
	@Override
	public void setEntry(int row, int column, Float value) {
		List<Float> theRow = matrixImpl.get(row);
		theRow.set(column, value);
	}

	/**
	 * Sets every value of the matrix to the given value.
	 *
	 * @param value the value given with which to fill the matrix
	 */
	@Override
	public void fill(Float value) {
		for (List<Float> row : matrixImpl) {
			for (int c = 0; c < cols; c++) {
				row.set(c, value);
			}
		}
	}

	/**
	 * Sets the values of the matrix to incremental values from the given start number.
	 *
	 * @param start with this value at matrix[0][0] and increment subsequent values
	 *              going from left to right and top to bottom.
	 *              Runs in O(nm) time and O(1) space.
	 */
	@Override
	public void setValuesIncrementedFrom(Float start) {
		for (int r = 0; r < rows; ++r) {
			for (int c = 0; c < cols; ++c) {
				matrixImpl.get(r).set(c, start++);
			}
		}
	}

	/**
	 * Sets the row in the matrix with the given index to a given new row.
	 *
	 * @param index  of the row to set
	 * @param newRow to use to set the row in the matrix with the given index
	 */
	@Override
	public void setRow(int index, List<Float> newRow) {
		matrixImpl.set(index, newRow);
	}

	/**
	 * Set the values in the column with the given index for this matrix.
	 * If the length of the values list is less than the matrix column,
	 * the method sets as many of the values as it can up to the number of values.
	 * If the length of the values list is greater than the matrix column.,
	 * the method only sets the values that it can up to the number of rows.
	 *
	 * @param colIndex - the index of the column of this matrix to set.
	 * @param values to assign this column.
	 */
	@Override
	public void setColumn(int colIndex, List<Float> values) {
		int indexLimit = values.size() < this.rowSize() ? values.size() : this.rowSize();
		for (int r = 0; r < indexLimit; ++r) {
			this.setEntry(r, colIndex, values.get(r));
		}
	}

	/**
	 * Adds the given matrix to this matrix if this matrix and the given matrix
	 * are of the same order (same number of rows and columns).
	 * Throws an IllegalArgumentException if the given matrix is not of the same order as this matrix.
	 *
	 * @param matrix a given matrix to add to this matrix
	 *               Runs in O(nm) time and O(1) space.
	 */
	@Override
	public void add(HSMatrix matrix) {
		if (!isSameOrder(matrix))
			throw new IllegalArgumentException("Cannot sum matrices. The given matrix is not the same order as this matrix.");
		else {
			for (int i = 0; i < rows; ++i) {
				List<Float> thisCurRow = getRow(i);
				List<Float> givenCurRow = matrix.getRow(i);
				for (int j = 0; j < cols; ++j) {
					thisCurRow.set(j, thisCurRow.get(j) + givenCurRow.get(j));
				}
				setRow(i, thisCurRow);
			}
		}
	}

	@Override
	public void subtract(HSMatrix matrix) {
		this.add(matrix.negative());
	}

	@Override
	public void scaleBy(Float scalar) {
		for (int r = 0; r < rows; ++r) {
			for (int c = 0; c < cols; ++c) {
				Float element = this.getEntry(r, c);
				this.setEntry(r, c, element * scalar);
			}
		}
	}

	/**
	 * Multiplies this mxn matrix A by the given nxp matrix B (A rows == B columns) and
	 * changing this matrix to a mxp product matrix C.
	 *
	 * @param matrixB - a matrix with the same number of columns as this matrix has rows.
	 */
	@Override
	public void multiplyBy(HSMatrix matrixB) {
		if (this.columnSize() != matrixB.rowSize()) {
			throw new IllegalArgumentException("Can't multiply by given matrix. Its row size does not equal this matrix's column size.");
		} else {
			HSMatrix matrixA = this.clone();
			rows = matrixA.rowSize();
			cols = matrixB.columnSize();
			matrixImpl.clear();
			for (int i = 0; i < this.rowSize(); i++) {
				matrixImpl.add(new ArrayList<>(this.columnSize()));
				for (int j = 0; j < this.columnSize(); j++) {
					float Cij = 0.0f;
					for (int c = 0; c < matrixA.columnSize(); c++) {
						Cij += matrixA.getEntry(i, c) * matrixB.getEntry(c, j);
					}
					matrixImpl.get(i).add(Cij);
				}
			}
		}
	}

	/**
	 * Inverts or transposes this matrix so that its columns and rows are interchanged.
	 */
	@Override
	public void invert() {
		HSMatrix matrixCopy = this.clone();
		rows = matrixCopy.columnSize();
		cols = matrixCopy.rowSize();
		matrixImpl.clear();
		for (int r = 0; r < rows; r++) {
			List<Float> curRow = new ArrayList<>(cols);
			for (int c = 0; c < cols; c++) {
				curRow.add(matrixCopy.getEntry(c, r));
			}
			matrixImpl.add(curRow);
		}
	}
}
