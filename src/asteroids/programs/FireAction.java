package asteroids.programs;

import asteroids.model.Program;
import asteroids.model.exceptions.IllegalPositionException;
import asteroids.model.exceptions.IllegalRadiusException;
import asteroids.model.exceptions.IllegalShipException;
import asteroids.part3.programs.SourceLocation;

public class FireAction extends ActionStatement {

	/// CONSTRUCTOR ///
	
	public FireAction() {
		//setSourceLocation(location);
		System.out.println("FIREACTION");
	}


	@Override
	public void executeAction(Program program) throws IllegalPositionException, IllegalRadiusException, IllegalShipException {
		program.getShip().fireBullet();
		
	}

}