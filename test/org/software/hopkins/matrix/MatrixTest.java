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
	HSMatrix matrix1 = new Matrix(ROW_SIZE, COL_SIZE, 1.0f);
	HSMatrix matrix2 = new Matrix(COL_SIZE, ROW_SIZE);

	@BeforeEach
	void setUp() {

	}

	@Test
	void constructors() {
		HSMatrix zeroMatrix = new Matrix(ROW_SIZE, COL_SIZE);
		HSMatrix zeroMatrix1 = new Matrix(ROW_SIZE, COL_SIZE, 0.0f);
		HSMatrix zeroMatrix2 = new Matrix(ROW_SIZE, COL_SIZE, 0.0f, false);
		List<List<Float>> zeroListMatrix = new ArrayList<>(ROW_SIZE);
		for (int r = 0; r < ROW_SIZE; r++) {
			List<Float> curRow = new ArrayList<>(COL_SIZE);
			for (int c = 0; c < COL_SIZE; c++) {
				curRow.add(0.0f);
			}
			zeroListMatrix.add(curRow);
		}
		HSMatrix zeroMatrix3 = new Matrix(zeroListMatrix);
		Float[][] zeroArrayMatrix = new Float[ROW_SIZE][COL_SIZE];
		for (int r = 0; r < ROW_SIZE; r++) {
			for (int c = 0; c < COL_SIZE; c++) {
				zeroArrayMatrix[r][c] = 0.0f;
			}
		}
		HSMatrix zeroMatrix4 = new Matrix(zeroArrayMatrix);
		assertTrue(zeroMatrix.equals(zeroMatrix1));
		assertTrue(zeroMatrix1.equals(zeroMatrix2));
		assertTrue(zeroMatrix2.equals(zeroMatrix3));
		assertTrue(zeroMatrix3.equals(zeroMatrix4));
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
		assertThrows(UnsupportedOperationException.class, () -> aRow.set(0, -1.0f));
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