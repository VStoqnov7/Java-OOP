package P15Exams.P16Exam.easterRaces.repositories.interfaces;

import java.util.Collection;

public interface Repository<T> {
    T getByName(String name);

    Collection<T> getAll();

    void add(T model);

    boolean remove(T model);
}
