package asteroids.programs;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class DisableThrust extends ActionStatement{

	
	public DisableThrust(SourceLocation location) {
	//	setSourceLocation(location);

	}
	
	@Override
	public void executeAction(Program program) {
		program.getShip().thrustOff();
		
	}

}
