package asteroids.programs;

import java.util.List;

public class VelocityYExpression extends EntityPropertyExpression {


	public VelocityYExpression(Expression operand) {
		super(operand);
	}

	@Override
	protected Object getResult(Function function, List<Expression> arguments) {
		return this.getOperandResultingEntity().getYVelocity();
	}

}
