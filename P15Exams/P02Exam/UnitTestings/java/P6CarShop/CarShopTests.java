package P15Exams.P02Exam.UnitTestings.java.P6CarShop;

import P15Exams.P02Exam.TaskForUnitTestings.java.P6CarShop.Car;
import P15Exams.P02Exam.TaskForUnitTestings.java.P6CarShop.CarShop;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CarShopTests {

    private CarShop carShop;


    @Before
    public void setup(){
        this.carShop = new CarShop();

    }
    private void fill(){
        Car car1 = new Car("Mercedes",100,1000);
        Car car2 = new Car("Fiat",200,2000);
        Car car3 = new Car("Renault",300,3000);
        carShop.add(car1);
        carShop.add(car2);
        carShop.add(car3);
    }

    @Test
    public void testGetCars(){
        fill();
        List<Car> expectedCars = new ArrayList<>();
        Car car1 = new Car("Mercedes",100,1000);
        Car car2 = new Car("Fiat",200,2000);
        Car car3 = new Car("Renault",300,3000);
        expectedCars.add(car1);
        expectedCars.add(car2);
        expectedCars.add(car3);

        List<Car> returnedCars = carShop.getCars();
        Assert.assertEquals(expectedCars.size(),returnedCars.size());
        Assert.assertEquals(3,expectedCars.size());
        Assert.assertEquals("Mercedes",returnedCars.get(0).getModel());
        Assert.assertEquals(3,carShop.getCount());
    }

    @Test
    public void testFindAllCarsWithMaxHorsePower(){
        fill();
        List<Car> returnedCars = carShop.findAllCarsWithMaxHorsePower(100);

        Assert.assertEquals(2,returnedCars.size());
        Assert.assertEquals("Fiat",returnedCars.get(0).getModel());
        Assert.assertEquals(2000,returnedCars.get(0).getPrice(),0.01);
        Assert.assertEquals("Renault",returnedCars.get(1).getModel());
        Assert.assertEquals(3000,returnedCars.get(1).getPrice(),0.01);
    }

    @Test(expected = NullPointerException.class)
    public void testAddCarNullThrowException(){
        fill();
        carShop.add(null);
    }

    @Test
    public void testRemoveCar(){
        fill();
        Car car = new Car("Fabia",400,4000);
        boolean returnedFalse = carShop.remove(car);
        Assert.assertFalse(returnedFalse);
        carShop.add(car);
        boolean returnedTrue = carShop.remove(car);
        Assert.assertTrue(returnedTrue);
    }

    @Test
    public void testGetMostLuxuryCar(){
        fill();
        Car car = carShop.getTheMostLuxuryCar();
        Assert.assertEquals("Renault",car.getModel());
        Assert.assertEquals(3000,car.getPrice(),0.01);
    }

    @Test
    public void testFindAllCarsByModel(){
        fill();
        Car car1 = new Car("Mercedes",300,11000);
        carShop.add(car1);
        List<Car> cars = carShop.findAllCarByModel("Mercedes");
        Assert.assertEquals(2,cars.size());
    }
}





















