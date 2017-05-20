package asteroids.programs;

import java.util.List;

public interface DoubleExpression {

	public default Boolean isValidDoubeOperand(Function function, List<Expression> arguments, Expression expression){
		return (expression.getResult(function, arguments) instanceof Double);
	};
}
