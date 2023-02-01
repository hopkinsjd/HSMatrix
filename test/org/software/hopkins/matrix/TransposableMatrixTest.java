package org.software.hopkins.matrix;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TransposableMatrixTest {

	@Test
	void transpose() {
		HSMatrix matrix = new Matrix(2, 3);
		Float start = 1.0f;
		matrix.setValuesIncrementedFrom(start);
		HSMatrix matrix1 = new Matrix(3, 2);

		for (int c = 0; c < matrix1.columnSize(); c++) {
			for (int r = 0; r < matrix1.rowSize(); r++) {
				matrix1.setEntry(r, c, start++);
			}
		}
		assertFalse(matrix.equals(matrix1));
		assertTrue(matrix.transpose().equals(matrix1));
	}

	@Test
	void invert() {
		HSMatrix matrixA = new Matrix(3, 3);
		matrixA.setRow(0, Arrays.asList(1.0f, 0.0f, 3.0f));
		matrixA.setRow(1, Arrays.asList(3.0f, 4.0f, 2.0f));
		matrixA.setRow(2, Arrays.asList(7.0f, 8.0f, 3.0f));

		HSMatrix matrixB = new Matrix(3, 3);
		matrixB.setColumn(0, Arrays.asList(1.0f, 0.0f, 3.0f));
		matrixB.setColumn(1, Arrays.asList(3.0f, 4.0f, 2.0f));
		matrixB.setColumn(2, Arrays.asList(7.0f, 8.0f, 3.0f));

		assertFalse(matrixA.equals(matrixB));
		matrixA.invert();
		assertTrue(matrixA.equals(matrixB));
	}
}