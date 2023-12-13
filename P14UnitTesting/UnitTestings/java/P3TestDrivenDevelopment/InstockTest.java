package P14UnitTesting.UnitTestings.java.P3TestDrivenDevelopment;

import P14UnitTesting.Tasks.java.P3TestDrivenDevelopment.Instock;
import P14UnitTesting.Tasks.java.P3TestDrivenDevelopment.Product;
import P14UnitTesting.Tasks.java.P3TestDrivenDevelopment.ProductStock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class InstockTest {


    private ProductStock stock;

    @Before
    public void setup(){
        this.stock = new Instock();
    }

    private void fill(){
        Product product1 = new Product("water",1.20,2);
        Product product2 = new Product("lemon",2.20,3);
        Product product3 = new Product("bread",3.20,4);
        this.stock.add(product1);
        this.stock.add(product2);
        this.stock.add(product3);
    }


    @Test
    public void testContainsAndGetCount(){
        Product product1 = new Product("water",1.20,2);
        Assert.assertFalse(this.stock.contains(product1));
        stock.add(product1);
        Assert.assertTrue(this.stock.contains(product1));
        Assert.assertEquals("water",product1.getLabel());
    }

    @Test
    public void testGetCount(){
        Assert.assertEquals(0,this.stock.getCount());
        fill();
        Assert.assertEquals(3,this.stock.getCount());
    }


    @Test
    public void testFind(){
        fill();
        Product product = this.stock.find(1);
        Assert.assertEquals("lemon",product.getLabel());
        Assert.assertEquals(3,product.getQuantity());

    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindThrowException(){
        fill();
        this.stock.find(4);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindThrowExceptionNegativeNumber(){
        fill();
        this.stock.find(-1);

    }


    @Test
    public void testChangeQuantity(){
        fill();
        Product product = this.stock.find(1);
        this.stock.changeQuantity("lemon",9);
        Assert.assertEquals(9,product.getQuantity());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeQuantityInvalidProductThrowException(){
        fill();
        this.stock.changeQuantity("watermelon",100);
    }



    @Test
    public void testFindByLabel(){
        fill();
        Product product1 = this.stock.find(1);
        Product product2 = this.stock.findByLabel("lemon");

        Assert.assertEquals(product1.getLabel(),product2.getLabel());
        Assert.assertEquals(product1.getQuantity(),product2.getQuantity());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindByLabelThrowException(){
        fill();
        this.stock.findByLabel("orange");
    }


    @Test
    public void testFindFirstByAlphabeticalOrderCorrectCountProducts(){
        fill();
        Iterable<Product> iterable = stock.findFirstByAlphabeticalOrder(2);

        int count = 0;
        for (Product product : iterable) {
            count++;
        }
        Assert.assertEquals(2,count);

    }

    @Test
    public void testFindFirstByAlphabeticalOrderCorrectSorted(){

        List<String> expectedProductsLabels = new ArrayList<>();


        Product product1 = new Product("water",1.20,2);
        Product product2 = new Product("lemon",2.20,3);
        Product product3 = new Product("bread",3.20,4);
        this.stock.add(product1);
        this.stock.add(product2);
        this.stock.add(product3);

        expectedProductsLabels.add(product1.getLabel());
        expectedProductsLabels.add(product2.getLabel());
        expectedProductsLabels.add(product3.getLabel());

        expectedProductsLabels = expectedProductsLabels.
                stream()
                .sorted()
                .collect(Collectors.toList());

        Iterable<Product> iterable = stock.findFirstByAlphabeticalOrder(3);
        List<String> returnedProductLabels = new ArrayList<>();

        for (Product product : iterable) {
            returnedProductLabels.add(product.getLabel());
        }

        Assert.assertEquals(expectedProductsLabels,returnedProductLabels);

    }

    @Test
    public void testFindFirstByAlphabeticalOrderThrowException(){
        fill();
        Iterable<Product> iterable = this.stock.findFirstByAlphabeticalOrder(5);
        List<Product> returnedProducts = getProductsFromIterable(iterable);

        Assert.assertEquals(0,returnedProducts.size());
    }



    @Test
    public void testFindAllInPriceRange(){
        fill();

        Iterable<Product> returnedProducts = this.stock.findAllInRange(1.10,3.10);

        int count = 0;
        for (Product returnedProduct : returnedProducts) {
            count++;
        }

        Assert.assertEquals(2, count);

    }


    @Test
    public void testFindAllInPriceRangeCorrectOrder(){
        fill();

        Iterable<Product> iterable = this.stock.findAllInRange(1.10,3.10);

        List<Product> returnedProducts = getProductsFromIterable(iterable);

        Assert.assertEquals("lemon",returnedProducts.get(0).getLabel());
    }

    @Test
    public void testFindAllInPriceRangeNoneMatch(){
        fill();
        Iterable<Product> iterable = this.stock.findAllInRange(10,30);

        Assert.assertFalse(iterable.iterator().hasNext());
    }


    @Test
    public void testFindAllByPriceCorrectProducts(){
        fill();

        Iterable<Product> iterable = this.stock.findAllByPrice(3.20);

        List<Product> returnedProducts = getProductsFromIterable(iterable);

        Assert.assertEquals(1,returnedProducts.size());
        Assert.assertEquals("bread",returnedProducts.get(0).getLabel());

    }

    private static List<Product> getProductsFromIterable(Iterable<Product> iterable) {
        List<Product> returnedProducts = new ArrayList<>();

        for (Product product : iterable) {
            returnedProducts.add(product);
        }
        return returnedProducts;
    }

    @Test
    public void testFindAllByPriceEmptyProducts(){
        fill();

        Iterable<Product> iterable = this.stock.findAllByPrice(13.20);
        Assert.assertFalse(iterable.iterator().hasNext());
    }

    @Test
    public void testFindAllByQuantityCorrectProducts(){
        fill();

        Iterable<Product> iterable = this.stock.findAllByQuantity(3);

        List<Product> returnedProducts = getProductsFromIterable(iterable);

        Assert.assertEquals(1,returnedProducts.size());
        Assert.assertEquals("lemon",returnedProducts.get(0).getLabel());

    }

    @Test
    public void testFindAllByQuantityEmptyProducts(){
        fill();

        Iterable<Product> iterable = this.stock.findAllByQuantity(30);
        Assert.assertFalse(iterable.iterator().hasNext());
    }



    @Test
    public void testIterator(){
        fill();
        Iterator<Product> iterator = this.stock.iterator();
        List<Product> returnedProducts = new ArrayList<>();
        while (iterator.hasNext()){
            returnedProducts.add(iterator.next());
        }

        Assert.assertEquals("water",returnedProducts.get(0).getLabel());
        Assert.assertEquals("lemon",returnedProducts.get(1).getLabel());
        Assert.assertEquals("bread",returnedProducts.get(2).getLabel());


    }

    @Test
    public void testFindFirstMostExpensiveProducts(){

        fill();

        Iterable<Product> iterable = this.stock.findFirstMostExpensiveProducts(2);

        List<Product> returnedProducts = getProductsFromIterable(iterable);

        Assert.assertEquals("bread",returnedProducts.get(0).getLabel());
        Assert.assertEquals("lemon",returnedProducts.get(1).getLabel());

    }


    @Test(expected = IllegalArgumentException.class)
    public void findFirstMostExpensiveProductsThrowException(){

        stock.findFirstMostExpensiveProducts(6);

    }

    @Test
    public void testGetProducts() {


        fill();

        Product product4 = new Product("cheese",1.00,3);
        Product product5 = new Product("cherry",2.00,4);
        Product product6 = new Product("carrot",3.00,5);
        List<Product> newProductsList = new ArrayList<>();
        newProductsList.add(product4);
        newProductsList.add(product5);
        newProductsList.add(product6);

        stock.setProducts(newProductsList);
        List<Product> newList = stock.getProducts();

        newList.get(0).setLabel("tomato");
        newList.get(0).setPrice(10);

        // Get the products using getProducts

        // Test if the retrieved products match the original list
        Assert.assertEquals("tomato",newList.get(0).getLabel());
        Assert.assertEquals("cherry",newList.get(1).getLabel());
        Assert.assertEquals("carrot",newList.get(2).getLabel());




    }
}
































