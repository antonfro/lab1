import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Model implements ActionListener {
    CarView cv;

    // The delay (ms) corresponds to 20 updates a sec (hz)
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    CarShop<Volvo240> volvoShop = new CarShop<>(5);
    private final Map<Vehicle, String> vehicleNames = new HashMap<>();

    public Model(CarView cv) {
        this.cv = cv;
    }

    void addVehicle (Vehicle vehicle, int startX, int startY) {
        String uniqueName = vehicle.getClass().getSimpleName() +  "_" + vehicles.size();
        vehicles.add(vehicle);
        vehicleNames.put(vehicle,uniqueName);
        cv.drawPanel.addCarImage(uniqueName, vehicle.getClass().getSimpleName(), startX, startY);
    }

    public void removeVehicle () {
        if (!vehicles.isEmpty()) {

            Vehicle lastVehicle = vehicles.removeLast();

            String uniqueName = lastVehicle.getClass().getSimpleName() + "_" + vehicles.size();

            cv.drawPanel.images.remove(uniqueName);
            cv.drawPanel.carPositions.remove(uniqueName);

            cv.drawPanel.repaint();
        }
    }

    boolean collision(Vehicle v) {
        if (v instanceof Volvo240) {
            if (v.getX() > 300 && v.getX() < 400 && v.getY() > 300 && v.getY() < 400) {
                volvoShop.insertCar((Volvo240) v);
                return true;
            }
        }
        return false;
    }

    void setInitialPosition() {
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle vehicle = vehicles.get(i);
            String uniqueName = vehicle.getClass().getSimpleName() + "_" + i;
            Point startPos = cv.drawPanel.carPositions.get(uniqueName);
            if (startPos != null) {
                vehicle.setPosition(startPos.x, startPos.y);
            }
        }
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

    // Calls the gas method for each car once
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

    public void actionPerformed(ActionEvent e) {
        ArrayList<Vehicle> removes = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            vehicle.move();
            int x = (int) Math.round(vehicle.getX());
            int y = (int) Math.round(vehicle.getY());
            String uniqueName = vehicleNames.get(vehicle);
            cv.drawPanel.moveit(uniqueName, x, y);

            if (collision(vehicle)) {
                removes.add(vehicle);
                cv.drawPanel.images.remove(uniqueName);
                cv.drawPanel.carPositions.remove(uniqueName);
            }
            if (x > 700 || y > 500 || x < 0 || y < 0) {
                vehicle.turnLeft();
                vehicle.turnLeft();
            }
            // repaint() calls the paintComponent method of the panel
        }
        for (Vehicle v : removes) {
            vehicles.remove(v);

        } cv.drawPanel.repaint();
    }
}
