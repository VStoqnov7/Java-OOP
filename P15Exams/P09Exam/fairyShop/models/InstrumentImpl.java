package P15Exams.P09Exam.fairyShop.models;

import P15Exams.P09Exam.fairyShop.common.ExceptionMessages;

public class InstrumentImpl implements Instrument{

    private int power;

    public InstrumentImpl(int power) {
        this.setPower(power);
    }

    private void setPower(int power) {
        if (power < 0){
            throw new IllegalArgumentException(ExceptionMessages.INSTRUMENT_POWER_LESS_THAN_ZERO);
        }
        this.power = power;
    }

    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public void use() {
        this.setPower(Math.max(0,this.getPower() - 10));
    }

    @Override
    public boolean isBroken() {
        return this.power == 0;
    }
}
