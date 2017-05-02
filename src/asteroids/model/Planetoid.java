package asteroids.model;

import asteroids.model.exceptions.IllegalDurationException;
import asteroids.model.exceptions.IllegalPositionException;
import asteroids.model.exceptions.IllegalRadiusException;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * A class for dealing with planetoids, a specific kind of minor planets.
 * They have a certain position, radius, velocity and mass.
 * When a planetoid is terminated, it spawns 
 * 
 * @invar The mass of each planetoid must be valid.
 * 		  |this.getMass() > 0
 * 
 * @version 3.0
 * @author Michiel & James
 *
 */

public class Planetoid extends MinorPlanet {
	
	/**
	 * Initialize this new planetoid with given position,radius, speed.
	 * 
	 * 
	 * @param  xPosition
	 *         The position of this planetoid, according to the x-axis. 
	 *         Expressed in km.
	 *         
	 * @param  yPosition
	 *         The position of this planetoid,according to the y-axis.
	 *         Expressed in km.
	 *         
	 * @param  radius
	 * 		   The radius of this new planetoid.
	 * 		   Expressed in km.	
	 * 
	 * @param  xVelocity 
	 *         The velocity of this planetoid, in the x-direction.
	 *         Expressed in km/s.
	 *         
	 * @param  yVelocity 
	 * 		   The velocity of the planetoid, in the y-direction. 
	 * 		   Expressed in km/s.
	 *          
	 * @effect The given parameters are set as the properties of the new planetoid.
	 * 		   |setPosition(xPosition,yPosition);
	 *	       |setVelocity(xVelocity,yVelocity);
	 *	       |setRadius(radius);
	 *		   |setPlanetoidMass(new.getRadius());
	 *
	 */
	public Planetoid(double xPosition, double yPosition, double xVelocity, double yVelocity, double radius)
			throws IllegalPositionException, IllegalRadiusException {
		super(xPosition, yPosition, xVelocity, yVelocity, radius);
		setPlanetoidMass(this.getRadius());
		initialRadius = radius;
	}
	
	/**
 	 * This method sets the mass of this planetoid depending on it's size (=defined by radius)
 	 * 
 	 * @param radius
 	 * 		  The radius of this planetoid.
 	 * @post The new mass of this planetoid now equals the value calculated using the formula with the given radius.
 	 * 		 |new.mass == default_Density*(4/3)*Math.PI*(Math.pow(radius, 3));
 	 */
 	private void setPlanetoidMass(double radius){
 		this.mass = default_Density*(4/3)*Math.PI*(Math.pow(radius, 3));
 	}
	
 	
 	//TODO when a planetoid is bigger than 30 km and is terminated, it spawns two smaller asteroids.
 	
 	public void terminate(){
 		
 	}
 	
 	/**
 	 * Moves this planetoid during a certain duration, depending on their velocity.
 	 * 
 	 * @param duration
 	 * 		  The time during which this planetoid moves
 	 * @post The distanceTravelled is incremented by the amount of kilometers that the planetoid had moved.
 	 * 		 That amount is calculated using the duration and the velocity of this ship.
 	 * 		 | new.getDistanceTravelled() += duration*this.getTotalVelocity()
 	 */
 	public void move(double duration) throws IllegalPositionException, IllegalDurationException{
 		// TODO misschien hier al krimpen
 		super.move(duration);
 		// TODO ik denk dat dit een juiste manier is om dit te beschrijven.
 		this.distanceTravelled += duration*this.getTotalVelocity();		
 	}
 	
	/**
	 * Variable registering the default density of a planetoid.
	 */
	protected final static double default_Density = 0.917*(Math.pow(10.0, 12.0));
	
	
	/**
	 * Returns the distance this planetoid has travelled.
	 */
	@Basic
	public double getDistanceTravelled(){
		return this.distanceTravelled;
	}
	/**
	 * Returns the radius that this planetoid had upon creation.
	 */
	@Basic
	public double getInitialRadius(){
		return this.initialRadius;
	}
	
	/**
	 * Variable registering the distance this planetoid has travelled.
	 * Initialised to a value of zero.
	 */
	protected double distanceTravelled = 0;
	
	/**
     * Variable registering the radius that this planetoid had upon creation.
     */
    protected final double initialRadius;
	
}
