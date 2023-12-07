package P15Exams.P01Exam.robotService.entities.services;

import P15Exams.P01Exam.robotService.common.ConstantMessages;
import P15Exams.P01Exam.robotService.common.ExceptionMessages;
import P15Exams.P01Exam.robotService.entities.robot.Robot;
import P15Exams.P01Exam.robotService.entities.supplements.Supplement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseService implements Service {

    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Robot> robots;

    public BaseService(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.supplements = new ArrayList<>();
        this.robots = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {

        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.SERVICE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public Collection<Robot> getRobots() {
        return this.robots;
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return this.supplements;
    }

    @Override
    public void addRobot(Robot robot) {
        if (robots.size() < capacity) {
            robots.add(robot);
        } else {
            throw new IllegalArgumentException(ConstantMessages.NOT_ENOUGH_CAPACITY_FOR_ROBOT);
        }
    }

    @Override
    public void removeRobot(Robot robot) {

        robots.remove(robot);
    }

    @Override
    public void addSupplement(Supplement supplement) {
        supplements.add(supplement);
    }

    @Override
    public void feeding() {
        robots.forEach(Robot::eating);
    }

    @Override
    public int sumHardness() {

        int sum = supplements.stream().mapToInt(Supplement::getHardness).sum();
        return sum;
    }

    @Override
    public String getStatistics() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s:", this.name, this.getClass().getSimpleName()));
        sb.append(System.lineSeparator());
        sb.append("Robots: ");
        if (robots.isEmpty()) {
            sb.append("none");
        } else {
            sb.append(robots.stream()
                    .map(Robot::getName)
                    .collect(Collectors.joining(" ")));
        }
        sb.append(System.lineSeparator());
        sb.append(String.format("Supplements: %d Hardness: %d", supplements.size(), sumHardness()));


        return sb.toString().trim();
    }
}
