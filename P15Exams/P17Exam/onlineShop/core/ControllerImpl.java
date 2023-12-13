package P15Exams.P17Exam.onlineShop.core;

import P15Exams.P17Exam.onlineShop.common.constants.ExceptionMessages;
import P15Exams.P17Exam.onlineShop.common.constants.OutputMessages;
import P15Exams.P17Exam.onlineShop.core.interfaces.Controller;
import P15Exams.P17Exam.onlineShop.models.products.components.*;
import P15Exams.P17Exam.onlineShop.models.products.computers.Computer;
import P15Exams.P17Exam.onlineShop.models.products.computers.DesktopComputer;
import P15Exams.P17Exam.onlineShop.models.products.computers.Laptop;
import P15Exams.P17Exam.onlineShop.models.products.peripherals.*;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {

    private List<Computer> computers;


    public ControllerImpl() {
        this.computers = new ArrayList<>();
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        if (computers.stream().filter(c -> c.getId() == id).findFirst().orElse(null) != null) {
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPUTER_ID);
        }

        Computer computer = null;
        switch (computerType){
            case "DesktopComputer":
                computer = new DesktopComputer(id,manufacturer,model,price);
                break;
            case "Laptop":
                computer = new Laptop(id,manufacturer,model,price);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPUTER_TYPE);
        }
        computers.add(computer);

        return String.format(OutputMessages.ADDED_COMPUTER,id);// XXXXXXXX
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model, double price, double overallPerformance, String connectionType) {

        Peripheral peripheral;
        switch (peripheralType) {
            case "Headset":
                peripheral = new Headset(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Keyboard":
                peripheral = new Keyboard(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Monitor":
                peripheral = new Monitor(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Mouse":
                peripheral = new Mouse(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_PERIPHERAL_TYPE);
        }

        Computer computer = computers.stream().filter(c -> c.getId() == computerId).findFirst().orElse(null);
        if (computer == null){
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
        if (computer.getPeripherals().stream().filter(c -> c.getId() == id).findFirst().orElse(null) != null) {
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_PERIPHERAL_ID);
        }
        computer.addPeripheral(peripheral);


        return String.format(OutputMessages.ADDED_PERIPHERAL,peripheralType,id,computerId);
    }
    

    @Override
    public String removePeripheral(String peripheralType, int computerId) {

        Computer computer = computers.stream().filter(c -> c.getId() == computerId).findFirst().orElse(null);

        if (computer == null){
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
        Peripheral removedPeripheral = computer.getPeripherals().stream().filter(component -> component.getClass().getSimpleName().equals(peripheralType)).findFirst().orElse(null);

        computer.removePeripheral(peripheralType);

        return String.format(OutputMessages.REMOVED_PERIPHERAL,peripheralType,removedPeripheral.getId());
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model, double price, double overallPerformance, int generation) {

        Component component;
        switch (componentType) {
            case "CentralProcessingUnit":
                component = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "Motherboard":
                component = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "PowerSupply":
                component = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "RandomAccessMemory":
                component = new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "SolidStateDrive":
                component = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "VideoCard":
                component = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPONENT_TYPE);
        }

        Computer computer = computers.stream().filter(c -> c.getId() == computerId).findFirst().orElse(null);
        if (computer == null){
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
        if (computer.getComponents().stream().filter(c -> c.getId() == id).findFirst().orElse(null) != null) {
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPONENT_ID);
        }
        computer.addComponent(component);

        return String.format(OutputMessages.ADDED_COMPONENT,componentType,id,computerId);
    }

    @Override
    public String removeComponent(String componentType, int computerId) {

        Computer computer = computers.stream().filter(c -> c.getId() == computerId).findFirst().orElse(null);
        if (computer == null){
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
        Component removedComponent = computer.getComponents().stream().filter(component -> component.getClass().getSimpleName().equals(componentType)).findFirst().orElse(null);
        computer.removeComponent(componentType);

        return String.format(OutputMessages.REMOVED_COMPONENT,componentType,removedComponent.getId());

    }

    @Override
    public String buyComputer(int id) {
        Computer computer = computers.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        if (computer == null){
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
        computers.remove(computer);



        return computer.toString();
    }

    @Override
    public String BuyBestComputer(double budget) {
        Computer bestComputer = computers.stream()
                .filter(c -> c.getPrice() <= budget)
                .max((c1, c2) -> Double.compare(c1.getOverallPerformance(), c2.getOverallPerformance()))
                .orElse(null);

        if (bestComputer == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAN_NOT_BUY_COMPUTER,budget));
        }

        computers.remove(bestComputer);
        return bestComputer.toString(); //XXXXXXXXXXX
    }

    @Override
    public String getComputerData(int id) {
        Computer computer = computers.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        if (computer == null){
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
        return computer.toString();
    }
}
