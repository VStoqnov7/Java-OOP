package P15Exams.P02Exam.vehicleShop.models.vehicle;

import P15Exams.P02Exam.vehicleShop.common.ExceptionMessages;

public class VehicleImpl implements Vehicle{

    private String name;
    private int strengthRequired;

    public VehicleImpl(String name, int strengthRequired) {
        this.setName(name);
        this.setStrengthRequired(strengthRequired);
    }

    protected void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.VEHICLE_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    protected void setStrengthRequired(int strengthRequired) {
        if (strengthRequired < 0){
            throw new IllegalArgumentException(ExceptionMessages.VEHICLE_STRENGTH_LESS_THAN_ZERO);
        }
        this.strengthRequired = strengthRequired;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getStrengthRequired() {
        return this.strengthRequired;
    }

    @Override
    public boolean reached() {
        return this.strengthRequired == 0;
    }

    @Override
    public void making() {
        this.setStrengthRequired(Math.max(0,this.getStrengthRequired() - 5));
    }
}
