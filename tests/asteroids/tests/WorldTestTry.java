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


public class WorldTestTry{
	
	private final static double SPEED_OF_LIGHT = 300000;
	private static final double EPSILON = 0.0001;

	IFacade facade;

	@Before
	public void setUp() {
		facade = new Facade();
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
		Ship ship4 = facade.createShip(10000, 9900, 0, 10, 10, Math.PI / 2,0);
		Ship ship5 = facade.createShip(9900, 10000, 10, 0, 10, 0,0);
		Ship ship6 = facade.createShip(1000, 1000, 10, 0, 100,0,0);
		Ship ship7 = facade.createShip(10000, 10000, SPEED_OF_LIGHT, SPEED_OF_LIGHT, 100, Math.PI / 4,0);
		Ship ship8 = facade.createShip(10001,10001,0,0,100,0,0);
		Ship ship9 = facade.createShip(0,0,0,0,10,0,0);
		Ship ship10 = facade.createShip(29000.0,10000.0,200,0,200,0,0);
		Ship ship11 = facade.createShip(10000,29000,0,200,200,0,0);
		Ship ship12 = facade.createShip(10000,10000,1000,1500,1000,0,0);
		Ship ship13 = facade.createShip(10000,12050,0,0,1000,3*Math.PI/2,0);
		
		Ship[] Total = { ship1, ship2, ship3, ship4, ship5, ship6, ship7,ship8,ship9,ship10,ship11,ship12,ship13};

		return Total;
	}


/// --> WORLD <-- ///

	/// CREATE WORLDS ///
	
	public World[] createWorlds() throws ModelException{
		World world1 = facade.createWorld(30000, 30000);
		World world2 = facade.createWorld(-40000, 30000);
		World[] Total ={world1, world2};
		return Total;
	}
	
	
	/// TEST WORLD CREATION ///
	
	@Test
	public void creationWorlds() throws ModelException{
		World world1 = createWorlds()[0];
		World world2 = createWorlds()[1];
		
		assertEquals(30000,facade.getWorldSize(world1)[0],EPSILON);
		assertEquals(30000,facade.getWorldSize(world1)[1],EPSILON);
		assertEquals(40000,facade.getWorldSize(world2)[0],EPSILON);
	}
	
	
	/// ENTITIES IN WORLD ///
	
	@Test 
	public void shipToWorld() throws ModelException{
		World world1 = createWorlds()[0];
		Ship ship7 = createShips()[6];
		facade.addShipToWorld(world1, ship7);
		assert (facade.getShipWorld(ship7) == world1);
//		assert (facade.getWorldShips(world1).contains(ship7));
	}
	
	@Test 
	public void bulletToWorld() throws ModelException{
		World world1 = createWorlds()[0];
		Bullet bullet = createBullets()[0];
		facade.addBulletToWorld(world1, bullet);
		assert (facade.getBulletWorld(bullet) == world1);
		//assert (facade.getWorldBullets(world1).contains(bullet));
	}
	
	
	/// TERMINATE ///
	
	@Test 
	public void terminateWorld() throws ModelException{
		World world = createWorlds()[0];
		Ship ship = createShips()[6];
		facade.addShipToWorld(world, ship);
		Bullet bullet = createBullets()[10];
		facade.addBulletToWorld(world, bullet);
		facade.terminateWorld(world);
		assert(facade.getBulletWorld(bullet)==null);
		assert(facade.getShipWorld(ship)==null);
		assert(facade.isTerminatedWorld(world));
	}
}