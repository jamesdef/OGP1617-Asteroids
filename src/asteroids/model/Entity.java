package asteroids.model;
import java.util.ArrayList;
import java.util.List;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

//The goal is to create a class that involves all the thinggs that entities (ie ships and bullets)
//have in common. Bullet and ship will then be made subclasses of Entity. If needed, the methods within
//these classes can override the methods of ENtity.

/** 
*  A class for dealing with entities. We could define these as 'objects that can move through space'.
*  An example of such an entity can be a ship or a bullet. 
*  Properties described are: position, velocity,radius and orientation.
*  
* @invar   The highest possible absolute, total velocity is lower than a certain maximum, the entity can never exceed this speed.
* 	      	|!exceedsMaxVelocity(getxVelocity(), getyVelocity())
*  
* @invar	The orientation of the entity must be a valid value.
* 			|isValidOrientation(getOrientation())
* 
* @invar 	The radius of each entity must be a valid value.
* 			|isValidRadius(getRadius())
* 
* @invar   The coordinates of an entity must be valid.
* 			|isValidPosition(getxPosition,getyPosition);
* 
* @invar  	The entity has to belong to a proper world at all times.
* 			| hasProperWorld()
* 
* @invar   The mass of an entity must be valid.
* 		    |isValidMass(this.getEntityMass)
* 
* @invar   The density of an entity must be valid.
* 		    |isValidDensity(this.getEntityDensity)
* 
* 
* @version 1.0
* @author Michiel De Koninck & James Defauw
*
*/
public abstract class Entity {
	
	//-------------------------------- CONSTRUCTOR -------------------------------- 
	
	/**
	 * Initialize this new entity with given position,radius, speed, orientation and mass.
	 * 
	 * 
	 * @param  xPosition
	 *         The position of this entity, according to the x-axis. 
	 *         Expressed in km.
	 *         
	 * @param  yPosition
	 *         The position of this entity,according to the y-axis.
	 *         Expressed in km.
	 *         
	 * @param  radius
	 * 		   The radius of this new entity.
	 * 		   Expressed in km.	
	 * 
	 * @param  xVelocity 
	 *         The velocity of this vessel, in the x-direction.
	 *         Expressed in km/s.
	 *         
	 * @param  yVelocity 
	 * 		   The velocity of the vessel, in the y-direction. 
	 * 		   Expressed in km/s.
	 *         
	 * @param  orientation
	 * 		   The orientation of this vessel, i.e., it's direction.
	 * 		   Expressed in radians.    
	 * 
	 * @effect The given parameters are set as the properties of the new entity.
	 * 		   |setPosition(xPosition,yPosition);
	 *	       |setVelocity(xVelocity,yVelocity);
	 *	       |setRadius(radius);
	 *	       |setOrientation(orientation);
	 *
	 *@note    Any new entity initialized with this constructor
	 * 		   will satisfy all its class invariants. The setters will see to this in their implementation.
	 */
	public Entity(double xPosition, double yPosition, double xVelocity, double yVelocity, double radius, double orientation)
		throws IllegalPositionException, IllegalRadiusException{
		
		// At this point we can invoke our mutators. They will see to it that the class invariants hold at all times.
		setPosition(xPosition,yPosition);
		setVelocity(xVelocity,yVelocity);
		setRadius(radius);
		setOrientation(orientation);	
	}

	
	// ----------------Termination-------------------
	
	/**
	 * This method terminates this entity.
	 * 
	 * @post This entity is now terminated
	 * 		| new.isTerminated() == true
	 */
	public void terminate(){
		if(this.getWorld()!= null)
			this.getWorld().removeEntity(this);
		this.isTerminated = true;
	}
	
	/**
	 * Checks whether this entity is terminated.
	 * @return The state of this entity; whether it is terminated or not.
	 */
	public boolean isTerminated(){
		return this.isTerminated;
	}
	
	/**
	 * A variable registering whether or not this enity is terminated.
	 * Initialised as false.
	 */
	private boolean isTerminated = false;
	
	
	// -----------------------  VARIABLES (DEFAULTS & FINAL) --------
    
	/**
	 * Variable registering the xPosition of this Entity.
	 */
	protected double xPosition = 0.0;

	/**
	 * Variable registering the yPosition of this Entity.
	 */
	protected double yPosition = 0.0;


	/**
	 * Variable registering the xVelocity of this Entity.
	 */
	protected double xVelocity = Min_Velocity;


	/**
	 * Variable registering the yVelocity of this Entity.
	 */
	protected double yVelocity = Min_Velocity;


	/**
	 * Variable registering the minimum allowed velocity.
	 */
	protected static final double Min_Velocity = 0;

	/**
	 * Variable registering the maximum allowed velocity.
	 */
	protected static final double Max_Velocity = 300000;

	/**
	 * Variable registering the orientation of this Entity.
	 */
	protected double orientation = Min_Orientation;

	/**
	 * Variable registering the minimum allowed orientation
	 */
	protected static final double Min_Orientation = 0.0;


	/**
	 * Variable registering the maximum allowed orientation
	 */
	protected static final double Max_Orientation = 2.0*Math.PI;

	
	/**
	 * Variable registering the minimum allowed Radius.
	 */
	protected static double Min_Radius = 10.0;

	/**
	 * Variable registering the radius of this Entity.
	 * @Override
	 */
	private double radius = Min_Radius;
    
    /**
     * Variable registering the world to which this entity belongs.
     * Initialised as null; the entity has no world per default.
     */
    private World world = null;


	private double density;

//-----------------------------------------
    
	//Inspectors
    
    /**
     * Return the world to which this entity belongs.
     * @return The world to which this entity belongs.
     * 		   null is returned if this entity does not belong to any world.
     */
    public World getWorld() {
    	return this.world;
    }
    
    /**
	 * Return the x-coordinate of this entity.  
	 * @return the x-coordinate of this entity.
	 */
	@Basic
	public double getxPosition(){
		return this.xPosition;	
	}	


	/**
	 * Return the y-coordinate of this entity.
	 * @return the y-coordinate of this entity.
	 * 
	 */
	@Basic
	public double getyPosition(){
		return this.yPosition;	
	}	


	/**
	 * Returns the velocity of this Entity, in the x-direction.
	 * @return the horizontal velocity of this Entity.
	 */
	@Basic
	public double getxVelocity(){
		return this.xVelocity;
	}

	/**
	 * Returns the velocity of this Entity, in the y-direction.
	 * @return the vertical velocity of this Entity.
	 */
	@Basic
	public double getyVelocity(){
		return this.yVelocity;
	}
	
	/**
	 * Returns the velocity in an array: (Vx,Vy).
	 * 
	 * @return A set of doubles; the xVelocity and yVelocity.
	 * 		   |double[] Velocity = {getxVelocity(),getyVelocity()}
	 * 		   |return Velocity
	 */
	public double[] getVelocity(){
		double[] Velocity = {getxVelocity(),getyVelocity()};
		return Velocity;
	}

	/**
	 * Return the total velocity of this Entity.
	 * @return the total velocity of this Entity.
	 * @note The total velocity of a Entity is computed:
	 * 		 by taking the square root of the sum of the
	 * 		 secondpowers of the horizontal and vertical velocity.
	 * 		-> sqrt(Vx^2+Vy^2)
	 */
	@Basic
	public double getTotalVelocity(){
		return Math.sqrt(Math.pow(getyVelocity(),2.0)+Math.pow(getxVelocity(),2.0));	
	}

	/**
	 *  Return the orientation of this ship.
	 * @return the orientation of this ship.
	 */
	@Basic
	public double getOrientation(){
		return this.orientation;	
	}	

	/**
	 *  Return the radius of this Entity.
	 * @return the radius of this Entity.
	 */
	@Basic
	@Immutable
	public double getRadius(){
		return this.radius;
	}

	/** 
	 *  Returns the x and y coordinates within an array.
	 *  
	 * @return the x and y coordinate as an array.
	 * 		 
	 */
	@Basic
	public double[] getPosition(){
		double[] position = {this.getxPosition(),this.getyPosition()};	
		return position;			
	}
	
	/**
	 * Returns the density of this ship.
	 * @return the density of this ship.
	 */
	public double getDensity(){
		return this.density;
	}
	
	/**
	 * Return the mass of this entity.
	 * @return the mass of this entity.
	 */
	@Basic
	public double getMass(){
		return this.mass;
	}
	
    /**
     * Variable registering the mass of this entity.
     */
    protected double mass;
	
	//Setters
	
	
	/**
	 * Sets the given world as the world of this entity.
	 * 
	 * @param world
	 * 		  The world that we want to link this entity to.
	 * 
	 * @pre if the given world is effective, it already has to reference this entity as one of its entities.
	 * 		|if (world != null)
	 * 		|   then world.hasEntity(this)
	 * @pre if the given world is not effective and this entity belongs to an active world, 
	 *      than that world can not reference this world as one of its entities.
	 *      |if ((world == null) && this.getWorld() != null)
	 *      |	then !this.getWorld().hasEntity(this)
	 * 
	 * @post This entity now references the given world as the world to which it belongs.
	 * 		|new.getWorld() == world
	 */
	public void setWorld(World world){
		// If the world is effective, it already has to have this entity as one if its entities. 
		assert((world == null) || (world.hasEntity(this)));
		// If the world is not effective, and this entity belongs to an effective world, 
		// then that world can not reference this entity as one of its entities.
		assert((world != null) || (this.getWorld() == null) || (!this.getWorld().hasEntity(this)));
		
		this.world = world;
	}
	
	
	/**
	 * Check whether this entity can belong to the given world.
	 * 
	 * @param world
	 * 		 The world that we will investigate
	 * @return True if the given world is effective and the given world can has this entity as one of its entities.
	 * 		   | result == (world != null && world.canHaveAsEntity(this));
	 */
	public boolean canHaveAsWorld(World world){
		return (world != null && world.canHaveAsEntity(this));
	}
	
	/**
	 * Check whether this entity belongs to a proper world.
	 * 
	 * @return true if and only if this entity can be rightly associated with it's world and if this world is
	 * 		   either not effective or if it has this entity as one of its entities.
	 * 		   |result == (   (canHaveAsWorld(getWorld()) &&
	 * 						  ((getWorld() == null) || getWorld().hasEntity(this)))   )
	 */
	public boolean hasProperWorld(){
		return (canHaveAsWorld(getWorld()) && ((getWorld() == null) || getWorld().hasEntity(this)));
	}
	
	//POSITION
	
	/** 
	 * Sets the position to the given coordinates, if these make for a valid position.
	 * 
	 * @param xPostion
	 * 		  The new x-coordinate for this ship.	
	 * @param yPosition
	 * 		  The new y-coordinate for this ship.
	 * @post The x-coordinate of the  ship equals the given xPosition.
	 * 		 |new.getxPosition == xPosition
	 * @post The y-coordinate of the  ship equals the given yPosition.
	 * 		 |new.getyPosition == yPosition
	 * 
	 * @throws IllegalPositonException
	 * 		 The given xPosition or yPosition is not valid.
	 * 	     | !isValidPosition(xPosition,yPosition)
	 */
	public void setPosition(double xPosition, double yPosition) 
			throws IllegalPositionException{
		if (!isValidPosition(xPosition,yPosition)){
			throw new IllegalPositionException(xPosition,yPosition);
		}
		
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
	
	/**
	 * Returns whether the given Position is valid.
	 * 
	 * @param xPostion
	 * 		  The x-coordinate for this Entity.	
	 * @param yPosition
	 * 		  The y-coordinate for this Entity.	 
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
	 * This method sets the Velocity to the given value.  
	 * 
	 * @param xVelocity
	 * 		  The new velocity in the x-direction for this Entity.
	 * @param yVelocity
	 * 		  The new velocity in the y-direction for this Entity.
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

				if(this.exceedsMaxVelocity(xVelocity, yVelocity)){
					this.scaleVelocity(xVelocity, yVelocity);
				}		
				else{
					this.xVelocity = xVelocity;
					this.yVelocity = yVelocity;
				}
		}
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
	
// Je programeert hier nominaal, dan moeten mensen maar een valid orientation geven, is ze negatief en mag dat niet?
// Dat kan jou niets schelen, wij werken met ValidOrientations, dat staat in de pre-conditie, de gebruiker moet daaraan voldoen.

	/**
	 *  Sets the orientation to the given angle, if this is a valid angle.
	 * 
	 * @param orientation
	 * 		  The new, given orientation of the ship.
	 * @pre The given orientation must be a valid one.
	 * 		|isValidOrientation(orientation)
	 * @post The orientation of the ship is now changed to the given value
	 *		|new.getOrientation()== orientation
	 */
	public void setOrientation(double orientation){
		assert isValidOrientation(orientation);
		this.orientation = orientation;	
	}

// OPLETTEN MET HET GEBRUIK VAN "Min_Orientation" in je documentatie. 
// dit is immers een private variable, wat betekent dat de gebruiker die niet kan zien. Misschien best een methode
// getMin_Orientation invoeren.
	/**
	 *  Check whether the given orientaton is a valid value.
	 * 
	 * @param orientations
	 * 		  The orientation of which we need to check whether it is legal.
	 * 
	 * @return True if and only if the given orientation is within the boundaries opposed upon orientation. (And has to be a number)
	 * 		   |result == (getMin_Orientation <= orientation) && (orientation < getMax_Orientation) && !Double.isNaN(orientation)
	 * 
	 */
	public static boolean isValidOrientation(double orientation){
		return ((Min_Orientation <= (orientation)) && (orientation <= Max_Orientation) && (!Double.isNaN(orientation)));	
	}
	
    
	/** 
	 * Sets the radius to the given Value, if it is valid.
	 * 
	 * @param radius
	 * 		  The new, given radius of the ship.
	 * 
	 * @post The radius of the ship is now equal to the given, valid radius.
	 * 		|new.getRadius() == radius	
	 * 
	 * @throws  IllegalRadiusException
	 * 		   The given radius is not a valid radius.
	 * 		   | ! isValidRadius(radius)
	 */
	public void setRadius(double radius) throws IllegalRadiusException{
		if (!isValidRadius(radius)){
			throw new IllegalRadiusException(radius);}
		this.radius = radius;
	}

	/** 
	 * Checks whether the given radius has a valid value.
	 * 
	 * @param  radius
	 * 		   The radius of the ship.
	 * 
	 * @return True if the radius exceeds the minimal radius
	 * 		   false if the radius is less than the minimal_radius. 
	 * 		   Or if the radius is Infinity or not a number.
	 * 		   | radius >= getMin_Radius;
	 */
	public static boolean isValidRadius(double radius){
		return (radius >= Min_Radius && (!Double.isNaN(radius) && radius != Double.POSITIVE_INFINITY));
	}
	
	// ---------------- Moving -------------------
	
	
	/**
	 *  Move the entity, given a certain duration.
	 * 
	 * @param duration
	 * 		  The duration of the movement, given in seconds
	 * 
	 * @throws IllegalDurationException
	 * 		   If the duration is not valid, the method throws an exception
	 * 		 | !isValidDuration(duration)
	 * 		   
	 * @throws IllegalPositionException 
	 * 		   If the position is not valid, the method throws an exception.
	 * 		   | !isValidPosition(newxPosition, newyPosition)
	 * 
	 * @effect The new position of the entity is calculated by adding the duration multiplied with the velocity, 
	 *         to the positon of the ship.
	 *        |newxPosition = this.getxPosition() + (duration)*(this.getxVelocity());
	 *	      |newyPosition = this.getyPosition() + (duration)*(this.getyVelocity());
	 *	       |this.setPosition(newxPosition, newyPosition);
	 * 
	 */
	public void move(double duration) throws IllegalPositionException, IllegalDurationException{
		if (!isValidDuration(duration)){
			throw new IllegalDurationException(duration);
		}
		double newxPosition = this.getxPosition() + (duration)*(this.getxVelocity());
		double newyPosition = this.getyPosition() + (duration)*(this.getyVelocity());

		this.setPosition(newxPosition, newyPosition);
	}
	
	
	/**
	 *  Check whether the given duration is legal.
	 * 
	 * @param duration
	 * 		  The duration of the specific movement of the ship
	 * @return true if the duration is a non-negative number and finite.
	 * 		   | return (duration >= 0 && !Double.isNaN(duration) && (duration != Double.POSITIVE_INFINITY))
	 */			
	public boolean isValidDuration(double duration){
		return ((duration >= 0) && (!Double.isNaN(duration)) && (duration != Double.POSITIVE_INFINITY));
	}
	
	
	// ---------------------  COLLISION and Relative Postioning ----------------------
	
    //  COLLISION PREDICTION  : DEFENSIVE
	
	/**
	 *  Return the distance betwheen two entities.
	 * 
	 * @param other
	 *        The other entity of which we want to know the distance to this entity.
	 * 
	 * @return If the 2 entities are in fact different entities; The distance between the two entities. Computed as below:
	 * 		   | centerDistance = Math.sqrt(Math.pow((this.getxPosition()-other.xPosition), 2.0)+ 
	 * 											Math.pow((this.getyPosition()-other.yPosition), 2.0));
	 * 		   | result  == centerDistance - this.getRadius() - other.radius;
	 * 
	 * @return If the given entities refer the same entity, 0 will be the returned result.
	 * 		   |if entity == other entity
	 * 		   |result == 0
	 */
	public double getDistanceBetween(Entity other){
		double result = 0;
		if (this != other){
		double centerDistance = Math.sqrt(Math.pow((this.getxPosition()-other.xPosition), 2.0)+
									Math.pow((this.getyPosition()-other.yPosition), 2.0));
		result = centerDistance - this.getRadius() - other.radius;
		}
		return result;
	}


	/**
	 * Check whether two Entities overlap.
	 * 
	 * @param other
	 * 		  The other entity of which we want to check if it overlaps with this entity.
	 * @return True if and only if the distance between the two entities is less than zero.
	 * 		   | getDistanceBetween(other) < 0
	 */
	public boolean overlap(Entity other){
		return (this.getDistanceBetween(other) < 0);	
	}
	
	
	/**
	 * This method returns the time until an entity will reach a certain boundary of the world it is in.
	 * 
	 * @return If this entity will never collide with a boundary, the method will return infinity.
	 * 		   Otherwise it will return the time until the entity reaches a boundary.
	 * 		   A use for the following method: 
	 * 		   One can use the result to move the given entity by the resulted duration to place the
	 * 		   entity directly against the boundary. If one were to move the ship for a slightly longer duration; 
	 * 		   the ship would be out of the world's boundary.
	 * 		   |if (extra > 0){
	 * 		   | 	this.move(resulting_time + extra)
	 * 		   |	!this.getWorld().withinBoundaries(this)
	 * 	       If we had not increased resulting_time with extra, the entity would have still been within this world.
	 * 
	 * @throw IllegalStateException
	 * 		  The entity is not within a certain world.
	 * 		  |this.getWorld() == null
	 * 		  
	 */
	public double getTimeToBoundaryCollision(){
		if (this.getWorld() == null){
			throw new IllegalStateException();
			}
		
		List<Double> times = new ArrayList<>();
		
		
		return 0;
		
	}

// DECLARATIEVE SPECIFICATIE:
	// HOUDT in dat je duidt op wat het nut is van de functie, wat je kan doen met het resultaat.
	//Je bespreekt dus niet exact hoe het resultaat bekomen wordt, maar wel wat je ermee kan doen.
	// Daar heeft de gebruiker immers het meeste aan.
	
	/**
	 *  Calculates the time to the point where the two given entities collide.
	 * 	If they never collide, it returns positive infinity.
	 * 
	 * @param other
	 * 		  The other entity with which this entity might collide
	 * 
	 * 
	 * @return Returns in how many seconds 2 entities will collide.
	 * 		   If they never collide it wil return positive infinity.
	 *			
	 *		   This result can, for example, be used to calculate the distance between a entity and
	 *		   it's collision with the other object. This simply by multiplying the time (found by this method)
	 *		   with the speed of the entity. One could also use the method move to move the entity to to the place of collision.
	 *
	 * @see implementation		   
	 *	   	
	 * @throws IllegalCollisionException
	 * 		   If two entities overlap, this method does not apply.
	 */
	public double getTimeToEntityCollision(Entity other) throws IllegalCollisionException {
		if (overlap(other)){
			throw new IllegalCollisionException(this,other);
		}
		
		//Sigma is centerdistance at the moment of collision : sum of two radii.
		double sigma = other.getRadius() + this.getRadius();
		double[] Dv= {other.getxVelocity() - this.getxVelocity(), other.getyVelocity() - this.getyVelocity()};
		double[] Dr= {other.getxPosition() - this.getxPosition(), other.getyPosition() - this.getyPosition()};

		// Avoided scalair product by implementing this 'fake multiplication' to guarantee easy computing.
		double DrDr = Math.pow(Dr[0], 2.0)+Math.pow(Dr[1], 2.0);
		double DvDr = Dv[0]*Dr[0] + Dv[1]*Dr[1];
		double DvDv = Math.pow(Dv[0], 2.0)+Math.pow(Dv[1], 2.0);

		double d = Math.pow(DvDr, 2.0) - (DvDv)*(DrDr- Math.pow(sigma, 2.0));

		if ((d <= 0) || (DvDr >= 0)){
			return Double.POSITIVE_INFINITY;
		}
		
		else{
			return -(DvDr + Math.sqrt(d))/(DvDv);}
	}
	
	/** 
	 * Returns the position on which two entities collide, if they ever collide. Otherwise it returns null.
	 * 
	 * @param other
	 * 		  The other entity with which this entity might collide.
	 * 
	 * @return The Position (an array) on which two entities collide (if they ever collide).
	 * 		   Computation starts from initial positions and calculates the difference in positions at the time of collision.
	 * 		   This information is used to return the Collision coordinates.
	 * 		   Null if they never collide.
	 * 
	 * @see implementation
	 *		
	 * 
	 * @throws IllegalCollisionException
	 * 		   Created within getTimeToCollision(other)
	 * 		   We cannot calculate the collision position of two overlapping entities.
	 */
	public double[] getEntityCollisionPosition(Entity other) throws IllegalCollisionException{

		//Using the time to collision, we now compute the position of the collision.
		//For this we first calculate where the two others are at, at the time of collision.
		//Then we calculate where exactly they collide. 

		double T = getTimeToEntityCollision(other);

		if (T == Double.POSITIVE_INFINITY){
			return null;
		}

		//Where are the entities after time T?

		double[] FirstEntityPosition = {this.getxPosition() + this.getxVelocity()*T, this.getyPosition() + this.getyVelocity()*T};
		double[] SecondEntityPosition = {other.getxPosition() + other.getxVelocity()*T, other.getyPosition() + other.getyVelocity()*T};

		//The position of the first entity, incremented with it's radius 
		// (in the right direction = direction to the center of the other entity) results in the answer.

		double[] CenterDistance = {SecondEntityPosition[0] - FirstEntityPosition[0], SecondEntityPosition[1]- FirstEntityPosition[1]};
		double Norm = Math.sqrt(Math.pow(CenterDistance[0],2.0)+ Math.pow(CenterDistance[1],2.0));
		double[] NormedCenterDistance = {(SecondEntityPosition[0] - FirstEntityPosition[0])/Norm, (SecondEntityPosition[1]- FirstEntityPosition[1])/Norm};
		double[]RadiusWithDirection = {this.getRadius()*NormedCenterDistance[0],this.getRadius()*NormedCenterDistance[1]};
		double[] CollisionCoordinates = {FirstEntityPosition[0]+RadiusWithDirection[0], FirstEntityPosition[1]+RadiusWithDirection[1]};

		return CollisionCoordinates;
	}	
}