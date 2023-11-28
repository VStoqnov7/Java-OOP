package P02WorkingWithAbstractionExercise.P02CardRank;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        CardRank[] cardRank = CardRank.values();

        System.out.println("Card Ranks:");

        for (CardRank item : cardRank) {
            System.out.printf("Ordinal value: %d; Name value: %s%n",item.ordinal(),item.toString());
        }

    }
}
