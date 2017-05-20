package asteroids.programs;

import java.util.List;

public class NullEntity extends EntityExpression{

	@Override
	protected Object getResult(Function function, List<Expression> arguments) {
		return null;
	}

}
