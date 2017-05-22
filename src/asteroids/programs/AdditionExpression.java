package asteroids.programs;

import java.util.List;

public class AdditionExpression extends BinaryExpression<DoubleExpression> implements DoubleExpression {

	protected AdditionExpression(DoubleExpression leftExpression, DoubleExpression rightExpression) throws IllegalArgumentException {
		super(leftExpression, rightExpression);
	}

	@Override
	protected Object getResult(Function function, List<Expression> arguments) {
		Double left = (Double) this.getLeftOperandResult(function, arguments);
		Double right = (Double) this.getRightOperandResult(function, arguments);

		return (left+right);
	}

}
