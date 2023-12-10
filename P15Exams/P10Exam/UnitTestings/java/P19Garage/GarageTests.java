package P15Exams.P10Exam.UnitTestings.java.P19Garage;

import P15Exams.P10Exam.TaskForUnitTestings.java.P19Garage.Car;
import P15Exams.P10Exam.TaskForUnitTestings.java.P19Garage.Garage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GarageTests {

    private Garage garage;


    @Before
    public void setup(){
        this.garage = new Garage();

    }

    @Test
    public void testAddCar(){
        Car car = new Car("Mercedes",100,1000);
        this.garage.addCar(car);
        Assert.assertEquals(1,this.garage.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCarThrow(){
        this.garage.addCar(null);
    }



    @Test
    public void testFindAllCarsWithMaxSpeedAbove(){
        Car car1 = new Car("Mercedes",100,1000);
        Car car2 = new Car("Passat",200,1000);
        Car car3 = new Car("Fiat",300,1000);
        this.garage.addCar(car1);
        this.garage.addCar(car2);
        this.garage.addCar(car3);
        List<Car> carsReturned = this.garage.findAllCarsWithMaxSpeedAbove(100);
        Assert.assertEquals(2,carsReturned.size());
    }

    @Test
    public void testGetCars(){
        Car car1 = new Car("Mercedes",100,1000);
        Car car2 = new Car("Passat",200,1000);
        Car car3 = new Car("Fiat",300,1000);
        this.garage.addCar(car1);
        this.garage.addCar(car2);
        this.garage.addCar(car3);

        List<Car> expected = new ArrayList<>();
        expected.add(car1);
        expected.add(car2);
        expected.add(car3);
        Assert.assertEquals(expected,this.garage.getCars());
    }

    @Test
    public void testGetTheMostExpensiveCar(){
        Car car1 = new Car("Mercedes",100,1000);
        Car car2 = new Car("Passat",200,2000);
        Car car3 = new Car("Fiat",300,3000);
        this.garage.addCar(car1);
        this.garage.addCar(car2);
        this.garage.addCar(car3);

        Car car = this.garage.getTheMostExpensiveCar();
        Assert.assertEquals(car3,car);
    }

    @Test
    public void testFindAllCarsByBrand(){
        Car car1 = new Car("Mercedes",100,1000);
        Car car2 = new Car("Passat",200,2000);
        Car car3 = new Car("Passat",300,3000);
        this.garage.addCar(car1);
        this.garage.addCar(car2);
        this.garage.addCar(car3);
        Assert.assertEquals(2,this.garage.findAllCarsByBrand("Passat").size());
    }
}