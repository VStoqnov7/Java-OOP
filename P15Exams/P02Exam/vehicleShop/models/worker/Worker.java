package P15Exams.P02Exam.vehicleShop.models.worker;

import P15Exams.P02Exam.vehicleShop.models.tool.Tool;

import java.util.Collection;

public interface Worker {
    void working();

    void addTool(Tool tool);

    boolean canWork();

    String getName();

    int getStrength();

    Collection<Tool> getTools();
}
