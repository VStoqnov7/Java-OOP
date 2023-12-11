package P15Exams.P13Exam.restaurant.core;

import P15Exams.P13Exam.restaurant.common.ExceptionMessages;
import P15Exams.P13Exam.restaurant.common.OutputMessages;
import P15Exams.P13Exam.restaurant.core.interfaces.Controller;
import P15Exams.P13Exam.restaurant.entities.drinks.Fresh;
import P15Exams.P13Exam.restaurant.entities.drinks.Smoothie;
import P15Exams.P13Exam.restaurant.entities.healthyFoods.Food;
import P15Exams.P13Exam.restaurant.entities.healthyFoods.Salad;
import P15Exams.P13Exam.restaurant.entities.healthyFoods.VeganBiscuits;
import P15Exams.P13Exam.restaurant.entities.healthyFoods.interfaces.HealthyFood;
import P15Exams.P13Exam.restaurant.entities.drinks.interfaces.Beverages;
import P15Exams.P13Exam.restaurant.entities.tables.InGarden;
import P15Exams.P13Exam.restaurant.entities.tables.Indoors;
import P15Exams.P13Exam.restaurant.entities.tables.interfaces.Table;
import P15Exams.P13Exam.restaurant.repositories.interfaces.*;

public class ControllerImpl implements Controller {

    private HealthFoodRepository<HealthyFood> healthFoodRepository;
    private BeverageRepository<Beverages> beverageRepository;
    private TableRepository<Table> tableRepository;

    private double totalMoney;


    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository, BeverageRepository<Beverages> beverageRepository, TableRepository<Table> tableRepository) {
        this.healthFoodRepository = healthFoodRepository;
        this.beverageRepository = beverageRepository;
        this.tableRepository = tableRepository;
        this.totalMoney = 0;
    }

    @Override
    public String addHealthyFood(String type, double price, String name) {
        Food food = null;
        if (type.equals("Salad")) {
            food = new Salad(name, price);
        } else if (type.equals("VeganBiscuits")) {
            food = new VeganBiscuits(name, price);
        }

        if (healthFoodRepository.foodByName(name) == null) {
            healthFoodRepository.add(food);

        } else {
            throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_EXIST, name));

        }

        return String.format(OutputMessages.FOOD_ADDED, name);
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name) {

        Beverages beverages = null;
        if (type.equals("Fresh")) {
            beverages = new Fresh(name, counter, brand);
        } else if (type.equals("Smoothie")) {
            beverages = new Smoothie(name, counter, brand);
        }

        if (beverageRepository.beverageByName(name, brand) == null) {
            beverageRepository.add(beverages);

        } else {
            throw new IllegalArgumentException(String.format(ExceptionMessages.BEVERAGE_EXIST, name));

        }

        return String.format(OutputMessages.BEVERAGE_ADDED,type,brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {

        Table table = null;
        if (type.equals("Indoors")){
            table = new Indoors(tableNumber,capacity);
        } else if (type.equals("InGarden")) {
            table = new InGarden(tableNumber,capacity);
        }

        if (tableRepository.byNumber(tableNumber) == null){
            tableRepository.add(table);
        }else {
            throw new IllegalArgumentException(String.format(ExceptionMessages.TABLE_IS_ALREADY_ADDED,tableNumber));
        }

        return String.format(OutputMessages.TABLE_ADDED,tableNumber);
    }

    @Override
    public String reserve(int numberOfPeople) {

        Table table = tableRepository.getAllEntities().stream().filter(table1 -> !table1.isReservedTable() && numberOfPeople <= table1.getSize()).findFirst().orElse(null);

        if (table == null){
            return String.format(OutputMessages.RESERVATION_NOT_POSSIBLE,numberOfPeople);
        }

        table.reserve(numberOfPeople);

        return String.format(OutputMessages.TABLE_RESERVED,table.getTableNumber(),numberOfPeople);
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {

        Table table = tableRepository.byNumber(tableNumber);
        if (table == null){
            return String.format(OutputMessages.WRONG_TABLE_NUMBER,tableNumber);
        }

        HealthyFood food = healthFoodRepository.foodByName(healthyFoodName);
        if (food == null){
            return String.format(OutputMessages.NONE_EXISTENT_FOOD,healthyFoodName);
        }

        table.orderHealthy(food);

        return String.format(OutputMessages.FOOD_ORDER_SUCCESSFUL,healthyFoodName,tableNumber);
    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {

        Table table = tableRepository.byNumber(tableNumber);
        if (table == null){
            return String.format(OutputMessages.WRONG_TABLE_NUMBER,tableNumber);
        }
        Beverages beverages = beverageRepository.beverageByName(name,brand);
        if (beverages == null){
            return String.format(OutputMessages.NON_EXISTENT_DRINK,name,brand);
        }

        table.orderBeverages(beverages);

        return String.format(OutputMessages.BEVERAGE_ORDER_SUCCESSFUL,name,tableNumber);
    }

    @Override
    public String closedBill(int tableNumber) {

        Table table = tableRepository.byNumber(tableNumber);

        double bill = table.bill();
        table.clear();

        this.totalMoney += bill;
        return String.format(OutputMessages.BILL,tableNumber,bill);
    }


    @Override
    public String totalMoney() {
        return String.format(OutputMessages.TOTAL_MONEY,this.totalMoney);
    }
}
