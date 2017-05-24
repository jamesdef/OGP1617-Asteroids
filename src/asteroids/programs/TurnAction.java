package asteroids.programs;

import asteroids.model.Program;
import asteroids.model.exceptions.IllegalPositionException;
import asteroids.model.exceptions.IllegalRadiusException;
import asteroids.model.exceptions.IllegalShipException;
import asteroids.programs.ActionStatement;

class TurnAction extends ActionStatement {

	/// CONSTRUCTOR ///

	public TurnAction(Expression angle) {
		System.out.println("TURN ACTION with angle: " + angle.getResult());
		setAngle(angle);
	}

	
	//ANGLE
	private Expression angle;
	
	private Expression getAngle() {
		return angle;
	}

	private void setAngle(Expression angle) {
		this.angle = angle;
		System.out.println("angle set as: " + angle.getResult());

	}

	
	//EXECTUTE
	@Override
	public void executeAction(Program program) 
			throws IllegalPositionException, IllegalRadiusException, IllegalShipException
	{
		System.out.println("EXECUTING TURN ACTION, program is: " + program);
		Double angleValue = (Double) this.getAngle().getResult();
		System.out.println("ANGLE VALUE IS: " + angleValue);

		try {
			this.getShip().turn(angleValue);
			System.out.println(this.getShip());
		} catch (AssertionError error) {
			System.out.println("assertionerror");

			throw new IllegalArgumentException();
		}
		
	}

}