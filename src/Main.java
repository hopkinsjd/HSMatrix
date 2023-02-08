import org.software.hopkins.matrix.HSMatrix;
import org.software.hopkins.matrix.HSMutableMatrix;
import org.software.hopkins.matrix.Matrix;
import org.software.hopkins.matrix.MatrixCommand;
import org.software.hopkins.matrix.MatrixOperation;
import org.software.hopkins.matrix.MutableMatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	private static final HashMap<String, HSMatrix> matrixHashMap = new HashMap<>();

	/**
	 * Main method for a Matrix command-line calculator using HSMatrix.
	 * Provides to the user - matrix command options, which are not case-sensitive:
	 * 	Make - name and create a matrix. A new matrix of the same name as an old replaces it.
	 * 	Edit - edit an existing named matrix.
	 * 	Print - prints a named matrix or all named matrices.
	 * 	Calc - perform math operations on named matrices. May store result in a new or old named matrix.
	 * 		Add - add multiple matrices together.
	 * 		Subtract - subtract multiple matrices in given order of appearance.
	 * 		Scale - multiply a named matrix by a given scalar value (number).
	 * 		Sum - get the sum of a column or row of a named matrix.
	 * 		Transpose - swap the rows and columns of a named matrix (inverting it).
	 * 	Demo - prints out a pre-programmed set of calculations, demonstrating the programs capabilities.
	 * 	Exit - quits the program.
	 * @param args - none used.
	 */
	public static void main(String[] args) {
		boolean done = false;
		while(!done) {
			System.out.println("Enter matrix command (make, edit, print, calc, demo, exit): ");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine().trim();
			MatrixCommand cmd = MatrixCommand.valueOf(input.toUpperCase());
			switch (cmd) {
				case MAKE -> constructUserMatrix();
				case EDIT -> editUserMatrix();
				case PRINT -> printUserMatrices();
				case CALC -> matrixCalculations();
				case DEMO -> demo();
				case EXIT -> done = true;
				default -> System.out.println("Incorrect command. Try again.");
			}
		}
	}

	/**
	 * Implements user interaction for performing available operations on matrices
	 * made through this calculator.
	 */
	private static void matrixCalculations() {
		Scanner scanner = new Scanner(System.in);
		MatrixOperation opCode = getUserMatrixOpcode(scanner);
		if (opCode == MatrixOperation.CANCEL)
			return;

		String[] operands = getOperandsFromUser(scanner);
		if (operands[0].isEmpty())
			return; // none entered

		System.out.println("Store result matrix? (y|n)");
		String input = scanner.nextLine();
		String resultMatrixName = "";
		if (input.charAt(0) == 'y') {
			resultMatrixName = getUserMatrixName();
		}

		doCalculation(opCode, operands, resultMatrixName, scanner);
	}

	/**
	 * Executes a matrix calculation previously set up by the user.
	 * More setup may be required of the user here, depending on the operation.
	 * @param opCode - code or word for the math operation on one or more matrices.
	 * @param operands - one or more named matrices on which the operation is to be performed.
	 * @param resultMatrixName - name of a variable to place the calculation result.
	 *                         If empty, the result will still be printed, but not stored.
	 *                         THIS MUST NOT BE NULL.
	 * @param scanner - user command-line input, such as from System.in.
	 */
	private static void doCalculation(MatrixOperation opCode, String[] operands,
									  String resultMatrixName, Scanner scanner) {
		switch (opCode) {
			case ADD, SUBTRACT -> multiOperandOperation(opCode, operands, resultMatrixName);
			case SCALE -> {
				Float scalar = getUserScalar(scanner);
				singleOperandOperation(opCode, operands[0], resultMatrixName, scalar);
			}
			case TRANSPOSE -> singleOperandOperation(opCode, operands[0], resultMatrixName);
			case SUM -> {
				char rc = getUserRowOrColumnChoice(scanner);
				HSMatrix matrix = matrixHashMap.get(operands[0]);
				Integer rcNum;
				if (rc == 'r')
					rcNum = getUserRowOrColumnNumber(scanner, matrix.columnSize());
				else
					rcNum = getUserRowOrColumnNumber(scanner, matrix.rowSize());
				singleOperandOperation(opCode, operands[0], resultMatrixName, rc, rcNum);
			}
			default -> System.out.println("Invalid operation. Try another.");
		}
	}

	/**
	 * Gets a calculation's operands (named matrices) from the user of the command-line.
	 * Enforces each operand to be a previously defined named matrix.
	 * Entering an empty line by the user should cancel the calculation.
	 *
	 * @param scanner - user command-line input, such as from System.in.
	 * @return an array of Strings, representing named matrices chosen by the user.
	 */
	private static String[] getOperandsFromUser(Scanner scanner) {
		String[] operands;
		do {
			System.out.println("Enter matrix operand name, names separated by spaces, or nothing to cancel: ");
			String input = scanner.nextLine().trim().toUpperCase();
			operands = input.split(" ");
			// test operands to see if they were made and named
			for (String operand : operands) {
				if (operand.isEmpty())
					System.out.println("No operand entered. Canceling calculation.");
				else if (!matrixHashMap.containsKey(operand)) {
					System.out.println("Operand " + operand + " unavailable. Use Make command.");
					operands = null;
					break;
				}
			}
		} while(operands == null);
		return operands;
	}

	/**
	 * Get a valid matrix operation opcode from the user and return it.
	 *
	 * @param scanner - user command-line input, such as from System.in.
	 * @return the opcode entered by the user at the command line.
	 */
	private static MatrixOperation getUserMatrixOpcode(Scanner scanner) {
		MatrixOperation opCode = MatrixOperation.INVALID;
		do {
			System.out.println("Enter matrix operation (add, subtract, transpose, scale, sum, cancel):");
			String input = scanner.nextLine().trim();
			try {
				opCode = MatrixOperation.valueOf(input.toUpperCase());
			} catch (Exception e) {
				System.out.println("Invalid operand. Try again.");
			}
		} while (opCode == MatrixOperation.INVALID);
		return opCode;
	}

	/**
	 * Gets a valid scalar value from the user for use in the Scale calculation.
	 *
	 * @param scanner - user command-line input, such as from System.in.
	 * @return a valid scalar value entered by the user.
	 */
	private static Float getUserScalar(Scanner scanner) {
		Float scalar = null;
		do {
			System.out.println("Enter the scalar value (number): ");
			String scalarStr = scanner.nextLine().trim();
			try {
				scalar = Float.parseFloat(scalarStr);
			} catch (Exception e) {
				System.out.println("Bad number: " + e.getMessage() + " Try again.");
			}
		} while (scalar == null);
		return scalar;
	}

	/**
	 * Gets from the user the number of a column or row of a matrix to sum.
	 * The number is zero-based.
	 *
	 * @param scanner - user command-line input, such as from System.in.
	 * @return the row or column number of a matrix to sum.
	 */
	private static Integer getUserRowOrColumnNumber(Scanner scanner, int rcLength) {
		Integer rcNum = null;
		do {
			System.out.println("Enter row or column number:");
			String intStr = scanner.nextLine().trim();
			try {
				rcNum = Integer.parseInt(intStr);
				if (rcNum < 0 || rcNum >= rcLength) {
					System.out.println(rcNum + " needs to between 0 and " + (rcLength-1));
					rcNum = null;
				}
			} catch(Exception e) {
				System.out.println("Bad number: " + e.getMessage() + " Try again.");
			}

		} while(rcNum == null);
		return rcNum;
	}

	/**
	 * Gets from the user their choice of whether to sum the values of a row or column
	 * of a matrix.
	 *
	 * @param scanner - user command-line input, such as from System.in.
	 * @return the character 'r' for row or 'c' for column
	 */
	private static char getUserRowOrColumnChoice(Scanner scanner) {
		char rc;
		do {
			System.out.println("Sum matrix row or column (r|c)?");
			rc = scanner.nextLine().charAt(0);
		} while(rc != 'r' && rc != 'c');
		return rc;
	}

	private static void multiOperandOperation(MatrixOperation opCode, String[] operands,
											  String resultMatrixName) {
		HSMatrix resultMatrix = null;
		for (String operand : operands) {
			if (matrixHashMap.containsKey(operand)) {
				if (resultMatrix == null) {
					resultMatrix = matrixHashMap.get(operand);
				} else {
					if (opCode == MatrixOperation.ADD)
						resultMatrix = resultMatrix.plus(matrixHashMap.get(operand));
					else if (opCode == MatrixOperation.SUBTRACT)
						resultMatrix = resultMatrix.minus(matrixHashMap.get(operand));
					else
						System.out.println("Operation not implemented:  " + opCode);
				}
			} else {
				System.out.println("Operand " + operand + " does not exist.");
			}
		}
		System.out.println(resultMatrix);
		if (!resultMatrixName.isEmpty()) {
			matrixHashMap.put(resultMatrixName, resultMatrix);
		}
	}

	private static void singleOperandOperation(MatrixOperation opCode, String operand,
											  String resultMatrixName, Float scalar,
											   char rc, Integer rcNum) {
		HSMatrix resultMatrix = null;
		if (matrixHashMap.containsKey(operand)) {
			resultMatrix = matrixHashMap.get(operand);
			if (opCode == MatrixOperation.SCALE)
				resultMatrix = resultMatrix.times(scalar);
			else if (opCode == MatrixOperation.TRANSPOSE)
				resultMatrix = resultMatrix.transpose();
			else if (opCode == MatrixOperation.SUM) {
				Float sum;
				if (rc == 'r') {
					sum = resultMatrix.sumRow(rcNum);
				} else {
					sum = resultMatrix.sumColumn(rcNum);
				}
				resultMatrix = new Matrix(1, 1, sum);
			}
			else
				System.out.println("Operation not implemented:  " + opCode);
		} else {
			System.out.println("Operand " + operand + " does not exist.");
		}

		System.out.println(resultMatrix);
		if (!resultMatrixName.isEmpty()) {
			matrixHashMap.put(resultMatrixName, resultMatrix);
		}
	}

	private static void singleOperandOperation(MatrixOperation opCode, String operand,
											   String resultMatrixName, Float scalar) {
		singleOperandOperation(opCode, operand, resultMatrixName, scalar, '0', 0); // SCALE
	}

	private static void singleOperandOperation(MatrixOperation opCode, String operand,
											   String resultMatrixName, char rc, Integer rcNum) {
		singleOperandOperation(opCode, operand, resultMatrixName, 1.0f, rc, rcNum); // SUM
	}

	private static void singleOperandOperation(MatrixOperation opCode, String operand,
											   String resultMatrixName) {
		singleOperandOperation(opCode, operand, resultMatrixName, 1.0f, '0', 0); //Others
	}


	private static void editUserMatrix() {
		String matrixName = getUserMatrixName();
		if (matrixHashMap.containsKey(matrixName)) {
			HSMatrix newUserMatrix = getUserMatrix();
			matrixHashMap.put(matrixName, newUserMatrix);
		} else {
			System.out.println("Matrix " + matrixName + " not found. Try Make.");
		}
	}

	private static void printUserMatrices() {
		System.out.println("Enter matrix name or nothing for all:");
		Scanner scanner = new Scanner(System.in);
		String userInput = scanner.nextLine().trim().toUpperCase();
		if (userInput.isEmpty()) {
			for (Map.Entry entry : matrixHashMap.entrySet()) {
				System.out.println("Matrix " + entry.getKey() + ": ");
				System.out.println(entry.getValue());
			}
		} else {
			HSMatrix matrix = matrixHashMap.get(userInput);
			System.out.println("Matrix " + userInput + ": ");
			System.out.println(matrix);
		}

	}

	private static String getUserMatrixName() {
		Scanner scanner = new Scanner(System.in);
		String userInput;
		String matrixName;
		do {
			System.out.println("Enter matrix name: ");
			userInput = scanner.nextLine().trim();
			matrixName = userInput.toUpperCase();
		} while(matrixName.isEmpty());
		return matrixName;
	}

	private static HSMatrix getUserMatrix() {
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
		// check row lengths, add 0 for lengths shorter than that of the first row
		int cols = entryMatrix.get(0).size();
		for (int r = 1; r < entryMatrix.size(); r++) {
			List<Float> row = entryMatrix.get(r);
			while (row.size() < cols) {
				row.add(0.0f);
			}
		}
		return new Matrix(entryMatrix);
	}

	private static HSMatrix constructUserMatrix() {
		String matrixName = getUserMatrixName();
		HSMatrix matrixMade = getUserMatrix();
		matrixHashMap.put(matrixName, matrixMade);
		System.out.println("Matrix " + matrixName + ":");
		System.out.println(matrixMade);
		return matrixMade;
	}

	private static void demo() {
		final int ROWS = 2;
		final int COLS = 3;
		HSMutableMatrix matrix = new MutableMatrix(ROWS, COLS);
		System.out.println("Initial Matrix");
		System.out.println(matrix);
		matrix.fill(1.0f);
		System.out.println("Filled with 1");
		System.out.println(matrix);
		matrix.setValuesIncrementedFrom(0.0f);
		System.out.println("Filled with Incremental Values");
		System.out.println(matrix);

		HSMutableMatrix matrixA = new MutableMatrix(ROWS, COLS);
		matrixA.setRow(0, Arrays.asList(1.0f, 2.0f, 3.0f));
		matrixA.setRow(1, Arrays.asList(4.0f, -1.0f, -2.0f));

		HSMutableMatrix matrixB = new MutableMatrix(ROWS, COLS);
		matrixB.setRow(0, Arrays.asList(-1.0f, 2.0f, -3.0f));
		matrixB.setRow(1, Arrays.asList(-2.0f, 0.0f, 1.0f));

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