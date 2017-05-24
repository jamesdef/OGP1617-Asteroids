package asteroids.programs;

import java.util.List;
import java.util.Set;

import asteroids.model.Planetoid;

public class PlanetoidEntityExpression extends EntityExpression{

	@Override
	protected Object getResult() {
		
		Set<Planetoid> planetoids = getWorld().getSpecificEntities(Planetoid.class);
		
		return (Planetoid) this.getClosest(planetoids);
	}

}
