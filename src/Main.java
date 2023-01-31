import org.software.hopkins.matrix.HSMatrix;
import org.software.hopkins.matrix.Matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		if (args.length == 0)
			demo();
		else {
			System.out.println("Create matrix A:");
			HSMatrix matrixA = constructUserMatrix();
			System.out.println(matrixA);
			System.out.println("Create matrix B:");
			HSMatrix matrixB = constructUserMatrix();
			System.out.println(matrixB);
			System.out.println("Matrix A + Matrix B = ");
			System.out.println(matrixA.plus(matrixB));
			System.out.println("Does Matrix A = Matrix B?");
			System.out.println(matrixA.equals(matrixB));

		}
	}

	private static HSMatrix constructUserMatrix() {
		Scanner scanner = new Scanner(System.in);
		String userInput;
		List<List<Float>> entryMatrix = new ArrayList<>();
		List<Float> entryRow;
		do {
			entryRow = new ArrayList<>();
			System.out.println("Enter a row of numbers separated by spaces (After last row, enter an empty line):");
			userInput = scanner.nextLine();
			String[] elements = userInput.split(" ");
			for (String element : elements) {
				if (!element.isEmpty())
					entryRow.add(Float.parseFloat(element));
			}
			if (!entryRow.isEmpty()) {
				entryMatrix.add(entryRow);
			}
		} while (!entryRow.isEmpty());
		return new Matrix(entryMatrix);
	}

	private static void demo() {
		final int ROWS = 2;
		final int COLS = 3;
		HSMatrix matrix = new Matrix(ROWS, COLS);
		System.out.println("Initial Matrix");
		System.out.println(matrix);
		matrix.fill(1.0f);
		System.out.println("Filled with 1");
		System.out.println(matrix);
		matrix.setValuesIncrementedFrom(0.0f);
		System.out.println("Filled with Incremental Values");
		System.out.println(matrix);

		HSMatrix matrixA = new Matrix(ROWS, COLS);
		matrixA.setRow(0, Arrays.asList(new Float[] {1.0f, 2.0f, 3.0f}));
		matrixA.setRow(1, Arrays.asList(new Float[]{4.0f, -1.0f, -2.0f}));

		HSMatrix matrixB = new Matrix(ROWS, COLS);
		matrixB.setRow(0, Arrays.asList(new Float[]{-1.0f, 2.0f, -3.0f}));
		matrixB.setRow(1, Arrays.asList(new Float[]{-2.0f, 0.0f, 1.0f}));

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