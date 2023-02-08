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

	public Matrix(int mRows, int nCols, float fill) {
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

	public Matrix(int mRows, int nCols, float val, boolean increment) {
		this(mRows, nCols, val);
		if (increment) {
			for (int r = 0; r < mRows; r++) {
				List<Float> curRow = matrixImpl.get(r);
				for (int c = 0; c < nCols; ++c) {
					curRow.set(c, val++);
				}
			}
		}
	}

	public Matrix(List<List<Float>> entryMatrix) {
		matrixImpl = entryMatrix;
		rows = entryMatrix.size();
		cols = entryMatrix.get(0).size();
	}

	public Matrix(Float[][] twoDimensionalArray) {
		rows = twoDimensionalArray.length;
		cols = twoDimensionalArray[0].length;
		matrixImpl = new ArrayList<>(rows);
		for (int r = 0; r < rows; r++) {
			List<Float> curRow = List.of(twoDimensionalArray[r]);
			matrixImpl.add(curRow);
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
		List<List<Float>> clonedMatrix = new ArrayList<>(rows);

		for (int r = 0; r < rows; r++) {
			List<Float> curRow = new ArrayList<>(cols);
			for (int c = 0; c < cols; c++) {
				curRow.add(this.getEntry(r, c));
			}
			clonedMatrix.add(curRow);
		}
		return new Matrix(clonedMatrix);
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
	 * throws an IllegalArgumentException if they are not of the same order.
	 * Runs in O(nm) time with O(nm) space
	 */
	@Override
	public HSMatrix plus(HSMatrix matrix) {
		List<List<Float>> matrixSum = new ArrayList<>(rows);

		if (isSameOrder(matrix)) {
			for (int i = 0; i < rows; ++i) {
				//List<Float> thisCurRow = this.getRow(i);
				//List<Float> givenCurRow = matrix.getRow(i);
				List<Float> sumCurRow = new ArrayList<>(cols);
				for (int j = 0; j < cols; ++j) {
					//sumCurRow.add(thisCurRow.get(j) + givenCurRow.get(j));
					sumCurRow.add(this.getEntry(i, j) + matrix.getEntry(i, j));
				}
				matrixSum.add(sumCurRow);
			}
		} else {
			throw new IllegalArgumentException("Can't add matrices. They are not the same order.");
		}

		return new Matrix(matrixSum);
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
		List<List<Float>> scaledMatrix = new ArrayList<>(rowSize());
		for (int r = 0; r < rowSize(); r++) {
			List<Float> curRow = new ArrayList<>(columnSize());
			for (int c = 0; c < columnSize(); c++) {
				curRow.add(scalar * this.getEntry(r, c));
			}
			scaledMatrix.add(curRow);
		}

		return new Matrix(scaledMatrix);
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
		List<List<Float>> transposedMatrix = new ArrayList<>(columnSize());

		for (int r = 0; r < cols; r++) {
			List<Float> curRow = new ArrayList<>(rowSize());
			for (int c = 0; c < rows; c++) {
				curRow.add(this.getEntry(c, r));
			}
			transposedMatrix.add(curRow);
		}
		return new Matrix(transposedMatrix);
	}

	/**
	 * Multiplies this mxn matrix A by the given nxp matrix B (A rows == B columns) and
	 * produces a mxp product matrix C. Does not change this matrix.
	 *
	 * @param matrixB - a matrix with the same number of columns as this matrix has rows.
	 * @return - the resulting product matrix.
	 */
	@Override
	public HSMatrix times(HSMatrix matrixB) {
		if (this.columnSize() != matrixB.rowSize()) {
			throw new IllegalArgumentException("Can't multiply by given matrix. Its row size does not equal this matrix's column size.");
		} else {
			HSMatrix matrixA = this.clone();
			List<List<Float>> productMatrix = new ArrayList<>(matrixA.rowSize());
			for (int i = 0; i < matrixA.rowSize(); i++) {
				productMatrix.add(new ArrayList<>(this.columnSize()));
				for (int j = 0; j < matrixB.columnSize(); j++) {
					float Cij = 0.0f;
					for (int c = 0; c < matrixA.columnSize(); c++) {
						Cij += matrixA.getEntry(i, c) * matrixB.getEntry(c, j);
					}
					productMatrix.get(i).add(Cij);
				}
			}
			return new Matrix(productMatrix);
		}
	}


}
