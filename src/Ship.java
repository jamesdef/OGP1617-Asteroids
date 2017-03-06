
public class Ship {

//Constructors 
	/**
	 * Initialize this new ship with given position,radius, speed and orientation.
	 * 
	 * 
	 * 
	 * @param  position
	 *         The position of this new ship.
	 *         
	 * @param  radius
	 * 		   The radius of this new ship.
	 * 
	 * @param  speed
	 *         The speed of this vessel.
	 * @param  orientation
	 * 		   The orientation of this vessel, i.e., it's direction.
	 *       
	 * @post   The lowest possible speed is zero, it can only move forward.
	 * 		  
	 * @post   The lowest possible radius is 10 km.
	 * 
	 * @post   The hightest possible speed is 300000 (km/s) it can never exceed this.
	 * 		   digital clock is equal to 23.
	 * 
	 * @post   The orientation of the ship will always be a value between 0 and 2PI.
	 * 
	 * 
	 *      
	 * @post   If the given hours are in the range 0..23, the hours of
	 *         this new digital clock are equal to the given hours.
	 *         If the given hours exceed 23, the hours for this new
	 *         digital clock are equal to the given hours modulo 24.
	 *         If the given hours are negative, the hours for  this new
	 *         digital clock are equal to 0.
	 *       | if ( (hours >= 0) && (hours <= 23) )
	 *       |   then new.getHours() == hours
	 *       | else if (hours > 23)
	 *       |   then new.getHours() == (hours % 24)
	 *       | else if (hours < 0)
	 *       |   then new.getHours() == 0
	 * @effect The given minutes are set as the minutes of this
	 *         new digital clock.
	 *       | setMinutes(minutes)
	 * @note   We cannot use the mutator setHours(int) in the specification,
	 *         because the range for the hours of this new digital clock is
	 *         0..0 upon entry to this constructor. We could introduce a more
	 *         general method setHours(hours,minHours,maxHours) to avoid
	 *         a duplication of the specification of setting the hours.
	 *         We prefer not to do so to keep things simple at this
	 *         stage of the course.
	 */
	public Ship(){
		
	}

}
