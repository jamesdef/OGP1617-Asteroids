package asteroids.programs;

import java.util.List;

public abstract class ArithmeticUnaryExpression extends UnaryExpression<ArithmeticInterface> implements ArithmeticInterface {

	protected ArithmeticUnaryExpression(ArithmeticInterface operand) {
		super(operand);
	}

	
	public Object solveOperand(Function function, List<Expression> arguments) {
		return (double) getOperandResult(function, arguments);
	}

}
