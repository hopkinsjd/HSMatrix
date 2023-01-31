package org.software.hopkins.matrix;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatrixSummableTest {
	final int ROW_SIZE = 2;
	final int COL_SIZE = 3;

	HSMatrix matrix = new Matrix(ROW_SIZE, COL_SIZE);
	HSMatrix matrix1 = new Matrix(ROW_SIZE, COL_SIZE);
	HSMatrix matrix2 = new Matrix(COL_SIZE, ROW_SIZE);
	HSMatrix matrix3 = new Matrix(ROW_SIZE, COL_SIZE);

	@Test
	void sumRow() {
		matrix.fill(2.0f);
		assertTrue(matrix.sumRow(0) == 6.0f);
	}

	@Test
	void sumColumn() {
		matrix1.setValuesIncrementedFrom(1.0f);
		assertTrue(matrix1.sumColumn(0) == 5.0f);
	}

	@Test
	void plus() {
		matrix.setRow(0, Arrays.asList(new Float[] {1.0f, 2.0f, 3.0f}));
		matrix.setRow(1, Arrays.asList(new Float[] {4.0f, -1.0f, -2.0f}));
		matrix1.setRow(0, Arrays.asList(new Float[] {-1.0f, 2.0f, -3.0f}));
		matrix1.setRow(1, Arrays.asList(new Float[] {-2.0f, 0.0f, 1.0f}));
		matrix3.setRow(0, Arrays.asList(new Float[] {0.0f, 4.0f, 0.0f}));
		matrix3.setRow(1, Arrays.asList(new Float[] {2.0f, -1.0f, -1.0f}));
		HSMatrix matrix4 = matrix.plus(matrix1);
		assertTrue(matrix4.equals(matrix3));
	}

	@Test
	void add() {
		matrix.setRow(0, Arrays.asList(new Float[] {1.0f, 2.0f, 3.0f}));
		matrix.setRow(1, Arrays.asList(new Float[] {4.0f, -1.0f, -2.0f}));
		matrix1.setRow(0, Arrays.asList(new Float[] {-1.0f, 2.0f, -3.0f}));
		matrix1.setRow(1, Arrays.asList(new Float[] {-2.0f, 0.0f, 1.0f}));
		matrix3.setRow(0, Arrays.asList(new Float[] {0.0f, 4.0f, 0.0f}));
		matrix3.setRow(1, Arrays.asList(new Float[] {2.0f, -1.0f, -1.0f}));
		matrix.add(matrix1);
		assertTrue(matrix.equals(matrix3));
	}



}
