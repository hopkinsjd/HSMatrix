package org.software.hopkins.matrix.future;

import org.software.hopkins.matrix.Matrix;

public class SquareMatrix extends Matrix implements HSSquareMatrix {
	protected int size;

	public SquareMatrix(int size) {
		super(size, size);
		this.size = size;
	}

	@Override
	public int size() {
		return size;
	}
}
