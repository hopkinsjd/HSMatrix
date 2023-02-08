package org.software.hopkins.matrix;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class MultiplicativeMatrixTest {

	@Test
	void times() {
		Float[][] arrayMatrixA = {{300.0f, 250.0f, 350.0f}};
		HSMatrix matrixA = new Matrix(arrayMatrixA);

		Float[][] arrayMatrixB = {{30.0f, 40.0f}, {20.0f, 25.0f}, {10.0f, 5.0f}};
		HSMatrix matrixB = new Matrix(arrayMatrixB);

		Float[][] arrayMatrixC = {{17500.0f, 20000.0f}};
		HSMatrix matrixC = new Matrix(arrayMatrixC);

		assertFalse(matrixA.equals(matrixC));
		assertFalse(matrixA.equals(matrixB));
		assertTrue(matrixA.times(matrixB).equals(matrixC));
	}

	@Test
	void scales() {
		HSMatrix matrix = new Matrix(3, 3, 1.0f);
		HSMatrix matrix1 = new Matrix(3, 3, 2.0f);
		HSMatrix matrix2 = matrix.times(2.0f);
		assertTrue(matrix2.equals(matrix1));
		assertFalse(matrix2.equals(matrix));
	}

}