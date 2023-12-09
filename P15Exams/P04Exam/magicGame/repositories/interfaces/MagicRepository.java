package P15Exams.P04Exam.magicGame.repositories.interfaces;

import P15Exams.P04Exam.magicGame.models.magics.Magic;

import java.util.Collection;

public interface MagicRepository<T> {
    Collection<T> getData();

    void addMagic(Magic model);

    boolean removeMagic(Magic model);

    T findByName(String name);
}
