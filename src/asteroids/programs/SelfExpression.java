package asteroids.programs;

import java.util.List;

public class SelfExpression extends EntityExpression{


	@Override
	protected Object getResult(Function function, List<Expression> arguments) {
		// TODO Auto-generated method stub
		return this.getShip();
	}

}
