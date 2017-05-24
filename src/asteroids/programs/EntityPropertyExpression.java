package asteroids.programs;

import asteroids.model.Entity;

public abstract class EntityPropertyExpression extends Expression {
	public EntityPropertyExpression(Expression operand){
		this.setOperand(operand);
	}
	
	//OPERAND
	private Expression operand;
	
	private void setOperand(Expression operand) {
		if (isLegalEntityOperand(operand))
			this.operand = operand;
		else
			throw new IllegalArgumentException();
	}
	
	private Expression getOperand(){
		return this.operand;
	}
	
	protected Entity getOperandResultingEntity(){
		this.getOperand().setProgram(getProgram());
		return (Entity) this.getOperand().getResult();
	}
	
	
	public boolean isLegalEntityOperand(Expression operand){
		return ((operand instanceof EntityExpression || operand instanceof VariableExpression)
				&& !(operand instanceof NullExpression));
	}
	
}
