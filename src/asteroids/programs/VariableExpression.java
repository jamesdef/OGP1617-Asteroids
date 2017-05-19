package asteroids.programs;

import java.util.List;
import java.util.Map;

import asteroids.model.Program;

public class VariableExpression extends Expression{

	private Program program;

	public VariableExpression(String name, Program program){
		this.setName(name);
		this.setProgram(program);
	}
	
	private void setProgram(Program program) {
		// TODO Auto-generated method stub
		this.program= program;
		
	}

	private void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
		
	}

	private String name;
	
	protected String getName(){
		return this.name;
	}

	@Override
	protected Object getResult(Function function, List<Expression> arguments) {
		// TODO Auto-generated method stub
		
		if(function != null){
			Map<String, Object> variables = function.getVariables();
			
		}
		
		
		
		
		return null;
	}

	

	
}
