package asteroids.model;

/** 
 * A class for signaling illegal bullets.
 * 
 * @version 2.0
 * @author Michiel
 */
public class IllegalBulletException extends Exception {

	/**
	 * Initialize this new bullet exception with given bullet.
	 * 
	 * @param 	bullet
	 * 			The bullet for this new illegal bullet exception.
	 * @post	The bullet of this new illegal bullet exception is the given bullet.
	 * 			| new.getBullet() == bullet
	 */
	public IllegalBulletException(Bullet bullet) {
		this.bullet = bullet;
	}
	
	/**
	 * Return the bullet registered for this illegal bullet exception.
	 */
	public Bullet getBullet() {
		return this.bullet;
	}

	/**
	 * A variable registering the bullet of this illegal bullet exception.
	 */
	private final Bullet bullet;
	
	/**
	 * Java API recommends defining a version number for classes that implement the interface Serializable.
	 */
	private static final long serialVersionUID = -3462628179530524936L;
}