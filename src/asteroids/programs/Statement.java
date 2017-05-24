package asteroids.programs;

import java.util.List;

import asteroids.model.Program;
import asteroids.model.Ship;

public abstract class Statement {


	public void runAt(Bookmark bookmark){
		System.out.println("STATEMENT " + this +" Runat");

		if(bookmark.getFailedAction() == null){
			System.out.println("STATEMENT > failedAction is null, RUN STATEMENT from beginning ");
			this.run();
		} else {
			System.out.println("STATEMENT > failedAction not null, RUN STATEMENT from navigate ");

			this.navigateToAction(bookmark);
		}

	}
	
	

	//RUN
	public abstract void run();
	
	
	//PROGRAM
	private Program program;
	
	public Program getProgram(){
		return program;
	}
	
	public void setProgram(Program program){
		this.program = program;
		
	}
	
	
	//SHIP
	public Ship getShip(){
		return this.getProgram().getShip();
	}
	

	public boolean navigateToAction(Bookmark bookmark) {
		Boolean failedIsThis = (bookmark.getFailedAction() == this);
		System.out.println("SIMPEL STATEMENT > NAVIGATETOACTION, failedAction is this: " + failedIsThis);
		return (failedIsThis);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
