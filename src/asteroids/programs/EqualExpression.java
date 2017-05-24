package asteroids.programs;

import java.util.List;

public class EqualExpression extends BinaryExpression<Expression> implements BooleanExpression {

	protected EqualExpression(Expression leftExpression, Expression rightExpression) throws IllegalArgumentException {
		super(leftExpression, rightExpression);
	}

	@Override
	protected Object getResult() {

		Object leftOperand = this.getLeftOperandResult();
		Object rightOperand = this.getRightOperandResult();
		
		
		return (leftOperand == rightOperand);
	}

}
