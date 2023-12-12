package P15Exams.P15Exam.viceCity.models.neighbourhood;


import P15Exams.P15Exam.viceCity.models.guns.Gun;
import P15Exams.P15Exam.viceCity.models.players.Player;
import P15Exams.P15Exam.viceCity.repositories.interfaces.Repository;


import java.util.*;


public class GangNeighbourhood implements Neighbourhood {


    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {

        Repository<Gun> tommyGunRepository = mainPlayer.getGunRepository();
        Deque<Gun> tommyGuns = new ArrayDeque<>(tommyGunRepository.getModels());
        Deque<Player> players = new ArrayDeque<>(civilPlayers);


        Player player = players.poll();
        Gun gun = tommyGuns.poll();

        while (player != null && gun != null) {

            while (gun.canFire() && player.isAlive()) {
                int shot = gun.fire();
                player.takeLifePoints(shot);
            }

            if (gun.canFire()) {
                player = players.poll();
            } else {
                gun = tommyGuns.poll();
            }


        }

        for (Player civilPlayer : civilPlayers) {
            if (civilPlayer.isAlive()) {

                Repository<Gun> civilPlayerGunRepository = civilPlayer.getGunRepository();
                Deque<Gun> civilPlayerGuns = new ArrayDeque<>(civilPlayerGunRepository.getModels());
                Gun civilPlayerGun = civilPlayerGuns.poll();
                while (civilPlayerGun != null) {
                    while (civilPlayerGun.canFire() && mainPlayer.isAlive()) {
                        int shot = civilPlayerGun.fire();
                        mainPlayer.takeLifePoints(shot);
                    }

                    if (!mainPlayer.isAlive()){
                        return;
                    }
                   civilPlayerGun = civilPlayerGuns.poll();
                }
            }
        }
    }
}
