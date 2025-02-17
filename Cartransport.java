import java.awt.*;
import java.util.Stack;
import java.lang.Math;

public class Cartransport extends Truck{
    private rampPos ramp = rampPos.UP;
    public Stack<Car> loadedCars = new Stack<>();
    private final int cargoSize = 10;
    private final double carProximity = 0.5;

    public Cartransport() {
        super(2, Color.magenta, 300, "Blictne", 120);
    }





    public boolean getHaveCar() {return !loadedCars.isEmpty();}

    public void unloadCar() {
        if (getRampPos() == rampPos.UP) {
            throw new IllegalArgumentException("Rampen är uppe.");
        }
        else if (loadedCars.empty()) {
            throw new IllegalArgumentException("Flaket är tomt.");
        }
        else {
            loadedCars.getLast().transport = null; // Funkar?
            loadedCars.pop();
        }
    }

    public void loadCar(Car b) {
        if (b.getSize() > 20) {
            throw new IllegalArgumentException("Bilen är för stor.");
        }
        else if (Math.abs(getX() - b.getX()) > carProximity && Math.abs(getY() - b.getY()) > carProximity) {
            throw new IllegalArgumentException("Bilen är för långt bort.");
        }
        else if (getRampPos() == rampPos.UP) {
            throw new IllegalArgumentException("Rampen är uppe.");
        }
        else if (loadedCars.size() < cargoSize) {
            b.transport = this; // "kopplar" Cartransport till bilen.
            b.towards = towards; // Bilen får samma riktning som Cartransport.
            loadedCars.add(b);
        }
        else {
            throw new IllegalArgumentException("Flaket är fullt.");
        }
    }

    @Override
    public boolean isTailOK() {
        return getRampPos() == rampPos.UP;
    }

    public rampPos getRampPos(){return ramp;}

    public enum rampPos{UP, DOWN}

    public void rampButton(){
        if (getCurrentSpeed() == 0) {
            switch (getRampPos()) {
                case UP:
                    ramp = rampPos.DOWN;
                    break;
                case DOWN:
                    ramp = rampPos.UP;
                    break;
            }
        }
        else {
            throw new IllegalArgumentException("Lastbilen rör sig");
        }
    }

    @Override
    public void move() {
        if (isTailOK()) {
            switch (getTowards()) {
                case SOUTH:
                    y -= getCurrentSpeed();
                    if (getHaveCar()) {
                        for (Car b : loadedCars) {
                            b.y -= getCurrentSpeed();
                        }
                    }
                    break;
                case WEST:
                    x -= getCurrentSpeed();
                    if (getHaveCar()) {
                        for (Car b : loadedCars) {
                            b.x -= getCurrentSpeed();
                        }
                    }
                    break;
                case NORTH:
                    y += getCurrentSpeed();
                    if (getHaveCar()) {
                        for (Car b : loadedCars) {
                            y += getCurrentSpeed();
                        }
                    }
                    break;
                case EAST:
                    x += getCurrentSpeed();
                    if (getHaveCar()) {
                        for (Car b : loadedCars) {
                            b.x += getCurrentSpeed();
                        }
                    }
                    break;
            }
        }
    }

}



