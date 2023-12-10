package P15Exams.P11Exam.catHouse.repositories;

import P15Exams.P11Exam.catHouse.entities.toys.Toy;

public interface Repository {

    void buyToy(Toy toy);

    boolean removeToy(Toy toy);

    Toy findFirst(String type);
}
