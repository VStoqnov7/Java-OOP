package P10PolymorphismExercises.P02VehiclesExtension;

import java.text.DecimalFormat;

public abstract class Vehicle {
    protected double fuelQuantity;                //количество
    protected double fuelConsumption;            //Консумация
    protected double capacity;

    protected Vehicle(double fuelQuantity, double fuelConsumption, double capacity) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
        this.capacity = capacity;
    }

    public void drive(double distance) {

        double fuelNeeded = distance * this.fuelConsumption;
        if (this.fuelQuantity > fuelNeeded) {
            this.fuelQuantity -= distance * this.fuelConsumption;

            DecimalFormat df = new DecimalFormat("0.##");

            System.out.printf("%s travelled %s km%n", this.getClass().getSimpleName(), df.format(distance));
        } else {
            System.out.printf("%s needs refueling%n", this.getClass().getSimpleName());

        }

    }
    public void refuel(double fuel) {
        if (fuel <= 0){
            throw new IllegalArgumentException("Fuel must be a positive number");
        }

        if (this.fuelQuantity + fuel <= capacity) {
            this.fuelQuantity += fuel;

        }else {
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }
    }
    public double getFuelQuantity() {
        return fuelQuantity;
    }

    @Override
    public String toString() {

        DecimalFormat df = new DecimalFormat("##.00######");

        return String.format("%s: %.2f", this.getClass().getSimpleName(), this.fuelQuantity);
    }
}
