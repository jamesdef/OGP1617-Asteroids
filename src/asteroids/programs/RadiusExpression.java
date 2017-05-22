package asteroids.programs;

import java.util.List;

public class RadiusExpression extends EntityPropertyExpression {


	public RadiusExpression(Expression operand) {
		super(operand);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object getResult(Function function, List<Expression> arguments) {
		
		return this.getOperandResultingEntity().getRadius();
	}

}
