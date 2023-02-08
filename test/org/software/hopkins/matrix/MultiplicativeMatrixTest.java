package org.software.hopkins.matrix;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MultiplicativeMatrixTest {

	@Test
	void times() {
		HSMatrix matrixA = new Matrix(1, 3);
		matrixA.setRow(0, Arrays.asList(300.0f, 250.0f, 350.0f));

		HSMatrix matrixB = new Matrix(3, 2);
		matrixB.setRow(0, Arrays.asList(30.0f, 40.0f));
		matrixB.setRow(1, Arrays.asList(20.0f, 25.0f));
		matrixB.setRow(2, Arrays.asList(10.0f, 5.0f));

		HSMatrix matrixC = new Matrix(1, 2);
		matrixC.setRow(0, Arrays.asList(17500.0f, 20000.0f));

		assertTrue(matrixA.times(matrixB).equals(matrixC));
		assertFalse(matrixA.equals(matrixC));
		assertFalse(matrixA.equals(matrixB));
	}

	@Test
	void multiplyBy() {
		HSMatrix matrixA = new Matrix(1, 3);
		matrixA.setRow(0, Arrays.asList(300.0f, 250.0f, 350.0f));

		HSMatrix matrixB = new Matrix(3, 2);
		matrixB.setRow(0, Arrays.asList(30.0f, 40.0f));
		matrixB.setRow(1, Arrays.asList(20.0f, 25.0f));
		matrixB.setRow(2, Arrays.asList(10.0f, 5.0f));

		HSMatrix matrixC = new Matrix(1, 2);
		matrixC.setRow(0, Arrays.asList(17500.0f, 20000.0f));

		assertFalse(matrixA.equals(matrixC));
		matrixA.multiplyBy(matrixB);
		assertTrue(matrixA.equals(matrixC));

	}
}