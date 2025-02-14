import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class Cartransport extends Truck{
    private rampPos ramp = rampPos.UP;
    Stack<Car> loadedCars = new Stack<>();
    private final int cargoSize = 10;

    public Cartransport() {
        super(2, Color.magenta, 300, "Blictne");
    }




    public void unloadCar() {
        if (getRampPos() == rampPos.UP) {

        }
        if (loadedCars.empty()) {
            throw new IllegalArgumentException("Flaket är tomt.");
        }
        else {
            loadedCars.pop();
        }
    }

    public void loadCar(Car c) {
        if (loadedCars.size() <= cargoSize) {
            loadedCars.add(c);
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

}
