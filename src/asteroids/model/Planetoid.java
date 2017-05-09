package asteroids.model;

import asteroids.model.exceptions.IllegalDurationException;
import asteroids.model.exceptions.IllegalEntityException;
import asteroids.model.exceptions.IllegalPositionException;
import asteroids.model.exceptions.IllegalRadiusException;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class for dealing with planetoids, a specific kind of minor planets.
 * They have a certain position, radius, velocity and mass.
 * When a planetoid is terminated, it spawns two new asteroid children.
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
	 * @param totalDistanceTraveled
	 * 		  The distance that this planetoid has traveled upon initialisation.
	 *          
	 * @effect The given parameters are set as the properties of the new planetoid.
	 * 		   |setPosition(xPosition,yPosition);
	 *	       |setVelocity(xVelocity,yVelocity);
	 *	       |setRadius(radius);
	 *		   |setPlanetoidMass(new.getRadius());
	 *@post The initial radius is set to the value with which this planetoid is initialised.
	 *		new.initialRadius = radius
	 *
	 *@post The distance traveled is now equal to the given value.
	 *		|new.distanceTraveled = totalDistanceTraveled
	 */
	@Raw
	public Planetoid(double xPosition, double yPosition, double xVelocity, double yVelocity, double radius, double totalDistanceTraveled)
			throws IllegalPositionException, IllegalRadiusException {
		super(xPosition, yPosition, xVelocity, yVelocity, radius);
		this.setPlanetoidMass(this.getRadius());
		this.initialRadius = this.getRadius();
		this.distanceTraveled = totalDistanceTraveled;
		//Perhaps the totalDistanceTraveled is not equal to zero
		//and so the radius has to be shrunk right away.
		this.shrink(distanceTraveled);	
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
 		this.setMass(default_Density*(4/3)*Math.PI*(Math.pow(radius, 3)));
 	}
	
 	
 	//TODO when a planetoid is bigger than 30 km and is terminated, it spawns two smaller asteroids.
 	/**
 	 * This planetoid is terminated. 
 	 * If this planetoid is in a world and 
 	 * it has a radius bigger than 30 km's, it spawns two new asteroids.
 	 * 
 	 * @post This planetoid is terminated.
 	 * 	     | new.isTerminated == true
 	 * 
 	 * @post If this planetoid has a radius larger than 30 and is within a world:
 	 * 		 it spawns two new asteroids upon termination. 
 	 * 		 These 'children' are placed so that their centres are on one line 
 	 * 		 with the centre of their parent on a distance of half the radius of the parent from that centre. 
 	 * 		 We choose to place them randomly at the sides of their deceized parent.
 	 * 		 Their radius is half of the parents' radius,
 	 * 		 the direction of the first child is determined at random,
 	 * 		 the direction of the second child is the opposite thereof.
 	 * 		 Finally, their speed equals 1.5 times the speed their parent had.
 	 * 		 | if (this.getWorld() != null && this.getRadius() >= 30)
 	 * 		  | 			then @see implementation
 	 * 
 	 * @throws IllegalPositionException
 	 * 		   If the position of the created asteroid child is not allowed.
 	 */
 	@Override
 	public void terminate(){
 		super.terminate();
 		if (this.getWorld() != null && this.getRadius() >= 30){
 			//TODO Kan ik wel deze dingen opvragen? De entiteit is immers getermineerd.
 			double xPosition = this.getxPosition();
 			double yPosition = this.getyPosition();
 			double r = this.getRadius();
 			double parentVelocity = this.getTotalVelocity();
 			double randomAngle = Math.random() *2.0*Math.PI; 
 			// TODO: aan de hand van testen; Misschien moeten we ze wel random plaatsen.
 			double randomPlacement = Math.random() * 2.0 * Math.PI;
 			
 			//TODO: Probleem: Bij het aanmaken van een asteroid kunnen exceptions worden gegooid
// 			        daardoor moeten die hier meteen opgevangen worden, en dan mogen die entiteiten
// 			        meteen getermineerd worden maar dat lukt hier niet perfect.
 			
// 			Asteroid firstChild = new Asteroid(xPosition+(r/2*Math.cos(randomPlacement)), yPosition + (r/2*Math.sin(randomPlacement)), 
//					1.5*parentVelocity*Math.cos(randomAngle),1.5*parentVelocity*Math.sin(randomAngle),
//					r/2);
// 			
// 			Asteroid secondChild = new Asteroid(xPosition-(r/2*Math.cos(randomPlacement)), yPosition - (r/2*Math.sin(randomPlacement)), 
//					-1.5*parentVelocity*Math.cos(randomAngle),-1.5*parentVelocity*Math.sin(randomAngle),
//					r/2);
 			
// 			TODO CONSULTATIE: vragen of deze triviale try catch een goede oplossing is voor het throws probleem.
 			 // prof: mag maar volgens liskov mag je ook de nodige exceptions gooien in terminate in entity en dan 
 			 // die gegooide entities beperken in bijvoorbeeld ship waar die exceptions niet nodig zijn.
 			// Maar dit mag dus in principe ook wel.
 			
 			Asteroid firstChild;
			try {
				firstChild = new Asteroid(xPosition+(r/2*Math.cos(randomPlacement)), yPosition + (r/2*Math.sin(randomPlacement)), 
						1.5*parentVelocity*Math.cos(randomAngle),1.5*parentVelocity*Math.sin(randomAngle),
						r/2);
				this.getWorld().addEntity(firstChild);
			} catch (IllegalPositionException | IllegalRadiusException e) {
			}
 			
			Asteroid secondChild;
			try {
				secondChild = new Asteroid(xPosition-(r/2*Math.cos(randomPlacement)), yPosition - (r/2*Math.sin(randomPlacement)), 
						-1.5*parentVelocity*Math.cos(randomAngle),-1.5*parentVelocity*Math.sin(randomAngle),
						r/2);
				this.getWorld().addEntity(secondChild);
			} catch (IllegalPositionException | IllegalRadiusException | IllegalEntityException e) {
			}		
 		}
 	}
 	
 	/**
 	 * Moves this planetoid during a certain duration, depending on their velocity.
 	 * 
 	 * @param duration
 	 * 		  The time during which this planetoid moves
 	 * @throws IllegalRadiusException 
 	 * @post The distanceTravelled is incremented by the amount of kilometers that the planetoid had moved.
 	 * 		 That amount is calculated using the duration and the velocity of this ship.
 	 * 		 | new.getDistanceTravelled() += duration*this.getTotalVelocity()
 	 */
 	public void move(double duration) throws IllegalPositionException, IllegalDurationException{
 		// TODO misschien hier al krimpen
 		super.move(duration);
 		// TODO ik denk dat dit een juiste manier is om dit te beschrijven.
 		this.distanceTraveled += duration*this.getTotalVelocity();		
 		shrink(distanceTraveled);
 	}
 	
 	/**
 	 * This method shrinks this planetoid based on the distance it has travelled.
 	 * If the planetoid no longer has a valid radius, through this shrinking, it 
 	 * is terminated.
 	 * 
 	 * @param distanceTravelled
 	 * 		  The amount of kilometres travelled by this planetoid
 	 * @effect The radius of this planetoid is shrunken
 	 * 		   | new. getRadius() == (getInitialRadius() - (0.0001)*distanceTravelled);
 	 * @effect If the radius is not valid, the planetoid is terminated.
 	 * 		   | if !isValidRadius()
 	 * 				| then this.terminate(); 
 	 */
 	private void shrink(double distanceTravelled){
 		double shrunk_radius = (getInitialRadius() - (0.0001)*distanceTravelled);
 		
 		try {
			setRadius(shrunk_radius);
		} catch (IllegalRadiusException e) {
			this.terminate();
		}
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
		return this.distanceTraveled;
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
	protected double distanceTraveled = 0;
	
	/**
     * Variable registering the radius that this planetoid had upon creation.
     */
    protected final double initialRadius;
    
  //@Override
  	public void handleOtherEntityCollision(Entity entity){
  		if(entity instanceof MinorPlanet){
  			//casual collision
  			this.handleCasualCollision(entity);
  		}
  	
  		if(entity instanceof Bullet){
  			//this dies
  			this.terminate();
  		}
  		
  		if(entity instanceof Ship){
  			//ONLY ACTIVE HANDLER, THIS WILL HANDLE THE SHIP'S TELEPORTATION AS THE SHIP CLASS CAN'T BE UPDATED FROM PART 2
  			((Ship) entity).teleport();
  		}
  	}
  	
  	public boolean isValidDistanceTraveled(double distance){
  		return true;
  	}
	
}
