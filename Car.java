import java.awt.*;

public abstract class Car extends Vehicle{
    protected Cartransport transport;

    public Car(int doors, Color colr, int engPow, String mdlName, double size) {
        super(doors, colr, engPow, mdlName, size);
    }


/*    public boolean isOnTransport(Cartransport transport) {
        return transport.loadedCars.contains(this);
    }*/

// Måste veta om bil är på en transport ?
    @Override
    public void move() {
        if (transport == null) {
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
        else {
            throw new IllegalArgumentException("Bil är på en transport.");
        }
    }

}