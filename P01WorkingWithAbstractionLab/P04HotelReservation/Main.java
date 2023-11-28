package P01WorkingWithAbstractionLab.P04HotelReservation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] reservationInfoInput = scanner.nextLine().split("\\s+");

        double pricePerDay = Double.parseDouble(reservationInfoInput[0]);
        int numberOfDays = Integer.parseInt(reservationInfoInput[1]);
        Season season = Season.getName(reservationInfoInput[2]);
        DiscountType discountType = DiscountType.getName(reservationInfoInput[3]);

        System.out.printf("%.2f",PriceCalculator.calculatingPrice(pricePerDay, numberOfDays, season, discountType));

    }
}