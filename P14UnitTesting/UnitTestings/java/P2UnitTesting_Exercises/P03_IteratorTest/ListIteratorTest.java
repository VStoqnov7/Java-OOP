package P14UnitTesting.UnitTestings.java.P2UnitTesting_Exercises.P03_IteratorTest;

import P14UnitTesting.Tasks.java.P2UnitTesting_Exercises.P03_IteratorTest.ListIterator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class ListIteratorTest {

    private ListIterator listIterator;
    private static final String[] ELEMENTS = {"Desi", "Venci", "Pesho"};


    @Before
    public void setup() throws OperationNotSupportedException {
        listIterator = new ListIterator(ELEMENTS);

    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThanThrowException() throws OperationNotSupportedException {
        new ListIterator(null);
    }


    @Test
    public void testMoveAndHasNext(){

        Assert.assertTrue(listIterator.hasNext());
        Assert.assertTrue(listIterator.move());
        Assert.assertTrue(listIterator.hasNext());
        Assert.assertTrue(listIterator.move());
        Assert.assertFalse(listIterator.hasNext());
        Assert.assertFalse(listIterator.move());


    }

    @Test(expected = IllegalStateException.class)
    public void testPrintThrowException() throws OperationNotSupportedException {
        listIterator = new ListIterator();
        listIterator.print();
    }


    @Test
    public void testPrint(){
        Assert.assertEquals("Desi", listIterator.print());
        listIterator.move();
        Assert.assertEquals("Venci", listIterator.print());
        listIterator.move();
        Assert.assertEquals("Pesho", listIterator.print());
    }
}