package P15Exams.P02Exam.vehicleShop.core;

import P15Exams.P02Exam.vehicleShop.common.ConstantMessages;
import P15Exams.P02Exam.vehicleShop.common.ExceptionMessages;
import P15Exams.P02Exam.vehicleShop.models.shop.Shop;
import P15Exams.P02Exam.vehicleShop.models.shop.ShopImpl;
import P15Exams.P02Exam.vehicleShop.models.tool.Tool;
import P15Exams.P02Exam.vehicleShop.models.tool.ToolImpl;
import P15Exams.P02Exam.vehicleShop.models.vehicle.Vehicle;
import P15Exams.P02Exam.vehicleShop.models.vehicle.VehicleImpl;
import P15Exams.P02Exam.vehicleShop.models.worker.FirstShift;
import P15Exams.P02Exam.vehicleShop.models.worker.SecondShift;
import P15Exams.P02Exam.vehicleShop.models.worker.Worker;
import P15Exams.P02Exam.vehicleShop.repositories.VehicleRepository;
import P15Exams.P02Exam.vehicleShop.repositories.WorkerRepository;

import java.util.List;
import java.util.stream.Collectors;

import static P15Exams.P02Exam.vehicleShop.common.ConstantMessages.COUNT_BROKEN_INSTRUMENTS;
import static P15Exams.P02Exam.vehicleShop.common.ConstantMessages.VEHICLE_DONE;

public class ControllerImpl implements Controller {

    private WorkerRepository workerRepository;
    private VehicleRepository vehicleRepository;
    private int countMadeVehicle;

    public ControllerImpl() {
        this.workerRepository = new WorkerRepository();
        this.vehicleRepository = new VehicleRepository();
        this.countMadeVehicle = 0;
    }

    @Override
    public String addWorker(String type, String workerName) {
        Worker worker = null;
        if (type.equals("FirstShift")){
            worker = new FirstShift(workerName);
        } else if (type.equals("SecondShift")) {
            worker = new SecondShift(workerName);
        }else {
            throw new IllegalArgumentException(ExceptionMessages.WORKER_TYPE_DOESNT_EXIST);
        }
        workerRepository.add(worker);
        return String.format(ConstantMessages.ADDED_WORKER,type,workerName);
    }

    @Override
    public String addVehicle(String vehicleName, int strengthRequired) {
        Vehicle vehicle = new VehicleImpl(vehicleName,strengthRequired);
        vehicleRepository.add(vehicle);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_VEHICLE,vehicleName);
    }

    @Override
    public String addToolToWorker(String workerName, int power) {
        Worker worker = workerRepository.findByName(workerName);
        if (worker == null){
            throw new IllegalArgumentException(ExceptionMessages.HELPER_DOESNT_EXIST);
        }

        Tool tool = new ToolImpl(power);

        worker.addTool(tool);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOOL_TO_WORKER,power,workerName);

    }

    @Override
    public String makingVehicle(String vehicleName) {
        List<Worker> workers = workerRepository.getWorkers().stream().filter(worker -> worker.getStrength() > 70).collect(Collectors.toList());

        if (workers.isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.NO_WORKER_READY);
        }
        Vehicle vehicle = vehicleRepository.findByName(vehicleName);

        Shop shop = new ShopImpl();
        int count = 0;
        while (!workers.isEmpty() && !vehicle.reached()) {
            Worker worker = workers.get(0);
            shop.make(vehicle, worker);

            if (!worker.canWork() || worker.getTools().stream().noneMatch(t -> !t.isUnfit())) {
                count += worker.getTools().stream().filter(Tool::isUnfit).count();
                workers.remove(worker);
                break;
            }
            count += worker.getTools().stream().filter(Tool::isUnfit).count();
        }

        String infoAboutVehicle = "not done";
        if (vehicle.reached()) {
            infoAboutVehicle = "done";
            countMadeVehicle++;
        }
        StringBuilder output = new StringBuilder();
        output.append(String.format(VEHICLE_DONE, vehicleName, infoAboutVehicle));
        output.append(String.format(COUNT_BROKEN_INSTRUMENTS, count));
        return output.toString().trim();
    }

    @Override
    public String statistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d vehicles are ready!",this.countMadeVehicle));
        sb.append(System.lineSeparator());
        sb.append("Info for workers:");
        sb.append(System.lineSeparator());
        workerRepository.getWorkers().stream()
                .forEach(worker -> sb.append(worker.toString()).append(System.lineSeparator()));
        return sb.toString().trim();

    }
}
