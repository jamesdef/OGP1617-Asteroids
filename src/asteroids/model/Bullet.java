package asteroids.model;

public class Bullet extends Entity {
	
	public Bullet(double xPosition, double yPosition, double xVelocity, double yVelocity, Ship ship, World world){
		
		setPosition(xPosition,yPosition);
		setVelocity(xVelocity,yVelocity);
		setRadius(radius);
		setOrientation(orientation);
		
		setShip(ship);
		setWorld(world);
		
	}
	
	// Initialising Variables & Defaults
	
	/**
	 * Variable registering the radius of this Ship.
	 * @Override
	 */
	private double radius = Min_Radius;
	
	/**
	 * Variable registering the minimum allowed Radius.
	 * The minimum radius may change in the future. 
	 * But it will always remain the same for all Bullets.
	 * @Override
	 */
	private static double Min_Radius = 1.0;
	
	/**
	 * Variable registering the default density of a bullet.
	 */
	private final static double Default_Density = 7.8*(Math.pow(10, 12));
	
	
	/**
	 * Variable registering the density of this bullet.
	 * @Override
	 */
	private double density = Default_Density;
	
	/**
	 * Variable registering the mass of this bullet.
	 * @Override
	 */
	private double mass = density*(4/3)*Math.PI*(Math.pow(radius, 3));
    
	

	// Getters
	
	
	
	// Setters
	
	 /**
     * This method makes it possible for the user to change the lower bound
     * imposed upon ships, for all ships.
     * 
     * @param Lower_Bound
     * 		  The new minimum radius.
     * 
     * @post The new universal lower bound for the radius is equal to the given value.
     * 		 |new.Min_Radius == Lower_Bound
     * 
     * @throws IllegalArgumentException
     * 		   The given argument is not valid.
     * 		   | !(Lower_bound > 0)
     */
    public void setMin_Radius(double Lower_Bound){
    	if (Lower_Bound > 0){
    		Bullet.Min_Radius = Lower_Bound;
    	}
    	else{
    		throw new IllegalArgumentException(Double.toString(Lower_Bound));
    	}
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
 * Either loaded or fired by the ship which it has as source.
 * getState(){
 * }
 * 
 * 
 * 
 */
}
