package asteroids.programs;

import java.util.List;
import java.util.Map;

import asteroids.model.Program;

public class VariableExpression extends Expression implements DoubleExpression{

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
		this.name = name;
	}

	
	@Override
	protected Object getResult(Function function, List<Expression> arguments) {
		Object resultExpression = this.getProgram().getVariables().get(this.getName());
	
		if (resultExpression == null)
			throw new IllegalArgumentException();

		return resultExpression;
			
	}

	

	
}
