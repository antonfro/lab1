import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/*
* This class represents the Controller part in the MVC pattern.
* Its responsibilities are to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController extends JFrame{
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    CarShop<Volvo240> volvoShop = new CarShop<>(5);


    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();
        cc.frame = new CarView("CarSim 1.0", cc);

        cc.addVehicle(new Volvo240(), 0, 0);
        cc.addVehicle(new Saab95(), 0, 100);
        cc.addVehicle(new Scania(), 0, 200);
        cc.addVehicle(new Volvo240(), 200, 200);
        cc.addVehicle(new Scania(), 300, 300);

        // Start a new view and send a reference of self

        cc.setInitialPosition();

        // Start the timer
        cc.timer.start();
    }

    public void addVehicle (Vehicle vehicle, int startX, int startY) {
        String uniqueName = vehicle.getClass().getSimpleName() +  "_" + vehicles.size();
        vehicles.add(vehicle);
        frame.drawPanel.addCarImage(uniqueName, vehicle.getClass().getSimpleName(), startX, startY);
    }

    public void removeVehicle (Vehicle Volvo240) {
        if (vehicles.getLast() == Volvo240)
            vehicles.removeLast();
    }

    private boolean collision(Vehicle v) {
        if (v instanceof Volvo240) {
            if (v.getX() > 300 && v.getX() < 400 && v.getY() > 300 && v.getY() < 400) {
                volvoShop.insertCar((Volvo240) v);
                return true;
            }
        }
        return false;
    }


    private void setInitialPosition() {
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle vehicle = vehicles.get(i);
            String uniqueName = vehicle.getClass().getSimpleName() + "_" + i;
            Point startPos = frame.drawPanel.carPositions.get(uniqueName);
            if (startPos != null) {
                vehicle.setPosition(startPos.x, startPos.y);
            }
        }
    }

    public void start() {
        for (Vehicle vehicle : vehicles) {
            vehicle.startEngine();
        }
    }

    public void stop() {
        for (Vehicle vehicle : vehicles) {
            vehicle.stopEngine();
        }
    }

    public void turboOff() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Saab95){
                ((Saab95) vehicle).setTurboOff();
            }
        }
    }

    public void turboOn() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Saab95) {
                ((Saab95) vehicle).setTurboOn();
            }
        }
    }

    public void liftBed() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Scania scania){
                scania.incrementTrailer(10);
            }

        }
    }

    public void lowerBed() {
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

    public void turnLeft() {
        for (Vehicle vehicle : vehicles) {
            vehicle.turnLeft();
        }
    }

    public void turnRight() {
        for (Vehicle vehicle : vehicles) {
            vehicle.turnRight();
        }
    }



    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ArrayList<Vehicle> removes = new ArrayList<>();
            for (int i = 0; i < vehicles.size(); i++) {
                Vehicle vehicle = vehicles.get(i);
                vehicle.move();
                int x = (int) Math.round(vehicle.getX());
                int y = (int) Math.round(vehicle.getY());
                String uniqueName = vehicle.getClass().getSimpleName() + "_" + i;
                frame.drawPanel.moveit(uniqueName, x, y);
                frame.drawPanel.repaint();
                if (collision(vehicle)) {
                    removes.add(vehicle);
                }
                    if (x > 700 || y > 500 || x < 0 || y < 0) {
                        vehicle.turnLeft();
                        vehicle.turnLeft();
                }
                  // repaint() calls the paintComponent method of the panel
            }
            for (Vehicle v : removes) {
                vehicles.remove(v);
            }
        }
    }
}
