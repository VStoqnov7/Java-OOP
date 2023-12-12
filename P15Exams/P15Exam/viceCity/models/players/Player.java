package P15Exams.P15Exam.viceCity.models.players;

import P15Exams.P15Exam.viceCity.models.guns.Gun;
import P15Exams.P15Exam.viceCity.repositories.interfaces.Repository;

public interface Player {
    String getName();

    int getLifePoints();

    boolean isAlive();

    Repository<Gun> getGunRepository();

    void takeLifePoints(int points);
}
