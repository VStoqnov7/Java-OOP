package P10PolymorphismExercises.P02VehiclesExtension;


public class Bus extends Vehicle {

    public final static double NOT_EMPTY = 1.4;
    private boolean hasPeople;

    protected Bus(double fuelQuantity, double fuelConsumption, double capacity) {
        super(fuelQuantity, fuelConsumption, capacity);
        this.hasPeople = false;

    }

    public void driveWhitPeople(double distance) {
        if (hasPeople) {
            fuelConsumption += NOT_EMPTY;
        }
        drive(distance);
        fuelConsumption -= NOT_EMPTY;
        this.setHasPeople(false);
    }
    public void setHasPeople(boolean hasPeople) {
        this.hasPeople = hasPeople;
    }

}
