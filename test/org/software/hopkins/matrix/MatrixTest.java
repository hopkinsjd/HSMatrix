package org.software.hopkins.matrix;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
	final int ROW_SIZE = 2;
	final int COL_SIZE = 3;

	HSMatrix matrix = new Matrix(ROW_SIZE, COL_SIZE);
	HSMatrix matrix1 = new Matrix(ROW_SIZE, COL_SIZE);
	HSMatrix matrix2 = new Matrix(COL_SIZE, ROW_SIZE);

	@BeforeEach
	void setUp() {

	}

	@Test
	void rowSize() {
		assertEquals(ROW_SIZE, matrix.rowSize());
	}

	@Test
	void columnSize() {
		assertEquals(COL_SIZE, matrix.columnSize());
	}

	@Test
	void getEntry() {
		assertEquals(0, (float) matrix.getEntry(0, 0));
	}

	@Test
	void setEntry() {
		matrix.setEntry(1, 1, 1.0f);
		assertEquals(1, (float) matrix.getEntry(1, 1));
	}

	@Test
	void fill() {
		HSMatrix matrix1 = new Matrix(ROW_SIZE, COL_SIZE);
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
	void isSameOrder() {
		assertTrue(matrix.isSameOrder(matrix1));
		assertFalse(matrix.isSameOrder(matrix2));
	}

	@Test
	void testEquals() {
		assertTrue(matrix.equals(matrix1));
		assertFalse(matrix2.equals(matrix1));
		matrix.fill(1.0f);
		assertFalse(matrix.equals(matrix1));
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
	void getRow() {
		List<Float> aRow = matrix.getRow(0);
		assertEquals(COL_SIZE, aRow.size());
		assertSame(aRow.get(2), matrix.getEntry(0, 2));
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
	void getRowEncapsulation() {
		List<Float> theRow = matrix1.getRow(0); // 7, 8, 9
		matrix.setRow(1, theRow);
		matrix.setEntry(1, 1, 10.0f);
		assertNotSame(matrix.getEntry(1, 1), matrix1.getEntry(0, 1));
	}
	
	@Test
	void copying () {
		HSMatrix matrixCopy = matrix.clone();
		assertNotSame(matrix, matrixCopy);
		assertTrue(matrix.equals(matrixCopy));
		assertSame(matrix.getEntry(0, 0), matrixCopy.getEntry(0, 0));
		matrixCopy.setEntry(0,0, 100.0f);
		assertNotSame(matrix.getEntry(0, 0), matrixCopy.getEntry(0, 0));

	}

	@Test
	void getAndSetColumn () {
		HSMatrix matrix4 = new Matrix(3, 3, 1.0f);
		List<Float> col1 = matrix4.getColumn(0);
		List<Float> col2 = matrix4.getColumn(1);
		assertEquals(col1, col2);
		HSMatrix matrix5 = new Matrix(3, 3, 5.0f);
		List<Float> col3 = matrix5.getColumn(2);
		assertNotEquals(col3, col1);
		matrix4.setColumn(0, col3);
		col1 = matrix4.getColumn(0);
		assertEquals(col3, col1);
		assertNotSame(col3, col1);
	}

}