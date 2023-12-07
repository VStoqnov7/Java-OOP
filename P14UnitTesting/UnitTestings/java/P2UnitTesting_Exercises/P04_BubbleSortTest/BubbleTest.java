package P14UnitTesting.UnitTestings.java.P2UnitTesting_Exercises.P04_BubbleSortTest;

import P14UnitTesting.Tasks.java.P2UnitTesting_Exercises.P04_BubbleSortTest.Bubble;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BubbleTest {

    private static final int[] ARR = {1,3,2,5,7,4,6,8,10,9};
    private Bubble bubble;

    @Before
    public void setup(){
        this.bubble = new Bubble();
    }

    @Test
    public void testSortBubble(){

        int[] numbers = {1,2,3,4,5,6,7,8,9,10};
        Bubble.sort(ARR);

        Assert.assertArrayEquals(ARR,numbers);

    }

}