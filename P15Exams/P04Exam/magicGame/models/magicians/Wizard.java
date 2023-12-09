package P15Exams.P04Exam.magicGame.models.magicians;

import P15Exams.P04Exam.magicGame.models.magics.Magic;

public class Wizard extends MagicianImpl{


    public Wizard(String username, int health, int protection, Magic magic) {
        super(username, health, protection, magic);
    }
}
