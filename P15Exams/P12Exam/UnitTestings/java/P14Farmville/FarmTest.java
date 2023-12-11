package P15Exams.P12Exam.UnitTestings.java.P14Farmville;

import P15Exams.P12Exam.TaskForUnitTestings.java.P14Farmville.Animal;
import P15Exams.P12Exam.TaskForUnitTestings.java.P14Farmville.Farm;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FarmTest {

    private Farm farm;


    @Before
    public void setup(){
        this.farm = new Farm("Venci",2);
    }


    @Test(expected = NullPointerException.class)
    public void testSetNameNullThrow1(){
        this.farm = new Farm(null,3);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameWhiteSpaceThrow2(){
        this.farm = new Farm("   " ,3);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityLessZeroThrow(){
        this.farm = new Farm("Stoyan",-3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddThrow1(){
        Animal animal1 = new Animal("Dog",10);
        Animal animal2 = new Animal("Cat",10);
        Animal animal3 = new Animal("Horse",10);
        this.farm.add(animal1);
        this.farm.add(animal2);
        this.farm.add(animal3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddThrow2(){
        Animal animal1 = new Animal("Dog",10);
        Animal animal2 = new Animal("Dog",10);
        this.farm.add(animal1);
        this.farm.add(animal2);

    }

    @Test
    public void testAdd(){
        Animal animal1 = new Animal("Dog",10);
        Assert.assertEquals(10,animal1.getEnergy(),0.01);
        Animal animal2 = new Animal("Cat",10);
        this.farm.add(animal1);
        this.farm.add(animal2);
        Assert.assertEquals(2,this.farm.getCount());
        Assert.assertEquals(2,this.farm.getCapacity());
        Assert.assertEquals("Venci",this.farm.getName());

    }

    @Test
    public void testRemove1(){
        Animal animal1 = new Animal("Dog",10);
        Animal animal2 = new Animal("Cat",10);
        this.farm.add(animal1);
        this.farm.add(animal2);
        Assert.assertEquals(2,this.farm.getCount());
        this.farm.remove("Dog");
        Assert.assertEquals(1,this.farm.getCount());
        this.farm.add(animal1);
        Assert.assertEquals(2,this.farm.getCount());
        boolean expectedTrue = this.farm.remove("Dog");
        Assert.assertTrue(expectedTrue);
        boolean expectedFalse = this.farm.remove("Ant");
        Assert.assertFalse(expectedFalse);

    }
}