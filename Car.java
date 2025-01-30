import java.awt.*;

public abstract class Car implements Movable{
    public int nrDoors;
    public double enginePower;
    public double currentSpeed;
    public Color color;
    public String modelName;
    public double x = 0;
    public double y = 0;
    public Direction towards = Direction.NORTH;

    public double getX(){return x;}

    public double getY(){return y;}

    public enum Direction{SOUTH, WEST, NORTH, EAST}

    public Direction getTowards(){return towards;}

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

    public void stopEngine(){ currentSpeed = 0;}

    public void decrementSpeed(double amount){}

    public void incrementSpeed(double amount){}

    public double speedFactor(){return 0;}

    public void gas(double amount){
        incrementSpeed(amount);
    }

    public void brake(double amount){
        decrementSpeed(amount);
    }

    @Override
    public void move() {
        switch (getTowards()) {
            case SOUTH:
                this.y -= getCurrentSpeed();
                break;
            case WEST:
                this.x -= getCurrentSpeed();
                break;
            case NORTH:
                this.y += getCurrentSpeed();
                break;
            case EAST:
                this.x += getCurrentSpeed();
                break;
        }
    }

    @Override
    public void turnLeft(){
        switch (getTowards()) {
            case SOUTH:
                this.towards = Direction.EAST;
                break;
            case WEST:
                this.towards = Direction.SOUTH;
                break;
            case NORTH:
                this.towards = Direction.WEST;
                break;
            case EAST:
                this.towards = Direction.NORTH;
                break;
        }
    }

    @Override
    public void turnRight(){
        switch (getTowards()) {
            case SOUTH:
                this.towards = Direction.WEST;
                break;
            case WEST:
                this.towards = Direction.NORTH;
                break;
            case NORTH:
                this.towards = Direction.EAST;
                break;
            case EAST:
                this.towards = Direction.SOUTH;
                break;
        }
    }



}