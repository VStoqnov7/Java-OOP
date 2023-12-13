package P14UnitTesting.Tasks.java.P4TestDrivenDevelopmentExercises;

public interface Transaction {
    String getFrom();

    String getTo();

    double getAmount();

    int getId();

    TransactionStatus getStatus();

    void setStatus(TransactionStatus newStatus);

}
