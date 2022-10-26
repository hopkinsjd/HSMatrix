package org.software.hopkins.matrix.future;

import org.software.hopkins.matrix.HSMatrix;

public interface MatrixScalable {
	HSMatrix multiply(Integer scalar);
	void scale(Integer scalar);
	HSMatrix negative();
}
