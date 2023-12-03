package P07InterfacesAndAbstractionLab.P02CarShopExtended;

public interface Rentable extends Car{
    Integer getMinRentDay();
    Double getPricePerDay();
}
