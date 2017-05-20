package asteroids.programs;

import asteroids.model.Entity;

import java.util.List;

public interface ArithmeticInterface {

	public default boolean isValidArithmeticOperand(
			Expression expression, 
			List<Expression> arguments, 
			Function function
			) {
	
		Object result = expression.getResult(function, arguments);
		
		
		return 
				(expression instanceof ParameterExpression) 
				||
				(expression instanceof VariableExpression) 
				||
				(expression instanceof DoubleLiteralExpression) 
				||
				(((result != null) && !(result instanceof Entity)));
	
	}
}
