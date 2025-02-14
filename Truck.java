import java.awt.*;

public abstract class Truck extends Vehicle {

    public Truck(int doors, Color colr, int engPow, String mdlName) {
        super(doors, colr, engPow, mdlName);
    }





    public boolean isTailOK() {return true;}; // Kollar om det är ok att köra.

    @Override
    public void move() { // Annan move än vad bilar behöver.
        if (isTailOK()) {
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

}
