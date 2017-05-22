package asteroids.programs;

import java.util.List;

public class NullExpression extends Expression {

	@Override
	protected Object getResult(Function function, List<Expression> arguments) {
		return null;
	}

}
