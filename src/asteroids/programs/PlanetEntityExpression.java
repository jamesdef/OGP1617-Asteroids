package asteroids.programs;

import java.util.List;
import java.util.Set;

import asteroids.model.Asteroid;
import asteroids.model.Entity;
import asteroids.model.MinorPlanet;
import asteroids.model.Planetoid;

public class PlanetEntityExpression extends EntityExpression {

	@Override
	protected Object getResult(Function function, List<Expression> arguments) {
		
		Set<MinorPlanet> planets = this.getWorld().getSpecificEntities(MinorPlanet.class);

		return (MinorPlanet) this.getClosest(planets);

		
	}

}
