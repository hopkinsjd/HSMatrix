package org.software.hopkins.matrix;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
	final int ROW_SIZE = 2;
	final int COL_SIZE = 3;

	HSMatrix matrix = new Matrix(ROW_SIZE, COL_SIZE);
	HSMatrix matrix1 = new Matrix(ROW_SIZE, COL_SIZE);
	HSMatrix matrix2 = new Matrix(COL_SIZE, ROW_SIZE);
	HSMatrix matrix3 = new Matrix(ROW_SIZE, COL_SIZE);

	@BeforeEach
	void setUp() {

	}

	@Test
	void rowSize() {
		assertTrue(matrix.rowSize() == ROW_SIZE);
	}

	@Test
	void columnSize() {
		assertTrue(matrix.columnSize() == COL_SIZE);
	}

	@Test
	void getEntry() {
		assertTrue(matrix.getEntry(0, 0) == 0);
	}

	@Test
	void setEntry() {
		matrix.setEntry(1, 1, 1.0f);
		assertTrue(matrix.getEntry(1, 1) == 1);
	}

	@Test
	void fill() {
		matrix.fill(1.0f);
		HSMatrix matrix1 = new Matrix(ROW_SIZE, COL_SIZE);
		assertTrue(matrix.getEntry(1, 1) == 1);
	}

	@Test
	void isSameOrder() {
		assertTrue(matrix.isSameOrder(matrix1));
		assertTrue(!matrix.isSameOrder(matrix2));

	}

	@Test
	void testEquals() {
		assertTrue(matrix.equals(matrix1));
		assertTrue(!matrix2.equals(matrix1));
		matrix.fill(1.0f);
		assertTrue(!matrix.equals(matrix1));
	}

	@Test
	void setValuesIncrementedFrom() {
		matrix.setValuesIncrementedFrom(1.0f);
		assertTrue(matrix.getEntry(0, 0) == 1);
		assertTrue(matrix.getEntry(0, 1) == 2);
		assertTrue(matrix.getEntry(ROW_SIZE-1, COL_SIZE-1)
		== ROW_SIZE * COL_SIZE);
	}

	@Test
	void getRow() {
		List<Float> aRow = matrix.getRow(0);
		assertTrue(aRow.size() == COL_SIZE);
		assertTrue(aRow.get(2) == matrix.getEntry(0, 2));
	}

	@Test
	void setRow() {
		List<Float> newRow = new ArrayList<>(COL_SIZE);
		newRow.add(7.0f);
		newRow.add(8.0f);
		newRow.add(9.0f);
		matrix1.setRow(0, newRow);
		assertTrue(matrix1.getEntry(0, 0) == newRow.get(0));
		assertTrue(matrix1.getEntry(0, 1) == newRow.get(1));
		assertTrue(matrix1.getEntry(0, 2) == newRow.get(2));

	}

	@Test
	void getRowEncapsulation() {
		List<Float> theRow = matrix1.getRow(0); // 7, 8, 9
		matrix.setRow(1, theRow);
		matrix.setEntry(1, 1, 10.0f);
		assertTrue(matrix.getEntry(1, 1) !=
				matrix1.getEntry(0, 1));
	}
	
	@Test
	void copying () {
		HSMatrix matrixCopy = matrix.clone();
		assertTrue(matrix != matrixCopy);
		assertTrue(matrix.equals(matrixCopy));
		assertTrue(matrix.getEntry(0, 0) == matrixCopy.getEntry(0, 0));
		matrixCopy.setEntry(0,0, 100.0f);
		assertTrue(matrix.getEntry(0, 0) != matrixCopy.getEntry(0, 0));

	}

}