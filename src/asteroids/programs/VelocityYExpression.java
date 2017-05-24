package asteroids.programs;

public class VelocityYExpression extends EntityPropertyExpression {


	public VelocityYExpression(Expression operand) {
		super(operand);
	}

	@Override
	protected Object getResult() {
		return this.getOperandResultingEntity().getYVelocity();
	}

}
