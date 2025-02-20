import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarShopTest {

    Volvo240 c1;
    Volvo240 c2;
    Saab95 c3;
    Volvo240 c4;
    CarShop<Volvo240> VolvoShop;

    @BeforeEach
    void setup() {
        c1 = new Volvo240();
        c2 = new Volvo240();
        c3 = new Saab95();
        c4 = new Volvo240();
        VolvoShop = new CarShop<>(20) ;
    }

    @Test
    void printCars() {
        VolvoShop.insertCar(c1);
        VolvoShop.insertCar(c2);
        VolvoShop.insertCar(c4);
        VolvoShop.printCars();
    }

    @Test
    void removeCar() {
        VolvoShop.insertCar(c1);
        VolvoShop.insertCar(c2);
        VolvoShop.insertCar(c4);
        VolvoShop.printCars();
        VolvoShop.removeCar(c1);
        VolvoShop.printCars();
    }

    @Test
    void insertCar() {
        VolvoShop.insertCar(c1);
        VolvoShop.insertCar(c2);
        VolvoShop.insertCar(c4);
        // VolvoShop.insertCar(c3); Går ej, bilen är en Saab.
    }

    @Test
    void volvoShop() {
        VolvoShop.insertCar(c1);
    }

}