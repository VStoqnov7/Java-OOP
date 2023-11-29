package P02WorkingWithAbstractionExercise.P05JediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = getDimensions(scanner.nextLine());
        int rowMatrix = dimensions[0];
        int colMatrix = dimensions[1];

        int[][] matrix = new int[rowMatrix][colMatrix];

        int value = 0;
        moveMatrix(rowMatrix, colMatrix, matrix, value);

        String command = scanner.nextLine();
        long sum = 0;
        while (!command.equals("Let the Force be with you")) {
            int[] peter = getDimensions(command);
            int rowPeter = peter[0];
            int colPeter = peter[1];
            int[] devil = getDimensions(scanner.nextLine());
            int rowDevil = devil[0];
            int colDevil = devil[1];

            devilMove(matrix, rowDevil, colDevil);


            sum = getSum(matrix, sum, rowPeter, colPeter);

            command = scanner.nextLine();
        }

        System.out.println(sum);


    }

    private static int[] getDimensions(String scanner) {
        return Arrays.stream(scanner.split("\\s+")).mapToInt(Integer::parseInt).toArray();
    }

    private static long getSum(int[][] matrix, long sum, int rowPeter, int colPeter) {
        while (rowPeter >= 0 && colPeter < matrix[1].length) {
            if (rowPeter < matrix.length && colPeter >= 0 && colPeter < matrix[0].length) {
                sum += matrix[rowPeter][colPeter];
            }

            colPeter++;
            rowPeter--;
        }
        return sum;
    }

    private static void devilMove(int[][] matrix, int rowDevil, int colDevil) {
        while (rowDevil >= 0 && colDevil >= 0) {
            if (rowDevil < matrix.length && colDevil < matrix[0].length) {
                matrix[rowDevil][colDevil] = 0;
            }
            rowDevil--;
            colDevil--;
        }
    }

    private static void moveMatrix(int rowMatrix, int colMatrix, int[][] matrix, int value) {
        for (int row = 0; row < rowMatrix; row++) {
            for (int col = 0; col < colMatrix; col++) {
                matrix[row][col] = value++;
            }
        }
    }
}
