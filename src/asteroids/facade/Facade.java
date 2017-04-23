package asteroids.facade;


import java.util.Collection;
import java.util.Set;

import asteroids.model.Bullet;
import asteroids.model.Entity;
import asteroids.model.IllegalBulletException;
import asteroids.model.IllegalCollisionException;
import asteroids.model.IllegalDurationException;
import asteroids.model.IllegalPositionException;
import asteroids.model.IllegalRadiusException;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.part1.facade.IFacade;
import asteroids.part2.CollisionListener;
import asteroids.util.ModelException;

public class Facade implements IFacade, asteroids.part2.facade.IFacade {

	@Override
	public Ship createShip()throws ModelException{
		
		try {
			return new Ship();
		} catch (IllegalPositionException exc) {
			throw new ModelException("ILLEGAL POSITION");
		} catch (IllegalRadiusException exc) {
			throw new ModelException("ILLEGAL RADIUS");
		}
	}

//	@Override
//	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double orientation)
//			throws ModelException {
//		
//		try {
//			return new Ship(x, y, xVelocity, yVelocity, radius, orientation);
//		} catch (IllegalPositionException exc) {
//			throw new ModelException("ILLEGAL POSITION");
//		} catch (IllegalRadiusException exc) {
//			throw new ModelException("ILLEGAL RADIUS");
//		}
//	}

	@Override
	public double[] getShipPosition(Ship ship) throws ModelException {
		return ship.getPosition();
	}

	@Override
	public double[] getShipVelocity(Ship ship) throws ModelException {
		return ship.getVelocity();
	}

	@Override
	public double getShipRadius(Ship ship) throws ModelException {
		return ship.getRadius();
	}

	@Override
	public double getShipOrientation(Ship ship) throws ModelException {
		return ship.getOrientation();
	}

//	@Override
//	public void move(Ship ship, double dt) throws ModelException {
//		
//			try {
//				ship.move(dt);
//			} catch (IllegalPositionException e) {
//				throw new ModelException("ILLEGAL POSITION");
//			} catch (IllegalDurationException e) {
//				throw new ModelException("ILLEGAL DURATION");
//			}
//	}

//	@Override 
//	public void thrust(Ship ship, double amount) throws ModelException {
//		
//		ship.thrust(amount);
//		
//	}

	@Override
	public void turn(Ship ship, double angle) throws ModelException {
		ship.turn(angle);
	}

	@Override
	public double getDistanceBetween(Ship ship1, Ship ship2) throws ModelException {
		return ship1.getDistanceBetween(ship2);
		
	}

	@Override
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException {
		return ship1.significantOverlap(ship2);
	}

	@Override
	public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.getTimeToEntityCollision(ship2);
		} catch (IllegalCollisionException exc) {
			throw new ModelException("Ships overlap");
		}
	}

	@Override
	public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.getEntityCollisionPosition(ship2);
		} catch (IllegalCollisionException exc) {
			throw new ModelException("Ships overlap");
		}
	}

	@Override
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double direction,
			double mass) throws ModelException {
		try {
			return new Ship(x,y,xVelocity,yVelocity,radius,direction,mass);
		} catch (IllegalPositionException e) {
			throw new ModelException("Illegal Position");
		} catch (IllegalRadiusException e) {
			throw new ModelException("Illegal Radius");
		}
	}
	

	@Override
	public void terminateShip(Ship ship) throws ModelException {
		ship.terminate();
	}

	@Override
	public boolean isTerminatedShip(Ship ship) throws ModelException {
		return ship.isTerminated();
	}

	@Override
	public double getShipMass(Ship ship) throws ModelException {
		return ship.getTotalMass();
	}

	@Override
	public World getShipWorld(Ship ship) throws ModelException {
		return ship.getWorld();
	}

	@Override
	public boolean isShipThrusterActive(Ship ship) throws ModelException {
		return ship.getThrustState();
	}

	@Override
	public void setThrusterActive(Ship ship, boolean active) throws ModelException {
		ship.setThrusterActivity(active);
	}

	@Override
	public double getShipAcceleration(Ship ship) throws ModelException {
		return ship.getAcceleration();
	}

	@Override
	public Bullet createBullet(double x, double y, double xVelocity, double yVelocity, double radius)
			throws ModelException {
		try {
			return new Bullet(x, y,  xVelocity,  yVelocity,  radius);
		} catch (IllegalPositionException e) {
			throw new ModelException("Illegal Position");
		} catch (IllegalRadiusException e) {
			throw new ModelException("Illegal Radius");	}
	}

	@Override
	public void terminateBullet(Bullet bullet) throws ModelException {
		bullet.terminate();
	}

	@Override
	public boolean isTerminatedBullet(Bullet bullet) throws ModelException {
		return bullet.isTerminated();
	}

	@Override
	public double[] getBulletPosition(Bullet bullet) throws ModelException {
		return bullet.getPosition();
	}

	@Override
	public double[] getBulletVelocity(Bullet bullet) throws ModelException {
		return bullet.getVelocity();
	}

	@Override
	public double getBulletRadius(Bullet bullet) throws ModelException {
		return bullet.getRadius();
	}

	@Override
	public double getBulletMass(Bullet bullet) throws ModelException {
		return bullet.getMass();
	}

	@Override
	public World getBulletWorld(Bullet bullet) throws ModelException {
		return bullet.getWorld();
	}

	@Override
	public Ship getBulletShip(Bullet bullet) throws ModelException {
		return bullet.getShip();
	}

	@Override
	public Ship getBulletSource(Bullet bullet) throws ModelException {
		return bullet.getSource();
	}

	@Override
	public World createWorld(double width, double height) throws ModelException {
		return new World(width,height);
	}
	

	@Override
	public void terminateWorld(World world) throws ModelException {
		world.terminate();
	}

	@Override
	public boolean isTerminatedWorld(World world) throws ModelException {
		return world.isTerminated();
	}

	@Override
	public double[] getWorldSize(World world) throws ModelException {
		return new double[]{world.getWidth(), world.getHeight()};
	}

	@Override
	public Set<? extends Ship> getWorldShips(World world) throws ModelException {
		return world.getAllShips();
	}

	@Override
	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException {
		return world.getAllBullets();
	}

	@Override
	public void addShipToWorld(World world, Ship ship) throws ModelException {
		world.addEntity(ship);
	}

	@Override
	public void removeShipFromWorld(World world, Ship ship) throws ModelException {
		world.removeEntity(ship);
	}

	@Override
	public void addBulletToWorld(World world, Bullet bullet) throws ModelException {
		world.addEntity(bullet);
	}

	@Override
	public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException {
		world.removeEntity(bullet);
	}

	@Override
	public Set<? extends Bullet> getBulletsOnShip(Ship ship) throws ModelException {
		return ship.getBullets();
	}

	@Override
	public int getNbBulletsOnShip(Ship ship) throws ModelException {
		return ship.getNbOfBullets();
	}

	@Override
	public void loadBulletOnShip(Ship ship, Bullet bullet) throws ModelException {
		try {
			ship.loadBullet(bullet);
		} catch (IllegalBulletException e) {
			throw new ModelException ("Illegal Bullet");
		} catch (IllegalPositionException e) {
			throw new ModelException("Illegal Position");
		}
	}

	@Override
	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException {
		try {
			ship.loadBullets(bullets);
		} catch (IllegalBulletException e) {
			throw new ModelException ("Illegal Bullet");
		} catch (IllegalPositionException e) {
			throw new ModelException("Illegal Position");
		}
	}

	@Override
	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException {
		ship.removeBullet(bullet);
	}

	@Override
	public void fireBullet(Ship ship) throws ModelException {
		try {
			ship.fireBullet();
		} catch (IllegalPositionException e) {
			throw new ModelException("Illegal Position");
		} catch (IllegalRadiusException e) {
			throw new ModelException("Illegal Radius");		}
		}

	@Override
	public double getTimeCollisionBoundary(Object object) throws ModelException {
		return ((Entity) object).getTimeToBoundaryCollision();
	}

	@Override
	public double[] getPositionCollisionBoundary(Object object) throws ModelException {
		return ((Entity) object).getBoundaryCollisionPosition();

	}

	@Override
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException {
		 try {
			return ((Entity) entity1).getTimeToEntityCollision((Entity) entity2);
		} catch (IllegalCollisionException e) {
			throw new ModelException("Illegal Collision");
		}
	}

	@Override
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException {
		try {
			return ((Entity) entity1).getEntityCollisionPosition((Entity) entity2);
		} catch (IllegalCollisionException e) {
			throw new ModelException("Illegal Collision");
		}
	}

	@Override
	public double getTimeNextCollision(World world) throws ModelException {
		try {
			return world.getTimeToFirstCollision();
		} catch (IllegalCollisionException e) {
			throw new ModelException("Illegal Collision");
		}
	}

	@Override
	public double[] getPositionNextCollision(World world) throws ModelException {
		try {
			return world.getFirstCollisionPosition();
		} catch (IllegalCollisionException e) {
			throw new ModelException("Illegal Collision");
		}
	}

	@Override
	public void evolve(World world, double dt, CollisionListener collisionListener) throws ModelException {	
		try {
			world.evolve(dt);
		} catch (IllegalCollisionException e) {
			throw new ModelException("Illegal Collision");

		} catch (IllegalPositionException e) {
			throw new ModelException("Illegal Position");

		} catch (IllegalDurationException e) {
			throw new ModelException("Illegal Duration");

		}
	}

	@Override
	public Object getEntityAt(World world, double x, double y) throws ModelException {
		return world.getEntityAt(x, y);
	}

	@Override
	public Set<? extends Object> getEntities(World world) throws ModelException {
		return world.getAllEntities();
	}
}
