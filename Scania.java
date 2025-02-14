import java.awt.*;

public class Scania extends Truck{
    private int trailerAngle;

    public Scania() {
        super(2, Color.red, 250, "Scania");
        trailerAngle = 0;
    }




    @Override
    public boolean isTailOK() {
        return getTrailerAngle() == 0;
    }

    public int getTrailerAngle() {return trailerAngle;}


    public void incrementTrailer(int amount) {
        if (getCurrentSpeed() == 0 && amount >= 0) {
            if (getTrailerAngle() + amount > 70) {
                trailerAngle = 70;
            }
            else {
                trailerAngle += amount;
            }
        }
    }


    public void decrementTrailer(int amount) {
        if (getCurrentSpeed() == 0 && amount >= 0) {
            if (getTrailerAngle() - amount < 0) {
                trailerAngle = 0;
            }
            else {
                trailerAngle -= amount;
            }
        }
    }

}
