package P15Exams.P06Exam.goldDigger.repositories;

import java.util.Collection;

public interface Repository<T> {
    Collection<T> getCollection();

    void add(T entity);

    boolean remove(T entity);

    T byName(String name);
}
