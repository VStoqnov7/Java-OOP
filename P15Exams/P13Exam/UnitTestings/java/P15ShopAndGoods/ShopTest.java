package P15Exams.P13Exam.UnitTestings.java.P15ShopAndGoods;


import P15Exams.P13Exam.TaskForUnitTestings.java.P15ShopAndGoods.Goods;
import P15Exams.P13Exam.TaskForUnitTestings.java.P15ShopAndGoods.Shop;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ShopTest {

    private Shop shop;


    @Before
    public void setup() {
        this.shop = new Shop();
    }


    @Test
    public void testGetShelves() {
        Map<String, Goods> expected = new LinkedHashMap<>();
        expected.put("Shelves1", null);
        expected.put("Shelves2", null);
        expected.put("Shelves3", null);
        expected.put("Shelves4", null);
        expected.put("Shelves5", null);
        expected.put("Shelves6", null);
        expected.put("Shelves7", null);
        expected.put("Shelves8", null);
        expected.put("Shelves9", null);
        expected.put("Shelves10", null);
        expected.put("Shelves11", null);
        expected.put("Shelves12", null);
        Assert.assertEquals(expected,this.shop.getShelves());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodsThrow1() throws OperationNotSupportedException {
        Goods goods = new Goods("Venci","10");
        this.shop.addGoods("Simeon",goods);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodsThrow2() throws OperationNotSupportedException {
        Goods goods1 = new Goods("Venci","10");
        Goods goods2 = new Goods("Stoyan","10");
        this.shop.addGoods("Shelves1",goods1);
        this.shop.addGoods("Shelves1",goods2);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddGoodsThrow3() throws OperationNotSupportedException {
        this.shop.addGoods("Shelves1",null);
    }

    @Test
    public void testAddGoodsThrow() throws OperationNotSupportedException {
        Goods goods1 = new Goods("Venci","10");
        this.shop.addGoods("Shelves1",goods1);
        Assert.assertEquals(goods1,this.shop.getShelves().get("Shelves1"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoods1(){
        Goods goods1 = new Goods("Venci","10");
        this.shop.removeGoods("Venci",goods1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoods2(){
        Goods goods1 = new Goods("Venci","10");
        this.shop.removeGoods("Shelves1",goods1);
    }

    @Test
    public void testRemoveGoods() throws OperationNotSupportedException {
        Goods goods = new Goods("Venci","10");
        Assert.assertEquals("Venci",goods.getName());
        this.shop.addGoods("Shelves1",goods);
        String expected = "Goods: 10 is removed successfully!";
        String returned = this.shop.removeGoods("Shelves1",goods);
        Assert.assertEquals(expected,returned);
        Assert.assertNull(this.shop.getShelves().get("Shelves1"));
    }
}