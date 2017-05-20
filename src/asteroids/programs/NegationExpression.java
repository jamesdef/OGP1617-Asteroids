package asteroids.programs;

import java.util.List;

public class NegationExpression extends UnaryExpression<BooleanExpression> implements BooleanExpression {

	protected NegationExpression(BooleanExpression operand) {
		super(operand);
	}

	@Override
	protected Object getResult(Function function, List<Expression> arguments) throws IllegalArgumentException{
		return ! (Boolean) getOperandResult(function, arguments);
		
	}

	
}


