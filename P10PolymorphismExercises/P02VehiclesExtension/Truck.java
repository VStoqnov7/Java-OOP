package P10PolymorphismExercises.P02VehiclesExtension;

public class Truck extends Vehicle {

    private final static double SUMMER_FUEL_CONSUMPTION = 1.6;

    public Truck(double fuelQuantity, double fuelConsumption,double capacity) {
        super(fuelQuantity, fuelConsumption + SUMMER_FUEL_CONSUMPTION,capacity);
    }

    @Override
    public void refuel(double fuel) {
        if (fuel <= 0){
            throw new IllegalArgumentException("Fuel must be a positive number");
        }

        if (this.fuelQuantity + fuel <= capacity) {
        super.fuelQuantity += fuel * 0.95;


        }else {
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }
    }
}
