package asteroids.programs;

import java.util.List;

public abstract class UnaryExpression<U> extends Expression {

	protected UnaryExpression(U operand){
		setOperand(operand);
	}
	
	
	//OPERAND
	private U operand;

	public void setOperand(U operand){
		this.operand= operand;
	}
	
	
	protected U getOperand(){
		return this.operand;

	}
	
	protected Object getOperandResult() {
		((Expression) this.getOperand()).setProgram(this.getProgram());
		return ((Expression) this.getOperand()).getResult();
	}
	
}
