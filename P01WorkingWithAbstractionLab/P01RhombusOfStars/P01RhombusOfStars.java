package P01WorkingWithAbstractionLab.P01RhombusOfStars;

import java.util.Scanner;

public class P01RhombusOfStars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= n; i++) {
            printSpace(n - i, " ");
            printSpace(i, "* ");
            System.out.println();
        }
        for (int i = 1; i <= n - 1; i++) {
            printSpace(i, " ");
            printSpace(n - i, "* ");
            System.out.println();
        }
    }
    private static void printSpace(int n, String s) {
        for (int j = 0; j < n; j++) {
            System.out.print(s);
        }
    }
}
