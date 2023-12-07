package P13ExceptionsAndErrorHandlingLab;


import java.util.Scanner;

public class P03EnterNumbers {
    public static void main(String[] args) {
        int[] numbers = new int[10];
        int count = 0;

        while (count < 10) {
            try {
                int number = readNumber(count  + 1, 100);
                numbers[count] = number;
                count++;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Number!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println(formatArray(numbers));
    }

    public static int readNumber(int start, int end) {
        Scanner scanner = new Scanner(System.in);
        int number = Integer.parseInt(scanner.nextLine());

        if (number <= start || number >= end) {
            throw new IllegalArgumentException("Your number is not in range " + start + " - " + end + "!");
        }

        return number;
    }

    public static String formatArray(int[] numbers) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < numbers.length; i++) {
            builder.append(numbers[i]);

            if (i < numbers.length - 1) {
                builder.append(", ");
            }
        }

        return builder.toString();
    }
}