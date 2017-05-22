package asteroids.programs;

import java.util.List;
import java.util.Set;

import asteroids.model.Asteroid;
import asteroids.model.Ship;

public class AsteroidEntityExpression extends EntityExpression {

	@Override
	protected Object getResult(Function function, List<Expression> arguments) {
		Set<Asteroid> asteroids = getWorld().getAllAsteroids();
		
		return (Asteroid) this.getClosest(asteroids);
	}

}
