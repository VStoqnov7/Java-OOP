package P15Exams.P05Exam.UnitTestings.java.P8ToyStore;


import P15Exams.P05Exam.TaskForUnitTestings.java.P8ToyStore.Toy;
import P15Exams.P05Exam.TaskForUnitTestings.java.P8ToyStore.ToyStore;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;


public class ToyStoreTest {
    private ToyStore toyStore;
    private Map<String, Toy> toyMap;




    @Before
    public void setup() {
        toyStore = new ToyStore();
        toyMap = new LinkedHashMap<>();
        this.toyMap.put("A", null);
        this.toyMap.put("B", null);
        this.toyMap.put("C", null);
        this.toyMap.put("D", null);
        this.toyMap.put("E", null);
        this.toyMap.put("F", null);
        this.toyMap.put("G", null);
    }



    @Test(expected = IllegalArgumentException.class)
    public void testAddToyThrow() throws OperationNotSupportedException {
        Toy toy = new Toy("Venci","10");
        this.toyStore.addToy("Toni",toy);


    }


    @Test(expected = IllegalArgumentException.class)
    public void testAddShelfThrow() throws OperationNotSupportedException {
        Toy toy = new Toy("Venci","10");
        this.toyStore.addToy("A",toy);
        this.toyStore.addToy("A",toy);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddToyExistThrow() throws OperationNotSupportedException {
        Toy toy = new Toy("Venci","10");
        this.toyStore.addToy("A",null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveToyThrow(){
        Toy toy = new Toy("A",null);
        this.toyStore.removeToy("A",toy);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testRemoveToyThrowIfNotExist(){
        Toy toy = new Toy("Venci","10");
        this.toyStore.removeToy("Venci",toy);
    }
    @Test
    public void testAddToy() throws OperationNotSupportedException {
        Toy toy = new Toy("Venci","10");
        this.toyStore.addToy("A",toy);
        this.toyMap.put("A",toy);
        Assert.assertEquals(this.toyMap,toyStore.getToyShelf());
        Assert.assertEquals("Venci",this.toyStore.getToyShelf().get("A").getManufacturer());

    }
    @Test
    public void testRemove() throws OperationNotSupportedException {
        Toy toy = new Toy("Venci","10");
        this.toyStore.addToy("A",toy);
        this.toyMap.put("A",toy);
        Assert.assertEquals(this.toyMap,toyStore.getToyShelf());
        this.toyStore.removeToy("A",toy);
        this.toyMap.put("A",null);
        Assert.assertEquals(7,this.toyStore.getToyShelf().size());
    }

    @Test
    public void testAddToyString() throws OperationNotSupportedException {
        Toy toy = new Toy("Venci","10");
        String expected = "Toy:10 placed successfully!";
        String receive = this.toyStore.addToy("A",toy);
        Assert.assertEquals(expected,receive);
    }

    @Test
    public void testRemoveToyString() throws OperationNotSupportedException {
        Toy toy = new Toy("Venci","10");
        this.toyStore.addToy("A",toy);
        String expected = "Remove toy:10 successfully!";
        String receive = this.toyStore.removeToy("A",toy);
        Assert.assertEquals(expected,receive);
        long exist = toyStore.getToyShelf().values().stream().filter(Objects::nonNull).count();
        Assert.assertEquals(0, exist);
    }
}
