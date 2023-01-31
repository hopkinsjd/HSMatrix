package org.software.hopkins.matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScalableMatrixTest {

	@Test
	void times() {
		HSMatrix matrix = new Matrix(3, 3);
		matrix.fill(1.0f);
		HSMatrix matrix1 = new Matrix(3, 3);
		matrix1.fill(2.0f);
		HSMatrix matrix2 = matrix.times(2.0f);
		assertTrue(matrix2.equals(matrix1));
		assertTrue(!matrix2.equals(matrix));
	}

	@Test
	void scaleBy() {
		HSMatrix matrix = new Matrix(3, 3);
		matrix.fill(1.0f);
		HSMatrix matrix1 = new Matrix(3, 3);
		matrix1.fill(3.0f);
		assertTrue(!matrix.equals(matrix1));
		matrix.scaleBy(3.0f);
		assertTrue(matrix.equals(matrix1));
	}

	@Test
	void negative() {
		HSMatrix matrix = new Matrix(3, 3);
		matrix.fill(1.0f);
		HSMatrix matrix1 = new Matrix(3, 3);
		matrix1.fill(-1.0f);
		assertTrue(matrix1.equals(matrix.negative()));
		assertTrue(!matrix.equals(matrix1));
	}
}