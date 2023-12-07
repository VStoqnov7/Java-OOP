package P14UnitTesting.UnitTestings.java.P01unitTesting;

import P14UnitTesting.Tasks.java.P01unitTesting.Dummy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DummyTest {

    private static final int HEALTH = 100;
    private static final int XP = 50;

    private Dummy dummy;
    private Dummy deadDummy;

    @Before
    public void setup(){
        this.dummy = new Dummy(HEALTH,XP);
        this.deadDummy = new Dummy(0,XP);
    }


    @Test
    public void testDummyLosesHealthWhenAttacked(){
        int attackPoints = 10;
        dummy.takeAttack(attackPoints);
        Assert.assertEquals(HEALTH - attackPoints,dummy.getHealth());
    }

    @Test(expected =  IllegalStateException.class)
    public void testDeadDummyThrowsWhenAttacked(){

        deadDummy.takeAttack(10);
    }

    @Test
    public void testDeadDummyGivesXp(){
        int actual = deadDummy.giveExperience();
        Assert.assertEquals(XP,actual);
    }

    @Test(expected = IllegalStateException.class)
    public void testAliveDummyThrowsWhenGivingXp(){
        dummy.giveExperience();
    }

}