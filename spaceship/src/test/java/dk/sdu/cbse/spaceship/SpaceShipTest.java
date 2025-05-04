package dk.sdu.cbse.spaceship;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpaceShipTest {
    
    @BeforeAll
    public static void init() {
        System.out.println("Test class initialized");
    }

    @Test
    public void testSpaceshipCreation() {
        Spaceship spaceship = new Spaceship();
        Assertions.assertNotNull(spaceship, "Spaceship should be created");
        Assertions.assertEquals(0, spaceship.x, "Initial x position should be 0");
        Assertions.assertEquals(0, spaceship.y, "Initial y position should be 0");
    }

    @Test
    public void testSpaceshipMovement() {
        Spaceship spaceship = new Spaceship();

        Assertions.assertEquals(0, spaceship.xVelocity, "Initial x velocity should be 0");
        spaceship.move(true, false, false);
        Assertions.assertNotEquals(spaceship.yVelocity, 0, "Spaceship should move forward");
    }

    @Test
    public void testSpaceshipCollision() {
        Spaceship spaceship1 = new Spaceship();
        Spaceship spaceship2 = new Spaceship();

        Assertions.assertTrue(spaceship1.collidesWith(spaceship2), "Spaceships should collide");

        spaceship1.invulnerableTime = 1000;

        Assertions.assertFalse(spaceship1.collidesWith(spaceship2), "Spaceships should not collide when invulnerable");
    }
}