package asteroids.model;

public class Bullet extends Entity {
	
	public Bullet(double xPosition, double yPosition, double xVelocity, double yVelocity, Ship ship, Worlsd world){
		
		setxPosition(xPosition);
		setyPosition(yPosition);
		setxVelocity(xVelocity);
		setyVelocity(yVelocity);
		
		setShip(ship);
		setWorld(world);
		
	}
/*
 * position x,y
 * velocity x, y
 * speed
 * maxSpeed
 * radius
 * minRadius 1
 * mass
 * density
 * hasBeenFired()
 * 
 * getWorld(){
 * 
 * }
 * 
 * getSource(){
 * 
 * }
 * 
 * 
 */
	
	
	/**
	 * Variable registering the x cordinate of the position of the bullet
	 * 
	 */
	private double xPosition
}
