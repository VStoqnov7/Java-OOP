package P15Exams.P12Exam.glacialExpedition.models.explorers;

import P15Exams.P12Exam.glacialExpedition.common.ExceptionMessages;
import P15Exams.P12Exam.glacialExpedition.models.suitcases.Carton;
import P15Exams.P12Exam.glacialExpedition.models.suitcases.Suitcase;

public abstract class BaseExplorer implements Explorer{

    private String name;
    private double energy;
    private Suitcase suitcase;


    public BaseExplorer(String name, double energy) {
        this.setName(name);
        this.setEnergy(energy);
        this.suitcase = new Carton();

    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.EXPLORER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setEnergy(double energy) {
        if (energy < 0){
            throw new IllegalArgumentException(ExceptionMessages.EXPLORER_ENERGY_LESS_THAN_ZERO);
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
    public boolean canSearch() {
        return this.energy > 0;
    }

    @Override
    public Suitcase getSuitcase() {
        return this.suitcase;
    }

    @Override
    public void search() {
        this.setEnergy(Math.max(0,this.getEnergy() - 15));
    }
}
