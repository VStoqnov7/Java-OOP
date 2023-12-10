package P15Exams.P11Exam.UnitTestings.java.P13Cats;

import P15Exams.P11Exam.TaskForUnitTestings.java.P13Cats.Cat;
import P15Exams.P11Exam.TaskForUnitTestings.java.P13Cats.House;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HouseTests {


    private House house;

    @Before
    public void setup(){
        this.house = new House("Stoyan",2);
    }


    @Test(expected = NullPointerException.class)
    public void testSetNameNullThrow(){
        this.house = new House(" ",2);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameNullThrow2(){
        this.house = new House(null,2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityThrow(){
        this.house = new House("Venci",-2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCatThrow(){
        Cat cat1 = new Cat("Toni");
        Cat cat2 = new Cat("Simeon");
        Cat cat3 = new Cat("Venci");
        this.house.addCat(cat1);
        this.house.addCat(cat2);
        this.house.addCat(cat3);
    }

    @Test
    public void testAddCat(){
        Cat cat1 = new Cat("Toni");
        Cat cat2 = new Cat("Simeon");
        this.house.addCat(cat1);
        this.house.addCat(cat2);
        Assert.assertEquals(2,this.house.getCount());
        Assert.assertEquals(2,this.house.getCapacity());
        Assert.assertEquals("Stoyan",this.house.getName());
    }

    @Test
    public void testRemoveCat(){
        Cat cat1 = new Cat("Toni");
        Cat cat2 = new Cat("Simeon");
        this.house.addCat(cat1);
        this.house.addCat(cat2);
        this.house.removeCat("Simeon");
        Assert.assertEquals(1,this.house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveCatThrow(){
        Cat cat1 = new Cat("Toni");
        Cat cat2 = new Cat("Simeon");
        this.house.addCat(cat1);
        this.house.addCat(cat2);
        this.house.removeCat("Elena");
    }

    @Test
    public void testCatForSale(){
        Cat cat1 = new Cat("Toni");
        Cat cat2 = new Cat("Simeon");
        this.house.addCat(cat1);
        this.house.addCat(cat2);
        Assert.assertTrue(cat2.isHungry());
        this.house.catForSale("Simeon");
        Assert.assertFalse(cat2.isHungry());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testCatForSaleThrow(){
        Cat cat1 = new Cat("Toni");
        Cat cat2 = new Cat("Simeon");
        this.house.addCat(cat1);
        this.house.addCat(cat2);
        this.house.catForSale("Elena");
    }

    @Test
    public void testStatistics(){
        Cat cat1 = new Cat("Toni");
        Cat cat2 = new Cat("Simeon");
        this.house.addCat(cat1);
        this.house.addCat(cat2);
        String expected = "The cat Toni, Simeon is in the house Stoyan!";
        String returned = this.house.statistics();
        Assert.assertEquals(expected,returned);
    }
}
