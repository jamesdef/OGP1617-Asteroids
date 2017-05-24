package asteroids.programs;

import java.util.List;

public class AssignmentStatement extends Statement {

	public AssignmentStatement(String name, Expression expression) {
		setName(name);
		setExpression(expression);
		
		System.out.println("ASSIGNMENTSTATEMENT, name: " + name + " exp: " + expression);
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
	
	public void setExpression(Expression expression) throws IllegalArgumentException{
		this.expression = expression;
	}
	
	public Expression getExpression(){
		return this.expression;
	}
	
	
	//RUN
	public boolean isValidVariable(String name, Expression expression){
		expression.setProgram(getProgram());
		return 
				((!this.getProgram().getFunctions().containsKey(name)) &&
				(expression.getResult() instanceof Double));
		//TODO
	}
	
	@Override
	public void run() {
		if (isValidVariable(this.getName(), this.getExpression())){
			this.getProgram().addVariable(this.getName(), 
					this.getExpression().getResult());
		}
		else{
			System.out.println("throwing illegal argument for "
					+ "ASSIGNMENTSTATEMENT, name: " + name + " exp: " + expression);
			throw new IllegalArgumentException();
		}
		
	}

}
