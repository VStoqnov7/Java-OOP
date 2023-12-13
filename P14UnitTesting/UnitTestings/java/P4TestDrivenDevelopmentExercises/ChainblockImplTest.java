package P14UnitTesting.UnitTestings.java.P4TestDrivenDevelopmentExercises;

import P14UnitTesting.Tasks.java.P4TestDrivenDevelopmentExercises.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ChainblockImplTest {

    private Chainblock database;
    private List<Transaction> transactions;

    @Before
    public void setup() {
        this.database = new ChainblockImpl();
        this.transactions = new ArrayList<>();
        Transaction transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL,"Desi","Stoyan",150.90);
        Transaction transaction2 = new TransactionImpl(2,TransactionStatus.SUCCESSFUL,"Desi","Emil",120.90);
        Transaction transaction3 = new TransactionImpl(3,TransactionStatus.FAILED,"Petar","Emil",100.90);
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
    }

    private void fillDataBase() {
        this.database.add(this.transactions.get(2));
        this.database.add(this.transactions.get(1));
        this.database.add(this.transactions.get(0));
    }

    @Test
    public void testAdd(){
        Assert.assertEquals(0,database.getCount());
        this.database.add(this.transactions.get(0));
        Assert.assertEquals(1,database.getCount());
    }

    @Test
    public void testAddExistingTransaction(){
        Assert.assertEquals(0,database.getCount());
        this.database.add(this.transactions.get(0));
        Assert.assertEquals(1,database.getCount());
        this.database.add(this.transactions.get(0));
        Assert.assertEquals(1,database.getCount());
    }

    @Test
    public void testContains(){

        Assert.assertFalse(this.database.contains(this.transactions.get(0)));
        Assert.assertFalse(this.database.contains(this.transactions.get(0).getId()));
        this.database.add(this.transactions.get(0));
        Assert.assertTrue(this.database.contains(this.transactions.get(0)));
        Assert.assertTrue(this.database.contains(this.transactions.get(0).getId()));
    }

    @Test
    public void testChangeTransactionStatus(){
        this.database.add(this.transactions.get(0));
        this.database.changeTransactionStatus(1,TransactionStatus.ABORTED);
        Assert.assertEquals(TransactionStatus.ABORTED,this.transactions.get(0).getStatus());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeTransactionStatusInvalidId(){
        this.database.add(this.transactions.get(0));
        this.database.changeTransactionStatus(2,TransactionStatus.ABORTED);
    }

    @Test
    public void testRemoveTransactionById(){
        this.database.add(this.transactions.get(0));
        this.database.add(this.transactions.get(1));
        Assert.assertEquals(2,this.database.getCount());
        int id = this.transactions.get(0).getId();
        this.database.removeTransactionById(id);
        Assert.assertEquals(1,this.database.getCount());
        Assert.assertFalse(this.database.contains(id));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveTransactionByIdThrowException(){
        this.database.add(this.transactions.get(0));
        this.database.removeTransactionById(5);
    }

    @Test
    public void testGetById(){
        Transaction transaction = this.transactions.get(0);
        this.database.add(transaction);
        Transaction returnedTransaction = this.database.getById(transaction.getId());
        Assert.assertEquals(transaction.getId(),returnedTransaction.getId());
        Assert.assertEquals(transaction.getStatus(),returnedTransaction.getStatus());
        Assert.assertEquals(transaction.getFrom(),returnedTransaction.getFrom());
        Assert.assertEquals(transaction.getTo(),returnedTransaction.getTo());
        Assert.assertEquals(transaction.getAmount(),returnedTransaction.getAmount(),0.01);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByIdThrowException(){
        this.database.add(this.transactions.get(0));
        this.database.getById(5);
    }

    @Test
    public void testGetByTransactionStatus(){
        List<Transaction> successfulTransactions = this.transactions
                .stream()
                .filter(tr -> tr.getStatus() == TransactionStatus.SUCCESSFUL)
                .collect(Collectors.toList());

        fillDataBase();

        Iterable<Transaction> result =this.database.getByTransactionStatus(TransactionStatus.SUCCESSFUL);
        List<Transaction> returnedTransactions = new ArrayList<>();
        result.forEach(returnedTransactions::add);
        Assert.assertEquals(successfulTransactions.size(),returnedTransactions.size());
        Assert.assertEquals(successfulTransactions,returnedTransactions);

        returnedTransactions.forEach(tr -> Assert.assertEquals(TransactionStatus.SUCCESSFUL,tr.getStatus()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByTransactionStatusThrowException(){
        fillDataBase();
        this.database.getByTransactionStatus(TransactionStatus.UNAUTHORIZED);
    }

    @Test
    public void testGetAllSendersWithTransactionStatus(){
        fillDataBase();
        this.database.add(new TransactionImpl(4,TransactionStatus.SUCCESSFUL,"Petar","Desi",980.40));

        Iterable<String> result = this.database.getAllSendersWithTransactionStatus(TransactionStatus.SUCCESSFUL);

        List<String> returnedSenders = new ArrayList<>();
        result.forEach(returnedSenders::add);
        Assert.assertEquals(3,returnedSenders.size());
        Assert.assertEquals("Petar",returnedSenders.get(0));
        Assert.assertEquals("Desi",returnedSenders.get(1));
        Assert.assertEquals("Desi",returnedSenders.get(2));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetAllSendersWithTransactionStatusThrowException(){
        fillDataBase();
        this.database.getAllSendersWithTransactionStatus(TransactionStatus.UNAUTHORIZED);
    }
    @Test
    public void testGetAllReceiversWithTransactionStatus(){
        fillDataBase();
        this.database.add(new TransactionImpl(4,TransactionStatus.SUCCESSFUL,"Petar","Desi",980.40));

        Iterable<String> result = this.database.getAllReceiversWithTransactionStatus(TransactionStatus.SUCCESSFUL);

        List<String> returnedSenders = new ArrayList<>();
        result.forEach(returnedSenders::add);
        Assert.assertEquals(3,returnedSenders.size());
        Assert.assertEquals("Desi",returnedSenders.get(0));
        Assert.assertEquals("Stoyan",returnedSenders.get(1));
        Assert.assertEquals("Emil",returnedSenders.get(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAllReceiversWithTransactionStatusThrowException(){
        fillDataBase();
        this.database.getAllReceiversWithTransactionStatus(TransactionStatus.UNAUTHORIZED);
    }

    @Test
    public void testGetAllOrderedByAmountDescendingThenById(){
        fillDataBase();

        Iterable<Transaction> result = this.database.getAllOrderedByAmountDescendingThenById();
        List<Transaction> returned = new ArrayList<>();
        result.forEach(returned::add);
        List<Transaction> expected = this.transactions
                .stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed()
                        .thenComparing(Transaction::getId))
                .collect(Collectors.toList());
        Assert.assertEquals(expected,returned);

    }

    @Test
    public void getBySenderOrderedByAmountDescending(){
        fillDataBase();

        Iterable<Transaction> result = this.database.getBySenderOrderedByAmountDescending("Desi");
        List<Transaction> returned = new ArrayList<>();
        result.forEach(returned::add);

        //  new TransactionImpl(1,TransactionStatus.SUCCESSFUL,"Desi","Stoyan",150.90);
        //  new TransactionImpl(2,TransactionStatus.SUCCESSFUL,"Desi","Emil",120.90);
        //  new TransactionImpl(3,TransactionStatus.FAILED,"Petar","Emil",100.90);
        List<Transaction> expected = this.transactions
                .stream()
                .sorted(Comparator.comparing(Transaction::getFrom))
                .filter(name -> name.getFrom().equals("Desi"))
                .collect(Collectors.toList());
        Assert.assertEquals(expected,returned);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getBySenderOrderedByAmountDescendingThrowException(){
        fillDataBase();
        this.database.getBySenderOrderedByAmountDescending("Elena");
    }

    @Test
    public void testGetByReceiverOrderedByAmountThenById(){
        fillDataBase();
        Iterable<Transaction> result = this.database.getByReceiverOrderedByAmountThenById("Emil");
        List<Transaction> returned = new ArrayList<>();
        result.forEach(returned::add);


        List<Transaction> expected = this.transactions
                .stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed()
                 .thenComparing(Transaction::getId).reversed())
                .filter(name -> name.getTo().equals("Emil"))
                .collect(Collectors.toList());

        Assert.assertEquals(expected,returned);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByReceiverOrderedByAmountThenByIdThrowException(){
        fillDataBase();
        this.database.getByReceiverOrderedByAmountThenById("Eli");
    }

    @Test
    public void testGetByTransactionStatusAndMaximumAmount(){

        fillDataBase();
        Transaction transaction4 = new TransactionImpl(4,TransactionStatus.SUCCESSFUL,"Petar","Emil",120.90);
        this.database.add(transaction4);

        Iterable<Transaction> result = this.database.getByTransactionStatusAndMaximumAmount(TransactionStatus.SUCCESSFUL,120.90);
        List<Transaction> returned = new ArrayList<>();
        result.forEach(returned::add);

        this.transactions.add(transaction4);

        List<Transaction> expected = transactions
                .stream()
                .filter(transaction -> transaction.getStatus().equals(TransactionStatus.SUCCESSFUL))
                .filter(transaction -> transaction.getAmount() <= 120.90)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .collect(Collectors.toList());


        Assert.assertEquals(returned,expected);
        Assert.assertEquals(2,returned.get(0).getId());

    }

    @Test
    public void testGetBySenderAndMinimumAmountDescending(){



        fillDataBase();

        Iterable<Transaction> result = this.database.getBySenderAndMinimumAmountDescending("Desi",120.00);
        List<Transaction> returned = new ArrayList<>();
        result.forEach(returned::add);

        List<Transaction> expected = transactions
                .stream()
                .filter(transaction -> transaction.getFrom().equals("Desi"))
                .filter(transaction -> transaction.getAmount() > 120.00)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .collect(Collectors.toList());
        Assert.assertEquals(returned,expected);
        Assert.assertEquals(2,returned.get(0).getId());
        Assert.assertEquals(2,returned.size());


    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBySenderAndMinimumAmountDescendingThrowException(){
        fillDataBase();
        this.database.getBySenderAndMinimumAmountDescending("Alex",300.00);

    }


    @Test
    public void testGetByReceiverAndAmountRange(){

        fillDataBase();

        Transaction transaction4 = new TransactionImpl(4,TransactionStatus.SUCCESSFUL,"Desi","Emil",110.80);
        this.database.add(transaction4);
        this.transactions.add(transaction4);

        Iterable<Transaction> result = this.database.getByReceiverAndAmountRange("Emil",100.00,120.90);
        List<Transaction> returned = new ArrayList<>();
        result.forEach(returned::add);

        List<Transaction> expected = transactions
                .stream()
                .filter(transaction -> transaction.getTo().equals("Emil"))
                .filter(transaction -> transaction.getAmount() >= 100.00 && transaction.getAmount() < 120.90)
                .sorted(Comparator.comparing(Transaction::getAmount).thenComparing(Transaction::getId))
                .collect(Collectors.toList());

        Assert.assertEquals(returned,expected);
        Assert.assertEquals(2,returned.size());
        Assert.assertEquals(3,returned.get(0).getId());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByReceiverAndAmountRangeThrowException(){
        //  Transaction transaction1 = new TransactionImpl(1,TransactionStatus.SUCCESSFUL,"Desi","Stoyan",150.90);
        // Transaction transaction2 = new TransactionImpl(2,TransactionStatus.SUCCESSFUL,"Desi","Emil",120.90);
        // Transaction transaction3 = new TransactionImpl(3,TransactionStatus.FAILED,"Petar","Emil",100.90);
        fillDataBase();
        this.database.getByReceiverAndAmountRange("Eli",100,120.90);

    }

    @Test
    public void testGetAllInAmountRange(){

        this.database.add(this.transactions.get(0));
        this.database.add(this.transactions.get(1));
        this.database.add(this.transactions.get(2));
        Iterable<Transaction> result = this.database.getAllInAmountRange(100.00,120.90);
        List<Transaction> returned = new ArrayList<>();
        result.forEach(returned::add);


        List<Transaction> expected = transactions
                .stream()
                .filter(transaction -> transaction.getAmount() >= 100.00 && transaction.getAmount() <= 120.90)
                .collect(Collectors.toList());


        Assert.assertEquals(returned,expected);
        Assert.assertEquals(2,returned.size());
        Assert.assertEquals(2,returned.get(0).getId());
    }
    @Test
    public void testIterator(){
        // Transaction transaction1 = new TransactionImpl(1,TransactionStatus.SUCCESSFUL,"Desi","Stoyan",150.90);
        // Transaction transaction2 = new TransactionImpl(2,TransactionStatus.SUCCESSFUL,"Desi","Emil",120.90);
        // Transaction transaction3 = new TransactionImpl(3,TransactionStatus.FAILED,"Petar","Emil",100.90);

        fillDataBase();
        Iterator<Transaction> iterator = this.database.iterator();
        List<Transaction> returned = new ArrayList<>();
        while (iterator.hasNext()){
            returned.add(iterator.next());
        }

        Assert.assertEquals("Desi",returned.get(0).getFrom());
        Assert.assertEquals("Emil",returned.get(1).getTo());
        Assert.assertEquals(100.90,returned.get(2).getAmount(),0.01);
        Assert.assertEquals(3,returned.get(2).getId());
    }

}






































