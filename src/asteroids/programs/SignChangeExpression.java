package asteroids.programs;

import java.util.List;

public class SignChangeExpression extends ArithmeticUnaryExpression {

	protected SignChangeExpression(ArithmeticInterface operand) throws IllegalArgumentException {
		super(operand);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected Object getResult(Function function, List<Expression> arguments) {
		// TODO Auto-generated method stub
		
		Double operand = (Double) this.solveOperand(function, arguments);
		
		return - operand;
	}


}
