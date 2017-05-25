package asteroids.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import asteroids.model.Ship;
import asteroids.model.exceptions.IllegalCollisionException;
import asteroids.model.exceptions.IllegalDurationException;
import asteroids.model.exceptions.IllegalPositionException;
import asteroids.model.exceptions.IllegalRadiusException;
import asteroids.util.ModelException;

public class Part1ShipTest {

	private static final double EPSILON = 0.0001;

	@Test
	public void testCreateDefaultShip() throws IllegalPositionException, IllegalRadiusException{
		
		Ship ship = new Ship();
		
		assertNotNull(ship);
		
		double[] position = ship.getPosition();
		assertNotNull(position);
		assertEquals(0, position[0], EPSILON);
		assertEquals(0, position[1], EPSILON);
		
		double xVelocity = ship.getXVelocity();
		double yVelocity = ship.getYVelocity();
		assertEquals(0, xVelocity, EPSILON);
		assertEquals(0, yVelocity, EPSILON);
		
		double radius = ship.getRadius();
		assertEquals(10, radius, EPSILON);
		
		double orientation = ship.getOrientation();
		assertEquals(0, orientation, EPSILON);
			
	}
	
	@Test
	public void testPosition() throws IllegalPositionException, IllegalRadiusException{
		Ship ship = new Ship(100.0, 200.0, 10.0, 10.0, 10.0, 0.0, 0);
		
		double[] position = ship.getPosition();
		assertNotNull(position);
		assertEquals(100, position[0], EPSILON);
		assertEquals(200, position[1], EPSILON);
		
		ship.setPosition(200, 300);
		double[] newPosition = ship.getPosition();
		assertNotNull(newPosition);
		assertEquals(200, newPosition[0], EPSILON);
		assertEquals(300, newPosition[1], EPSILON);
		
		//negative position
		
	}
	
	@Test
	public void testOrientation() throws IllegalPositionException, IllegalRadiusException{
		Ship ship = new Ship(0.0, 0.0, 10.0, 10.0, 10.0, 0.5,0.0);
		double orientation = ship.getOrientation();
		assertNotNull(orientation);
		assertEquals(0.5, orientation, EPSILON);
		
	}
	
	@Test
	public void testVelocity() throws IllegalPositionException, IllegalRadiusException{
		Ship ship = new Ship(0.0, 0.0, 10.0, 10.0, 10.0, 0.0, 0.0);
		
		double xVelocity = ship.getXVelocity();
		double yVelocity = ship.getYVelocity();

		assertEquals(10, xVelocity, EPSILON);
		assertEquals(10, yVelocity, EPSILON);
		
		ship.setVelocity(20, 30);
		double newxVelocity = ship.getXVelocity();
		double newyVelocity = ship.getYVelocity();
		assertEquals(20, newxVelocity, EPSILON);
		assertEquals(30, newyVelocity, EPSILON);
		
		
	}
	
	@Test (expected = IllegalDurationException.class)
	public void testMove() throws ModelException, IllegalPositionException, IllegalRadiusException, IllegalDurationException {
		Ship ship = new Ship(100.0, 100.0, 30.0, -15.0, 20.0, 0.0, 0.0);
		
		ship.move(1);		
		double[] position = ship.getPosition();
		assertNotNull(position);
		assertEquals(130, position[0], EPSILON);
		assertEquals(85, position[1], EPSILON);
		
		//test negative value --> We expect this part to give an IllegalDurationExcpetion)
		ship.move(-3);		
		double[] position1 = ship.getPosition();
		
		assertNotNull(position1);
		assertEquals(130, position1[0], EPSILON);
		assertEquals(85, position1[1], EPSILON);
		
	}
//	
//	@Test @Deprecated
//	public void testThrust() throws IllegalPositionException, IllegalRadiusException{
//		Ship ship = new Ship(0, 0, 10, 10, 10, 0);
//		ship.thrust(10);
//		double newxVelocity = ship.getxVelocity();
//		assertEquals(20, newxVelocity, EPSILON);
//		
//		
//	}
	
	@Test
	public void testTurn() throws IllegalPositionException, IllegalRadiusException{
		Ship ship = new Ship(0.0, 0.0, 10.0, 10.0, 10.0, 0.0, 0.0);
		ship.turn(0.5);
		double newOrientation = ship.getOrientation();
		assertEquals(0.5,newOrientation,EPSILON);		
	}

	@Test
	public void testCollision() throws IllegalPositionException, IllegalRadiusException, IllegalCollisionException {
		Ship ship1 = new Ship(0, 0, 10.0 , 0, 10, 0, 0);
		Ship ship2 = new Ship(40, 0, 0, 0, 10, 0, 0);
		
		double distanceBetween = ship1.getDistanceBetween(ship2);
		assertEquals(20, distanceBetween, EPSILON);
		
		double timeToCollision = ship1.getTimeToEntityCollision(ship2);
		assertEquals(2.0, timeToCollision, EPSILON);
		
		double[] collisionPosition = ship1.getEntityCollisionPosition(ship2);
		double xCord = collisionPosition[0];
		double yCord = collisionPosition[1];
		assertEquals(30, xCord, EPSILON);
		assertEquals(0, yCord, EPSILON);
	}
}
