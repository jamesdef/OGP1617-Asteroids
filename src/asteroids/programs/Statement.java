package asteroids.programs;

import java.util.List;

import asteroids.model.Program;
import asteroids.model.Ship;

public abstract class Statement {


	public void runAt(Bookmark bookmark, List<Expression> arguments){
		if(bookmark == null){
			this.run(arguments);
		} else {
			this.navigateToAction(bookmark);
			
			//INDIEN 1 -> MOET NU DE TWEEDE ACTION UITVOEREN
			
			
			
			
		}
	}
	
	

	//RUN
	public abstract void run(List<Expression> arguments);
	
	
	//PROGRAM
	private Program program;
	
	public Program getProgram(){
		return program;
	}
	
	public void setProgram(Program program){
		this.program = program;
		
		//if(this instanceof )
		
		//statement
	}
	
	
	//SHIP
	public Ship getShip(){
		return this.getProgram().getShip();
	}
	
	
	
	
	
	
}
