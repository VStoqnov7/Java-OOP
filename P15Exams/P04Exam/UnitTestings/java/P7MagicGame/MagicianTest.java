package P15Exams.P04Exam.UnitTestings.java.P7MagicGame;

import P15Exams.P04Exam.TaskForUnitTestings.java.P7MagicGame.Magic;
import P15Exams.P04Exam.TaskForUnitTestings.java.P7MagicGame.Magician;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MagicianTest {
    private Magician magician;

    @Before
    public void setup(){
        this.magician = new Magician("Venci",100);
        Magic magic = new Magic("Toni",100);
        this.magician.addMagic(magic);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameNullThrow(){
        this.magician = new Magician(null,100);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameWhiteSpaceThrow(){
        this.magician = new Magician("  ",100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetHealthLessZeroThrow(){
        this.magician = new Magician("Denis",- 3);
    }

    @Test
    public void testTakeDamage(){

        this.magician.takeDamage(99);
        Assert.assertEquals(1,this.magician.getHealth());
        Assert.assertEquals("Venci",this.magician.getUsername());


    }

    @Test(expected = IllegalStateException.class)
    public void testTakeDamageLessZero(){
        this.magician.takeDamage(101);
        this.magician.takeDamage(10);
    }

    @Test(expected = NullPointerException.class)
    public void testAddMagicNullThrow(){
        this.magician.addMagic(null);
    }

    @Test
    public void testRemoveMagic(){
        Magic magic1 = new Magic("Emil",101);
        boolean expectedFalse = this.magician.removeMagic(magic1);
        Assert.assertFalse(expectedFalse);
        this.magician.addMagic(magic1);
        boolean expectedTrue = this.magician.removeMagic(magic1);
        Assert.assertTrue(expectedTrue);
    }

    @Test
    public void testGetMagicByName(){
        Magic magic1 = new Magic("Emil",101);
        Magic magic2 = new Magic("Emilia",100);
        this.magician.addMagic(magic1);
        this.magician.addMagic(magic2);

        Magic magic = this.magician.getMagic("Toni");
        Assert.assertEquals("Toni",magic.getName());
        Assert.assertEquals(100,magic.getBullets());
        Assert.assertEquals(3,this.magician.getMagics().size());

    }
}