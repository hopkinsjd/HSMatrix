package org.software.hopkins.matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SummableMatrixTest {
	final int ROW_SIZE = 2;
	final int COL_SIZE = 3;

	HSMatrix matrix = new Matrix(ROW_SIZE, COL_SIZE, 1.0f, true);



	@Test
	void sumRow() {
		assertEquals(6.0f, matrix.sumRow(0));
	}

	@Test
	void sumColumn() {
		assertEquals(5.0f, matrix.sumColumn(0));
	}

	@Test
	void plus() {
		Float[][] arrayMatrix1 = {{1.0f, 2.0f, 3.0f}, {4.0f, -1.0f, -2.0f}};
		HSMatrix matrix1 = new Matrix(arrayMatrix1);
		Float[][] arrayMatrix2 = {{-1.0f, 2.0f, -3.0f}, {-2.0f, 0.0f, 1.0f}};
		HSMatrix matrix2 = new Matrix(arrayMatrix2);
		Float[][] arrayMatrix3 = {{0.0f, 4.0f, 0.0f}, {2.0f, -1.0f, -1.0f}};
		HSMatrix matrix3 = new Matrix(arrayMatrix3);
		assertTrue(matrix1.plus(matrix2).equals(matrix3));
	}





}
