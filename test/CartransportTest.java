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
    void getHaveCar() {
        Volvo240 b = new Volvo240();
        s.rampButton();
        s.loadCar(b);
        assertTrue(s.getHaveCar());
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
    void getColor() {
    }

    @Test
    void setColor() {
    }

    @Test
    void startEngine() {
    }

    @Test
    void stopEngine() {
    }

    @Test
    void speedFactor() {
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