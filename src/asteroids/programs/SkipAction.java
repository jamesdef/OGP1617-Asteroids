package asteroids.programs;

import asteroids.model.Program;
import asteroids.model.exceptions.IllegalPositionException;
import asteroids.model.exceptions.IllegalRadiusException;
import asteroids.model.exceptions.IllegalShipException;
import asteroids.part3.programs.SourceLocation;

class SkipAction extends ActionStatement {

	/// CONSTRUCTOR ///
	
	public SkipAction(SourceLocation location) {
		System.out.println("SKIPACTION");
		//setSourceLocation(location);
	}

	@Override
	public void executeAction(Program program)
			throws IllegalPositionException, IllegalRadiusException, IllegalShipException {
		//do nothing		
	}


}