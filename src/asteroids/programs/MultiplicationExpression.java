package asteroids.programs;

import java.util.List;

public class MultiplicationExpression extends BinaryExpression<DoubleExpression> implements DoubleExpression {

	protected MultiplicationExpression(DoubleExpression leftExpression, DoubleExpression rightExpression) throws IllegalArgumentException {
		super(leftExpression, rightExpression);
	}

	@Override
	protected Object getResult() {
		Double left = (Double) this.getLeftOperandResult();
		Double right = (Double) this.getRightOperandResult();

		return (left * right);
	}

}
