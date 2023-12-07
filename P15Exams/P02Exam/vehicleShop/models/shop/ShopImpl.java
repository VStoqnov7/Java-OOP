package P15Exams.P02Exam.vehicleShop.models.shop;

import P15Exams.P02Exam.vehicleShop.models.tool.Tool;
import P15Exams.P02Exam.vehicleShop.models.vehicle.Vehicle;
import P15Exams.P02Exam.vehicleShop.models.worker.Worker;

import java.util.Collection;

public class ShopImpl implements Shop{

    @Override
    public void make(Vehicle vehicle, Worker worker) {
        Collection<Tool> tools = worker.getTools();
        while (worker.canWork() && !vehicle.reached() && tools.iterator().hasNext()) {
            Tool currentTool = tools.iterator().next();
            currentTool.decreasesPower();
            worker.working();
            vehicle.making();
            if (currentTool.isUnfit()) {
                currentTool = tools.iterator().next();
            }
        }
    }
}
