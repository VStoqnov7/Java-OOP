package P14UnitTesting.UnitTestings.java.P2UnitTesting_Exercises.P01_Database;


import P14UnitTesting.Tasks.java.P2UnitTesting_Exercises.P01_Database.Database;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {

    private Database database;
    private static final Integer[] NUMBERS = {1, 2, 3, 4, 5};


    @Before
    public void prepareDatabase() throws OperationNotSupportedException {
        database = new Database(NUMBERS);
    }


    @Test
    public void testConstructor() {
        Integer[] elements = database.getElements();
        Assert.assertArrayEquals(elements, database.getElements());

    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowWhenMoreThanSixteenElements() throws OperationNotSupportedException {
        Integer[] numbers = new Integer[17];
        new Database(numbers);

    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowWhenLessThanOneElement() throws OperationNotSupportedException {
        Integer[] numbers = new Integer[0];
        new Database(numbers);
    }

    @Test
    public void testAddShouldAddElement() throws OperationNotSupportedException {
        database.add(6);

        Assert.assertEquals(database.getElements().length,NUMBERS.length + 1);

        Integer[] elements = database.getElements();
        Assert.assertEquals(elements[elements.length - 1],Integer.valueOf(6));
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddShouldThrowNullParam() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void testRemoveLastElement() throws OperationNotSupportedException {
        database.remove();

        Integer[] elements = database.getElements();

        Assert.assertEquals(elements.length,NUMBERS.length - 1);
        Assert.assertEquals(elements[elements.length - 1],Integer.valueOf(4));


    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveThrowEmptyDatabase() throws OperationNotSupportedException {

        for (int i = 0; i < NUMBERS.length; i++) {
            database.remove();
        }

        database.remove();


    }


}