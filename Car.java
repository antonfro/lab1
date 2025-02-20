import java.awt.*;

public abstract class Car extends Vehicle{
    protected Cartransport transport; // Vilken transport bilen är på.
    protected CarShop shop;

    public Car(int doors, Color colr, int engPow, String mdlName, double size) {
        super(doors, colr, engPow, mdlName, size);
    }


    public boolean isInCarShop() {
        return shop != null;
    }

    public boolean isOnTransport() {
        return transport != null;
    }

    @Override
    public void turnRight() {
        if (!isOnTransport() && !isInCarShop()) {
            super.turnRight();
        }
    }

    @Override
    public void turnLeft() {
        if (!isOnTransport() && !isInCarShop()) {
            super.turnLeft();
        }
    }

    @Override
    public void move() {
        if (!isOnTransport() && !isInCarShop()) {
            super.move();
        }
        else {
            throw new IllegalArgumentException("Bilen är på släp eller i verkstad.");
        }
    }

}