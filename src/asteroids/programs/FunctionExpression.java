package asteroids.programs;

import java.util.List;
import java.util.Map;

public class FunctionExpression extends Expression {

	public FunctionExpression(String functionName, List<Expression> arguments) {
		
	}
	
	@Override
	protected Object getResult(Function function, List<Expression> arguments) {
		Map<String, Object> globalVariables = this.getProgram().getVariables();
		
		this.getFunction().updateLocalVariables;
		
		return null;
	}
	
	
	private Function getFunction() {
		if (getProgram().getFunctions().containsKey(getName()))
			return getProgram().getFunctions().get((getName()));
		
		throw new IllegalArgumentException();
	}
	
	//NAME
	private String name;
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	
	
	//ARGUMENTSLIST
	private List<Expression> arguments;

	private void setArguments(List<MyExpression> arguments) {
		this.arguments = arguments;
	}

	

}
