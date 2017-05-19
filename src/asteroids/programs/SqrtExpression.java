package asteroids.programs;

import java.util.List;

public class SqrtExpression extends ArithmeticUnaryExpression{


	protected SqrtExpression(Expression operand) {
		super(operand);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object getResult(Function function, List<Expression> arguments) {
		// TODO Auto-generated method stub
		
		Double operand = this.getNumericValue(program, actualArgs, function);
		
		if (operand >= 0)
			return Math.sqrt(Operand);
		else
			throw new IllegalArgumentException();
	}

}
