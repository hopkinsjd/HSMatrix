package org.software.hopkins.matrix;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
	final int ROW_SIZE = 2;
	final int COL_SIZE = 3;

	HSMatrix matrix = new Matrix(ROW_SIZE, COL_SIZE);
	HSMatrix matrix1 = new Matrix(ROW_SIZE, COL_SIZE, 1.0f);
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
	void isSameOrder() {
		assertTrue(matrix.isSameOrder(matrix1));
		assertFalse(matrix.isSameOrder(matrix2));
	}

	@Test
	void testEquals() {
		HSMatrix matrix3 = new Matrix(ROW_SIZE, COL_SIZE, 1.0F);
		assertTrue(matrix3.equals(matrix1));
		assertFalse(matrix2.equals(matrix1));
		assertFalse(matrix.equals(matrix1));
	}

	@Test
	void getRow() {
		List<Float> aRow = matrix.getRow(0);
		assertEquals(COL_SIZE, aRow.size());
		assertSame(aRow.get(2), matrix.getEntry(0, 2));
	}

	@Test
	void copying () {
		HSMatrix matrixCopy = matrix.clone();
		assertNotSame(matrix, matrixCopy);
		assertTrue(matrix.equals(matrixCopy));
		assertSame(matrix.getEntry(0, 0), matrixCopy.getEntry(0, 0));
	}

	@Test
	void getColumn () {
		HSMatrix matrix4 = new Matrix(3, 3, 1.0f);
		List<Float> col1 = matrix4.getColumn(0);
		List<Float> col2 = matrix4.getColumn(1);
		assertEquals(col1, col2);
	}

	@Test
	void transpose() {
		HSMatrix matrix = new Matrix(2, 3, 1.0f, true);
		HSMatrix matrix1 = matrix.transpose();

		assertFalse(matrix.equals(matrix1));
		assertEquals(matrix.rowSize(), matrix1.columnSize());
		assertEquals(matrix.columnSize(), matrix1.rowSize());
		HSMatrix matrix2 = matrix1.transpose();
		assertTrue(matrix.equals(matrix2));
	}
}