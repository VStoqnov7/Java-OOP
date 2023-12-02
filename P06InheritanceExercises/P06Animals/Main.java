package P06InheritanceExercises.P06Animals;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String origin = scanner.nextLine();

        while (!origin.equals("Beast!")) {


            String[] data = scanner.nextLine().split("\\s+");
            String name = data[0];
            int age = Integer.parseInt(data[1]);
            String gender = data[2];


            try {


                switch (origin) {
                    case "Cat":
                        Cat cat = new Cat(name, age, gender);
                        System.out.println(cat);

                        break;
                    case "Dog":
                        Dog dog = new Dog(name, age, gender);
                        System.out.println(dog);
                        break;
                    case "Frog":
                        Frog frog = new Frog(name, age, gender);
                        System.out.println(frog);
                        break;
                    case "Kitten":
                        Kitten kitten = new Kitten(name, age);
//                    System.out.println(kitten);
//                    System.out.printf("%s %d %s%n",kitten.getName(),kitten.getAge(),kitten.getGender());
//                    kitten.produceSound();
                        break;
                    case "Tomcat":
                        Tomcat tomCat = new Tomcat(name, age);
//                    System.out.println(tomCat);
//                    System.out.printf("%s %d %s%n",tomCat.getName(),tomCat.getAge(),tomCat.getGender());
//                    tomCat.produceSound();
                        break;


                }

            }catch (IllegalArgumentException e ){
                System.out.println(e.getMessage());
            }

            origin = scanner.nextLine();
        }


    }
}
