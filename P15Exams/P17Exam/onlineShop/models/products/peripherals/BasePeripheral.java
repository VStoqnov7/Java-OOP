package P15Exams.P17Exam.onlineShop.models.products.peripherals;

import P15Exams.P17Exam.onlineShop.models.products.BaseProduct;

public abstract class BasePeripheral extends BaseProduct implements Peripheral{

    private String connectionType;


    public BasePeripheral(int id, String manufacturer, String model, double price, double overallPerformance,String connectionType) {
        super(id, manufacturer, model, price, overallPerformance);
        this.connectionType = connectionType;
    }

    @Override
    public String getConnectionType() {
        return this.connectionType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(String.format(" Type: %s",this.connectionType));
        return sb.toString().trim();
    }
}
