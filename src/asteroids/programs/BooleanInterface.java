package asteroids.programs;

import java.util.List;

public interface BooleanInterface {
	
	public default boolean isValidBoolean(Expression expression){
		return (expression instanceof BooleanInterface);
	}

}
