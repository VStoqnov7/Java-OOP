package P15Exams.P15Exam.viceCity.core.interfaces;

public interface Controller {
    String addPlayer(String name);

    String addGun(String type, String name);

    String addGunToPlayer(String name);

    String fight();
}
