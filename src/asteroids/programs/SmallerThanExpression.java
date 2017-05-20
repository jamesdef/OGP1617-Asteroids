package asteroids.programs;

import java.util.List;

public class SmallerThanExpression extends BinaryExpression implements BooleanInterface, ArithmeticInterface {

protected SmallerThanExpression(Object leftExpression, Object rightExpression) throws IllegalArgumentException {
		super(leftExpression, rightExpression);
		// TODO Auto-generated constructor stub
	}

//	protected SmallerThanExpression(ArithmeticInterface e1, ArithmeticInterface e2)
//			throws IllegalArgumentException {
//		super(e1, e2);
//	}

	@Override
	protected Object getResult(Function function, List<Expression> arguments) {		
		Double left = (Double) this.getLeftOperandResult(function, arguments);
		Double right = (Double) this.getRightOperandResult(function, arguments);

		System.out.println("SMALLERTHAN" + left + right);
		return (Boolean) (left < right);
	}
}