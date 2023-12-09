package P15Exams.P07Exam.UnitTestings.java.P10Football;

import P15Exams.P07Exam.TaskForUnitTestings.java.P10Football.FootballTeam;
import P15Exams.P07Exam.TaskForUnitTestings.java.P10Football.Footballer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FootballTeamTests {


    private FootballTeam footballTeam;


    @Before
    public void setup(){
        this.footballTeam = new FootballTeam("Venci",2);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameNull(){
        this.footballTeam = new FootballTeam(null,10);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameWhiteSpace(){
        this.footballTeam = new FootballTeam("  ", 10);

    }


    @Test(expected = IllegalArgumentException.class)
    public void testSetVacantPositionLessZero(){
        this.footballTeam = new FootballTeam("Venci",-10);
    }

    @Test
    public void testAddFootballer(){
        List<Footballer> footballerTest = new ArrayList<>();

        Footballer footballer1 = new Footballer("Stoyan");
        Footballer footballer2 = new Footballer("Simeon");
        footballerTest.add(footballer1);
        footballerTest.add(footballer2);

        this.footballTeam.addFootballer(footballer1);
        this.footballTeam.addFootballer(footballer2);
        Assert.assertEquals(footballerTest.size(),this.footballTeam.getCount());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFootballerThrow(){
        Footballer footballer1 = new Footballer("Stoyan");
        Footballer footballer2 = new Footballer("Simeon");
        Footballer footballer3 = new Footballer("Toni");
        this.footballTeam.addFootballer(footballer1);
        this.footballTeam.addFootballer(footballer2);
        this.footballTeam.addFootballer(footballer3);
    }

    @Test
    public void testGetStatistics(){
        Footballer footballer1 = new Footballer("Stoyan");
        Footballer footballer2 = new Footballer("Simeon");
        this.footballTeam.addFootballer(footballer1);
        this.footballTeam.addFootballer(footballer2);

        String expected = "The footballer Stoyan, Simeon is in the team Venci.";
        String returned = this.footballTeam.getStatistics();
        Assert.assertEquals(expected,returned);
    }

    @Test
    public void testRemoveFootBaller(){
        Footballer footballer1 = new Footballer("Stoyan");
        Footballer footballer2 = new Footballer("Simeon");
        this.footballTeam.addFootballer(footballer1);
        this.footballTeam.addFootballer(footballer2);
        this.footballTeam.removeFootballer("Stoyan");
        Assert.assertEquals(1,this.footballTeam.getCount());
        Assert.assertEquals(2,this.footballTeam.getVacantPositions());
        Assert.assertEquals("Venci",this.footballTeam.getName());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveFootBallerThrow(){
        Footballer footballer1 = new Footballer("Stoyan");
        Footballer footballer2 = new Footballer("Simeon");
        this.footballTeam.addFootballer(footballer1);
        this.footballTeam.addFootballer(footballer2);
        this.footballTeam.removeFootballer("Asen");
    }

    @Test
    public void testFootBallerForSale(){
        Footballer footballer1 = new Footballer("Stoyan");
        Footballer footballer2 = new Footballer("Simeon");
        this.footballTeam.addFootballer(footballer1);
        this.footballTeam.addFootballer(footballer2);
        this.footballTeam.footballerForSale("Stoyan");
        Assert.assertFalse(footballer1.isActive());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFootBallerForSaleThrow(){
        Footballer footballer1 = new Footballer("Stoyan");
        Footballer footballer2 = new Footballer("Simeon");
        this.footballTeam.addFootballer(footballer1);
        this.footballTeam.addFootballer(footballer2);
        this.footballTeam.footballerForSale("Stefan");
        Assert.assertTrue(footballer1.isActive());
    }
}
