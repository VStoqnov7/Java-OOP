package P05InheritanceLab.P02HierarchicalInheritance;



import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        Animal animal = new Animal();
        Dog dog = new Dog();
        Cat cat = new Cat();

        animal.eat();
        dog.bark();
        cat.eat();
        cat.meow();

    }
}
