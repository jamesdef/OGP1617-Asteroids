package asteroids.programs;

import java.util.List;
import java.util.Map;

public class FunctionExpression extends Expression {

	public FunctionExpression(String functionName, List<Expression> arguments) {
		
	}
	
	@Override
	protected Object getResult() {
		return null;
	}
	
	
	private Function getFunction() {
	return null;
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

	private void setArguments(List<Expression> arguments) {
	//	this.arguments = arguments;
	}

	

}
