package asteroids.programs;

import java.util.List;

public abstract class BinaryExpression<B> extends Expression{


	protected BinaryExpression(B leftExpression, B rightExpression)
			throws IllegalArgumentException {
		setOperandLeft(leftExpression);
		setOperandRight(rightExpression);
	}
	
	
	//LEFT AND RIGHT OPERANDS
	private B leftOperand;
	private B rightOperand;
	
	public void setOperandLeft(B operand){
		this.leftOperand = operand;
	}
	
	public void setOperandRight(B operand){
		this.rightOperand= operand;
	}
	
	public boolean isValidOperand(Expression operand){
		return true;
	}
	
	public B getLeftOperand(){
		return leftOperand;
	}
	
	public B getRightOperand(){
		return rightOperand;
	}
	
	protected Object getLeftOperandResult(Function function, List<Expression> arguments) {
		((Expression) this.getLeftOperand()).setProgram(getProgram());
		return ((Expression) this.getLeftOperand()).getResult(function, arguments);
	}

	protected Object getRightOperandResult(Function function, List<Expression> arguments) {
		((Expression) this.getRightOperand()).setProgram(getProgram());
		return ((Expression) this.getRightOperand()).getResult(function, arguments);
	}
	
	
	
}
