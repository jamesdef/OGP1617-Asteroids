package asteroids.programs;

import java.util.List;

public class DirectionExpression extends Expression {

	@Override
	protected Object getResult(Function function, List<Expression> arguments) {
		return this.getShip().getOrientation();
	}

}
