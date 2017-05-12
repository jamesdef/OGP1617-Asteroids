package asteroids.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import asteroids.model.Asteroid;
import asteroids.model.Bullet;
import asteroids.model.Planetoid;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.part3.facade.IFacade;
//import asteroids.part3.model.Program;
//import asteroids.part3.model.programs.ProgramFactory;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.internal.ProgramParser;
import asteroids.util.ModelException;

public class DraaiendeTesten {

  private static final double EPSILON = 0.0001;
  private static final double BIG_EPSILON = 1.0E14;
  private static final double VERY_BIG_EPSILON = 1.0E34;

  static int nbStudentsInTeam;
  IFacade facade;
//  IProgramFactory<?, ?, ?, Program> programFactory = new ProgramFactory();
  World filledWorld;
  Ship ship1, ship2, ship3;
  Bullet bullet1;
  static int score = 0;
  static int max_score = 0;

  @AfterClass
  public static void tearDownAfterClass() {
    System.out.println("Score: " + score + "/" + max_score);
  }

  @Before
  public void setUp() throws ModelException {
	  
    facade = new asteroids.facade.Facade();
    
    nbStudentsInTeam = facade.getNbStudentsInTeam();
    filledWorld = facade.createWorld(2000, 2000);
    ship1 = facade.createShip(100, 120, 10, 5, 50, 0, 1.0E20);
    for (int i = 1; i < 10; i++) {
      Bullet bulletToLoad = facade.createBullet(100, 120, 0, 0, 10);
      facade.loadBulletOnShip(ship1, bulletToLoad);
    }
    facade.addShipToWorld(filledWorld, ship1);
    ship2 = facade.createShip(200, 220, 10, 5, 50, 0, 1.0E20);
    facade.addShipToWorld(filledWorld, ship2);
    bullet1 = facade.createBullet(300, 320, 10, 5, 50);
    facade.addBulletToWorld(filledWorld, bullet1);
  }
  
//  @Test
//  public void testEvolveShipDoubleBoundaryCollision() throws ModelException {
//    max_score += 8;
//    World world = facade.createWorld(1000, 1000);
//    Ship ship1 = facade.createShip(100, 100, -10, -10, 50, 0, 1.0E20);
//    facade.addShipToWorld(world, ship1);
//    // Probleem: blijft steken in evolve!
//    // collision after 15 seconds
//    facade.evolve(world, 15, null);
//    assertEquals(1, facade.getWorldShips(world).size());
//    assertEquals(150, facade.getShipPosition(ship1)[0], EPSILON);
//    assertEquals(150, facade.getShipPosition(ship1)[1], EPSILON);
//    assertEquals(10, facade.getShipVelocity(ship1)[0], EPSILON);
//    assertEquals(10, facade.getShipVelocity(ship1)[1], EPSILON);
//    score += 8;
//  }
  
}