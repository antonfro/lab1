import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    Car c;

    @BeforeEach
    void Setup() {
        c = new Saab95();
    }

    @Test
    void hasTransport() {
        Cartransport s = new Cartransport();
        assertFalse(c.isOnTransport());
        s.rampButton();
        s.loadCar(c);
        assertTrue(c.isOnTransport());
        s.unloadCar();
        assertFalse(c.isOnTransport());
    }

    @Test
    void getX() {
        c.startEngine();
        c.turnRight();
        c.move();
        c.move();
        assertEquals(0.2,c.getX());
    }

    @Test
    void getY() {
        assertEquals(0,c.getY());
    }

    @Test
    void getTowards() {
        assertEquals(Car.Direction.NORTH, c.getTowards());
    }

    @Test
    void getNrDoors() {
        assertEquals(2, c.getNrDoors());
    }

    @Test
    void getEnginePower() {
        assertEquals(125, c.getEnginePower());
    }

    @Test
    void getCurrentSpeed() {
        c.startEngine();
        assertEquals(0.1, c.getCurrentSpeed());
    }

    @Test
    void getColor() {
        assertEquals(Color.red, c.getColor());
    }

    @Test
    void startEngine() {
        c.startEngine();
        assertEquals(0.1, c.getCurrentSpeed());
    }

    @Test
    void stopEngine() {
        assertEquals(0, c.getCurrentSpeed());
    }

    @Test
    void decrementSpeed() {
        c.startEngine();
        c.brake(1);
        assertEquals(0, c.getCurrentSpeed());
    }

    @Test
    void incrementSpeed() {
        c.startEngine();
        c.gas(1);
        assertEquals(1.35, c.getCurrentSpeed());
    }

    @Test
    void speedFactor() {
        c.startEngine();
        assertEquals(1.25, c.getSpeedFactor());
    }

    @Test
    void gas() {
        c.startEngine();
        c.gas(1);
        assertEquals(1.35, c.getCurrentSpeed());
    }

    @Test
    void illegalGas() {
        c.startEngine();
        assertThrows(IllegalArgumentException.class, () -> c.gas(2));
        assertThrows(IllegalArgumentException.class, () -> c.gas(-1));
    }

    @Test
    void brake() {
        c.startEngine();
        c.brake(1);
        assertEquals(0, c.getCurrentSpeed());
    }

    @Test
    void illegalBrake() {
        c.startEngine();
        assertThrows(IllegalArgumentException.class, () -> c.brake(2));
        assertThrows(IllegalArgumentException.class, () -> c.brake(-1));
    }

    @Test
    void move() {
        c.startEngine();
        c.move();
        c.move();
        c.move();
        c.move();
        assertEquals(0.4, c.getY());
    }

    @Test
    void turnLeft() {
        c.startEngine();
        c.turnLeft();
        assertEquals(Car.Direction.WEST, c.getTowards());
    }

    @Test
    void turnRight() {
        c.startEngine();
        c.turnRight();
        c.turnRight();
        assertEquals(Car.Direction.SOUTH, c.getTowards());
    }

    @Test
    void testSpeedFactor() {
        assertEquals(1.25, c.getSpeedFactor());
    }

    @Test
    void testIncrementSpeed() {
        c.startEngine();
        c.gas(1);
        c.gas(1);
        assertEquals(2.6, c.getCurrentSpeed());
    }

    @Test
    void testDecrementSpeed() {
        c.startEngine();
        c.brake(1);
        assertEquals(0, c.getCurrentSpeed());
    }
}