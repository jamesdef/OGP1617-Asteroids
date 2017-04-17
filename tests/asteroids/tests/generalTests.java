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
	
	
	public Bullet[] Bullets() throws ModelException{
		Bullet bulletA = facade.createBullet(10000, 10000, 0, 0, 3);
		Bullet bulletB = facade.createBullet(10000, 10000, 10, 0, 3);
		Bullet bulletC = facade.createBullet(10000, 10000, -10, 0, 3);
		
		Bullet[] Total ={bulletA, bulletB, bulletC };
		return Total;
	}
	
	public Ship[] Ships() throws ModelException {
		Ship shipA = facade.createShip(10000, 10000, 0, 0, 10, 0,5E16);
		Ship shipB = facade.createShip(10000, 10100, 0, -10, 10, 3 * Math.PI / 2,5E16);
		Ship shipC = facade.createShip(10100, 10000, -10, 0, 10, Math.PI,0);
		
		Ship[] Total = { shipA, shipB, shipC };
		return Total;
	}
	
	public World[] Worlds() throws ModelException{
		World worldA = facade.createWorld(200, 200);
		World worldB = facade.createWorld(-100, -100);

		World[] Total ={worldA, worldB};
		return Total;
	}
	
	
	
	//WORLD TESTS----------------------------
		

	
	
	//Create Worlds
	@Test
	public void testCreateWorld() throws IllegalPositionException, IllegalRadiusException, ModelException{
		World worldA = Worlds()[1];
		World worldB = Worlds()[1];
		assertEquals(200,facade.getWorldSize(worldA)[0],EPSILON);
		assertEquals(100,facade.getWorldSize(worldB)[1],EPSILON);
		//negative position
		
	}
	
	//Ships and World
	@Test
	public void testShipsandWorld() throws ModelException{
		World world = Worlds()[1];
		Ship ship = Ships()[1];
		System.out.println(ship.toString());
		facade.addShipToWorld(world, ship);
		assert (facade.getShipWorld(ship) == world);
		assert (facade.getWorldShips(world).contains(ship));
		
	}
	
	//Bullets and World
	@Test
	public void testBulletsAndWorld() throws ModelException{
		World world = Worlds()[1];
		Bullet bullet = Bullets()[1];
		facade.addBulletToWorld(world, bullet);
		assert (facade.getBulletWorld(bullet) == world);
		assert (facade.getWorldBullets(world).contains(bullet));
		
	}
	//Overlap
	@Test
	public void testShipOverlap() throws ModelException {
		Ship shipA = Ships()[0];
		Ship shipB = Ships()[1];
		Ship shipC = Ships()[2];

		assertTrue(facade.overlap(shipA,shipB));
		assertFalse(facade.overlap(ship1,ship2));

	}
	
	
	//Ship Boundary collision
	@Test
	public void testShipBoundaryCollision() throws ModelException{
		World world = Worlds()[1];
		Ship shipA = Ships()[0];
		Ship shipB = Ships()[1];

		facade.addShipToWorld(world, ship);

		
		//collision time
		double time = facade.getTimeNextCollision(world);
		assertEquals(Double.POSITIVE_INFINITY,time,EPSILON);
		
		//collision position
		
	}
	
	//Ship ship collision
	@Test
	public void testShipsandShipsAndWorld() throws ModelException{
		World world1 = createWorlds()[1];
		Ship ship1 = createShips()[1];
		System.out.println(ship1.toString());
		facade.addShipToWorld(world1, ship1);
		assert (facade.getShipWorld(ship1) == world1);
//		assert (facade.getWorldShips(world1).contains(ship7));
		
	}
	
	//Ship Bullet collision
		@Test
		public void testShipandBulletCollsionWorld() throws ModelException{
			World world1 = createWorlds()[1];
			Ship ship1 = createShips()[1];
			System.out.println(ship1.toString());
			facade.addShipToWorld(world1, ship1);
			assert (facade.getShipWorld(ship1) == world1);
//			assert (facade.getWorldShips(world1).contains(ship7));
			
		}
		
		
	//Terminate
		@Test 
		public void testTerminateWorld() throws ModelException{
			World world = Worlds()[1];
			Ship ship = Ships()[0];
			facade.addShipToWorld(world, ship);
			
			Bullet bullet = Bullets()[0];
			facade.addBulletToWorld(world, bullet);
			facade.terminateWorld(world);
			assert(facade.getBulletWorld(bullet)==null);
			assert(facade.getShipWorld(ship)==null);
			assert(facade.isTerminatedWorld(world));
		}
		
	
	


}
