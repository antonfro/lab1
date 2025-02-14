import java.awt.*;

public abstract class Car extends Vehicle{


    public Car(int doors, Color colr, int engPow, String mdlName) {
        super(doors, colr, engPow, mdlName);
    }

    @Override
    public void move() {
        switch (getTowards()) {
            case SOUTH:
                y -= getCurrentSpeed();
                break;
            case WEST:
                x -= getCurrentSpeed();
                break;
            case NORTH:
                y += getCurrentSpeed();
                break;
            case EAST:
                x += getCurrentSpeed();
                break;
        }
    }

}