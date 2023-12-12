package P15Exams.P15Exam.UnitTestings.java.P18BlueOrigin;

import P15Exams.P15Exam.TaskForUnitTestings.java.P18BlueOrigin.Astronaut;
import P15Exams.P15Exam.TaskForUnitTestings.java.P18BlueOrigin.Spaceship;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpaceshipTests {

    private Spaceship spaceship;

    @Before
    public void setup(){
        this.spaceship = new Spaceship("Stoyan",2);
    }
    @Test
    public void testAdd(){
        Astronaut astronaut1 = new Astronaut("Venci",10.00);
        Assert.assertEquals("Venci",astronaut1.getName());
        Assert.assertEquals(10.00,astronaut1.getOxygenInPercentage(),0.01);
        Astronaut astronaut2 = new Astronaut("Toni",10.00);
        this.spaceship.add(astronaut1);
        this.spaceship.add(astronaut2);
        Assert.assertEquals(2,this.spaceship.getCount());
        Assert.assertEquals(2,this.spaceship.getCapacity());
        Assert.assertEquals("Stoyan",this.spaceship.getName());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAddThrow1(){
        Astronaut astronaut1 = new Astronaut("Venci",10.00);
        Astronaut astronaut2 = new Astronaut("Toni",10.00);
        Astronaut astronaut3 = new Astronaut("Emily",10.00);
        this.spaceship.add(astronaut1);
        this.spaceship.add(astronaut2);
        this.spaceship.add(astronaut3);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddThrow2(){
        Astronaut astronaut1 = new Astronaut("Venci",10.00);
        this.spaceship.add(astronaut1);
        this.spaceship.add(astronaut1);

    }

    @Test
    public void testRemove(){
        Astronaut astronaut1 = new Astronaut("Venci",10.00);
        Astronaut astronaut2 = new Astronaut("Toni",10.00);
        this.spaceship.add(astronaut1);
        this.spaceship.add(astronaut2);
        Assert.assertEquals(2,this.spaceship.getCount());
        Assert.assertTrue(this.spaceship.remove("Venci"));
        Assert.assertFalse(this.spaceship.remove("Milen"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityLessZero(){
        this.spaceship = new Spaceship("Venci",-20);
    }


    @Test(expected = NullPointerException.class)
    public void testSetNameNull(){
        this.spaceship = new Spaceship(null,20);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameWhiteSpace(){
        this.spaceship = new Spaceship(null,20);
    }


    @Test
    public void testRemove3(){
        Astronaut astronaut1 = new Astronaut("Venci",10.00);
        Astronaut astronaut2 = new Astronaut("Toni",10.00);
        this.spaceship.add(astronaut1);
        this.spaceship.add(astronaut2);
        Assert.assertEquals(2,this.spaceship.getCount());
        this.spaceship.remove("Venci");
        Assert.assertEquals(1,this.spaceship.getCount());
    }
}
