package asteroids.programs;

import java.util.HashMap;
import java.util.Map;

import asteroids.model.Program;


public class Function {

	public Function(String name, Statement body){
		setName(name);
		setBody(body);
	}
	
	protected Statement getBody(){
		return this.body;
	}
	
	public String getName(){
		return this.name;
	}
	
	protected Map<String, Object> getVariables() {
		return variables;
	}

	protected void setLocalVariables(Map<String, Object> local_variables) {
		this.variables = variables;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public void setProgram(Program program) {
		this.program = program;
	}	
	
	protected void setBody(Statement body){
		this.body = body;
	}
	
	private Program program;
	private String name;
	private Statement body;
	private Map<String, Object> variables = new HashMap<String, Object>();
}
