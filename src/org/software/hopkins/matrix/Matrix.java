package org.software.hopkins.matrix;

import java.util.ArrayList;
import java.util.List;

public class Matrix implements HSMatrix {
	protected List<List<Float>> matrixImpl;
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
		this(mRows, nCols, 0.0f);
	}

	public Matrix(int mRows, int nCols, Float fill) {
		rows = mRows;
		cols = nCols;
		matrixImpl = new ArrayList<>(mRows);
		for (int r = 0; r < mRows; ++r) {
			matrixImpl.add(new ArrayList<>(nCols));
			for (int c = 0; c < nCols; ++c) {
				matrixImpl.get(r).add(fill);
			}
		}
	}

	public Matrix(List<List<Float>> entryMatrix) {
		matrixImpl = entryMatrix;
		rows = entryMatrix.size();
		cols = entryMatrix.get(0).size();
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
	public final List<Float> getRow(int index) {
		List<Float> theRow = matrixImpl.get(index);
		return new ArrayList<>(theRow);
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
	 * Get the entry value at the given row and column.
	 *
	 * @param row    the entry's row index
	 * @param column the entry's column index
	 * @return the value of the entry
	 */
	@Override
	public final Float getEntry(int row, int column) {
		List<Float> theRow = matrixImpl.get(row);
		return theRow.get(column);
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
				List<Float> thisCurRow = this.matrixImpl.get(i);
				List<Float> givenCurRow = matrix.getRow(i);
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
	public void fill(Float value) {
		for (List<Float> row : matrixImpl) {
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
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < rows; ++r) {
			for (int c = 0; c < cols; ++c) {
				sb.append(matrixImpl.get(r).get(c));
				sb.append('\t');
			}
			sb.append('\n');
		}
		return sb.toString();
	}

	/**
	 * Make an independent copy of this matrix.
	 * @return a deep copy of this matrix.
	 */
	@Override
	public HSMatrix clone() {
		HSMatrix clonedMatrix = new Matrix(rows, cols);
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				clonedMatrix.setEntry(r, c, this.getEntry(r, c));
			}
		}
		return clonedMatrix;
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
	 * Get the list of values of the column of this matrix with the given index.
	 *
	 * @param colIndex - index of the column with the values to get.
	 * @return the list of values for this column.
	 */
	@Override
	public List<Float> getColumn(int colIndex) {
		List<Float> column = new ArrayList<>(this.rowSize());
		for (int r = 0; r < this.rowSize(); r++) {
			column.add(this.getEntry(r, colIndex));
		}
		return column;
	}

	/*
	  SummableMatrix Interface Implementation
	 */

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
				List<Float> thisCurRow = getRow(i);
				List<Float> givenCurRow = matrix.getRow(i);
				List<Float> sumCurRow = matrixSum.getRow(i);
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
				List<Float> thisCurRow = getRow(i);
				List<Float> givenCurRow = matrix.getRow(i);
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
	public Float sumRow(int rowIndex) {
		Float sum = 0.0f;
		List<Float> theRow = getRow(rowIndex);
		for (Float val: theRow) {
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
	public Float sumColumn(int colIndex) {
		Float sum = 0.0f;

		for (int i = 0; i < rows; i++) {
			List<Float> theRow = getRow(i);
			sum += theRow.get(0);
		}

		return sum;
	}

	/*
	  Scalable Matrix Interface Implementation
	 */

	/**
	 * Multiply a copy of this matrix by the given scalar.
	 * @param scalar - the number to multiply each element of the matrix by.
	 * @return a copy of this HSMatrix with each element multiplied by the given scalar.
	 *
	 */
	@Override
	public HSMatrix times(Float scalar) {
		HSMatrix matrixCopy = this.clone();
		matrixCopy.scaleBy(scalar);
		return matrixCopy;
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

	@Override
	public HSMatrix negative() {
		return this.times(-1.0f);
	}

	/*
	  Subtractable Matrix Interface Implementation
	 */

	/**
	 * Returns a copy of this matrix minus the given matrix.
	 * @param matrix - a matrix of the same order as this one
	 * @return a copy of this matrix minus the given matrix.
	 */
	@Override
	public HSMatrix minus(HSMatrix matrix) {
		return this.plus(matrix.negative());
	}

	@Override
	public void subtract(HSMatrix matrix) {
		this.add(matrix.negative());
	}

	/*
	  Transposable Matrix Interface Implementation
	 */

	/**
	 * Returns a transposed/inverted copy of this matrix.
	 *
	 * @return a transposed copy of this HSMatrix.
	 */
	@Override
	public HSMatrix transpose() {
		HSMatrix matrix1 = new Matrix(cols, rows);
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				matrix1.setEntry(c, r, this.getEntry(r, c));
			}
		}
		return matrix1;
	}

	/**
	 * Inverts or transposes this matrix so that its columns and rows are interchanged
	 */
	@Override
	public void invert() {
		HSMatrix matrix1 = this.transpose();
		rows = matrix1.rowSize();
		cols = matrix1.columnSize();
		matrixImpl.clear();
		for (int r = 0; r < rows; r++) {
			matrixImpl.add(new ArrayList<>());
			List<Float> row = matrixImpl.get(r);
			for (int c = 0; c < cols; c++) {
				row.add(matrix1.getEntry(r, c));
			}
		}
	}
}
