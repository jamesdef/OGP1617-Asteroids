package asteroids.programs;

import java.util.List;

public class EqualExpression extends BinaryExpression<Expression> implements BooleanExpression {

	protected EqualExpression(Expression leftExpression, Expression rightExpression) throws IllegalArgumentException {
		super(leftExpression, rightExpression);
	}

	@Override
	protected Object getResult(Function function, List<Expression> arguments) {

		Object leftOperand = this.getLeftOperandResult(function, arguments);
		Object rightOperand = this.getRightOperandResult(function, arguments);
		
		
		return (leftOperand == rightOperand);
	}

}
