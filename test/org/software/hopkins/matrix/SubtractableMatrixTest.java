package org.software.hopkins.matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtractableMatrixTest {

	@Test
	void minus() {
		HSMatrix matrix1 = new Matrix(3, 3, 1.0f);
		HSMatrix matrix2 = new Matrix(3, 3, 2.0f);
		assertTrue(!matrix2.equals(matrix1));
		assertTrue(matrix2.minus(matrix1).equals(matrix1));
	}

	@Test
	void subtract() {
		HSMatrix matrix = new Matrix(3, 3);
		HSMatrix matrix1 = new Matrix(3, 3, 1.0f);
		assertTrue(!matrix.equals(matrix1));
		matrix1.subtract(matrix1);
		assertTrue(matrix.equals(matrix1));
	}
}