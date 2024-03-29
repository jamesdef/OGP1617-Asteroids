package asteroids.model;

import asteroids.model.exceptions.IllegalPositionException;
import asteroids.model.exceptions.IllegalRadiusException;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class for dealing with MinorPlanets, a specific kind of entities.
 * 
 * @invar 	The radius of each minor planet must be a valid value.
 * 			|isValidRadius(getRadius()) 
 * 
 * @version 3.0     
 * @author James Defauw & Michiel De Koninck
 *
 */

public abstract class MinorPlanet extends Entity {

	/**
	 * Initialize this new minor planet with given position,radius, speed.
	 * 
	 * 
	 * @param  xPosition
	 *         The position of this minor planet, according to the x-axis. 
	 *         Expressed in km.
	 *         
	 * @param  yPosition
	 *         The position of this minor planet,according to the y-axis.
	 *         Expressed in km.
	 *         
	 * @param  radius
	 * 		   The radius of this new minor planet.
	 * 		   Expressed in km.	
	 * 
	 * @param  xVelocity 
	 *         The velocity of this minor planet, in the x-direction.
	 *         Expressed in km/s.
	 *         
	 * @param  yVelocity 
	 * 		   The velocity of the minor planet, in the y-direction. 
	 * 		   Expressed in km/s.
	 *          
	 * @effect The given parameters are set as the properties of the new minor planet.
	 * 		   |setPosition(xPosition,yPosition);
	 *	       |setVelocity(xVelocity,yVelocity);
	 *	       |setRadius(radius);
	 *
	 */
	@Raw @Model
	public MinorPlanet(double xPosition, double yPosition, double xVelocity, double yVelocity, double radius)
			throws IllegalPositionException, IllegalRadiusException {
		super(xPosition, yPosition, xVelocity, yVelocity, radius);
		
	}
	
	 /**
	 * Checks whether the given radius has a valid value.
	 * 
	 * @return The radius must, at all times, be bigger or equal to 5km.
	 * 		   |radius >= 5;
	 */
	@Override
	public boolean isValidRadius(double radius){
		return super.isValidRadius(radius) && radius>=5.0;
	}

}
