package asteroids.programs;

import java.util.List;

public class AdditionExpression extends BinaryExpression {

	/*protected AdditionExpression(ArithmeticInterface leftExpression, ArithmeticInterface rightExpression)
			throws IllegalArgumentException {
		super(leftExpression, rightExpression);
	}

*/

	protected AdditionExpression(Object leftExpression, Object rightExpression) throws IllegalArgumentException {
		super(leftExpression, rightExpression);
	}

	@Override
	protected Object getResult(Function function, List<Expression> arguments) {
		Double left = (Double) this.getLeftOperandResult(function, arguments);
		Double right = (Double) this.getRightOperandResult(function, arguments);

		return (left+right);
	}

}
