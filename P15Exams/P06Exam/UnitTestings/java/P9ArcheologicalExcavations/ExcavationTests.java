package P15Exams.P06Exam.UnitTestings.java.P9ArcheologicalExcavations;

import P15Exams.P06Exam.TaskForUnitTestings.java.P9ArcheologicalExcavations.Archaeologist;
import P15Exams.P06Exam.TaskForUnitTestings.java.P9ArcheologicalExcavations.Excavation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class ExcavationTests {

    private Excavation excavation;


    @Before
    public void setup() {
        this.excavation = new Excavation("Venci", 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddArchaeologistThrow() {
        Archaeologist archaeologist1 = new Archaeologist("Toni", 100);
        Archaeologist archaeologist2 = new Archaeologist("Emil", 200);
        Archaeologist archaeologist3 = new Archaeologist("Stoyan", 100);
        excavation.addArchaeologist(archaeologist1);
        excavation.addArchaeologist(archaeologist2);
        excavation.addArchaeologist(archaeologist3);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddEqualArchaeologist() {
        Archaeologist archaeologist1 = new Archaeologist("Toni", 100);
        excavation.addArchaeologist(archaeologist1);
        excavation.addArchaeologist(archaeologist1);
    }

    @Test
    public void testRemove() {
        Archaeologist archaeologist1 = new Archaeologist("Toni", 100);
        Archaeologist archaeologist2 = new Archaeologist("Emil", 200);
        excavation.addArchaeologist(archaeologist1);
        Assert.assertEquals(100,archaeologist1.getEnergy(),0.01);
        excavation.addArchaeologist(archaeologist2);
        Assert.assertEquals(2, excavation.getCount());
        excavation.removeArchaeologist("Toni");
        Assert.assertEquals(1, excavation.getCount());
        Assert.assertEquals("Venci", excavation.getName());
        Assert.assertEquals(2, excavation.getCapacity());
        boolean isRemovedExpectedFalse = excavation.removeArchaeologist("Stoyan");
        boolean isRemovedExpectedTrue = excavation.removeArchaeologist("Emil");
        Assert.assertFalse(isRemovedExpectedFalse);
        Assert.assertTrue(isRemovedExpectedTrue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityLessZero(){
        excavation = new Excavation("Venci",-5);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNullName(){
        excavation = new Excavation(null,10);
    }
    @Test(expected = NullPointerException.class)
    public void testSetWhiteSpaceName(){
        excavation = new Excavation("  " ,10);
    }
}
