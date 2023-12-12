package P15Exams.P14Exam.UnitTestings.java.P17Aquarium;

import P15Exams.P14Exam.TaskForUnitTestings.java.P17Aquarium.Aquarium;
import P15Exams.P14Exam.TaskForUnitTestings.java.P17Aquarium.Fish;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AquariumTests {
    private Aquarium aquarium;

    @Before
    public void setup(){
        this.aquarium = new Aquarium("Venci",2);
    }


    @Test(expected = NullPointerException.class)
    public void testSetName1(){
        this.aquarium = new Aquarium(null,14);
    }

    @Test(expected = NullPointerException.class)
    public void testSetName2(){
        this.aquarium = new Aquarium("  ",14);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddThrow(){
        Fish fish1 = new Fish("Sara");
        Fish fish2 = new Fish("Dara");
        Fish fish3 = new Fish("Emily");
        this.aquarium.add(fish1);
        this.aquarium.add(fish2);
        this.aquarium.add(fish3);
    }

    @Test
    public void testAdd(){
        Fish fish1 = new Fish("Sara");
        Fish fish2 = new Fish("Dara");
        this.aquarium.add(fish1);
        this.aquarium.add(fish2);
        Assert.assertEquals(2,this.aquarium.getCount());
        Assert.assertEquals(2,this.aquarium.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityLessZero(){
        this.aquarium = new Aquarium("Stoyan",-14);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveThrow(){
        Fish fish1 = new Fish("Sara");
        Fish fish2 = new Fish("Dara");
        this.aquarium.add(fish1);
        this.aquarium.add(fish2);
        this.aquarium.remove("Emily");
    }

    @Test
    public void testRemove(){
        Fish fish1 = new Fish("Sara");
        Fish fish2 = new Fish("Dara");
        this.aquarium.add(fish1);
        this.aquarium.add(fish2);
        Assert.assertEquals(2,this.aquarium.getCount());
        this.aquarium.remove("Sara");
        Assert.assertEquals(1,this.aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSellFishThrow(){
        Fish fish1 = new Fish("Sara");
        Fish fish2 = new Fish("Dara");
        this.aquarium.add(fish1);
        this.aquarium.add(fish2);
        this.aquarium.sellFish("Emily");
    }


    @Test
    public void testSellFish(){
        Fish fish1 = new Fish("Sara");
        Fish fish2 = new Fish("Dara");
        this.aquarium.add(fish1);
        this.aquarium.add(fish2);
        Assert.assertEquals("Venci",this.aquarium.getName());
        Assert.assertTrue(fish1.isAvailable());
        this.aquarium.sellFish("Sara");
        Assert.assertFalse(fish1.isAvailable());
    }

    @Test
    public void testReport(){
        Fish fish1 = new Fish("Sara");
        Fish fish2 = new Fish("Dara");
        this.aquarium.add(fish1);
        this.aquarium.add(fish2);

        String expected = "Fish available at Venci: Sara, Dara";
        String returned = this.aquarium.report();
        Assert.assertEquals(expected,returned);
    }
}

