package asteroids.programs;

public interface BooleanInterface {
	
	public default boolean isValidBoolean(Expression expression){
		return (expression instanceof BooleanInterface);
	}

}
