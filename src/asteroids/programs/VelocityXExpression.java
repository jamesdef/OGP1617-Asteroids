package asteroids.programs;

public class VelocityXExpression extends EntityPropertyExpression {


	public VelocityXExpression(Expression operand) {
		super(operand);
	}

	@Override
	protected Object getResult() {
		return this.getOperandResultingEntity().getXVelocity();
	}

}
