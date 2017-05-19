package asteroids.programs;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.ActionStatement;

class TurnAction extends ActionStatement {

	/// CONSTRUCTOR ///

	public TurnAction(Expression angle, SourceLocation location) {
		setAngle(angle);
		//setSourceLocation(location);
	}

	
	/// BASIC PROPERTIES ///
	
	private Expression angle;

	
	/// GETTERS ///
	
	private Expression getAngle() {
		return angle;
	}

	
	private void setAngle(Expression angle) {
		this.angle = angle;
	}

	@Override
	public void executeAction(Program program) {
		try {
			program.getShip().turn(getAngle().getValue());
		} catch (AssertionError error) {
			throw new IllegalArgumentException();
		}
		
	}

}