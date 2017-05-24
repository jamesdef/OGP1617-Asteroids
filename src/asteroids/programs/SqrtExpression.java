package asteroids.programs;

import java.util.List;

public class SqrtExpression extends UnaryExpression<DoubleExpression> implements DoubleExpression{


	protected SqrtExpression(DoubleExpression operand) {
		super(operand);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object getResult() {
		
		Double operandResult = (Double) this.getOperandResult();
		
		if (operandResult >= 0)
			return Math.sqrt(operandResult);
		else
			throw new IllegalArgumentException();
	}

}
