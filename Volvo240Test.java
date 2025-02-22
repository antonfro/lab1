import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Volvo240Test {

    Car c;

    @BeforeEach
    void Setup() {
        c = new Volvo240();
    }

    @Test
    void getX() {
        assertEquals(0,c.getX());
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
        assertEquals(4, c.getNrDoors());
    }

    @Test
    void getEnginePower() {
        assertEquals(100, c.getEnginePower());
    }

    @Test
    void getCurrentSpeed() {
        c.startEngine();
        assertEquals(0.1, c.getCurrentSpeed());
    }

    @Test
    void getColor() {
        assertEquals(Color.black, c.getColor());
    }

    @Test
    void setColor() {
        c.color = Color.blue;
        assertEquals(Color.blue, c.getColor());
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
        c.decrementSpeed(1);
        assertEquals(0, c.getCurrentSpeed());
    }

    @Test
    void incrementSpeed() {
        c.startEngine();
        c.incrementSpeed(1);
        assertEquals(1.35, c.getCurrentSpeed());
    }

    @Test
    void speedFactor() {
        c.startEngine();
        assertEquals(100, c.getEnginePower());
    }

    @Test
    void gas() {
        c.startEngine();
        c.gas(1);
        assertEquals(1.35, c.getCurrentSpeed());
    }

    @Test
    void brake() {
        c.startEngine();
        c.brake(1);
        assertEquals(0, c.getCurrentSpeed());
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
        assertEquals(1.25, c.speedFactor());
    }

    @Test
    void testIncrementSpeed() {
        c.startEngine();
        c.incrementSpeed(1);
        c.incrementSpeed(1);
        assertEquals(2.6, c.getCurrentSpeed());
    }

    @Test
    void testDecrementSpeed() {
        c.startEngine();
        c.decrementSpeed(1);
        assertEquals(0, c.getCurrentSpeed());
    }
}