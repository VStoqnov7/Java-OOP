package P14UnitTesting.UnitTestings.java.P01unitTesting;

import P14UnitTesting.Tasks.java.P01unitTesting.Axe;
import P14UnitTesting.Tasks.java.P01unitTesting.Dummy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AxeTest {
    private static final int AXE_ATTACK = 10;
    private static final int AXE_DURABILITY = 10;
    private Dummy dummy;
    private Axe axe;
    private Axe brokenAxe;


    @Before
    public void setup(){
        this.dummy = new Dummy(100,100);
        this.axe = new Axe(AXE_ATTACK,AXE_DURABILITY);
        this.brokenAxe = new Axe(AXE_ATTACK,0);
    }

    @Test
    public void testWeaponLoseDurabilityAfterAttack(){

        axe.attack(dummy);

        Assert.assertEquals(9,axe.getDurabilityPoints());
    }

    @Test(expected = IllegalStateException.class)
    public void testAxeAttackThrowsIfAxeIsBroken(){
        brokenAxe.attack(dummy);
    }

}
//  @Test
//    public void testWeaponLoseDurabilityAfterAttack(){
//
//        Axe axe = new Axe(10,10);
//        Dummy dummy = new Dummy(100,100);
//
//        axe.attack(dummy);
//
//        Assert.assertEquals(9,axe.getDurabilityPoints());
//    }
//
//    @Test(expected = IllegalStateException.class)
//    public void testAxeAttackThrowsIfAxeIsBroken(){
//        Axe brokenAxe = new Axe(10,0);
//        Dummy dummy = new Dummy(100,100);
//
//        brokenAxe.attack(dummy);
//    }
//
//}