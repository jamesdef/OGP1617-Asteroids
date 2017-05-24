package asteroids.programs;

import java.util.List;
import java.util.Set;

import asteroids.model.Entity;
import asteroids.model.Ship;

public class ShipEntityExpression extends EntityExpression{

	@Override
	protected Object getResult() {
		
		
		Set<Ship> ships = this.getWorld().getSpecificEntities(Ship.class);
		
		ships.remove(this.getShip());

		return (Ship) this.getClosest(ships);
	}

}
