package org.software.hopkins.matrix.future;

import java.util.List;

/**
 * A square 2n matrix class which has the same number of rows and columns
 * which are always even in size (twice the given initial size n).
 * This allows game operations which flip columns or rows.
 */
public class Square2nMatrix extends SquareMatrix {
	Square2nMatrix(int n) {
		super(n);
	}

	/**
	 * Swap the value at the given first row and column with that of the second row and column.
	 * @param val1Row row of the first given value
	 * @param val1Col column of the first given value
	 * @param val2Row row of the second given value
	 * @param val2Col column of the second given value
	 */
	public void swapValues(int val1Row, int val1Col, int val2Row, int val2Col) {
		float tmp;
		List<Float> row1 = matrix.get(val1Row);
		List<Float> row2 = matrix.get(val2Row);
		tmp = row1.get(val1Col);
		row1.set(val1Col, row2.get(val2Col));
		row2.set(val2Col, tmp);
	}

	/**
	 * Flip the given matrix row by swapping its outer values, working inward.
	 * @param rowIndex index of the row to flip
	 */
	public void flipRow(int rowIndex) {
		for (int left = 0, right = cols - 1; left < right; ++left, --right) {
			swapValues(rowIndex, left, rowIndex, right);
		}
	}

	/**
	 * Flip the given matrix column by swapping its topmost and bottommost values, working inward.
	 * @param colIndex index of the column to flip
	 */
	public void flipColumn(int colIndex) {
		for (int top = 0, bottom = rows - 1; top < bottom; ++top, --bottom) {
			swapValues(top, colIndex, bottom, colIndex);
		}
	}

	public int sumRowLeft(int rowIndex) {
		int sum = 0;

		if (rowIndex >= 0 && rowIndex < size) {
			List<Float> theRow = getRow(rowIndex);
			for (int i = 0; i < size/2; ++i) {
				sum += theRow.get(i);
			}
		}

		return sum;
	}

	public int sumRowRight(int rowIndex) {
		int sum = 0;

		if (rowIndex >= 0 && rowIndex < size) {
			List<Float> theRow = getRow(rowIndex);
			for (int i = size/2; i < size; ++i) {
				sum += theRow.get(i);
			}
		}

		return sum;
	}

	public int sumColumnTop(int colIndex) {
		int sum = 0;

		if (colIndex >= 0 && colIndex < size) {
			for (int i = 0; i < size/2; ++i) {
				sum += matrix.get(i).get(0);
			}
		}

		return sum;
	}

	public int sumColumnBottom(int colIndex) {
		int sum = 0;

		if (colIndex >= 0 && colIndex < size) {
			for (int i = size/2; i < size; ++i) {
				sum += matrix.get(i).get(0);
			}
		}

		return sum;
	}

}
