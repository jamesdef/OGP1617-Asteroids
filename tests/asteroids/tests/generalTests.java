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

public class generalTests {
	private static final double EPSILON = 0.0001;

	IFacade facade;

	@Before
	public void setUp() {
		facade = new Facade();
	}
	
	
	//SHIP TESTS----------------------------
	
	//WORLD TESTS----------------------------
	public World[] createWorlds() throws ModelException{
		World worldA = facade.createWorld(200, 200);
		World worldB = facade.createWorld(-100, -100);

		World[] Total ={worldA, worldB};
		return Total;
	}
	
	public Bullet[] createBullets() throws ModelException{
		Bullet bullet1 = facade.createBullet(10000, 10000, 0, 0, 3);
		Bullet bullet2 = facade.createBullet(10000, 10000, 10, 0, 3);
		Bullet bullet3 = facade.createBullet(10000, 10000, -10, 0, 3);
		Bullet bullet4 = facade.createBullet(10000, 10000, 10, 10, 3);
		Bullet bullet5 = facade.createBullet(10000, 10000, 10, 10, 3);
		Bullet bullet6 = facade.createBullet(10000, 10000, 10, 10, 3);
		Bullet bullet7 = facade.createBullet(10000, 10000, 10, 10, 3);
		Bullet bullet8 = facade.createBullet(10000, 10000, 4999, 3000, 4);
		Bullet bullet9 = facade.createBullet(29000, 10000, 10000, 0, 3);
		Bullet bullet10 = facade.createBullet(10000, 10000, 10, 10, 3);
		Bullet bullet11 = facade.createBullet(29500, 10000, 0, 0, 3);
		Bullet bullet12 = facade.createBullet(10001, 10100, 0, 0, 3);
		Bullet bullet13 = facade.createBullet(10000, 12050, 0, 0, 80);

		Bullet[] Total ={bullet1, bullet2,bullet3,bullet4,bullet5,bullet6,bullet7,bullet8,bullet9,bullet10,bullet11,bullet12,bullet13};
		
		return Total;
	}
	
	public Ship[] createShips() throws ModelException {
		Ship ship1 = facade.createShip(10000, 10000, 0, 0, 10, 0,5E16);
		Ship ship2 = facade.createShip(10000, 10100, 0, -10, 10, 3 * Math.PI / 2,5E16);
		Ship ship3 = facade.createShip(10100, 10000, -10, 0, 10, Math.PI,0);
		
		Ship[] Total = { ship1, ship2, ship3 };
		return Total;
	}

	
	
	//Create Worlds
	@Test
	public void testCreateWorld() throws IllegalPositionException, IllegalRadiusException, ModelException{
		World worldA = createWorlds()[1];
		World worldB = createWorlds()[1];
		assertEquals(200,facade.getWorldSize(worldA)[0],EPSILON);
		assertEquals(100,facade.getWorldSize(worldB)[1],EPSILON);
		//negative position
		
	}
	
	//Ships and World
	@Test
	public void testShipsandWorld() throws ModelException{
		World world1 = createWorlds()[1];
		Ship ship1 = createShips()[1];
		System.out.println(ship1.toString());
		facade.addShipToWorld(world1, ship1);
		assert (facade.getShipWorld(ship1) == world1);
//		assert (facade.getWorldShips(world1).contains(ship7));
		
	}
	
	
	


}
