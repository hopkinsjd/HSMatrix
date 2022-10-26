package org.software.hopkins.matrix;

import java.util.ArrayList;
import java.util.List;

public class Matrix implements HSMatrix {
	protected List<List<Integer>> matrix;
	protected int rows, cols;

	/**
	 * Constructor
	 * Create a new matrix of m rows and n columns.
	 * @param mRows the number of matrix rows
	 * @param nCols the number of matrix columns
	 *
	 * Takes O(nm) time in O(nm) space.
	 */
	public Matrix(int mRows, int nCols) {
		rows = mRows;
		cols = nCols;
		matrix = new ArrayList<>(mRows);
		for (int r = 0; r < mRows; ++r) {
			matrix.add(new ArrayList<>(nCols));
			for (int c = 0; c < nCols; ++c) {
				matrix.get(r).add(0);
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
	public void setValuesIncrementedFrom(int start) {
		for (int r = 0; r < rows; ++r) {
			for (int c = 0; c < cols; ++c) {
				matrix.get(r).set(c, start++);
			}
		}
	}

	/**
	 * Get the number of rows in the matrix.
	 *
	 * @return the number of rows
	 */
	@Override
	public int rowSize() {
		return rows;
	}

	/**
	 * Get the number of columns in the matrix
	 *
	 * @return the number of columns
	 */
	@Override
	public int columnSize() {
		return cols;
	}

	/**
	 * Does the given matrix have the same number of
	 * rows and columns as this matrix?
	 *
	 * @param matrix the given matrix
	 * @return whether the given matrix has the same order
	 * as this matrix.
	 */
	@Override
	public boolean isSameOrder(HSMatrix matrix) {
		return rows == matrix.rowSize() && cols == matrix.columnSize();
	}

	/**
	 * Get the row of the matrix with the given index.
	 * Note that getting a row makes a copy of it
	 * to avoid an encapsulation violation,
	 * making it take O(n) time and O(n) space
	 * where n equals the number of columns.
	 * Use carefully only when needed.
	 *
	 * @param index of the desired row
	 * @return the desired row
	 */
	@Override
	public final List<Integer> getRow(int index) {
		List<Integer> theRow = matrix.get(index);
		return new ArrayList<>(theRow);
	}

	/**
	 * Sets the row in the matrix with the given index to a given new row.
	 *
	 * @param index  of the row to set
	 * @param newRow to use to set the row in the matrix with the given index
	 */
	@Override
	public void setRow(int index, List<Integer> newRow) {
		matrix.set(index, newRow);
	}

	/**
	 * Get the entry value at the given row and column.
	 *
	 * @param row    the entry's row index
	 * @param column the entry's column index
	 * @return the value of the entry
	 */
	@Override
	public final Integer getEntry(int row, int column) {
		List<Integer> theRow = matrix.get(row);
		return theRow.get(column);
	}

	/**
	 * Set the entry's value at the given row and column.
	 *
	 * @param row    the entry's row index
	 * @param column the entry's column index
	 */
	@Override
	public void setEntry(int row, int column, Integer value) {
		List<Integer> theRow = matrix.get(row);
		theRow.set(column, value);
	}

	/**
	 * Indicates whether the given matrix is equal to this matrix.
	 * Two matrices are equal if they are of the same order and
	 * if each entry in one equals the corresponding entry in
	 * the other.
	 *
	 * @param matrix the given matrix
	 * @return whether the given matrix is equal to this matrix
	 */
	@Override
	public boolean equals(HSMatrix matrix) {
		boolean isEqual = false;
		boolean sameOrder = isSameOrder(matrix);
		if (sameOrder) {
			isEqual = true;
			for (int i = 0; i < rows; ++i) {
				List<Integer> thisCurRow = this.matrix.get(i);
				List<Integer> givenCurRow = matrix.getRow(i);
				for (int j = 0; j < cols; j++) {
					if (!thisCurRow.get(j).equals(givenCurRow.get(j))) {
						isEqual = false;
						break;
					}
				}
			}
		}
		return isEqual;
	}

	/**
	 * Sets every value of the matrix to the given value.
	 *
	 * @param value the value given with which to fill the matrix
	 */
	@Override
	public void fill(Integer value) {
		for (List<Integer> row : matrix) {
			for (int c = 0; c < cols; c++) {
				row.set(c, value);
			}
		}
	}

	/**
	 * Converts the matrix to a string representation.
	 *
	 * @return the string representing the matrix.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < rows; ++r) {
			for (int c = 0; c < cols; ++c) {
				sb.append(matrix.get(r).get(c));
				sb.append('\t');
			}
			sb.append('\n');
		}
		return sb.toString();
	}

	/**
	 * If this matrix and the given matrix are of the same order (same number of rows and columns),
	 * returns a new matrix of the matrices two added together or null otherwise.
	 *
	 * @param matrix a given matrix to add to this matrix
	 * @return the sum of this matrix and the given one as a new Matrix of the same order or
	 * null if they are not of the same order.
	 * Runs in O(nm) time with O(nm) space
	 */
	@Override
	public HSMatrix plus(HSMatrix matrix) {
		HSMatrix matrixSum = null;

		if (isSameOrder(matrix)) {
			matrixSum = new Matrix(rows, cols);

			for (int i = 0; i < rows; ++i) {
				List<Integer> thisCurRow = getRow(i);
				List<Integer> givenCurRow = matrix.getRow(i);
				List<Integer> sumCurRow = matrixSum.getRow(i);
				for (int j = 0; j < cols; ++j) {
					sumCurRow.set(j, thisCurRow.get(j) + givenCurRow.get(j));
				}
				matrixSum.setRow(i, sumCurRow);
			}
		}

		return matrixSum;
	}

	/**
	 * If this matrix and the given matrix are of the same order (same number of rows and columns)
	 * Adds the given matrix to this matrix.
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
				List<Integer> thisCurRow = getRow(i);
				List<Integer> givenCurRow = matrix.getRow(i);
				for (int j = 0; j < cols; ++j) {
					thisCurRow.set(j, thisCurRow.get(j) + givenCurRow.get(j));
				}
				setRow(i, thisCurRow);
			}
		}
	}

	/**
	 * Get the sum of a given row of this matrix.
	 *
	 * @param rowIndex the given row's index
	 * @return the sum of the given row or null if index invalid
	 */
	@Override
	public Integer sumRow(int rowIndex) {
		Integer sum = 0;
		List<Integer> theRow = getRow(rowIndex);
		for (Integer val: theRow) {
			sum += val;
		}
		return sum;
	}

	/**
	 * Get the sum of the given column of this matrix.
	 *
	 * @param colIndex the given column's index
	 * @return the sum of the given column
	 */
	@Override
	public Integer sumColumn(int colIndex) {
		Integer sum = 0;

		for (int i = 0; i < rows; i++) {
			List<Integer> theRow = getRow(i);
			sum += theRow.get(0);
		}

		return sum;
	}
}
