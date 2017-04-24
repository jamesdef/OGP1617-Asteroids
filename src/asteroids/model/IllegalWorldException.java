package asteroids.model;

/** 
 * A class for signaling illegal worlds.
 * 
 * @version 2.0
 * @author Michiel
 */
public class IllegalWorldException extends Exception{
	

	/**
	 * Initialize this new world exception with given world.
	 * 
	 * @param 	world
	 * 			The world for this new illegal world exception.
	 * @post	The world of this new illegal world exception is the given world.
	 * 			| new.getWorld() == world
	 */
	public IllegalWorldException(World world) {
		this.world = world;
	}
		
	/**
	 * Return the world of this illegal world exception.
	 */
	public World getWorld() {
		return this.world;
	}

	/**
	 * A variable registering the world of this illegal world exception.
	 */
	private final World world;
	
	
	/**
	 * Java API recommends defining a version number for classes that implement the interface Serializable.
	 */
	private static final long serialVersionUID = 7135318319883809324L;


}
