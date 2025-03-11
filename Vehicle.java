import java.awt.*;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Vehicle implements Movable{

    private static final AtomicInteger counter = new AtomicInteger(0);

    private final int regId = counter.incrementAndGet();

    private int nrDoors;
    private Color color;
    private int enginePower;
    protected String modelName;
    protected double currentSpeed;
    protected double x;
    protected double y;
    public Direction towards = Direction.NORTH;
    private double size;

    public Vehicle(int doors, Color colr, int engPow, String mdlName, double size) {
        this.nrDoors = doors;
        this.enginePower = engPow;
        this.color = colr;
        this.modelName = mdlName;
        this.size = size;
        stopEngine();
    }

    public int getRegId() {
        return regId;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public double getSize() {return size;}

    public double getX(){return x;}

    public double getY(){return y;}

    public enum Direction{SOUTH, WEST, NORTH, EAST}

    public Direction getTowards(){return towards;}

    public double getSpeedFactor(){return speedFactor();}

    public int getNrDoors(){
        return nrDoors;
    }

    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void incrementTrailer(int amount) {}

    public void decrementTrailer(int amount) {}

    public void stopEngine(){ currentSpeed = 0;}

    private void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - getSpeedFactor() * amount, 0);
    }

    protected void incrementSpeed(double amount) {
        currentSpeed = Math.min(getCurrentSpeed() + getSpeedFactor() * amount, getEnginePower());
    }

    protected double speedFactor(){
        return getEnginePower() * 0.01;
    }

    public void gas(double amount){
        if (amount >= 0 && amount <= 1)
            incrementSpeed(amount);
        else {
            throw new IllegalArgumentException("Värdet ligger inte mellan 0-1");
        }
    }

    public void brake(double amount) {
        if (amount >= 0 && amount <= 1) {
            decrementSpeed(amount);
        }
        else {
            throw new IllegalArgumentException("Värdet ligger inte mellan 0-1");
        }
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

    @Override
   public void turnLeft(){
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

    @Override
    public void turnRight(){
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return regId == vehicle.regId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(regId);
    }
}
