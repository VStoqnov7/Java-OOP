package P14UnitTesting.UnitTestings.java.P2UnitTesting_Exercises.P05_CustomLinkedList;

import P14UnitTesting.Tasks.java.P2UnitTesting_Exercises.P05_CustomLinkedList.CustomLinkedList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomLinkedListTest {

    private CustomLinkedList<Integer> linkedList;

    @Before
    public void setUp() {
        linkedList = new CustomLinkedList<>();
    }

    @Test
    public void testAddAndGetElement() {
        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);

        Assert.assertEquals(10, linkedList.get(0).intValue());
        Assert.assertEquals(20, linkedList.get(1).intValue());
        Assert.assertEquals(30, linkedList.get(2).intValue());
    }

    @Test
    public void testSetElement() {
        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);

        linkedList.set(1, 25);

        Assert.assertEquals(25, linkedList.get(1).intValue());
    }

    @Test
    public void testRemoveElementByIndex() {
        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);

        int removedElement = linkedList.removeAt(1);

        Assert.assertEquals(20, removedElement);
        Assert.assertEquals(10, linkedList.get(0).intValue());
        Assert.assertEquals(30, linkedList.get(1).intValue());
    }

    @Test
    public void testRemoveElement() {
        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);

        int index = linkedList.remove(20);

        Assert.assertEquals(1, index);
        Assert.assertEquals(10, linkedList.get(0).intValue());
        Assert.assertEquals(30, linkedList.get(1).intValue());
    }

    @Test
    public void testIndexOf() {
        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);

        int index = linkedList.indexOf(20);

        Assert.assertEquals(1, index);
    }

    @Test
    public void testContains() {
        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);

        boolean contains = linkedList.contains(20);

        Assert.assertTrue(contains);
    }
}