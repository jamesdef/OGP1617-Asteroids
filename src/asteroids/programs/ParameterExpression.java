package asteroids.programs;

import java.util.List;
import java.util.Map;

import asteroids.model.Program;

public class ParameterExpression extends Expression {
	private Program program;

	public void ParameterExpression(String name, Program program){
		this.setname(name);
		this.setProgram(program);
	}
	
	private void setProgram(Program program) {
		// TODO Auto-generated method stub
		this.program= program;
		
	}

	private void setname(String name) {
		// TODO Auto-generated method stub
		this.name = name;
		
	}

	private String name;
	
	protected String getName(){
		return this.name;
	}

	@Override
	protected Object getValue(Function function, List<Expression> arguments) {
		// TODO Auto-generated method stub
		
		//return value of parameter in arguments list
		
		
		return null;
	}
	

	
}
