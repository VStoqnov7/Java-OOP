package P15Exams.P11Exam.catHouse.core;

import P15Exams.P11Exam.catHouse.common.ConstantMessages;
import P15Exams.P11Exam.catHouse.common.ExceptionMessages;
import P15Exams.P11Exam.catHouse.entities.cat.Cat;
import P15Exams.P11Exam.catHouse.entities.cat.LonghairCat;
import P15Exams.P11Exam.catHouse.entities.cat.ShorthairCat;
import P15Exams.P11Exam.catHouse.entities.houses.House;
import P15Exams.P11Exam.catHouse.entities.houses.LongHouse;
import P15Exams.P11Exam.catHouse.entities.houses.ShortHouse;
import P15Exams.P11Exam.catHouse.entities.toys.Ball;
import P15Exams.P11Exam.catHouse.entities.toys.Mouse;
import P15Exams.P11Exam.catHouse.entities.toys.Toy;
import P15Exams.P11Exam.catHouse.repositories.ToyRepository;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {

    private ToyRepository toys;
    private Collection<House> houses;

    public ControllerImpl() {
        this.toys = new ToyRepository();
        this.houses = new ArrayList<>();
    }

    @Override
    public String addHouse(String type, String name) {
        if (!(type.equals("ShortHouse") || type.equals("LongHouse"))){
            throw new NullPointerException(ExceptionMessages.INVALID_HOUSE_TYPE);
        }
        House house = null;
        switch (type){
            case "ShortHouse":
                house = new ShortHouse(name);
                break;
            case "LongHouse":
                house = new LongHouse(name);
                break;
        }
        houses.add(house);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_HOUSE_TYPE,type);
    }

    @Override
    public String buyToy(String type) {
        if (!(type.equals("Ball") || type.equals("Mouse"))){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_TOY_TYPE);
        }

        Toy toy = null;
        switch (type){
            case "Ball":
                toy = new Ball();
                break;
            case "Mouse":
                toy = new Mouse();
                break;
        }

        toys.buyToy(toy);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_TYPE,type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {

        Toy toy = toys.findFirst(toyType);
        if (toy == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_TOY_FOUND,toyType));
        }

        House house = houses.stream().filter(house1 -> house1.getName().equals(houseName)).findFirst().orElse(null);
        house.buyToy(toy);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_IN_HOUSE,toyType,houseName);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        if (!(catType.equals("ShorthairCat") || catType.equals("LonghairCat"))){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_CAT_TYPE);
        }

        Cat cat = null;
        switch (catType){
            case "ShorthairCat":
                cat = new ShorthairCat(catName,catBreed,price);
                break;
            case "LonghairCat":
                cat = new LonghairCat(catName,catBreed,price);
                break;
        }

        House house = houses.stream().filter(house1 -> house1.getName().equals(houseName)).findFirst().orElse(null);

        if (house.getClass().getSimpleName().equals("ShortHouse") && catType.equals("ShorthairCat")){
            house.addCat(cat);
        } else if (house.getClass().getSimpleName().equals("LongHouse") && catType.equals("LonghairCat")) {
            house.addCat(cat);
        }else {
            return ConstantMessages.UNSUITABLE_HOUSE;
        }


        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CAT_IN_HOUSE,catType,houseName);
    }

    @Override
    public String feedingCat(String houseName) {
        House house = houses.stream().filter(house1 -> house1.getName().equals(houseName)).findFirst().orElse(null);

        house.feeding();
        int count = house.getCats().size();
        return String.format(ConstantMessages.FEEDING_CAT,count);

    }

    @Override
    public String sumOfAll(String houseName) {
        House house = houses.stream().filter(house1 -> house1.getName().equals(houseName)).findFirst().orElse(null);

        double sumCats = house.getCats().stream().mapToDouble(Cat::getPrice).sum();
        double sumToys = house.getToys().stream().mapToDouble(Toy::getPrice).sum();

        return String.format(ConstantMessages.VALUE_HOUSE,houseName,sumCats + sumToys);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        for (House house : houses) {
            sb.append(house.getStatistics()).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
