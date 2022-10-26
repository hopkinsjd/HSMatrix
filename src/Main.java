import org.software.hopkins.matrix.HSMatrix;
import org.software.hopkins.matrix.Matrix;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		final int ROWS = 2;
		final int COLS = 3;
		HSMatrix matrix = new Matrix(ROWS, COLS);
		System.out.println("Initial Matrix");
		System.out.println(matrix);
		matrix.fill(1);
		System.out.println("Filled with 1");
		System.out.println(matrix);
		matrix.setValuesIncrementedFrom(0);
		System.out.println("Filled with Incremental Values");
		System.out.println(matrix);

		HSMatrix matrixA = new Matrix(ROWS, COLS);
		matrixA.setRow(0, Arrays.asList(new Integer[] {1, 2, 3}));
		matrixA.setRow(1, Arrays.asList(new Integer[]{4, -1, -2}));

		HSMatrix matrixB = new Matrix(ROWS, COLS);
		matrixB.setRow(0, Arrays.asList(new Integer[]{-1, 2, -3}));
		matrixB.setRow(1, Arrays.asList(new Integer[]{-2, 0, 1}));

		System.out.println("Matrix Sums\n");
		System.out.println("Matrix A");
		System.out.println(matrixA);
		System.out.println("plus\n");
		System.out.println("Matrix B");
		System.out.println(matrixB);
		System.out.println("equals");
		System.out.println(matrixA.plus(matrixB));
	}
}