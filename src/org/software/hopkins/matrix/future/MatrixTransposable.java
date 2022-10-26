package org.software.hopkins.matrix.future;

import org.software.hopkins.matrix.future.HSSquareMatrix;

public interface MatrixTransposable {
	HSSquareMatrix transpose();
	void invert();
}
