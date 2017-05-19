package asteroids.programs;

public abstract class UnaryExpression extends Expression {

	protected UnaryExpression(Expression operand){
		setOperand(operand);
	}
	
	public void setOperand(Expression operand){
		this.operand= operand;
	}
	
	public Expression getOperand(){
		return this.operand;
	}
	
	private Expression operand;
	
	
	
	
	
	
	
}
