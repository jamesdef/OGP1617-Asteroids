package asteroids.programs;

import java.util.List;

public class NegationExpression extends UnaryExpression implements BooleanInterface {

	protected NegationExpression(Expression operand) {
		super(operand);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object getResult(Function function, List<Expression> arguments) {
		// TODO Auto-generated method stub
		
		if (isValidBoolean(getOperand())) {
			return !(Boolean) getResult(function, arguments);
		} else {
			throw new IllegalArgumentException();
		}
	}

}


