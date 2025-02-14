import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScaniaTest {

    Scania s;

    @BeforeEach
    void setup(){
        s = new Scania();
    }

    @Test
    void getX() {
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
    void getNrDoors() {
    }

    @Test
    void getEnginePower() {
    }

    @Test
    void getCurrentSpeed() {
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
        s.incrementTrailer(20);
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
    void incrementTrailer() {
        s.startEngine();
        s.incrementTrailer(30);
        assertEquals(0,s.getTrailerAngle());
        s.stopEngine();
        s.incrementTrailer(200);
        assertEquals(70, s.getTrailerAngle());
    }

    @Test
    void decrementTrailer() {
        s.incrementTrailer(40);
        s.decrementTrailer(20);
        assertEquals(20, s.getTrailerAngle());
        s.startEngine();
        s.decrementTrailer(10);
        assertEquals(20, s.getTrailerAngle());
    }
}