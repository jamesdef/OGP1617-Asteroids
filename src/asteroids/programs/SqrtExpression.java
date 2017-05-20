package asteroids.programs;

import java.util.List;

public class SqrtExpression extends ArithmeticUnaryExpression{


	protected SqrtExpression(ArithmeticInterface operand) {
		super(operand);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object getResult(Function function, List<Expression> arguments) {
		// TODO Auto-generated method stub
		
		Double operandResult = (Double) this.solveOperand(function, arguments);
		
		if (operandResult >= 0)
			return Math.sqrt(operandResult);
		else
			throw new IllegalArgumentException();
	}

}
