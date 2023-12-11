package P15Exams.P14Exam.UnitTestings.java.P16HeroRepository;

import P15Exams.P14Exam.TaskForUnitTestings.java.P16HeroRepository.Hero;
import P15Exams.P14Exam.TaskForUnitTestings.java.P16HeroRepository.HeroRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class HeroRepositoryTests {

    private HeroRepository heroRepository;

    @Before
    public void setup(){
        this.heroRepository = new HeroRepository();
    }


    @Test(expected = NullPointerException.class)
    public void testCreate1(){
        this.heroRepository.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreate2(){
        Hero hero = new Hero("Venci",100);
        this.heroRepository.create(hero);
        this.heroRepository.create(hero);

    }

    @Test
    public void testCreate(){
        Hero hero = new Hero("Venci",100);
        Hero hero1 = new Hero("Stoyan",100);
        this.heroRepository.create(hero);
        Assert.assertEquals(1,this.heroRepository.getCount());
        this.heroRepository.create(hero1);
        Assert.assertEquals(2,this.heroRepository.getCount());
    }


    @Test(expected = NullPointerException.class)
    public void testRemove1(){
        this.heroRepository.remove(null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemove2(){
        this.heroRepository.remove("    ");
    }


    @Test
    public void testRemove3(){

        Hero hero = new Hero("Venci",100);
        Hero hero1 = new Hero("Stoyan",100);
        this.heroRepository.create(hero);
        this.heroRepository.create(hero1);
        this.heroRepository.remove("Venci");
        Assert.assertEquals(1,this.heroRepository.getCount());
    }

    @Test
    public void testRemove4(){
        Hero hero = new Hero("Venci",100);
        Hero hero1 = new Hero("Stoyan",100);
        this.heroRepository.create(hero);
        this.heroRepository.create(hero1);
        Assert.assertTrue(this.heroRepository.remove("Venci"));
        Assert.assertFalse(this.heroRepository.remove("Stefan"));
    }

    @Test
    public void testGetHeroWithHighestLevel(){
        Hero hero = new Hero("Venci",200);
        Hero hero1 = new Hero("Stoyan",100);
        this.heroRepository.create(hero);
        this.heroRepository.create(hero1);
        Hero returnedHero = this.heroRepository.getHeroWithHighestLevel();
        Assert.assertEquals("Venci",returnedHero.getName());
        Assert.assertEquals(200,returnedHero.getLevel());
    }

    @Test
    public void testGetHeroWhitName(){
        Hero hero = new Hero("Venci",200);
        Hero hero1 = new Hero("Stoyan",100);
        this.heroRepository.create(hero);
        this.heroRepository.create(hero1);
        Hero returnedHero = this.heroRepository.getHero("Venci");
        Assert.assertEquals("Venci",returnedHero.getName());
        Assert.assertEquals(200,returnedHero.getLevel());
    }

    @Test
    public void testGetHeroes(){
        Hero hero = new Hero("Venci",200);
        Hero hero1 = new Hero("Stoyan",100);
        this.heroRepository.create(hero);
        this.heroRepository.create(hero1);
        Collection<Hero> heroes = this.heroRepository.getHeroes();
        Assert.assertEquals(2,heroes.size());

    }
}
