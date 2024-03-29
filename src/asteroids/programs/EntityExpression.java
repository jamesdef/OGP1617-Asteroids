package asteroids.programs;

import java.util.Map;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import asteroids.model.Asteroid;
import asteroids.model.Bullet;
import asteroids.model.Entity;
import asteroids.model.MinorPlanet;
import asteroids.model.Planetoid;
import asteroids.model.Ship;
import asteroids.model.World;

public abstract class EntityExpression extends Expression {

	
	
	
	//WORLD
	protected World getWorld() {
		return this.getShip().getWorld();
	}
	
	protected Set<Entity> getOtherEntities() {
		return getWorld().getAllEntities();
	}

	
	protected Entity getClosest(Set<? extends Entity> set){
		Ship ship = this.getShip();
		Entity result = null;
		
		if (!set.isEmpty()){
			// Makes a map with the distances as keys and the entities that have these distances in a list as values.
			Map<Double, List<Entity>> ordered_map = (Map) set.stream().collect(Collectors.groupingBy(e -> ship.getDistanceBetween(e)));
			
			// get the smallest distance.
			Double key = ordered_map.keySet().stream().reduce(Double::min).get();
			
			// get the first entity with this minimal distance.
			result = (ordered_map.get(key)).get(0);
		}
		return result;
	}
	

	

}
