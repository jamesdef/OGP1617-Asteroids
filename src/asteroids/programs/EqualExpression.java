package asteroids.programs;

import java.util.List;

public class EqualExpression extends BinaryExpression<Expression> implements BooleanInterface{

	protected EqualExpression(Expression leftExpression, Expression rightExpression) throws IllegalArgumentException {
		super(leftExpression, rightExpression);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object getResult(Function function, List<Expression> arguments) {
		// TODO Auto-generated method stub
		Object leftOperand = this.getLeftOperandResult(function, arguments);
		Object rightOperand = this.getRightOperandResult(function, arguments);
		
		
		return (leftOperand == rightOperand);
	}

}
