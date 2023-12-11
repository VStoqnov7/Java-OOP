package P15Exams.P13Exam.restaurant.entities.tables;

import P15Exams.P13Exam.restaurant.common.ExceptionMessages;
import P15Exams.P13Exam.restaurant.entities.drinks.interfaces.Beverages;
import P15Exams.P13Exam.restaurant.entities.healthyFoods.interfaces.HealthyFood;
import P15Exams.P13Exam.restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseTable implements Table {

    private Collection<HealthyFood> healthyFood;
    private Collection<Beverages> beverages;
    private int number;
    private int size;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReservedTable;
    private double allPeople;


    public BaseTable(int number, int size, double pricePerPerson) {
        this.healthyFood = new ArrayList<>();
        this.beverages = new ArrayList<>();
        this.number = number;
        this.setSize(size);
        this.pricePerPerson = pricePerPerson;
        this.isReservedTable = false;
    }


    public void setSize(int size) {
        if (size < 0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_TABLE_SIZE);
        }
        this.size = size;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public int getTableNumber() {
        return this.number;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int numberOfPeople() {
        return this.numberOfPeople;
    }

    @Override
    public double pricePerPerson() {
        return this.pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {
        return this.isReservedTable;
    }

    @Override
    public double allPeople() {
        return this.numberOfPeople * this.pricePerPerson;
    }

    @Override
    public void reserve(int numberOfPeople) {
        this.isReservedTable = true;
        this.setNumberOfPeople(numberOfPeople);
    }

    @Override
    public void orderHealthy(HealthyFood food) {
        this.healthyFood.add(food);
    }

    @Override
    public void orderBeverages(Beverages beverages) {
        this.beverages.add(beverages);
    }

    @Override
    public double bill() {
        double drinks = this.beverages.stream().mapToDouble(Beverages::getPrice).sum();
        double food = this.healthyFood.stream().mapToDouble(HealthyFood::getPrice).sum();
        return drinks + food + this.allPeople();
    }

    @Override
    public void clear() {
        this.healthyFood.clear();
        this.beverages.clear();
        this.isReservedTable = false;
        this.numberOfPeople = 0;
        this.allPeople = 0;
    }

    @Override
    public String tableInformation() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Table - %d",this.number));
        sb.append(System.lineSeparator());
        sb.append(String.format("Size - %d",this.size));
        sb.append(System.lineSeparator());
        sb.append(String.format("Type - %s",this.getClass().getSimpleName()));
        sb.append(System.lineSeparator());
        sb.append(String.format("All price - %.2f",this.allPeople));

        return sb.toString().trim();
    }
}
