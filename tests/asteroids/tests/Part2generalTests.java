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
import asteroids.model.exceptions.IllegalPositionException;
import asteroids.model.exceptions.IllegalRadiusException;
import asteroids.facade.Facade;
import asteroids.part2.facade.IFacade;
import asteroids.util.ModelException;

public class Part2generalTests {
	private static final double EPSILON = 0.0001;

	IFacade facade;
	

	@Before
	public void setUp() {
		facade = new Facade();
	}
	
	
	//SHIP TESTS----------------------------
	
	
	public Bullet[] Bullets() throws ModelException{
		Bullet bulletA = facade.createBullet(203, 200, -10, 0, 3);
		Bullet bulletB = facade.createBullet(300, 300, 0, 0, 3);
		Bullet bulletC = facade.createBullet(400, 400, 0, 0, 3);
		
		Bullet[] Total ={bulletA, bulletB, bulletC };
		return Total;
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
	
	
	
	//WORLD TESTS----------------------------
		

	
	
	//Create Worlds
	@Test
	public void testCreateWorld() throws IllegalPositionException, IllegalRadiusException, ModelException{
		World worldA = Worlds()[0];
		World worldB = Worlds()[1];
		assertEquals(500,facade.getWorldSize(worldA)[0],EPSILON);
		assertEquals(500,facade.getWorldSize(worldB)[1],EPSILON);
		//negative position
		
	}
	
	//Ships and World
	@Test
	public void testShipsandWorld() throws ModelException{
		World world = Worlds()[1];
		Ship shipA = Ships()[0];
		facade.addShipToWorld(world, shipA);
		assert (facade.getShipWorld(shipA) == world);
		assert (facade.getWorldShips(world).contains(shipA));
		
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
	@Test(expected = ModelException.class)
	public void testShipsThatOverlap() throws ModelException {
		World world = Worlds()[1];

		Ship shipA = Ships()[0];
		Ship shipB = Ships()[1];
		Ship shipD = Ships()[3];
		
		
		facade.addShipToWorld(world, shipA);
		facade.addShipToWorld(world, shipB);
		facade.addShipToWorld(world, shipD);


		assertFalse(facade.overlap(shipA,shipB));
		assertTrue(facade.overlap(shipB,shipD));

	}
	

	
	
	//Test getEntityAt
	@Test
	public final void getEntityAt() throws ModelException{
		World world = Worlds()[0];
		Ship shipA = Ships()[0];
		Bullet bulletA = Bullets()[0];
		
		facade.addBulletToWorld(world, bulletA);
		facade.addShipToWorld(world, shipA);
		
		assertEquals(shipA,facade.getEntityAt(world, 100, 200));
		assertEquals(bulletA,facade.getEntityAt(world, 203, 200));
	}
	
	
	//Ship Boundary collision
	@Test
	public void testShipAndBoundaryCollision() throws ModelException{
		World world = Worlds()[1];
		Ship shipC = Ships()[2];

		facade.addShipToWorld(world, shipC);
		
		//collision time
		double time = facade.getTimeNextCollision(world);
		assertEquals(19 ,time,EPSILON);
		
		//collision position
		
	}
	
	//Bullet Boundary collision
		@Test
		public void testBulletAndBoundaryCollision() throws ModelException{
			World world = Worlds()[1];
			Bullet bulletA = Bullets()[0];

			facade.addBulletToWorld(world, bulletA);
			
			//collision time
			double time = facade.getTimeNextCollision(world);
			assertEquals(20 ,time,EPSILON);
			
			//collision position
			
		}
	
	//Ship ship collision
	@Test
	public void testShipsShipsCollision() throws ModelException{
		World world = Worlds()[1];
		Ship shipE = Ships()[4];
		Ship shipB = Ships()[1];

		facade.addShipToWorld(world, shipE);
		facade.addShipToWorld(world, shipB);
		double time = facade.getTimeNextCollision(world);

		assertEquals(18 ,time,EPSILON);


		
	}
	
	//Ship Bullet collision
		@Test
		public void testShipandBulletCollsionWorld() throws ModelException{
			World world = Worlds()[1];
			Ship shipA = Ships()[0];
			Bullet bulletA = Bullets()[0];
			
			facade.addShipToWorld(world, shipA);
			facade.addBulletToWorld(world, bulletA);
			double time = facade.getTimeNextCollision(world);
			assertEquals(9 ,time,EPSILON);
			
		}
		
		
	//Terminate
		@Test 
		public void testTerminateWorld() throws ModelException{
			World world = Worlds()[1];
			Ship shipA = Ships()[0];
			facade.addShipToWorld(world, shipA);
			
			Bullet bullet = Bullets()[0];
			facade.addBulletToWorld(world, bullet);
			facade.terminateWorld(world);
			assert(facade.getBulletWorld(bullet)==null);
			assert(facade.getShipWorld(shipA)==null);
			assert(facade.isTerminatedWorld(world));
		}
		
	// Evolve 

		@Test
		public void testEvolve() throws ModelException{
			Ship shipC = Ships()[2];
			World world = Worlds()[0];
			facade.addShipToWorld(world, shipC);
			assertEquals(1,facade.getEntities(world).size());
			facade.evolve(world, 2,null);
			assertEquals(180,facade.getShipPosition(shipC)[0],EPSILON);
		}
		
		@Test
		public void testEvolveBoundaryCollision() throws ModelException{
			Ship shipC = Ships()[2];	
			World world = Worlds()[0];
			
			facade.addShipToWorld(world, shipC);

			facade.evolve(world, 40, null);
			assertEquals(220,facade.getShipPosition(shipC)[0],EPSILON);	
		}
		
		@Test
		public void testEvolveBulletMultipleCollision() throws ModelException{
			Bullet bulletA = Bullets()[0];
			
			World world = Worlds()[0];

			facade.addBulletToWorld(world, bulletA);

			facade.evolve(world, 20, null);
			assertEquals(3,facade.getBulletPosition(bulletA)[0],EPSILON);	
			
			facade.evolve(world, 50, null);
			assertEquals(491,facade.getBulletPosition(bulletA)[0],EPSILON);	
			

			facade.evolve(world, 50, null);
			assertTrue(facade.isTerminatedBullet(bulletA));	
		}
		
		
		
		
		
		
		
		
	
		
	
	


}
