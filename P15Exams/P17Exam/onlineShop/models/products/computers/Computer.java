package P15Exams.P17Exam.onlineShop.models.products.computers;

import P15Exams.P17Exam.onlineShop.models.products.Product;
import P15Exams.P17Exam.onlineShop.models.products.components.Component;
import P15Exams.P17Exam.onlineShop.models.products.peripherals.Peripheral;

import java.util.List;

public interface Computer extends Product {
    List<Component> getComponents();

    List<Peripheral> getPeripherals();

    void addComponent(Component component);

    Component removeComponent(String componentType);

    void addPeripheral(Peripheral peripheral);

    Peripheral removePeripheral(String peripheralType);
}