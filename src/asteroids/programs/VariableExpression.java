package asteroids.programs;

import java.util.List;
import java.util.Map;

import asteroids.model.Program;

public class VariableExpression extends Expression implements ArithmeticInterface{

	protected VariableExpression(String name){
		System.out.println("VARIABLEEXP name: " + name);
		this.setName(name);
	}
	
	//NAME
	private String name;
	
	protected String getName(){
		return this.name;
	}
	
	private void setName(String name) throws IllegalArgumentException {
		System.out.println("VARIABLEEXP NAME SET TO: " + name);
		this.name = name;
	}

	
	@Override
	protected Object getResult(Function function, List<Expression> arguments) {
		
		Expression resultExpression = this.getProgram().getVariables().get(this.getName());
		System.out.println("VARIABLEEXP > GETRESULT name: " + name + " is exp: " + resultExpression);

		if (resultExpression == null)
			throw new IllegalArgumentException();

		return resultExpression;
			
	}

	

	
}
