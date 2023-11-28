package P01WorkingWithAbstractionLab.P04HotelReservation;

public class PriceCalculator {

    private double pricePerDay;
    private int numberOfDays;
    private Season season;
    private DiscountType discountType;

    public PriceCalculator(double pricePerDay, int numberOfDays, Season season, DiscountType discountType){
        this.pricePerDay = pricePerDay;
        this.numberOfDays = numberOfDays;
        this.season = season;
        this.discountType = discountType;
    }
    public static double calculatingPrice(double pricePerDay, int numberOfDays,Season season, DiscountType discountType){

        double newPricePerDay = pricePerDay * season.getMultiplier();

        return (newPricePerDay * numberOfDays) * discountType.getFindNewPrice();

    }
}