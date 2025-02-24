import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
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

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.vehicles.add(new Volvo240());
        cc.vehicles.add(new Scania());

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }
    // starts  all vehicles engines

    public void start() {
        for (Vehicle vehicle : vehicles) {
            vehicle.startEngine();
        }
    }
    // stops all vehicles engines
    public void stop() {
        for (Vehicle vehicle : vehicles) {
            vehicle.stopEngine();
        }
    }
    // truns off turbo if The vehicle is a Saab
    public void turboOff() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Saab95  ){ // ev typecast saab95 as new varible
              ((Saab95) vehicle).setTurboOff();
            }
        }
    }
    // truns on turbo if The vehicle is a Saab
    public void turboOn() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Saab95  ){ // ev typecast saab95 as new varible
                ((Saab95) vehicle).setTurboOn();
            }
        }
    }
    // lifts the bed for all truck types
    public void liftBed() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Scania ){
                vehicle.incrementTrailer(2);
            }
        }
    }
    // lowers the bed for all truck types
    public void lowerBed() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Scania ) {
                vehicle.decrementTrailer(2);
            } else if (vehicle instanceof Cartransport ) {
                ((Cartransport) vehicle).rampButton();
            }
        }
    }
    // Break for each car
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


    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle vehicle : vehicles) {
                vehicle.move();
                int x = (int) Math.round(vehicle.getX());
                int y = (int) Math.round(vehicle.getY());
                frame.drawPanel.moveit(x, y);
//                 repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }





}
