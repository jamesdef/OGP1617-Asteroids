package asteroids.programs;

import java.util.List;

public class SignChangeExpression extends UnaryExpression<DoubleExpression> implements DoubleExpression {

	
	protected SignChangeExpression(DoubleExpression operand) {
		super(operand);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object getResult() {
		// TODO Auto-generated method stub
		
		Double operand = (Double) this.getOperandResult();
		
		return - operand;
	}


}
