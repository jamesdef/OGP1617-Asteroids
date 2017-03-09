package asteroids.tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.Ship;

public class ShipTest {

	private static final double EPSILON = 0.0001;

	@Test
	public void testCreateShip(){
		
		Ship ship = new Ship(100, 200, 10, -10, 20, Math.PI);
		
		assertNotNull(ship);
		double[] position = ship.getPosition();
		assertNotNull(position);
		assertEquals(100, position[0], EPSILON);
		assertEquals(200, position[1], EPSILON);
	}

	@Test
	public void testCreateShipXIsNan() throws ModelException {
		facade.createShip(Double.NaN, 200, 10, -10, 20, -Math.PI);
	}

	@Test
	public void testCreateShipRadiusNegative() throws ModelException {
		facade.createShip(100, 200, 10, -10, -20, -Math.PI);
	}

	@Test
	public void testMove() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		facade.move(ship, 1);
		double[] position = facade.getShipPosition(ship);
		assertNotNull(position);
		assertEquals(130, position[0], EPSILON);
		assertEquals(85, position[1], EPSILON);
	}

}
