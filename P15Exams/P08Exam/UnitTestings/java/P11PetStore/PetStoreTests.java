package P15Exams.P08Exam.UnitTestings.java.P11PetStore;

import P15Exams.P08Exam.TaskForUnitTestings.java.P11PetStore.Animal;
import P15Exams.P08Exam.TaskForUnitTestings.java.P11PetStore.PetStore;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PetStoreTests {

    private PetStore petStore;

    @Before
    public void setup(){
        this.petStore = new PetStore();
    }

    @Test
    public void testAddAnimal(){
        Animal animal1 = new Animal("Dog",10,100);
        Animal animal2 = new Animal("Cat",10,100);
        petStore.addAnimal(animal1);
        petStore.addAnimal(animal2);
        Assert.assertEquals(2,petStore.getCount());

        List<Animal> returned = petStore.getAnimals();
        Assert.assertEquals(returned,petStore.getAnimals());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNull(){
        petStore.addAnimal(null);
    }


    @Test
    public void testFindAllAnimalsWhitMaxKilograms(){
        Animal animal1 = new Animal("Dog",10,100);
        Animal animal2 = new Animal("Cat",20,200);
        Animal animal3 = new Animal("Horse",30,300);
        petStore.addAnimal(animal1);
        petStore.addAnimal(animal2);
        petStore.addAnimal(animal3);
        List<Animal> returned = petStore.findAllAnimalsWithMaxKilograms(10);
        Assert.assertEquals(2,returned.size());
    }

    @Test
    public void testFindAllAnimalBySpecie(){
        Animal animal1 = new Animal("Dog",10,100);
        Animal animal2 = new Animal("Dog",20,200);
        Animal animal3 = new Animal("Horse",30,300);
        petStore.addAnimal(animal1);
        petStore.addAnimal(animal2);
        petStore.addAnimal(animal3);

        List<Animal> returned = petStore.findAllAnimalBySpecie("Dog");
        Assert.assertEquals(2,returned.size());
    }

    @Test
    public void testGetTheMostExpensiveAnimal(){
        Animal animal1 = new Animal("Dog",10,100);
        Animal animal2 = new Animal("Cat",20,200);
        Animal animal3 = new Animal("Horse",30,300);
        petStore.addAnimal(animal1);
        petStore.addAnimal(animal2);
        petStore.addAnimal(animal3);
        Animal animal = petStore.getTheMostExpensiveAnimal();
        Assert.assertEquals(300 ,animal.getPrice(),0.01);
        animal.setAge(35);
        Assert.assertEquals(35,animal.getAge());
    }
}

