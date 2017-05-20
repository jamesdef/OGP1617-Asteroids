package asteroids.programs;

import java.util.List;

public abstract class ArithmeticBinaryExpression extends BinaryExpression<ArithmeticInterface> implements ArithmeticInterface {

	protected ArithmeticBinaryExpression(ArithmeticInterface leftExpression, ArithmeticInterface rightExpression)
			throws IllegalArgumentException {
		super(leftExpression, rightExpression);
	}

	
	public Object solveRightOperand(Function function, List<Expression> arguments) {
		return this.getRightOperandResult(function, arguments);
	}

	public Object solveLeftOperand(Function function, List<Expression> arguments) {
		return this.getLeftOperandResult(function, arguments);
	}


}
