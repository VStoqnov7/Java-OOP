package P15Exams.P13Exam.restaurant.entities.tables.interfaces;

import P15Exams.P13Exam.restaurant.entities.healthyFoods.interfaces.HealthyFood;
import P15Exams.P13Exam.restaurant.entities.drinks.interfaces.Beverages;

public interface Table {
    int getTableNumber();

    int getSize();

    int numberOfPeople();

    double pricePerPerson();

    boolean isReservedTable();

    double allPeople();

    void reserve(int numberOfPeople);

    void orderHealthy(HealthyFood food);

    void orderBeverages(Beverages beverages);

    double bill();

    void clear();

    String tableInformation();
}
