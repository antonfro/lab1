public interface modelListener {

    void update(Integer regID, int x , int y );

    void addedCar(Integer regID, String model, int x, int y);
    void removedCar(Integer regID);

}
