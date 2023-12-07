package P15Exams.P02Exam.vehicleShop.models.tool;

import P15Exams.P02Exam.vehicleShop.common.ExceptionMessages;

public class ToolImpl implements Tool{

    private int power;

    public ToolImpl(int power) {
        this.setPower(power);
    }

    protected void setPower(int power) {
        if (power < 0){
            throw new IllegalArgumentException(ExceptionMessages.TOOL_POWER_LESS_THAN_ZERO);
        }
        this.power = power;
    }

    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public void decreasesPower() {
        this.setPower(Math.max(0,this.getPower() - 5));
    }

    @Override
    public boolean isUnfit() {
        return this.power == 0;
    }
}
