import javax.swing.*;

public class Application {

    public static void main(String[] args) {
        int delay = 100;
        // The timer is started with a listener (see below) that executes the statements
        // each step between delays

        Model model = new Model();
        CarView view = new CarView("CarSim 1.0");
        CarController cc = new CarController(model, view);
        Timer timer = new Timer(delay, model);
        model.addListener(view);

        model.addVehicle(new Volvo240(), 0, 0);
        model.addVehicle(new Saab95(), 100, 200);
        model.addVehicle(new Scania(), 0, 400);
        model.addVehicle(new Saab95(), 400, 400);

//        model.setInitialPosition();
//
        // Start the timer
        timer.start();
    }


}
