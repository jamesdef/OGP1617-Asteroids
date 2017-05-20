package asteroids.programs;

import java.util.List;

import asteroids.model.Program;

class DoubleLiteralExpression extends Expression implements ArithmeticInterface  {

	protected DoubleLiteralExpression(double value) {
		setValue(value);
	}
	
	//VALUE
	private Double value;

	private void setValue(double value) {
		this.value = value;
	}
	
	private double getValue(){
		return this.value;
	}


	@Override
	protected Object getResult(Function function, List<Expression> arguments) {
		return (Double) this.getValue();
	}
	


	

}
