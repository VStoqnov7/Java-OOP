package P14UnitTesting.UnitTestings.java.P2UnitTesting_Exercises.P02_ExtendedDatabase;

import P14UnitTesting.Tasks.java.P2UnitTesting_Exercises.P02_ExtendedDatabase.Database;
import P14UnitTesting.Tasks.java.P2UnitTesting_Exercises.P02_ExtendedDatabase.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {

    private Database database;
    private static final Person[] PEOPLE = {new Person(1,"Pesho"),new Person(2,"Gosho")};

    @Before
    public void setup() throws OperationNotSupportedException {
        this.database = new Database(PEOPLE);
    }

    @Test
    public void testConstructorIfIsCorrect() throws OperationNotSupportedException {
        Person[] elements = database.getElements();

        Assert.assertArrayEquals(elements,database.getElements());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testSetElementsMoreThanSixteen() throws OperationNotSupportedException {

        Person[] people = new Person[17];
        new Database(people);

    }

    @Test(expected = OperationNotSupportedException.class)
    public void testSetElementsLessThanOne() throws OperationNotSupportedException {
        Person[] people = new Person[0];
        new Database(people);
    }

    @Test
    public void testAddPerson() throws OperationNotSupportedException {
        database.add(new Person(3,"Petar"));
        Assert.assertEquals(database.getElements().length,PEOPLE.length + 1);

        Person[] people = database.getElements();
        Assert.assertEquals(Integer.valueOf(people[people.length - 1].getId()),Integer.valueOf(3));
        Assert.assertEquals(people[people.length - 1].getUsername(),"Petar");
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddThrowException() throws OperationNotSupportedException {
        database.add(null);
    }


    @Test
    public void testRemoveLastPerson() throws OperationNotSupportedException {
        database.remove();

        Assert.assertEquals(database.getElements().length,PEOPLE.length - 1);

        Person[] people = database.getElements();
        Assert.assertEquals(people[people.length - 1].getId(),1);
        Assert.assertEquals(people[people.length - 1].getUsername(),"Pesho");
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemovePeopleThanThrowException() throws OperationNotSupportedException {
        database = new Database();
        database.remove();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameThrowNullParameters() throws OperationNotSupportedException {
        database.findByUsername(null);
    }

    @Test
    public void testFindByUsername() throws OperationNotSupportedException {
        Person person = database.findByUsername("Pesho");
        Assert.assertEquals(person.getId(),1);
        Assert.assertEquals(person.getUsername(),"Pesho");
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameThrowEmptyDatabase() throws OperationNotSupportedException {
        database = new Database();
        database.findByUsername("Pesho");
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameMoreThanOnePerson() throws OperationNotSupportedException {
        database.add(new Person(2,"Gosho"));
        database.findByUsername("Gosho");

    }

    @Test
    public void testFindById() throws OperationNotSupportedException {
        Person person = database.findById(2);
        Assert.assertEquals(person.getId(),2);
        Assert.assertEquals(person.getUsername(),"Gosho");
    }


    @Test(expected = OperationNotSupportedException.class)
    public void testFindByIdMoreThanOneId() throws OperationNotSupportedException {
        database.add(new Person(2,"Gosho"));
        database.findById(2);

    }
}











































