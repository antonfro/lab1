import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class Model implements ActionListener {
    ArrayList<modelListener> Listeners = new ArrayList<>();
    // The delay (ms) corresponds to 20 updates a sec (hz)
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    CarShop<Volvo240> volvoShop = new CarShop<>(5);

    public Model(){}

    void addListener (modelListener L){
        Listeners.add(L);
    }

    void notifyListener(Integer regID, int x , int y ){
        for (modelListener l : Listeners ){
            l.update( regID,  x ,  y );
        }
    }

    void notifyCarAdded (Integer regID, String model, int x, int y){
        for (modelListener l : Listeners){
            l.addedCar(regID, model, x, y);
        }
    }

    void notifyCarRemoved( Integer regID) {
        for (modelListener l : Listeners) {
            l.removedCar(regID);
        }
    }

    void addVehicle (Vehicle vehicle, int startX, int startY) {
        vehicles.add(vehicle);
        notifyCarAdded(vehicle.getRegId(), vehicle.getModelName(),startX, startY);

    }

    public void removeVehicle () {
        if (!vehicles.isEmpty()) {
            Vehicle lastVehicle = vehicles.removeLast();
            Integer removedID = lastVehicle.getRegId();
            notifyCarRemoved(removedID);
        }
    }

    boolean inBoundsWorkShop(Vehicle v) {
        if (v instanceof Volvo240) {
            if (v.getX() > 300 && v.getX() < 400 && v.getY() > 300 && v.getY() < 400) {
                volvoShop.insertCar((Volvo240) v);
                return true;
            }
        }
        return false;
    }

    boolean wallCollision(Vehicle v) {
        double x = v.getX();
        double y = v.getY();

        if (x > 700){
            v.x = 700;
            return true;
        }else if (y > 500) {
            v.y = 500;
            return true;
        }else if (x < 0 ){
            v.x = 0;
            return true;

        }else if (y < 0){
            v.y = 0;
            return true;

        }
    return false;
    }

    void start() {
        for (Vehicle vehicle : vehicles) {
            vehicle.startEngine();
        }
    }

    void stop() {
        for (Vehicle vehicle : vehicles) {
            vehicle.stopEngine();
        }
    }

    void turboOff() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Saab95){
                ((Saab95) vehicle).setTurboOff();
            }
        }
    }

    void turboOn() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Saab95) {
                ((Saab95) vehicle).setTurboOn();
            }
        }
    }

    void liftBed() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Scania scania){
                scania.incrementTrailer(10);
            }

        }
    }

    void lowerBed() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Scania scania) {
                scania.decrementTrailer(10);
            }

        }
    }

    void brake() {
        for (Vehicle vehicle : vehicles) {
            vehicle.brake(0.5);
        }
    }

    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle vehicle : vehicles) {
            vehicle.gas(gas);
        }
    }

    void turnLeft() {
        for (Vehicle vehicle : vehicles) {
            vehicle.turnLeft();
        }
    }

    void turnRight() {
        for (Vehicle vehicle : vehicles) {
            vehicle.turnRight();
        }
    }

    /** This is called from Timer every 50 millis. Timer initially set in Application **/
    public synchronized void actionPerformed(ActionEvent e) {
        Iterator<Vehicle> iterator = vehicles.iterator();
        while (iterator.hasNext()) {

            Vehicle vehicle = iterator.next();

            vehicle.move();

            int x = (int) Math.round(vehicle.getX());
            int y = (int) Math.round(vehicle.getY());

            if (inBoundsWorkShop(vehicle)) {
                iterator.remove();
            } else if (wallCollision(vehicle)) {
                vehicle.stopEngine();
                vehicle.turnLeft();
                vehicle.turnLeft();
                vehicle.startEngine();
            }
            notifyListener(vehicle.getRegId(), x, y );
        }
    }
}
