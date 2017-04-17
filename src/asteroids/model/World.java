package asteroids.model;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of worlds with a size and a collection of entities
 * @invar   Each world has a valid width as its width.
 *          | isValidWidth(this.getWidth())
 * @invar   Each world has a valid height as its height
 *          | isValidHeight(this.getHeight())
 * @invar   Each world must have proper entities.
 *          | hasProperEntities()
 * @invar  The entities is an effective collection.
 *          | entities != null
 * @author Pieter Claeys, Pieter Mangelschots (2Bcwselt1)
 * @version 1.0
 */

/**
 * A class that handles worlds which have a certain size and possibly a
 * collection of entities.
 * 
 * @invar   Every world must have a valid width.
 *          | isValidWidth(this.getWidth())
 * @invar   Every world must have a valid height.
 *          | isValidHeight(this.getHeight()) 
 *          
 * @invar   Every world must have proper entities.
 * 			|hasProperEntities();
 *          
 * @version 2.0         
 * @author James Defauw & Michiel De Koninck
 *
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
	
	/*
	 * Default constructor, ceates a world of maximum size.
	 * 
	 * @effect An empty world is created, with maximum size.
	 */
	public World() {
		this(Upper_Bound, Upper_Bound);
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
	 * Check whether this world is terminated.
	 * @return The state of this world; whether it is terminated or not.
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
	 * Return the entities located in this world at a certain positionas a map (using location as the key of each entity).
	 * @return a map holding the entities and their positions.
	 */ 
	public HashMap<double[],Entity> getEntities() {
		return this.entities;	
	}
	
	/**
	 * Returns the entities within this world.
	 * @return All the entities within this world.
	 */
	public Set<Entity> getAllEntities(){
		return new HashSet<>(this.entities.values());
	}
	
	/**
	 * Returns a set of all ships that belong to this world.
	 * @return a set of all ships that belong to this world.
	 */
	public Set<Ship> getAllShips(){
		Set<Ship> ships = new HashSet<>();
		for(Entity entityToCheck: this.getAllEntities()){
			if (entityToCheck instanceof Ship){
				ships.add((Ship)entityToCheck);
			}
		}
		return ships;
	}
	
	/**
	 * Returns a set of all bullets that belong to this world.
	 * @return a set of all bullets that belong to this world.
	 */
	public Set<Bullet> getAllBullets(){
		Set<Bullet> bullets = new HashSet<>();
		for(Entity entityToCheck: this.getAllEntities()){
			if (entityToCheck instanceof Bullet){
				bullets.add((Bullet)entityToCheck);
			}
		}
		return bullets;
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
	 * @return the width of this world.
	 */
	 @Basic
	 public double getWidth(){
		 return World.width;
	 }
		
	 /**
	 * Return the height of this world.
	 * @return the height of this world.
	 */
	 @Basic
	 public double getHeight(){
		return World.height;
	 }
	 
	 
	
	/** TOTAL PROGRAMMING
	 * Sets the width of this world to the absolute of the given value.
	 * 
	 * @param width
	 * 		  The given width
	 * 
	 * @post if the given width is greater than the upper bound
	 * 		 the width is set to be equal to the upper bound.
	 * 
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
		if (widthpositive  > Upper_Bound) {
			World.width = Upper_Bound;
		}
		else if (widthpositive  >0){
			World.width = widthpositive;
		}
	}
	
	/** TOTAL PROGRAMMING
	 * Sets the height of this world to the absolute of the given value.
	 * 
	 * @param height
	 * 		  The given height
	 * 
	 * @post if the given height is greater than the upper bound
	 * 		 the height is set to be equal to the upper bound.
	 * 
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
		if (heightpositive > Upper_Bound) {
			World.height = Upper_Bound;
		}
		else if (heightpositive >0){
			World.height = heightpositive;
		}
	}
	
	
//--------------- ASSOCIATIONS WITH ENTITIES -------------------------
	
	
	/**
	 * This method checks whether all the entities in this world 
	 * 		are in fact proper entities.
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
	 * @throws IllegalArgumentException()
	 * 		   If this world can not have the given entity as one of its entities 
	 * 			or the given entity already has a world, this exception is thrown.
	 * 		   |(!canHaveAsEntity(entity) || entity.getWorld()!=null)
	 */
	public void addEntity(Entity entity){
		if (!canHaveAsEntity(entity) || entity.getWorld()!=null)
				throw new IllegalArgumentException();
		// Add entity to the map: with its position as key.
		this.entities.put(entity.getPosition(), entity);
		System.out.println(this.entities.size());
		//This entity has the world as its world.
		entity.setWorld(this);
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
	 * @throws IllegalArgumentException 
	 * 		   If the given entity is not active or the given entity does not belong to this
	 * 		   world, this exception is thrown.
	 * 		
	 */
	public void removeEntity(Entity entity) throws IllegalArgumentException{
		if (entity == null || !hasEntity(entity)){
			throw new IllegalArgumentException();
		}

		this.entities.remove(entity.getPosition());
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
//TODO			-If the given entity overlaps with another entity, it can not belong to this world.
	 * 
	 */
	public Boolean canHaveAsEntity(Entity entity){
		if (entity.isTerminated() || this.isTerminated() || entity == null  || entity.getWorld() != null 
				|| !this.withinWorldBoundaries(entity) || (entity instanceof Bullet && ((Bullet)entity).getShip()!= null)){
			return false;
		}
		for (Entity ship : this.getAllShips()) {
			if (entity.overlap(ship)){
				return false;
			}
	    }
	    return true;
	}
	
	/** Returns within constant time because we use a map that has positions of entities as keys.
	 * Returns the entity (ship or bullet) or entities, if there is one, at the given Position.
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
		System.out.println("getEntityAt");
	  double[] position = {xPosition, yPosition};
	  
		  	return this.getEntities().get(position);
	}
	
	/** 
	 * Returns whether two objects (entities) overlap. 
	 * A certain boundary is set to account for rounding issues with the double-representation.
	 * 
	 * @param object1
	 * 		  One of two entities that might overlap.
	 * @param object2
	 * 		  The other of two enitities that might overlap.
	 * @return 
	 * 			True only if they overlap significantly. 
	 * 			A significant overlap is reached when the distance between the centres
	 * 			of the two entities is smaller than 99% of the sum of the objects' radii.
	 * 		 | @see implementation
	 * 		  
	 */
	public Boolean significantOverlap (Entity object1, Entity object2){
		double boundary = object1.getRadius() + object2.getRadius();
		return (object1.getDistanceBetween(object2) <= 0.99*boundary);
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
	 *  Math.abs: In onze beschrijving van ship hebben we niet ge�ist
	 *  dat de position enkel positieve coordinaten kent. Maar: een schip met negatieve 
	 *  coordinaten ligt toch sowieso niet in de wereld?
	 */
	public Boolean withinWorldBoundaries(Entity object){
		
		double x = object.getxPosition();
		double y = object.getyPosition();
		double width = this.getWidth();
		double height = this.getHeight();
		double radius = object.getRadius();
		
		return (
				((width - x) >= 0.99*radius) &&
				(x >= 0.99*radius) &&
				((height - y) >= 0.99*radius) &&
				(y >= 0.99*radius)
				);
	}
	
// ---------------------- EVOLVING AND COLLISION ----------------------
	
	public void moveAllentities( double Dt) throws IllegalPositionException, IllegalDurationException{

		for(Entity entity: this.getAllEntities()){
			entity.move(Dt);
			
			if(entity instanceof Ship){
				((Ship) entity).accelerate(Dt);
			}			
		}
	}
	
	
	/**
	 * 
	 * @param Dt
	 * @throws IllegalCollisionException
	 * @throws IllegalDurationException 
	 * @throws IllegalPositionException 
	 */
	
	

	public void evolve(double Dt) throws IllegalCollisionException, IllegalPositionException, IllegalDurationException{
		
		//NEXT ENTITY-BOUNDARY COLLISIONS INFO
		double tNextEntityBoundaryCollision = this.getTimeToNextEntityBoundaryCollision();
		Entity nextEntityBoundaryCollisionEntity = this.getNextEntityBoundaryCollisionEntity();
		
		
		//NEXT ENTITY-ENTITY COLLISSION INFO
		double tNextEntityEntityPosition = this.getTimeToNextEntityEntityCollision();
		HashSet<Entity> nextEntityEntityPositionEntities = this.getNextEntityEntityCollisionEntities();
		
		double tC = Math.min(tNextEntityBoundaryCollision, tNextEntityEntityPosition);

		if (tC <= Dt){
			this.moveAllentities(tC);

			if (tNextEntityBoundaryCollision<=tNextEntityEntityPosition) {
				
				//handle entity boundary collision
				this.handleEntityBoundaryCollision(nextEntityBoundaryCollisionEntity);
			}
			else {
				//handle entity entity collision
				List<Entity> ArrayofEntities = new ArrayList<>(nextEntityEntityPositionEntities);
				Entity entityA = ArrayofEntities.get(0);
				Entity entityB = ArrayofEntities.get(1);

				this.handleEntityEntityCollision(entityA, entityB);
			}
				this.evolve(Dt-tC);
			} else {
				this.moveAllentities(Dt);
			}
		
	}
	
	
	/**
	 * This method returns the time to the next collision between an entity and a boundary.
	 * 
	 * @return the time to the next collision between an entity and a boundary.
	 * 		   It does this by checking the time until each entity reaches a boundary;
	 * 		   and then taking the smallest of times.
	 * 		   | @see implementation
	 * @throws IllegalCollisionException 
	 * 			--> Handled within getTimeToCollision
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
	 * @throws IllegalCollisionException
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
	 * @return the two entities which will collide with eachother the soonest.
	 * 		   It does this by taking those 2 entities who have the smallest time until
	 * 		   they collide with eachother.
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
	
	// TODO : Added methode om tijd tot eerst volgende gebeurtenis te berekenen? Die was verdwenen? 
	// Is dit een goede implementatie dan?
	
	/**
	 * Returns the time until the next collision in this world.
	 * This can be the time until the first boundary collision or entity collision.
	 * 
	 * @return The time until the first collision happens. 
	 * 		   It does this by taking the smallest time of the 2 possible kinds of collisions.
	 * 		   | @see implementation
	 * @throws IllegalCollisionException
	 */
	public double getTimeToFirstCollision() throws IllegalCollisionException{
		return Math.min(this.getTimeToNextEntityBoundaryCollision(),this.getTimeToNextEntityEntityCollision());
	}
	
	
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
	

	
	
//------------------- COLLISION HANDLERS -------------------
	
	
	/**
	 * This method handles a collision between an entity and a boundary.
	 * @param entity
	 * 		  The entity to handle.
	 * @effect handles a collision between an entity and a boundary.
	 * 		   | @see implementation
	 */
	public void handleEntityBoundaryCollision(Entity entity){
		
		if(entity instanceof Bullet){
			Bullet bullet = (Bullet) entity;
			int nbBouncesLeft = bullet.getBouncesLeft();
			
			if(nbBouncesLeft == 0){
				entity.terminate();
			} else {
				bullet.decrementBouncesLeft();
			}
		}
		
		boolean horizontally = (entity.getxPosition()< 1.01 * entity.getRadius()) || (entity.getxPosition() > (entity.getWorld().getWidth() - 1.01 * entity.getRadius()));
		
		if(horizontally){
			entity.setVelocity(-entity.getxVelocity(), entity.getyVelocity());
		} else {
			entity.setVelocity(entity.getxVelocity(), -entity.getyVelocity());
		}
		
	}
	
	/**
	 * This method handles the collision between two different entities.
	 * 
	 * @param entityA
	 * 		  One of the 2 entities in this collision.
	 * @param entityB
	 * 		  One of the 2 entities in this collision.
	 * @effect handles the collision between two different entities.
	 * 		   Depening on whether it is a collision between ships or bullets or a bullet and a ship,
	 * 		   the collision is handled differently.
	 * 		   | @see implementation
	 */
	public void handleEntityEntityCollision(Entity entityA, Entity entityB){

	if(entityA instanceof Ship){
		if(entityB instanceof Ship) handleShipShipCollision((Ship) entityA, (Ship) entityB);
		if(entityB instanceof Bullet) handleBulletShipCollision((Bullet) entityB, (Ship) entityA);
	}
	else{
		if(entityB instanceof Ship) handleBulletShipCollision((Bullet) entityA, (Ship) entityB);
		if(entityB instanceof Bullet) handleBulletBulletCollision((Bullet) entityB, (Bullet) entityA);
	}
	
	}
	
	
	
	//SPECIFIC EE CASES
	
	/**
	 * This method handles the specific case of a collision between 2 ships.
	 * @param shipA
	 * 		  One of the ships in this collision.
	 * @param shipB
	 * 		  One of the ships in this collision.
	 * @effect The collision is resolved by changing the ships directions and velocity;
	 *         the mathematical way of doing this was provided to us.
	 *         | @see implementation
	 */
	public void handleShipShipCollision(Ship shipA, Ship shipB){
		
		
		double shipAPositionX = shipA.getxPosition();
		double shipAPositionY = shipA.getyPosition();
		
		double shipAVelocityX = shipA.getxVelocity();
		double shipAVelocityY = shipA.getyVelocity();
		
		double shipARadius = shipA.getRadius();
		double shipAmass = shipA.getMass();
		
		
		double shipBPositionX = shipB.getxPosition();
		double shipBPositionY = shipB.getyPosition();
		
		double shipBVelocityX = shipB.getxVelocity();
		double shipBVelocityY = shipB.getyVelocity();
		
		double shipBRadius = shipB.getRadius();
		double shipBmass = shipB.getMass();

		

		double deltaPosx = shipAPositionX - shipBPositionX;
		double deltaPosy = shipAPositionY - shipBPositionY;
		
		double deltaVelX = shipAVelocityX - shipBVelocityX;
		double deltaVelY = shipAVelocityY - shipBVelocityY;
		
		double delta = deltaPosx * deltaVelX + deltaVelY * deltaVelY;

		double sumRadius = shipARadius + shipBRadius;
		
		double BigJ = 
				(2 * shipAmass * shipBmass * delta) / 
				(sumRadius * (shipAmass + shipBmass));
		
		double Jx = (BigJ * deltaPosx) / sumRadius;
		double Jy = (BigJ * deltaPosy) / sumRadius;
		
		double shipAnewXVel = shipAVelocityX + Jx / shipAmass;
		double shipAnewYVel = shipAVelocityY + Jy / shipAmass;
		
		double shipBnewXVel = shipAVelocityX + Jx / shipAmass;
		double shipBnewYVel = shipAVelocityY + Jy / shipAmass;
				
		
		shipA.setVelocity(shipAnewXVel, shipAnewYVel);
		shipB.setVelocity(shipBnewXVel, shipBnewYVel);
	}
	
	
	/**
	 * Handles the collision between a bullet and a ship.
	 * 
	 * @param bullet
	 * 		  The bullet in this collision
	 * @param ship
	 * 		  The ship in this collision.
	 * @effect Both entities are terminated.
	 * 
	 */
	public void handleBulletShipCollision(Bullet bullet, Ship ship){
		if(bullet.getShip() == ship){
			//ship.ge
		} else {
			bullet.terminate();
			ship.terminate();
		}
	}
	
	/**
	 * Handles the collision between two bullets.
	 * 
	 * @param bulletA
	 * 		  One bullet in this collision
	 * @param bulletB
	 * 		  One bullet in this collision
	 * @effect Both bullets are terminated.
	 * 
	 */
	public void handleBulletBulletCollision(Bullet bulletA, Bullet bulletB){
		bulletA.terminate();
		bulletB.terminate();
	}
	
	
// ----------------------------------  VARIABLES --------
	
	/**
	 * A map containing the different entities in this world, allong with the position of their center.
	 */
	private final HashMap <double[], Entity> entities = new HashMap<double[],Entity>();
	
	/**
	 * Variable registering the maxium possible width and heigth for all worlds.
	 * The default value for this is set to be the largest number achievable.
	 */
	private static double Upper_Bound = Double.MAX_VALUE;

	/**
	 * Variable registering the width of this world. The default value is set to
	 * be half of the maximum possible Value.
	 */
	private static double width = (1/2)*Upper_Bound;

	/**
	 * Variable registering the height of this world. The default value is set to
	 * be half of the maximum possible Value.
	 */
	private static double height = (1/2)*Upper_Bound;
}


