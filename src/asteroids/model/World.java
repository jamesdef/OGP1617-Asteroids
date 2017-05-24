package asteroids.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import asteroids.model.exceptions.IllegalBulletException;
import asteroids.model.exceptions.IllegalCollisionException;
import asteroids.model.exceptions.IllegalDurationException;
import asteroids.model.exceptions.IllegalEntityException;
import asteroids.model.exceptions.IllegalPositionException;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class that handles worlds which have a certain size and possibly a
 * collection of entities. These entities can collide with the boundaries of
 * this world or with each other. Such collisions will be handled as time 
 * goes on in this world. 
 * 
 * @invar   Every world must have a valid width.
 *          | isValidWidth(this.getWidth())
 * @invar   Every world must have a valid height.
 *          | isValidHeight(this.getHeight()) 
 *          
 * @invar   Every world must have proper entities.
 * 			|hasProperEntities();
 *          
 * @version 3.0     
 * @author James Defauw & Michiel De Koninck
 */


public class World {

// --------------------CONSTRUCTORS 2 --------------------------
	/**
	 * Create this new world as an empty world (no entities within it) that is not terminated.
	 * 
	 * @param width
	 * 		  The width of this new world.
	 * @param height 
	 * 		  The height of this new world.
	 * 
	 * @post The world is empty upon creation
	 * 			| new.getNumberOfEntities() == 0
	 */
	public World(double width, double height) {
		setWidth(width);
		setHeight(height);
	}
	
	/**
	 * Default constructor, creates a world of maximum size.
	 * 
	 * @effect An empty world is created, with maximum size.
	 * 	       | @see implementation
	 */
	public World() {
		this(World.getUpperBound(), World.getUpperBound());
	}
	

/// ----------------- TERMINATION --------------------------------
	
	/**
	 * Terminate this world
	 * 
	 * @post The world is terminated
	 * 		 |new.isTerminated()
	 */
	public void terminate(){
		for (Entity entity : this.getAllEntities())
			this.removeEntity(entity);
		this.isTerminated = true;
	}

	/**
	 * Returns whether this world is terminated (true) or not (false).
	 */
	@Basic
	public boolean isTerminated(){
		return this.isTerminated;
	}

	/**
	 * Variable registering whether or not this world is terminated.
	 */
	private boolean isTerminated = false;

	
// --------------------  Sets; Maps; Getters---------------------------------

	
	/**
	 * Return the entities located in this world at a certain position as
	 *  a map (using location as the key of each entity).
	 *  
	 * @return a map holding the entities and their positions.
	 */ 
	public HashMap<String,Entity> getEntities() {
		return this.entities;	
	}
	
	/**
	 * Returns the entities within this world.
	 * @return All the entities within this world.
	 */
	public Set<Entity> getAllEntities(){
		return new HashSet<>(this.entities.values());
	}
	
	// Je wilt een set terug geven die enkel die elementen bevat die van dat classetype zijn
	// dat je opvraagt.
	// Hiervoor maken we een (TODO stream) stream van alle entities in deze wereld.
	// We filteren daaruit (TODO met lambda) die entiteiten die van de opgegeven klasse zijn.
	// Tenslotte voegen we dezen allemaal samen in een set (die natuuurlijk enkel objecten
	// van de specifieke klasse bevat)
	
	// TODO: vragen: moet c erbij als parameter in de documentatie?
	/**
	 * Returns a set of specific entities of class c, that  belong to this world.
	 * 
	 * @param c  The type of the entities that we want to collect.
	 * 
	 * @return A collection of all the entities of the given type, that belong to this world.
	 * 		   |  @see implementation
	 */
	@SuppressWarnings("unchecked")
	public <T extends  Entity> Set<T> getSpecificEntities(Class<T> c){
		// This cast is unchecked, but this causes no problems because we KNOW it is right.
		return (Set<T>)this.getAllEntities().stream().filter(c::isInstance).collect(Collectors.toSet());
	}
	
	/**
	 * A method returning the amount of entities within this world.
	 * 
	 * @return The number of entities within this world.
	 * 			|result == entities.size();
	 */
	public int getNumberofEntities(){
		return getAllEntities().size();
	}
	

	
// ----------------- HEIGHT AND WIDTH------------------
	
	/**
	 * Return the width of this world.
	 */
	 @Basic
	 public double getWidth(){
		 return this.width;
	 }
		
	 /**
	 * Return the height of this world.
	 */
	 @Basic
	 public double getHeight(){
		return this.height;
	 }
	 
	 
	
	/** 
	 * Sets the width of this world to the absolute of the given value.
	 * 
	 * @param width
	 * 		  The given width
	 * 
	 * @post if the given width is greater than the upper bound
	 * 		 the width is set to be equal to the upper bound.
	 * 	     |if (widthpositive  > World.getUpperBound())
	 *		 |		then this.width = World.getUpperBound();
	 * @post if the given width is negative, it's opposite value is set.
	 * 	     Via the absolute
	 * 		|widthpositive = Math.abs(width);
	 * @post if the given width is positive and smaller than the upper bound:
	 *       the width is changed to the given value.
	 *       | new.getWidth() == width
	 *
	 */
	public void setWidth(double width){
		double widthpositive = Math.abs(width);
		if (widthpositive  > World.getUpperBound()) {
			this.width = World.getUpperBound();
		}
		else if (widthpositive  >=0){
			this.width = widthpositive;
		}
	}
	
	/**
	 * Sets the height of this world to the absolute of the given value.
	 * 
	 * @param height
	 * 		  The given height
	 * 
	 * @post if the given height is greater than the upper bound
	 * 		 the height is set to be equal to the upper bound.
	 * 		|if (heightpositive > World.getUpperBound()) 
			|			then this.height = World.getUpperBound();
	 * @post if the given height is negative, it's opposite value is set. 
	 * 		 Via it's absolute.
	 * 		 | heightpositive = Math.abs(height);
	 * 
	 * @post if the given height is positive and smaller than the upper bound:
	 *       the height is changed to the given value.
	 *       | new.getHeight() == height
	 */
	public void setHeight(double height){
		double heightpositive = Math.abs(height);
		if (heightpositive > World.getUpperBound()) {
			this.height = World.getUpperBound();
		}
		else if (heightpositive >=0){
			this.height = heightpositive;
		}
	}
	
	/**
	 * Method that sets the upperbound for the width 
	 * and height of this world to the given value.
	 * 
	 * @param upperbound
	 * 		  The new value for the upper bound
	 * @post  If the given value is bigger than zero
	 * 		  and smaller than the current upper bound;
	 * 		  it is set as the new upper bound.
	 * 		  |if (0< upperbound && upperbound < this.getUpperBound()){
			  |			then new.upper_bound = upperbound;
	 */
	public void setUpperBound(double upperbound){
		if (0< upperbound && upperbound < World.getUpperBound()){
			World.upper_bound = upperbound;
		}
	}
	
	public static double getUpperBound(){
		return World.upper_bound;
	}
	
//--------------- ASSOCIATIONS WITH ENTITIES -------------------------
	
	
	/**
	 * This method checks whether all the entities in this world 
	 * 		are in fact proper entities.
	 * 
	 * @return True if this world can have all of the entities that are within it
	 * 		   as its entities. Also, every entity within this world should reference this world.
	 * 		   | @see implementation
	 */
	@Raw
	public boolean hasProperEntities(){
		for (Entity entity : this.getAllEntities()) {
			if (!canHaveAsEntity(entity))
				return false;
			if (entity.getWorld() != this)
				return false;
		}
		return true;
	}
	
	/**
	 * This method adds a certain entity to this world.
	 * @param entity
	 * 		  The entity to add to this world.
	 * 
	 * @post This world has the given entity as one of its entities
	 * 		 and this entity has this world as its world.
	 * 		  |  new.hasAsEntity(entity) == true
	 * 		  |	 entity.getWorld() == this
	 * 
	 * @throws IllegalEntityException
	 * 		   If the entity is null, an exception is thrown.
	 * 		   If this world can not have the given entity as one of its entities 
	 * 			or the given entity already has a world, this exception is thrown.
	 * 		   | entity == null || (!canHaveAsEntity(entity) || entity.getWorld()!=null)
	 */
	public void addEntity(Entity entity) throws IllegalEntityException{

		if (entity == null || !canHaveAsEntity(entity) || entity.getWorld()!=null){
				throw new IllegalEntityException(entity);
		}

		this.entities.put((StringMaker(entity.getPosition())), entity);
		//This entity has the world as its world.
	
		entity.setWorld(this);
		
	}
	
	/** HELPER FUNCTION 
	 * This method makes an array (with 2 elements) into a string.
	 *  Example:  {x,y} => (x,y)
	 * 
	 * @param	array
	 * 			The array to change into a string.			
	 * @return	The array, converted to a string: form (x,y)
	 */
	public String StringMaker(double[] array) {
		return (array[0] + "," + array[1]);
	}
	
	/**
	 * The given entity is removed from the set of entities that this world holds.
	 * 
	 * @param  entity
	 *         The entity which will be removed.
	 * @post   The world no longer has the entity as one of its entities
	 *         | !new.hasAsEntity(entity)
	 *       
	 * @post	If this world has the given entity as one of its entities,
	 * 			the given entity is no longer attached to any world.
	 * 			|if (hasAsEntity(entity){
	 * 			|	((new entity).getWorld == null)}
	 * @throws IllegalEntityException 
	 * 		   If the given entity is not active or the given entity does not belong to this
	 * 		   world, this exception is thrown.
	 * 		
	 */
	public void removeEntity(Entity entity) throws IllegalEntityException{
		if (entity == null || !hasEntity(entity)){
			throw new IllegalEntityException(entity);
		}

		this.entities.values().remove(entity);

		entity.setWorld(null);
	}
	
	/**
	 * Returns whether or not the given entity is within this world.
	 * 
	 * @param entity
	 * 		  The entity of which we want to know whether it is within this world.
	 * @return True if the given entity is within this world.
	 * 		   False if the given entity is not within this world.
	 */
	@Basic
	public Boolean hasEntity(Entity entity) {
		return getEntities().containsValue(entity);
	}
	
	/**
	 * Check whether this world can have the given entity as one of its entities.
	 * 
	 * @param entity
	 * 		  The entity to investigate
	 * 
	 * @return True if and only if:
	 * 			- The world and the entity are not terminated
	 * 			- The entity is effective
	 * 			- The entity has a world ascribed to it
	 * 			- The entity is within the worlds boundaries 
	 * 			- In the case that the entity is a bullet, it must not have a ship ascribed to it. 
	 * 					(otherwise the bullet should belong to the ship, not the world)
	 * 			- The entity does not overlap with any other entity, that is already within this world.
	 *			| @see implementation
	 */
	
	public Boolean canHaveAsEntity(Entity entity){

		if (entity.isTerminated() || this.isTerminated() || entity == null  || (entity.getWorld() != null && this != entity.getWorld())
				|| !this.withinWorldBoundaries(entity) || (entity instanceof Bullet && ((Bullet)entity).getShip()!= null)){
			return false;
		}
		
		for (Entity Object : this.getAllEntities()) {
			if (entity.significantOverlap(Object)){
				return false ;
			}
		}
	    return true;
	}
	
	/** Returns within constant time because we use a map that has positions (as strings) of entities as keys.
	 * Returns the entity (ship or bullet) or entities, if there is one, at the given Position.
	 * Positions are made into strings so that the keys in the map are exactly equal to
	 * the given position (which is also made into a string)	 
	 *
	 *@param The xPosition at which we want to find an entity.
	 *
	 *@param The yPosition at which we want to find an entity.
	 *
	 * @returns The entity (ship or bullet) that has it's centre at the given postion.
	 * 		  | if ((this.getEntities().containsKey(position))
	 * 		  |		result.getxPosition == xPosition;
	 * 		  |		result.getyPosition == yPosition;
	 * 
   	 * @returns Null if no entity has it's centre at the given position.
   	 * 		  | if (!(this.getEntities().containsKey(position))
   	 * 		  |   	result == null
   	 */
	public Entity getEntityAt(double xPosition, double yPosition){
		
	String KeyPosition = (xPosition + "," + yPosition);
	 
	if (this.entities.containsKey(KeyPosition)){
		return this.getEntities().get(KeyPosition);
	}
	else{
		return null;
	}
	}

	
	
	/** 
	 * Returns whether an object is within the boundaries of this world.
	 * 
	 * @param object
	 * 		  The object of which we want to check if it is within this world
	 * @return
	 * 		  True only if the distance between the boundaries of this world and 
	 * 		  the centre of the object is bigger than 99% of the objectsradius.
	 * 		  |@ see implementation
	 * 
	 */
	public Boolean withinWorldBoundaries(Entity object){
	
		double x = object.getXPosition();
		double y = object.getYPosition();
		double width = this.getWidth();
		double height = this.getHeight();
		double radius = object.getRadius();
		
			return (
				((width - x) >= roundingFactor*radius) &&
				(x >= roundingFactor*radius) &&
				((height - y) >= roundingFactor*radius) &&
				(y >= roundingFactor*radius)
				);
	}
	
// ---------------------- EVOLVING AND MOVING----------------------
	
	/**
	 * This method moves all entities during a given duration.
	 * 
	 * @param Dt
	 * 		  The given duration
	 * 
	 * @post  Each entity is moved and so it's key in getEntities()
	 * 		  must change to the new location of that entity.
	 * 		|for(Entity entity: this.getAllEntities())
	 * 		  | entity == new.entities.get(new.getPosition)
	 * @effect All entities within this world are moved.
	 * 		   A ship can be accelerated if its thruster is on.
	 * 		  | @see implementation
	 */
	public void moveAllEntities( double Dt) throws IllegalPositionException, IllegalDurationException{
		
		for(Entity entity: this.getAllEntities()){
			// The objects with value 'entity' 
			// are first removed from the map of entities, 
			// before the entities are moved.
			this.getEntities().values().remove(entity);
			entity.move(Dt);
			// We removed the object from the entity map, 
			// and so now we add it again with its updated position.
			this.entities.put((StringMaker(entity.getPosition())), entity);
			if(entity instanceof Ship){
				((Ship) entity).accelerate(Dt);
			}			
		}
	}
	
	
	/**
	 * The state of a world can change as time passes.
	 * For example: a position of an entity flying through space changes.
	 * Or a ship can seize exististing after it hits a bullet.
	 * This method evolves the world to a next state, a given duration later.
	 * 
	 * If something important will happen within this world before the given duration,
	 * this event will first be handled. If after that, another event happens before the remaining
	 * duration is completed, that event is handled also.
	 * 
	 * @param Dt
	 * 		  The duration with which we will evolve.
	 * 
	 * @throws IllegalDurationExcepton 
	 * 		   The given duration is not a valid duration.
	 * 		   | Dt < 0
	 */
	public void evolve(double Dt) throws IllegalCollisionException, IllegalPositionException, IllegalDurationException, IllegalBulletException{
				
		if (! isValidDuration(Dt)){
			throw new IllegalDurationException(Dt);
		}

//		ENTITY-BOUNDARY INFO
		// The time until the first boundary collision is calculated.
		double tNextEntityBoundaryCollision = this.getTimeToNextEntityBoundaryCollision();
		Entity nextEntityBoundaryCollisionEntity = this.getNextEntityBoundaryCollisionEntity();

//      ENTITY - ENTITY INFO
		// The time until the first collision between two entities is calculated.
		double tNextEntityEntityCollision = this.getTimeToNextEntityEntityCollision();
		HashSet<Entity> nextEntityEntityCollisionEntities = this.getNextEntityEntityCollisionEntities();
		
		double tC = Math.min(tNextEntityBoundaryCollision, tNextEntityEntityCollision);

		if (tC <= Dt){
//			 Time to first collision is smaller than given evolve time Dt.
			if (! isValidDuration(tC)){
				throw new IllegalDurationException(tC);
			}
			else
				// All entities are moved until the time of collision
				this.moveAllEntities(tC);

			//-------------- COLLISIONS ARE HANDLED, entities are at point of collision.

			if (tNextEntityBoundaryCollision<=tNextEntityEntityCollision) {
				//handle entity boundary collision
				nextEntityBoundaryCollisionEntity.handleBoundaryCollision();
			}


			else {
				//handle entity entity collision
				List<Entity> ArrayofEntities = new ArrayList<>(nextEntityEntityCollisionEntities);
				Entity entityA = ArrayofEntities.get(0);
				Entity entityB = ArrayofEntities.get(1);

				// TWO-SIDED solving of collision.
				entityA.handleOtherEntityCollision(entityB);
				//Flag for collision handled is raised in case
				// this is a casual collision.
				setCasualCollisionHandled(true);
				entityB.handleOtherEntityCollision(entityA);
				// Flag is now put to false again:
				setCasualCollisionHandled(false);
			}

			// If tC was valid, it is now substracted from Dt.
			// This to calculate the remaining time until evolve is complete.		
			if (isValidDuration(tC))
				Dt = Dt - tC;
			// Recursive: a new Dt has been computed and we now 
			// call upon evolve with the smaller value for Dt.
			this.evolve(Dt);
		}
		
		else{ 
			// ! tC < Dt
			// Dt is smaller than the time until the first collision.
			this.moveAllEntities(Dt);
		}
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
		
	
// ------------------- COLLISIONS ----------------------

	/**
	 * Method returning whether the casual collision is already handled.
	 */
	@Basic
	public boolean isCasualCollisionHandled(){
		return casualCollisionHandled;
	}
	
	/**
	 * Method that sets whether the casual collision has already been handled.
	 */
	@Basic
	public void setCasualCollisionHandled(Boolean given){
		casualCollisionHandled = given;
	}
	
	
	/**
	 * This method returns the time to the next collision between an entity and a boundary.
	 * 
	 * @return the time to the next collision between an entity and a boundary.
	 * 		   It does this by checking the time until each entity reaches a boundary;
	 * 		   and then taking the smallest of times.
	 * 		   | @see implementation
	 */
	public double getTimeToNextEntityBoundaryCollision(){
		List<Entity> ArrayofEntities = new ArrayList<>(this.getAllEntities());
		double boundaryCollisionTime = Double.POSITIVE_INFINITY;
		for (int i = 0; i < getAllEntities().size(); i++){
			double time = (ArrayofEntities.get(i)).getTimeToBoundaryCollision();
			if (time < boundaryCollisionTime){
				boundaryCollisionTime = time;
			}
		}
		return boundaryCollisionTime;	
	}
	
	/**
	 * This method returns the entity which will collide with a boundary the fastest.
	 * 
	 * @return the entity which will collide with a boundary the fastest.
	 * 		   It runs through the list of all entities and looks up which has the
	 * 		   smallest time until the next boundary collision and returns that entity.
	 * 		   | @see implementation.
	 */
	public Entity getNextEntityBoundaryCollisionEntity(){
		List<Entity> ArrayofEntities = new ArrayList<>(this.getAllEntities());
		double boundaryCollisionTime = Double.POSITIVE_INFINITY;
		Entity entity = null;
		for (int i = 0; i < getAllEntities().size(); i++){
			double time = (ArrayofEntities.get(i)).getTimeToBoundaryCollision();
			if (time < boundaryCollisionTime){
				boundaryCollisionTime = time;
				entity = ArrayofEntities.get(i);
			}
		}
		return entity;	
	}
	
	/**
	 * This method returns the time until the next collision between two entities.
	 * 
	 * @return the time until the next collision between two entities.
	 * 		   It does this by checking for each pair of entities, and taking the smallest time.
	 * 		   | @see implementation
	 */
	public double getTimeToNextEntityEntityCollision() throws IllegalCollisionException {
		List<Entity> ArrayofEntities = new ArrayList<>(this.getAllEntities());
		double TimetoFirstEntityEntityCollision = Double.POSITIVE_INFINITY;
		for (int i = 0; i < getAllEntities().size(); i++){
			for (int j = i +1; j < getAllEntities().size(); j++){
				
				double time = (ArrayofEntities.get(i)).getTimeToEntityCollision((ArrayofEntities.get(j)));
				if (time < TimetoFirstEntityEntityCollision){
					TimetoFirstEntityEntityCollision = time;
				}
			}
		}
		return TimetoFirstEntityEntityCollision;
		
	}

	/**
	 * This method gets the two entities which will collide with eachother the soonest.
	 * 
	 * @return the two entities which will collide with each other the soonest.
	 * 		   It does this by taking those 2 entities who have the smallest time until
	 * 		   they collide with each other.
	 * 		   | @see implementation
	 * @throws IllegalCollisionException
	 */
	public HashSet<Entity> getNextEntityEntityCollisionEntities() throws IllegalCollisionException{
		List<Entity> ArrayofEntities = new ArrayList<>(this.getAllEntities());
		
		double TimetoFirstEntityEntityCollision = Double.POSITIVE_INFINITY;

		Entity entityA = null;
		Entity entityB = null;
		
		// Initialise the time to something way bigger than what you expect to find.
		for (int i = 0; i < getAllEntities().size(); i++){
			for (int j = i +1; j < getAllEntities().size(); j++){
				double time = (ArrayofEntities.get(i)).getTimeToEntityCollision((ArrayofEntities.get(j)));
				if (time < TimetoFirstEntityEntityCollision){
					TimetoFirstEntityEntityCollision = time;
					entityA = ArrayofEntities.get(i);
					entityB = ArrayofEntities.get(j);
				}
			}
		}
		
		HashSet<Entity> entitiesSet = new HashSet<Entity>();
		entitiesSet.add(entityA);
		entitiesSet.add(entityB);
		
		return entitiesSet;
	}
	
	/**
	 * Returns the time until the next collision in this world.
	 * This can be the time until the first boundary collision or entity collision.
	 * 
	 * @return The time until the first collision happens. 
	 * 		   It does this by taking the smallest time of the 2 possible kinds of collisions.
	 * 		   | @see implementation
	 */
	public double getTimeToFirstCollision() throws IllegalCollisionException{
		return Math.min(this.getTimeToNextEntityBoundaryCollision(),this.getTimeToNextEntityEntityCollision());
	}
	
	
	/**
	 * Returns the position where the next collision will take place.
	 * 
	 * @return the position where the next collision will take place.
	 * 		   | @see implementation
	 */
	public double[] getFirstCollisionPosition() throws IllegalCollisionException{
		double timeToNextCollsion = this.getTimeToFirstCollision();
		double timeToNextEntityBoundaryCollision = this.getTimeToNextEntityBoundaryCollision();
		if(timeToNextCollsion == timeToNextEntityBoundaryCollision){
			return this.getNextEntityBoundaryCollisionEntity().getBoundaryCollisionPosition();
			
		} else {
			List<Entity> ArrayofEntities = new ArrayList<>(this.getNextEntityEntityCollisionEntities());
			
			Entity entityA = ArrayofEntities.get(0);
			Entity entityB = ArrayofEntities.get(1);
			
			return entityA.getEntityCollisionPosition(entityB);	
		}
	}
	
	
// ----------------------------------  VARIABLES --------
	
	/**
	 * Variable registering the value for a roundingfactor.
	 */
	private final double roundingFactor = 0.99;
	
	/**
	 * A map containing the different entities (values) in this world,
	 * along with the position of their center (the key).
	 * The key will be a string so that we can seek through the map without having problems
	 * by creating new objects.
	 */
	private HashMap<String, Entity> entities = new HashMap<String,Entity>();
	
	/**
	 * Variable registering the maximum possible width and height for all worlds.
	 * The default value for this is set to be the largest number achievable.
	 */
	private static double upper_bound = Double.MAX_VALUE;

	/**
	 * Variable registering the width of this world. 
	 */
	private double width;

	/**
	 * Variable registering the height of this world.
	 */
	private double height;
	
	/*
	 * Variable registering whether this casual collision is handled.
	 * Initialized as false.
	 */
	private boolean casualCollisionHandled = false;
}