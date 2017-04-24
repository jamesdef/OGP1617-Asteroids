package asteroids.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.*;
import asteroids.facade.Facade;
import asteroids.part2.facade.IFacade;
import asteroids.util.ModelException;

import static org.junit.Assert.*;

import org.junit.Test;

public class SingleTest {
	
	private static final double EPSILON = 0.0001;
	
	private static final double SPEED_OF_LIGHT = 300000;

	IFacade facade;

	@Before
	public void setUp() {
		facade = new Facade();
	}
	
	public Ship[] Ships() throws ModelException {
		Ship shipA = facade.createShip(100, 200, 0, 0, 10, 3 * Math.PI / 2,5E16);
		Ship shipB = facade.createShip(100, 100, 0, 0, 10, 0,5E16);
		Ship shipC = facade.createShip(200, 100, -10, 0, 10, Math.PI,0);
		Ship shipD = facade.createShip(110, 100, 0, 0, 10, Math.PI,0);
		Ship shipE = facade.createShip(300, 100, -10, 0, 10, Math.PI,0);
		
		Ship[] Total = { shipA, shipB, shipC, shipD, shipE };
		return Total;
	}
	
	public World[] Worlds() throws ModelException{
		World worldA = facade.createWorld(500, 500);
		World worldB = facade.createWorld(-500, -500);

		World[] Total ={worldA, worldB};
		return Total;
	}

	@Test (expected = ModelException.class)
	public void testShipsThatOverlap() throws ModelException {
		World world = Worlds()[1];

//		Ship shipA = Ships()[0];
		Ship shipB = Ships()[1];
		Ship shipD = Ships()[3];
		
		System.out.println(shipB.significantOverlap(shipD));
		
//		assertFalse(facade.overlap(shipA,shipB));
		assertTrue(facade.overlap(shipB,shipD));
		
//		facade.addShipToWorld(world, shipA);
		facade.addShipToWorld(world, shipB);
		facade.addShipToWorld(world, shipD);

		



	}

}
