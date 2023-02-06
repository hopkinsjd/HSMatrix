package org.software.hopkins.matrix.future;

import org.software.hopkins.matrix.HSMatrix;
import org.software.hopkins.matrix.Matrix;
import org.software.hopkins.matrix.MatrixOperation;

import java.util.HashMap;

public abstract class MatrixCalculator {
	protected HashMap<String, HSMatrix> matrixHashMap = new HashMap<>();

	public void makeMatrix(String name, HSMatrix matrix) {
		matrixHashMap.put(name, matrix);
	}

	public boolean editMatrix(String name, HSMatrix newMatrix) {
		if (matrixHashMap.containsKey(name)) {
			matrixHashMap.put(name, newMatrix);
			return true;
		} else {
			return false;
		}
	}

	public abstract void printMatrix(String name);

	public abstract void printAllMatrices();

	public HSMatrix doMultiMatrixCalculation(MatrixOperation opCode, String[] operands,
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
						throw new RuntimeException("Operation not implemented:  " + opCode);
				}
			} else {
				throw new RuntimeException("Operand " + operand + " does not exist.");
			}
		}
		if (!resultMatrixName.isEmpty()) {
			matrixHashMap.put(resultMatrixName, resultMatrix);
		}
		return resultMatrix;
	}

	private HSMatrix singleMatrixCalculation(MatrixOperation opCode, String operand,
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
				throw new RuntimeException("Operation not implemented:  " + opCode);
		} else {
			throw new RuntimeException("Operand " + operand + " does not exist.");
		}
		if (!resultMatrixName.isEmpty()) {
			matrixHashMap.put(resultMatrixName, resultMatrix);
		}
		return resultMatrix;
	}

	public HSMatrix scaleMatrixCalculation(String operand, String resultMatrixName, Float scalar) {
		return singleMatrixCalculation(MatrixOperation.SCALE, operand, resultMatrixName, scalar,
				'0', 0);
	}

	public HSMatrix sumMatrixRow(String operand, String resultMatrixName, Integer rowNum) {
		return singleMatrixCalculation(MatrixOperation.SUM, operand, resultMatrixName,
				1.0f, 'r', rowNum);
	}

	public HSMatrix sumMatrixColumn(String operand, String resultMatrixName, Integer colNum) {
		return singleMatrixCalculation(MatrixOperation.SUM, operand, resultMatrixName,
				1.0f, 'c', colNum);
	}

	public HSMatrix transposeMatrixCalculation(String operand, String resultMatrixName) {
		return singleMatrixCalculation(MatrixOperation.TRANSPOSE, operand, resultMatrixName,
				1.0f, '0', 0);
	}
}
