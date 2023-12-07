package P15Exams.P01Exam.UnitTestings.java.P5Robots;

import P15Exams.P01Exam.TaskForUnitTestings.java.P5Robots.Robot;
import P15Exams.P01Exam.TaskForUnitTestings.java.P5Robots.Service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class ServiceTests {

    private Service service;

    @Before
    public void setup(){
        this.service = new Service("Ivan",2);
    }

    @Test
    public void testGetName(){
        String name = this.service.getName();
        Assert.assertEquals("Ivan",name);
    }
    @Test(expected = NullPointerException.class)
    public void testSetNameThrow(){
        this.service = new Service(null,3);
    }
    @Test(expected = NullPointerException.class)
    public void testSetNameWhiteSpaceThrow(){
        this.service = new Service("  ", 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityThrow(){
        this.service = new Service("Ivan", -1);
    }

    @Test
    public void testGetCapacity(){
        int capacity = this.service.getCapacity();
        Assert.assertEquals(2,capacity);
    }

    @Test
    public void testAddRobot(){
        Robot robot = new Robot("Denis");
        this.service.add(robot);
        int count = this.service.getCount();
        Assert.assertEquals(1,count);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddRobotThrow(){
        Robot robot1 = new Robot("Denis");
        Robot robot2 = new Robot("Alex");
        Robot robot3 = new Robot("Radi");
        service.add(robot1);
        service.add(robot2);
        service.add(robot3);

    }

    @Test
    public void testRemove(){
        Robot robot1 = new Robot("Denis");
        Robot robot2 = new Robot("Alex");
        service.add(robot1);
        service.add(robot2);
        Assert.assertEquals(2,service.getCount());
        service.remove("Denis");
        Assert.assertEquals(1,service.getCount());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveThrow() {
        Robot robot1 = new Robot("Denis");
        Robot robot2 = new Robot("Alex");
        service.add(robot1);
        service.add(robot2);
        Assert.assertEquals(2, service.getCount());
        service.remove("Toni");

    }

    @Test
    public void testForSale() {
        Robot robot1 = new Robot("Denis");
        Robot robot2 = new Robot("Alex");
        service.add(robot1);
        service.add(robot2);
        Assert.assertTrue(robot1.isReadyForSale());
        Robot robot = service.forSale("Denis");
        Assert.assertEquals("Denis",robot.getName());
        Assert.assertFalse(robot.isReadyForSale());

    }
    @Test(expected = IllegalArgumentException.class)
    public void testForSaleThrow() {
        Robot robot1 = new Robot("Denis");
        Robot robot2 = new Robot("Alex");
        service.add(robot1);
        service.add(robot2);
        Robot robot = service.forSale("Stoyan");

    }

    @Test
    public void testReport(){
        Robot robot1 = new Robot("Denis");
        Robot robot2 = new Robot("Alex");
        service.add(robot1);
        service.add(robot2);
        String report = service.report();
        Assert.assertEquals("The robot Denis, Alex is in the service Ivan!",report);

    }
}
