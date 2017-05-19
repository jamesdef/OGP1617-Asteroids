package asteroids.model;

import asteroids.model.exceptions.IllegalBulletException;
import asteroids.model.exceptions.IllegalCollisionException;
import asteroids.model.exceptions.IllegalDurationException;
import asteroids.model.exceptions.IllegalPositionException;
import asteroids.model.exceptions.IllegalRadiusException;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;



// TODO: Basic methoden vereisen geen echte specificatie
// TODO: oppassen met finals
// TODO: Throws moeten enkel in de specifactie staan als ze uitermate relevant zijn
// TODO: Postcondities en classe invarianten niet herhalen bij subklassen en overriding
// TODO: Constructoren zijn raw als ze niet de laatste subklasse zijn.
// TODO: De constructor van een abstracte klasse is steeds 'model'
// TODO: Value klasse voor vector operaties

/** 
 *  A class for dealing with entities. 
 *  We could define these as 'objects that can move through space'.
 *  Entities can interact with eachother upon collision.
 *  It can belong to a certain world.
 *  Properties described are: position, velocity,radius, density and mass.
 *  
 * @invar   The highest possible absolute, total velocity is lower than a certain maximum, the entity can never exceed this speed.
 * 	      	|!exceedsMaxVelocity(getxVelocity(), getyVelocity())
 * 
 * @invar 	The radius of each entity must be a valid value.
 * 			|isValidRadius(getRadius())
 * 
 * @invar   The coordinates of an entity must be valid.
 * 			|isValidPosition(getxPosition,getyPosition);
 * 
 * @invar  	The entity has to belong to a proper world at all times.
 * 			|hasProperWorld()
 * 
 * 
 * @version 2.0
 * @author Michiel De Koninck & James Defauw
 *
 */
public abstract class Entity {

	//-------------------------------- CONSTRUCTOR -------------------------------- 

	/**
	 * Initialize this new entity with given position,radius, speed.
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
	 *         The velocity of this entity, in the x-direction.
	 *         Expressed in km/s.
	 *         
	 * @param  yVelocity 
	 * 		   The velocity of the entity, in the y-direction. 
	 * 		   Expressed in km/s.
	 *          
	 * @effect The given parameters are set as the properties of the new entity.
	 * 		   |setPosition(xPosition,yPosition);
	 *	       |setVelocity(xVelocity,yVelocity);
	 *	       |setRadius(radius);
	 *
	 *@note    Any new entity initialized with this constructor
	 * 		   will satisfy all its class invariants. The setters will see to this in their implementation.
	 */
	@Raw @Model
	public Entity(double xPosition, double yPosition, double xVelocity, double yVelocity, double radius)
			throws IllegalPositionException, IllegalRadiusException{

		// At this point we can invoke our mutators. They will see to it that the class invariants hold at all times.
		setPosition(xPosition,yPosition);
		setVelocity(xVelocity,yVelocity);
		setRadius(radius);
	}


// ----------------Termination-------------------

	/**
	 * This method terminates this entity.
	 * 
	 * @post This entity is now terminated
	 * 		| new.isTerminated() == true
	 */
	public void terminate() {
		if(this.getWorld()!= null)
			this.getWorld().removeEntity(this);
		this.isTerminated = true;
	}

	/**
	 * Checks whether this entity is terminated.
	 * @return The state of this entity; whether it is terminated or not.
	 */
	@Basic
	public boolean isTerminated(){
		return this.isTerminated;
	}

	/**
	 * A variable registering whether or not this enity is terminated.
	 * Initialised as false.
	 */
	private boolean isTerminated = false;


	//----------------------------------------- Standard Inspectors--------------

	/**
	 * Return the world to which this entity belongs.
	 * @return The world to which this entity belongs.
	 * 		   null is returned if this entity does not belong to any world.
	 */
	@Basic
	public World getWorld() {
		return this.world;
	}

	/**
	 * Return the x-coordinate of this entity.  
	 */
	@Basic
	public double getxPosition(){
		return this.xPosition;	
	}	


	/**
	 * Return the y-coordinate of this entity.
	 */
	@Basic
	public double getyPosition(){
		return this.yPosition;	
	}	


	/**
	 * Returns the velocity of this Entity, in the x-direction.
	 */
	@Basic
	public double getxVelocity(){
		return this.xVelocity;
	}

	/**
	 * Returns the velocity of this Entity, in the y-direction.
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
	@Basic
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
	 * Return the radius of this Entity.
	 */
	@Basic
	public double getRadius(){
		return this.radius;
	}

	/** 
	 *  Returns the x and y coordinates within an array.	 
	 */
	@Basic
	public double[] getPosition(){
		double[] position = {this.getxPosition(),this.getyPosition()};	
		return position;			
	}

	/**
	 * Returns the density of this entity.
	 */
	@Basic
	public double getDensity(){
		return this.density;
	}

	/**
	 * Return the mass of this entity.
	 */
	@Basic
	public double getMass(){
		return this.mass;
	}



	// ------------SETTERS--------------

	//---------------WORLD; associations--------------

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
	@Raw
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
	@Raw
	public boolean hasProperWorld(){
		return (canHaveAsWorld(getWorld()) && ((getWorld() == null) || getWorld().hasEntity(this)));
	}

	//--------------POSITION-----------------------

	/** 
	 * Sets the position to the given coordinates, if these make for a valid position.
	 * 
	 * @param xPostion
	 * 		  The new x-coordinate for this entity.	
	 * @param yPosition
	 * 		  The new y-coordinate for this entity.
	 * @post The x-coordinate of the  entity equals the given xPosition.
	 * 		 |new.getxPosition == xPosition
	 * @post The y-coordinate of the  entity equals the given yPosition.
	 * 		 |new.getyPosition == yPosition
	 * 
	 * @throws IllegalPositonException
	 * 		 The given xPosition or yPosition is not valid.
	 * 	     | !isValidPosition(xPosition,yPosition)
	 */
	@Raw
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
	@Raw
	public static boolean isValidPosition(double xPosition, double yPosition){


		return (isValidCoordinate(xPosition) && isValidCoordinate(yPosition));



	}

	/**
	 * Returns whether this coordinate is valid
	 * 
	 * @param coordinate
	 * 		   The coordinate that we wish to inspect
	 * 
	 * @return True if and only if this coordinate is not a number.
	 * 		   |result == (!Double.isNaN(coordinate));
	 */
	@Raw
	public static boolean isValidCoordinate(double coordinate){
		return (!Double.isNaN(coordinate));
	}


	//--------------------------VELOCITY-----------------

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
	@Raw
	public void setVelocity(double xVelocity, double yVelocity){

		if ((!Double.isNaN(xVelocity)) && (!Double.isNaN(yVelocity))){

			if(this.exceedsMaxVelocity(xVelocity, yVelocity)){
				this.scaleVelocity(xVelocity, yVelocity);
				return;
			}		
			else {
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
	 * 		 | (Math.sqrt(Math.pow(getyVelocity(),2)+Math.pow(getxVelocity(),2)) > getMaxVelocity());	
	 */
	@Raw
	public boolean exceedsMaxVelocity(double xVelocity, double yVelocity){
		return (Math.sqrt(Math.pow(yVelocity,2)+Math.pow(xVelocity,2)) > getMaxVelocity());
	}

	/**
	 * Return the maximum velocity an entity can have.
	 */
	@Basic @Immutable
	public static double getMaxVelocity(){
		return Entity.max_Velocity;
	}

	/**
	 * Return the minimum velocity an entity can have.
	 */
	@Basic
	public static double getMinVelocity(){
		return Entity.min_Velocity;
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
	 * 		| new.xVelocity = (xVelocity*getMaxVelocity())/this.getTotalVelocity();
	 *      | new.yVelocity = (yVelocity*getMaxVelocity())/this.getTotalVelocity();	    
	 *      
	 * @note This method is only called upon if the total velocity 
	 * 		 of the given parameters exceed the maximum velocity.
	 */
	public void scaleVelocity(double xVelocity, double yVelocity ){
		// The velocities are first changed to their illegal values.
		// So that getTotalVelocity can be computed
		// TODO total velocity with lambda expression
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;

		double scaledxVelocity = (xVelocity*getMaxVelocity())/this.getTotalVelocity();
		double scaledyVelocity = (yVelocity*getMaxVelocity())/this.getTotalVelocity();


		this.xVelocity = scaledxVelocity;
		this.yVelocity = scaledyVelocity;
	}


	// ---------------------------- RADIUS ----------------------------	
	/** 
	 * Sets the radius to the given Value, if it is valid.
	 * 
	 * @param radius
	 * 		  The new, given radius of the entity.
	 * 
	 * @post The radius of the entity is now equal to the given, valid radius.
	 * 		|new.getRadius() == radius	
	 * 
	 * @throws  IllegalRadiusException
	 * 		   The given radius is not a valid radius.
	 * 		   | ! isValidRadius(radius)
	 */
	@Raw
	protected void setRadius(double radius) throws IllegalRadiusException{
		if (!isValidRadius(radius)){
			throw new IllegalRadiusException(radius);
		}
		this.radius = radius;
	}

	/** 
	 * Checks whether the given radius has a valid value.
	 * 
	 * @param  radius
	 * 		   The radius of the entity.
	 * 
	 * @return True if the radius exceeds the minimal radius
	 * 		   false if the radius is less than the minimal_radius. 
	 * 		   Or if the radius is Infinity or not a number.
	 * 		   | radius >= getMin_Radius;
	 */
	@Raw
	// TODO getMinradius moet gelden voor alle entitiess
	public boolean isValidRadius(double radius){
		return ((!Double.isNaN(radius)) && (radius != Double.POSITIVE_INFINITY));
	}


	// TODO je kan ook deze functie wel gebruiken en enkel dit overschrijven in andere klassen.
	//	
	//	/**
	//	 * Return the minimum radius an entity can have.
	//	 * @return the minimum radius an entity can have.
	//	 */
	//	@Basic
	//	public abstract double getMinRadius();



	// ---------------- Mass -------------------

	/**
	 * Sets the mass of this entity to the given value
	 * @param mass
	 * 		  The mass to set this entity to.
	 * @post The new mass of this entity equals the given mass.
	 * 		 new.getMass() == mass
	 */		
	@Raw
	public void setMass(double mass){
		this.mass = mass;
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
	 *         to the positon of the entity.
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
	 * 		  The duration of the specific movement of the entity.
	 * @return true if the duration is a non-negative number and finite.
	 * 		   | return (duration >= 0 && !Double.isNaN(duration) && (duration != Double.POSITIVE_INFINITY))
	 */			
	@Raw
	public boolean isValidDuration(double duration){
		return ((duration >= 0) && (!Double.isNaN(duration)) && (duration != Double.POSITIVE_INFINITY));
	}


	// ---------------------  COLLISION and Relative Postioning ----------------------
	
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
			// TODO VECTOR OPERATIE: value klasse maken
			double centerDistance = Math.sqrt(Math.pow((this.getxPosition()-other.getxPosition()), 2.0)+
					Math.pow((this.getyPosition()-other.getyPosition()), 2.0));
			result = centerDistance - this.getRadius() - other.radius;
		}
		return result;
	}


	// TODO dit lijkt niet goed te werken: marge inwerken

	/** 
	 * Returns whether two objects (entities) overlap. 
	 * A certain boundary is set to account for rounding issues with the double-representation.
	 * 
	 * @param Other
	 * 		  The other of two enitities that might overlap.
	 * @return True only if they overlap significantly. 
	 * 		   We first calculate the distance between the two entities.
	 * 		   Then we check whether this distance is smaller than 0.99 times the sum of the radii of both.	
	 * 		 | @see implementation
	 * 		  
	 */
	public Boolean significantOverlap (Entity other){
		if (this.equals(other)){
			return true;
		}
		// TODO VECTOR OPERATIE: value klasse maken

		double centerDistance = Math.sqrt(Math.pow((this.getxPosition()-other.getxPosition()), 2.0)+
				Math.pow((this.getyPosition()-other.getyPosition()), 2.0));

		return (centerDistance < 0.99*(this.getRadius() + other.getRadius()));
	}

	/**
	 * Returns whether this entity is fully within another entity.
	 * That means the space that this entity occupies, is also completely
	 * occupied by the other entity.
	 * 
	 * @param other
	 * 		  The other entity that might surround this entity.
	 * 
	 * @return True if the sum of distance between the two centres and 
	 * 		   the radius of this entity is smaller than the radius of the other entity.
	 * 		   | @see implementation 
	 * 
	 */
	public Boolean isFullyWithinEntity (Entity other){
		double thisRadius = this.getRadius();
		double otherRadius = other.getRadius();

		//TODO vector operatie: value klasse
		double centerDistance = Math.sqrt(Math.pow((this.getxPosition()-other.getxPosition()), 2.0)+
				Math.pow((this.getyPosition()-other.getyPosition()), 2.0));

		return ((centerDistance + thisRadius) < otherRadius);

	}

	/**
	 * This method returns the time until an entity will reach a certain boundary of the world it is in.
	 * 
	 * 
	 * @return If this entity will never collide with a boundary, the method will return infinity.
	 * 		   Otherwise it will return the shortest time until the entity reaches a boundary.
	 * 		   It simply calculates both the times to reach either the vertical or horizontal boundary.
	 * 		   And then takes the smallest of both times. 
	 * 		   @see implementation 
	 *         
	 *         A use for this method: 
	 * 		   One can use the result to move the given entity by the resulted duration to place the
	 * 		   entity directly against the boundary. If one were to move the entity for a slightly longer duration; 
	 * 		   the entity would be out of the world's boundary.
	 * 		   |if (extra > 0){
	 * 		   | 	this.move(resulting_time + extra)
	 * 		   |	!this.getWorld().withinBoundaries(this)
	 * 	       If we had not increased resulting_time with extra, the entity would have still been within this world.
	 * 		  
	 */
	public double getTimeToBoundaryCollision(){
		if (this.getWorld() == null){
			return Double.POSITIVE_INFINITY;
		}

		double xTime = getTimeToBoundaryAxisCollsion(this.getxVelocity(), this.getxPosition(),
				this.getWorld().getWidth());
		double yTime = getTimeToBoundaryAxisCollsion(this.getyVelocity(), this.getyPosition(),
				this.getWorld().getHeight());

		return Math.min(xTime, yTime);		
	}


	//TODO DOCUMENTATION  

	public double[] getBoundaryCollisionPosition(){
		if (this.getWorld() == null){
			return null;
		}

		double T = this.getTimeToBoundaryCollision();

		if (T == Double.POSITIVE_INFINITY){
			return null;
		}

		//Where are the entities after time T?

		double[] entityPosition = { this.getxPosition() + this.getxVelocity() * T,
				this.getyPosition() + this.getyVelocity() * T };

		double xTime = getTimeToBoundaryAxisCollsion(this.getxVelocity(), this.getxPosition(),
				this.getWorld().getWidth());


		if(T == xTime){
			if(this.getxVelocity() >0){
				entityPosition[0]+= this.getRadius();
			} else {
				entityPosition[0]-= this.getRadius();

			}

		} else {
			if(this.getyVelocity() >0){
				entityPosition[1]+= this.getRadius();
			} else {
				entityPosition[1]-= this.getRadius();

			}

		}
		return entityPosition;
	}


	/**
	 * This method calculaties the time until this entity collides with a boundary.
	 * This either in the x- or y- direction. 
	 * There is a zero border (can be seen as the x- or y-axis) and a far boundary (paralel to the axis, at a certain length).
	 * 
	 * 
	 * @param axisVelocity
	 * 		  The velocity in the direction of a certain axis (x or y)
	 * @param axisPosition
	 * 		  The position as according to a certain axis (x or y)
	 * @param worldAxisLength
	 * 		  The length of this wordth (either the height or the width, depending on the given axis (x or y) )
	 * @return The time to the furthest boundary or to the zero boundary, depending on which is the smallest.
	 * 		   It returns the smallest of the two.
	 * 		  @see implementation
	 */
	public double getTimeToBoundaryAxisCollsion(double axisVelocity, double axisPosition, double worldAxisLength){

		double time = Double.POSITIVE_INFINITY;
		if(axisVelocity != 0){
			double timeToLongBorder = (worldAxisLength-axisPosition-this.getRadius())/axisVelocity;
			double timeToZeroBorder = -(axisPosition -this.getRadius())/axisVelocity;

			time = Math.max(timeToZeroBorder,timeToLongBorder);
		}	
		return time;
	}

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
		if (this.significantOverlap(other)){
			throw new IllegalCollisionException(this,other);
		}
		
		//Check to see whether the bullet with which this might collide is within
		// a ship, then it can not collide.
		if(other instanceof Bullet){
			if(((Bullet) other).getShip() != null){
				return Double.POSITIVE_INFINITY;
			}
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

		double[] FirstEntityPosition = { this.getxPosition() + this.getxVelocity() * T,
				this.getyPosition() + this.getyVelocity() * T };
		double[] SecondEntityPosition = { other.getxPosition() + other.getxVelocity() * T,
				other.getyPosition() + other.getyVelocity() * T };

		// The position of the first entity, incremented with it's radius
		// (in the right direction = direction to the center of the other
		// entity) results in the answer.

		double[] CenterDistance = { SecondEntityPosition[0] - FirstEntityPosition[0],
				SecondEntityPosition[1] - FirstEntityPosition[1] };
		double Norm = Math.sqrt(Math.pow(CenterDistance[0], 2.0) + Math.pow(CenterDistance[1], 2.0));
		double[] NormedCenterDistance = { (SecondEntityPosition[0] - FirstEntityPosition[0]) / Norm,
				(SecondEntityPosition[1] - FirstEntityPosition[1]) / Norm };
		double[] RadiusWithDirection = { this.getRadius() * NormedCenterDistance[0],
				this.getRadius() * NormedCenterDistance[1] };
		double[] CollisionCoordinates = { FirstEntityPosition[0] + RadiusWithDirection[0],
				FirstEntityPosition[1] + RadiusWithDirection[1] };

		return CollisionCoordinates;
	}	


	// TODO DOCUMENTATIE COLLISIONS

	public void handleBoundaryCollision(){
	
		// A horizontal collision occurs when the x-position is around the
		// distance of the radius of this ship. Or same thing but on the other side.
		boolean horizontally = 
				(this.getxPosition()< 1.01 * this.getRadius()) || 
				(this.getxPosition() > (this.getWorld().getWidth() - 1.01 * this.getRadius()));
		
		// A vertical collision occurs when the y-position is around the
		// distance of the radius of this ship. Or same thing but on the other side.		
		boolean vertically = 
				(this.getyPosition()< 1.01 * this.getRadius()) || 
				(this.getyPosition() > (this.getWorld().getHeight() - 1.01 * this.getRadius()));


		if(horizontally){
			this.setVelocity(-this.getxVelocity(), this.getyVelocity());
		} 

		if (vertically) {
			this.setVelocity(this.getxVelocity(), -this.getyVelocity());
		}

	}
	
	// TODO: hebben abstracte methoden documentatie?
	public abstract void handleOtherEntityCollision(Entity entityB) throws IllegalPositionException, IllegalBulletException;

	/**
	 * This method handles a casual collision between two entities.
	 * This means a collision in which two entities merely bounce off of eachother.
	 * 
	 * @param other
	 * 		  The other entity in this collision
	 *
	 * @post The collision is only resolved if it hasn't been resolved already.
	 * 		 | if (!this.getWorld().isCasualCollisionHandled())
	 *       |		then old. getxVelocity == new. getyVelocity;
	 *       ---
	 * @effect The collision is resolved by 
	 * 		   changing the entities' directions and velocity;
	 *         the mathematical way of doing this was provided to us.
	 *         | @see implementation
	 */
	public void handleCasualCollision(Entity other){

		// Only do this if the casual collision has not been handled already.
		if (!this.getWorld().isCasualCollisionHandled()){
			// This entity
			double thisEntityPositionX = this.getxPosition();
			double thisEntityPositionY = this.getyPosition();

			double thisEntityVelocityX = this.getxVelocity();
			double thisEntityVelocityY = this.getyVelocity();

			double thisEntityRadius = this.getRadius();
			double thisEntitymass = this.getMass();

			// The other entity
			double otherEntityPositionX = other.getxPosition();
			double otherEntityPositionY = other.getyPosition();

			double otherEntityVelocityX = other.getxVelocity();
			double otherEntityVelocityY = other.getyVelocity();

			double otherEntityRadius = other.getRadius();
			double otherEntitymass = other.getMass();
			
			// The computations
			double deltaPosX = otherEntityPositionX - thisEntityPositionX;
			double deltaPosY = otherEntityPositionY - thisEntityPositionY;
			double deltaVelX = otherEntityVelocityX - thisEntityVelocityX;
			double deltaVelY = otherEntityVelocityY - thisEntityVelocityY;

			double delta = deltaPosX * deltaVelX + deltaPosY * deltaVelY;
			double sumRadius = thisEntityRadius + otherEntityRadius;

			double jValue = 
					(2 * thisEntitymass * otherEntitymass * delta) / 
					(sumRadius * (thisEntitymass + otherEntitymass));

			double Jx = (jValue * deltaPosX) / sumRadius;
			double Jy = (jValue * deltaPosY) / sumRadius;

			double thisEntitynewXVel = thisEntityVelocityX + (Jx / thisEntitymass);
			double thisEntitynewYVel = thisEntityVelocityY + (Jy / thisEntitymass);	

			this.setVelocity(thisEntitynewXVel, thisEntitynewYVel);

			double otherEntitynewXVel = otherEntityVelocityX - (Jx / otherEntitymass);
			double otherEntitynewYVel = otherEntityVelocityY - (Jy / otherEntitymass);	

			other.setVelocity(otherEntitynewXVel,otherEntitynewYVel);
		}			
	}
	
	/**
	 * Method registering if this entity is deadly.
	 * Meaning that if it hits another entity, that other entity is terminated.
	 * The default is set to false and is changed within deadly entities.
	 */
	public Boolean isDeadly(){
		return false;
	}



	// -----------------------  VARIABLES (DEFAULTS & FINAL) --------

	/**
	 * Variable registering the speed of light; 300000 km/s.
	 */
	private static final double speedoflight = 300000.0;

	/**
	 * Variable registering the xPosition of this Entity.
	 */
	private double xPosition = 0.0;

	/**
	 * Variable registering the yPosition of this Entity.
	 */
	private double yPosition = 0.0;


	/**
	 * Variable registering the xVelocity of this Entity.
	 */
	private double xVelocity = min_Velocity;


	/**
	 * Variable registering the yVelocity of this Entity.
	 */
	private double yVelocity = min_Velocity;


	/**
	 * Variable registering the minimum allowed velocity.
	 */
	private static final double min_Velocity = 0;

	/**
	 * Variable registering the maximum allowed velocity.
	 */
	private static final double max_Velocity = speedoflight;

	/**
	 * Variable registering the radius of this Entity.
	 */
	private double radius;

	/**
	 * Variable registering the mass of this entity.
	 */
	private double mass;

	/**
	 * Variable registering the world to which this entity belongs.
	 * Initialised as null; the entity has no world per default.
	 */
	private World world = null;

	/**
	 * Variable registering the density of this entity.
	 */
	private double density;

}