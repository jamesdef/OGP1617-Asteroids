package asteroids.programs;

import java.util.List;

public class VelocityXExpression extends EntityPropertyExpression {


	public VelocityXExpression(Expression operand) {
		super(operand);
	}

	@Override
	protected Object getResult(Function function, List<Expression> arguments) {
		return this.getOperandResultingEntity().getxVelocity();
	}

}
