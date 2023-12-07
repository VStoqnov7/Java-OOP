package P15Exams.P01Exam.robotService.core;

import P15Exams.P01Exam.robotService.common.ConstantMessages;
import P15Exams.P01Exam.robotService.common.ExceptionMessages;
import P15Exams.P01Exam.robotService.entities.robot.FemaleRobot;
import P15Exams.P01Exam.robotService.entities.robot.MaleRobot;
import P15Exams.P01Exam.robotService.entities.robot.Robot;
import P15Exams.P01Exam.robotService.entities.services.MainService;
import P15Exams.P01Exam.robotService.entities.services.SecondaryService;
import P15Exams.P01Exam.robotService.entities.services.Service;
import P15Exams.P01Exam.robotService.entities.supplements.MetalArmor;
import P15Exams.P01Exam.robotService.entities.supplements.PlasticArmor;
import P15Exams.P01Exam.robotService.entities.supplements.Supplement;
import P15Exams.P01Exam.robotService.repositories.SupplementRepository;

import java.util.LinkedHashMap;
import java.util.Map;

import static P15Exams.P01Exam.robotService.common.ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_IN_SERVICE;

public class ControllerImpl implements Controller {

    private SupplementRepository supplements;
    private Map<String,Service> services;

    public ControllerImpl() {
        this.supplements = new SupplementRepository();
        this.services = new LinkedHashMap<>();
    }

    @Override
    public String addService(String type, String name) {

        Service service = null;
        if (type.equals("MainService")){
            service = new MainService(name);
        } else if (type.equals("SecondaryService")) {
            service = new SecondaryService(name);
        }else {
            throw new NullPointerException(ExceptionMessages.INVALID_SERVICE_TYPE);
        }
        services.put(name,service);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SERVICE_TYPE,type);
    }

    @Override
    public String addSupplement(String type) {
        Supplement supplement = null;
        if (type.equals("PlasticArmor")){
            supplement = new PlasticArmor();
        } else if (type.equals("MetalArmor")) {
            supplement = new MetalArmor();
        }else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_SUPPLEMENT_TYPE);
        }
        supplements.addSupplement(supplement);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE,type);
    }

    @Override
    public String supplementForService(String serviceName, String supplementType) {
        Supplement supplement = supplements.findFirst(supplementType);
        if (supplement == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_SUPPLEMENT_FOUND,supplementType));
        }

        Service service = this.services.get(serviceName);
        service.addSupplement(supplement);
        this.supplements.removeSupplement(supplement);

        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_IN_SERVICE, supplementType, serviceName);

    }

    @Override
    public String addRobot(String serviceName, String robotType, String robotName, String robotKind, double price) {

        Robot robot = null;
        if (robotType.equals("MaleRobot")){
            robot = new MaleRobot(robotName,robotKind,price);
        } else if (robotType.equals("FemaleRobot")) {
            robot = new FemaleRobot(robotName,robotKind,price);
        }else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_ROBOT_TYPE);
        }

        Service service = this.services.get(serviceName);

        String output;

        if (!isSuitableService(robotType, service)) {
            output = ConstantMessages.UNSUITABLE_SERVICE;
        } else {
            service.addRobot(robot);
            output = String.format(ConstantMessages.SUCCESSFULLY_ADDED_ROBOT_IN_SERVICE, robotType, serviceName);
        }

        return output;
    }

    private boolean isSuitableService(String robotType, Service service) {
        String serviceType = service.getClass().getSimpleName();

        if (robotType.equals("FemaleRobot") && serviceType.equals("SecondaryService")) {
            return true;
        } else if (robotType.equals("MaleRobot") && serviceType.equals("MainService")) {
            return true;
        }

        return false;
    }

    @Override
    public String feedingRobot(String serviceName) {
        Service service = services.get(serviceName);
        int sumFeedRobots = service.getRobots().size();
        service.feeding();
        return String.format(ConstantMessages.FEEDING_ROBOT,sumFeedRobots);
    }

    @Override
    public String sumOfAll(String serviceName) {

        Service service = this.services.get(serviceName);

        double robotPrices = service.getRobots().stream()
                .mapToDouble(Robot::getPrice).sum();
        double supplementPrices = service.getSupplements().stream()
                .mapToDouble(Supplement::getPrice).sum();

        return String.format(ConstantMessages.VALUE_SERVICE, serviceName, robotPrices + supplementPrices);
    }

    @Override
    public String getStatistics() {

        StringBuilder sb = new StringBuilder();

        for (Service value : services.values()) {
            sb.append(value.getStatistics());
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
