package P15Exams.P17Exam.onlineShop.models.products.computers;

import P15Exams.P17Exam.onlineShop.common.constants.ExceptionMessages;
import P15Exams.P17Exam.onlineShop.common.constants.OutputMessages;
import P15Exams.P17Exam.onlineShop.models.products.BaseProduct;
import P15Exams.P17Exam.onlineShop.models.products.Product;
import P15Exams.P17Exam.onlineShop.models.products.components.Component;
import P15Exams.P17Exam.onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseComputer extends BaseProduct implements Computer {

    private List<Component> components;
    private List<Peripheral> peripherals;



    public BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return this.peripherals;
    }

    @Override
    public void addComponent(Component component) {
        if (components.stream().anyMatch(c -> c.getClass().equals(component.getClass()))) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_COMPONENT,component.getClass().getSimpleName(),this.getClass().getSimpleName(),super.getId()));

        }
        components.add(component);
    }

    @Override
    public Component removeComponent(String componentType) {
        Component componentToRemove = null;
        for (Component component : components) {
            if (component.getClass().getSimpleName().equals(componentType)) {
                componentToRemove = component;
            }
        }
        if (componentToRemove == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_COMPONENT,componentType,this.getClass().getSimpleName(),super.getId()));
        }else {
            components.remove(componentToRemove);
        }
        return componentToRemove;
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        if (peripherals.stream().anyMatch(c -> c.getClass().equals(peripheral.getClass()))) {

            throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_PERIPHERAL,peripheral.getClass().getSimpleName(),this.getClass().getSimpleName(),super.getId()));

        }
        peripherals.add(peripheral);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        Peripheral peripheralToRemove = null;
        for (Peripheral peripheral : peripherals) {
            if (peripheral.getClass().getSimpleName().equals(peripheralType)) {
                peripheralToRemove = peripheral;
            }
        }
        if (peripheralToRemove == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_PERIPHERAL,peripheralType,this.getClass().getSimpleName(),super.getId()));
        }else {
            peripherals.remove(peripheralToRemove);
        }

        return peripheralToRemove;
    }


    @Override
    public double getOverallPerformance() {
        if (components.isEmpty()) {
            return super.getOverallPerformance();
        } else {
            double sum = components.stream().mapToDouble(Component::getOverallPerformance).sum();
            return super.getOverallPerformance() + sum / components.size();
        }
    }

    @Override
    public double getPrice() {
        double total = super.getPrice();
        for (Component component : components) {
            total += component.getPrice();
        }
        for (Peripheral peripheral : peripherals) {
            total += peripheral.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        double sum1 = components.stream().mapToDouble(Component::getPrice).sum();
        double sum2 = peripherals.stream().mapToDouble(Peripheral::getPrice).sum();

        sb.append(String.format(OutputMessages.PRODUCT_TO_STRING,this.getOverallPerformance(),super.getPrice() + sum1 + sum2,this.getClass().getSimpleName()
                ,super.getManufacturer(),super.getModel(),super.getId()));
        sb.append(System.lineSeparator());
        sb.append(" ").append(String.format(OutputMessages.COMPUTER_COMPONENTS_TO_STRING,this.components.size()));
        for (Component component : components) {
            sb.append(System.lineSeparator());
            sb.append("  ").append(component.toString());
        }

        double avgOverallPerformance = peripherals.stream()
                .mapToDouble(Product::getOverallPerformance)
                .sum();

        sb.append(System.lineSeparator());
        sb.append(" ").append(String.format(OutputMessages.COMPUTER_PERIPHERALS_TO_STRING,this.peripherals.size(),avgOverallPerformance));

        for (Peripheral peripheral : peripherals) {
            sb.append(System.lineSeparator());
            sb.append("  ").append(peripheral.toString());
        }

        return sb.toString().trim();



    }
}
