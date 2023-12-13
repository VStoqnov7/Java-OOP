package P15Exams.P16Exam.easterRaces.entities.cars;

import P15Exams.P16Exam.easterRaces.common.ExceptionMessages;

public abstract class BaseCar implements Car{

    private String model;
    private int horsePower;
    private double cubicCentimeters;

    public BaseCar(String model, int horsePower, double cubicCentimeters) {
        this.setModel(model);
        this.setHorsePower(horsePower);
        this.cubicCentimeters = cubicCentimeters;
    }

    protected void setModel(String model) {
        if (model == null || model.trim().isEmpty() || model.length() < 4){
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_MODEL, model,4));
        }
        this.model = model;
    }

    protected abstract void checkHorsepower(int horsePower);

    protected void setHorsePower(int horsePower) {
        this.checkHorsepower(horsePower);
        this.horsePower = horsePower;
    }


    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public int getHorsePower() {
        return this.horsePower;
    }

    @Override
    public double getCubicCentimeters() {
        return this.cubicCentimeters;
    }

    @Override
    public double calculateRacePoints(int laps) {
        return cubicCentimeters / horsePower * laps;
    }
}
