package asteroids.programs;

import java.util.List;

public class DirectionExpression extends Expression {

	@Override
	protected Object getResult() {
		return this.getShip().getOrientation();
	}

}
