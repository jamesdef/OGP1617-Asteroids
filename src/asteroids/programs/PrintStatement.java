package asteroids.programs;

public class PrintStatement extends Statement {
	public PrintStatement(Expression expression) {
		setExpression(expression);
	}
	
	private Expression expression;
	
	public void setExpression(Expression expression){
		this.expression = expression;
	}

	@Override
	public void run() {

		Object result = expression.getExpressionResult(program, actualArgs, null);
		System.out.println(result);
		//this.getProgram().addPrintOut(result);
		
	}
	

}
