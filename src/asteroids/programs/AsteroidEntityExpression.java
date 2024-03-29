package asteroids.programs;

import java.util.List;
import java.util.Set;

import asteroids.model.Asteroid;
import asteroids.model.Ship;

public class AsteroidEntityExpression extends EntityExpression {

	@Override
	protected Object getResult() {
		Set<Asteroid> asteroids = getWorld().getSpecificEntities(Asteroid.class);
		
		return (Asteroid) this.getClosest(asteroids);
	}

}
