package asteroids.model;

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
	public World(double width, double height, Ship ships, Bullet bullets) {

		setWidth(width);
		setHeight(height);
		setShips(ships);
		setBullets(bullets);
	}

	/*
	 * Default constructor, creates an 'empty' world.
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
	
	
	
	/**
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
  
}
