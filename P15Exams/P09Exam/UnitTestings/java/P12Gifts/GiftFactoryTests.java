package P15Exams.P09Exam.UnitTestings.java.P12Gifts;

import P15Exams.P09Exam.TaskForUnitTestings.java.P12Gifts.Gift;
import P15Exams.P09Exam.TaskForUnitTestings.java.P12Gifts.GiftFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GiftFactoryTests {

    private GiftFactory giftFactory;

    @Before
    public void setup(){
        this.giftFactory = new GiftFactory();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateGiftThrow(){
        Gift gift = new Gift("Smile",10.00);
        giftFactory.createGift(gift);
        giftFactory.createGift(gift);

    }

    @Test
    public void testCreateGift(){
        Gift gift = new Gift("Smile",10.00);
        giftFactory.createGift(gift);
        Assert.assertEquals(1,giftFactory.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveGiftThrow1(){
        Gift gift = new Gift("Smile",10.00);
        giftFactory.createGift(gift);
        giftFactory.removeGift(null);
    }
    @Test(expected = NullPointerException.class)
    public void testRemoveGiftThrow2(){
        Gift gift = new Gift("Smile",10.00);
        giftFactory.createGift(gift);
        giftFactory.removeGift("  ");
    }

    @Test
    public void testRemoveGift(){
        Gift gift = new Gift("Smile",10.00);
        Gift gift1 = new Gift("Sad",10.00);
        giftFactory.createGift(gift);
        giftFactory.createGift(gift1);
        Assert.assertEquals(2,giftFactory.getCount());
        giftFactory.removeGift("Sad");
        Assert.assertEquals(1,giftFactory.getCount());
    }

    @Test
    public void testGetPresentWithLeastMagic(){
        Gift gift = new Gift("Smile",20.00);
        Gift gift1 = new Gift("Sad",10.00);
        giftFactory.createGift(gift);
        giftFactory.createGift(gift1);

        Gift giftReturned = giftFactory.getPresentWithLeastMagic();
        Assert.assertEquals("Sad",giftReturned.getType());
        Assert.assertEquals(10,giftReturned.getMagic(),0.01);
    }

    @Test
    public void testGetPresent(){
        Gift gift = new Gift("Smile",20.00);
        Gift gift1 = new Gift("Sad",10.00);
        giftFactory.createGift(gift);
        giftFactory.createGift(gift1);

        Gift giftReturned = giftFactory.getPresent("Smile");
        Assert.assertEquals("Smile",giftReturned.getType());
    }

    @Test
    public void testGetPresents(){
        Gift gift = new Gift("Smile",20.00);
        Gift gift1 = new Gift("Sad",10.00);
        giftFactory.createGift(gift);
        giftFactory.createGift(gift1);

        Assert.assertEquals(2,giftFactory.getPresents().size());
    }
}
