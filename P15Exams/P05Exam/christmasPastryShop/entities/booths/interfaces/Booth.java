package P15Exams.P05Exam.christmasPastryShop.entities.booths.interfaces;

import P15Exams.P05Exam.christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import P15Exams.P05Exam.christmasPastryShop.entities.drinks.interfaces.Cocktail;

public interface Booth {
    int getBoothNumber();

    int getCapacity();

    boolean isReserved();

    double getPrice();

    void reserve(int numberOfPeople);

    void orderDelicacy(Delicacy food);

    void orderCocktail(Cocktail cocktail);

    double getBill();

    void clear();
}
