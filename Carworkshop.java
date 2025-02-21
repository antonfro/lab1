import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Carworkshop<T extends Car> {
    private final List<T> cars = new ArrayList<>();
    private final int capacity;

    public Carworkshop(int capacity) {
        this.capacity = capacity;
    }

    public void addCar(T Car) {
        if (cars.size() < capacity) {
            cars.add(Car);
        }
        else {
            throw new IllegalStateException("Verkstaden är full");
        }
    }

    public Optional<Car> retrieveCar() {
        if (!cars.isEmpty()) {
            return Optional.of(cars.removeFirst());
        }
        return Optional.empty();
    }
}
