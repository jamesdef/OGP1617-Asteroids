package asteroids.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import asteroids.model.Asteroid;
import asteroids.model.Bullet;
import asteroids.model.Planetoid;
import asteroids.model.Program;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.model.exceptions.IllegalPositionException;
import asteroids.model.exceptions.IllegalRadiusException;
import asteroids.part3.facade.IFacade;
import asteroids.part3.programs.IProgramFactory;
import asteroids.util.ModelException;


public class Part3CustomTests {
	private static final double EPSILON = 0.0001;
	  private static final double BIG_EPSILON = 1.0E14;
	  private static final double VERY_BIG_EPSILON = 1.0E34;

	  static int nbStudentsInTeam;
	  IFacade facade;
	  IProgramFactory<?, ?, ?, Program> programFactory;
	  World filledWorld;
	  Ship ship1, ship2, ship3;
	  Bullet bullet1;
	  static int score = 0;
	  static int max_score = 0;

	  @AfterClass
	  public static void tearDownAfterClass() {
	    System.out.println("Score: " + score + "/" + max_score);
	  }

	  @Before
	  public void setUp() throws ModelException {
	    facade = new asteroids.facade.Facade();
	    programFactory = (IProgramFactory<?, ?, ?, Program>) facade.createProgramFactory();
	    nbStudentsInTeam = facade.getNbStudentsInTeam();
	    filledWorld = facade.createWorld(2000, 2000);
	    ship1 = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
	    for (int i = 1; i < 10; i++) {
	      Bullet bulletToLoad = facade.createBullet(100, 120, 0, 0, 10);
	      facade.loadBulletOnShip(ship1, bulletToLoad);
	    }
	    facade.addShipToWorld(filledWorld, ship1);
	    ship2 = facade.createShip(200, 220, 10, 5, 50, 0, 1.0E20);
	    facade.addShipToWorld(filledWorld, ship2);
	    bullet1 = facade.createBullet(300, 320, 10, 5, 50);
	    facade.addBulletToWorld(filledWorld, bullet1);
	    
	    
	  }
	  
	  
	//-------------------------------------------
	// ASTEROID TESTS
	//-------------------------------------------
	 
	  @Test
	  public void testCreateAsteroid() throws ModelException, IllegalPositionException, IllegalRadiusException {
	      World world = facade.createWorld(1000, 1000);

		  Asteroid asteroid = new Asteroid(10, 10, 10, 10, 10);
		  
		  facade.addAsteroidToWorld(world, asteroid);
		  
		  assert(world == facade.getAsteroidWorld(asteroid));
	      
	   }
	
	  
	//-------------------------------------------
	// ASTEROID COLLISIONS TESTS
	//-------------------------------------------
	  
	  @Test
	  public void testAsteroidShipCollision() throws ModelException, IllegalPositionException, IllegalRadiusException {
		  
	      World world = facade.createWorld(1000, 1000);

	      Ship ship = facade.createShip(100, 100, 10, 0, 10, 0, 1.0E20);
		  Asteroid asteroid = new Asteroid(200, 100, 0, 0, 10);
		  
		  facade.addShipToWorld(world, ship);
		  facade.addAsteroidToWorld(world, asteroid);
		  
		  facade.evolve(world, 10, null);
		  
		  assertTrue(facade.isTerminatedShip(ship));
		  assertFalse(facade.isTerminatedAsteroid(asteroid));

	      
	   }
	  
	  @Test
	  public void testAsteroidAsteroidCollision() throws ModelException, IllegalPositionException, IllegalRadiusException {
		  
	      World world = facade.createWorld(1000, 1000);

		  Asteroid asteroid1 = new Asteroid(200, 100, 0, 0, 10);
		  Asteroid asteroid2 = new Asteroid(100, 100, 10, 0, 10);
		  
		  facade.addAsteroidToWorld(world, asteroid1);
		  facade.addAsteroidToWorld(world, asteroid2);
		  
		  facade.evolve(world, 10, null);
		  
		  assertFalse(facade.isTerminatedAsteroid(asteroid2));
		  assertFalse(facade.isTerminatedAsteroid(asteroid1));

	      
	   }
	  
	  @Test
	  public void testAsteroidBulletCollision() throws ModelException, IllegalPositionException, IllegalRadiusException {
		  
	      World world = facade.createWorld(1000, 1000);

		  Asteroid asteroid = new Asteroid(200, 100, 0, 0, 10);
		  Bullet bullet = new Bullet(100, 100, 10, 0, 10);
		  
		  facade.addAsteroidToWorld(world, asteroid);
		  facade.addBulletToWorld(world, bullet);
		  
		  facade.evolve(world, 1, null);
		  assertFalse(facade.isTerminatedAsteroid(asteroid));
		  assertFalse(facade.isTerminatedBullet(bullet));
		  
		  facade.evolve(world, 9, null);
		  assertTrue(facade.isTerminatedAsteroid(asteroid));
		  assertTrue(facade.isTerminatedBullet(bullet));  
		

	      
	   }
		
	  
	  @Test
	  public void testAsteroidBoundaryCollision() throws ModelException, IllegalPositionException, IllegalRadiusException {
		  
	      World world = facade.createWorld(1000, 1000);

		  Asteroid asteroid = new Asteroid(210, 100, -10, 0, 10);
		  
		  facade.addAsteroidToWorld(world, asteroid);
		  
		  facade.evolve(world, 20, null);
		  
		  assertFalse(facade.isTerminatedAsteroid(asteroid));
		  double xPos = facade.getAsteroidPosition(asteroid)[0];
		  assertEquals(10, xPos, EPSILON);
		  
		  facade.evolve(world, 10, null);
		  double newXPos = facade.getAsteroidPosition(asteroid)[0];
		  assertEquals(110, newXPos, EPSILON);
	      
	   }
	  
	//-------------------------------------------
	// PLANETOID TESTS
	//-------------------------------------------


	  @Test
	  public void testCreatePlanetoid() throws ModelException, IllegalPositionException, IllegalRadiusException {
	      World world = facade.createWorld(1000, 1000);

		 Planetoid planetoid = new Planetoid(10, 10, 10, 10, 10, 0);
		  
		  facade.addPlanetoidToWorld(world, planetoid);
		  
		  assert(world == facade.getPlanetoidWorld(planetoid));
	      
	   }
	  
	  @Test
	  public void testMovingPlanetoid() throws ModelException, IllegalPositionException, IllegalRadiusException {
	     World world = facade.createWorld(1000, 1000);

		 Planetoid planetoid = new Planetoid(10, 10, 10, 0, 10, 0);

		  
		 facade.addPlanetoidToWorld(world, planetoid);
		 
		 facade.evolve(world, 100, null);
		 double planetoidRadius = facade.getPlanetoidRadius(planetoid);
		 assertEquals(9.999, planetoidRadius, EPSILON);

	   }
	  
	
	  
	  
	//-------------------------------------------
	// PLANETOID COLLISIONS TESTS
	//-------------------------------------------
		  
	  @Test
	  public void testPlanetoidShipCollision() throws ModelException, IllegalPositionException, IllegalRadiusException {
		  
	      World world = facade.createWorld(1000, 1000);

	      Ship ship = facade.createShip(100, 100, 10, 0, 10, 0, 1.0E20);
		  Planetoid planetoid = new Planetoid(200, 100, 0, 0, 10, 0);
		  
		  facade.addShipToWorld(world, ship);
		  facade.addPlanetoidToWorld(world, planetoid);
		  
		  facade.evolve(world, 10, null);
		  
		  assertFalse(facade.isTerminatedShip(ship));
		  assertFalse(facade.isTerminatedPlanetoid(planetoid));

	      
	   }
	  
	  @Test
	  public void testPlanetoidplanetoidCollision() throws ModelException, IllegalPositionException, IllegalRadiusException {
		  
	      World world = facade.createWorld(1000, 1000);

		  Planetoid planetoid1 = new Planetoid(200, 100, 0, 0, 10, 0);
		  Planetoid planetoid2 = new Planetoid(100, 100, 10, 0, 10, 0);
		  
		  facade.addPlanetoidToWorld(world, planetoid1);
		  facade.addPlanetoidToWorld(world, planetoid2);
		  
		  facade.evolve(world, 10, null);
		  
		  assertFalse(facade.isTerminatedPlanetoid(planetoid2));
		  assertFalse(facade.isTerminatedPlanetoid(planetoid1));

	      
	   }
	  
	  @Test
	  public void testPlanetoidBulletCollision() throws ModelException, IllegalPositionException, IllegalRadiusException {
		  
	      World world = facade.createWorld(1000, 1000);

		  Planetoid planetoid = new Planetoid(200, 100, 0, 0, 10, 0);
		  Bullet bullet = new Bullet(100, 100, 10, 0, 10);
		  
		  facade.addPlanetoidToWorld(world, planetoid);
		  facade.addBulletToWorld(world, bullet);
		  
		  facade.evolve(world, 1, null);
		  assertFalse(facade.isTerminatedPlanetoid(planetoid));
		  assertFalse(facade.isTerminatedBullet(bullet));
		  
		  facade.evolve(world, 9, null);
		  assertTrue(facade.isTerminatedPlanetoid(planetoid));
		  assertTrue(facade.isTerminatedBullet(bullet));  
		

	      
	   }
		
	  
	  @Test
	  public void testPlanetoidBoundaryCollision() throws ModelException, IllegalPositionException, IllegalRadiusException {
		  
	      World world = facade.createWorld(1000, 1000);

		  Planetoid planetoid = new Planetoid(210, 100, -10, 0, 10, 0);
		  
		  facade.addPlanetoidToWorld(world, planetoid);
		  
		  facade.evolve(world, 20, null);
		  
		  assertFalse(facade.isTerminatedPlanetoid(planetoid));
		  double xPos = facade.getPlanetoidPosition(planetoid)[0];
		  assertEquals(10, xPos, EPSILON);
		  
		  facade.evolve(world, 10, null);
		  double newXPos = facade.getPlanetoidPosition(planetoid)[0];
		  assertEquals(110, newXPos, EPSILON);
	      
	   }
  
	  
	//-------------------------------------------
	// PLANETOID-ASTEROID COLLISIONS TESTS
	//-------------------------------------------
	  
	  @Test
	  public void testAsteroidPlanetoidCollision() throws ModelException, IllegalPositionException, IllegalRadiusException {
		  
	      World world = facade.createWorld(1000, 1000);

		  Planetoid planetoid = new Planetoid(200, 100, 0, 0, 10, 0);
		  Asteroid asteroid = new Asteroid(100, 100, 10, 0, 10);
		  
		  facade.addPlanetoidToWorld(world, planetoid);
		  facade.addAsteroidToWorld(world, asteroid);
		  
		  facade.evolve(world, 10, null);
		  
		  assertFalse(facade.isTerminatedPlanetoid(planetoid));
		  assertFalse(facade.isTerminatedAsteroid(asteroid));

	      
	   }
	  
	 
}
