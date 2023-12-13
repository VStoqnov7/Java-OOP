package P15Exams.P17Exam.UnitTestings.java.P20Computers;

import P15Exams.P17Exam.TaskForUnitTestings.java.P20Computers.Computer;
import P15Exams.P17Exam.TaskForUnitTestings.java.P20Computers.ComputerManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ComputerManagerTests {
    private ComputerManager computerManager;

    @Before
    public void setup(){
        this.computerManager = new ComputerManager();
    }

    @Test
    public void testGetComputers(){
        Computer computer1 = new Computer("Venci","Asus",1000);
        Computer computer2 = new Computer("Toni","LG",1000);
        Computer computer3 = new Computer("Stoyan","Samsung",1000);
        this.computerManager.addComputer(computer1);
        this.computerManager.addComputer(computer2);
        this.computerManager.addComputer(computer3);

        List<Computer> expected = new ArrayList<>();
        expected.add(computer1);
        expected.add(computer2);
        expected.add(computer3);
        Assert.assertEquals(expected,this.computerManager.getComputers());
        Assert.assertEquals(3,this.computerManager.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddThrow(){
        Computer computer1 = new Computer("Venci","Asus",1000);
        Computer computer2 = new Computer("Venci","Asus",1000);
        this.computerManager.addComputer(computer1);
        this.computerManager.addComputer(computer2);
    }

    @Test
    public void testRemove(){
        Computer computer1 = new Computer("Venci","Asus",1000);
        Computer computer2 = new Computer("Toni","LG",1000);
        Computer computer3 = new Computer("Stoyan","Samsung",1000);
        this.computerManager.addComputer(computer1);
        this.computerManager.addComputer(computer2);
        this.computerManager.addComputer(computer3);
        Assert.assertEquals(3,this.computerManager.getCount());
        this.computerManager.removeComputer("Venci","Asus");
        Assert.assertEquals(2,this.computerManager.getCount());
    }

    @Test
    public void testGetComputer(){
        Computer computer1 = new Computer("Venci","Asus",1000);
        Computer computer2 = new Computer("Toni","LG",1000);
        Computer computer3 = new Computer("Stoyan","Samsung",1000);
        this.computerManager.addComputer(computer1);
        this.computerManager.addComputer(computer2);
        this.computerManager.addComputer(computer3);

        Computer computer = this.computerManager.getComputer("Venci","Asus");
        Assert.assertEquals(computer1,computer);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetComputerThrow(){
        Computer computer1 = new Computer("Venci","Asus",1000);
        Computer computer2 = new Computer("Toni","LG",1000);
        Computer computer3 = new Computer("Stoyan","Samsung",1000);
        this.computerManager.addComputer(computer1);
        this.computerManager.addComputer(computer2);
        this.computerManager.addComputer(computer3);

        this.computerManager.getComputer("Simeon","Ipad");
    }

    @Test
    public void testGetComputerByManufacturer(){
        Computer computer1 = new Computer("Venci","Asus",1000);
        Computer computer2 = new Computer("Toni","LG",1000);
        Computer computer3 = new Computer("Stoyan","Samsung",1000);
        this.computerManager.addComputer(computer1);
        this.computerManager.addComputer(computer2);
        this.computerManager.addComputer(computer3);

        List<Computer> computers = this.computerManager.getComputersByManufacturer("Venci");
        Assert.assertEquals(1,computers.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidateNullValue(){
        Computer computer1 = new Computer("Venci","Asus",1000);
        Computer computer2 = new Computer("Toni","LG",1000);
        this.computerManager.addComputer(computer1);
        this.computerManager.addComputer(computer2);
        this.computerManager.addComputer(null);
    }
}