package org.software.hopkins.matrix.future;

import org.software.hopkins.matrix.HSMatrix;

import java.util.Map;

public class CommandLineMatrixCalculator extends MatrixCalculator {

	/**
	 * @param name
	 */
	@Override
	public void printMatrix(String name) {
		if (matrixHashMap.containsKey(name)) {
			HSMatrix matrix = matrixHashMap.get(name);
			System.out.println("Matrix " + name + ": ");
			System.out.println(matrix);
		}
	}

	/**
	 *
	 */
	@Override
	public void printAllMatrices() {
		for (Map.Entry entry : matrixHashMap.entrySet()) {
			System.out.println("Matrix " + entry.getKey() + ": ");
			System.out.println(entry.getValue());
		}
	}
}
