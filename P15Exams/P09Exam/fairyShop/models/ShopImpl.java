package P15Exams.P09Exam.fairyShop.models;

import java.util.ArrayList;
import java.util.List;

public class ShopImpl implements Shop {
    @Override
    public void craft(Present present, Helper helper) {

        List<Instrument> instruments = new ArrayList<>(helper.getInstruments());

        while (!present.isDone() && helper.canWork() && !instruments.isEmpty()) {

            Instrument instrument = instruments.get(0);
            if (!instrument.isBroken()) {
                helper.work();
                instrument.use();
                present.getCrafted();
            } else {
                instruments.remove(0);
            }
        }
    }
}



