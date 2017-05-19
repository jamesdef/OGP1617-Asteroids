package asteroids.programs;

import asteroids.model.Program;

public class EnableThrust extends ActionStatement{

	
	public EnableThrust() {
	//	setSourceLocation(location);

	}
	
	@Override
	public void executeAction(Program program) {
		program.getShip().thrustOn();
		
	}

}
