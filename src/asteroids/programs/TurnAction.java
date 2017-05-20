package asteroids.programs;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.ActionStatement;

class TurnAction extends ActionStatement {

	/// CONSTRUCTOR ///

	public TurnAction(Expression angle) {
		System.out.println("TURN ACTION with angle: " + angle);
		setAngle(angle);
	}

	
	//ANGLE
	private Expression angle;
	
	private Expression getAngle() {
		return angle;
	}

	private void setAngle(Expression angle) {
		this.angle = angle;
	}

	
	//EXECTUTE
	@Override
	public void executeAction(Program program) {
		System.out.println("EXECUTING TURN ACTION");
		try {
			Double angleValue = (Double) this.getAngle().getResult(null, null);
			this.getShip().turn(angleValue);
			System.out.println(this.getShip());
		} catch (AssertionError error) {
			System.out.println("assertionerror");

			throw new IllegalArgumentException();
		}
		
	}

}