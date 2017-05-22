package asteroids.model;

import asteroids.model.exceptions.IllegalPositionException;
import asteroids.model.exceptions.IllegalRadiusException;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class for dealing with asteroids, a specific kind of minor planets.
 * They have a certain position, radius, velocity and mass.
 * Contrary to planetoids, the radius of an asteroid does not change.
 * 
 * @invar The mass of each asteroid must be valid.
 * 		  |this.getMass() > 0
 * 
 * @version 3.0
 * @author Michiel & James
 *
 */
public class Asteroid extends MinorPlanet{
	
	
	/**
	 * Initialize this new asteroid with given position,radius, speed.
	 * 
	 * 
	 * @param  xPosition
	 *         The position of this asteroid, according to the x-axis. 
	 *         Expressed in km.
	 *         
	 * @param  yPosition
	 *         The position of this asteroid,according to the y-axis.
	 *         Expressed in km.
	 *         
	 * @param  radius
	 * 		   The radius of this new asteroid.
	 * 		   Expressed in km.	
	 * 
	 * @param  xVelocity 
	 *         The velocity of this asteroid, in the x-direction.
	 *         Expressed in km/s.
	 *         
	 * @param  yVelocity 
	 * 		   The velocity of the asteroid, in the y-direction. 
	 * 		   Expressed in km/s.
	 *          
	 * @effect The given parameters are set as the properties of the new asteroid.
	 * 		   |setPosition(xPosition,yPosition);
	 *	       |setVelocity(xVelocity,yVelocity);
	 *	       |setRadius(radius);
	 *		   |setAsteroidMass(new.getRadius());
	 *
	 */
	@Raw
	public Asteroid(double xPosition, double yPosition, double xVelocity, double yVelocity, double radius)
			throws IllegalPositionException, IllegalRadiusException {
		super(xPosition, yPosition, xVelocity, yVelocity, radius);
		setAsteroidMass(this.getRadius());
	}
	
	/**
 	 * This method sets the mass of this asteroid depending on it's size (=defined by radius)
 	 * 
 	 * @param radius
 	 * 		  The radius of this asteroid.
 	 * @post The new mass of this asteroid now equals the value calculated using the formula with the given radius.
 	 * 		 |new.mass == default_Density*(4/3)*Math.PI*(Math.pow(radius, 3));
 	 */
 	private void setAsteroidMass(double radius){
 		this.setMass(default_Density*(4.0/3.0)*Math.PI*(Math.pow(radius, 3)));
 	}
	
 	/**
 	 * This handles the collision between an asteroid 
 	 * and a certain entity.
 	 * 
 	 * @param entity
 	 * 		  The other entity in this collision
 	 * 
 	 * @effect If the other entity is a minor planet
 	 * 		 the collision is handled as a casual collision.
 	 * 		 | @see implementation
 	 * 
 	 * @effect If the other entity is a bullet,
 	 * 		 the asteroid is terminated.
 	 * 		 | if(entity instanceof Bullet){
	 *		 |        this.terminate();
 	 */
	@Override
	public void handleOtherEntityCollision(Entity entity){
		if(entity instanceof MinorPlanet){
			this.handleCasualCollision(entity);
		}
		if(entity instanceof Bullet){
			//this dies
			this.terminate();
		}
	}
	
	/**
	 * Boolean registering if this entity is deadly.
	 */
	@Override
	public Boolean isDeadly(){
		return true;
	}
	
	
// --------------- INITIALISATION ----------------
	
	/**
	 * Variable registering the default density of an asteroid.
	 */
	private static double default_Density = 2.65*(Math.pow(10.0, 12.0));
	
	
}

