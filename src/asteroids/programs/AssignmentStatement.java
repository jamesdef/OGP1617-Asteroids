package asteroids.programs;

import java.util.List;

public class AssignmentStatement extends Statement {

	public AssignmentStatement(String name, Expression expression) {
		setName(name);
		setExpression(expression);
	}
	
	//NAME
	private String name;
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	
	
	//EXPRESSION
	private Expression expression;
	
	public void setExpression(Expression expression){
		this.expression = expression;
	}
	
	public Expression getExpression(){
		return this.expression;
	}
	
	
	//RUN
	public boolean isValidVariable(List<Expression> arguments){
		return 
				() &&
				(this.getExpression().getResult(null, arguments) instanceof Double);
		//TODO
	}
	
	@Override
	public void run(List<Expression> arguments) {
		if (isValidVariable())
			this.getProgram().addVariable(this.getName(), this.getExpression());
		
		else
			throw new IllegalArgumentException();
		
	}

}
