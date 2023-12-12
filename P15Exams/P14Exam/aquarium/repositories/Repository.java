package P15Exams.P14Exam.aquarium.repositories;

import P15Exams.P14Exam.aquarium.entities.decorations.Decoration;

public interface Repository  {
    void add(Decoration decoration);

    boolean remove(Decoration decoration);

    Decoration findByType(String type);
}
