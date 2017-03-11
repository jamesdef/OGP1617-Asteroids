package asteroids.facade;


import asteroids.model.IllegalCollisionException;
import asteroids.model.IllegalDurationException;
import asteroids.model.IllegalPositionException;
import asteroids.model.Ship;
import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;

public class Facade implements IFacade {
	
	//Je moet de ModelException enkel gebruiken indien de methode waarop ze van toepassing is defensief werd geprogrammeerd.
	//In dat geval ziet je body er bv als volgt uit: try{....Body waarin je de methode van je klasse oproept...} catch{ throw argumentexception}
	// In try probeer je die methode uit je klasse te doorlopen, gooit die methode een exception, dan vang je die dus hier op met catch.

	@Override
	public Ship createShip()throws ModelException{
		// TODO Auto-generated method stub
		Ship(0.0,0.0,0,0.0,0.0,0.0);
	}

	@Override
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double orientation)
			throws ModelException {
		// TODO Auto-generated method stub
		ship.setPosition(xPosition,yPosition);
		this.setVelocity(xVelocity,yVelocity);
		this.setRadius(radius);
		this.setOrientation(orientation);	
	}

	@Override
	public double[] getShipPosition(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return ship.getPosition();
	}

	@Override
	public double[] getShipVelocity(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return ship.getVelocity();
	}

	@Override
	public double getShipRadius(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return ship.getRadius();
	}

	@Override
	public double getShipOrientation(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return ship.getOrientation();
	}

	@Override
	public void move(Ship ship, double dt) throws ModelException {
		// TODO Auto-generated method stub
		try {
			ship.move(dt);
		} catch (IllegalPositionException | IllegalDurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void thrust(Ship ship, double amount) throws ModelException {
		// TODO Auto-generated method stub
		ship.thrust(amount);
		
	}

	@Override
	public void turn(Ship ship, double angle) throws ModelException {
		// TODO Auto-generated method stub
		ship.turn(angle);
	}

	@Override
	public double getDistanceBetween(Ship ship1, Ship ship2) throws ModelException {
		// TODO Auto-generated method stub
		return ship1.getDistanceBetween(ship2);
		
	}

	@Override
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException {
		// TODO Auto-generated method stub
		return ship1.overlap(ship2);
	}

	@Override
	public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException {
		// TODO Auto-generated method stub
		try {
			return ship1.getTimeToCollision(ship2);
		} catch (IllegalCollisionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException {
		// TODO Auto-generated method stub
		try {
			return ship1.getCollisionPosition(ship2);
		} catch (IllegalCollisionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
