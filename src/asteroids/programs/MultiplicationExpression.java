package asteroids.programs;

import java.util.List;

public class MultiplicationExpression extends ArithmeticBinaryExpression implements ArithmeticInterface {

	protected MultiplicationExpression(ArithmeticInterface leftExpression, ArithmeticInterface rightExpression)
			throws IllegalArgumentException {
		super(leftExpression, rightExpression);
		
		System.out.println("RUNNING MULT EXP WITH: " + leftExpression + " " + rightExpression );

	}

	@Override
	protected Object getResult(Function function, List<Expression> arguments) {
		Double left = (Double) this.solveLeftOperand(function, arguments);
		Double right = (Double) this.solveRightOperand(function, arguments);

		return (left*right);
	}

}
