package asteroids.model;

// Mijn naam is Michiel en Git is mijn vriend 

/**
 * 
 * @author James Defauw & Michiel De Koninck
 *
 */

/**final width (boundary)
 * final height (boundary)
 * static MAX_VALUE (not final because this value may change in the future)
 * 
 * addBullet
 * addShip
 * rmBullet
 * rmShip
 * 
 * getAllBullets (HASHSET ?)
 * getAllShips (HASHSET ?)
 * getAllEntities (Combination of previous sets?)
 *s
 *
 *
 *The advancing of time happens within this class. 
 *  --> This class should contain a method 'evolve' which advances 
 *  			the state of the world a certain number of seconds Dt.
 *          ---> (NO SPECIFICATION REQUIRED FOR THIS METHOD). Implement defensively. (Described within assignment P4)
 */
	
public class World {
 /**
  * 
  */ public World(width,height,ships,bullets){
	  setWidth(with);
	  setHeigth(heigth);
	  setShips(ships);
	  setBullets(bullets);
  }
  
  
  /**
   * Variable registering the maxium possible width and heigth for all worlds.
   * The default value for this is set to be the largest number achievable.
   */
  private double Upper_Bound = Double.MAX_VALUE;
  
  
  /**
   * Variable registering the width of this world. 
   * The default value is set to be half of the maximum possible Value.
   */
  private double width = (1/2)*(Upper_Bound);
  
  /**
   * Variable registering the width of this world. 
   * The default value is set to be half of the maximum possible Value.
   */
  private double height = (1/2)*(Upper_Bound);
  
 
  
  
  /**
   * 
   */
  
  
}
