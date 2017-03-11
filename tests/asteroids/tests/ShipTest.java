package asteroids.tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.Ship;

public class ShipTest {

	private static final double EPSILON = 0.0001;

	@Test
	public void testCreateDefaultShip(){
		
		Ship ship = new Ship();
		
		assertNotNull(ship);
		
		double[] position = ship.getPosition();
		assertNotNull(position);
		assertEquals(0, position[0], EPSILON);
		assertEquals(0, position[1], EPSILON);
		
		double xVelocity = ship.getxVelocity();
		double yVelocity = ship.getyVelocity();
		assertEquals(0, xVelocity, EPSILON);
		assertEquals(0, yVelocity, EPSILON);
		
		double radius = ship.getRadius();
		assertEquals(10, radius, EPSILON);
		
		double orientation = ship.getOrientation();
		assertEquals(0, orientation, EPSILON);

		
		
	}
	
	@Test
	public void testPosition(){
		Ship ship = new Ship(100, 200, 10, 10, 10, 0);
		
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
	public void testOrientation(){
		Ship ship = new Ship(0, 0, 10, 10, 10, 0.5);
		double orientation = ship.getOrientation();
		assertNotNull(orientation);
		assertEquals(0.5, orientation, EPSILON);
		
		

	}
	
	@Test
	public void testVelocity(){
		Ship ship = new Ship(0, 0, 10, 10, 10, 0);
		
		double xVelocity = ship.getxVelocity();
		double yVelocity = ship.getyVelocity();

		assertEquals(10, xVelocity, EPSILON);
		assertEquals(10, yVelocity, EPSILON);
		
		ship.setVelocity(20, 30);
		double newxVelocity = ship.getxVelocity();
		double newyVelocity = ship.getyVelocity();
		assertEquals(20, newxVelocity, EPSILON);
		assertEquals(30, newyVelocity, EPSILON);
		
		
	}
	
	@Test
	public void testMove() throws ModelException {
		Ship ship = new Ship(100, 100, 30, -15, 20, 0);
		
		ship.move(1);		
		double[] position = ship.getShipPosition(ship);
		assertNotNull(position);
		assertEquals(130, position[0], EPSILON);
		assertEquals(85, position[1], EPSILON);
		
		//test negative value
		ship.move(-3);		
		double[] position = ship.getShipPosition(ship);
		assertNotNull(position);
		assertEquals(130, position[0], EPSILON);
		assertEquals(85, position[1], EPSILON);
		
	}
	
	@Test
	public void testThrust(){
		Ship ship = new Ship(100, 100, 30, -15, 20, 0);

		
	}
	
	@Test
	public void testTurn(){
		Ship ship = new Ship(0, 0, 10, 10, 10, 0);
		ship.turn(0.5);
		double newOrientation = ship.getOrientation();
		assertNotNull(newOrientation);
		assertEquals(0.5, newOrientation, EPSILON);
		
		//negative angle
		ship.turn(-0.5);
		
	}
	
	@Test
	public void testCollision(){
		
	}


	
	
	

}
