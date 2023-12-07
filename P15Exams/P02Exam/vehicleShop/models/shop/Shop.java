package P15Exams.P02Exam.vehicleShop.models.shop;

import P15Exams.P02Exam.vehicleShop.models.vehicle.Vehicle;
import P15Exams.P02Exam.vehicleShop.models.worker.Worker;

public interface Shop {
    void make(Vehicle vehicle, Worker worker);
}
