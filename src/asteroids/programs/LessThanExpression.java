package asteroids.programs;

import java.util.List;

public class LessThanExpression extends BinaryExpression<DoubleExpression> implements BooleanExpression {

	protected LessThanExpression(DoubleExpression leftExpression, DoubleExpression rightExpression)
			throws IllegalArgumentException {
		super(leftExpression, rightExpression);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object getResult(Function function, List<Expression> arguments) {
		Double leftOperand = (Double) this.getLeftOperandResult(function, arguments);
		Double rightOperand = (Double) this.getRightOperandResult(function, arguments);
		
		
		return (leftOperand < rightOperand);
	}

}
