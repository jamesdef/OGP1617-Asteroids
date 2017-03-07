import be.kuleuven.cs.som.annotate.Basic;

/**
 * A class for dealing with ships that have a certain position, radius, speed and orientation.
 * 
 * 		-	Work In Progress
 * 
 * @author James Defauw & Michiel De Koninck
 *
 */

public class Ship {

//Constructors: #2
	/**
	 * Initialize this new ship with given position,radius, speed and orientation.
	 * 
	 * 
	 * 
	 * @param  xPosition
	 *         The position of this ship, according to the x-axis.
	 *         
	 * @param  yPosition
	 *         The position of this ship,according to the y-axis.
	 *         
	 * @param  radius
	 * 		   The radius of this new ship.
	 * 
	 * @param  xVelocity 
	 *         The velocity of this vessel, in the x-direction.
	 *         
	 * @param  yVelocity 
	 * 		   The velocity of the vessel, in the y-direction. 
	 *         
	 * @param  orientation
	 * 		   The orientation of this vessel, i.e., it's direction.
	 *       
	 * @post   The lowest possible velocity is zero, it can only move forward.
	 * 		  
	 * @post   The lowest possible radius is 10 km. (This minimum can easily be changed).
	 * 
	 * @post   The highest possible speed is 300000 (km/s) it can never exceed this.
	 * 		   digital clock is equal to 23.
	 * 
	 * @post   The orientation of the ship will always be a value between 0(Right) and 2PI(Right).
	 * 
// NOG GEEN FORMELE IMPLEMENTATIE VAN VOORGAANDE POST-condities 
	 *      
	 * 
	 */
// Position X and Y are described seperatly, this proves to be the easiest to work with. Same goes for speed.
	public Ship(double xPosition, double yPosition, double xVelocity, double yVelocity, double radius, double orientation){
		// At this point we can invoke our mutators, because the range is known.
		setPosition(xPosition,yPosition);
		setVelocity(xVelocity,yVelocity);
		setRadius(radius);
		setOrientation(orientation);
		
	}
	
	

	/**
	 * Initialize this new ship with their parameters (position,speed,radius,orientation) set to their lowest possible Value.
	 * 
	 * @effect This new ship is initialized in the center of the grid: (0,0)
	 * 			xPosition = 0
	 * 			yPosition = 0
	 * 			It's radius will be set to it's lowest possible value.
	 * 			It's velocity will be set to 0. The ship will not be moving.
	 * 			It's orientation is to the right, i.e.: it has an angle of 0� to the x-axis.
	 * 
	 * 			
	 */
	public Ship(){
		this(0.0,0.0,0.0,0.0,Ship.getMinRadius(),0.0);
	}
	 
	

// The inspectors 
	public double getVelocity(){
		
	}
	
	public double getPosition(){
		
	}	
	public double getOrientation(){
		
		
	}	
	public double getRadius(){
		
	}
	
	/**
	 * 
	 * @return
	 */
	@Basic
	public static double getMinRadius() {
		return 10; 
	}
	
	//thrust is total programming
	public void thrust(double acceleration){
		double a = Math.max(0, acceleration);
		double xVelocity = 0 + a*(Math.cos(this.getOrientation()));
		double yVelocity = 0 + a*(Math.sin(this.getOrientation()));
		
		this.setVelocity(xVelocity, yVelocity);
		
	}
	
	/**
	 * 
	 * @param duration
	 * 			The duration of the movement
	 * @post The duration is non-negative 
	 */
	
	public void move( double duration){
		
		
	}
	
	public void turn(double angle){
		
	}
// The Mutators...
	
	
}
