package asteroids.programs;

import java.util.List;

public class ArithmeticUnaryExpression extends UnaryExpression implements ArithmeticInterface {

	protected ArithmeticUnaryExpression(Expression operand) {
		super(operand);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object getResult(Function function, List<Expression> arguments) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public Object getNumericValue(Function function, List<Expression> arguments) {
	//	Double parameter = parameterArray[0];
		
		Double numericValue = (Double) this.getArgumentExpression(getOperand(), arguments).getResult(function, arguments);
		
		if (numericValue != null)
			return numericValue;
		else {
			if (isValidArithmeticOperand())
				return (double) getOperandResult(program, actualArgs, function);
			else
				throw new IllegalArgumentException();
		}
	}

}
