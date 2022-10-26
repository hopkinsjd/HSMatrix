package org.software.hopkins.matrix.future;

import org.software.hopkins.matrix.HSMatrix;
import org.software.hopkins.matrix.MatrixSummable;
import org.software.hopkins.matrix.future.MatrixScalable;

public interface MatrixSubtractable extends MatrixScalable, MatrixSummable {
	HSMatrix minus(HSMatrix matrix);
	void subtract(HSMatrix matrix);
}
