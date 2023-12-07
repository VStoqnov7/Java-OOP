package P14UnitTesting.UnitTestings.java.P01unitTesting;

import P14UnitTesting.Tasks.java.P01unitTesting.Hero;
import P14UnitTesting.Tasks.java.P01unitTesting.Target;
import P14UnitTesting.Tasks.java.P01unitTesting.Weapon;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class HeroTest {
//    private static class FakeAxe implements Weapon{
//
//        @Override
//        public int getAttackPoints() {
//            return 0;
//        }
//
//        @Override
//        public int getDurabilityPoints() {
//            return 0;
//        }
//
//        @Override
//        public void attack(Target target) {
//
//        }
//    }
//
//    private static class FakeTarget implements Target{
//
//        @Override
//        public int getHealth() {
//            return 0;
//        }
//
//        @Override
//        public void takeAttack(int attackPoints) {
//
//        }
//
//        @Override
//        public int giveExperience() {
//            return 100;
//        }
//
//        @Override
//        public boolean isDead() {
//            return true;
//        }
//    }
//    private static class AliveTarget implements Target{
//
//        @Override
//        public int getHealth() {
//            return 0;
//        }
//
//        @Override
//        public void takeAttack(int attackPoints) {
//
//        }
//
//        @Override
//        public int giveExperience() {
//            return 100;
//        }
//
//        @Override
//        public boolean isDead() {
//            return false;
//        }
//    }
    //    private Hero hero;
//
//    @Before
//    public void setup(){
//        this.hero = new Hero("Pesho",new FakeTarget );
//    }
//
//
//
//    @Test
//    public void testUponTargetKillHeroGetsXp(){
//        this.hero.attack(new FakeTarget());
//        Assert.assertEquals(100,this.hero.getExperience());
//
//    }
//
//    @Test
//    public void testUponTargetAttackWhileTargetIsStillAliveHeroGetsNoXP(){
//        this.hero.attack(new AliveTarget());
//        Assert.assertEquals(0,this.hero.getExperience());
//
//
//    }
    private Hero hero;

    @Before
    public void setup(){
        Weapon mock = Mockito.mock(Weapon.class);
        this.hero = new Hero("Pesho",mock );
    }



    @Test
    public void testUponTargetKillHeroGetsXp(){
        Target mockedTarget = Mockito.mock(Target.class);
        Mockito.when(mockedTarget.isDead()).thenReturn(true);
        Mockito.when(mockedTarget.giveExperience()).thenReturn(100);
        this.hero.attack(mockedTarget);
        Assert.assertEquals(100,this.hero.getExperience());

    }

    @Test
    public void testUponTargetAttackWhileTargetIsStillAliveHeroGetsNoXP(){
        Target mockedTarget = Mockito.mock(Target.class);
        Mockito.when(mockedTarget.isDead()).thenReturn(false);
        Mockito.when(mockedTarget.giveExperience()).thenReturn(100);

        this.hero.attack(mockedTarget);
        Assert.assertEquals(0,this.hero.getExperience());


    }

}