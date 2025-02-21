import java.awt.*;

public abstract class Truck extends Vehicle {

    public Truck(int doors, Color colr, int engPow, String mdlName, double size) {
        super(doors, colr, engPow, mdlName, size);
    }


    public boolean isTailOK() {return true;}; // Kollar om det är ok att köra.

    @Override
    public void turnRight(){
        if (isTailOK()) {
            switch (getTowards()) {
                case SOUTH:
                    towards = Direction.WEST;
                    break;
                case WEST:
                    towards = Direction.NORTH;
                    break;
                case NORTH:
                    towards = Direction.EAST;
                    break;
                case EAST:
                    towards = Direction.SOUTH;
                    break;
            }
        }
    }

    @Override
    public void turnLeft(){
        if (isTailOK()) {
            switch (getTowards()) {
                case SOUTH:
                    towards = Direction.EAST;
                    break;
                case WEST:
                    towards = Direction.SOUTH;
                    break;
                case NORTH:
                    towards = Direction.WEST;
                    break;
                case EAST:
                    towards = Direction.NORTH;
                    break;
            }
        }
    }

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
