package asteroids.programs;

import java.util.List;

import asteroids.model.Program;

public abstract class Expression {
	
	
	private static final int O = 0;
	//program
	
	private Program program;
	
	public void setProgram(Program program){
		this.program = program;
	}
	
	public Program getProgram(){
		return this.program;
	}
	
	protected Ship getShip() {
		return this.getExpressionProgram().getProgramShip();
	}
	
	public Expression getArgumentExpression(Expression key, List<Expression> arguments){
		int index = O;

		return arguments.get(index);
	}
	
	
	
	
	
	protected abstract Object getResult(Function function, List<Expression> arguments) {
	
	protected Object getArgumentExpression(MyExpression operand, List<MyExpression> actualArgs) {
		return getArgument(operand, actualArgs).getExpressionResult(getExpressionProgram(), actualArgs);
	}
	
}
