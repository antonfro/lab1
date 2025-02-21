import java.util.ArrayList;

public class CarShop<T extends Car> {
    private int capacity;
    private ArrayList<T> cars = new ArrayList<>(capacity);

    public CarShop(int capac) {
        //this.cars = cars;
        this.capacity = capac;
    }

    public void printCars() { // Behövs ej men kul.
        int place = 1;
        for (T b : cars) {
            System.out.println("Plats " + place + ": " + b.modelName);
            place += 1;
        }
    }

    public void removeCar(T b) {
        if (cars.contains(b)) {
            System.out.println("Bil hämtad, modell: " + b.modelName);
            cars.remove(b);
            b.shop = null;
            System.out.println("Lediga platser: " + (capacity-cars.size()));
        }
        else {
            throw new IllegalArgumentException("Bilen finns ej i verkstaden.");
        }
    }

    public void insertCar(T b) {
        if (cars.size() >= capacity) {
            throw new IllegalArgumentException("Verkstaden är full.");
        }
        else if (cars.contains(b)) {
            throw new IllegalArgumentException("Bilen är redan i verkstaden.");
        }
        else if (cars.size() < capacity) { // ?
            b.shop = this;
            cars.add(b);
        }
    }

}
