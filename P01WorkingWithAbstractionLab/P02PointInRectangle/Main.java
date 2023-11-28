package P01WorkingWithAbstractionLab.P02PointInRectangle;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int[] inputCoordinates = getInputCoordinates(scanner);

        Point bottomLeft = new Point(inputCoordinates[0], inputCoordinates[1]);
        Point topRight = new Point(inputCoordinates[2],inputCoordinates[3]);

        Rectangle rectangle = new Rectangle(bottomLeft, topRight);

        int countNewCoordinates = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < countNewCoordinates; i++) {
            int[] newCoordinates = getInputCoordinates(scanner);

            Point newPoint = new Point(newCoordinates[0], newCoordinates[1]);

            boolean contains = rectangle.contains(newPoint);
            System.out.println(contains);
        }

    }

    private static int[] getInputCoordinates(Scanner scan) {
        return Arrays.stream(scan.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}