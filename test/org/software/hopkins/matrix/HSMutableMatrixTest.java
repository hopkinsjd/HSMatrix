package org.software.hopkins.matrix;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HSMutableMatrixTest {
	final int ROW_SIZE = 2;
	final int COL_SIZE = 3;

	HSMutableMatrix matrix = new MutableMatrix(ROW_SIZE, COL_SIZE);
	HSMutableMatrix matrix1 = new MutableMatrix(ROW_SIZE, COL_SIZE);

	@Test
	void setEntry() {
		matrix.setEntry(1, 1, 1.0f);
		assertEquals(1, (float) matrix.getEntry(1, 1));
	}

	@Test
	void fill() {
		HSMutableMatrix matrix1 = new MutableMatrix(ROW_SIZE, COL_SIZE);
		for (int r = 0; r < ROW_SIZE; ++r) {
			for (int c = 0; c < COL_SIZE; ++c) {
				assertEquals(0.0f, (float) matrix1.getEntry(r, c));
			}
		}
		matrix1.fill(1.0f);
		for (int r = 0; r < ROW_SIZE; ++r) {
			for (int c = 0; c < COL_SIZE; ++c) {
				assertEquals(1.0f, (float) matrix1.getEntry(r, c));
			}
		}
	}

	@Test
	void setValuesIncrementedFrom() {
		matrix.setValuesIncrementedFrom(1.0f);
		assertEquals(1, (float) matrix.getEntry(0, 0));
		assertEquals(2, (float) matrix.getEntry(0, 1));
		assertEquals(ROW_SIZE * COL_SIZE,
				(float) matrix.getEntry(ROW_SIZE - 1, COL_SIZE - 1));
	}

	@Test
	void setRow() {
		List<Float> newRow = new ArrayList<>(COL_SIZE);
		newRow.add(7.0f);
		newRow.add(8.0f);
		newRow.add(9.0f);
		matrix1.setRow(0, newRow);
		assertSame(matrix1.getEntry(0, 0), newRow.get(0));
		assertSame(matrix1.getEntry(0, 1), newRow.get(1));
		assertSame(matrix1.getEntry(0, 2), newRow.get(2));

	}

	@Test
	void rowEncapsulation() {
		List<Float> theRow = matrix1.getRow(0); // 7, 8, 9
		matrix.setRow(1, theRow);
		matrix.setEntry(1, 1, 10.0f);
		assertNotSame(matrix.getEntry(1, 1), matrix1.getEntry(0, 1));
	}

	@Test
	void setColumn () {
		HSMutableMatrix matrix4 = new MutableMatrix(3, 3);
		List<Float> col1 = matrix4.getColumn(0);
		HSMutableMatrix matrix5 = new MutableMatrix(3, 3);
		matrix5.fill(5.0f);
		List<Float> col3 = matrix5.getColumn(2);
		assertNotEquals(col3, col1);
		matrix4.setColumn(0, col3);
		col1 = matrix4.getColumn(0);
		assertEquals(col3, col1);
		assertNotSame(col3, col1);
	}

	@Test
	void add() {
		matrix.setRow(0, List.of(1.0f, 2.0f, 3.0f));
		matrix.setRow(1, List.of(4.0f, -1.0f, -2.0f));
		matrix1.setRow(0, List.of(-1.0f, 2.0f, -3.0f));
		matrix1.setRow(1, List.of(-2.0f, 0.0f, 1.0f));
		HSMutableMatrix matrix3 = new MutableMatrix(ROW_SIZE, COL_SIZE);
		matrix3.setRow(0, List.of(0.0f, 4.0f, 0.0f));
		matrix3.setRow(1, List.of(2.0f, -1.0f, -1.0f));
		matrix.add(matrix1);
		assertTrue(matrix.equals(matrix3));
	}

	@Test
	void subtract() {
		HSMatrix matrix = new Matrix(3, 3);
		HSMutableMatrix matrix1 = new MutableMatrix(3, 3);
		matrix1.fill(1.0f);
		assertFalse(matrix.equals(matrix1));
		matrix1.subtract(matrix1);
		assertTrue(matrix.equals(matrix1));
	}

	@Test
	void scaleBy() {
		HSMutableMatrix matrix = new MutableMatrix(3, 3);
		matrix.fill(1.0f);
		HSMutableMatrix matrix1 = new MutableMatrix(3, 3);
		matrix1.fill(3.0f);
		assertFalse(matrix.equals(matrix1));
		matrix.scaleBy(3.0f);
		assertTrue(matrix.equals(matrix1));
	}

	@Test
	void multiplyBy() {
		HSMutableMatrix matrixA = new MutableMatrix(1, 3);
		matrixA.setRow(0, List.of(300.0f, 250.0f, 350.0f));

		HSMutableMatrix matrixB = new MutableMatrix(3, 2);
		matrixB.setRow(0, List.of(30.0f, 40.0f));
		matrixB.setRow(1, List.of(20.0f, 25.0f));
		matrixB.setRow(2, List.of(10.0f, 5.0f));

		HSMutableMatrix matrixC = new MutableMatrix(1, 2);
		matrixC.setRow(0, List.of(17500.0f, 20000.0f));

		assertFalse(matrixA.equals(matrixC));
		matrixA.multiplyBy(matrixB);
		assertTrue(matrixA.equals(matrixC));
	}

	@Test
	void invert() {
		HSMutableMatrix matrixA = new MutableMatrix(3, 3);
		matrixA.setRow(0, List.of(1.0f, 0.0f, 3.0f));
		matrixA.setRow(1, List.of(3.0f, 4.0f, 2.0f));
		matrixA.setRow(2, List.of(7.0f, 8.0f, 3.0f));

		HSMutableMatrix matrixB = new MutableMatrix(3, 3);
		matrixB.setColumn(0, List.of(1.0f, 0.0f, 3.0f));
		matrixB.setColumn(1, List.of(3.0f, 4.0f, 2.0f));
		matrixB.setColumn(2, List.of(7.0f, 8.0f, 3.0f));

		assertFalse(matrixA.equals(matrixB));
		matrixA.invert();
		assertTrue(matrixA.equals(matrixB));
	}
}