package asteroids.programs;

import asteroids.model.*;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.SourceLocation;

import java.util.List;

public class ProgramFactory<E, S, F, P> implements IProgramFactory<Expression, Statement, Function, Program> {

	@Override
	public Program createProgram(List<Function> functions, Statement main) { 
		System.out.println("PROGRAMFACTORY CreateProgram");
		// TODO Auto-generated method stub
		
		return new Program(functions, main);
	}

	@Override
	public Function createFunctionDefinition(String functionName, Statement body, SourceLocation sourceLocation) { System.out.println("PROGRAMFACTORY");
		// TODO Auto-generated method stub
		return new Function(functionName, body);
	}

	@Override
	public Statement createAssignmentStatement(String variableName, Expression value, SourceLocation sourceLocation) { System.out.println("PROGRAMFACTORY");
		// TODO Auto-generated method stub
		return new AssignmentStatement(variableName,value);
	}

	@Override
	public Statement createWhileStatement(Expression condition, Statement body, SourceLocation sourceLocation) { System.out.println("PROGRAMFACTORY");
		try{
			return new WhileStatement((BooleanExpression) condition, body);
		} catch(ClassCastException e){
			throw new IllegalArgumentException("no boolean");
		}
	}

	@Override
	public Statement createBreakStatement(SourceLocation sourceLocation) { System.out.println("PROGRAMFACTORY");
		// TODO Auto-generated method stub
		return new BreakStatement();
	}

	@Override
	public Statement createReturnStatement(Expression value, SourceLocation sourceLocation) { System.out.println("PROGRAMFACTORY");
		// TODO Auto-generated method stub
		return new ReturnStatement(value);
	}

	@Override
	public Statement createIfStatement(Expression condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) { System.out.println("PROGRAMFACTORY");
		try {
			return new IfElseStatement((BooleanInterface) condition, ifBody, elseBody);
		} catch (ClassCastException error) { System.out.println("PROGRAMFACTORY");
			throw new IllegalArgumentException();
		}
	}

	@Override
	public Statement createPrintStatement(Expression value, SourceLocation sourceLocation) { System.out.println("PROGRAMFACTORY");
		// TODO Auto-generated method stub
		
		return new PrintStatement(value);
	}

	@Override
	public Statement createSequenceStatement(List<Statement> statements, SourceLocation sourceLocation) { System.out.println("PROGRAMFACTORY");
		// TODO Auto-generated method stub
		return new BlockStatement(statements);
	}

	@Override
	public Expression createReadVariableExpression(String variableName, SourceLocation sourceLocation) { System.out.println("PROGRAMFACTORY");
		// TODO Auto-generated method stub
		return new VariableExpression(variableName);
	}

	@Override
	public Expression createReadParameterExpression(String parameterName, SourceLocation sourceLocation) { System.out.println("PROGRAMFACTORY");
		// TODO Auto-generated method stub
		return new ParameterExpression(parameterName);
	}

	@Override
	public Expression createFunctionCallExpression(String functionName, List<Expression> actualArgs,
			SourceLocation sourceLocation) { System.out.println("PROGRAMFACTORY");
		return new FunctionExpression(functionName, actualArgs);
	}

	@Override
	public Expression createChangeSignExpression(Expression expression, SourceLocation sourceLocation) { System.out.println("PROGRAMFACTORY");
		try {
			return new SignChangeExpression((DoubleExpression) expression);
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("You've made a negation with a non-arithmetic expression");
		}
	}

	@Override
	public Expression createNotExpression(Expression expression, SourceLocation sourceLocation) { System.out.println("PROGRAMFACTORY");
		try{
			return new NegationExpression((BooleanExpression) expression);
		} catch(ClassCastException e){
			throw new IllegalArgumentException("no boolean value");
		}
	}

	@Override
	public Expression createDoubleLiteralExpression(double value, SourceLocation location) { System.out.println("PROGRAMFACTORY");
		// TODO Auto-generated method stub
		return new DoubleLiteralExpression(value);
	}

	@Override
	public Expression createNullExpression(SourceLocation location) { System.out.println("PROGRAMFACTORY");
		// TODO Auto-generated method stub
		return new NullExpression();
	}

	@Override
	public Expression createSelfExpression(SourceLocation location) { System.out.println("PROGRAMFACTORY");
		// TODO Auto-generated method stub
		return new SelfExpression();
	}

	@Override
	public Expression createShipExpression(SourceLocation location) { System.out.println("PROGRAMFACTORY");
		// TODO Auto-generated method stub
		return new ShipEntityExpression();
	}

	@Override
	public Expression createAsteroidExpression(SourceLocation location) { System.out.println("PROGRAMFACTORY");
		// TODO Auto-generated method stub
		return new AsteroidEntityExpression();
	}

	@Override
	public Expression createPlanetoidExpression(SourceLocation location) { System.out.println("PROGRAMFACTORY");
		// TODO Auto-generated method stub
		return new PlanetoidEntityExpression();
	}

	@Override
	public Expression createBulletExpression(SourceLocation location) { System.out.println("PROGRAMFACTORY");
		// TODO Auto-generated method stub
		return new BulletEntityExpression();
	}

	@Override
	public Expression createPlanetExpression(SourceLocation location) { System.out.println("PROGRAMFACTORY");
		// TODO Auto-generated method stub
		return new PlanetEntityExpression();
	}

	@Override
	public Expression createAnyExpression(SourceLocation location) { System.out.println("PROGRAMFACTORY");
		// TODO Auto-generated method stub
		return new AnyEntityExpression();
	}

	@Override
	public Expression createGetXExpression(Expression e, SourceLocation location) { System.out.println("PROGRAMFACTORY");
		try {
			return new PositionXExpression(e);
		} catch (IllegalArgumentException exception) {
			throw new IllegalArgumentException("no entity");
		}
	}

	@Override
	public Expression createGetYExpression(Expression e, SourceLocation location) { System.out.println("PROGRAMFACTORY");
		try {
			return new PositionYExpression(e);
		} catch (IllegalArgumentException exception) {
			throw new IllegalArgumentException("no entity");
		}
	}

	@Override
	public Expression createGetVXExpression(Expression e, SourceLocation location) { System.out.println("PROGRAMFACTORY");
		try {
			return new VelocityXExpression(e);
		} catch (IllegalArgumentException exception) {
			throw new IllegalArgumentException("no entity");
		}
	}

	@Override
	public Expression createGetVYExpression(Expression e, SourceLocation location) { System.out.println("PROGRAMFACTORY");
		try {
			return new VelocityYExpression(e);
		} catch (IllegalArgumentException exception) {
			throw new IllegalArgumentException("no entity");
		}
	}

	@Override
	public Expression createGetRadiusExpression(
			Expression e, SourceLocation location) { 
		System.out.println("PROGRAMFACTORY radiusexp");
		try {
			return new RadiusExpression(e);
		} catch (IllegalArgumentException exception) {
			throw new IllegalArgumentException("not an entity");
		}
	}

	@Override
	public Expression createLessThanExpression(Expression e1, Expression e2, SourceLocation location) { 
		System.out.println("PROGRAMFACTORY createLessThanExpression");
	try {

		System.out.println(e1);
		System.out.println(e2);

		return new LessThanExpression((DoubleExpression) e1, (DoubleExpression) e2);
	} catch (ClassCastException e) {
		throw new IllegalArgumentException("");
	}
	}

	@Override
	public Expression createEqualityExpression(Expression e1, Expression e2, SourceLocation location) { System.out.println("PROGRAMFACTORY");
		// TODO Auto-generated method stub
		return new EqualExpression(e1, e2);
	}

	@Override
	public Expression createAdditionExpression(Expression e1, Expression e2, SourceLocation location) { System.out.println("PROGRAMFACTORY");
		// TODO Auto-generated method stub
		try{
			return new AdditionExpression((DoubleExpression) e1, (DoubleExpression) e2);
		} catch (ClassCastException e) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public Expression createMultiplicationExpression(Expression e1, Expression e2, SourceLocation location) { System.out.println("PROGRAMFACTORY");
		// TODO Auto-generated method stub
	try{
		return new MultiplicationExpression((DoubleExpression) e1, (DoubleExpression) e2);
	} catch (ClassCastException e) {
		throw new IllegalArgumentException();
	}
	}

	@Override
	public Expression createSqrtExpression(Expression e, SourceLocation location) { System.out.println("PROGRAMFACTORY");
	try {
		return new SqrtExpression((DoubleExpression) e);
	} catch (ClassCastException f) {
		throw new IllegalArgumentException("non artihmetic");
}
	}

	@Override
	public Expression createGetDirectionExpression(SourceLocation location) { System.out.println("PROGRAMFACTORY");
		// TODO Auto-generated method stub
		return new DirectionExpression();
	}

	@Override
	public Statement createThrustOnStatement(SourceLocation location) { System.out.println("PROGRAMFACTORY");
		// TODO Auto-generated method stub
		return new EnableThrust();
	}

	@Override
	public Statement createThrustOffStatement(SourceLocation location) { System.out.println("PROGRAMFACTORY");
		// TODO Auto-generated method stub
		return new DisableThrust(location);
	}

	@Override
	public Statement createFireStatement(SourceLocation location) { System.out.println("PROGRAMFACTORY");
		// TODO Auto-generated method stub
		return new FireAction();
	}

	@Override
	public Statement createTurnStatement(Expression angle, SourceLocation location) { 
		System.out.println("PROGRAMFACTORY TURNACTION");
		// TODO Auto-generated method stub
		return new TurnAction(angle);
	}

	@Override
	public Statement createSkipStatement(SourceLocation location) { System.out.println("PROGRAMFACTORY");
		// TODO Auto-generated method stub
		return new SkipAction(location);
	}
}