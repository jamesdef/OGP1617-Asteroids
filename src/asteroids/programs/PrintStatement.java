package asteroids.programs;

import java.util.List;

public class PrintStatement extends Statement {
	public PrintStatement(Expression expression) {
		setExpression(expression);
	}
	
	
	//EXPRESSION
	private Expression expression;
	
	public void setExpression(Expression expression){
		this.expression = expression;
	}
	
	protected Expression getExpression(){
		return this.expression;
	}


	@Override
	public void run(List<Expression> arguments) {
		// TODO Auto-generated method stub ille
		if (expression instanceof ParameterExpression)
			throw new IllegalArgumentException();
		
		this.getExpression().setProgram(this.getProgram());
		Object printResult = this.getExpression().getResult(null, arguments);
		
		this.getProgram().addToPrints(printResult);
		System.out.println("RUN PRINTSTATEMENT: " + printResult);

		
	}
	

}
