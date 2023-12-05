package P10PolymorphismExercises.P01Vehicles;

public class Truck extends Vehicle{

    private final static double SUMMER_FUEL_CONSUMPTION = 1.6;

    public Truck(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption + SUMMER_FUEL_CONSUMPTION);
    }

    @Override
    public void refuel(double fuel) {
        super.fuelQuantity += fuel * 0.95;
    }
}
