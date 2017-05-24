package asteroids.programs;

import java.util.List;

public class NegationExpression extends UnaryExpression<BooleanExpression> implements BooleanExpression {

	protected NegationExpression(BooleanExpression operand) {
		super(operand);
	}

	@Override
	protected Object getResult() throws IllegalArgumentException{
		return ! (Boolean) getOperandResult();
		
	}

	
}


