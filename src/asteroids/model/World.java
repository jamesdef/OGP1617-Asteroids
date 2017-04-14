package asteroids.model;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;

// Mijn naam is Michiel en Git is mijn vriend 

//git problemen opgelost

/**
 * 
 * @author James Defauw & Michiel De Koninck
 *
 */

/**
 * final width (boundary) final height (boundary) static MAX_VALUE (not final
 * because this value may change in the future)
 * 
 * De grootte van het coordinaten vlak dient als volgt te worden gedefinieerd:
 *  x element of [0, width] , y element of [0,height] 
 *  This means that coordinates can never be negative!
 * 
 * DEFENSIVELY addBullet addShip rmBullet rmShip
 * 
 * All ships must fully lie in this world and not overlap with other ships
 * 
 * getAllBullets (HASHSET ?) getAllShips (HASHSET ?) getAllEntities (Combination
 * of previous sets?) s
 *
 *
 * The advancing of time happens within this class. --> This class should
 * contain a method 'evolve' which advances the state of the world a certain
 * number of seconds Dt. ---> (NO SPECIFICATION REQUIRED FOR THIS METHOD).
 * Implement defensively. (Described within assignment P4)
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
		this.terminated = true;
	}

	/**
	 * Check whether this world is terminated.
	 * @return The state of this world; whether it is terminated or not.
	 */
	@Basic
	public boolean isTerminated(){
		return this.terminated;
	}

	/**
	 * Variable registering whether or not this world is terminated.
	 */
	private boolean terminated = false;


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

	
// -------------------- Registers; variables; maps; sets ---------------------------------
	/**
	 * A map containing the different entities in this world, allong with the position of their center.
	 */
	private final HashMap <double[], Entity> entities = new HashMap<double[],Entity>();
	
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
	 * A method returning the amount of entities within this world.
	 * 
	 * @return The number of entities within this world.
	 * 			|result == entities.size();
	 */
	public int getNumberofEntities(){
		return getAllEntities().size();
	}
	
	/**
	 * A variable containing the ships that are within this world.
	 */
	private final HashSet<Ship> ships = new HashSet<Ship>();
	
	/**
	 * A variable containing the bullets that are within this world.
	 */
	private final HashSet<Bullet> bullets = new HashSet<Bullet>();
	
	/**
	 * A method that returns the ships located in this world.
	 * @return The ships located in this world.
	 */
	public HashSet<Ship> getships(){
			return this.ships;
	}
	
	/**
	 * A method that returns the bullets located in this world.
	 * @return The bullets located in this worl.
	 */
	public HashSet<Bullet> getBullets(){
		return this.bullets;
	}
	
// ----------------- HEIGHT AND WIDTH------------------
	
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
	 * Sets the width of this world to a given value.
	 * 
	 * @param width
	 * 		  The given width
	 * 
	 * @post if the given width is greater than the upper bound
	 * 		 the width is set to be equal to the upper bound.
	 * 
	 * @post if the given width is negative, nothing is changed to the width.
	 * 		 | if (width < 0)
	 *       |     then new.getWidth() = this.getWidth()
	 * @post if the given width is positive and smaller than the upper bound:
	 *       the width is changed to the given value.
	 *       | new.getWidth() == width
	 *
	 */
	public void setWidth(double width){
		if (width > Upper_Bound) {
			World.width = Upper_Bound;
		}
		else if (width >0){
			World.width = width;
		}
	}
	
	/** TOTAL PROGRAMMING
	 * Sets the height of this world to a given value.
	 * 
	 * @param height
	 * 		  The given height
	 * 
	 * @post if the given height is greater than the upper bound
	 * 		 the height is set to be equal to the upper bound.
	 * 
	 * @post if the given height is negative, nothing is changed to the height.
	 * 		 | if (height < 0)
	 *       |     then new.getHeight() = this.getHeight()
	 * @post if the given height is positive and smaller than the upper bound:
	 *       the height is changed to the given value.
	 *       | new.getHeight() == height
	 */
	public void setHeight(double height){
		if (height > Upper_Bound) {
			World.height = Upper_Bound;
		}
		else if (height >0){
			World.height = height;
		}
	}
	
	
//--------------- ASSOCIATIONS WITH ENTITIES -------------------------
	
	/** DEFENSIVE PROGRAMMING
	 * 
	 * 
	 * 
	 * @param ship
	 */
	public void addAsShip(Ship ship){
		this.ships.add(ship);
	}
	

	
	public void addAsBullet(Bullet bullet){
		this.bullets.add(bullet);
	}
	
	
	public Set<Ship> getAllShips(){
		return this.ships;
	}
	
	public Set<Bullet> getAllBullets(){
		return this.bullets;
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
	 * 			-If the given entity overlaps with another entity, it can not belong to this world.
	 * 
	 */
	public Boolean canHaveAsEntity(Entity entity){
		if (entity.isTerminated() || this.isTerminated() || entity == null  || entity.getWorld() != null 
				|| !this.withinWorldBoundaries(entity) || (entity instanceof Bullet && ((Bullet)entity).getShip()!= null)){
			return false;
		}
		for (Entity other : this.getAllEntities()) {
			if (entity.overlap(other)){
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
	  double[] position = {xPosition, yPosition};
	  if (this.getEntities().containsKey(position)){
		  	return this.getEntities().get(position);
	  }
	  else{
		  return null;
	  }
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
	
	
	/**
	 * 
	 * @param Dt
	 * @throws IllegalCollisionException
	 */
	public void evolve(double Dt) throws IllegalCollisionException{
		double tC = getTimetoFirstCollision();
		if (tC < Dt){
			Dt = Dt - tC;
		}
		// tC heeft nu de waarde waarmee we stappen id tijd.
		// We verplaatsen nu alle schepen en bullets over deze time frame.
		
		We hebben zowel de tijd nodig tot de eerste entiteit-botsing; zowel als de eerste botsing met een muur.
		Immers, in die gevallen moeten we (als dat eerst gebeurt) exact dan iets aanpassen in de wereld.
		--> TODO: getTimeToBoundaryCollision() en getPositionBoundaryCollision()
	}
	

	
	/**
	 * This method returns the time to the next collision. 
	 * This collision can be one between two entities, or between an entity and a boundary.
	 * 
	 * @return The time to the first collision which will happen.
	 * 
	 * @throws IllegalCollisionException 
	 * 			--> Handled within getTimeToCollision
	 */
	public double getTimetoFirstCollision() throws IllegalCollisionException{
		// We need a list so we can use the concept of order in efficiently comparing entities.
		List<Entity> ArrayofEntities = new ArrayList<>(this.getAllEntities());
		// Initialise the time to something way bigger than what you expect to find.
		double TimetoFirstCollision = Double.POSITIVE_INFINITY;
		for (int i = 0; i < getAllEntities().size(); i++){
			for (int j = i +1; j < getAllEntities().size(); j++){
				double time = (ArrayofEntities.get(i)).getTimeToEntityCollision((ArrayofEntities.get(j)));
				if (time < TimetoFirstCollision){
					TimetoFirstCollision = time;
				}
			}
			//If the entity that is being handled in the primary for-loop, collides with the boundary of it's world, sooner than with another entity;
			//then this shorter time will be our result (for now). This is because if an entity bounces with a wall, 
			// we also need to change things to it's properties befor going further.
			double time = (ArrayofEntities.get(i)).getTimeToBoundaryCollision();
			if (time < TimetoFirstCollision){
				TimetoFirstCollision = time;
			}
		}
		return TimetoFirstCollision;
	}
	
	public boolean overlaps(Entity entity){
		
		
	}
	
}


