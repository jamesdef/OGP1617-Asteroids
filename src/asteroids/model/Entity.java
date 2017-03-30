package asteroids.model;
import be.kuleuven.cs.som.annotate.Basic;

public class Entity {
	public Entity(double xPosition, double yPosition, double xVelocity, double yVelocity, double radius, double mass, double density){
		setPosition(xPosition,yPosition);
		setVelocity(xVelocity,yVelocity);
		setRadius(radius);
		setOrientation(orientation);	
		setMass(mass);
		setDensity(density);
	}
	
	
	//Setters
	
	
	//POSITION
	double xPosition;
	double yPosition;
	public void setPosition(double xPosition, double yPosition) 
			throws IllegalPositionException{
		if (!isValidPosition(xPosition,yPosition)){
			
			throw new IllegalPositionException(xPosition,yPosition);}
		
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
	
	/**
	 * Returns whether the given Position is valid.
	 * 
	 * @param xPostion
	 * 		  The x-coordinate for this ship.	
	 * @param yPosition
	 * 		  The y-coordinate for this ship.	 
	 * @return True if and only if neither of the coordinates is either infinity or NaN
	 *		  | result == (isValidCoordinate(xPosition) && isValidCoordinate(yPosition))
	 */
	public static boolean isValidPosition(double xPosition, double yPosition){
	
		return (isValidCoordinate(xPosition) && isValidCoordinate(yPosition));
	}
	
	/**
	 * Returns whether this coordinate is valid
	 * 
	 * @param coordinate
	 * 		   The coordinate that we wish to inspect
	 * 
	 * @return True if and only if this coordinate is not infinity or not a number.
	 * 		   |result == (!Double.isNaN(coordinate) && (coordinate != Double.POSITIVE_INFINITY) 
				&& (coordinate != Double.NEGATIVE_INFINITY));
	 */
	public static boolean isValidCoordinate(double coordinate){
		return (!Double.isNaN(coordinate) && (coordinate != Double.POSITIVE_INFINITY) 
				&& (coordinate != Double.NEGATIVE_INFINITY));
	}
	
	
	//VELOCITY
	
	/**
	 * Variable registering the xVelocity of this Ship.
	 */
	private double xVelocity = Min_Velocity;


	/**
	 * Variable registering the yVelocity of this Ship.
	 */
	private double yVelocity = Min_Velocity;


	/**
	 * Variable registering the minimum allowed velocity.
	 */
	private static final double Min_Velocity = 0;

	/**
	 * Variable registering the maximum allowed velocity.
	 */
	private static final double Max_Velocity = 300000;
	
	/**
	 * Return the velocity of this ship, in the x-direction.
	 * @return the horizontal velocity of this ship.
	 */
	@Basic
	public double getxVelocity(){
		return this.xVelocity;

	}

	/**
	 * Return the velocity of this ship, in the y-direction.
	 * @return the vertical velocity of this ship.
	 */
	@Basic
	public double getyVelocity(){
		return this.yVelocity;

	}

	public double[] getVelocity(){
		double[] Velocity = {getxVelocity(),getyVelocity()};
		return Velocity;
	}
	
	/**
	 * Return the total velocity of this ship.
	 * @return the total velocity of this ship.
	 * @note The total velocity of a ship is computed:
	 * 		 by taking the square root of the sum of the
	 * 		 secondpowers of the horizontal and vertical velocity.
	 * 		-> sqrt(Vx^2+Vy^2)
	 */
	@Basic
	public double getTotalVelocity(){
		return Math.sqrt(Math.pow(getyVelocity(),2.0)+Math.pow(getxVelocity(),2.0));	
	}


	
	/** 
	 * This method sets the Velocity to the given value.  
	 * 
	 * @param xVelocity
	 * 		  The new velocity in the x-direction for this ship.
	 * @param yVelocity
	 * 		  The new velocity in the y-direction for this ship.
	 * 
	 * @post if one of the given velocities is not a number, the velocities are left untouched.
	 * 	     if (Double.isNaN(xVelocity) || Double.isNaN(yVelocity)){
	 * 				then new.getxVelocity() = getxVelocity()
	 * 					 new.getyVelocity() = getyVelocity()
	 * 
	 * @post If the new total velocity is valid and does not exceed the maximum velocity
	 *		 and both components are postive then the given values are set as the new velocity.
	 *		|if (!new.exceedsMaxVelocity())
	 *		|		then new.xVelocity = xVelocity;
	 *   				 new.yVelocity = yVelocity;
	 * 
	 * @post If the new total velocity exceeds the maximum velocity, 
	 * 		 both velocities scaled so that the class invariants hold.
	 * 		|if (new.exceedsMaxVelocity()) 
	 * 		|   then new.scaleVelocity()
	 * 
	 */
	public void setVelocity(double xVelocity, double yVelocity){
		
		if ((!Double.isNaN(xVelocity)) && (!Double.isNaN(yVelocity))){
		
		
			this.xVelocity = xVelocity;
			this.yVelocity = yVelocity;

				if(this.exceedsMaxVelocity(xVelocity, yVelocity)){
					this.scaleVelocity(xVelocity, yVelocity);
		}		}
	}



	/**
	 * Check whether the velocity exceeds the maximum allowed velocity.
	 * 
	 * @param xVelocity
	 * 		  The velocity in the x-direction
	 * @param yVelocity
	 *        The velocity in the y-direction
	 *        
	 * @return if the total velocity (which is computed
	 * 		  by taking the square root of the sum of the
	 * 		  secondpowers of the horizontal and vertical velocity.
	 * 		 -> sqrt(Vx^2+Vy^2)) exceeds the maximum. This returns true.
	 * 		 Otherwise it will return false.
	 * 		 | (Math.sqrt(Math.pow(getyVelocity(),2)+Math.pow(getxVelocity(),2)) > Max_Velocity);	
	 */
	public boolean exceedsMaxVelocity(double xVelocity, double yVelocity){
		return (Math.sqrt(Math.pow(getyVelocity(),2)+Math.pow(getxVelocity(),2)) > Max_Velocity);

	}

	/** 
	 * Changes the velocity to it's scaled value (so that the class invariants hold)
	 *  .
	 * 
	 * @param xVelocity
	 * 		  The velocity in the x-direction
	 * @param yVelocity
	 *        The velocity in the y-direction
	 * 		 
	 * @post The velocities are changed to their scaled values. 
	 * 		 The velocity no longer exceeds the limit.
	 * 		| new.xVelocity = (xVelocity*Max_Velocity)/this.getTotalVelocity();
	 *      | new.yVelocity = (yVelocity*Max_Velocity)/this.getTotalVelocity();	    
	 *      
	 * @note This method is only called upon if the total velocity 
	 * 		 of the given parameters exceed the maximum velocity.
	 */
	public void scaleVelocity(double xVelocity, double yVelocity ){
		double scaledxVelocity = (xVelocity*Max_Velocity)/this.getTotalVelocity();
		double scaledyVelocity = (yVelocity*Max_Velocity)/this.getTotalVelocity();

		this.xVelocity = scaledxVelocity;
		this.yVelocity = scaledyVelocity;
	}
	
	
	
	
	//RADIUS
	
	public void setRadius(){
		
	}
	
	public void setMass(double mass){
		if(isValidMass(mass)){
			this.mass=mass;
		}
		
	}
	
	public boolean isValidMass(double mass, double radius){
		double minimalMass = this.density;
	}
	
	
	
	//DENSITY
	public double density;
	public static final double defaultDensity = 	1.42*(Math.pow(10, 12));

	public void setDensity(double density){
		if(this.isValidDensity(density)){
			this.density = density;
		} else {
			this.density = this.defaultDensity;
		}
	}
	
	public boolean isValidDensity(double density){
		return (density >= this.defaultDensity);
	}
	
	public double getDensity(){
		return this.density;
	}
		

	//Inspectors
	
	//Mutators

// The goal is to create a class that involves all the thinggs that entities (ie ships and bullets)
// have in common. Bullet and ship will then be made subclasses of Entity. If needed, the methods within
// these classes can override the methods of ENtity.

/** Plaats, snelheid, standaard methoden,...
 *  
 * 
 * @author Michiel
 *
 */

public class Entity {
	
	public Entity(double xPosition, double yPosition, double xVelocity, double yVelocity, double radius, double orientation){ 
	

}
