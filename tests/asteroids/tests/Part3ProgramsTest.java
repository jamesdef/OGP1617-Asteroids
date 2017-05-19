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
import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.internal.ProgramParser;
import asteroids.util.ModelException;

public class Part3ProgramsTest {

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

  
  
  
  // Fire statement

  @Test
  public void testFireStatement_EnoughTimeLeft() throws ModelException {
    max_score += 3;
    String code = "fire; " + "print 0.4; ";
    int oldNbBullets = facade.getNbBulletsOnShip(ship1);
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 0.45);
    assertEquals(oldNbBullets - 1, facade.getNbBulletsOnShip(ship1));
    Object[] expecteds = { 0.4 };
    assertArrayEquals(expecteds, results.toArray());
    score += 3;
  }

  @Test
  public void testFireStatement_NotEnoughTimeLeft() throws ModelException {
    max_score += 3;
    String code = "fire; " + "print 0.4; ";
    int oldNbBullets = facade.getNbBulletsOnShip(ship1);
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 0.15);
    assertEquals(oldNbBullets, facade.getNbBulletsOnShip(ship1));
    assertNull(results);
    score += 3;
  }

  @Test
  public void testFireStatement_InFunctionBody() throws ModelException {
    try {
      max_score += 3;
      String code = "def f { " + "  fire; " + "  return 5.0; " + "}" + "print f(); ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 0.3);
      fail();
    } catch (ModelException exc) {
      score += 3;
    }
  }

  // Turn statement

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

  @Test
  public void testTurnStatement_NotEnoughTimeLeft() throws ModelException {
    max_score += 3;
    String code = "turn 1.0; " + "print 0.4; ";
    facade.turn(ship1, 1.5);
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 0.15);
    assertEquals(1.5, facade.getShipOrientation(ship1), EPSILON);
    assertNull(results);
    score += 3;
  }

  @Test
  public void testTurnStatement_InvalidAngle() throws ModelException {
    max_score += 5;
    try {
      String code = "turn 10.0; " + "print 0.4; ";
      facade.turn(ship1, 1.5);
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      List<Object> results = facade.executeProgram(ship1, 0.45);
      // It is allowed to do nothing in case of an illegal angle.
      assertEquals(1.5, facade.getShipOrientation(ship1), EPSILON);
      Object[] expecteds = { 0.4 };
      assertArrayEquals(expecteds, results.toArray());
      score += 5;
    } catch (ModelException exc) {
      assertEquals(1.5, facade.getShipOrientation(ship1), EPSILON);
      score += 5;
    }
  }

  @Test
  public void testTurnStatement_InFunctionBody() throws ModelException {
    try {
      max_score += 3;
      String code = "def f { " + "  turn 1.0; " + "  return 5.0; " + "}" + "print f(); ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 0.3);
      fail();
    } catch (ModelException exc) {
      score += 3;
    }
  }

  // Thruster ON statement

  @Test
  public void testThrusterOnStatement_EnoughTimeLeft() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 3;
      String code = "thrust; " + "print 0.4; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      List<Object> results = facade.executeProgram(ship1, 0.45);
      assertTrue(facade.isShipThrusterActive(ship1));
      Object[] expecteds = { 0.4 };
      assertArrayEquals(expecteds, results.toArray());
      score += 3;
    }
  }

  @Test
  public void testThrusterStatement_NotEnoughTimeLeft() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 3;
      String code = "thrust; " + "print 0.4; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      List<Object> results = facade.executeProgram(ship1, 0.15);
      assertFalse(facade.isShipThrusterActive(ship1));
      assertNull(results);
      score += 3;
    }
  }

  @Test
  public void testThrusterOnStatement_InFunctionBody() throws ModelException {
    if (nbStudentsInTeam > 1) {
      try {
        max_score += 3;
        String code = "def f { " + "  thrust; " + "  return 5.0; " + "}" + "print f(); ";
        Program program = ProgramParser.parseProgramFromString(code, programFactory);
        facade.loadProgramOnShip(ship1, program);
        facade.executeProgram(ship1, 0.3);
        fail();
      } catch (ModelException exc) {
        score += 3;
      }
    }
  }

  // Thruster OFF statement

  @Test
  public void testThrusterOffStatement_EnoughTimeLeft() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 3;
      String code = "thrust; " + "print 0.4; " + "thrust_off; " + "print 0.8; ";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 0.3);
      assertTrue(facade.isShipThrusterActive(ship1));
      List<Object> results = facade.executeProgram(ship1, 0.35);
      assertFalse(facade.isShipThrusterActive(ship1));
      Object[] expecteds = { 0.4, 0.8 };
      assertArrayEquals(expecteds, results.toArray());
      score += 3;
    }
  }

  @Test
  public void testThrusterOffStatement_NotEnoughTimeLeft() throws ModelException {
    if (nbStudentsInTeam > 1) {
      max_score += 3;
      String code = "thrust; " + "print 0.4; " + "thrust_off; " + "print 0.8;";
      Program program = ProgramParser.parseProgramFromString(code, programFactory);
      facade.loadProgramOnShip(ship1, program);
      facade.executeProgram(ship1, 0.2);
      assertTrue(facade.isShipThrusterActive(ship1));
      List<Object> results = facade.executeProgram(ship1, 0.15);
      assertTrue(facade.isShipThrusterActive(ship1));
      assertNull(results);
      score += 3;
    }
  }

  @Test
  public void testThrusterOffStatement_InFunctionBody() throws ModelException {
    if (nbStudentsInTeam > 1) {
      try {
        max_score += 3;
        String code = "def f { " + "  thrust_off; " + "  return 5.0; " + "}" + "print f(); ";
        Program program = ProgramParser.parseProgramFromString(code, programFactory);
        facade.loadProgramOnShip(ship1, program);
        facade.executeProgram(ship1, 0.3);
        fail();
      } catch (ModelException exc) {
        score += 3;
      }
    }
  }

  // Skip statement

  @Test
  public void testSkipStatement_EnoughTimeLeft() throws ModelException {
    max_score += 3;
    String code = "skip; " + "print 0.4;";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 1.0);
    Object[] expecteds = { 0.4 };
    assertArrayEquals(expecteds, results.toArray());
    score += 3;
  }

  @Test
  public void testSkipStatement_NotEnoughTimeLeft() throws ModelException {
    max_score += 3;
    String code = "skip; " + "print 0.4;";
    Program program = ProgramParser.parseProgramFromString(code, programFactory);
    facade.loadProgramOnShip(ship1, program);
    List<Object> results = facade.executeProgram(ship1, 0.1);
    assertNull(results);
    score += 3;
  }


}