package asteroids.programs;

import java.util.List;

public class NegationExpression extends UnaryExpression<BooleanInterface> implements BooleanInterface {

	public NegationExpression(BooleanInterface operand) throws IllegalArgumentException {
		super(operand);
	}

	@Override
	protected Object getResult(Function function, List<Expression> arguments) throws IllegalArgumentException{
		return ! (Boolean) getOperandResult(function, arguments);
		
	}

	
}


