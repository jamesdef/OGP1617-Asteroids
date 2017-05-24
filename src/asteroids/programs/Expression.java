package asteroids.programs;

import java.util.List;

import asteroids.model.Program;
import asteroids.model.Ship;

public abstract class Expression {
	
	
	private static final int O = 0;
	//program
	
	private Program program;
	
	public void setProgram(Program program){
		this.program = program;
	}
	
	public Program getProgram(){
		return this.program;
	}
	
	protected Ship getShip() {
		return this.getProgram().getShip();
	}
	
	
	protected abstract Object getResult();
}
