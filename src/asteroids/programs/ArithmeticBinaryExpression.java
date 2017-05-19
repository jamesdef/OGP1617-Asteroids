package asteroids.programs;

import java.util.List;

public class ArithmeticBinaryExpression extends BinaryExpression implements ArithmeticInterface {

	protected ArithmeticBinaryExpression(Expression leftExpression, Expression rightExpression)
			throws IllegalArgumentException {
		super(leftExpression, rightExpression);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object getResult(Function function, List<Expression> arguments) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Object getLeftOperandNumericResult(){
		
	}
	
	public Object solveRightOperand(Function function, List<Expression> arguments) {
		Double[] parameterArray = getExpressionParameter(actualArgs, function);
		Double rightParameter = parameterArray[1];
		
		if (rightParameter != null)
			return rightParameter;
		else {
			if (canHaveAsArithmeticOperand(program, actualArgs, getRightOperand(), function))
				return (double) getRightOperandResult(program, actualArgs, function);
			else
				throw new IllegalArgumentException();
		}
	}

}
