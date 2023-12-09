package P15Exams.P04Exam.magicGame.models.magicians;

import P15Exams.P04Exam.magicGame.models.magics.Magic;

public interface Magician {
    String getUsername();

    int getHealth();

    int getProtection();

    Magic getMagic();

    boolean isAlive();

    void takeDamage(int points);
}
