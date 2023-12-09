package P15Exams.P08Exam.zoo.entities.areas;

import P15Exams.P08Exam.zoo.entities.animals.Animal;
import P15Exams.P08Exam.zoo.entities.foods.Food;

import java.util.Collection;

public interface Area {
    String getName();

    Collection<Animal> getAnimals();

    Collection<Food> getFoods();

    int sumCalories();

    void addAnimal(Animal animal);

    void removeAnimal(Animal animal);

    void addFood(Food food);

    void feed();

    String getInfo();
}
