package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import be.kuleuven.cs.som.annotate.Value;

/**
 * Class that defines vectors and operations upon them. 
 * A vector has x and y values.
 * 
 * @invar The x-value of this vector must at all times be valid.
 * 		  | isValidValue(this.getXValue())
 * 
 * @invar The y-value of this vector must at all times be valid.
 * 		  | isValidValue(this.getYValue())
 * 
 * @version 3.0
 * @author Michiel De Koinck & James Defauw
 */

@Value
public class Vector {
	
	/**
	 * Inititialize this new vector with given x-value and y-value.
	 * 
	 * @param xValue
	 * 		  The value to be set as the x-Value for this vector.
	 * @param yValue
	 * 		  The value to be set as the y-value for this vector.
	 */
	public Vector(double xValue, double yValue){
		if (!isValidValue(xValue)){
			// Change the xValue to an allowed default value: 0
			xValue = 0;
		}

		if(!isValidValue(yValue)){
			yValue = 0;
		}

		this.xValue = xValue;
		this.yValue = yValue;
	}
	
// --------------- Standard getters ---------------
	
	/**
	 * Returns the x-value of this vector.
	 */
	@Basic @Raw  @Immutable
	public double getXValue(){
		return this.xValue;
	}
	
	/**
	 * Returns the y-value of this vector.
	 */
	@Basic @Raw  @Immutable
	public double getYValue(){
		return this.yValue;
	}
	
	/**
	 * Returns whether the given value is valid.
	 * 
	 * @param value
	 * 
	 * @return False only if the given double is not a number
	 * 		   | result = !Double.isNaN(yValue)
	 */
	@Raw
	public boolean isValidValue(Double value){
		return (!Double.isNaN(value));
	}
	
	/**
	 * This method computes the norm of this vector.
	 * 
	 * @return The square root of the sum of the squared x- and y-values of this vector.
	 * 		   |@see implementation
	 */
	public double norm() {
		return Math.sqrt((Math.pow(this.getXValue(),2.0)) + (Math.pow(this.getYValue(),2.0)));
		
	}
	
	/**
	 * Return the hash code for this vector.
	 * As doubles are not hashable, we first convert the doubles to long-values.
	 * We then take the sum of both of the hashcodes to compute the hashcode for this vector.
	 */
	@Override
	public int hashCode() {
		return (   Long.valueOf(Double.doubleToLongBits(getXValue())).hashCode()
		+ Long.valueOf(Double.doubleToLongBits(getYValue())).hashCode()   );
	}
	
	
	/**
	 * Return a textual representation of this vector.
	 * 
	 * @return A string consisting of the textual representation of the 
	 * 		   values owned by this vector. These are seperated by 
	 * 		   a space and a comma and are enclosed by brackets.$
	 * 		  |result.equals( "(" + getXValue() + " , " + getYValue() + ")"
	 */
	@Override
	public String toString() {
		return "(" + getXValue() + " , " + getYValue() + ")";
	}
		
	
	/**
	 * Check whether this vector is equal to the given object.
	 * 
	 * @return True if and only if the given object is effective, 
	 * 		   this vector and the given object belong to the same class,
	 * 		   and if this vector and the other object, interpreted as a vector, 
	 * 		   have the same xValue and yValue.
	 * 		   | result == ( (other != null)
	 * 		   | 	&& (this.getClass() == other.getClass() )
	 * 		   |    && (this.getXValue() == (Vector other).getXValue()
	 * 		   |	&& ((this.getYValue() == (Vector other).getYValue()
	 */
	@Override
	public boolean equals (Object other) {
		if (other == null)
			return false;
		if (this.getClass() != other.getClass())
			return false;
		Vector otherVector = (Vector) other;
		
		return ((this.getXValue() == otherVector.getXValue()) && 
							(this.getYValue() == otherVector.getYValue()));
	}
	
// ------------- Initialising Variables ----------------
	/**
	 * Variable registerng the vector that has 0 as x-value and as y-value.
	 */
	public final static Vector VECTOR_0 = new Vector(0,0);
	
	/**
	 * A variable registering the x-value of this vector.
	 */
	private final double xValue;
		
	/**
	 * A variable registering the y-value of this vector.
	 */
	private final double yValue;
			
		
		
}
