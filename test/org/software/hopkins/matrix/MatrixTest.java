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
		matrix.setEntry(1, 1, 1);
		assertTrue(matrix.getEntry(1, 1) == 1);
	}

	@Test
	void fill() {
		matrix.fill(1);
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
		matrix.fill(1);
		assertTrue(!matrix.equals(matrix1));
	}

	@Test
	void setValuesIncrementedFrom() {
		matrix.setValuesIncrementedFrom(1);
		assertTrue(matrix.getEntry(0, 0) == 1);
		assertTrue(matrix.getEntry(0, 1) == 2);
		assertTrue(matrix.getEntry(ROW_SIZE-1, COL_SIZE-1)
		== ROW_SIZE * COL_SIZE);
	}

	@Test
	void getRow() {
		List<Integer> aRow = matrix.getRow(0);
		assertTrue(aRow.size() == COL_SIZE);
		assertTrue(aRow.get(2) == matrix.getEntry(0, 2));
	}

	@Test
	void setRow() {
		List<Integer> newRow = new ArrayList<>(COL_SIZE);
		newRow.add(7);
		newRow.add(8);
		newRow.add(9);
		matrix1.setRow(0, newRow);
		assertTrue(matrix1.getEntry(0, 0) == newRow.get(0));
		assertTrue(matrix1.getEntry(0, 1) == newRow.get(1));
		assertTrue(matrix1.getEntry(0, 2) == newRow.get(2));

	}

	@Test
	void getRowEncapsulation() {
		List<Integer> theRow = matrix1.getRow(0); // 7, 8, 9
		matrix.setRow(1, theRow);
		matrix.setEntry(1, 1, 10);
		assertTrue(matrix.getEntry(1, 1) !=
				matrix1.getEntry(0, 1));
	}

	@Test
	void sumRow() {
		matrix.fill(2);
		assertTrue(matrix.sumRow(0) == 6);
	}

	@Test
	void sumColumn() {
		matrix1.setValuesIncrementedFrom(1);
		assertTrue(matrix1.sumColumn(0) == 5);
	}

	@Test
	void plus() {
		matrix.setRow(0, Arrays.asList(new Integer[] {1, 2, 3}));
		matrix.setRow(1, Arrays.asList(new Integer[] {4, -1, -2}));
		matrix1.setRow(0, Arrays.asList(new Integer[] {-1, 2, -3}));
		matrix1.setRow(1, Arrays.asList(new Integer[] {-2, 0, 1}));
		matrix3.setRow(0, Arrays.asList(new Integer[] {0, 4, 0}));
		matrix3.setRow(1, Arrays.asList(new Integer[] {2, -1, -1}));
		HSMatrix matrix4 = matrix.plus(matrix1);
		assertTrue(matrix4.equals(matrix3));
	}

	@Test
	void add() {
		matrix.setRow(0, Arrays.asList(new Integer[] {1, 2, 3}));
		matrix.setRow(1, Arrays.asList(new Integer[] {4, -1, -2}));
		matrix1.setRow(0, Arrays.asList(new Integer[] {-1, 2, -3}));
		matrix1.setRow(1, Arrays.asList(new Integer[] {-2, 0, 1}));
		matrix3.setRow(0, Arrays.asList(new Integer[] {0, 4, 0}));
		matrix3.setRow(1, Arrays.asList(new Integer[] {2, -1, -1}));
		matrix.add(matrix1);
		assertTrue(matrix.equals(matrix3));
	}





}