package asteroids.model;

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

	/**
	 * 
	 */
	public World(double width, double height, List<Ship> ships, List<Bullet> bullets) {

		setWidth(width);
		setHeight(height);
		for (Ship ship : ships){
			addAsShip(ship);
		}
		for (Bullet bullet : bullets){
			addAsBullet(bullet);
		}
	}
	
	

	private final Set<Ship> ships = 
			new HashSet<Ship>();
	
	/** DEFENSIVE PROGRAMMING
	 * 
	 * 
	 * 
	 * @param ship
	 */
	public void addAsShip(Ship ship){
		this.ships.add(ship);
	}
	
	private final Set<Bullet> bullets = 
			new HashSet<Bullet>();
	
	public void addAsBullet(Bullet bullet){
		this.bullets.add(bullet);
	}
	
	
	public Set<Ship> getAllShips(){
		return this.ships;
	}
	
	public Set<Bullet> getAllBullets(){
		return this.bullets;
	}
	
	public  Set<Entity> getAllEntities(){
		return this.ships.combine(this.bullets);
	}
	
	/*
	 * Default constructor, ceates an 'empty' world.
	 */
	public World() {
		this(width, height, null, null);
	}

	/**
	 * Variable registering the maxium possible width and heigth for all worlds.
	 * The default value for this is set to be the largest number achievable.
	 */
	private static double Upper_Bound = Double.MAX_VALUE;

	/**
	 * Variable registering the width of this world. The default value is set to
	 * be half of the maximum possible Value.
	 */
	private static double width = (1 / 2) * (Upper_Bound);

	/**
	 * Variable registering the width of this world. 
	 * The default value is set to be half of the maximum possible Value.
	 */
	private static double height = (1/2)*(Upper_Bound);
	
	
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
	
	
	
	/**  TOTAL PROGRAMMING
	 * Returns the entity (ship or bullet), if there is one, at the given Positon.
	 *	 
	 * @returns The entity (ship or bullet) that has it's centre at the given postion.
	 * 		  |
   	 * @returns Null if no entity has it's centre at the given position.
   	 * 		  |
   	 */
	public Entity getEntityatPosition(double xPosition, double yPosition){
	  return Entity;
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
	
	/** FOUT GEEN REKENING GEHOUDEN MET 0 grenzen.
	 * 
	 * Returns whether an object is within the boundaries of this world.
	 * 
	 * @param object
	 * 		  The object of which we want to check if it is within this world
	 * @return
	 * 		  True only if the distance between the boundaries of this world and 
	 * 		  the centre of the object is bigger than 99% of the objectsradius.
	 * 		  |@ see implementation
	 * 
	 *  Math.abs: In onze beschrijving van ship hebben we niet geëist
	 *  dat de position enkel positieve coordinaten kent. Maar: een schip met negatieve 
	 *  coordinaten ligt toch sowieso niet in de wereld?
	 */
	public Boolean withinWorldBoundaries(Entity object){
		return ((World.width - Math.abs(object.getxPosition()) >= 0.99*object.getRadius()) ||
				(World.height - Math.abs(object.getyPosition()) >= 0.99*object.getRadius()));
	}
	
	public void evolve(double Dt){
		double tC = getFastestTimetoCollision();
		if (tC < Dt){
			Dt = Dt - tC;
		}
		// tC heeft nu de waarde waarmee we stappen id tijd.
		// We verplaatsen nu alle schepen en bullets over deze time frame.
	}
	
	public double getFastestTimetoCollision(){
		double TimetoFastestCollision = 100.0;
		for (Ship ship1 : this.getAllShips());{
			for (Ship ship2 : this.getAllShips());{
				double time = ship1.getTimeToCollision(ship2);
				if (time < TimetoFastestCollision){
					TimetoFastestCollision = time;
				}
			}
		}
		return TimetoFastestCollision;
	}
}
