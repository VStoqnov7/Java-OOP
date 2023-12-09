package P15Exams.P08Exam.zoo.repositories;

import P15Exams.P08Exam.zoo.entities.foods.Food;

public interface FoodRepository {
    void add(Food food);

    boolean remove(Food food);

    Food findByType(String type);
}
