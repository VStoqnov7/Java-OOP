package P02WorkingWithAbstractionExercise.P01CardSuit;


public class Main {
    public static void main(String[] args) {

        System.out.println("Card Suits:");
        for (CardSuit item : CardSuit.values()) {
            System.out.printf("Ordinal value: %d; Name value: %s%n",item.ordinal(),item);

        }
    }
}
