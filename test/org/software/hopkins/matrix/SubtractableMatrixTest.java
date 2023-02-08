package org.software.hopkins.matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtractableMatrixTest {

	@Test
	void minus() {
		HSMatrix matrix1 = new Matrix(3, 3, 1.0f);
		HSMatrix matrix2 = new Matrix(3, 3, 2.0f);
		assertFalse(matrix2.equals(matrix1));
		assertTrue(matrix2.minus(matrix1).equals(matrix1));
	}

	@Test
	void negative() {
		HSMatrix matrix = new Matrix(3, 3, 1.0f);
		HSMatrix matrix1 = new Matrix(3, 3, -1.0f);
		assertTrue(matrix1.equals(matrix.negative()));
		assertFalse(matrix.equals(matrix1));
	}
}