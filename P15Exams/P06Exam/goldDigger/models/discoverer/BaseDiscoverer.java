package P15Exams.P06Exam.goldDigger.models.discoverer;

import P15Exams.P06Exam.goldDigger.common.ExceptionMessages;
import P15Exams.P06Exam.goldDigger.models.museum.BaseMuseum;
import P15Exams.P06Exam.goldDigger.models.museum.Museum;

public abstract class BaseDiscoverer implements Discoverer {

    private String name;
    private double energy;
    private Museum museum;

    public BaseDiscoverer(String name, double energy) {
        this.setName(name);
        this.setEnergy(energy);
        this.museum = new BaseMuseum();
    }


    private void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.DISCOVERER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    private void setEnergy(double energy) {
        if (energy < 0){
            throw new IllegalArgumentException(ExceptionMessages.DISCOVERER_ENERGY_LESS_THAN_ZERO);
        }
        this.energy = energy;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getEnergy() {
        return this.energy;
    }

    @Override
    public boolean canDig() {
        return this.energy > 0;
    }

    @Override
    public Museum getMuseum() {
        return this.museum;
    }

    @Override
    public void dig() {
        this.setEnergy(Math.max(0,this.getEnergy() - 15));
    }
}
