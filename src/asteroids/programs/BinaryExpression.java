package asteroids.programs;

import java.util.List;

public abstract class BinaryExpression extends Expression{


	protected BinaryExpression(Expression leftExpression, Expression rightExpression)
			throws IllegalArgumentException {
		setOperandLeft(leftExpression);
		setOperandRight(rightExpression);
	}
	
	public void setOperandLeft(Expression operand){
		this.leftOperand = operand;
	}
	
	public void setOperandRight(Expression operand){
		this.rightOperand= operand;
	}
	
	public boolean isValidOperand(Expression operand){
		return true;
	}
	
	public Expression getLeftOperand(){
		return leftOperand;
	}
	
	public Expression getRightOperand(){
		return rightOperand;
	}
	
	public Object getLeftOperandResult(Function function, List<Expression> arguments){
		return this.getLeftOperand().getResult(function, arguments);
	}
	
	public Object getRightOperandResult(Function function, List<Expression> arguments){
		return this.getRightOperand().getResult(function, arguments);
	}
	
	private Expression leftOperand;
	private Expression rightOperand;
	
	
}
