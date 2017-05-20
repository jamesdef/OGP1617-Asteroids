package asteroids.programs;

import java.util.List;

public class PositionXExpression extends EntityPropertyExpression {


	public PositionXExpression(Expression operand) {
		super(operand);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object getResult(Function function, List<Expression> arguments) {
		// TODO Auto-generated method stub
		return this.getOperandResultingEntity().getxPosition();
	}

}
