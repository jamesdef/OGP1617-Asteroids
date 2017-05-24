package asteroids.programs;

import java.util.List;
import java.util.Map;

import asteroids.model.Program;

public class ParameterExpression extends Expression {

	public  ParameterExpression(String name){
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
	protected Object getResult() {

		return null;
	}

	protected int getParameterNumber() {
		String parameter = getName();

		return Integer.parseInt(parameter.substring(1));
	}

}