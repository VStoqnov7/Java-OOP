package P15Exams.P12Exam.glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer{

    private static final double ENERGY = 60;

    public NaturalExplorer(String name) {
        super(name, ENERGY);
    }

    @Override
    public void search() {
        this.setEnergy(Math.max(0,this.getEnergy() - 7));
    }
}
