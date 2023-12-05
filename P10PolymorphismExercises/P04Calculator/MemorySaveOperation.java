package P10PolymorphismExercises.P04Calculator;

public class MemorySaveOperation implements Operation {

    private int lastSaved;
    private boolean completed;
    private Memory memory;

    public MemorySaveOperation(Memory memory) {
        this.memory = memory;
    }


    @Override
    public void addOperand(int operand) {

        memory.save(operand);
        lastSaved = operand;
        this.completed = true;
    }

    @Override
    public int getResult() {
        return this.lastSaved;
    }

    @Override
    public boolean isCompleted() {
        return this.completed;
    }
}
