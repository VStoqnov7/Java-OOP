package P15Exams.P02Exam.vehicleShop.repositories;

import P15Exams.P02Exam.vehicleShop.models.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class VehicleRepository implements Repository<Vehicle>{
    private Collection<Vehicle> vehicles;

    public VehicleRepository() {
        this.vehicles = new ArrayList<>();
    }

    @Override
    public Collection<Vehicle> getWorkers() {
        return Collections.unmodifiableCollection(this.vehicles);
    }

    @Override
    public void add(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    @Override
    public boolean remove(Vehicle vehicle) {
        if (vehicles.contains(vehicle)){
            vehicles.remove(vehicle);
            return true;
        }
        return false;
    }

    @Override
    public Vehicle findByName(String name) {
        return vehicles.stream().filter(vehicle -> vehicle.getName().equals(name)).findFirst().orElse(null);
    }
}
