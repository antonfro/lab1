import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartransportTest {

    Cartransport s;

    @BeforeEach
    void setup(){
        s = new Cartransport();
    }

    @Test
    void getRampPos() {
        s.startEngine();
        assertThrows(IllegalArgumentException.class, () -> s.rampButton());
        s.stopEngine();
        s.rampButton();
        assertEquals(Cartransport.rampPos.DOWN, s.getRampPos());
    }

    @Test
    void hasCar() {
        Volvo240 b = new Volvo240();
        s.rampButton();
        s.loadCar(b);
        assertTrue(s.hasCar());
    }

    @Test
    void getY() {
    }

    @Test
    void getTowards() {
    }

    @Test
    void getSpeedFactor() {
    }

    @Test
    void isTailOK() {
        assertTrue(s.isTailOK());
        s.rampButton();
        assertFalse(s.isTailOK());
    }

    @Test
    void loadCar() {
        Volvo240 b = new Volvo240();
        Saab95 b2 = new Saab95();
        Volvo240 b3 = new Volvo240();
        s.rampButton();
        b.startEngine();
        b.move();
        b.move();
        b.move();
        b.move();
        s.loadCar(b);
        assertEquals(b, s.loadedCars.getFirst());
        s.loadCar(b2);
        assertEquals(b, s.loadedCars.getFirst());
        s.loadCar(b3);
        assertEquals(b3, s.loadedCars.getLast());
    }

    @Test
    void unloadCar() {
        assertThrows(IllegalArgumentException.class, () -> s.unloadCar());
        Volvo240 b = new Volvo240();
        Saab95 b2 = new Saab95();
        Volvo240 b3 = new Volvo240();
        s.rampButton();
        s.loadCar(b);
        s.loadCar(b2);
        s.loadCar(b3);
        s.unloadCar();
        assertEquals(b2, s.loadedCars.getLast());
    }

    @Test
    void turnLeftWithCar() {
        Volvo240 b = new Volvo240();
        s.rampButton();
        s.loadCar(b);
        s.rampButton();
        assertTrue(s.hasCar());
        s.turnLeft();
        assertEquals(Vehicle.Direction.WEST, s.getTowards());
        assertEquals(Vehicle.Direction.WEST, b.getTowards());
        b.turnLeft();
        assertEquals(Vehicle.Direction.WEST, b.getTowards());
    }

    @Test
    void turnRightWithCar() {
        Volvo240 b = new Volvo240();
        s.rampButton();
        s.loadCar(b);
        s.rampButton();
        assertTrue(s.hasCar());
        s.turnRight();
        assertEquals(Vehicle.Direction.EAST, s.getTowards());
        assertEquals(Vehicle.Direction.EAST, b.getTowards());
        b.turnLeft();
        assertEquals(Vehicle.Direction.EAST, b.getTowards());
    }

    @Test
    void startEngine() {
    }

    @Test
    void stopEngine() {
    }

    @Test
    void moveWithCars() {
        Volvo240 b = new Volvo240();
        s.rampButton();
        s.loadCar(b);
        s.rampButton();
        assertTrue(s.hasCar());
        s.startEngine();
        assertEquals(0.0, b.getY());
        s.move();
        s.move();
        s.move();
        s.move();
        assertEquals(0.4, b.getY());
        assertThrows(IllegalArgumentException.class, b::move);
    }

    @Test
    void gas() {
    }

    @Test
    void brake() {
    }

    @Test
    void move() {
        s.startEngine();
        s.move();
        s.move();
        assertEquals(0.2, s.getY());
        s.stopEngine();
        s.move();
        s.move();
        assertEquals(0.2, s.getY());
    }

    @Test
    void turnLeft() {
    }

    @Test
    void turnRight() {
    }

    @Test
    void rampButton() {
        assertEquals(Cartransport.rampPos.UP, s.getRampPos());
        s.rampButton();
        assertEquals(Cartransport.rampPos.DOWN, s.getRampPos());
    }

}