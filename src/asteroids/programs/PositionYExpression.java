package asteroids.programs;

import java.util.List;

public class PositionYExpression extends EntityPropertyExpression {


	public PositionYExpression(Expression operand) {
		super(operand);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object getResult(Function function, List<Expression> arguments) {
		return this.getOperandResultingEntity().getYPosition();
	}

}
