package P15Exams.P08Exam.zoo.core;

import P15Exams.P08Exam.zoo.common.ConstantMessages;
import P15Exams.P08Exam.zoo.common.ExceptionMessages;
import P15Exams.P08Exam.zoo.entities.animals.Animal;
import P15Exams.P08Exam.zoo.entities.animals.AquaticAnimal;
import P15Exams.P08Exam.zoo.entities.animals.TerrestrialAnimal;
import P15Exams.P08Exam.zoo.entities.areas.Area;
import P15Exams.P08Exam.zoo.entities.areas.LandArea;
import P15Exams.P08Exam.zoo.entities.areas.WaterArea;
import P15Exams.P08Exam.zoo.entities.foods.Food;
import P15Exams.P08Exam.zoo.entities.foods.Meat;
import P15Exams.P08Exam.zoo.entities.foods.Vegetable;
import P15Exams.P08Exam.zoo.repositories.FoodRepository;
import P15Exams.P08Exam.zoo.repositories.FoodRepositoryImpl;


import java.util.LinkedHashMap;
import java.util.Map;

public class ControllerImpl implements Controller {

    private FoodRepository foodRepository;
    private Map<String,Area> areas;

    public ControllerImpl() {
        this.foodRepository = new FoodRepositoryImpl();
        this.areas = new LinkedHashMap<>();
    }

    @Override
    public String addArea(String areaType, String areaName) {
        if (!(areaType.equals("WaterArea") || areaType.equals("LandArea"))){
            throw new NullPointerException(ExceptionMessages.INVALID_AREA_TYPE);
        }

        Area area = null;
        switch (areaType){
            case "WaterArea":
                area = new WaterArea(areaName);
                break;
            case "LandArea":
                area = new LandArea(areaName);
                break;
        }
        areas.putIfAbsent(areaName,area);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_AREA_TYPE,areaType);
    }

    @Override
    public String buyFood(String foodType) {
        if (!(foodType.equals("Vegetable") || foodType.equals("Meat"))){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_FOOD_TYPE);
        }

        Food food = null;
        switch (foodType){
            case "Vegetable":
                food = new Vegetable();
                break;
            case "Meat":
                food = new Meat();
                break;
        }

        foodRepository.add(food);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FOOD_TYPE,foodType);
    }

    @Override
    public String foodForArea(String areaName, String foodType) {

        Food food = foodRepository.findByType(foodType);
        if (food == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_FOOD_FOUND,foodType));
        }

        Area area = areas.get(areaName);
        area.addFood(food);
        foodRepository.remove(food);


        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FOOD_IN_AREA,foodType,areaName);
    }

    @Override
    public String addAnimal(String areaName, String animalType, String animalName, String kind, double price) {

        if (!(animalType.equals("AquaticAnimal") || animalType.equals("TerrestrialAnimal"))){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_ANIMAL_TYPE);
        }
        Animal animal = null;
        switch (animalType){
            case "AquaticAnimal":
                animal = new AquaticAnimal(animalName,kind,price);
                break;
            case "TerrestrialAnimal":
                animal = new TerrestrialAnimal(animalName,kind,price);
                break;
        }
        Area area = areas.get(areaName);
        if (animal.getClass().getSimpleName().equals("AquaticAnimal") && area.getClass().getSimpleName().equals("WaterArea")){
            area.addAnimal(animal);
        } else if (animal.getClass().getSimpleName().equals("TerrestrialAnimal") && area.getClass().getSimpleName().equals("LandArea")) {
            area.addAnimal(animal);
        }else {
            return ConstantMessages.AREA_NOT_SUITABLE;
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_ANIMAL_IN_AREA,animalType,areaName);
    }

    @Override
    public String feedAnimal(String areaName) {
        int count = 0;
        for (Area value : areas.values()) {
            if (value.getName().equals(areaName)){
                value.feed();
                count += value.getAnimals().size();
            }
        }
        return String.format(ConstantMessages.ANIMALS_FED,count);
    }

    @Override
    public String calculateKg(String areaName) {
        double total = areas.get(areaName).getAnimals().stream().mapToDouble(Animal::getKg).sum();
        return String.format(ConstantMessages.KILOGRAMS_AREA,areaName,total);
    }

    @Override
    public String getStatistics() {

        StringBuilder sb = new StringBuilder();
        for (Area value : areas.values()) {
            sb.append(value.getInfo()).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
