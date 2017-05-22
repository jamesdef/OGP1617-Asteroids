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
import asteroids.model.Program;
import asteroids.programs.BlockStatement;
import asteroids.programs.ProgramFactory;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.internal.ProgramParser;
import asteroids.util.ModelException;

public class Part3TestFull {

  private static final double EPSILON = 0.0001;
  private static final double BIG_EPSILON = 1.0E14;
  private static final double VERY_BIG_EPSILON = 1.0E34;

  static int nbStudentsInTeam;
  IFacade facade;
  IProgramFactory<?, ?, ?, Program> programFactory;
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
    programFactory = (IProgramFactory<?, ?, ?, Program>) facade.createProgramFactory();
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
 

  
  @Test
  public void testTurnStatement_ValidAngleEnoughTimeLeft() throws ModelException {
    max_score += 3;
    String code = "turn 1.0; " + "print 0.4; ";
    facade.turn(ship1, 1.5);
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 0.45);
    assertEquals(2.5, facade.getShipOrientation(ship1), EPSILON);
    Object[] expecteds = { 0.4 };
    assertArrayEquals(expecteds, results.toArray());
    score += 3;
  }
  
}