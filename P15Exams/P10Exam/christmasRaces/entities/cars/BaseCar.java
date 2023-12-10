package P15Exams.P10Exam.christmasRaces.entities.cars;


import P15Exams.P10Exam.christmasRaces.common.ExceptionMessages;

public abstract class BaseCar implements Car{

    private String model;
    private int horsePower;
    private double cubicCentimeters;

    public BaseCar(String model, int horsePower, double cubicCentimeters) {
        this.setModel(model);
        this.setHorsePower(horsePower);
        this.cubicCentimeters = cubicCentimeters;
    }

    protected abstract void checkHorsePower(int horsePower);
    private void setHorsePower(int horsePower){
        this.checkHorsePower(horsePower);
        this.horsePower = horsePower;
    }

    private void setModel(String model) {
        if (model == null || model.trim().isEmpty() || model.length() < 4){
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_MODEL,model,4));
        }
        this.model = model;
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
